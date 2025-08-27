import java.util.*;
import java.time.Duration;

public class Musica {
    private String titulo;
    private int duracao_minutos;
    private int duracao_segundos;
    private int id;
    private static int contadorid = 1;
    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Musica> musicas = new ArrayList<>();

    public Musica(String titulo, int duracao_minutos, int duracao_segundos, int id){
        this.id = id;
        this.titulo = titulo;
        this.duracao_minutos = duracao_minutos;
        this.duracao_segundos = duracao_segundos;
    }
    public int getDuracao_segundos(){
        return this.duracao_segundos;
    }

    public String getTitulo(){
        return this.titulo;
    }
    public int getId(){
        return this.id;
    }
    public int getDuracao_minutos(){
        return this.duracao_minutos;
    }

    static public void criarNovaMusica(){
        boolean existe = false;
        boolean executando = true;
        while (executando){
            System.out.print("Insira as informações da música:\n" +
                    "Título: ");
            String inputtitulo = entrada.next();
            for (Musica m: musicas){
                if (Objects.equals(inputtitulo,m.getTitulo())){
                    System.out.print("Esse título já foi registrado.");
                    existe = true;
                    executando = false;
                }
            }
            if (existe) continue;
            System.out.print("Duração (minutos e segundos): ");
            int inputminuto = entrada.nextInt();
            int  inputsegundo = entrada.nextInt();
            Musica m = new Musica(inputtitulo, inputminuto, inputsegundo, contadorid);
            musicas.add(m);
            contadorid++;
            executando = false;
        }


    }

    static public void consultarMusica(){
        boolean executando = true;
        while (executando) {
            System.out.print("MENU\n" +
                    "1 - Pesquisar por Id\n" +
                    "2 - Pesquisar por título\n" +
                    "3 - Exibir a lista inteira\n" +
                    "4 - Sair\n" +
                    "Escolha uma opção: ");
            int opcao = Integer.parseInt(entrada.nextLine());
            if (opcao == 1) {
                System.out.print("Digite o ID da música: ");
                int opcaoid = Integer.parseInt(entrada.nextLine());
                for (Musica m : musicas) {
                    if (m.id == opcaoid) {
                        System.out.print("Música encontrado:" +
                                "Id:" + m.id +
                                "Nome:" + m.titulo);
                    }
                }
            }
            if (opcao == 2) {
                System.out.print("Digite o nome da música: ");
                String opcaonome = entrada.nextLine();
                for (Musica m : musicas) {
                    if (Objects.equals(m.titulo, opcaonome)) {
                        System.out.print("Música encontrada:" +
                                "Id:" + m.id +
                                "Nome:" + m.titulo);
                    }
                }
            }
            if (opcao == 3) {
                System.out.printf("%-10s %-10s %-5s\n", "Título", "Duração", "Id");
                for (Musica m : musicas) {
                    System.out.printf("%-10s %-10d %-1d %-5d\n", m.titulo, m.duracao_minutos, m.duracao_segundos, m.id);
                }
            }
            if (opcao == 4) {
                executando = false;
            }
        }
    }

}
