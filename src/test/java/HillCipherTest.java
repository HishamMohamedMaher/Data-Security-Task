import Security.HillCipher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HillCipherTest {
    // Helper to create mutable lists (since HillCipher might pad the list)
    private List<Integer> mutableList(Integer... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    // Test Data
    List<Integer> key = List.of(3, 2, 8, 5);
    List<Integer> plain = List.of(15, 0, 24, 12, 14, 17, 4, 12, 14, 13, 4, 24);
    List<Integer> cipher = List.of(19, 16, 18, 18, 24, 15, 10, 14, 16, 21, 8, 22);

    List<Integer> key3 = List.of(17, 17, 5, 21, 18, 21, 2, 2, 19);
    List<Integer> cipher3 = List.of(11, 13, 18, 7, 3, 11, 4, 22, 12, 19, 17, 22);

    List<Integer> plain4 = List.of(5, 21, 2, 5, 2, 16, 19, 14, 1);
    List<Integer> cipher4 = List.of(7, 6, 17, 25, 4, 20, 3, 21, 16);
    List<Integer> key4 = List.of(1, 10, 0, 0, 20, 1, 2, 15, 2);

    @Test
    public void hillCipherTestDec2() {
        HillCipher algorithm = new HillCipher();
        List<Integer> plain2 = algorithm.decrypt(cipher, key);

        Assertions.assertEquals(plain.size(), plain2.size());
        for (int i = 0; i < plain.size(); i++) {
            Assertions.assertEquals(plain.get(i), plain2.get(i));
        }
    }

    @Test
    public void hillCipherTestDec4() {
        HillCipher algorithm = new HillCipher();
        List<Integer> plain2 = algorithm.decrypt(cipher3, key3);

        for (int i = 0; i < plain.size(); i++) {
            Assertions.assertEquals(plain.get(i), plain2.get(i));
        }
    }

    @Test
    public void hillCipherTestDec6() {
        HillCipher algorithm = new HillCipher();
        List<Integer> plain2 = algorithm.decrypt(cipher4, key4);

        for (int i = 0; i < plain4.size(); i++) {
            Assertions.assertEquals(plain4.get(i), plain2.get(i));
        }
    }

    @Test
    public void hillCipherTest3By3Analysis2() {
        HillCipher algorithm = new HillCipher();
        List<Integer> resultKey = algorithm.analyse3By3Key(plain4, cipher4);
        for (int i = 0; i < key4.size(); i++) {
            assertEquals(key4.get(i), resultKey.get(i));
        }
    }
}
