package ru.nsu.fit.g14203.net;

public class ServerDisabledException extends Exception {

    public ServerDisabledException() {
        super("Server option disabled");
    }
}
