import java.text.CharacterIterator;

public class Reservada_IFelse extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        String ref_IFelse = "XESQDELE";

        for (char c : ref_IFelse.toCharArray()) {
            if (codigo.current() == c) {
                codigo.next();
            }
        }

        // System.out.println("terminou if");
        if (codigo.current() == ' ' || codigo.current() == '\n' || codigo.current() == CharacterIterator.DONE) {
            return new Token(ref_IFelse, "Reservada_IFelse");
        }

        return null;
    }

}
