package echoserver;
import java.net.*;
import java.io.*;

public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) throws IOException {
        String server;
        // Use "127.0.0.1", i.e., localhost, if no server is specified.
        if (args.length == 0) {
            server = "127.0.0.1";
        } else {
            server = args[0];
        }

        try {
            // Connect to the server
            Socket socket = new Socket(server, portNumber);

            // The output stream
            OutputStream writer = socket.getOutputStream();
            // Get the bite from the keyboard, writes it to server in the next while loop
            InputStream key = System.in;
            // Get the input stream so we can read from that socket
            InputStream reader = socket.getInputStream();

            // Print all the input we receive from the server
            int keyValue;
            int input;
            while ((keyValue = key.read()) != -1) {
                writer.write(keyValue);
                input = reader.read();
                System.out.write(input);
            }

            // Close the socket when we're done reading from it
            socket.close();

            // Flush out the writer data
            System.out.flush();

            // Provide some minimal error handling.
        } catch (ConnectException ce) {
            System.out.println("We were unable to connect to " + server);
            System.out.println("You should make sure the server is running.");
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}