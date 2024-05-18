import java.text.CharacterIterator;

public class Id extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        if (Character.isAlphabetic(codigo.current())) {
            String letter = readLetter(codigo);

            if (endLetter(codigo)) {
                return new Token(letter, "Id");
            }

        }

        return null;
    }

    private String readLetter(CharacterIterator codigo) {
        String letter = "";
        while (Character.isAlphabetic(codigo.current()) || Character.isDigit(codigo.current())) {
            letter += codigo.current();
            codigo.next();
        }
        return letter;
    }

    private boolean endLetter(CharacterIterator codigo) {

        return codigo.current() == ' ' || codigo.current() == '\n' || codigo.current() == CharacterIterator.DONE || codigo.current() == ';' || codigo.current() == '{' || codigo.current() == '}' || codigo.current() == '+';

    }

}
