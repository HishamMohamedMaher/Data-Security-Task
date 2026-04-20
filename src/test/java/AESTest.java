import Security.AES;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AESTest {

    private final AES algorithm = new AES();

    // Test Vectors
    private final String mainPlain = "0x3243F6A8885A308D313198A2e0370734";
    private final String mainCipher = "0x3925841D02DC09FBDC118597196A0B32";
    private final String mainKey = "0x2B7E151628AED2A6ABF7158809CF4F3C";

    private final String mainPlain2 = "0x00000000000000000000000000000001";
    private final String mainCipher2 = "0x58e2fccefa7e3061367f1d57a4e7455a";
    private final String mainKey2 = "0x00000000000000000000000000000000";

    private final String mainPlain3 = "0x00112233445566778899aabbccddeeff";
    private final String mainCipher3 = "0x69c4e0d86a7b0430d8cdb78070b4c55a";
    private final String mainKey3 = "0x000102030405060708090a0b0c0d0e0f";

    private final String newPlain = "0x54776F204F6E65204E696E652054776F";
    private final String newCipher = "0x29C3505F571420F6402299B31A02D73A";
    private final String newKey = "0x5468617473206D79204B756E67204675";

    @Test
    public void aesTestEnc1() {
        String cipher = algorithm.encrypt(mainPlain, mainKey);
        Assertions.assertTrue(cipher.equalsIgnoreCase(mainCipher));
    }

    @Test
    public void aesTestDec1() {
        String plain = algorithm.decrypt(mainCipher, mainKey);
        Assertions.assertTrue(plain.equalsIgnoreCase(mainPlain));
    }

    @Test
    public void aesTestEnc2() {
        String cipher = algorithm.encrypt(mainPlain2, mainKey2);
        Assertions.assertTrue(cipher.equalsIgnoreCase(mainCipher2));
    }

    @Test
    public void aesTestDec2() {
        String plain = algorithm.decrypt(mainCipher2, mainKey2);
        Assertions.assertTrue(plain.equalsIgnoreCase(mainPlain2));
    }

    @Test
    public void aesTestEnc3() {
        String cipher = algorithm.encrypt(mainPlain3, mainKey3);
        Assertions.assertTrue(cipher.equalsIgnoreCase(mainCipher3));
    }

    @Test
    public void aesTestDec3() {
        String plain = algorithm.decrypt(mainCipher3, mainKey3);
        Assertions.assertTrue(plain.equalsIgnoreCase(mainPlain3));
    }

    @Test
    public void aesTestNewEnc() {
        String cipher = algorithm.encrypt(newPlain, newKey);
        Assertions.assertTrue(cipher.equalsIgnoreCase(newCipher));
    }

    @Test
    public void aesTestNewDec() {
        String plain = algorithm.decrypt(newCipher, newKey);
        Assertions.assertTrue(plain.equalsIgnoreCase(newPlain));
    }
}