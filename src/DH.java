import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class DH {

    private BigInteger secretKey ;
    private BigInteger p ;
    private  BigInteger g ;

    public DH() throws NoSuchAlgorithmException {
        secretKey = this.generate_key(256);
        p = this.generate_key(256);
        g = this.generate_key(256);

    }

    public BigInteger getSecretKey(){
        return this.secretKey;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getG() {
        return g;
    }

    public BigInteger generate_key(int keySize) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[keySize / 8];
        random.nextBytes(bytes);
        return new BigInteger(1, bytes);
    }
    public void send_key( ) throws IOException {

        Socket socket = new Socket("127.0.0.1", 8181);


        BigInteger Q1 = g.modPow(secretKey,p);

        try (DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream())) {
            byte[] numberBytes = Q1.toByteArray();

            // Write the length of the byte array
            outputStream.writeInt(numberBytes.length);

            // Write the byte array
            outputStream.write(numberBytes);
            System.out.println("key sent!");
            socket.close();
            Thread.sleep(800);


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public BigInteger recieve_key( ) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8181) ;
        System.out.println("Listening ...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket.getInetAddress());

        try (DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream())) {
            System.out.println("Got something");

            // Read the length of the byte array
            int length = inputStream.readInt();

            // Read the byte array
            byte[] numberBytes = new byte[length];
            inputStream.readFully(numberBytes);

            // Convert the byte array to a BigInteger
            BigInteger receivedNumber = new BigInteger(numberBytes);

            serverSocket.close();

            return receivedNumber;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public BigInteger Calculate_shared_key(String name ) throws IOException {

        if ( name.equals("ALICE")){
            this.send_key() ;
            System.out.println("i'm alice !");


            BigInteger recieved = this.recieve_key();
            return recieved.multiply(this.getSecretKey());


        }
        else    {
            System.out.println("i'm bob !");

            BigInteger recieved = this.recieve_key();
            this.send_key();

            return recieved.multiply(this.getSecretKey());

        }

    }

}
