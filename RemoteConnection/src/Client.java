import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        int port;
        BufferedReader Buf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the Port Address: ");
        port = Integer.parseInt(Buf.readLine());
        Socket s = new Socket("localhost", port);
        if (s.isConnected()) {
            System.out.println("Server Socket is Connected Successfully");
        }
        InputStream in = s.getInputStream();
        OutputStream ou = s.getOutputStream();
        BufferedReader buf = new BufferedReader(new InputStreamReader(in));
        PrintWriter pr = new PrintWriter(ou);
        System.out.println("Enter the Command to be Executed: ");
        pr.println(Buf.readLine());
        pr.flush();
        String cmd = buf.readLine();
        System.out.println(cmd + " Opened Successfully");
        System.out.println("The " + cmd + " Command is Executed Successfully");
        pr.close();
        ou.close();
        in.close();
        s.close();
    }
}
