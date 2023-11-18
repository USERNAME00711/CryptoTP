
import java.io.IOException;

import java.security.NoSuchAlgorithmException;

public class User1 {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InterruptedException {

        String name = "ALICE" ;
        ECDH algo = new ECDH();


        System.out.println("shared key   "+algo.Calculate_shared_key(name)); ;





    }
}
