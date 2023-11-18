import java.math.BigInteger;
import java.security.spec.ECPoint;

public class Courbe_secp112r1 {

    private String Name ="Courbe_secp112r1";
    private BigInteger a = new BigInteger("DB7C2ABF62E35E668076BEAD2088", 16);
    private BigInteger b = new BigInteger("659EF8BA043916EEDE8911702B22", 16);
    private BigInteger p = new BigInteger("DB7C2ABF62E35E668076BEAD2088", 16);
    private BigInteger G = new BigInteger("0209487239995A5EE76B55F9C2F098", 16);
    private BigInteger n = new BigInteger("DB7C2ABF62E35E7628DFAC6561C5", 16);

    public BigInteger getA() {
        return a;
    }





    public BigInteger getB() {
        return b;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getG() {
        return G;
    }

    public BigInteger getN() {
        return n;
    }
}
