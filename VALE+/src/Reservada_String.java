import java.text.CharacterIterator;

public class Reservada_String extends AFD{

    @Override
    public Token evaluate(CharacterIterator codigo) {

        String ref = "ABCD";

        for (char c : ref.toCharArray()) {
            if (codigo.current() == c) {
                codigo.next();
            }
        }

        if (codigo.current() == ' ' || codigo.current() == '\n' || codigo.current() == CharacterIterator.DONE) {
            return new Token(ref, "Reservada_String");
        }

        return null;
    }

}
