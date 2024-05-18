import java.text.CharacterIterator;

public class Reservada_Else extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        String ref_Else = "DELE";

        for (char c : ref_Else.toCharArray()) {
            if (codigo.current() == c) {
                codigo.next();
            }
        }

        if (codigo.current() == ' ' || codigo.current() == '\n' || codigo.current() == CharacterIterator.DONE) {
            return new Token(ref_Else, "Reservada_Else");
        }

        return null;
    }

}