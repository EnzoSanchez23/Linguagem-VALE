import java.text.CharacterIterator;

public class Reservada_IF extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        String ref_IF = "XESQ";

        for (char c : ref_IF.toCharArray()) {
            if (codigo.current() == c) {
                codigo.next();
            }
        }

        // System.out.println("terminou if");
        if (codigo.current() == ' ' || codigo.current() == '\n' || codigo.current() == CharacterIterator.DONE) {
            return new Token(ref_IF, "Reservada_IF");
        }

        return null;
    }

}
