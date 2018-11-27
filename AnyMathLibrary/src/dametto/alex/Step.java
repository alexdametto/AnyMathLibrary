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
public class Step {
    private String description = "";
    private String exp = "";
    
    public Step(String description, String exp) {
        this.description = description;
        this.exp = exp;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getExp() {
        return this.exp;
    }
}
