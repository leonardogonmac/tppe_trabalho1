package app;

public class DeducaoIntegral extends Deducao {
    public String nome;

    public DeducaoIntegral() {
        super();
        this.nome = "";
    }

    public DeducaoIntegral(float valor, String nome) {
        super(valor);
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
