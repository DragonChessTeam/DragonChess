package ru.nsu.fit.g14203.net.util;

import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.IOException;

public interface MessageChannel extends Closeable {

    boolean addReceiver(MessageReceiver messageReceiver);
    boolean removeReceiver(MessageReceiver messageReceiver);

    void send(@NotNull Message message) throws IOException;
}
