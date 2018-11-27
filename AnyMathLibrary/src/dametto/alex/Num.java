/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dametto.alex;

/**
 *
 * @author alex
 */
public class Num implements IUnary {
    private int denominatore = 1;
    private int numeratore = 1;
    private double num = 0;
    
    /**
     *
     * @param num il valore del numero
     */
    public Num(double num){
        this.num = num;
        
        boolean negativo = false;
        if(num < 0){
            negativo = true;
            num = Math.abs(num);
        }
        
        String s = String.valueOf(num);
        int digitsDec = s.length() - 1 - s.indexOf('.');
        int denom = 1;
        for (int i = 0; i < digitsDec; i++) {
            num *= 10;    
            denom *= 10;
        }

        int n = (int) Math.round(num);
        int g = MCD(n, denom);
        this.numeratore = n / g;
        this.denominatore = denom /g;
        
        if(negativo && numeratore > 0)
            numeratore = -numeratore;
    }
    
    /**
     *
     * @param numerator numeratore della frazione
     * @param denominator denominatore della frazione
     */
    public Num(int numerator, int denominator){
        this.numeratore = numeratore;
        this.denominatore = denominatore;
        this.num = this.numeratore / this.denominatore;
    }
    
    /**
     *
     * @param a primo numero
     * @param b secondo numero
     */
    private int MCD(int a, int b) {
        if (b==0) return a;
        return MCD(b,a%b);
    }

    /**
     *
     * @return il valore del numero
     */
    @Override
    public Num valuta() {
        return new Num(this.num);
    }
    
    /**
     *
     * @return il valore del numero in double
     */
    public double toDouble(){
        return num;
    }

    /**
     *
     * @return il valore del numero in double
     */
    @Override
    public Steps valutaPassoAPasso(String exp) throws Exception {
        Steps s = new Steps();
        s.addStep(new Step("", exp));
        s.setResult(new Num(this.num));
        return s;
    }
    
    
    /**
     *
     * @return la stringa da poter stampare
     */
    @Override
    public String toString() {
        if(Double.valueOf(numeratore)/Double.valueOf(denominatore) == this.num){
            if(denominatore == 1)
                return String.valueOf(numeratore);
            if(numeratore == 0)
                return "0";
            else return numeratore + "/" + denominatore;
        }
        
        if(this.num == Math.E)
            return "e";
        else if (this.num == Math.PI)
            return "pi";
        else return String.valueOf(this.num);
    }
}
