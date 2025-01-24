package app;

import java.util.ArrayList;
import java.util.List;


public class IRPF {
    private List<Rendimento> rendimentos;
    private List<Dependente> dependentes;
    private List<ContribuicaoPrevidencia> contribuicoesPrevidenciarias;
    private List<PensaoAlimenticia> pensoesAlimenticias;
    private List<DeducaoIntegral> deducoes;

    public IRPF() {
        rendimentos = new ArrayList<>();
        dependentes = new ArrayList<>();
        contribuicoesPrevidenciarias = new ArrayList<>();
        pensoesAlimenticias = new ArrayList<>();
        deducoes = new ArrayList<>();
    }

    public void criarRendimento(String nome, boolean tributavel, float valor) {
        rendimentos.add(new Rendimento(nome, tributavel, valor));
    }

    public int getNumRendimentos() {
        return rendimentos.size();
    }

    public float getTotalRendimentos() {
        float totalRendimentos = 0;
        for (Rendimento rendimento : rendimentos) {
            totalRendimentos += rendimento.getValorRendimento();
        }
        return totalRendimentos;
    }

    public float getTotalRendimentosTributaveis() {
        float total = 0;
        for (Rendimento rendimento : rendimentos) {
            if (rendimento.isTributavel()) {
                total += rendimento.getValorRendimento();
            }
        }
        return total;
    }

    public void cadastrarDependente(String nome, String parentesco) {
        dependentes.add(new Dependente(nome, parentesco));
    }

    public int getNumDependentes() {
        return dependentes.size();
    }

    public float getDeducao() {
        float total = 0;
        for (Dependente d : dependentes) {
            total += d.getValor();
        }
        total += getTotalContribuicoesPrevidenciarias();
        return total;
    }

    public void cadastrarContribuicaoPrevidenciaria(float contribuicao) {
        contribuicoesPrevidenciarias.add(new ContribuicaoPrevidencia(contribuicao));
    }

    public int getNumContribuicoesPrevidenciarias() {
        return contribuicoesPrevidenciarias.size();
    }

    public float getTotalContribuicoesPrevidenciarias() {
        float total = 0;
        for (ContribuicaoPrevidencia contribuicao : contribuicoesPrevidenciarias) {
            total += contribuicao.getValor();
        }
        return total;
    }

    public String getDependente(String nome) {
        for (Dependente d : dependentes) {
            if (d.getNomeDependente().contains(nome))
                return d.getNomeDependente();
        }
        return null;
    }

    public String getParentesco(String dependente) {
        for (Dependente d : dependentes) {
            if (d.getNomeDependente().equalsIgnoreCase(dependente))
                return d.getParentescoDependente();
        }
        return null;
    }

    public void cadastrarPensaoAlimenticia(String dependente, float valor) {
        String parentesco = getParentesco(dependente);
        if (parentesco.toLowerCase().contains("filh") ||
                parentesco.toLowerCase().contains("alimentand")) {
            pensoesAlimenticias.add(new PensaoAlimenticia(valor, dependente));
        }
    }

    public float getTotalPensaoAlimenticia() {
        float total = 0;
        for (PensaoAlimenticia p : pensoesAlimenticias) {
            total += p.getValor();
        }
        return total;
    }

    public void cadastrarDeducaoIntegral(String nome, float valorDeducao) {
        deducoes.add(new DeducaoIntegral(valorDeducao, nome));
    }

    public String getOutrasDeducoes(String nome) {
        for (DeducaoIntegral d : deducoes) {
            if (d.getNome().toLowerCase().contains(nome.toLowerCase()))
                return d.getNome();
        }
        return null;
    }

    public float getDeducao(String nome) {
        for (DeducaoIntegral d : deducoes) {
            if (d.getNome().toLowerCase().contains(nome.toLowerCase()))
                return d.getValor();
        }
        return 0;
    }

    public float getTotalOutrasDeducoes() {
        float soma = 0;
        for (DeducaoIntegral d : deducoes) {
            soma += d.getValor();
        }
        return soma;
    }

    public float baseCalculo() {
        BaseCalculo baseCalculo = new BaseCalculo(this);
        return baseCalculo.calcular();
    }

    public float calcularImposto() {
        float base = baseCalculo();
        return calcularFaixasImposto(base);
    }

    private float calcularFaixasImposto(float base) {
        float imposto = 0;
        if (base > 4664.68f) {
            imposto += (base - 4664.68f) * 0.275f;
            base = 4664.68f;
        }
        if (base > 3751.05f) {
            imposto += (base - 3751.05f) * 0.225f;
            base = 3751.05f;
        }
        if (base > 2826.65f) {
            imposto += (base - 2826.65f) * 0.15f;
            base = 2826.65f;
        }
        if (base > 2259.20f) {
            imposto += (base - 2259.20f) * 0.075f;
        }
        return imposto;
    }

    public float calcularAliquotaEfetiva() {
        float contribuicoes = getTotalRendimentosTributaveis();
        if (contribuicoes > 0)
            return (calcularImposto() / contribuicoes);
        else
            return 0.0f;
    }
}
