from tkinter import messagebox, filedialog
import tkinter
import customtkinter as ctk
import subprocess
import os
import webbrowser


def compilar():
    texto = codeBOX.get("1.0", tkinter.END)#Le oq esta escrito na caixa de texto

    #Escreve oq foi lido para o arquivo txt para o compilador funcionar
    with open('arquivos\\codigo.txt','w') as arquivo:
        arquivo.write(f"{texto}")

    #Executa o jar do Coompilador
    result = subprocess.run(["java", "-jar", "--enable-preview" ,"VALE+.jar"], capture_output=True, text=True)
    
    #print(result)

    if ( "regra" in result.stdout.lower() or result.returncode == 1):
        popUpBox("Resultado" , f"Falha na Compilação: {result.stdout}")
    else:
        popUpBox("Resultado" , "Compilação Concluida!")

    resultSplit = result.stdout.split("COMPILADO")
    codeFinal = resultSplit[0]   
    
    #Cria o arquivo do tipo C
    arquivoC = open("arquivos\\Main.c", "w")
    #Escreve no arquivo C o output do .jar
    arquivoC.write(codeFinal)
    arquivoC.close


def gerarCodigo():
    #Abre o arquivo C em txt para mostra o codigo traduzido
    try:
        os.startfile("arquivos\\Main.c")
    except:
        print("Erro na leitura arquivo C")


def abrirTokens():
    #Abre o txt com os tokens gerado
    try:
        os.startfile("arquivos\\output.txt")
    except:
        print("Erro na leitura arquivo Tokens")


def helpBtn():
    
    url = "https://github.com/EnzoSanchez23/Linguagem-VALE/wiki"    
    webbrowser.open(url)
    
    #Mostra um popup sobre a documentacao
    #messagebox.showinfo("Documentação", "Documentação em breve!")


def runButton():   
    # Compila o arquivo C
    compile_result = subprocess.run(["gcc", "arquivos\\Main.c", "-o", "output"], capture_output=True, text=True)

    # Verifica se a compilação foi bem-sucedida
    if compile_result.returncode == 0:
        print("Compilação bem-sucedida!")
        # Executa o programa compilado
        execute_result = subprocess.run(["./output"], capture_output=True, text=True)
        #terminalLabel.configure(text=execute_result.stdout)
        print(execute_result.stdout)
    else:
        print("Erro durante a compilação:")
        print(compile_result.stderr)


def popUpBox(title, msg):
    
    messagebox.showinfo(title,msg)


def centralizar_janela(janela, largura, altura):
    # Obtém as dimensões da tela
    largura_tela = janela.winfo_screenwidth()
    altura_tela = janela.winfo_screenheight()

    # Calcula as coordenadas x e y para centralizar a janela
    x = (largura_tela - largura) // 2
    y = (altura_tela - altura) // 2

    # Define as coordenadas da janela
    janela.geometry(f"{largura}x{altura}+{x}+{y}")
    
# Configurações do customtkinter
app = ctk.CTk()
app.title("IDE")
width_app = 720
heigth_app = 500
centralizar_janela(app, width_app, heigth_app)

'''
terminal = ctk.CTkToplevel(app)
terminal.title("Terminal")
width_terminal = 400
heigth_terminal = 200
centralizar_janela(terminal, width_terminal, heigth_terminal)
terminalLabel = ctk.CTkLabel(terminal, text="")
terminalLabel.grid(row=0,column=0, padx=10)
'''

#Largura X Altura
#app.geometry("580x500")

# Frame superior para os botões
frame = ctk.CTkFrame(app)
frame.grid(row=0, column=0, padx=10, pady=10, sticky="ew")

# Botões personalizados
compile_button = ctk.CTkButton(frame, text="Compilar", command=compilar,
                               fg_color="#1e1e2e",  # Cor de fundo do botão
                               hover_color="#343442",  # Cor do botão ao passar o mouse
                               text_color="white",  # Cor do texto
                               width=120, height=40, corner_radius=10)  # Tamanho e cantos arredondados
compile_button.grid(row=0, column=0, padx=10, pady=10)

showCode_button = ctk.CTkButton(frame, text="Código em C", command=gerarCodigo,
                                fg_color="#1e1e2e",
                                hover_color="#343442",
                                text_color="white",
                                width=120, height=40, corner_radius=10)
showCode_button.grid(row=0, column=1, padx=10, pady=10)

tokens_button = ctk.CTkButton(frame, text="Tokens", command=abrirTokens,
                               fg_color="#1e1e2e",
                               hover_color="#343442",
                               text_color="white",
                               width=120, height=40, corner_radius=10)
tokens_button.grid(row=0, column=2, padx=10, pady=10)

help_Button = ctk.CTkButton(frame, text="Help", command=helpBtn,
                            fg_color="#1e1e2e",
                            hover_color="#343442",
                            text_color="white",
                            width=120, height=40, corner_radius=10)
help_Button.grid(row=0, column=3, padx=10, pady=10)

run_Button = ctk.CTkButton(frame, text="Run", command=runButton,
                            fg_color="#1e1e2e",
                            hover_color="#343442",
                            text_color="white",
                            width=120, height=40, corner_radius=10)
run_Button.grid(row=0, column=4, padx=10, pady=10)

# Caixa de texto para o código
codeBOX = ctk.CTkTextbox(app)
codeBOX.grid(row=1, column=0, padx=10, pady=10, sticky="nsew")

# Configurar as colunas e linhas para redimensionamento
app.grid_columnconfigure(0, weight=1)
app.grid_rowconfigure(1, weight=1)

# Executa a interface
app.mainloop()
