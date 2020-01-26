package Biblioteca;

import java.io.Serializable;

public class Livro implements Serializable{
    
// A classe esta pronta, TALVEZ precise gerar os metodos hasCode e equals
//Consultar hemerson
    
private int numeroExemplar;
private int codigo;
private int status;
private String titulo;
private String autor;

     public Livro(int numeroExemplar, int codigo, String titulo, String autor) {
        this.numeroExemplar = numeroExemplar;
        this.codigo = codigo;
        if (this.numeroExemplar > 0) {
            this.status = 1;
        } else {
            this.status = 0;
        }
        this.titulo = titulo;
        this.autor = autor;
    }
     
     public Livro(){
     }
    /**
     * @return the numeroExemplar
     */
    public int getNumeroExemplar() {
        return numeroExemplar;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param numeroExemplar the numeroExemplar to set
     */
    public void setNumeroExemplar(int numeroExemplar) {
        this.numeroExemplar = numeroExemplar;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

   

}



