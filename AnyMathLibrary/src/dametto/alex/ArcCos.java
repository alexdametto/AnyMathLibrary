/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dametto.alex;

import java.util.ArrayList;

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
    public Steps valutaPassoAPasso(String exp) throws Exception {
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        
        Steps risArg = arg.valutaPassoAPasso(exp);
                        
        if(risArg.getRes().toDouble() < -1 || risArg.getRes().toDouble() > 1)
            throw new IllegalArgumentException("L'argomento deve essere compreso tra -1 e 1.");
        
        Num ris = new Num(Math.acos(risArg.getRes().toDouble()));
        
        Step n = new Step("Calcolo arccos(" + risArg.toString() + ")", exp.replace(this.exp, ris.toString()));
                
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
        return "arccos(" + arg.toString() + ")";
    }
}
