public class Token {

    //Atibutos
    String tipo;
    String lexema;

    //Construtor
    public Token(String lexema, String tipo){
        this.tipo = tipo;
        this.lexema = lexema;
    }

    //toString para imprimir <tipo, lexema>
    public String toString(){
        return "<" + lexema + ", " + tipo + ">";
    }
}
