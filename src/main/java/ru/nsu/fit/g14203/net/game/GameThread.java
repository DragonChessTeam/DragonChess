package ru.nsu.fit.g14203.net.game;

import ru.nsu.fit.g14203.net.channel.MessageChannel;

public interface GameThread {

    void start();
    void interrupt();

    MessageChannel getMessageChannel();
}
