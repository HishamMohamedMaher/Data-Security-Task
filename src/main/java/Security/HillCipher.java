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
// ---------------------------------------------------------------------------------
public List<Integer> decrypt(List<Integer> CiphText,List<Integer> k_ey) {
    chkInpts(CiphText,k_ey);
    int NN = get_n_sizeAndCheck(CiphText, k_ey); List<Integer> inv_k = invertMatrix(k_ey, NN);
    List<Integer> rESULT = dec_blks(CiphText,inv_k, NN);
    return rESULT;
}

    private void chkInpts(List<Integer> ctxt, List<Integer> ky) {
        boolean mssng1 = ctxt == null;boolean mssng2 = ky == null;
        if (mssng1||mssng2)
        { throw new InvalidAnalysisException(); }
    }

    private int get_n_sizeAndCheck(List<Integer> c_t, List<Integer> k_y) {
        int xX = findMatrixSize(k_y.size());
        boolean isok = xX==2 || xX==3; boolean vLen = c_t.size()%xX==0;
        if(!isok||!vLen) {
            throw new InvalidAnalysisException();}
        return xX;
    }

    private List<Integer> dec_blks(List<Integer> CT, List<Integer> IK, int nnn) {
        List<Integer> pt_list = new ArrayList<>();
        for(int i=0;i<CT.size();i+=nnn)
        {dec1Blk(CT,IK,pt_list,i,nnn);}
        return pt_list;
    }

    private void dec1Blk(List<Integer> ct,List<Integer> ik,
                         List<Integer> p_t, int b_strt, int n_val) {
        List<Integer> tempVals=new ArrayList<>();
        for (int r=0;r<n_val;r++) {
            tempVals.add(calc_val(ct, ik, b_strt, r, n_val));
        }p_t.addAll(tempVals);
    }

    private int calc_val(List<Integer> Ctxt, List<Integer> i_k,
                         int strt, int R, int N) {
        int sm=0;int cl=0;
        while(cl<N) {sm+=i_k.get(R*N+cl)*Ctxt.get(strt+cl);cl++;}
        return mod26(sm);
    }
//---------------------------------------------------------

    public List<Integer> analyse3By3Key(List<Integer> p_txt,List<Integer> c_txt) {
        check_Ana_in(p_txt, c_txt);
        int blx=calcBlkC(p_txt);
        List<Integer> k=fnd33(p_txt,c_txt,blx);
        return k;
    }

    private void check_Ana_in(List<Integer> pt,List<Integer> ct){
        boolean nUll = pt==null||ct==null;
        boolean diffSz = !nUll&&pt.size()!=ct.size();
        boolean shrt = !nUll&&pt.size()<9; boolean bdBlk = !nUll&&pt.size()%3!=0;
        if (nUll||diffSz||shrt||bdBlk) {throw new InvalidAnalysisException();}
    }

    private int calcBlkC(List<Integer> p) {return p.size()/3;}

    private List<Integer> fnd33(List<Integer> p,List<Integer> c,int bc) {
        for(int f=0;f<=bc-3;f++) {
            for(int s=f+1;s<=bc-2;s++) {
                for(int t=s+1;t<=bc-1;t++) {
                    List<Integer> res = try_combos(p,c,f,s,t);
                    if(res!=null) {return res;}
                }
            }
        }
        throw new InvalidAnalysisException();
    }

    private List<Integer> try_combos(List<Integer> PT,List<Integer> CT,
                                     int i1,int i2,int i3) {
        List<Integer> p_mat=new ArrayList<>();List<Integer> c_mat=new ArrayList<>();
        bld_Mats(PT,CT,p_mat,c_mat,i1,i2,i3);
        List<Integer> k_res=genV_key(PT,CT,p_mat,c_mat);
        return k_res;
    }

    private void bld_Mats(List<Integer> pp,List<Integer> cc,
                          List<Integer> pm,List<Integer> cm,
                          int x,int y,int z) {
        int[] arr={x,y,z};
        for(int rr=0;rr<3;rr++) {for(int cc2=0;cc2<3;cc2++){
            int chz=arr[cc2];int idx=chz*3+rr;
            pm.add(pp.get(idx));cm.add(cc.get(idx));
        }}
    }

    private List<Integer> genV_key(List<Integer> p,List<Integer> c,
                                   List<Integer> pM,List<Integer> cM) {
        try {
            List<Integer> invP=invertMatrix(pM, 3);
            List<Integer> testK=multiplyMatricesMod26(cM, invP, 3);
            if (!encrypt(p,testK).equals(c)){
                return null;}
            return testK;
        } catch(InvalidAnalysisException ignrd) {return null;}
    }
}
