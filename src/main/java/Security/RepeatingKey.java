package Security;

public class RepeatingKey {
    public String analyse(String plainText, String cipherText) {
        // Students should complete this part
        return null;
    }

    public String decrypt(String cipherText, String key) {
        // Students should complete this part
        return null;
    }

    public String encrypt(String plainText, String key) {
        plainText = plainText.toLowerCase();
        key = key.toLowerCase();
        int plainLen = plainText.length();

        // Repeat key to match plaintext length
        StringBuilder extendedKey = new StringBuilder(key);
        while (extendedKey.length() < plainLen) {
            extendedKey.append(extendedKey.charAt(extendedKey.length() - key.length()));
        }

        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < plainLen; i++) {
            int p = plainText.charAt(i) - 'a';
            int k = extendedKey.charAt(i) - 'a';
            cipherText.append((char) (((p + k) % 26) + 'a'));
        }

        return cipherText.toString();
    }
}
