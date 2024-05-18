import java.text.CharacterIterator;

public class Reservada_Return extends AFD {
    
    @Override
    public Token evaluate(CharacterIterator codigo) {
        
        String ref = "return";

        for(char c:ref.toCharArray()){
            if(codigo.current() == c){
                codigo.next();
            }else{
                return null;
            }
        }

        if(codigo.current() == ' ' || codigo.current() == '\n' || codigo.current() == CharacterIterator.DONE){
            return new Token(ref, "Reservada_Return");
        }

        return null;
    }

}
