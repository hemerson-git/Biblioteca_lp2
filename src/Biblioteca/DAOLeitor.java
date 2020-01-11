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
import java.util.List;

public class DAOLeitor {
//dados de arquivo de leitores

    private BufferedWriter saida;

    public List<Leitor> obterTodos() {
        List<Leitor> listaLeitor = null;
        try (FileInputStream fis = new FileInputStream("./files/leitores.fos")) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                return listaLeitor = (List<Leitor>) ois.readObject();
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void gravarTodos(List<Leitor> leitores) throws IOException {
        //Ler todos os leitors da lista leitores e gravar em arquivo
        criarArquivo();
        try (FileOutputStream fos = new FileOutputStream("./files/leitores.fos")) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(leitores);
            }
        }
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
        Path path = Paths.get("./files/leitores.fos");
        if (!Files.exists(path)) {
            try {
                Path path2 = Paths.get("./files");
                Files.createDirectory(path2);
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
