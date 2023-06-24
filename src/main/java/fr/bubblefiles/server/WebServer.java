package fr.bubblefiles.server;

import jakarta.servlet.MultipartConfigElement;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;


public class WebServer {

public static void launch() throws Exception {
    Server server = new Server(8113);

    ServletHandler srvhandler = new ServletHandler();
    srvhandler.addServletWithMapping(WebServerListener.class, "/*").getRegistration().setMultipartConfig(new MultipartConfigElement(
            "/Users/thibeau/Downloads/GETTED/"
    ));
    server.setHandler(srvhandler);

    server.start();
    server.join();
}

}
