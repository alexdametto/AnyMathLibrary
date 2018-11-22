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
public class Cosh implements IUnary {
    private String exp = "";
    private IOperation arg = null;
    
    /**
     *
     * @param arg l'argomento del coseno iperbolico
     */
    public Cosh(IOperation arg){
        this.arg = arg;
    }
    
    /**
     *
     * @param arg l'argomento del coseno iperbolico
     */
    public Cosh(String arg){
        this.exp = arg;
    }
    
    /**
     *
     * @return il valore del coseno iperbolico dell'argomento
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valuta() throws Exception{
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        
        return new Num(Math.cosh(arg.valuta().toDouble()));
    }

    
    /**
     *
     * @return il valore del coseno iperbolico dell'argomento
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valutaPassoAPasso() throws Exception {
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        
        Num risArg = arg.valutaPassoAPasso();
        
        System.out.println("Calcolo cosh(" + risArg.toString() + ")");
        
        return new Num(Math.acos(risArg.toDouble()));
    }

    
    /**
     *
     * @return la stringa da poter stampare
     */
    @Override
    public String toString() {
        return "cosh(" + arg.toString() + ")";
    }
}
