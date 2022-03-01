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

    public static boolean mayor_subcadena(String A, String B) {

        int base = 311;
        long Hash = 0;

        for (int i = 0; i < A.length(); i++) {
            Hash = Hash * 10 + (A.charAt(i) - 'a') * base;
        }

        long Hash2 = 0;

        for (int i = 0; i < B.length(); i++) {
            Hash2 = Hash2 * 10 + (B.charAt(i) - 'a') * base;

            if (i >= A.length()) {
                if (Hash == Hash2) {
                    return true;
                }
                Hash2 = (Hash2 - ((B.charAt(i - A.length()) - 'a') * base)) / 10;
            }
        }
        return false;
    }
}
