import java.text.CharacterIterator;

public class Reservada_True extends AFD{

    @Override
    public Token evaluate(CharacterIterator codigo) {
        
        String ref = "RAIZ";

        for(char c:ref.toCharArray()){
            if(codigo.current() == c){
                codigo.next();
            }
        }

        if(codigo.current() == ' ' || codigo.current() == '\n' || codigo.current() == CharacterIterator.DONE || codigo.current() == ';'){
            return new Token(ref, "Reservada_True");
        }

        return null;
    }
    
}
