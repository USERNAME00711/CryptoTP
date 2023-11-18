import java.io.IOException;

import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class User2 {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        String name = "BOB";
        Socket socket = null;


        ECDH algo = new ECDH();

        System.out.println("shared key   "+algo.Calculate_shared_key(name)); ;




    }
}
