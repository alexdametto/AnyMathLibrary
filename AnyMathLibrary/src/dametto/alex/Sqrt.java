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
public class Sqrt implements IBinary {
    private String expInd = "", expArg = "";
    private IOperation indice = new Num(2), argomento = null;
    
    /**
     *
     * @param indice l'indice della radice
     * @param argomento l'argomento della radice
     */
    public Sqrt(IOperation indice, IOperation argomento){
        this.indice = indice;
        this.argomento = argomento;
    }
    
    /**
     *
     * @param indice l'indice della radice
     * @param argomento l'argomento della radice
     */
    public Sqrt(String indice, String argomento){
        this.expInd = indice;
        this.expArg = argomento;
    }

    /**
     *
     * @return il valore della radice
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valuta() throws Exception{
        if(indice == null)
            this.indice = Exp.parseExp(this.expInd);
        
        if(argomento == null)
            this.argomento = Exp.parseExp(this.expArg);
        
        Double risArg = argomento.valuta().toDouble();
        Double risInd = indice.valuta().toDouble();
        
        if(risArg < 0)
            throw new IllegalArgumentException("L'argomento deve essere maggiore di zero.");
        
        if(risInd == 0)
            throw new IllegalArgumentException("L'indice deve essere diverso da zero.");
        
        return new Num(Math.pow(risArg, 1/risInd));
    }
    
    /**
     *
     * @return il valore della radice
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Steps valutaPassoAPasso(String exp) throws Exception {
        if(argomento == null)
            this.argomento = Exp.parseExp(this.expArg);
        if(indice == null)
            this.indice = Exp.parseExp(this.expInd);
        
        Steps risBase = argomento.valutaPassoAPasso(exp);
        Steps risEsp = indice.valutaPassoAPasso(exp);
        
        Num ris = new Num(Math.pow(risBase.getRes().toDouble(), 1/risEsp.getRes().toDouble()));
        
        Step n = new Step("Calcolo la radice di " + risBase.toString() + " con indice " + risEsp.toString(), exp.replace(this.toString(), ris.toString()));
        
        Steps st = new Steps();
        st.setSteps(risBase);
        st.setSteps(risEsp);
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
        return "(" + indice.toString() + ")sqrt(" + argomento.toString() + ")";
    }
}
