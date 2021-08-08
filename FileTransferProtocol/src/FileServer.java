import java.io.BufferedInputStream;
// Class that reads the data in the file and store it in a byte array(binary data)
import java.io.File; // File class
import java.io.FileInputStream; // Class that reads the file
import java.io.OutputStream; // Class to communicate with client (to write data to client)
import java.net.ServerSocket; // ServerSocket class
import java.net.Socket; // Socket class

public class FileServer {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Server is running...Waiting for client request...");
        Socket socket = serverSocket.accept();
        System.out.println("Connection Established !");
        System.out.println("Accepted connection : " + socket);

        File transferFile = new File("D:\\HelloWorld.txt"); // file path
        byte[] bytearray = new byte[(int) transferFile.length()];
        // bytearray(binary data) to store the contents of a file

        FileInputStream fin = new FileInputStream(transferFile); // reads the file
        BufferedInputStream bin = new BufferedInputStream(fin);
        // reads the file faster(buffer mechanism)
        bin.read(bytearray, 0, bytearray.length);
        // read the file and store the data in the bytearray

        OutputStream os = socket.getOutputStream(); // communicate with client
        System.out.println("Sending Files...");
        os.write(bytearray, 0, bytearray.length); // write the file and send to client

        serverSocket.close();
        bin.close();
        os.flush();
        socket.close();

        System.out.println("File transfer complete");
    }
}
