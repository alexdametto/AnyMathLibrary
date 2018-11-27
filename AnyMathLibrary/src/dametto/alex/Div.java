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
public class Div implements IBinary{
    private String exp1 = "", exp2 = "";
    private IOperation arg1 = null, arg2 = null;
    
    /**
     *
     * @param arg1 il numeratore della divisione
     * @param arg2 il denominatore della divisione
     */
    public Div(IOperation arg1, IOperation arg2){
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
    
    /**
     *
     * @param arg1 il numeratore della divisione
     * @param arg2 il denominatore della divisione
     */
    public Div(String arg1, String arg2){
        this.exp1 = arg1;
        this.exp2 = arg2;
    }
    
    /**
     *
     * @return il valore della divisione
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valuta() throws Exception {
        if(arg1 == null)
            this.arg1 = Exp.parseExp(this.exp1);
        if(arg2 == null)
            this.arg2 = Exp.parseExp(this.exp2);
        
        if(arg2.valuta().toDouble() == 0)
            throw new IllegalArgumentException("Il divisore deve essere diverso da 0.");
        return new Num(arg1.valuta().toDouble() / arg2.valuta().toDouble());
    }

    /**
     *
     * @return il valore della divisione
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Steps valutaPassoAPasso(String exp) throws Exception {
        if(arg1 == null)
            this.arg1 = Exp.parseExp(this.exp1);
        if(arg2 == null)
            this.arg2 = Exp.parseExp(this.exp2);
        
        Steps ris1 = arg1.valutaPassoAPasso(exp);
        Steps ris2 = arg2.valutaPassoAPasso(exp);
                        
        if(ris2.getRes().toDouble() == 0)
            throw new IllegalArgumentException("Il divisore deve essere diverso da 0.");
        
        Num ris = new Num(ris1.getRes().toDouble() / ris2.getRes().toDouble());
        
        Step n = new Step("Calcolo divisione tra " + ris1.toString() + " e " + ris2.toString(), exp.replace(this.toString(), ris.toString()));
        
        Steps st = new Steps();
        st.setSteps(ris1);
        st.setSteps(ris2);
        
        st.addStep(n);
        st.setResult(ris);
        
        return st;
    }

    
    /**
     *
     * @return la stringa da poter stampare
     */
    @Override
    public String toString() {
        return "(" + arg1.toString() + ")/(" + arg2.toString() + ")";
    }
}
