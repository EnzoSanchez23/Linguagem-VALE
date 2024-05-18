import java.text.CharacterIterator;

public class Texto extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        String ref = ""; 

        if(codigo.current() == '"'){
            ref += codigo.current();
            codigo.next();
            while (codigo.current() != '"') {
                ref += codigo.current();
                codigo.next();
                
            }
            ref += codigo.current();
            codigo.next();
        }
        
        if(endTexto(codigo)){
            return new Token(ref, "Texto");
        }

        return null;
    }
    
    private boolean endTexto(CharacterIterator codigo) {
        
        if (codigo.current() == ' ' || codigo.current() == ';' || codigo.current() == '\n' || codigo.current() == CharacterIterator.DONE){
            return true;
        }

        return false;
        
         

    }

}
