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
    public Steps valutaPassoAPasso(String exp) throws Exception {        
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        
        Steps risArg = arg.valutaPassoAPasso(exp);
        
        Num ris = new Num(- risArg.getRes().toDouble());
        
        Step n = new Step("", exp.replace(this.exp, ris.toString()));
                
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
        return "-" + arg.toString();
    }
}
