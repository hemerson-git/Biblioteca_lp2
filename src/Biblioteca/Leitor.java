package Biblioteca;

// A classe esta pronta, TALVEZ precise gerar os metodos hasCode e equals

import java.io.Serializable;

//Consultar hemerson
public class Leitor implements Serializable{

    private String nome;
    private int idLeitor;
    private TipoLeitor tipoLeitor;

    public Leitor(String nome, int idLeitor, TipoLeitor tipoLeitor) {
        this.nome = nome;
        this.idLeitor = idLeitor;
        this.tipoLeitor = tipoLeitor;
    }
    
    public Leitor(){
    }
    
    public String getNome() {
        return nome;
    }

    public int getIdLeitor() {
        return idLeitor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdLeitor(int idLeitor) {
        this.idLeitor = idLeitor;
    }

    public TipoLeitor getTipoLeitor() {
        return tipoLeitor;
    }

    public void setTipoLeitor(TipoLeitor tipoLeitor) {
        this.tipoLeitor = tipoLeitor;
    }
}
