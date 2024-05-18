import java.text.CharacterIterator;

public class Fim extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        //System.out.println("antes do fim");
        if (codigo.current() == ';') {
            codigo.next();
          //  System.out.println("aqui");
            return new Token(";", "Fim");

        }

        return null;
    }

}
