package Biblioteca;

import java.util.Calendar;
import java.util.Date;

public class Emprestimo {

    private int id;
    private int tipoLeitor;
    private int dias;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Date dataPrevisaoDevolucao;
    private Leitor leitor;
    private Livro livro;

//construtor e métodos get e set
    public Emprestimo(int id, int tipoLeitor, Leitor leitor, Livro livro) {
        this.id = id;
        this.leitor = leitor;
        this.livro = livro;
        this.tipoLeitor = tipoLeitor;
        //Pegando os dias de acordo ao tipo de leitor
        switch (tipoLeitor) {
            case 1:
                dias = TipoLeitor.ESTUDANTE.getDias();
                break;
            case 2:
                dias = TipoLeitor.BOLSISTA.getDias();
                break;
            case 3:
                dias = TipoLeitor.PROFESSOR.getDias();
                break;
        }

        //Data de emprestimo
        this.dataEmprestimo = new Date();//data do sistema

        //Previsão de entrega
        Calendar c = Calendar.getInstance();
        c.setTime(this.dataEmprestimo);
        c.add(Calendar.DATE, +dias);//acrescentando os dias relativos ao Tipo de leitor à data
        this.dataPrevisaoDevolucao = c.getTime();
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
        String x = null;
        return x;
    }

}
