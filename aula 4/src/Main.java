import java.util.Scanner;

//Lista de problemas
//* Se colocar um número nos inputs de String, o código quebra na hora (e o contrário também) so ta faltando arrumar esse no musica e album.
//* Tem como adicionar o mesmo ID várias vezes no mesmo album, pro artista e pra música.

public class Main {
    static Scanner entrada = new Scanner(System.in);
    public static void menu(){
        boolean executando = true;
        while (executando){
            System.out.print("""
                    MENU
                    1 - Criar nova pessoa
                    2 - Consultar pessoas
                    3 - Criar nova música
                    4 - Consultar musicas
                    5 - Criar novo album
                    6 - Consultar albums
                    7 - Sair
                    Escolha uma opção:\s""");
            int opcao = entrada.nextInt();
            if (opcao == 1){
                Pessoa.criarNovaPessoa();
            }
            if (opcao == 2){
                Pessoa.consultarPessoa();
            }
            if (opcao == 3){
                Musica.criarNovaMusica();
            }
            if (opcao == 4){
                Musica.consultarMusica();
            }
            if (opcao == 5){
                Album.criarNovoAlbum();
            }
            if (opcao ==6){
                Album.consultarAlbum();
            }
            if (opcao == 7){
                executando = false;
            }
        }

    }
    public static void main(String[] args) {
        menu();
    }
}
