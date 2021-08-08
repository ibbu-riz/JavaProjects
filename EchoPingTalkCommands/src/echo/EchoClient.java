package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) throws Exception {
        String sockin;
        try {
            Socket csock = new Socket(InetAddress.getLocalHost(), 2000);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader br_sock = new BufferedReader(new InputStreamReader(csock.getInputStream()));
            PrintStream ps = new PrintStream(csock.getOutputStream());
            System.out.println("Start echoing... type 'end' to terminate");
            while ((sockin = br.readLine()) != null) {
                ps.println(sockin);
                if (sockin.equals("end"))
                    break;
                else
                    System.out.println("echoed from server:" + br_sock.readLine());

            }
        } catch (UnknownHostException e) {
            System.out.println(e.toString());

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
