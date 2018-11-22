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
    public Num valutaPassoAPasso() throws Exception {
        if(base == null)
            this.base = Exp.parseExp(this.expBase);
        
        if(argomento == null)
            this.argomento = Exp.parseExp(this.expArg);
        
        
        Num risBase = base.valutaPassoAPasso();
        Num risArg = argomento.valutaPassoAPasso();
        
        System.out.println("Calcolo logaritmo in base " + risBase.toString() + " di " + risArg.toString());
        
        if(risArg.toDouble() <= 0)
            throw new IllegalArgumentException("L'argomento deve essere strettamente maggiore di 0.");
        
        if(risBase.toDouble() <= 0 || risBase.toDouble() == 1)
            throw new IllegalArgumentException("La base deve essere strettamente maggiore di 0 e diversa da 1.");
        
        return new Num(Math.log(risArg.toDouble()) / Math.log(risBase.toDouble()));
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
