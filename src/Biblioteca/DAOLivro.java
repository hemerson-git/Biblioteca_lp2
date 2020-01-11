package Biblioteca;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Hemerson
 *          Giovane
 *          Anderson
 *          Ualace
 */
public class DAOLivro {

    private BufferedWriter saida;

    public List<Livro> obterTodos() {
        //Ler todos os leitores do arquivo de leitores e retornar uma lista de leitores
        List<Livro> listaLivro = null;
        return listaLivro;
    }

    public void gravarTodos(List<Livro> livro) {

//Ler todos os livro da lista leitores e gravar em arquivo
    }

    public Emprestimo getEmprestimoByID(List<Emprestimo> emprestimo, int idEmprestimo) {
//Procurar o leitor idLeitor na lista leitores e retornar o objeto. //Retornar null se não encontrar

        if (!emprestimo.isEmpty()) {
            Emprestimo obj;
            for (int i = 0; i < emprestimo.size(); i++) {
                obj = (Emprestimo) emprestimo.get(i);
                if (obj.getId() == idEmprestimo) {
                    return obj;
                }
            }
        }

        return null;
    }

    public Emprestimo getEmprestimoByNome(List<Emprestimo> emprestimo, String nomeLeitor) {
//Procurar o leitor nomeLeitor na lista leitores e retornar o objeto. //Retornar null se não encontrar
        Emprestimo emprestimoNome = null;
        return emprestimoNome;
    }

    public boolean criarArquivo() {
        boolean flag = true;

        // saida = new BufferedWriter(new FileWriter(dao.getNomeArquivo()));
        return flag;
    }

    public boolean gravarLinhas(String[] linhas) {
        boolean flag = true;
        try {
            for (int i = 0; i < linhas.length; i++) {
                if (linhas[i] != null) {
                    saida.write(linhas[i] + "\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erro, arquivo não encontrado!\n" + e);
            flag = false;
        } catch (IOException e) {
            System.err.println("Erro, ao gravar linha!\n" + e);
            flag = false;
        }
        return flag;
    }

    public boolean gravarLinha(String linha) {
        boolean flag = true;

        try {
            if (linha != null) {
                saida.write(linha + "\n");
            }
        } catch (IOException e) {
            flag = false;
        }
        return flag;
    }

    public boolean fecharArquivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
