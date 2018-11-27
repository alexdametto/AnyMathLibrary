/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dametto.alex;

import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class Steps {
    private Num res;
    
    private ArrayList<Step> steps;
    
    public Steps() {
        this.res = null;
        this.steps = new ArrayList<Step>();
    }
    
    public Num getRes() {
        return this.res;
    }
    
    public void setSteps(Steps steps) {
        this.steps.addAll(steps.steps);
    }
    
    public void addStep(Step s) {
        this.steps.add(s);
    }
    
    public void setResult(Num res) {
        this.res = res;
    }
    
    public ArrayList<Step> getSteps() {
        return this.steps;
    }
    
    @Override
    public String toString() {
        return this.res.toString();
    }
}
