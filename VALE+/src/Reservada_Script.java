import java.text.CharacterIterator;

public class Reservada_Script extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        String ref = "SCRIPT";

        for (char c : ref.toCharArray()) {
            if (codigo.current() == c) {
                codigo.next();
            }
        }

        if (codigo.current() == '\n' || codigo.current() == CharacterIterator.DONE) {
            return new Token(ref, "Reservada_Script");
        }

        return null;
    }

}
