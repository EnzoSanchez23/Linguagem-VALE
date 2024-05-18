import java.text.CharacterIterator;

public class Operador_Mat extends AFD {

    // Le os codigo de entrada
    @Override
    public Token evaluate(CharacterIterator codigo) {

        // Percorre caracter por caracter do codigo
        switch (codigo.current()) {
            case '+':
                // Checa se o caracter é o "-"
                codigo.next();
                if(codigo.current() == '+'){
                    codigo.next();
                    return new Token("++", "INC");    
                }
                return new Token("+", "PLUS");
            case '-':
                // Checa se o caracter é o "-"
                codigo.next();
                if(codigo.current() == '-'){
                    codigo.next();
                    return new Token("--", "DEC");    
                }
                return new Token("-", "MINUS");
            case '*':
                // Checa se o caracter é o "*"
                codigo.next();
                if(codigo.current() == '*'){
                    codigo.next();
                    return new Token("**", "POTENCIA");
                }
                return new Token("*", "MULT");
            case '/':
                if (codigo.next() == '/') {
                    return null;
                }
                // Checa se o caracter é o "/"
                codigo.next();
                return new Token("/", "DIV");
            case '>':
                // Checa se o caracter é o "*"
                codigo.next();
                if(codigo.current() == '='){
                    codigo.next();
                    return new Token(">=", "MaiorIgual");
                }
                return new Token(">", "MAIOR");
            case '<':
                // Checa se o caracter é o "*"
                codigo.next();
                if(codigo.current() == '='){
                    codigo.next();
                    return new Token("<=", "MenorIgual");    
                }
                return new Token("<", "MENOR");
            case '!':
                codigo.next();
                if(codigo.current()== '='){
                    codigo.next();
                    return new Token("!=","Diferente");
                }
            case '%':
                codigo.next();
                return new Token("%", "DivResto");
            default:
                return null;
        }
    }
}
