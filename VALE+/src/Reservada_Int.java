import java.text.CharacterIterator;

public class Reservada_Int extends AFD {
    
    @Override
    public Token evaluate(CharacterIterator codigo) {
        
        String ref = "CHEIO";

        for(char c:ref.toCharArray()){
            if(codigo.current() == c){
                codigo.next();
            }
        }

        if(codigo.current() == ' ' || codigo.current() == '\n' || codigo.current() == CharacterIterator.DONE || codigo.current() == ';'){
            return new Token(ref, "Reservada_Int");
        }

        return null;
    }

}
