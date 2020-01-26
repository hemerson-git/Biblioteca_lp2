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
            System.out.println("                                                  ");
            System.out.println("                                                  ");
            System.out.println("__________________________________________________");
            System.out.println("| 0  - Sair                                       |");
            System.out.println("| 1  - Cadastrar livro:                           |");
            System.out.println("| 2  - Cadastrar leitor:                          |");
            System.out.println("| 3  - Criar empréstimo:                          |");
            System.out.println("| 4  - Alterar um empréstimo:                     |");
            System.out.println("| 5  - Excluir livro:                             |");
            System.out.println("| 6  - Excluir leitor:                            |");
            System.out.println("| 7  - Imprimir todos os livros:                  |");
            System.out.println("| 8  - Imprimir todos os Leitores:                |");
            System.out.println("| 9  - Imprimir um empréstimo:                    |");
            System.out.println("| 10 - Imprimir todos os empréstimos:             |");
            System.out.println("|_________________________________________________|");
            System.out.println("                                                   ");
            System.out.println("Escolha um número de 0 a 10 de acordo com o menu!  ");
            

            try {
                selecao = teclado.nextInt();

                switch (selecao) {
                    case 0:
                        biblioteca.gravarTodos();
                        exit(0);
                        break;
                        
                        case 1:
                        //Cadastro de livro  5
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

                            System.out.println("Deseja cadastrar novamente? 1-SIM 2-NÃO: ");
                            continuar = teclado.nextInt();
                        }


                        break;
                            
                        case 2:
//                        //Cadastro do leitor  6
                        int continuar2 = 1;
                        while (continuar2 == 1) {
                            System.out.print("Digite o nome do leitor: ");
                            String leitor = teclado.next();
                            System.out.print("Informe o tipo de Leitor 1-Estudante 2-Bolsista IC 3-Professor: ");
                            int tipo = teclado.nextInt();

                            biblioteca.cadastrarLeitor(leitor, tipo);

                            System.out.println("Deseja cadastrar novamente? 1-SIM 2-NÃO: ");
                            continuar2 = teclado.nextInt();
                        }
                        break;
                        
                            
                        case 3:
                            //Cadastro de emprestimo  1
                            int continuar3 = 1;
                            while (continuar3 == 1) {
                                System.out.print("Digite o nome do leitor: ");
                                String leitor = teclado.next();
                                leitor.replace (" ", "_");
                                System.out.print("Digite o título do livro: ");
                                String livro = teclado.next();
                                livro.replace (" ", "_");
                                biblioteca.criarEmprestimo(leitor, livro);

                                System.out.println("Deseja fazer outro emprestimo? 1-SIM 2-NÃO: ");
                                continuar3 = teclado.nextInt();
                            }
                            break;        

                        case 4:
                            //Alterar um emprestimo  2
                            System.out.println("Informe o ID do empréstimo: ");
                            int id = teclado.nextInt();
                            System.out.println("O que deseja fazer? ");
                            System.out.println("1- Devolver Livro 2-Alterar dado: ");
                            int escolha = teclado.nextInt();

                            switch (escolha) {
                                case 1:
                                    biblioteca.devolverLivro(id);
                                    break;
                                case 2:
                                    System.out.println("O que deseja alterar? ");
                                    System.out.print("1- Alterar Livro 2- Alterar Leitor: ");
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

                        case 5:
                            //Excluir livro   7
                            int continuarExcluirLivro = 1;
                            while (continuarExcluirLivro == 1) {
                                System.out.print("Informe o nome do Livro a ser excluído: ");
                                String nomeLivroExcluir = teclado.next();
                                biblioteca.excluirLivro(nomeLivroExcluir);

                                System.out.print("Deseja excluir outro livro? 1-SIM 2-NÃO: ");
                                continuarExcluirLivro = teclado.nextInt();
                            }
                            break;

                        case 6:
                            //excluir leitor  8
                            int continuarExcluirLeitor = 1;
                            while (continuarExcluirLeitor == 1) {
                                System.out.print("Informe o id leitor a ser excluído: ");
                                int idLeitorExcluir = teclado.nextInt();
                                biblioteca.excluirLeitor(idLeitorExcluir);

                                System.out.print("Deseja excluir outro livro? 1-SIM 2-NÃO: ");
                                continuarExcluirLeitor = teclado.nextInt();
                            }
                            break;

                        case 7:
                            //Imprimi todos os livros  9
                            biblioteca.imprimirTodosLivros();
                            break; 

                        case 8:
                            //Imprimi todos os leitores  10
                            biblioteca.imprimirTodosLeitores();
                            break;    

                        case 9:
                            //imprime um empréstimo 3
                            System.out.print("Informe o nome do Leitor: ");
                            String nomeLeitor = teclado.next();
                            biblioteca.imprimirEmprestimoNome(nomeLeitor);
                            break;


                        case 10:
                            //imprime todos os empréstimos  4
                            biblioteca.imprimirTodos();
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
