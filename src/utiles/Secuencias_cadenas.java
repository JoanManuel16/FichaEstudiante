package utiles;

import com.toedter.calendar.JCalendar;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
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
     
     public static boolean carnetIdentidadCorrecto(String CIT){
         
         if(CIT.length() != 11){
             return false;
         }
         String anno = CIT.substring(0, 2);
         String mes = CIT.substring(2, 4);
         String dia = CIT.substring(4, 6);
         String siglo = CIT.substring(6, 7);
         int sigloN = Integer.parseInt(siglo);
         if(sigloN >= 5 && sigloN <= 7){
             anno = "20" + anno;
         }
         else{
             anno = "19" + anno;
         }
         
         int annoActual = Calendar.getInstance().get(1);
         int diferencia = annoActual - Integer.parseInt(anno);
         if(diferencia <= 16){
             return false;
         }
         
         int mesN = Integer.parseInt(mes);
         int diaN = Integer.parseInt(dia);
         if(diaN <= 0){
             return false;
         }
         if(mesN <= 0 || mesN > 12){
             return false;
         }
         else{
             if(mesN == 2){
                 if(diaN > 28){
                     return false;
                 }
             }
             else if(mesN == 4 || mesN == 6 || mesN == 9 || mesN == 11){
                 if(diaN > 30){
                     return false;
                 }
             }
             else{
                 if(diaN > 31){
                     return false;
                 }
             }
         }
         
         return true;
     }
}
