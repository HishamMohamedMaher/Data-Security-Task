package Security;

import java.util.List;

public class DiffieHellman {
    public List<Integer> getKeys(int q, int alpha, int xa, int xb) {
        long ya = ElGamal.modPow(alpha, xa, q); // Alice public key
        long yb = ElGamal.modPow(alpha, xb, q); // Bob public key
        int keyA = (int) ElGamal.modPow(yb, xa, q);
        int keyB = (int) ElGamal.modPow(ya, xb, q);

        return List.of(keyA, keyB);
    }
}
