import java.text.CharacterIterator;

public class Reservada_While extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        String ref = "DURANTE";

        for (char c : ref.toCharArray()) {
            if (codigo.current() == c) {
                codigo.next();
            }
        }


        //System.out.println(codigo.current());
        if (codigo.current() == ' ' || codigo.current() == CharacterIterator.DONE) {
            return new Token(ref, "Reservada_While");
        }

        return null;
    }

}
