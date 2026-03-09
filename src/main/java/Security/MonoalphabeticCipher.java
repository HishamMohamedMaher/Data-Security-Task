package Security;

import java.util.*;

public class MonoalphabeticCipher {

    public String analyse(String plainText, String cipherText) {
        cipherText = cipherText.toLowerCase();
        plainText = plainText.toLowerCase();
        char[] key = new char[26];

        // Initialize with null characters
        Arrays.fill(key, '\0');

        // Map existing characters from plain to cipher
        for (int i = 0; i < plainText.length(); i++) {
            int m = plainText.charAt(i) - 'a';
            key[m] = cipherText.charAt(i);
        }

        // Fill remaining empty spots in the key with unused letters
        for (int i = 0; i < 26; i++) {
            if (key[i] == '\0') {
                for (int j = 0; j < 26; j++) {
                    char c = (char) (j + 'a');
                    boolean found = false;
                    for (int k = 0; k < 26; k++) {
                        if (key[k] == c) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        key[i] = c;
                        break;
                    }
                }
            }
        }
        return new String(key);
    }

    public String decrypt(String cipherText, String key) {
        cipherText = cipherText.toLowerCase();
        key = key.toLowerCase();
        StringBuilder plainText = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i++) {
            char letter = cipherText.charAt(i);
            int j = key.indexOf(letter);

            if (j != -1) {
                char l = (char) (j + 'a');
                plainText.append(l);
            }
        }
        return plainText.toString();
    }

    public String encrypt(String plainText, String key) {
        plainText = plainText.toLowerCase();
        key = key.toLowerCase();
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            int index = plainText.charAt(i) - 'a';
            ciphertext.append(key.charAt(index));
        }
        return ciphertext.toString();
    }


    /// Frequency Information:
        /// E   12.51%
        /// T	9.25
        /// A	8.04
        /// O	7.60
        /// I	7.26
        /// N	7.09
        /// S	6.54
        /// R	6.12
        /// H	5.49
        /// L	4.14
        /// D	3.99
        /// C	3.06
        /// U	2.71
        /// M	2.53
        /// F	2.30
        /// P	2.00
        /// G	1.96
        /// W	1.92
        /// Y	1.73
        /// B	1.54
        /// V	0.99
        /// K	0.67
        /// X	0.19
        /// J	0.16
        /// Q	0.11
        /// Z	0.09
    public String analyseUsingCharFrequency(String cipher) {
        // Students should complete this part
        return null;
    }
}
