/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

import java.awt.Component;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author
 */
public class Controle {

    private DAOEmprestimo daoEmprestimo = new DAOEmprestimo();
    private DAOLeitor daoLeitor = new DAOLeitor();
    private DAOLivro daoLivro = new DAOLivro();
    private List<Leitor> leitores = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private List<Livro> livros = new ArrayList<>();

    public Controle() {
        //instancia os dados
        //Deixar essa parte para o final:
        carregarTodos();
    }

    private int getId() {
        int lastId = emprestimos.get(emprestimos.size() - 1).getId();
        return lastId + 1;
    }
    
    public short criarEmprestimo(String nomeleitor, String tituloLivro) throws IOException {
        Livro livroSelecionado = null;
        Leitor leitorSelecionado = null;
        int idEmprestimo = getId();

//      *************************** LIVRO *********************
        for (Livro livro : livros) {
            if (livro.getTitulo().equals(tituloLivro)) {
                livroSelecionado = livro;
                break;
            }
        }

//      *************************** LEITOR *********************
        for (Leitor leitor : leitores) {
            if (leitor.getNome().equals(nomeleitor)) {
                leitorSelecionado = leitor;
                break;
            }
        }

//      *************************** DATAS DO EMPRESTIMO *********************
        int dias = 0;
        Date dataEmprestimo;
        Date dataPrevisaoDevolucao;
        Date dataDevolucao = null;
        if (leitorSelecionado != null) {
            switch (leitorSelecionado.getTipoLeitor()) {
                case ESTUDANTE:
                    dias = TipoLeitor.ESTUDANTE.getDias();
                    break;
                case BOLSISTA:
                    dias = TipoLeitor.BOLSISTA.getDias();
                    break;
                case PROFESSOR:
                    dias = TipoLeitor.PROFESSOR.getDias();
                    break;
            }
        }
        //Data de emprestimo
        dataEmprestimo = new Date();//data do sistema

        //Previsão de entrega
        Calendar c = Calendar.getInstance();
        c.setTime(dataEmprestimo);
        c.add(Calendar.DATE, +dias);//acrescentando os dias relativos ao Tipo de leitor à data
        dataPrevisaoDevolucao = c.getTime();
//      *************************** CRIAÇÃO DO EMPRESTIMO *********************
        if (leitorSelecionado != null && livroSelecionado != null && livroSelecionado.getNumeroExemplar() > 0) {
            Emprestimo emprestimo = new Emprestimo(idEmprestimo, dataEmprestimo, dataDevolucao, dataPrevisaoDevolucao, leitorSelecionado, livroSelecionado);
            emprestimos.add(emprestimo);
            livroSelecionado.setNumeroExemplar(livroSelecionado.getNumeroExemplar() - 1);
            if (livroSelecionado.getNumeroExemplar() > 1) {
                livroSelecionado.setStatus(1);
            } else {
                livroSelecionado.setStatus(0);
            }
        } else if (livroSelecionado == null) {
            System.out.println("O Livro não está cadastrado no sistema");
            return 3;
        } else if (livroSelecionado.getNumeroExemplar() == 0) {
            System.out.println("Livro não disponível");
            return 1;
        } else {
            System.out.println("O leitor selecionado não está cadastrado");
            return 2;
        }
        daoEmprestimo.criarArquivo();
        daoEmprestimo.gravarTodos(emprestimos);
        System.out.println("Empréstimo realizado com sucesso! ");
        return 4;
    }

    public void imprimirEmprestimoNome(String nomeLeitor) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLeitor().getNome().equals(nomeLeitor)) {
                System.out.println("============Empréstimos===========");
                System.out.println(emprestimo.toString());
            }
        }
    }

    public void alterarEmprestimo(int idEmprestimo, int operacao, String valor) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId() == idEmprestimo) {
                switch (operacao) {
                    case 1:
                        for (Livro livro : livros) {
                            if (livro.getTitulo().equals(valor)) {
                                emprestimo.setLivro(livro);
                            }
                        }
                        break;
                    case 2:
                        for (Leitor leitor : leitores) {
                            if (leitor.getNome().equals(valor)) {
                                emprestimo.setLeitor(leitor);
                            }
                        }
                        break;
                    default:
                        System.out.println("Dados não cadastrados");
                        break;
                }
            }
        }
    }

    public short alterarEmprestimoGUI(int idEmprestimo, String novoLivro, String novoLeitor) {
        short resultadoOperacao = 0;
        if (novoLivro.trim().isEmpty() == false && novoLeitor.trim().isEmpty() == false) {
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getId() == idEmprestimo) {
                    for (Livro livro : livros) {
                        if (livro.getTitulo().equals(novoLivro)) {
                            emprestimo.setLivro(livro);
                            resultadoOperacao = 0;
                            break;
                        } else {
                            resultadoOperacao = 1;
                        }
                    }
                    
                    if(resultadoOperacao == 1){
                        return resultadoOperacao;
                    }

                    for (Leitor leitor : leitores) {
                        if (leitor.getNome().equals(novoLeitor)) {
                            emprestimo.setLeitor(leitor);
                            resultadoOperacao = 0;
                            break;
                        } else {
                            resultadoOperacao = 2;
                        }
                    }
                }
            }
        } else {
            resultadoOperacao = 3;
        }
        System.out.println(resultadoOperacao);
        return resultadoOperacao;
    }

    public void imprimirTodos() {
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(emprestimo.toString());
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++\n");
        }
    }

    public void imprimirTodosGUI(DefaultTableModel modelo) {
        for (Emprestimo emprestimo : emprestimos) {
            int idEmprestimo = emprestimo.getId();
            String leitor = emprestimo.getLeitor().getNome();
            int codLivro = emprestimo.getLivro().getCodigo();
            String titulo = emprestimo.getLivro().getTitulo();
            String autor = emprestimo.getLivro().getAutor();
            int status = emprestimo.getLivro().getStatus();
            Date dtEmprestimo = emprestimo.getDataEmprestimo();
            Date prEntrega = emprestimo.getDataPrevisaoDevolucao();
            Date dtEntrega = emprestimo.getDataDevolucao();
            String dataFormatadaDevolucao = null;

            //Formatção da data no padrão br
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatadaEmprestimo = dateFormat.format(dtEmprestimo);
            String dataFormatadaPrevisao = dateFormat.format(prEntrega);
            if (dtEntrega != null) {
                dataFormatadaDevolucao = dateFormat.format(dtEntrega);
            }

            modelo.addRow(new Object[]{idEmprestimo, leitor, codLivro, titulo, autor,
                status, dataFormatadaEmprestimo, dataFormatadaPrevisao, dataFormatadaDevolucao});
        }
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public boolean devolverLivro(int idEmprestimo) {
        //tornar o livro não emprestado e alterar a data de devolução
        for (Emprestimo emprestimo : emprestimos) {
            //Vefica se exite um livro com o ID passado e se esse livro ainda não foi devolvido 
            //caso seja atendidas, aumenta o número de exemplares disponíveis, e configura a data de entrega para
            //a data atual do sistema e retorna true.

            if (emprestimo.getId() == idEmprestimo && emprestimo.getDataDevolucao() == null) {
                emprestimo.getLivro().setNumeroExemplar(emprestimo.getLivro().getNumeroExemplar() + 1);
                Date data = new Date();
                emprestimo.setDataDevolucao(data);
                System.out.println("Livro devolvido");
                return true;
            }
        }

        return false;
    }

    public void excluirEmprestimo(int idEmprestimo) {
        //tornar o livro não emprestado e alterar a data de devolução
        for (Emprestimo emprestimo : emprestimos) {
            //Vefica se exite um livro com o ID passado e se esse livro ainda não foi devolvido
            //caso seja atendidas, aumenta o número de exemplares disponíveis, e configura a data de entrega para
            //a data atual do sistema.

            if (emprestimo.getId() == idEmprestimo) {
                emprestimos.remove(emprestimo);
                System.out.println("Empréstimo removido do sistema");
                break;
            }
        }
    }

    public void cadastrarLeitor(String nomeLeitor, int tipoLeitor) throws IOException {
        TipoLeitor tipo = null;
        switch (tipoLeitor) {
            case 1:
                tipo = TipoLeitor.ESTUDANTE;
                break;
            case 2:
                tipo = TipoLeitor.BOLSISTA;
                break;
            case 3:
                tipo = TipoLeitor.PROFESSOR;
                break;
            default:
                tipo = TipoLeitor.ESTUDANTE;
        }
        int id = leitores.size() + 1;

        Leitor leitor = new Leitor(nomeLeitor, id, tipo);
        leitores.add(leitor);
        System.out.println(leitores.indexOf(leitor));
        daoLeitor.criarArquivo();
        daoLeitor.gravarTodos(leitores);

        System.out.println("Leitor cadastrado com sucesso! ");
    }

    public boolean cadastrarEmprestimoGUI(Component cmpnt, String nomeEmp, String tituloEmp) {
        if (nomeEmp.trim().isEmpty() == false && nomeEmp != null && tituloEmp.trim().isEmpty() == false && tituloEmp != null) {
            try {
                int retornoCadastro = criarEmprestimo(nomeEmp, tituloEmp);
                switch (retornoCadastro) {
                    case 1:
                        JOptionPane.showMessageDialog(cmpnt, "Livro indisponível no momento", "Erro", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(cmpnt, "Leitor não cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(cmpnt, "Livro não cadastrado no sistema", "Erro", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 4:
                        gravarTodos();
                        return true;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(cmpnt, "Erro ao realizar cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(cmpnt, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean cadastrarLivro(String titulo, String autor, int codigoLivro, int numeroExemplar) throws IOException {
        boolean resultado = false;
        for (Livro livro : livros) {
            if (livro.getCodigo() != codigoLivro) {
                resultado = true;
            } else {
                resultado = false;
                break;
            }
        }
        if (resultado == true) {
            Livro novoLivro = new Livro(numeroExemplar, codigoLivro, titulo, autor);
            livros.add(novoLivro);
            daoLivro.criarArquivo();
            daoLivro.gravarTodos(livros);
            System.out.println("Cadastro realizado com sucesso");
        }
        return resultado;
    }

    public void excluirLivro(String nomeLivro) {
        //Verifica se o livro passado exite nos livros cadastrados
        //caso sim, esse é removido.
        for (Livro livro : livros) {
            if (livro.getTitulo().equals(nomeLivro)) {
                livros.remove(livro);
                System.out.println("Livro excluído com sucesso!");
                break;
            }
        }
    }

    public void excluirLeitor(int idLeitor) {
        //Verifica se o leitor passado exite nos leitores cadastrados
        //caso sim, esse é removido.
        for (Leitor leitor : leitores) {
            if (leitor.getIdLeitor() == idLeitor) {
                leitores.remove(leitor);
                System.out.println("Leitor excluído com sucesso!");
                break;
            }
        }
    }

    public void gravarTodos() {
        if (leitores.size() > 0) {
            try {
                daoLeitor.gravarTodos(leitores);
            } catch (IOException e) {
                System.out.println("Impossivel gravar leitores");
            }
        }

        if (emprestimos.size() > 0) {
            try {
                daoEmprestimo.gravarTodos(emprestimos);

            } catch (IOException e) {
                System.out.println("Impossível gravar emprestimos");
            }
        }

        if (livros.size() > 0) {
            try {
                daoLivro.gravarTodos(livros);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Impossível gravar livros");
            }
        }
    }

    public void imprimirTodosLivros() {
        for (Livro livro : livros) {
            System.out.println("----------Livros-------------");
            System.out.println(livro.getTitulo());
        }
    }

    public List<Livro> imprimirTodosLivrosInterface() {
        return livros;
    }

    public void imprimirTodosLeitores() {

        for (Leitor leitor : leitores) {
            System.out.println("**********Leitores***********");
            System.out.println(leitor.getNome());

        }
    }

    public List<Leitor> imprimirTodosLeitorInterface() {
        return leitores;
    }

    public void carregarTodos() {
        if (daoEmprestimo.obterTodos() != null) {
            emprestimos = daoEmprestimo.obterTodos();
        }

        if (daoLivro.obterTodos() != null) {
            livros = daoLivro.obterTodos();
        }

        if (daoLeitor.obterTodos() != null) {
            leitores = daoLeitor.obterTodos();
        }
    }
}
