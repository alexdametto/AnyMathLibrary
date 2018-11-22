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
public class Potenza implements IBinary {
    private String expBase = "", expEsp = "";
    private IOperation base = null, esponente = null;
    
    /**
     *
     * @param base la base della potenza
     * @param esponente l'esponente della potenza
     */
    public Potenza(IOperation base, IOperation esponente){
        this.base = base;
        this.esponente = esponente;
    }
    
    
    /**
     *
     * @param base la base della potenza
     * @param esponente l'esponente della potenza
     */
    public Potenza(String base, String esponente){
        this.expBase = base;
        this.expEsp = esponente;
    }

    /**
     *
     * @return il valore della potenza
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valuta() throws Exception{
        if(base == null)
            this.base = Exp.parseExp(this.expBase);
        if(esponente == null)
            this.esponente = Exp.parseExp(this.expEsp);
        return new Num(Math.pow(base.valuta().toDouble(), esponente.valuta().toDouble()));
    }
    
    
    /**
     *
     * @return il valore della potenza
     * @throws Exception in caso di argomento non valido
     */
    @Override
    public Num valutaPassoAPasso() throws Exception {
        if(base == null)
            this.base = Exp.parseExp(this.expBase);
        if(esponente == null)
            this.esponente = Exp.parseExp(this.expEsp);
        
        Num risBase = base.valutaPassoAPasso();
        Num risEsp = esponente.valutaPassoAPasso();
        
        System.out.println("Calcolo potenza in base " + risBase.toString() + " alla " + risEsp.toString());
        
        return new Num(Math.pow(risBase.toDouble(), risEsp.toDouble()));
    }
    
    
    /**
     *
     * @return la stringa da poter stampare
     */
    @Override
    public String toString() {
        return base.toString() + "^" + esponente.toString();
    }
}
