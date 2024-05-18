import java.text.CharacterIterator;

public class Reservada_Print extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        String ref = "MOSTRA";

        for (char c : ref.toCharArray()) {
            if (codigo.current() == c) {
                codigo.next();
            }
        }

        //System.out.println("terminou print");
        if (codigo.current() == ' ' ||codigo.current() == CharacterIterator.DONE) {
            return new Token(ref, "Reservada_Print");
        }

        return null;
    }

}
