package ru.nsu.fit.g14203.net;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.api.Observer;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.EngineResponse;
import ru.nsu.fit.g14203.engine.api.utils.Way;
import ru.nsu.fit.g14203.net.channel.*;
import ru.nsu.fit.g14203.net.game.GameThread;
import ru.nsu.fit.g14203.net.game.GameThreadFactory;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GameThreadFactory.class)
public class NetEngineTests {

    @Mock
    private Engine engine;

    @Mock
    private GameThread thread;

    @Mock
    private MessageChannel channel;

    @Mock
    private Consumer<Color> connectHandler;

    @Mock
    private Runnable disconnectHandler;

    @Mock
    private Observer observer;

    @Mock
    private SocketAddress address;

    @Before
    public void prepare() throws Exception {
        when(thread.getMessageChannel()).thenReturn(channel);

        mockStatic(GameThreadFactory.class);
        when(GameThreadFactory.createGame(anyInt(), eq(address),
                eq(connectHandler), eq(disconnectHandler),
                any(MessageReceiver.class))).thenReturn(thread);
    }

    @Test
    public void captureTest() throws IOException {
        when(engine.doCapture(any(Color.class), any(Way.class))).thenReturn(EngineResponse.OK);

        final Way way = new Way(new Dot3D(1, 2, 3), new Dot3D(3, 2, 1));

        final NetEngine netEngine = new NetEngine(NetEngine.TYPE_SERVER, address, engine,
                connectHandler, disconnectHandler);
        final EngineResponse response = netEngine.doCapture(Color.WHITE, way);

        assertThat(response, is(EngineResponse.OK));

        verify(engine).doCapture(Color.WHITE, way);

        final ArgumentCaptor<StepMessage> captor = ArgumentCaptor.forClass(StepMessage.class);
        verify(channel).send(captor.capture());

        final StepMessage step = captor.getValue();
        assertThat(step.getType(), is(Message.TYPE_CAPTURE));
        assertThat(step.getColor(), is(Color.WHITE));
        assertThat(step.getWay(), is(way));
    }

    @Test
    public void moveTest() throws IOException {
        when(engine.doMove(any(Color.class), any(Way.class))).thenReturn(EngineResponse.OK);

        final Way way = new Way(new Dot3D(1, 2, 3), new Dot3D(3, 2, 1));

        final NetEngine netEngine = new NetEngine(NetEngine.TYPE_SERVER, address, engine,
                connectHandler, disconnectHandler);
        final EngineResponse response = netEngine.doMove(Color.WHITE, way);

        assertThat(response, is(EngineResponse.OK));

        verify(engine).doMove(Color.WHITE, way);

        final ArgumentCaptor<StepMessage> captor = ArgumentCaptor.forClass(StepMessage.class);
        verify(channel).send(captor.capture());

        final StepMessage step = captor.getValue();
        assertThat(step.getType(), is(Message.TYPE_MOVE));
        assertThat(step.getColor(), is(Color.WHITE));
        assertThat(step.getWay(), is(way));
    }

    @Test
    public void registerObserverTest() throws IOException {
        final NetEngine netEngine = new NetEngine(NetEngine.TYPE_SERVER, address, engine,
                connectHandler, disconnectHandler);
        netEngine.registerObserver(observer);

        verify(engine).registerObserver(observer);
    }

    @Test
    public void deleteObserverTest() throws IOException {
        final NetEngine netEngine = new NetEngine(NetEngine.TYPE_SERVER, address, engine,
                connectHandler, disconnectHandler);
        netEngine.deleteObserver(observer);

        verify(engine).deleteObserver(observer);
    }

    @Test
    public void receiveDisconnect() throws IOException {
        final NetEngine netEngine = new NetEngine(NetEngine.TYPE_SERVER, address, engine,
                connectHandler, disconnectHandler);
        netEngine.receive(new DisconnectMessage());

        verify(thread).interrupt();
    }

    @Test
    public void receiveCapture() throws IOException {
        when(engine.doCapture(any(Color.class), any(Way.class))).thenReturn(EngineResponse.OK);

        final Way way = new Way(new Dot3D(1, 2, 3), new Dot3D(3, 2, 1));

        final NetEngine netEngine = new NetEngine(NetEngine.TYPE_SERVER, address, engine,
                connectHandler, disconnectHandler);
        netEngine.receive(new StepMessage(Message.TYPE_CAPTURE, Color.WHITE, way));

        verify(engine).doCapture(Color.WHITE, way);
    }

    @Test
    public void receiveMove() throws IOException {
        when(engine.doMove(any(Color.class), any(Way.class))).thenReturn(EngineResponse.OK);

        final Way way = new Way(new Dot3D(1, 2, 3), new Dot3D(3, 2, 1));

        final NetEngine netEngine = new NetEngine(NetEngine.TYPE_SERVER, address, engine,
                connectHandler, disconnectHandler);
        netEngine.receive(new StepMessage(Message.TYPE_MOVE, Color.WHITE, way));

        verify(engine).doMove(Color.WHITE, way);
    }
}
