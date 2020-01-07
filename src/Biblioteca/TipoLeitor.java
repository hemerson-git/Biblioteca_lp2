package Biblioteca;
public enum TipoLeitor {
    ESTUDANTE("Estudante", 8),
    BOLSISTA("Bolsista IC", 12),
    PROFESSOR("Professor", 15);
    
    private String tipoLeitor;
    private int dias;

    private TipoLeitor(String tipoLeitor, int dias) {
        this.tipoLeitor = tipoLeitor;
        this.dias = dias;
    }

    public String getTipoLeitor() {
        return tipoLeitor;
    }

    public void setTipoLeitor(String tipoLeitor) {
        this.tipoLeitor = tipoLeitor;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
    
    @Override
    public String toString() {
     return "Tipo: " + getTipoLeitor() + "\n" + "Dias: " + getDias();
    }
}
