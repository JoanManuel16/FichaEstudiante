/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 *
 * @author SchWarZer
 */
public class CalculoICI {
    
    public static double ICI(int m, double M, double promedio, int mis){

 
            BigDecimal mi = new BigDecimal(mis);
            BigDecimal Mi = new BigDecimal(0.0);
            BigDecimal ICIi = new BigDecimal(0.0);
            
            if(promedio == 0.0){
                Mi = new BigDecimal(0);
            }
            else if(promedio >= 2 && promedio < 3){
                BigDecimal primer_elemento = new BigDecimal(((double)(64*m)/(double)25));
                Double expn = Math.pow(1.25, promedio);
                BigDecimal segundoElemento = new BigDecimal(expn);
                BigDecimal tercer_elemento = new BigDecimal(4*m);
                Mi = primer_elemento.multiply(segundoElemento);
                Mi = Mi.subtract(tercer_elemento);
            }
            else if(promedio < 3.5){
                BigDecimal A = new BigDecimal("2251799813685248").multiply(new BigDecimal((M-m)+""));
                BigDecimal primer_elemento = A.divide(new BigDecimal("6832086203604989"), 10, RoundingMode.HALF_EVEN);
                BigDecimal segundoElemento = new BigDecimal(Math.pow(1.5, promedio));
                Mi = primer_elemento.multiply(segundoElemento);
                BigDecimal B = new BigDecimal("194369165990474").multiply(new BigDecimal(m+""));
                BigDecimal tercerElemento = B.divide(new BigDecimal("92014629004781"), 10, RoundingMode.HALF_EVEN);
                BigDecimal C = new BigDecimal("1125899906842624").multiply(new BigDecimal(M+""));
                BigDecimal cuartoElemento = C.divide(new BigDecimal("1012160919052591"), 10, RoundingMode.HALF_EVEN);
                BigDecimal Temp = tercerElemento.subtract(cuartoElemento);
                Mi = Mi.add(Temp);
            }
            else if(promedio < 4){
                
                BigDecimal A = new BigDecimal("140737488355328").multiply(new BigDecimal((M-m)+""));
                BigDecimal primer_elemento = A.divide(new BigDecimal("2638147582215219"), 10, RoundingMode.HALF_EVEN);
                BigDecimal segundoElemento = new BigDecimal(Math.pow(2, promedio));
                Mi = primer_elemento.multiply(segundoElemento);
                BigDecimal B = new BigDecimal("7141747209585715").multiply(new BigDecimal(m+""));
                BigDecimal tercerElemento = B.divide(new BigDecimal("5276295164430438"), 10, RoundingMode.HALF_EVEN);
                BigDecimal C = new BigDecimal("1865452045155277").multiply(new BigDecimal(M+""));
                BigDecimal cuartoElemento = C.divide(new BigDecimal("5276295164430438"), 10, RoundingMode.HALF_EVEN);
                BigDecimal Temp = tercerElemento.subtract(cuartoElemento);
                Mi = Mi.add(Temp);
                
            }
            else if(promedio < 4.5){
                BigDecimal primer_elemento = new BigDecimal("17592186044416").multiply(new BigDecimal((M-m)+"")).divide(new BigDecimal("1597422252574269"), 10, RoundingMode.HALF_EVEN);
                BigDecimal segundoElemento = new BigDecimal(Math.pow(2.5, promedio));
                BigDecimal tercerElemento = new BigDecimal("223032717854269").multiply(new BigDecimal(M+"")).divide(new BigDecimal("3194844505148538"), 10, RoundingMode.HALF_EVEN);
                BigDecimal cuartoElemento = new BigDecimal("2971811787294269").multiply(new BigDecimal(m+"")).divide(new BigDecimal("3194844505148538"), 10, RoundingMode.HALF_EVEN);
                Mi = primer_elemento.multiply(segundoElemento);
                Mi = Mi.add(tercerElemento);
                Mi = Mi.add(cuartoElemento);
            }
            else if(promedio <= 5){
                BigDecimal primer_elemento = new BigDecimal("17592186044416").multiply(new BigDecimal((M-m)+"")).divide(new BigDecimal("7227143380549369"), 10, RoundingMode.HALF_EVEN);
                BigDecimal segundoElemento = new BigDecimal(Math.pow(3, promedio));
                BigDecimal tercerElemento = new BigDecimal("388772631671609").multiply(new BigDecimal(M+"")).divide(new BigDecimal("951722585092921"), 10, RoundingMode.HALF_EVEN);
                BigDecimal cuartoElemento = new BigDecimal("562949953421312").multiply(new BigDecimal(m+"")).divide(new BigDecimal("951722585092921"), 10, RoundingMode.HALF_EVEN);
                Mi = primer_elemento.multiply(segundoElemento);
                Mi = Mi.add(tercerElemento);
                Mi = Mi.add(cuartoElemento);
            }
            if(promedio == 0.0){
                ICIi = new BigDecimal(0);
            }
            else if(promedio >= 3 && promedio <= 5){
            ICIi = mi.add(Mi);
            ICIi = ICIi.divide(new BigDecimal(m+M), 10, RoundingMode.HALF_EVEN);
            ICIi = ICIi.multiply(new BigDecimal(100));
            }
            else{
            ICIi = mi.add(Mi);
            ICIi = ICIi.divide(new BigDecimal(2*m), 10, RoundingMode.HALF_EVEN);
            }
            
            return ICIi.doubleValue();
    }
}
