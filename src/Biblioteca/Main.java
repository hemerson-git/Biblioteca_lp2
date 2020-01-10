package Biblioteca;

//Andersdon Leite
//Hemerson Oliveira
//Giovane Fernandes
//Uallace Oliveira
import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main extends Controle {

    private static List<Livro> listaLivro = new ArrayList<Livro>();
    private static List<Leitor> listaLeitor = new ArrayList<Leitor>();
    private static List<Emprestimo> listaEmprestimo = new ArrayList<Emprestimo>();

    private static int cont = 1;

    public static void main(String args[]) throws IOException {
        Scanner teclado = new Scanner(System.in);

        Controle biblioteca = new Controle();

        int selecao = -1;

        while (selecao == -1) {
            System.out.println("0 - Sair");
            System.out.println("1 - Criar empréstimo");
            System.out.println("2 - Alterar um empréstimo: ");
            System.out.println("3 - Imprimir um empréstimo: ");
            System.out.println("4 - Imprimir todos os empréstimos: ");
            System.out.println("5 - Cadastrar livro: ");
            System.out.println("6 - Cadastrar leitor: ");
            System.out.println("7 - Excluir livro: ");
            System.out.println("8 - Excluir leitor: ");

            try {
                selecao = teclado.nextInt();

                switch (selecao) {
                    case 0:
                        exit(1);
                        biblioteca.cadastrarLivro("title", "Autor", 1, 12, 179856);
                    case 1:
                        //Cadastro de emprestimo
                        for (int i = 1; cont != 0; i++) {
                            System.out.print("Digite a data do " + i + "ºemprestimo: ");
                            //String data = teclado.next().;
                            System.out.print("Digite a data de devolução do emprestimo: ");
                            String dataDevolucao = teclado.next();
                            System.out.print("Digite a data prevista para devolução");
                            String dataPrevisaoDevolucao = teclado.next();
                            System.out.print("Digite o numero referente ao ID do leitor: ");
                            int leitor = teclado.nextInt();
                            System.out.print("Digite o numero referente ao ID do livro: ");
                            int livro = teclado.nextInt();

                            //listaEmprestimo.add(new Emprestimo(i, data, dataDevolucao, dataPrevisaoDevolucao, leitor, livro));
                            System.out.println("Deseja fazer outro emprestimo? 1-SIM 2-NÃO");
                            int continuar = teclado.nextInt();
                            if (continuar == 2) {
                                cont = 0;
                            }
                        }
                        System.out.println(selecao);
                        break;
                    case 2:
                        System.out.println(selecao);
                        break;
                    case 3:
                        System.out.println(selecao);
                        break;
                    case 4:
                        System.out.println(selecao);
                        break;
                    case 5:
                        //Cadastro de livro
                        for (int i = 1; cont != 0; i++) {
                            System.out.print("Digite o titulo do " + i + "ºlivro: ");
                            String titulo = teclado.next();
                            System.out.print("Digite o autor do livro " + titulo + ": ");
                            String autor = teclado.next();
                            System.out.print("Digite o número do exemplar: ");
                            int numeroExemplar = teclado.nextInt();
                            System.out.print("Digite o código do livro: ");
                            int codigo = teclado.nextInt();
                            System.out.print("Digite o status do livro: ");
                            int status = teclado.nextInt();

                            listaLivro.add(new Livro(numeroExemplar, codigo, status, titulo, autor));

                            System.out.println("Deseja cadastrar novamente? 1-SIM 2-NÃO");
                            int continuar = teclado.nextInt();
                            if (continuar == 2) {
                                cont = 0;
                            }
                        }

//                        System.out.println(selecao);
                        break;
                    case 6:
//                        //Cadastro do leitor
                        for (int i = 1; cont != 0; i++) {
                            System.out.print("Digite o nome do " + i + "ºleitor: ");
                            String leitor = teclado.next();

                            listaLeitor.add(new Leitor(i, leitor));

                            System.out.println("Deseja cadastrar novamente? 1-SIM 2-NÃO");
                            int continuar = teclado.nextInt();
                            if (continuar == 2) {
                                cont = 0;
                            }
                        }
                        break;
                    case 7:
                        System.out.println(selecao);
                        break;
                    case 8:
                        System.out.println(selecao);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "A opção escolhida é invalida!", "ERRO", JOptionPane.ERROR_MESSAGE);
                        selecao = -1;
                }
            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null, "Digite um número inteiro e válido", "ERRO", JOptionPane.ERROR_MESSAGE);
                selecao = -1;
                teclado.nextLine();
            }
        }
    }
}
