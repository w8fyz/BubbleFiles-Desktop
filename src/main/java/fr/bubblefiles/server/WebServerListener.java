package fr.bubblefiles.server;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.eclipse.jetty.server.MultiPartFormInputStream;
import org.eclipse.jetty.util.IO;
import org.eclipse.jetty.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class WebServerListener  extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      processParts(req, resp, Path.of("/Users/thibeau/Downloads/GETTED/"));
    }

    public static void processParts(HttpServletRequest request, HttpServletResponse response, Path outputDir) throws ServletException, IOException
    {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        for (Part part : request.getParts()){
            out.printf("Got Part[%s].size=%s%n", part.getName(), part.getSize());
            out.printf("Got Part[%s].contentType=%s%n", part.getName(), part.getContentType());
            out.printf("Got Part[%s].submittedFileName=%s%n", part.getName(), part.getSubmittedFileName());
            String filename = part.getSubmittedFileName();
            if (StringUtil.isNotBlank(filename)) {
                filename = URLEncoder.encode(filename, "utf-8");

                Path outputFile = outputDir.resolve(filename);
                try (InputStream inputStream = part.getInputStream();
                     OutputStream outputStream = Files.newOutputStream(outputFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                    IO.copy(inputStream, outputStream);
                    out.printf("Saved Part[%s] to %s%n", part.getName(), outputFile);
                }
            }
        }
    }

}