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
public class Cos implements IUnary {
    private String exp = "";
    private IOperation arg = null;
    
    /**
     *
     * @param arg l'argomento del coseno
     */
    public Cos(IOperation arg){
        this.arg = arg;
    }
    
    /**
     *
     * @param arg l'argomento del coseno
     */
    public Cos(String arg){
        this.exp = arg;
    }
    
    /**
     *
     * @return il valore del coseno dell'argomento
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valuta() throws Exception{
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        
        return new Num(Math.cos(arg.valuta().toDouble()));
    }

    
    /**
     *
     * @return il valore del coseno dell'argomento
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valutaPassoAPasso() throws Exception {
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        
        Num risArg = arg.valutaPassoAPasso();
        
        System.out.println("Calcolo cos(" + risArg.toString() + ")");
        
        return new Num(Math.cos(risArg.toDouble()));
    }

    
    /**
     *
     * @return la stringa da poter stampare
     */
    @Override
    public String toString() {
        return "cos(" + arg.toString() + ")";
    }
}
