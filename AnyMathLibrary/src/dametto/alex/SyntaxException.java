/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dametto.alex;

/**
 *
 * @author Alex
 */
public class SyntaxException extends Exception {
    public SyntaxException(){
        
    }
    
    /**
     *
     * @param exp stringa da stampare
     */
    public SyntaxException(String exp){
        super(exp);
    }
}
