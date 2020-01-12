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

    public static void main(String args[]) throws IOException {
        Scanner teclado = new Scanner(System.in);

        Controle biblioteca = new Controle();

        int selecao = 0;

        while (selecao > -1) {
            System.out.println("0 - Sair");
            System.out.println("1 - Criar empréstimo");
            System.out.println("2 - Alterar um empréstimo: ");
            System.out.println("3 - Imprimir um empréstimo: ");
            System.out.println("4 - Imprimir todos os empréstimos: ");
            System.out.println("5 - Cadastrar livro: ");
            System.out.println("6 - Cadastrar leitor: ");
            System.out.println("7 - Excluir livro: ");
            System.out.println("8 - Excluir leitor: ");
            System.out.println("8 - Imprimir todos os livros: ");

            try {
                selecao = teclado.nextInt();

                switch (selecao) {
                    case 0:
                        biblioteca.gravarTodos();
                        exit(0);
                        break;
                    case 1:
                        //Cadastro de emprestimo
                        int continuar3 = 1;
                        while (continuar3 == 1) {
                            System.out.print("Digite o nome do leitor: ");
                            String leitor = teclado.next();
                            System.out.print("Digite o título do livro: ");
                            String livro = teclado.next();

                            biblioteca.criarEmprestimo(leitor, livro);

                            System.out.println("Deseja fazer outro emprestimo? 1-SIM 2-NÃO");
                            continuar3 = teclado.nextInt();
                        }
                        break;

                    case 2:
                        System.out.println("Informe o ID do empréstimo: ");
                        int id = teclado.nextInt();
                        System.out.println("O que deseja fazer?");
                        System.out.println("1- Devolver Livro 2-Alterar dado: ");
                        int escolha = teclado.nextInt();

                        switch (escolha) {
                            case 1:
                                biblioteca.devolverLivro(id);
                                break;
                            case 2:
                                System.out.println("O que deseja alterar?");
                                System.out.print("1 - Alterar Livro 2 - Alterar Leitor");
                                int esc = teclado.nextInt();

                                switch (esc) {
                                    case 1:
                                        System.out.print("título do livro: ");
                                        String titulo = teclado.next();
                                        biblioteca.alterarEmprestimo(id, esc, titulo);
                                        break;
                                    case 2:
                                        System.out.print("Nome do leitor: ");
                                        String nome = teclado.next();
                                        biblioteca.alterarEmprestimo(id, esc, nome);
                                        break;
                                    default:
                                        System.out.println("Opção inválida!!");
                                        break;
                                }
                        }

                        System.out.println(selecao);
                        break;
                    case 3:
                        //imprime um empréstimo
                        System.out.print("Informe o nome do Leitor: ");
                        String nomeLeitor = teclado.next();
                        biblioteca.imprimirEmprestimoNome(nomeLeitor);
                        break;
                    case 4:
                        //imprime todos os empréstimos
                        biblioteca.imprimirTodos();
                        break;
                    case 5:
                        //Cadastro de livro
                        int continuar = 1;
                        while (continuar == 1) {
                            System.out.print("Digite o titulo do livro: ");
                            String titulo = teclado.next();
                            System.out.print("Digite o autor do livro " + titulo + ": ");
                            String autor = teclado.next();
                            System.out.print("Digite o número de exemplares: ");
                            int numeroExemplar = teclado.nextInt();
                            System.out.print("Código do livro: ");
                            int codigoLivro = teclado.nextInt();

                            biblioteca.cadastrarLivro(titulo, autor, codigoLivro, numeroExemplar);

                            System.out.println("Deseja cadastrar novamente? 1-SIM 2-NÃO");
                            continuar = teclado.nextInt();
                        }

//                        System.out.println(selecao);
                        break;
                    case 6:
//                        //Cadastro do leitor
                        int continuar2 = 1;
                        while (continuar2 == 1) {
                            System.out.print("Digite o nome do leitor: ");
                            String leitor = teclado.next();

                            System.out.print("Informe o tipo de Leitor 1-Estudante 2-Bolsista IC 3-Professor: ");
                            int tipo = teclado.nextInt();

                            biblioteca.cadastrarLeitor(leitor, tipo);

                            System.out.println("Deseja cadastrar novamente? 1-SIM 2-NÃO");
                            continuar2 = teclado.nextInt();
                        }
                        break;

                    case 7:
                        //Excluir livro
                        int continuarExcluirLivro = 1;
                        while (continuarExcluirLivro == 1) {
                            System.out.print("Informe o nome do Livro a ser excluído: ");
                            String nomeLivroExcluir = teclado.next();
                            biblioteca.excluirLivro(nomeLivroExcluir);

                            System.out.print("Deseja excluir outro livro? 1-SIM 2-NÃO");
                            continuarExcluirLivro = teclado.nextInt();
                        }
                        break;

                    case 8:
                        //excluir leitor
                        int continuarExcluirLeitor = 1;
                        while (continuarExcluirLeitor == 1) {
                            System.out.print("Informe o id leitor a ser excluído: ");
                            int idLeitorExcluir = teclado.nextInt();
                            biblioteca.excluirLeitor(idLeitorExcluir);

                            System.out.print("Deseja excluir outro livro? 1-SIM 2-NÃO");
                            continuarExcluirLeitor = teclado.nextInt();
                        }
                        break;
                    case 9:
                        biblioteca.imprimirTodosLivros();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "A opção escolhida é invalida!", "ERRO", JOptionPane.ERROR_MESSAGE);
                        selecao = 0;
                }

            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null, "Digite um número inteiro e válido", "ERRO", JOptionPane.ERROR_MESSAGE);
                selecao = 0;
                teclado.nextLine();
            }

        }
    }
}
