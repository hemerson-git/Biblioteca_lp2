package Biblioteca;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Emprestimo implements Serializable{

    private int id;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Date dataPrevisaoDevolucao;
    private Leitor leitor;
    private Livro livro;

//construtor e métodos get e set

    public Emprestimo(int id, Date dataEmprestimo, Date dataDevolucao, Date dataPrevisaoDevolucao, Leitor leitor, Livro livro) {
        this.id = id;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.dataPrevisaoDevolucao = dataPrevisaoDevolucao;
        this.leitor = leitor;
        this.livro = livro;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the dataEmprestimo
     */
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    /**
     * @return the dataDevolucao
     */
    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    /**
     * @return the dataPrevisaoDevolucao
     */
    public Date getDataPrevisaoDevolucao() {
        return dataPrevisaoDevolucao;
    }

    /**
     * @return the leitor
     */
    public Leitor getLeitor() {
        return leitor;
    }

    /**
     * @return the livro
     */
    public Livro getLivro() {
        return livro;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param dataEmprestimo the dataEmprestimo to set
     */
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    /**
     * @param dataDevolucao the dataDevolucao to set
     */
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * @param dataPrevisaoDevolucao the dataPrevisaoDevolucao to set
     */
    public void setDataPrevisaoDevolucao(Date dataPrevisaoDevolucao) {
        this.dataPrevisaoDevolucao = dataPrevisaoDevolucao;
    }

    /**
     * @param leitor the leitor to set
     */
    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    /**
     * @param livro the livro to set
     */
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String toString() {
//chamado quando solicitar a impressão de empréstimos. Mostrar todos os dados.
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatadaEmprestimo = dateFormat.format(this.dataEmprestimo);
        String dataFormatadaPrevisao = dateFormat.format(this.dataPrevisaoDevolucao);
        String status = (this.livro.getStatus() == 1) ? "Disponível": "Esgotado";
        String dados = "Nome do Leitor: " + this.leitor.getNome() + "\n" +
                      "Nome do Livro: " + this.livro.getTitulo()+ "\n" +
                       "Autor do Livro: " + this.livro.getAutor()+ "\n" +
                       "Status do Livro: " + status + "\n" +
                       "Código do Livro: " + this.livro.getCodigo() + "\n" +
                       "Número de exemplares disponíveis: " + this.livro.getNumeroExemplar()+ "\n" +
                        "Data do Empréstimo: " + dataFormatadaEmprestimo +"\n" +
                        "Previsão de entrega: " + dataFormatadaPrevisao +"\n" +
                        "Id do Emprestimo: " + this.id +"\n";
        if (this.dataDevolucao != null) {
            String dataFormatadaDevolucao = dateFormat.format(this.dataDevolucao);
            dados += "Data de devolução: " + dataFormatadaDevolucao + "\n";
        }
        return dados;
        
    }

}
