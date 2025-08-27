import java.util.*;

public class Pessoa {
    private String nome;
    private int idade;
    private int id;
    private static int contadorid = 1;
    static ArrayList<Pessoa> pessoas = new ArrayList<>();
    static Scanner entrada = new Scanner(System.in);

    public Pessoa(int id, String nome, int idade){
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public static void criarNovaPessoa(){
        boolean executando = true;
        boolean existe = false;
        while (executando){
            System.out.print("Insira os dados da nova pessoa\n" +
                    "Nome: ");
            String inputnomepessoa = entrada.nextLine();
            for(Pessoa p:pessoas){
                if (Objects.equals(inputnomepessoa,p.getNome())){
                    System.out.print("Esse nome ja foi registrado!\n");
                    existe = true;
                    executando = false;
                }
            }
            if (existe) continue;
            System.out.print("Idade: ");
            String inputidadepessoa = entrada.nextLine();
            if (inputidadepessoa.matches("\\d+")) {
                int idade = Integer.parseInt(inputidadepessoa);
                System.out.println("Idade válida: " + idade);
                Pessoa p = new Pessoa(contadorid,inputnomepessoa,idade);
                System.out.print("Pessoa criada com sucesso!\n");
                pessoas.add(p);
                contadorid++;
                executando = false;
            }
            else {
                System.out.println("Entrada inválida! Digite apenas números.");
                criarNovaPessoa();
            }

        }
    }

    public static void consultarPessoa(){
        boolean executando = true;
        while (executando){
            System.out.print("MENU\n" +
                    "1 - Pesquisar por Id\n" +
                    "2 - Pesquisar por nome\n" +
                    "3 - Exibir a lista inteira\n" +
                    "4 - Sair\n" +
                    "Escolha uma opção: ");
            int opcao = Integer.parseInt(entrada.nextLine());
            if (opcao==1){
                boolean encontrado = false;
                System.out.print("Digite o ID do Usuário: ");
                String opcaoid = entrada.nextLine();
                if (opcaoid.matches("\\d+")){
                    int opcaoidnovo = Integer.parseInt(opcaoid);
                    for (Pessoa p: pessoas){
                        if (p.id == opcaoidnovo){
                            System.out.print("Usuário encontrado:" +
                                    "Id:"+p.id +
                                    "Nome:"+p.nome);
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.print("Usuário não encontrado!\n");
                    }

                }
                else {
                    System.out.print("Digite um ID válido\n");
                    consultarPessoa(); //isso aqui ficou meio ruim, teria que ser uma função pra cada etapa do menu.
                }

            }
            if (opcao==2){
                boolean encontrado = false;
                System.out.print("Digite o nome do Usuário: ");
                String opcaonome = entrada.nextLine();
                for (Pessoa p: pessoas){
                    if (Objects.equals(p.nome, opcaonome)){
                        System.out.print("Usuário encontrado:" +
                                "Id:"+p.id +
                                "\nNome:"+p.nome);
                        encontrado = true;
                        break;
                    }

                }
                if (!encontrado){
                    System.out.print("Usuário não encontrado!\n");
                }
            }
            if (opcao ==3){
                System.out.printf("%-10s %-10s %-5s\n", "Nome", "Idade", "Id");
                    for (Pessoa p: pessoas){
                        System.out.printf("%-10s %-10d %-5d\n", p.nome, p.idade, p.id);
                }
            }
            if (opcao==4){
                executando = false;
            }
        }
    }
    public int getId(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public int getIdade(){
        return this.idade;
    }
}