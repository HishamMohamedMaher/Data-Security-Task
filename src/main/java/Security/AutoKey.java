package Security;

import java.util.Locale;

public class AutoKey {
    public String analyse(String plainText, String cipherText) {
        // Students should complete this part
        plainText = plainText.toLowerCase();
        cipherText = cipherText.toLowerCase();

        int len = plainText.length();

        StringBuilder keystream = new StringBuilder();

        for (int i = 0; i < len; i++) {

            int p = plainText.charAt(i) - 'a';
            int c = cipherText.charAt(i) - 'a';

            int k = (c - p + 26) % 26;

            keystream.append((char)(k + 'a'));
        }

        String ks = keystream.toString();

        for (int i = 0; i < ks.length(); i++) {

            if (ks.substring(i).equals(plainText.substring(0, ks.length() - i))) {
                return ks.substring(0, i);
            }
        }

        return ks;

    }

    public String decrypt(String cipherText, String key) {
        // Students should complete this part
        cipherText = cipherText.toLowerCase();
        key = key.toLowerCase();

        int len = cipherText.length();

        StringBuilder plainText = new StringBuilder();
        StringBuilder autoKey = new StringBuilder(key);

        for (int i = 0; i < len; i++) {

            int c = cipherText.charAt(i) - 'a';
            int k = autoKey.charAt(i) - 'a';

            int p = (c - k + 26) % 26;

            char plainChar = (char)(p + 'a');

            plainText.append(plainChar);

            autoKey.append(plainChar);
        }

        return plainText.toString();
        }

    public String encrypt(String plainText, String key) {
        plainText = plainText.toLowerCase();
        key = key.toLowerCase();
        int len = plainText.length();

        // Extend key using the plaintext
        StringBuilder autoKey = new StringBuilder(key);
        if (autoKey.length() < len) {
            int diffLen = len - autoKey.length();
            for (int i = 0; i < diffLen; i++) {
                autoKey.append(plainText.charAt(i));
            }
        }

        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int p = plainText.charAt(i) - 'a';
            int k = autoKey.charAt(i) - 'a';
            cipherText.append((char) (((p + k) % 26) + 'a'));
        }
        return cipherText.toString();
    }
}
