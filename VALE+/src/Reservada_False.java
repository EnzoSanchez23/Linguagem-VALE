import java.text.CharacterIterator;

public class Reservada_False extends AFD{

    @Override
    public Token evaluate(CharacterIterator codigo) {
        
        String ref = "NUTELA";

        for(char c:ref.toCharArray()){
            if(codigo.current() == c){
                codigo.next();
            }
        }

        if(codigo.current() == ' ' || codigo.current() == '\n' || codigo.current() == CharacterIterator.DONE || codigo.current() == ';'){
            return new Token(ref, "Reservada_False");
        }

        return null;
    }
    
}
