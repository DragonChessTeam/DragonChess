package ru.nsu.fit.g14203.net.channel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.Way;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MessageJSONTests {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void messageConnectTest() throws IOException {
        final Message message = new ConnectMessage();
        final byte[] bytes = mapper.writeValueAsBytes(message);
        final ConnectMessage connect = mapper.readValue(bytes, ConnectMessage.class);

        assertThat(connect.getType(), is(message.getType()));
    }

    @Test
    public void messageAcceptTest() throws IOException {
        final Message message = new AcceptMessage(Color.WHITE);
        final byte[] bytes = mapper.writeValueAsBytes(message);
        final AcceptMessage accept = mapper.readValue(bytes, AcceptMessage.class);

        assertThat(accept.getType(), is(message.getType()));
        assertThat(accept.getColor(), is(Color.WHITE));
    }

    @Test
    public void messageDisconnectTest() throws IOException {
        final Message message = new DisconnectMessage();
        final byte[] bytes = mapper.writeValueAsBytes(message);
        final DisconnectMessage disconnect = mapper.readValue(bytes, DisconnectMessage.class);

        assertThat(disconnect.getType(), is(message.getType()));
    }

    @Test
    public void messageStepTest() throws IOException {
        final Way way = new Way(new Dot3D(1, 2, 3), new Dot3D(3, 2, 1));

        final Message message = new StepMessage(Message.TYPE_CAPTURE, Color.WHITE, way);
        final byte[] bytes = mapper.writeValueAsBytes(message);
        final StepMessage step = mapper.readValue(bytes, StepMessage.class);

        assertThat(step.getType(), is(message.getType()));
        assertThat(step.getColor(), is(Color.WHITE));
        assertThat(step.getWay().start, is(step.getWay().start));
        assertThat(step.getWay().end, is(step.getWay().end));
    }
}
