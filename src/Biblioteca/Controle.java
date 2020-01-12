/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author
 */
public class Controle{

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

    public void criarEmprestimo(String nomeleitor, String tituloLivro) {
        Livro livroSelecionado = null;
        Leitor leitorSelecionado = null;
        int idEmprestimo = emprestimos.size() + 1;

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

        //Data de emprestimo
        dataEmprestimo = new Date();//data do sistema

        //Previsão de entrega
        Calendar c = Calendar.getInstance();
        c.setTime(dataEmprestimo);
        c.add(Calendar.DATE, +dias);//acrescentando os dias relativos ao Tipo de leitor à data
        dataPrevisaoDevolucao = c.getTime();

//      *************************** CRIAÇÃO DO EMPRESTIMO *********************
        Emprestimo emprestimo = new Emprestimo(idEmprestimo, dataEmprestimo, dataPrevisaoDevolucao, dataDevolucao, leitorSelecionado, livroSelecionado);
        emprestimos.add(emprestimo);
    }

    public void imprimirEmprestimoNome(String nomeLeitor) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLeitor().getNome().equals(nomeLeitor)) {
                System.out.println(emprestimo.toString());
            }
        }
    }

    public void imprimirTodos() {
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo.toString());
        }
    }

    public void devolverLivro(int idEmprestimo) {
        //tornar o livro não emprestado e alterar a data de devolução
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId() == idEmprestimo) {
                emprestimo.getLivro().setStatus(0);
            }
        }
    }

    public void cadastrarLeitor(String nomeLeitor, int tipoLeitor) {
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
        System.out.println("Leitor cadastrado com sucesso! ");
        System.out.println(leitores.indexOf(leitor));
    }

    public void cadastrarLivro(String titulo, String autor, int status, int numeroExemplar) {
        int codigoLivro = livros.size() + 1;
        Livro livro = new Livro(numeroExemplar, codigoLivro, status, titulo, autor);
        livros.add(livro);
        System.out.println("Cadastro realizado com sucesso");
    }

    public void excluirLivro(String nomeLivro) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equals(nomeLivro)) {
                livros.remove(livro);
                System.out.println("Livro excluído com sucesso!");
                break;
            }
        }
    }

    public void imprimirTodosLivros() {
        for (Leitor leitor : leitores) {
            System.out.println("ID" + leitor.getIdLeitor() + "Nome: " + leitor.getNome());
        }
    }
    
    public void excluirLeitor(int idLeitor) {
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
            daoEmprestimo.gravarTodos(emprestimos);
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
