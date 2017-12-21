package ru.nsu.fit.g14203.net.channel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ SelectableByteChannel.class, MessageChannelImpl.class })
public class MessageChannelTests {

    @Mock
    private SelectionKey key;

    @Mock
    private SelectableByteChannel channel;

    @Mock
    private MessageReceiver receiver;

    private final ObjectMapper mapper = new ObjectMapper();

    @Before
    public void prepare() throws IOException {
        when(key.channel())
                .thenReturn(channel);
        when(key.interestOps(anyInt()))
                .thenReturn(key);
        when(key.interestOps())
                .thenReturn(0)
                .thenReturn(SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        doNothing().when(channel).close();
    }

    @Test
    public void writeChannelTest() throws IOException {
        final Message send = new ConnectMessage();
        final byte[] sendBytes = mapper.writeValueAsBytes(send);

        final ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES + sendBytes.length);
        when(channel.write(any(ByteBuffer.class))).then(put3Bytes(buffer));

//        ------   test begin   ------
        final MessageChannelImpl messageChannel = new MessageChannelImpl(key);
        messageChannel.send(send);
        for (int i = 0; i < buffer.limit(); i += 3) {
            messageChannel.writeChannel();
        }
//        ------   test end   ------

        final ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        verify(key, times(2)).interestOps(captor.capture());
        assertThat(captor.getAllValues().get(0), is(SelectionKey.OP_WRITE));
        assertThat(captor.getAllValues().get(1), is(SelectionKey.OP_READ));

        buffer.flip();
        final int length = buffer.getInt();
        assertThat(length, is(sendBytes.length));

        final byte[] bytes = new byte[sendBytes.length];
        buffer.get(bytes);
        assertThat(bytes, is(sendBytes));
    }

    private Answer<Integer> put3Bytes(ByteBuffer dst) {
        return mock -> {
            final ByteBuffer src = (ByteBuffer) mock.getArguments()[0];

            int ret;
            for (ret = 0; ret < 3 && src.hasRemaining(); ret++) {
                dst.put(src.get());
            }

            return ret;
        };
    }

    @Test
    public void readChannelTest() throws IOException {
        final Message receive = new ConnectMessage();
        final byte[] receiveBytes = mapper.writeValueAsBytes(receive);

        final ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES + receiveBytes.length);
        buffer.putInt(receiveBytes.length);
        buffer.put(receiveBytes);
        buffer.flip();
        when(channel.read(any(ByteBuffer.class))).then(get3Bytes(buffer));

//        ------   test begin   ------
        final MessageChannelImpl messageChannel = new MessageChannelImpl(key);
        final boolean add = messageChannel.addReceiver(receiver);
        for (int i = 0; i < buffer.limit(); i += 3) {
            messageChannel.readChannel();
        }
        final boolean remove = messageChannel.removeReceiver(receiver);
//        ------   test end   ------

        assertThat(add, is(true));
        assertThat(remove, is(true));

        final ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        verify(key, times(2)).interestOps(captor.capture());
        assertThat(captor.getAllValues().get(0), is(SelectionKey.OP_READ));
        assertThat(captor.getAllValues().get(1), is(SelectionKey.OP_WRITE));

        verify(receiver).receive(any(ConnectMessage.class));
    }

    private Answer<Integer> get3Bytes(ByteBuffer src) {
        return mock -> {
            final ByteBuffer dst = (ByteBuffer) mock.getArguments()[0];

            int ret;
            for (ret = 0; ret < 3 && dst.hasRemaining(); ret++) {
                dst.put(src.get());
            }

            return ret;
        };
    }

    @Test
    public void closeTest() throws IOException {
        final MessageChannelImpl messageChannel = new MessageChannelImpl(key);
        messageChannel.addReceiver(receiver);
        messageChannel.close();

        verify(channel).close();
        verify(receiver).receive(any(DisconnectMessage.class));
    }
}
