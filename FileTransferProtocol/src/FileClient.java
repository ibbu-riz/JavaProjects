import java.io.BufferedOutputStream;
// Class that write the data in the file and store it in a byte array(binary data)
import java.io.FileOutputStream; // Class that write the file
import java.io.InputStream; // Class to communicate with server (to read data from server)
import java.net.Socket; // Socket class

public class FileClient {

    public static void main(String[] args) throws Exception {

        int fileSize = 100000; // buffer size
        int bytesRead; // current statistics of bytes read
        int currentTot = 0; // total of no.of bytes read

        Socket socket = new Socket("localhost", 1234);

        byte[] bytearray = new byte[fileSize]; // byte array (temp variable)
        InputStream is = socket.getInputStream(); // read the file from server

        FileOutputStream fos = new FileOutputStream("E:\\NewWorld.txt");
        // write the file in new file path
        BufferedOutputStream bos = new BufferedOutputStream(fos); // write the data to a new file
        bytesRead = is.read(bytearray, 0, bytearray.length);
        currentTot = bytesRead;

        do {
            bytesRead = is.read(bytearray, currentTot, (bytearray.length - currentTot));
            if (bytesRead >= 0)
                currentTot += bytesRead;
        } while (bytesRead > -1);

        bos.write(bytearray, 0, currentTot);
        bos.flush();
        bos.close();
        socket.close();
    }
}

