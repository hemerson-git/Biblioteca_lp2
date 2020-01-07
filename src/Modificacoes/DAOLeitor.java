package Modificacoes;

import Biblioteca.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DAOLeitor extends Leitor{
//dados de arquivo de leitores

    DAOEmprestimo dao = new DAOEmprestimo();
    BufferedWriter saida;

    public DAOLeitor(String nome, int i) {
        super(i, nome);
    }

    public List<Leitor> obterTodos() {
        List<Leitor> listaLeitor = null;
        return listaLeitor;
    }

    public void gravarTodos(List<Leitor> leitores) {
//Ler todos os leitors da lista leitores e gravar em arquivo
    }

    public Leitor getLeitorByID(List<Leitor> leitores, int idLeitor) {
//Procurar o leitor idLeitor na lista leitores e retornar o objeto. //Retornar null se não encontrar

        if (!leitores.isEmpty()) {
            Leitor obj;
            for (int i = 0; i < leitores.size(); i++) {
                obj = (Leitor) leitores.get(i);
                if (obj.getIdLeitor() == idLeitor) {
                    return obj;
                }
            }
        }

        return null;
    }

    public Leitor getLeitorByNome(List<Leitor> leitors, String nomeLeitor) {
        //Procurar o leitor nomeLeitor na lista leitores e retornar o objeto.
        for (Leitor leitor : leitors) {
            if (leitor.getNome().equals(nomeLeitor)) {
                return leitor;
            }
        }
        return null;
    }

    
    public boolean criarArquivo() {
        
        boolean flag = true;
        
            //saida = new BufferedWriter(new FileWriter(dao.getNomeArquivo()));
        
            //System.err.println("Erro ao criar o arquivo!\n" + e);
            flag = false;
        
        return flag;
    }

    public boolean gravarLinhas(String[] linhas) {
        boolean flag = true;
        try {
            for (String linha : linhas) {
                if (linha != null) {
                    saida.write(linha + "\n");
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
