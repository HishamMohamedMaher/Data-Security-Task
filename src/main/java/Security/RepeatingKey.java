package Security;

public class RepeatingKey {

    public String analyse(String plainText, String cipherText) {
        plainText = plainText.toLowerCase();
        cipherText = cipherText.toLowerCase();
        int len = plainText.length();

        StringBuilder key = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int p = plainText.charAt(i) - 'a';
            int c = cipherText.charAt(i) - 'a';
            int k = (c - p + 26) % 26;
            key.append((char) (k + 'a'));
        }

        String fullKey = key.toString();
        for (int keyLen = 1; keyLen <= fullKey.length(); keyLen++) {
            String candidate = fullKey.substring(0, keyLen);
            StringBuilder repeated = new StringBuilder();
            while (repeated.length() < fullKey.length()) {
                repeated.append(candidate);
            }
            if (repeated.toString().startsWith(fullKey)) {
                return candidate;
            }
        }

        return fullKey;
    }

    public String decrypt(String cipherText, String key) {
        cipherText = cipherText.toLowerCase();
        key = key.toLowerCase();
        int cipherLen = cipherText.length();

        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < cipherLen; i++) {
            int c = cipherText.charAt(i) - 'a';
            int k = key.charAt(i % key.length()) - 'a';
            plainText.append((char) (((c - k + 26) % 26) + 'a'));
        }

        return plainText.toString();
    }

    public String encrypt(String plainText, String key) {
        plainText = plainText.toLowerCase();
        key = key.toLowerCase();
        int plainLen = plainText.length();

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
