/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dametto.alex;

import dametto.alex.Num;

/**
 *
 * @author alex
 */
public interface IOperation {
    /* Composite DESIGN PATTERN */
    
    /**
     *
     * @return il valore della valutazione dell'operazione
     * @throws Exception in caso di argomento non valido
     */
    public Num valuta() throws Exception;
    
    /**
     *
     * @return il valore della valutazione dell'operazione
     * @throws Exception in caso di argomento non valido
     */
    public Num valutaPassoAPasso() throws Exception;
    
    /**
     *
     * @return la stringa da poter stampare
     */
    @Override
    public String toString();
}
