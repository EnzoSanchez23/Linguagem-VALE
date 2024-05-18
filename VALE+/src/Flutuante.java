import java.text.CharacterIterator;

public class Flutuante extends AFD {

    @Override
    public Token evaluate(CharacterIterator codigo) {

        if (Character.isDigit(codigo.current())) {

            String number = readNumber(codigo);
            if (codigo.current() == '.') {

                codigo.next();
                if (Character.isDigit(codigo.current())) {
                    String number2 = readNumber(codigo);

                    if (endNumber(codigo)) {
                        return new Token(number + '.' + number2, "NUM_Flutuante");
                    }

                }

            }

        }
        
        return null;
    }

    private String readNumber(CharacterIterator codigo) {
        String number = "";
        while (Character.isDigit(codigo.current())) {
            number += codigo.current();
            codigo.next();
        }
        return number;
    }

    private boolean endNumber(CharacterIterator codigo) {

        return codigo.current() == ' ' || codigo.current() == '\n' || codigo.current() == CharacterIterator.DONE
                || codigo.current() == ';';

    }

}
