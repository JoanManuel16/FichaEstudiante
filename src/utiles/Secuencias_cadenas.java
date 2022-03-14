package utiles;

import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JTextField;

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
        String menor = A;
        String mayor = B;
        long Hash = 0;

        int lon = menor.length();

        for (int i = 0; i < lon; i++) {
            Hash = Hash * base + (menor.charAt(i) - 'a' + 1);
        }

        long Hash2 = 0;

        for (int i = 0; i < mayor.length(); i++) {
            Hash2 = Hash2 * base + (mayor.charAt(i) - 'a' + 1);

            if (i >= lon - 1) {
                if (Hash == Hash2) {
                    return true;
                }

                Hash2 = Hash2 - ((mayor.charAt(i - lon + 1) - 'a' + 1) * (long) (Math.pow(base, lon - 1)));
            }
        }
        return false;
    }
    
    
    public static boolean sonNumeros(Character c) {
      
          if (c == KeyEvent.CHAR_UNDEFINED || c == '\b'
                || c == '\n') {
            return true;
        }
        
        Vector<Character> v = new Vector<>();
        v.add('0');
        v.add('1');
        v.add('2');
        v.add('3');
        v.add('4');
        v.add('5');
        v.add('6');
        v.add('7');
        v.add('8');
        v.add('9');
        return v.contains(c);
    }
    
     public static void borrarLetras(char X, JTextField texto) {
        if (X == KeyEvent.CHAR_UNDEFINED || X == '\b'
                || X == '\n') {
            return;
        }

        String Xs = texto.getText();
        int t = 0;
        try {
            int x = Integer.parseInt("" + X);
        } catch (NumberFormatException e) {
            do {
                t = Xs.indexOf(X + "");
                if (t == -1) {
                    break;
                }
                String x1 = Xs.substring(0, t);
                String x2 = Xs.substring(t + 1);
                Xs = x1 + x2;
                texto.setText(x1 + x2);
            } while (true);
        }

    }
}
