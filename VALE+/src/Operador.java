import java.text.CharacterIterator;

public class Operador extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        // Percorre caracter por caracter do codigo
        switch (codigo.current()) {
            case '(':
                // Checa se o caracter é o "("
                codigo.next();
                return new Token("(", "LPAREN");

            case ')':
                // Checa se o caracter é o ")"
                codigo.next();
                return new Token(")", "RPAREN");
            
            case '{':
                // Checa se o caracter é o "{"
                codigo.next();
                return new Token("{", "LCHAVE");

            case '}':
                // Checa se o caracter é o "}"
                codigo.next();
                return new Token("}", "RCHAVE");

            case '=':
                // Checa se o caracter é o "="
                codigo.next();
                if(codigo.current() == '='){
                    codigo.next();
                    return new Token("==", "ATRIBUICAO_COND");    
                }
                return new Token("=", "ATRIBUICAO");

            case '[':
                // Checa se o caracter é o "["
                codigo.next();
                return new Token("[", "LColch");

            case ']':
                // Checa se o caracter é o "]"
                codigo.next();
                return new Token("]", "RColch");

            default:
                return null;
        }
    }

}
