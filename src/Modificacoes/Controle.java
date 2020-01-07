/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modificacoes;

import Biblioteca.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class Controle extends Emprestimo {

    private List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
    private List<Leitor> leitores = new ArrayList<Leitor>();
    private List<Livro> livros = new ArrayList<Livro>();

//    private DAOLivro daoLivro;
    private DAOEmprestimo daoEmprestimo;
    private DAOLeitor daoLeitor;

    public Controle() {

//instancia os dados
//Deixar essa parte para o final:
//carregarTodos();
    }

    public void criarEmprestimo(String nomeLeitor, String tituloLivro) {
        //criar reserva e adicionar à lista empréstimos
        //Data de empréstimo deve ser a data do sistema
    }

    public void imprimirEmprestimoNome(String nomeLeitor) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLeitor().getNome().equals(nomeLeitor) ) {
                System.out.println(emprestimo.toString());
            }
        }
    }

    public void imprimirTodos() {
        for (Emprestimo emprestimo : emprestimos) {
            emprestimo.toString();
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

    public void cadastrarLeitor(String nomeLeitor, int id) {
        Leitor leitor = new Leitor(id, nomeLeitor);
        leitores.add(leitor);
    }

    public void cadastrarLivro(String titulo, String autor, int status,int numeroExemplar, int codigoLivro) {
        Livro livro = new Livro(numeroExemplar, codigoLivro, status, titulo, autor);
        livros.add(livro);
    }

    public void excluirLivro(int idLivro) {
        for (Livro livro : livros) {
            if (livro.getCodigo() == idLivro) {
                livro = null;
            }
        }
    }

    public void excluirLeitor(int idLeitor) {
        for (Leitor leitor : leitores) {
            if (leitor.getIdLeitor() == idLeitor) {
                leitor = null;
            }
        }
    }

    public void gravarTodos() {
//        daoLivro.gravarTodos(livros);
        daoLeitor.gravarTodos(leitores);
        daoEmprestimo.gravarTodos(emprestimos);
    }

    public void carregarTodos() {
        emprestimos = daoEmprestimo.obterTodos();
        leitores = daoLeitor.obterTodos();
//        livros = daoLivro.obterTodos();

    }
}