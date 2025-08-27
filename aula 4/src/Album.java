import java.time.Duration;
import java.util.*;
public class Album {
    private static ArrayList<String> artistas;
    private Duration duracao;
    private int id;
    private String genero;
    private int ano;
    private ArrayList<Musica> musiquinhas;
    private static int contadorid = 1;
    private String nomealbum;
    private String artista;
    private ArrayList<String> musicasNomes;
    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Album> albums = new ArrayList<>();


    public Album(int id, String nomealbum, String genero, ArrayList<String> artistas, ArrayList<Musica> musiquinhas, int ano, Duration duracao) {
        this.id = id;
        this.nomealbum = nomealbum;
        this.genero = genero;
        this.ano = ano;
        this.duracao = duracao;
        this.musiquinhas = musiquinhas;
        this.artistas = artistas;
        this.musicasNomes = new ArrayList<>();

        for (Musica m : musiquinhas) {
            this.musicasNomes.add(m.getTitulo());
        }
    }

    static public void criarNovoAlbum() {
        boolean executandoArtistas = true;
        boolean executandoMusicas = true;
        System.out.print("Insira as informações do album:\n" +
                "Título: ");
        String inputtitulo = entrada.nextLine();
        System.out.print("Gênero: ");
        String inputgenero = entrada.nextLine();
        ArrayList<String> artistas = new ArrayList<>();
            while(executandoArtistas){
                System.out.print("Adicione os artistas (por ID) '0' para sair\n");
                while (executandoArtistas) {
                    System.out.print("Id do artista: ");
                    int inputartista = Integer.parseInt(entrada.nextLine());
                    if (inputartista == 0) {
                        executandoArtistas = false;
                    }
                    else {
                        for (Pessoa p : Pessoa.pessoas) {
                            if (p.getId() == inputartista) {
                                String adicionar = p.getNome();
                                artistas.add(adicionar);
                                System.out.println("Artista adicionado: " + p.getNome());
                            }
                        }
                    }
                }
            ArrayList<Musica> musiquinhas = new ArrayList<>();
            ArrayList<String> musicasnome = new ArrayList<>();
            System.out.print("Adicione as músicas (por ID) '0' para sair\n");
            while (executandoMusicas) {
                System.out.print("Id da música: ");
                int inputmusica = Integer.parseInt(entrada.nextLine());
                for (Musica m : Musica.musicas) {
                    if (inputmusica == 0) {
                        executandoMusicas = false;
                    }
                    if (m.getId() == inputmusica) { //precisa verificar se ja nao ta na lista artistas
                        musiquinhas.add(m);
                        musicasnome.add(m.getTitulo());
                        System.out.println("Música adicionada: " + m.getTitulo());
                    }
                }
            }
            System.out.print("Digite o ano de lançamento: ");
            int inputanodelancamento = Integer.parseInt(entrada.nextLine());

            Album a = new Album(contadorid, inputtitulo, inputgenero, artistas, musiquinhas, inputanodelancamento, calcularDuracao(musiquinhas));
            albums.add(a);
            contadorid++;

        }
    }

    static public Duration calcularDuracao(ArrayList<Musica> musiquinhas) {
        int minutos = 0;
        int segundos = 0;

        for (Musica m : musiquinhas) {
            minutos += m.getDuracao_minutos();
            segundos += m.getDuracao_segundos();
        }
        Duration duracao = Duration.ofMinutes(minutos).plusSeconds(segundos);
        return duracao;
    }

    public static String formatarDuracao(Duration d) {
        long minutos = d.toMinutes();
        long segundos = d.minusMinutes(minutos).getSeconds();
        return String.format("%02d minutos %02d segundos", minutos, segundos);
    }

    static public void consultarAlbum() {
        boolean executando = true;
        while (executando) {
            System.out.print("MENU\n" +
                    "1 - Pesquisar por Id\n" +
                    "2 - Pesquisar por título\n" +
                    "3 - Exibir a lista inteira\n" +
                    "4 - Sair\n" +
                    "Escolha uma opção: ");
            int opcao = Integer.parseInt(entrada.nextLine());
            if (opcao ==1){
                System.out.print("Digite o Id do album: ");
                int inputalbumid = Integer.parseInt(entrada.nextLine());
                for (Album a: Album.albums){
                    if (a.id == inputalbumid){
                        System.out.println("Album encontrado:\n" +
                                "Nome: " + a.nomealbum+
                                "\nGenero: "+a.genero+
                                "\nArtistas: ");
                            for (String artista: artistas) {
                                System.out.print(artista + " ");

                            }
                        System.out.print("\nMusicas:");
                            for (Musica m: a.musiquinhas) {
                                System.out.print(m.getTitulo() + " ");

                            }
                        System.out.print("\nAno de lançamento: " + a.ano);
                        System.out.print("\nDuração: " + formatarDuracao(a.duracao)+"\n");


                    }
                }
            }
            if (opcao == 2) {
                System.out.print("Digite o nome do album: ");
                String opcaonome = entrada.nextLine();
                for (Album a: albums) {
                    if (Objects.equals(a.nomealbum, opcaonome)) {
                        System.out.println("Album encontrado:\n" +
                                "Nome: " + a.nomealbum+
                                "\nGenero: "+a.genero+
                                "\nArtistas: ");
                        for (String artista: artistas) {
                            System.out.print(artista + " ");

                        }
                        System.out.print("\nMusicas:");
                        for (Musica m: a.musiquinhas) {
                            System.out.print(m.getTitulo() + " ");

                        }
                        System.out.print("\nAno de lançamento: " + a.ano);
                        System.out.print("\nDuração: " + formatarDuracao(a.duracao)+"\n");


                    }
                }
            }
            if (opcao == 3) {
                System.out.printf("%-10s %-10s %-5s\n", "Título", "Duração", "Id");
                for (Album a: albums) {
                    System.out.printf("%-10s %-10s %-5d\n", a.nomealbum, formatarDuracao(a.duracao), a.id);
                }
            }
            if (opcao==4){
                executando = false;
            }
        }
    }
}