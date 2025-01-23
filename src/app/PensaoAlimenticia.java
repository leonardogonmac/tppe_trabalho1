package app;

public class PensaoAlimenticia extends Deducao {
    public String nomeDependente;

    public PensaoAlimenticia() {
        super();
    }

    public PensaoAlimenticia(float valor, String nomeDependente) {
        super(valor);
        this.nomeDependente = nomeDependente;
    }
}
