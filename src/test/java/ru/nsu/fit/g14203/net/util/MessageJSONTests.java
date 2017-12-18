package ru.nsu.fit.g14203.net.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.UpdateEntry;

import java.io.IOException;
import java.util.Collections;

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
        assertThat(accept.getClientColor(), is(Color.WHITE));
    }

    @Test
    public void messageUpdateNotNullTest() throws IOException {
        final UpdateEntry entry = new UpdateEntry();
        entry.fullPath = Collections.singletonList(new Dot3D(1, 2, 3));

        final Message message = new UpdateMessage(entry);
        final byte[] bytes = mapper.writeValueAsBytes(message);
        final UpdateMessage update = mapper.readValue(bytes, UpdateMessage.class);

        assertThat(update.getType(), is(message.getType()));
        assertThat(update.getUpdateEntry().pieceToPlace, is((Piece) null));
        assertThat(update.getUpdateEntry().fullPath.get(0).x, is(1));
        assertThat(update.getUpdateEntry().fullPath.get(0).y, is(2));
        assertThat(update.getUpdateEntry().fullPath.get(0).z, is(3));
    }

    @Test
    public void messageUpdateNullTest() throws IOException {
        final Piece piece = new TestPiece(Color.WHITE);

        final UpdateEntry entry = new UpdateEntry();
        entry.fullPath = Collections.singletonList(new Dot3D(1, 2, 3));
        entry.pieceToPlace = piece;

        final Message message = new UpdateMessage(entry);
        final byte[] bytes = mapper.writeValueAsBytes(message);
        final UpdateMessage update = mapper.readValue(bytes, UpdateMessage.class);

        assertThat(update.getType(), is(message.getType()));
        assertThat(update.getUpdateEntry().pieceToPlace, is(piece));
        assertThat(update.getUpdateEntry().fullPath.get(0).x, is(1));
        assertThat(update.getUpdateEntry().fullPath.get(0).y, is(2));
        assertThat(update.getUpdateEntry().fullPath.get(0).z, is(3));
    }

    @Test
    public void messageDisconnectTest() throws IOException {
        final Message message = new DisconnectMessage();
        final byte[] bytes = mapper.writeValueAsBytes(message);
        final DisconnectMessage disconnect = mapper.readValue(bytes, DisconnectMessage.class);

        assertThat(disconnect.getType(), is(message.getType()));
    }
}
