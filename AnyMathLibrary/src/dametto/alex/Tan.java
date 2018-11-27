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
public class Tan implements IUnary {
    private String exp = "";
    private IOperation arg = null;
    
    /**
     *
     * @param arg l'argomento della tangente
     */
    public Tan(IOperation arg){
        this.arg = arg;
    }
    
    /**
     *
     * @param arg l'argomento della tangente
     */
    public Tan(String arg){
        this.exp = arg;
    }

    /**
     *
     * @return il valore della tangente dell'argomento
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valuta() throws Exception{
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        return new Num(Math.tan(arg.valuta().toDouble()));
    }
    
    /**
     *
     * @return il valore della tangente dell'argomento
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Steps valutaPassoAPasso(String exp) throws Exception {
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        
        Steps risArg = arg.valutaPassoAPasso(exp);
        
        Num ris = new Num(Math.tan(risArg.getRes().toDouble()));
        
        Step n = new Step("Calcolo tan(" + risArg.toString() + ")", exp.replace(this.exp, ris.toString()));
                
        Steps st = new Steps();
        st.setResult(ris);
        st.setSteps(risArg);
        st.addStep(n);
        
        return st;
    }
    
    
    /**
     *
     * @return la stringa da poter stampare
     */
    @Override
    public String toString() {
        return "tan(" + arg.toString() + ")";
    }
    
}
