package Security;

import java.util.List;

public class ElGamal {
    public List<Long> encrypt(int q, int alpha, int y, int k, int m) {
        long c1 = modPow(alpha, k, q);
        long s = modPow(y, k, q);
        long c2 = (m * s) % q;
        return List.of(c1, c2);
    }

    public int decrypt(int c1, int c2, int x, int q) {
        long s = modPow(c1, x, q);
        long sInverse = modPow(s, q - 2, q); // s^(q-2) mod q
        long m = (c2 * sInverse) % q;

        return (int) m;
    }

    public static long modPow(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            exp >>= 1;
            base = (base * base) % mod;
        }
        return result;
    }
}
