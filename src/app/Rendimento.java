package app;

public class Rendimento {
    public static final boolean TRIBUTAVEL = true;
    public static final boolean NAOTRIBUTAVEL = false;
    private String nomeRendimento;
    private boolean rendimentoTributavel;
    private float valorRendimento;


    public Rendimento() {
        this.nomeRendimento = "";
        this.rendimentoTributavel = false;
        this.valorRendimento = 0;
    }

    public Rendimento(String nome, boolean tributavel, float valor) {
        this.nomeRendimento = nome;
        this.rendimentoTributavel = tributavel;
        this.valorRendimento = valor;
    }
    

    public boolean isTributavel() {
        return this.rendimentoTributavel;
    }

    public String getNomeRendimento() {
        return this.nomeRendimento;
    }

    public boolean getRendimentoTributavel() {
        return this.rendimentoTributavel;
    }

    public float getValorRendimento() {
        return this.valorRendimento;
    }

    public void setNomeRendimento(String nome) {
        this.nomeRendimento = nome;
    }

    public void setRendimentoTributavel(boolean tributavel) {
        this.rendimentoTributavel = tributavel;
    }

    public void setValorRendimento(float valor) {
        this.valorRendimento = valor;
    }
}
