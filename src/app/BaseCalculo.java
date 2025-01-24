package app;

public class BaseCalculo {
    private IRPF irpf;

    public BaseCalculo(IRPF irpf) {
        this.irpf = irpf;
    }

    public float calcular() {
        float valor = irpf.getTotalRendimentosTributaveis();
        valor -= irpf.getDeducao();
        valor -= irpf.getTotalPensaoAlimenticia();
        valor -= irpf.getTotalOutrasDeducoes();

        return Math.max(valor, 0f);
    }
}
