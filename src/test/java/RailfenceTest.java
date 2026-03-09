import Security.Railfence;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RailfenceTest {

    // Test Data
    String mainPlain1 = "meetmeaftertheparty";
    String mainPlain2 = "meetmeafterthepartyxx";

    String mainCipher = "mematrhpryetefeteat".toUpperCase();
    String mainCipher2 = "mtaehayemfrereettpt".toUpperCase();
    String mainCipher3 = "mtaehayemfrerxeettptx".toUpperCase();

    int mainKey = 2;
    int mainKey2 = 3;

    String newPlain = "nothingisasitseems";
    String newCipher = "NTIGSSTEMOHNIAISES";
    int newkey = 2;

    @Test
    void railFenceTestAnalysis1() {
        Railfence algorithm = new Railfence();
        int key = algorithm.analyse(mainPlain1, mainCipher);
        assertEquals(mainKey, key);
    }

    @Test
    void railFenceTestAnalysis2() {
        Railfence algorithm = new Railfence();
        int key = algorithm.analyse(mainPlain1, mainCipher2);
        int key2 = algorithm.analyse(mainPlain1, mainCipher3);
        assertTrue(mainKey2 == key || mainKey2 == key2);
    }

    @Test
    void railFenceTestNewAnalysis() {
        Railfence algorithm = new Railfence();
        int key = algorithm.analyse(newPlain, newCipher);
        assertEquals(newkey, key);
    }
}
