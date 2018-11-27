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
public class Log implements IBinary {
    private String expBase = "10", expArg = "";
    private IOperation base = null, argomento = null;
    
    /**
     *
     * @param base la base del logaritmo
     * @param argomento l'argomento del logaritmo
     */
    public Log(IOperation base, IOperation argomento){
        this.base = base;
        this.argomento = argomento;
    }
    
    /**
     *
     * @param base la base del logaritmo
     * @param argomento l'argomento del logaritmo
     */
    public Log(String base, String argomento){
        this.expBase = base;
        this.expArg = argomento;
    }

    /**
     *
     * @return il valore del logaritmo
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valuta() throws Exception{
        if(base == null)
            this.base = Exp.parseExp(this.expBase);
        
        if(argomento == null)
            this.argomento = Exp.parseExp(this.expArg);
        
        
        Double risBase = base.valuta().toDouble();
        Double risArg = argomento.valuta().toDouble();
        
        if(risArg <= 0)
            throw new IllegalArgumentException("L'argomento deve essere strettamente maggiore di 0.");
        
        if(risBase <= 0 || risBase == 1)
            throw new IllegalArgumentException("La base deve essere strettamente maggiore di 0 e diversa da 1.");
        
        return new Num(Math.log(argomento.valuta().toDouble()) / Math.log(base.valuta().toDouble()));
    }

    /**
     *
     * @return il valore del logaritmo
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Steps valutaPassoAPasso(String exp) throws Exception {
        if(base == null)
            this.base = Exp.parseExp(this.expBase);
        
        if(argomento == null)
            this.argomento = Exp.parseExp(this.expArg);
        
        
        Steps risBase = base.valutaPassoAPasso(exp);
        Steps risArg = argomento.valutaPassoAPasso(exp);
                
        if(risArg.getRes().toDouble() <= 0)
            throw new IllegalArgumentException("L'argomento deve essere strettamente maggiore di 0.");
        
        if(risBase.getRes().toDouble() <= 0 || risBase.getRes().toDouble() == 1)
            throw new IllegalArgumentException("La base deve essere strettamente maggiore di 0 e diversa da 1.");
        
        Num ris = new Num(Math.log(risArg.getRes().toDouble()) / Math.log(risBase.getRes().toDouble()));
        
        Step n = new Step("Calcolo logaritmo in base " + risBase.toString() + " di " + risArg.toString(), exp.replace(this.toString(), ris.toString()));
        
        Steps st = new Steps();
        st.setSteps(risBase);
        st.setSteps(risArg);
        st.setResult(ris);
        
        st.addStep(n);
        
        return st;
    }

    
    /**
     *
     * @return la stringa da poter stampare
     */
    @Override
    public String toString() {
        return "(" + base.toString() + ")log(" + argomento.toString() + ")";
    }
}
