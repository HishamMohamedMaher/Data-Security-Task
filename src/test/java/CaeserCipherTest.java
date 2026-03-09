import Security.CaeserCipher;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CaeserCipherTest {

    private final CaeserCipher algorithm = new CaeserCipher();

    // Test Data
    String mainPlain = "meetmeaftertheparty";
    String mainCipher = "phhwphdiwhuwkhsduwb".toUpperCase();
    int mainKey = 3;

    String mainPlain1 = "defendtheeastwallofthecastle";
    String mainCipher1 = "defendtheeastwallofthecastle".toUpperCase();
    int mainKey1 = 0;

    String mainPlain2 = "defendtheeastwallofthecastle";
    String mainCipher2 = "bcdclbrfccyqruyjjmdrfcayqrjc".toUpperCase();
    int mainKey2 = 24;

    String newPlain = "THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG".toLowerCase();
    String newCipher = "WKHTXLFNEURZQIRAMXPSVRYHUWKHODCBGRJ".toUpperCase();
    int newKey = 3;

    @Test
    public void caesarTestAnalysis1() {
        int key = algorithm.analyse(mainPlain, mainCipher);
        assertEquals(mainKey, key);
    }

    @Test
    public void caesarTestAnalysis2() {
        int key = algorithm.analyse(mainPlain1, mainCipher1);
        assertEquals(mainKey1, key);
    }

    @Test
    public void caesarTestAnalysis3() {
        int key = algorithm.analyse(mainPlain2, mainCipher2);
        assertEquals(mainKey2, key);
    }

    @Test
    public void caesarTestNewAnalysis1() {
        int key = algorithm.analyse(newPlain, newCipher);
        assertEquals(newKey, key);
    }
}