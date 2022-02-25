package utiles;

public class Secuencias_cadenas {

    public static double LongestCommonSubsequence(String A, String B) {

        int[][] mat = new int[A.length() + 1][B.length() + 1];

        A = " " + A;
        B = " " + B;

        for (int i = 1; i < A.length(); i++) {
            for (int j = 1; j < B.length(); j++) {

                if (A.charAt(i) == B.charAt(j)) {
                    mat[i][j] = mat[i - 1][j - 1] + 1;
                } else {
                    mat[i][j] = Math.max(mat[i - 1][j], mat[i][j - 1]);
                }
            }
        }

        int P = mat[A.length() - 1][B.length() - 1];
        int T = Math.max(A.length() - 1, B.length() - 1);

        return (double) (P * 100) / T;

    }

    public boolean mayor_subcadena(String A, String B) {

        int base = 311;
        String menor = A;
        String mayor = B;
        long Hash = 0;

        if (A.length() > B.length()) {
            menor = B;
            mayor = A;
        }

        for (int i = 0; i < menor.length(); i++) {
            Hash = Hash * 10 + (menor.charAt(i) - 'a') * base;
        }

        long Hash2 = 0;

        for (int i = 0; i < mayor.length(); i++) {
            Hash = Hash * 10 + (mayor.charAt(i) - 'a') * base;

            if (i >= menor.length()) {
                if (Hash == Hash2) {
                    return true;
                }
                Hash = (Hash - ((mayor.charAt(i - menor.length()) - 'a') * base)) / 10;
            }
        }
        return false;
    }
}
