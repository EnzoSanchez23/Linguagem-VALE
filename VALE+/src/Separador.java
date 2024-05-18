import java.text.CharacterIterator;

public class Separador extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        if(codigo.current() == ','){
            codigo.next();
        }
    
        if(codigo.current()==' '||codigo.current()=='\n'||codigo.current()==CharacterIterator.DONE||codigo.current()==';'){
            return new Token(",", "Separador");
        }
        
        return null;
    }
}
