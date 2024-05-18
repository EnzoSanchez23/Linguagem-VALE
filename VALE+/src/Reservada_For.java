import java.text.CharacterIterator;

public class Reservada_For extends AFD {
    @Override
    public Token evaluate(CharacterIterator codigo) {

        String ref = "LOOP";

        for (char c : ref.toCharArray()) {
            if (codigo.current() == c) {
                codigo.next();
            }
        }

        //System.out.println("terminou for");
        if (codigo.current() == ' ' || codigo.current() == '\n' || codigo.current() == CharacterIterator.DONE) {
            return new Token(ref, "Reservada_For");
        }

        return null;
    }

}
