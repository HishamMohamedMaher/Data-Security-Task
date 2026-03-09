import Security.RepeatingKey;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepeatingKeyTest {

    String mainPlain = "wearediscoveredsaveyourself";
    String mainCipherRep = "zicvtwqngrzgvtwavzhcqyglmgj".toUpperCase();
    String mainKey = "deceptive";

    String newPlain = "michigantechnologicaluniversity".toLowerCase();
    String newCipherRep = "TWWNPZOAASWNUHZBNWWGSNBVCSLYPMM".toUpperCase();
    String newKey = "houghton".toLowerCase();

    @Test
    void repVignereTestDec1() {
        RepeatingKey algorithm = new RepeatingKey();
        String plain = algorithm.decrypt(mainCipherRep, mainKey);
        assertTrue(plain.equalsIgnoreCase(mainPlain));
    }

    @Test
    void repVignereTestAnalysis1() {
        RepeatingKey algorithm = new RepeatingKey();
        String key = algorithm.analyse(mainPlain, mainCipherRep);
        assertTrue(key.equalsIgnoreCase(mainKey));
    }

    @Test
    void repVignereTestNewDec() {
        RepeatingKey algorithm = new RepeatingKey();
        String plain = algorithm.decrypt(newCipherRep, newKey);
        assertTrue(plain.equalsIgnoreCase(newPlain));
    }

    @Test
    void repVignereTestNewAnalysis() {
        RepeatingKey algorithm = new RepeatingKey();
        String key = algorithm.analyse(newPlain, newCipherRep);
        assertTrue(key.equalsIgnoreCase(newKey));
    }
}
