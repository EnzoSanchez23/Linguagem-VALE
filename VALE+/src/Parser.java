import java.util.List;

public class Parser {

    List<Token> tokens;
    Token token;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Token nextToken() {
        if (tokens.size() > 0) {
            return tokens.remove(0);
        }
        return null;
    }

    public void error(String regra) {
        System.out.println("Regra: " + regra);
        System.exit(0);// Encerra o programa
    }

    public void main() {
        token = nextToken();
        if (BlocoRec()) {
            if (token.lexema.equals("$")) {
                System.out.println("COMPILADO");
            }
        } else {
            error("Sintatica errada");
        }
    }


    // Gramatica Recursividade
    public boolean BlocoRec() {

        if (token.lexema.equals("SCRIPT")) {
            if (startCode()) {
                BlocoRec();
                return true;
            } else {
                error("BlocoSCRIPT");
            }
        }

        else if (token.lexema.equals("LOOP")) {// First LOOP
            if (loop()) {
                BlocoRec();
                return true;
            } else {
                error("BlocoLOOP");
            }
        }

        // While
        else if (token.lexema.equals("DURANTE")) { // First DURANTE
            if (durante()) {
                BlocoRec();
                return true;
            } else {
                error("BlocoDURANTE");
            }
        }

        // Print
        else if (token.lexema.equals("MOSTRA")) { // First MOSTRA
            if (mostra()) {
                BlocoRec();
                return true;
            } else {
                error("BlocoMOSTRA");
            }
        }

        // Scanf
        else if (token.lexema.equals("ENTRA")) { //First ENTRA
            if (entra()) {
                BlocoRec();
                return true;
            } else {
                error("BlocoENTRA");
            }
        }

        else if (token.lexema.equals("XESQ")){ //First IF
            if(Gram_If()){
                BlocoRec();
                return true;
            }else{
                error("BlocoGramIF");
            }
        }

        else if (token.lexema.equals("DELE")){ // First ELSE
            if(Gram_Else()){
                BlocoRec();
                return true;
            }else{
                error("BlocoElse");
            }
        }

        else if (token.lexema.equals("XESQDELE")){ //First IF_ELSE
            if(Gram_IFelse()){
                BlocoRec();
                return true;
            }else{
                error("BlocoGramIFelse");
            }
        }

        // Declaracao VAR
        else if (token.lexema.equals("CHEIO")
                || token.lexema.equals("QUEBRADO")
                || token.lexema.equals("BOOL")
        ){
            if (Declaracao()) {
                BlocoRec();
                return true;
            } else {
                error("BlocoDECLARACAO");
            }
        }

        else if(token.lexema.equals("ABCD")){
            if(DeclaracaoSTR()){
                BlocoRec();
                return true;
            } else {
                error("BlocoDeclaraSTR");
            }

        }

        // Expressao
        else if (token.tipo.equals("Id")) {
            if (expressao()) {
                BlocoRec();
                return true;
            } else {
                error("BlocoEXPRESSAO");
            }
        }

        // Fim de blocos de codigo
        else if (token.lexema.equals("}")) {
            return true;
        }

        else if (token.lexema.equals("$")) {
            traduz("\nreturn 0;\n}\n");
            return true;
        }

        error("BlocoRec");
        return false;
    }


    // Gramatica IF
    public boolean Gram_If() {

        if (matchT2("Reservada_IF","if")
            && matchL2("(", "(")
            && condicao()
            && matchL2(")", ")")
            && matchL2("{", "{\n")
            && BlocoRec()
            && fimBloco()
            && complementoIF()
        ){
            return true;
        }

        error("Gram_If");
        return false;
    }

    public boolean Gram_Else(){
        if(matchT2("Reservada_Else", "else")
        && matchL2("{", "{\n")
        && BlocoRec()
        && fimBloco()
        ){
            return true;
        }
        error("Gram_Else");
        return false;
    }

    public boolean Gram_IFelse(){
        if(matchT2("Reservada_IFelse", "else if")
        && matchL2("(", "(")
        && condicao()
        && matchL2(")", ")")
        && matchL2("{", "{\n")
        && BlocoRec()
        && fimBloco()
        ){
            return true;
        }

        error("Gram_IFelse");
        return false;
    }

    public boolean complementoIF(){

        if(token.tipo.equals("Reservada_Else")){
            if(Gram_Else()){
                return true;
            }
        }
        else if(token.tipo.equals("Reservada_IFelse")){
            if(Gram_IFelse()){
                return true;
            }
        }
        else{
            return true;
        }
        
        
        error("ComplementoIF");
        return true;
    }


    // Gramatica FOR
    public boolean loop() {
        if (matchT2("Reservada_For", "for")
                && matchL2("(", "(")
                && Declaracao()
                && matchL2(",", "; ")
                && condicao()
                && matchL2(",", "; ")
                && expressao()
                && matchL2(")", ")")
                && matchL2("{", "{\n")
                && BlocoRec()
                && fimBloco()) {

            return true;

        }
        error("loop");
        return false;
    }


    // Gramatica Mostra
    public boolean mostra() {
        if (matchT2("Reservada_Print", "printf")
                && matchL2("(", "(")
                && tiposMostra()
                && matchL2(",", ", ")
                && valorVar()
                && matchL2(")", ");\n")) {

            return true;
        }
        error("mostra");
        return false;
    }

    public boolean tiposMostra() {

        if (matchT2("Reservada_Int", "\"%d\\n\"")) {
            return true;
        } else if (matchT2("Reservada_Float", "\"%f\\n\"")) {
            return true;
        } else if (matchT2("Reservada_String", "\"%s\\n\"")) {
            return true;
        }

        error("Tipo");
        return false;
    }


    // Gramatica Entra
    public boolean entra() {
        if (matchT2("Reservada_Input", "scanf")
                && matchL2("(", "(")
                && tiposInput()
                && matchL2(",", ", ")
                && matchT2("Id", "&" + token.lexema)
                && matchL2(")", ");\n")) {

            return true;
        }
        error("entra");
        return false;
    }

    public boolean tiposInput() {

        if (matchT2("Reservada_Int", "\"%d\"")) {
            return true;
        } else if (matchT2("Reservada_Float", "\"%f\"")) {
            return true;
        } else if (matchT2("Reservada_String", "\"%s\"")) {
            return true;
        }

        error("Tipo");
        return false;
    }


    // Gramatica While
    public boolean durante() {
        if (matchT2("Reservada_While", "while")
        && matchL2("(", "(")
        && condicao()
        && matchL2(")", ")")
        && matchL2("{", "{\n")
        && BlocoRec()
        && fimBloco()
        ){
            return true;
        }
        error("Durante");
        return false;
    }


    // Gramatica Tipo
    public boolean Tipos() {

        if (matchT2("Reservada_Int", "int ")) {
            return true;
        } else if (matchT2("Reservada_Float", "float ")) {
            return true;
        } else if (matchT2("Reservada_Bool", "bool ")) {
            return true;
        } else if (matchT2("Reservada_String", "char ")) {
            return true;
        }

        error("Tipo");
        return false;
    }


    // Gramatica Var e STRING
    public boolean Declaracao() {
        if (Tipos()
        && matchT2("Id", token.lexema + " ")
        && matchL2("=", "= ")
        && valorVar()
        ){
            if(matchT2("Fim", ";\n")){
                return true;
            }
            //traduz("\n");
            return true;
        }
        error("Declaracao");
        return false;
    }

    public boolean DeclaracaoSTR() {
        if (Tipos()
        && matchT2("Id", token.lexema + "[] ")
        && matchL2("=", "= ")
        && matchT2("Texto", token.lexema)
        ){
            if(matchT2("Fim", ";\n")){
                return true;
            }
            return true;
        }
        error("DeclaracaoSTR");
        return false;
    }

    public boolean valorVar() {
        if (matchT2("NUM", token.lexema)) {
            //traduz("; ");
            return true;
        } else if (matchT2("NUM_Flutuante", token.lexema)) {
            //traduz("; ");
            return true;
        } else if (matchT2("Reservada_True", "true") || matchT2("Reservada_False", "false")) {
            //traduz("; ");
            return true;
        } else if (matchT2("Id", token.lexema)) {
            return true;
        } else if (matchT2("Texto", token.lexema)){
            return true;
        }

        return false;
    }


    // Gramatica Main
    public boolean startCode() {
        traduz("#include <stdio.h>\n");
        traduz("#include <stdlib.h>\n");
        traduz("\n");
        if (matchT2("Reservada_Script", "int main(){\n")) {
            return true;
        }

        error("startCode");
        return false;
    }


    // Gramatica expressao
    public boolean expressao() {
        if (matchT2("Id", token.lexema)
            && matchL2("=", " = ")
            && expMatematica()
        ){
            return true;
        }

        error("expressão");
        return false;
    }   

    // Gramatica expressao matematica
    public boolean expMatematica() {
        if (matchL2("(", "(") 
        && valorVar()
        && operador()
        && valorVar()
        && matchL2(")", ")")
        ){
            if(matchT2("Fim", ";\n")){
                return true;
            }
            return true;
        }
        else if (valorVar()){
            if(matchT2("Fim", ";\n")){
                return true;
            }
            return true;
        }

        error("expMatematica");
        return false;
    }

    // Gramatica condicao
    public boolean condicao() {

        if (matchT2("Id", token.lexema) && operador() && valorVar()){
            return true;
        }
        error("condição");
        return false;
    }


    // Gramatica operador
    public boolean operador() {
        if (matchL2(">", ">")
            || matchL2("<", "<")
            || matchL2("==", "==")
            || matchL2("=", "=")
            || matchL2("+", "+")
            || matchL2("-", "-")
            || matchL2("*", "*")
            || matchL2("/", "/")
            || matchL2("!=", "!=")
            || matchT2("MaiorIgual", ">=")
            || matchT2("MenorIgual", "<=")
            || matchT2("POTENCIA", "**")
            || matchT2("DivResto", "%")
        ){
            return true;
        }

        error("operador");
        return false;
    }


    // Fim de bloco
    public boolean fimBloco() {
        if (matchL2("}", "}\n")) {
            return true;
        }

        error("Fimbloco");
        return false;
    }


    // Lexema igual ao token
    public boolean matchL(String lexema) {
        if (token.lexema.equals(lexema)) {
            token = nextToken();
            return true;
        }
        return false;
    }


    // Tipo igual ao token
    public boolean matchT(String tipo) {
        if (token.tipo.equals(tipo)) {
            token = nextToken();
            return true;
        }
        return false;
    }


    // Lexema igual ao token - Com tradução
    public boolean matchL2(String lexema, String newcode) {
        if (token.lexema.equals(lexema)) {
            traduz(newcode);
            token = nextToken();
            return true;
        }
        return false;
    }


    // Tipo igual ao token - Com tradução
    public boolean matchT2(String tipo, String newcode) {
        if (token.tipo.equals(tipo)) {
            traduz(newcode);
            token = nextToken();
            return true;
        }
        return false;
    }


    // Traduzir
    public void traduz(String code) {
        System.out.print(code);
    }

}