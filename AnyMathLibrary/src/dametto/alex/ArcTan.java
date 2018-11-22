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
public class ArcTan implements IUnary{
    private String exp = "";
    private IOperation arg = null;
    
    /**
     *
     * @param arg l'argomento dell'arcotangente
     */
    public ArcTan(IOperation arg){
        this.arg = arg;
    }
    
    
    /**
     *
     * @param arg l'argomento dell'arcotangente
     */
    public ArcTan(String arg){
        this.exp = arg;
    }
    
    /**
     *
     * @return il valore dell'argotangente dell'argomento
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valuta() throws Exception {
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        Double risArg = arg.valuta().toDouble();        
        return new Num(Math.atan(risArg));
    }

    /**
     *
     * @return il valore dell'argotangente dell'argomento
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valutaPassoAPasso() throws Exception {
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        
        Num risArg = arg.valutaPassoAPasso();
        
        System.out.println("Calcolo arctan(" + risArg.toString() + ")");
        
        return new Num(Math.atan(risArg.toDouble()));
    }

    /**
     *
     * @return la stringa da poter stampare
     */
    @Override
    public String toString() {
        return "arctan(" + arg.toString() + ")";
    }
}
