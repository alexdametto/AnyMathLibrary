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
public class Diff implements IUnary {
    private String exp = "";
    private IOperation arg = null;
    
    /**
     *
     * @param arg parametro che indica il valore da negare
     */
    public Diff(IOperation arg){
        this.arg = arg;
    }
    
    
    /**
     *
     * @param arg parametro che indica il valore da negare
     */
    public Diff(String arg){
        this.exp = arg;
    }
    
    /**
     *
     * @return il valore della differenza
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valuta() throws Exception{
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        return new Num(-this.arg.valuta().toDouble());
    }

    
    /**
     *
     * @return il valore della differenza
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valutaPassoAPasso() throws Exception {if(arg == null)
        this.arg = Exp.parseExp(this.exp);
        
        Num risArg = arg.valutaPassoAPasso();
        
        return new Num(-risArg.toDouble());
    }

    /**
     *
     * @return la stringa da poter stampare
     */
    @Override
    public String toString() {
        return "-(" + arg.toString() + ")";
    }
}
