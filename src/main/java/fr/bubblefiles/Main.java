package fr.bubblefiles;

import fr.bubblefiles.server.WebServer;

public class Main {
    public static void main(String[] args) {
        try {
            WebServer.launch();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}