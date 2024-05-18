import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    
    private List<AFD> afds;
    private List<Token> tokens;
    private CharacterIterator codigo;

    public Lexer(String codigo){
        this.codigo = new StringCharacterIterator(codigo);
        tokens = new ArrayList<>();
        afds = new ArrayList<>();

        afds.add(new Fim());
        afds.add(new Texto());
        //Tipagem
        afds.add(new Reservada_Int());
        afds.add(new Reservada_Bool());
        afds.add(new Reservada_Float());
        afds.add(new Reservada_String());
        afds.add(new Reservada_True());
        afds.add(new Reservada_False());
        //Reservadas
        //afds.add(new Reservada_Return());//Return
        afds.add(new Reservada_IF());
        afds.add(new Reservada_Else());
        afds.add(new Reservada_IFelse());
        afds.add(new Reservada_For());
        afds.add(new Reservada_While());
        afds.add(new Reservada_Input());
        afds.add(new Reservada_Print());
        afds.add(new Reservada_Script());
        afds.add(new Operador());
        afds.add(new Id());
        afds.add(new Operador_Mat());
        afds.add(new Separador());
        afds.add(new Numero());
        afds.add(new Flutuante());
        afds.add(new Comentario());        
        
    }

    //Para pular os espacos em branco
    public void skipWhiteSpace(){
        while(codigo.current() == ' ' || codigo.current() == '\n'){
            codigo.next();
        }
    }

    public List<Token> getTokens(){
        boolean accepted;

        //Percorre o codigo testando os caracter nos automatos
        while(codigo.current() != CharacterIterator.DONE){
            //System.out.println("while"+codigo.current());
            accepted = false;
            skipWhiteSpace();
            if(codigo.current() == CharacterIterator.DONE)break;
            for(AFD afd: afds){
                int pos = codigo.getIndex();
                Token t = afd.evaluate(codigo);
                if(t != null){
                    accepted = true;
                    tokens.add(t);
                    break;
                }else{
                    codigo.setIndex(pos);
                }
            }
            if(accepted)continue;
            throw new RuntimeException("Token not recognized");
        }

        tokens.add(new Token("$", "EOF"));
        return tokens;
    }
}