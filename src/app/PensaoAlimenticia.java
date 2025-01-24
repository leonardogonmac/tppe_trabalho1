package app;

public class PensaoAlimenticia extends Deducao {
    private String nomeDependente;

    public PensaoAlimenticia() {
        super();
    }

    public PensaoAlimenticia(float valor, String nomeDependente) {
        super(valor);
        this.nomeDependente = nomeDependente;
    }

    public String getNomeDependente() {
        return this.nomeDependente;
    }

    public void setNomeDependente(String nomeDependente) {
        this.nomeDependente = nomeDependente;
    }
}
