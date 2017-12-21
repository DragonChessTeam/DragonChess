package ru.nsu.fit.g14203.net.game;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.net.channel.AcceptMessage;
import ru.nsu.fit.g14203.net.channel.ConnectMessage;
import ru.nsu.fit.g14203.net.channel.MessageChannelImpl;
import ru.nsu.fit.g14203.net.channel.MessageReceiver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Selector.class, SocketChannel.class, ClientThread.class })
public class ClientThreadTests {

    @Mock
    private Selector selector;

    @Mock
    private SocketChannel channel;

    @Mock
    private SelectionKey key;

    @Mock
    private MessageChannelImpl messageChannel;

    @Mock
    private Consumer<Color> connect;

    @Mock
    private Runnable disconnect;

    @Mock
    private MessageReceiver receiver;

    @Mock
    private SocketAddress address;

    @Before
    public void prepare() throws Exception {
        mockStatic(Selector.class);
        when(Selector.open()).thenReturn(selector);

        mockStatic(SocketChannel.class);
        when(SocketChannel.open()).thenReturn(channel);

        when(channel.register(eq(selector), anyInt())).thenReturn(key);

        whenNew(MessageChannelImpl.class).withAnyArguments().thenReturn(messageChannel);
    }

    @Test
    public void connectTest() throws IOException, InterruptedException {
        when(key.isConnectable()).thenReturn(true)
                .thenReturn(false);
        when(key.interestOps()).thenReturn(SelectionKey.OP_CONNECT);

        final ClientThread thread = new ClientThread(address, connect, disconnect, receiver);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();

        verify(key).interestOps(0);

        final ArgumentCaptor<MessageReceiver> captor =
                ArgumentCaptor.forClass(MessageReceiver.class);
        verify(messageChannel).addReceiver(captor.capture());
        captor.getValue().receive(new AcceptMessage(Color.WHITE));

        verify(messageChannel, times(2)).addReceiver(captor.capture());
        assertThat(captor.getValue(), is(receiver));

        verify(messageChannel).send(any(ConnectMessage.class));
        verify(connect).accept(Color.WHITE);
        verify(disconnect).run();
    }

    @Test
    public void writeTest() throws InterruptedException, IOException {
        when(key.isWritable()).thenReturn(true)
                .thenReturn(false);

        final ClientThread thread = new ClientThread(address, connect, disconnect, receiver);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();

        verify(messageChannel).writeChannel();
    }

    @Test
    public void readTest() throws InterruptedException, IOException {
        when(key.isReadable()).thenReturn(true)
                .thenReturn(false);

        final ClientThread thread = new ClientThread(address, connect, disconnect, receiver);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();

        verify(messageChannel).readChannel();
    }
}
