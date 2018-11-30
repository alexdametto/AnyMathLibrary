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
public class Parentesi implements IUnary {
    public static final int TONDA = 1;
    public static final int QUADRA = 2;
    public static final int GRAFFA = 3;
    
    private int type = -1;
    private IOperation arg;
    private String exp = "";
    
    public Parentesi(IOperation arg, int par) {      
        this.type = par;
        this.arg = arg;
    }

    @Override
    public Num valuta() throws Exception {
        return this.arg.valuta();
    }

    @Override
    public Steps valutaPassoAPasso(String exp) throws Exception {
        if(arg == null)
            this.arg = Exp.parseExp(this.exp);
        
        Steps risArg = arg.valutaPassoAPasso(exp);
        
        Num ris = new Num(risArg.getRes().toDouble());
                        
        Step n = new Step("", exp.replace(this.toString(), ris.toString()));
                
        Steps st = new Steps();
        st.setResult(ris);
        st.setSteps(risArg);
        st.addStep(n);
        
        return st;
    }
    
    /**
     *
     * @return la stringa da poter stampare
     */
    @Override
    public String toString() {      
        if(this.type == Parentesi.TONDA)
            return "(" + arg.toString() + ")";
        else if(this.type == Parentesi.QUADRA)
            return "[" + arg.toString() + "]";
        else if(this.type == Parentesi.GRAFFA)
            return "{" + arg.toString() + "}";
        else return "NaN";
    }
    
    public static int getParentesi(char par) {
        if(par == '(' || par == ')')
            return Parentesi.TONDA;
        else if(par == '[' || par == ']')
            return Parentesi.QUADRA;
        else if(par == '{' || par == '}')
            return Parentesi.GRAFFA;
        else return -1;
    }
}
