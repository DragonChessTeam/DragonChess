package ru.nsu.fit.g14203.net.util;

/**
 * Observer interface for MessageChannel.
 */
public interface MessageReceiver {

    /**
     * MessageChannel invokes this method when retrieves Message from underlying ByteChannel.
     *
     * @param message new message
     */
    void receive(Message message);
}
