/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dametto.alex;

/**
 *
 * @author Alex
 */
public class Variabile implements IUnary {
    
    private Num number = new Num(0);
    private char variabile = ' ';
    
    public Variabile(Num number, char variabile){
        this.number = number;
        this.variabile = variabile;
    }

    @Override
    public Num valuta() throws Exception {
        return new Num(Double.NaN);
    }

    @Override
    public Steps valutaPassoAPasso(String exp) throws Exception {
        Step n = new Step("Calcolo " + number.toString() + variabile, exp);
        
        Steps s = new Steps();
        s.addStep(n);
        s.setResult(new Num(Double.NaN));
        
        return s;        
    }
}
