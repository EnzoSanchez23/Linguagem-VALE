import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        try ( BufferedReader reader = new BufferedReader(new FileReader("arquivos\\codigo.txt")); 
        BufferedWriter writer = new BufferedWriter(new FileWriter("arquivos\\output.txt"))) {

            String line;
            StringBuilder sb = new StringBuilder();

            while ( (line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            String newCode = sb.toString();

            Lexer lexer = new Lexer(newCode);
            List<Token> tokens = lexer.getTokens();
            for (Token t : tokens) {
                //System.out.println(t);
                writer.write(t.toString());
                writer.newLine();
            }
            
            Parser parser = new Parser(tokens);
            parser.main();

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + e.getMessage());

        }

        /*
         * Lexer lexer = new Lexer(codigo);
         * List<Token> tokens = lexer.getTokens();
         * for (Token t : tokens) {
         * System.out.println(t);
         * }
         */

    }

}
