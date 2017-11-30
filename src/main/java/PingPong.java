import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class PingPong {


    public static void main(String[] args) throws Exception {
        GameMonitor monitor = new GameMonitor();
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/click", new MyHandler(monitor));
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        private GameMonitor monitor;
        public MyHandler (GameMonitor irgendeinname) {
            this.monitor = irgendeinname;
        }
        @Override
        public void handle(HttpExchange t) throws IOException {
            InputStream bodyStream = t.getRequestBody();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode payload = objectMapper.readTree(bodyStream);

            this.monitor.buttonPress(payload);

            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}
/**
 *
 * {
 *     button: '1',
 *     actionType: 'click'
 * }
 *
 *
 **/