import java.text.CharacterIterator;

public class Reservada_Input extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        String ref = "ENTRA";

        for (char c : ref.toCharArray()) {
            if (codigo.current() == c) {
                codigo.next();
            }
        }

        //System.out.println("terminou input");
        if (codigo.current() == ' ' ||codigo.current() == CharacterIterator.DONE) {
            return new Token(ref, "Reservada_Input");
        }

        return null;
    }

}
