package Biblioteca;

import java.io.BufferedWriter;
import java.io.FileInputStream;
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
    private List<Leitor> leitores = obterTodos();

    public List<Leitor> obterTodos() {
        List<Leitor> listaLeitor = null;
        try (FileInputStream fis = new FileInputStream("./files/leitores.fos")) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                return listaLeitor = (List<Leitor>) ois.readObject();
            }
        } catch (ClassNotFoundException | IOException e) {
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

    public Leitor getLeitorByID(int idLeitor) {
        //Procurar o leitor idLeitor na lista leitores e retornar o objeto. //Retornar null se não encontrar
        if (!leitores.isEmpty()) {
            for (Leitor leitor : leitores) {
                if (leitor.getIdLeitor() == idLeitor) {
                    return leitor;
                }
            }
        }

        return null;
    }

    public Leitor getLeitorByNome(String nomeLeitor) {
        //Procurar o leitor nomeLeitor na lista leitores e retornar o objeto.
        for (Leitor leitor : leitores) {
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
}
