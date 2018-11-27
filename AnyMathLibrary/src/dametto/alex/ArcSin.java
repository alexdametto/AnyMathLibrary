package dametto.alex;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alex
 */
public class ArcSin implements IUnary {
    private String exp = "";
    private IOperation arg = null;
    
    /**
     *
     * @param arg l'argomento dell'arcoseno
     */
    public ArcSin(IOperation arg){
        this.arg = arg;
    }
    
    
    /**
     *
     * @param arg l'argomento dell'arcoseno
     */
    public ArcSin(String arg){
        this.exp = arg;
    }
    
    /**
     *
     * @return il valore dell'arcoseno dell'argomento
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valuta() throws Exception{
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        
        Double risArg = arg.valuta().toDouble();
        
        if(risArg < -1 || risArg > 1)
            throw new IllegalArgumentException("L'argomento deve essere compreso tra -1 e 1.");
        
        return new Num(Math.asin(risArg));
    }

    /**
     *
     * @return il valore dell'arcoseno dell'argomento
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Steps valutaPassoAPasso(String exp) throws Exception {
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        
        Steps risArg = arg.valutaPassoAPasso(exp);
                        
        if(risArg.getRes().toDouble() < -1 || risArg.getRes().toDouble() > 1)
            throw new IllegalArgumentException("L'argomento deve essere compreso tra -1 e 1.");
        
        Num ris = new Num(Math.asin(risArg.getRes().toDouble()));
        
        Step n = new Step("Calcolo arcsin(" + risArg.toString() + ")", exp.replace(this.exp, ris.toString()));
                
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
        return "arcsin(" + arg.toString() + ")";
    }
}
