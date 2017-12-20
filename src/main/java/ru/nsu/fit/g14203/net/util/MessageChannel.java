package ru.nsu.fit.g14203.net.util;

import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.IOException;

/**
 * Provides event-based interface over ByteChannel.
 */
public interface MessageChannel extends Closeable {

    /**
     * Add message receiver to the receivers set if it is not already present.
     *
     * @param messageReceiver receiver to be added
     * @return <tt>true<tt/> if receiver set did not already contain equal receiver
     */
    boolean addReceiver(MessageReceiver messageReceiver);

    /**
     * Remove message receiver from the receivers set if it is present.
     *
     * @param messageReceiver receiver to be removed, if present
     * @return <tt>true</tt> if receiver set did contain equal receiver
     */
    boolean removeReceiver(MessageReceiver messageReceiver);

    /**
     * Add message to the send queue.
     *
     * @param message to be added
     */
    void send(@NotNull Message message);
}
