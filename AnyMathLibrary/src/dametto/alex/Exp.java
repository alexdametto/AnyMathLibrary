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
public class Exp {
    
    private IOperation root = null;
    private String expression = "";
    
    /**
     *
     * @param exp la stringa dell'espressione
     */
    public Exp(String exp) {
        this.expression = exp;
    }
    
    /**
     *
     * @throws SyntaxException in caso di errori di sintassi
     */
    public void parse() throws SyntaxException{
        if(root != null)
            this.expression = root.toString();
        else this.root = parseExp(this.expression);
    }
    
    /**
     *
     * @param exp la stringa da fare il parsing
     * @return il nodo operazione dell'espressione
     * @throws SyntaxException in caso di errori di sintassi
     */
    protected static IOperation parseExp(String exp) throws SyntaxException {
        exp = exp.replaceAll("\\s+",""); 
        
        if(exp.length() == 0)
            return new Num(0);
        
        int countParentesi = 0, i = exp.length()-1;

        while(i >= 0){
            char c = exp.charAt(i);

            switch (c){

                case '+':
                    if(countParentesi == 0){
                        Somma nodo = new Somma(parseExp(exp.substring(0, i)), parseExp(exp.substring(i+1, exp.length())));
                        return nodo;
                    }

                    break;
                case '-':
                    if(countParentesi == 0){
                        Somma nodo = new Somma(parseExp(exp.substring(0, i)), new Diff(parseExp(exp.substring(i+1, exp.length()))));
                        return nodo;
                    }

                    break;

                case '(':
                case '[':
                case '{':
                    countParentesi++;
                    break;

                case ')':
                case ']':
                case '}':
                    countParentesi--;
                    break;
            }

            i--;
        }

        i = exp.length()-1;
        while(i >= 0){
            char c = exp.charAt(i);

            switch (c){

                case '*':
                    if(countParentesi == 0){
                        Mult nodo = new Mult(parseExp(exp.substring(0, i)), parseExp(exp.substring(i+1, exp.length())));
                        return nodo;
                    }

                    break;
                case '/':
                    if(countParentesi == 0){
                        Div nodo = new Div(parseExp(exp.substring(0, i)), parseExp(exp.substring(i+1, exp.length())));
                        return nodo;
                    }

                    break;

                case '(':
                case '[':
                case '{':
                    countParentesi++;
                    break;

                case ')':
                case ']':
                case '}':
                    countParentesi--;
                    break;
            }

            i--;
        }
        
        IOperation op = findSpecialFunction(exp);
        
        if(op == null){ // HO UN SOTTOSTRINGA DATA DA DELLE PARENTESI CON DENTRO UN ALTRA ESPRESSIONE
            if(0 != exp.length()-1){
                String temp = exp.substring(1, exp.length()-1);
                            
                op = new Parentesi(parseExp(temp), Parentesi.getParentesi(exp.charAt(0)));
                return op;
            }
        }
        
        return op;
    }
    
    /**
     *
     * @param exp l'espressione
     * @return il nodo operazione
     * @throws SyntaxException in caso di errori di sintassi
     */
    protected static IOperation findSpecialFunction(String exp) throws SyntaxException{
        if(exp.length() > 4){
            if(exp.substring(0, 4).equals("sinh")){
                return new Sinh(parseExp(exp.substring(4 + 1, exp.length() - 1)));
            }
            else if(exp.substring(0, 4).equals("cosh")){
                return new Cosh(parseExp(exp.substring(4 + 1, exp.length() - 1)));
            }
            else if(exp.substring(0, 4).equals("tanh")){
                return new Tanh(parseExp(exp.substring(4 + 1, exp.length() - 1)));
            }
        }
        if(exp.length() > 6){
            if(exp.substring(0, 6).equals("arcsin")){
                return new ArcSin(parseExp(exp.substring(6 + 1, exp.length() - 1)));
            }
            else if(exp.substring(0, 6).equals("arccos")){
                return new ArcCos(parseExp(exp.substring(6 + 1, exp.length() - 1)));
            }
            else if(exp.substring(0, 6).equals("arctan")){
                return new ArcTan(parseExp(exp.substring(6 + 1, exp.length() - 1)));
            }
        }
        if(exp.length() > 3){
            if(exp.substring(0, 3).equals("sin")){
                return new Sin(parseExp(exp.substring(3 + 1, exp.length() - 1)));
            }
            else if(exp.substring(0, 3).equals("cos")){
                return new Cos(parseExp(exp.substring(3 + 1, exp.length() - 1)));
            }
            else if(exp.substring(0, 3).equals("tan")){
                return new Tan(parseExp(exp.substring(3 + 1, exp.length() - 1)));
            }
        }
        
        int i = exp.indexOf("^");
        int countParentesi = contaParentesi(exp, 0, i);
        if( i != -1 && countParentesi == 0){
            String sx = exp.substring(0, i);
            String dx = exp.substring(i + 1, exp.length());
            
            return new Potenza(parseExp(sx), parseExp(dx));
        }
        
        i = exp.indexOf("sqrt");
        countParentesi = contaParentesi(exp, 0, i);
        if(i != -1 && countParentesi == 0){
            String sx = exp.substring(0, i);
            String dx = exp.substring(i + 4, exp.length());
            
            if(sx.equals(""))
                return new Sqrt(new Num(2), parseExp(dx));
            else return new Sqrt(parseExp(sx), parseExp(dx));
        }
        
        i = exp.indexOf("log");
        countParentesi = contaParentesi(exp, 0, i);
        if(i != -1 && countParentesi == 0){
            String sx = exp.substring(0, i);
            String dx = exp.substring(i + 3, exp.length());
            
            if(sx.equals(""))
                return new Log(new Num(10), parseExp(dx));
            else return new Log(parseExp(sx), parseExp(dx));
        }
        
        i = exp.indexOf("ln");
        countParentesi = contaParentesi(exp, 0, i);
        if(i != -1 && countParentesi == 0){
            String dx = exp.substring(i + 2, exp.length());
            
            return new Log(parseExp("e"), parseExp(dx));
        }
        
        if(exp.contains("(") || exp.contains(")") || exp.contains("[") || exp.contains("]") || exp.contains("{") || exp.contains("}"))
            return null;
        
        if(exp.equals("e"))
            return new Num(Math.E);
        else if(exp.equals("pi"))
            return new Num(Math.PI);
        
        try{
            if(exp.charAt(exp.length()-1) > 'a' && exp.charAt(exp.length()-1) < 'z')
            {
                char var = exp.substring(exp.length()-1, exp.length()).charAt(0);
                Num number = new Num(Double.valueOf(exp.substring(0, exp.length()-1)));
                
                return new Variabile(number, var);
            }  
            
            return new Num(Double.valueOf(exp));
        }catch(Exception ex){
            throw new SyntaxException("Sintassi sbagliata vicino a '" + exp + "'.");
        }
    }
    
    /**
     *
     * @param exp l'espressione
     * @param inizio indice di inizio
     * @param fine indice di fine
     * @return differenza fra numero parentesi aperte e numero parentesi chiuse
     */
    protected static int contaParentesi(String exp, int inizio, int fine){
        int count = 0;
        for(int i = inizio; i <= fine; i++){
            char c = exp.charAt(i);
            
            switch(c){
                    case '(':
                    case '[':
                    case '{':
                        count++;
                        break;

                    case ')':
                    case ']':
                    case '}':
                        count--;
                        break;
            }
        }
        return count;
    }
    
    /**
     *
     * @return il risultato dell'espressione
     * @throws Exception in caso di errori
     */
    public Num valuta() throws Exception{
        if(this.root == null)
            throw new Exception("Non hai effettuato il parsing dell'espressione.");
        
        return this.root.valuta();
    }
    
    /**
     *
     * @return il risultato dell'espressione
     * @throws Exception in caso di errori
     */
    public Steps valutaPassoAPasso() throws Exception{
        if(this.root == null)
            throw new Exception("Non hai effettuato il parsing dell'espressione.");
        
        Step n = new Step("Valutazione passo a passo di " + this.root, this.expression);
        
        Steps ris = this.root.valutaPassoAPasso(this.toString());
        
        Steps s = new Steps();
        s.addStep(n);
        s.setSteps(ris);
        s.setResult(new Num(ris.getRes().toDouble()));
        
        return s;
    }
    
    
    /**
     *
     * @return la stringa da poter stampare
     */
    @Override
    public String toString(){
        if(this.root != null)
            return this.root.toString();
        else return "";
    }
}
