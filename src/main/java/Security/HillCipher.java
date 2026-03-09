package Security;
import java.util.ArrayList;
import java.util.List;

public class HillCipher {
    // Custom Exception for Analysis Failures
    public static class InvalidAnalysisException extends RuntimeException {
        public InvalidAnalysisException() {
            super("Invalid Analysis: Matrix is not invertible or data is insufficient.");
        }
    }

    private int findMatrixSize(int count) {
        for (int i = 1; i * i <= count; i++) {
            if (i * i == count) return i;
        }
        return -1;
    }

    private int mod26(int num) {
        int res = num % 26;
        return res < 0 ? res + 26 : res;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int modularInverse(int num, int mod) {
        if (gcd(num, mod) != 1) return -1;
        for (int i = 1; i < mod; i++) {
            if ((num * i) % mod == 1) return i;
        }
        return -1;
    }

    private int computeDeterminant(List<Integer> matrix, int n) {
        if (n == 2) {
            int determinant = matrix.get(0) * matrix.get(3) - matrix.get(1) * matrix.get(2);
            return mod26(determinant);
        } else if (n == 3) {
            int determinant =
                    matrix.get(0) * (matrix.get(4) * matrix.get(8) - matrix.get(5) * matrix.get(7))
                            - matrix.get(1) * (matrix.get(3) * matrix.get(8) - matrix.get(5) * matrix.get(6))
                            + matrix.get(2) * (matrix.get(3) * matrix.get(7) - matrix.get(4) * matrix.get(6));
            return mod26(determinant);
        }
        throw new InvalidAnalysisException();
    }

    private List<Integer> computeAdjugate(List<Integer> matrix, int n) {
        List<Integer> adjugate = new ArrayList<>();
        for (int i = 0; i < matrix.size(); i++) adjugate.add(0);

        if (n == 2) {
            adjugate.set(0, matrix.get(3));
            adjugate.set(1, -matrix.get(1));
            adjugate.set(2, -matrix.get(2));
            adjugate.set(3, matrix.get(0));
        } else if (n == 3) {
            adjugate.set(0, matrix.get(4) * matrix.get(8) - matrix.get(5) * matrix.get(7));
            adjugate.set(1, -(matrix.get(3) * matrix.get(8) - matrix.get(5) * matrix.get(6)));
            adjugate.set(2, matrix.get(3) * matrix.get(7) - matrix.get(4) * matrix.get(6));
            adjugate.set(3, -(matrix.get(1) * matrix.get(8) - matrix.get(2) * matrix.get(7)));
            adjugate.set(4, matrix.get(0) * matrix.get(8) - matrix.get(2) * matrix.get(6));
            adjugate.set(5, -(matrix.get(0) * matrix.get(7) - matrix.get(1) * matrix.get(6)));
            adjugate.set(6, matrix.get(1) * matrix.get(5) - matrix.get(2) * matrix.get(4));
            adjugate.set(7, -(matrix.get(0) * matrix.get(5) - matrix.get(2) * matrix.get(3)));
            adjugate.set(8, matrix.get(0) * matrix.get(4) - matrix.get(1) * matrix.get(3));
        }

        for (int i = 0; i < adjugate.size(); i++) {
            adjugate.set(i, mod26(adjugate.get(i)));
        }
        return adjugate;
    }

    private List<Integer> invertMatrix(List<Integer> matrix, int n) {
        int det = computeDeterminant(matrix, n);
        int detInverse = modularInverse(det, 26);
        if (detInverse == -1) throw new InvalidAnalysisException();

        List<Integer> adjugate = computeAdjugate(matrix, n);
        List<Integer> inverseMatrix = new ArrayList<>();

        if (n == 2) {
            for (int val : adjugate) {
                inverseMatrix.add(mod26(val * detInverse));
            }
        } else if (n == 3) {
            // Transpose for 3x3
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    inverseMatrix.add(mod26(adjugate.get(j * n + i) * detInverse));
                }
            }
        }
        return inverseMatrix;
    }

    private List<Integer> multiplyMatricesMod26(List<Integer> A, List<Integer> B, int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n * n; i++) result.add(0);

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += A.get(row * n + k) * B.get(k * n + col);
                }
                result.set(row * n + col, mod26(sum));
            }
        }
        return result;
    }

    public List<Integer> encrypt(List<Integer> plainText, List<Integer> key) {
        int n = findMatrixSize(key.size());
        if (n == -1) throw new InvalidAnalysisException();

        List<Integer> paddedPlain = new ArrayList<>(plainText);
        while (paddedPlain.size() % n != 0) paddedPlain.add(23); // 'X'

        List<Integer> cipherText = new ArrayList<>();
        for (int i = 0; i < paddedPlain.size(); i += n) {
            for (int row = 0; row < n; row++) {
                int sum = 0;
                for (int col = 0; col < n; col++) {
                    sum += key.get(row * n + col) * paddedPlain.get(i + col);
                }
                cipherText.add(mod26(sum));
            }
        }
        return cipherText;
    }

    public List<Integer> decrypt(List<Integer> cipherText, List<Integer> key) {
        // Students should complete this part
        return null;
    }

    public List<Integer> analyse3By3Key(List<Integer> plainText, List<Integer> cipherText) {
        // Students should complete this part
        throw new InvalidAnalysisException();
    }
}
