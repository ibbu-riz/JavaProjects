import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        int port;
        BufferedReader Buf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the Port Address: ");
        port = Integer.parseInt(Buf.readLine());
        ServerSocket ss = new ServerSocket(port);
        System.out.println("Server is Ready to Receive a Command");
        System.out.println("Waiting...");
        Socket s = ss.accept();
        if (s.isConnected()) {
            System.out.println("Client Socket is Connected Successfully");
        }
        InputStream in = s.getInputStream();
        OutputStream ou = s.getOutputStream();
        BufferedReader buf = new BufferedReader(new InputStreamReader(in));
        String cmd = buf.readLine();
        PrintWriter pr = new PrintWriter(ou);
        pr.println(cmd);
        Runtime H = Runtime.getRuntime();
        Process p;
//        p = H.exec(cmd);
        p = H.exec(new String[]{cmd, "/K", "Start"});
        System.out.println("The " + cmd + " Command is Executed Successfully");
        pr.flush();
        ou.close();
        in.close();
        s.close();
    }
}

