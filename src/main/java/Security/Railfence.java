package Security;

public class Railfence {
    public int analyse(String plainText, String cipherText) {
        // Students should complete this part
        for (int key = 2; key<plainText.length();key++){
          String encryptedTxt = encrypt(plainText,key);

          if (encryptedTxt.equalsIgnoreCase(cipherText.replace(" ",""))){
              return key;
          }
        }
        return -1;
    }

    public String decrypt(String cipherText, int key) {
        cipherText = cipherText.replace(" ", "").toLowerCase();
        int colNumbers = (int) Math.ceil((double) cipherText.length() / key);

        char[][] matrix = new char[key][colNumbers];

        int pos = 0;
        for (int r = 0; r < key && pos < cipherText.length(); r++) {
            for (int c = 0; c < colNumbers && pos < cipherText.length(); c++) {
                matrix[r][c] = cipherText.charAt(pos++);
            }
        }

        StringBuilder plainText = new StringBuilder();
        for (int c = 0; c < colNumbers; c++) {
            for (int r = 0; r < key; r++) {
                if (matrix[r][c] != '\0') {
                    plainText.append(matrix[r][c]);
                }
            }
        }
        return plainText.toString().toUpperCase();
    }

    public String encrypt(String plainText, int key) {
        plainText = plainText.replace(" ", "").toLowerCase();
        int colNumbers = (int) Math.ceil((double) plainText.length() / key);

        char[][] matrix = new char[key][colNumbers];

        int pos = 0;
        for (int c = 0; c < colNumbers && pos < plainText.length(); c++) {
            for (int r = 0; r < key && pos < plainText.length(); r++) {
                matrix[r][c] = plainText.charAt(pos++);
            }
        }

        StringBuilder cipherText = new StringBuilder();
        for (int r = 0; r < key; r++) {
            for (int c = 0; c < colNumbers; c++) {
                if (matrix[r][c] != '\0') {
                    cipherText.append(matrix[r][c]);
                }
            }
        }
        return cipherText.toString().toUpperCase();
    }
}
