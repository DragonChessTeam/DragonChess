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
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Selector.class, ServerSocketChannel.class, ServerThread.class })
public class ServerThreadTests {

    @Mock
    private Selector selector;

    @Mock
    private ServerSocketChannel serverChannel;

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

        mockStatic(ServerSocketChannel.class);
        when(ServerSocketChannel.open()).thenReturn(serverChannel);

        when(serverChannel.register(eq(selector), anyInt())).thenReturn(key);
        when(serverChannel.accept()).thenReturn(channel);

        when(channel.register(eq(selector), anyInt())).thenReturn(key);
        when(channel.write(any(ByteBuffer.class))).thenReturn(0);

        whenNew(MessageChannelImpl.class).withAnyArguments().thenReturn(messageChannel);
    }

    @Test
    public void connectTest() throws IOException, InterruptedException {
        when(key.isAcceptable()).thenReturn(true)
                .thenReturn(false);

        final ServerThread thread = new ServerThread(address, connect, disconnect, receiver);
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();

        verify(key).cancel();

        final ArgumentCaptor<MessageReceiver> captor =
                ArgumentCaptor.forClass(MessageReceiver.class);
        verify(messageChannel).addReceiver(captor.capture());
        assertThat(captor.getValue(), is(receiver));

        verify(messageChannel).send(any(AcceptMessage.class));
        verify(connect).accept(Color.WHITE);
        verify(disconnect).run();
    }
}
