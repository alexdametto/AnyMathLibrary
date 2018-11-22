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
    public Num valutaPassoAPasso() throws Exception {
        if(indice == null)
            this.indice = Exp.parseExp(this.expInd);
        
        if(argomento == null)
            this.argomento = Exp.parseExp(this.expArg);
        
        Num risArg = argomento.valutaPassoAPasso();
        Num risInd = indice.valutaPassoAPasso();
        
        System.out.println("Calcolo la radice di " + risArg.toString() + " con indice " + risInd.toString());
        
        if(risArg.toDouble() < 0)
            throw new IllegalArgumentException("L'argomento deve essere maggiore di zero.");
        
        if(risInd.toDouble() == 0)
            throw new IllegalArgumentException("L'indice deve essere diverso da zero.");
        
        return new Num(Math.pow(risArg.toDouble(), 1/risInd.toDouble()));
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
