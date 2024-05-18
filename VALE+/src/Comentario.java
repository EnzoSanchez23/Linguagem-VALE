import java.text.CharacterIterator;

public class Comentario extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        if (codigo.current() == '/') {
            codigo.next();
            if (codigo.current() == '/') {
                codigo.next();
                if(codigo.current() != '\n'){
                    String comment = readComment(codigo);
                    return new Token(comment, "Comentario");

                }
            }
        }

        return null;
    }

    private String readComment(CharacterIterator codigo) {

        String comentario = "";

        while (Character.isLetterOrDigit(codigo.current()) || codigo.current() == ' ') {
            comentario += codigo.current();
            codigo.next();
        }

        return comentario;
    }

}

/*
Character.isLetterOrDigit(codigo.current())
 * String comment = "";
 * 
 * if (codigo.current() == '/') {
 * codigo.next();
 * if (codigo.current() == '/') {
 * codigo.next();
 * while (codigo.current() != CharacterIterator.DONE) {
 * comment = readComment(codigo);
 * 
 * }
 * }
 * }
 * 
 * if(codigo.current() == '\n' || codigo.current() == '#' || codigo.current() ==
 * CharacterIterator.DONE){
 * return new Token("COMENTARIO", comment);
 * }
 * 
 * 
 */