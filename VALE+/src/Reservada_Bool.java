import java.text.CharacterIterator;

public class Reservada_Bool extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        String ref = "BOOL";

        for (char c : ref.toCharArray()) {
            if (codigo.current() == c) {
                codigo.next();
            } else {
                return null;
            }
        }

        if (codigo.current() == ' ' || codigo.current() == '\n' || codigo.current() == CharacterIterator.DONE) {
            return new Token(ref, "Reservada_Bool");
        }

        return null;
    }

}
