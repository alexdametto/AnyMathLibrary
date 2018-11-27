/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anymathlibrary;

import dametto.alex.Exp;
import dametto.alex.Step;
import dametto.alex.Steps;

/**
 *
 * @author Alex
 */
public class AnyMathLibrary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Exp e = new Exp("2*3+4-6");
        e.parse();
        Steps n = e.valutaPassoAPasso();
        
        for(Step a : n.getSteps()) {
            if(!a.getDescription().equals(""))
                System.out.println(a.getExp());
        }
    }
    
}
