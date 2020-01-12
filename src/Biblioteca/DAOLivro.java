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

public class DAOLivro {

//dados de arquivo de leitores
    private BufferedWriter saida;
    private List<Livro> livros = obterTodos();

    public List<Livro> obterTodos() {
        List<Livro> listaLivros = null;
        try (FileInputStream fis = new FileInputStream("./files/livros.fos")) {
            try (ObjectInputStream ois = new ObjectInputStream(fis)) {
                return listaLivros = (List<Livro>) ois.readObject();
            }
        } catch (ClassNotFoundException | IOException e) {
            return null;
        }
    }

    public void gravarTodos(List<Livro> livros) throws IOException {
        //Ler todos os leitors da lista leitores e gravar em arquivo
        criarArquivo();
        try (FileOutputStream fos = new FileOutputStream("./files/livros.fos")) {
            try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(livros);
            }
        }
    }

    public Livro getLivroByCodigo(int codigoLivro) {
        //Procurar o leitor idLeitor na lista leitores e retornar o objeto. //Retornar null se não encontrar
        if (!livros.isEmpty()) {
            for (Livro livro : livros) {
                if (livro.getCodigo() == codigoLivro) {
                    return livro;
                }
            }
        }

        return null;
    }

    public Livro getLivroByTitulo(String tituloLivro) {
        //Procurar o título do livro na lista leitores e retornar o objeto.
        for (Livro livro : livros) {
            if (livro.getTitulo().equals(tituloLivro)) {
                return livro;
            }
        }
        return null;
    }

    public boolean criarArquivo() {
        boolean flag = true;
        Path path = Paths.get("./files/livros.fos");
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
