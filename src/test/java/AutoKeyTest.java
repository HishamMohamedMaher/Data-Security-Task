import Security.AutoKey;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutoKeyTest {

    String mainPlain = "wearediscoveredsaveyourself";
    String mainCipherAuto = "zicvtwqngkzeiigasxstslvvwla".toUpperCase();
    String mainKey = "deceptive";

    String newPlain = "michigantechnologicaluniversity".toLowerCase();
    String newCipherAuto = "TWWNPZOAFMEOVULBZMEHYIYWBMTSTNL".toUpperCase();
    String newKey = "houghton".toLowerCase();

    @Test
    void autoVignereTestDec1() {
        AutoKey algorithm = new AutoKey();
        String plain = algorithm.decrypt(mainCipherAuto, mainKey);
        assertTrue(plain.equalsIgnoreCase(mainPlain));
    }

    @Test
    void autoVignereTestAnalysis1() {
        AutoKey algorithm = new AutoKey();
        String key = algorithm.analyse(mainPlain, mainCipherAuto);
        assertTrue(key.equalsIgnoreCase(mainKey));
    }

    @Test
    void autoVignereTestNewDec() {
        AutoKey algorithm = new AutoKey();
        String plain = algorithm.decrypt(newCipherAuto, newKey);
        assertTrue(plain.equalsIgnoreCase(newPlain));
    }

    @Test
    void autoVignereTestNewAnalysis() {
        AutoKey algorithm = new AutoKey();
        String key = algorithm.analyse(newPlain, newCipherAuto);
        assertTrue(key.equalsIgnoreCase(newKey));
    }
}
