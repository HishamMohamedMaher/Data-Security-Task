package Security;
import java.util.*;

public class ColumnarCipher {

    public List<Integer> analyse(String plainText, String cipherText) {
        // TODO: Analyze the plainText and cipherText to determine the key(s)
        plainText=plainText.toLowerCase();
        cipherText=cipherText.toLowerCase();

        for (int keySize =2; keySize<=10;keySize++) {
            if (cipherText.length() % keySize != 0)
                continue;

            int rows = cipherText.length() / keySize;

            StringBuilder padding = new StringBuilder(plainText);
            while (padding.length() < cipherText.length()) {
                padding.append('x');
            }

            String[] OriginalCols = new String[keySize];
            for (int c = 0; c < keySize; c++) {
                StringBuilder colData = new StringBuilder();
                for (int r = 0; r < rows; r++) {
                    colData.append(padding.charAt(r * keySize + c));
                }
                OriginalCols[c] = colData.toString();
            }

            String[] cipherCols = new String[keySize];
            for (int i = 0; i < keySize; i++) {
                cipherCols[i] = cipherText.substring(i * rows, (i + 1) * rows);
            }

            List<Integer> key = new ArrayList<>();
            for (int i = 0; i < keySize; i++) {
                for (int j = 0; j < keySize; j++) {
                    if (OriginalCols[i].equals(cipherCols[j])) {
                        key.add(j + 1); // جافا بتبدأ من 0 والدكتور عايز الكي يبدأ من 1
                        break;
                    }
                }
            }

            if (key.size() == keySize) {
                return key;
            }
        }

        return new ArrayList<>(); // Placeholder return
    }

    public String decrypt(String cipherText, List<Integer> key) {
        int cipherSize = cipherText.length();
        int rows = (int) Math.ceil((double) cipherSize / key.size());
        char[][] grid = new char[rows][key.size()];
        int count = 0;

        Map<Integer, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < key.size(); i++) {
            keyMap.put(key.get(i) - 1, i);
        }

        int remainingCols = cipherSize % key.size();
        for (int i = 0; i < key.size(); i++) {
            for (int j = 0; j < rows; j++) {
                if (remainingCols != 0 && j == rows - 1 && keyMap.get(i) >= remainingCols) continue;
                grid[j][keyMap.get(i)] = cipherText.charAt(count++);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.size(); j++) {
                result.append(grid[i][j]);
            }
        }
        return result.toString().toUpperCase().trim();
    }

    public String encrypt(String plainText, List<Integer> key) {
        int ptSize = plainText.length();
        int rows = (int) Math.ceil((double) ptSize / key.size());
        char[][] grid = new char[rows][key.size()];
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.size(); j++) {
                if (count >= ptSize) {
                    grid[i][j] = 'x';
                } else {
                    grid[i][j] = plainText.charAt(count++);
                }
            }
        }

        Map<Integer, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < key.size(); i++) {
            keyMap.put(key.get(i) - 1, i);
        }

        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < key.size(); i++) {
            for (int j = 0; j < rows; j++) {
                cipherText.append(Character.toUpperCase(grid[j][keyMap.get(i)]));
            }
        }
        return cipherText.toString();
    }
}
