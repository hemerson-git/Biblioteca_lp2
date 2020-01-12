/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

/**
 *
 * @author giovane
 */
public class DAOEmprestimo {

    BufferedWriter saida;
    private List<Emprestimo> emprestimos = obterTodos();

    public List<Emprestimo> obterTodos() {
        //Retorna uma lista com todos os emprestimos    
        List<Emprestimo> listaEmprestimos = null;
        try (FileInputStream fis = new FileInputStream("./files/emprestimos.fos")) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                return listaEmprestimos = (List<Emprestimo>) ois.readObject();
            }
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }

    public void gravarTodos(List<Emprestimo> emprestimo) throws IOException {
        //Grava a lista de emprestimos em uma arquivo
        criarArquivo();
        try (FileOutputStream fos = new FileOutputStream("./files/emprestimos.fos")) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(emprestimo);
            }
        }
    }

    public Emprestimo getEmprestimoByID(int idEmprestimo) {
        //Procurar o leitor idLeitor na lista leitores e retornar o objeto. //Retornar null se não encontrar
        if (!emprestimos.isEmpty()) {
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getId() == idEmprestimo) {
                    return emprestimo;
                }
            }
        }

        return null;
    }

    public Emprestimo getEmprestimoByNome(String nomeLeitor) {
        //Procurar o leitor nomeLeitor na lista leitores e retornar o objeto. //Retornar null se não encontrar
        Emprestimo emprestimoNome = null;
        if (!emprestimos.isEmpty()) {
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getLeitor().getNome().equals(nomeLeitor)) {
                    return emprestimo;
                }
            }
        }

        System.out.println("Empréstimo não encontrado");
        return emprestimoNome;
    }

    public boolean criarArquivo() {
        boolean flag = true;
        Path path = Paths.get("./files/emprestimos.fos");
        if (!Files.exists(path)) {
            try {
                Path path2 = Paths.get("./files");
                if (!Files.exists(path2)) {
                    Files.createDirectory(path2);
                }
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Impossível gravar o arquivo");
                flag = false;
            }
        }
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
