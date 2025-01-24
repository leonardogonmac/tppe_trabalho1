package app;

public class BaseCalculo {
    private IRPF irpf;

    public BaseCalculo(IRPF irpf) {
        this.irpf = irpf;
    }

    public float calcular() {
        float valor = irpf.getTotalRendimentosTributaveis();
        valor -= irpf.getTotalContribuicoesPrevidenciarias();
        valor -= irpf.getTotalPensaoAlimenticia();
        valor -= irpf.getTotalOutrasDeducoes();
        valor -= irpf.getNumDependentes() * 189.59f;

        return Math.max(valor, 0f);
    }
}
