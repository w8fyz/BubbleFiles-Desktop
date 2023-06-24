package fr.bubblefiles;

import fr.bubblefiles.server.WebServer;
import fr.bubblefiles.windows.MainWindow;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);
         });

        try {
            WebServer.launch();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}