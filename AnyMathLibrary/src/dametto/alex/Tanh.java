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
public class Tanh implements IUnary {
    private String exp = "";
    private IOperation arg = null;
    
    /**
     *
     * @param arg l'argomento della tangente iperbolica
     */
    public Tanh(IOperation arg){
        this.arg = arg;
    }
    
    /**
     *
     * @param arg l'argomento della tangente iperbolica
     */
    public Tanh(String arg){
        this.exp = arg;
    }

    /**
     *
     * @return il valore della tangente iperbolica dell'argomento
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valuta() throws Exception{
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        return new Num(Math.tanh(arg.valuta().toDouble()));
    }
    
    /**
     *
     * @return il valore della tangente iperbolica dell'argomento
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valutaPassoAPasso() throws Exception {
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        
        Num risArg = arg.valutaPassoAPasso();
        
        System.out.println("Calcolo tanh(" + risArg.toString() + ")");
        
        return new Num(Math.acos(risArg.toDouble()));
    }
    
    
    /**
     *
     * @return la stringa da poter stampare
     */
    @Override
    public String toString() {
        return "tanh(" + arg.toString() + ")";
    }
}
