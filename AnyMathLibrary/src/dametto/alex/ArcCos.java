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
public class ArcCos implements IUnary {
    private String exp = "";
    private IOperation arg = null;
    
    /**
     *
     * @param arg l'argomento dell'arcocoseno
     */
    public ArcCos(IOperation arg){
        this.arg = arg;
    }
    
    /**
     *
     * @param arg l'argomento dell'arcocoseno
     */
    public ArcCos(String arg){
        this.exp = arg;
    }

    /**
     *
     * @return un numero che è il risultato dell'arcocoseno dell'argomento
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valuta() throws Exception{
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        
        Double risArg = arg.valuta().toDouble();
        
        if(risArg < -1 || risArg > 1)
            throw new IllegalArgumentException("L'argomento deve essere compreso tra -1 e 1.");
        
        return new Num(Math.acos(risArg));
    }

    /**
     *
     * @return un numero che è il risultato dell'arcocoseno dell'argomento
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valutaPassoAPasso() throws Exception {
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        
        Num risArg = arg.valutaPassoAPasso();
        
        System.out.println("Calcolo arccos(" + risArg.toString() + ")");
        
        if(risArg.toDouble() < -1 || risArg.toDouble() > 1)
            throw new IllegalArgumentException("L'argomento deve essere compreso tra -1 e 1.");
        
        return new Num(Math.acos(risArg.toDouble()));
    }

    /**
     *
     * @return la stringa da poter stampare
     */
    @Override
    public String toString() {
        return "arccos(" + arg.toString() + ")";
    }
}
