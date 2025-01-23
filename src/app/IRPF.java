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

	/**
	 * Cadastra um rendimento na base do contribuinte, informando o nome do
	 * rendimento, seu valor e se ele é tributável ou não.
	 * 
	 * @param nome       nome do rendimento a ser cadastrado
	 * @param tributavel true caso seja tributável, false caso contrário
	 * @param valor      valor do rendimento a ser cadastrado
	 */
	public void criarRendimento(String nome, boolean tributavel, float valor) {
		rendimentos.add(new Rendimento(nome, tributavel, valor));
	}

	/**
	 * Retorna o número de rendimentos já cadastrados para o contribuinte
	 * 
	 * @return numero de rendimentos
	 */
	public int getNumRendimentos() {
		return rendimentos.size();
	}

	/**
	 * Retorna o valor total de rendimentos cadastrados para o contribuinte
	 * 
	 * @return valor total dos rendimentos
	 */
	public float getTotalRendimentos() {
		float totalRendimentos = 0;
		for (Rendimento rendimento : rendimentos) {
			totalRendimentos += rendimento.getValorRendimento();
		}
		return totalRendimentos;
	}

	/**
	 * Retorna o valor total de rendimentos tributáveis do contribuinte
	 * 
	 * @return valor total dos rendimentos tributáveis
	 */
	public float getTotalRendimentosTributaveis() {
		float totalRendimentosTributaveis = 0;
		for (Rendimento rendimento : rendimentos) {
			if (rendimento.isTributavel()) {
				totalRendimentosTributaveis += rendimento.getValorRendimento();
			}
		}
		return totalRendimentosTributaveis;
	}

	/**
	 * Método para realizar o cadastro de um dependente, informando seu grau
	 * de parentesco
	 * 
	 * @param nome       Nome do dependente
	 * @param parentesco Grau de parentesco
	 */
	public void cadastrarDependente(String nome, String parentesco) {
		dependentes.add(new Dependente(nome, parentesco));
	}

	/**
	 * Método que retorna o numero de dependentes do contribuinte
	 * 
	 * @return numero de dependentes
	 */
	public int getNumDependentes() {
		return dependentes.size();
	}

	/**
	 * Return o valor do total de deduções para o contribuinte
	 * 
	 * @return valor total de deducoes
	 */
	public float getDeducao() {
		float total = 0;
		for (Dependente d : dependentes) {
			total += d.getValor();
		}
		total += getTotalContribuicoesPrevidenciarias();
		return total;
	}

	/**
	 * Cadastra um valor de contribuição previdenciária oficial
	 * 
	 * @param contribuicao valor da contribuição previdenciária oficial
	 */
	public void cadastrarContribuicaoPrevidenciaria(float contribuicao) {
		contribuicoesPrevidenciarias.add(new ContribuicaoPrevidencia(contribuicao));
	}

	/**
	 * Retorna o numero total de contribuições realizadas como contribuicao
	 * previdenciaria oficial
	 * 
	 * @return numero de contribuições realizadas
	 */
	public int getNumContribuicoesPrevidenciarias() {
		return contribuicoesPrevidenciarias.size();
	}

	/**
	 * Retorna o valor total de contribuições oficiais realizadas
	 * 
	 * @return valor total de contribuições oficiais
	 */
	public float getTotalContribuicoesPrevidenciarias() {
		float totalContribuicoes = 0;
		for (ContribuicaoPrevidencia contribuicao : contribuicoesPrevidenciarias) {
			totalContribuicoes += contribuicao.getValor();
		}
		return totalContribuicoes;
	}

	/**
	 * Realiza busca do dependente no cadastro do contribuinte
	 * 
	 * @param nome nome do dependente que está sendo pesquisado
	 * @return nome do dependente ou null, caso nao conste na lista de dependentes
	 */
	public String getDependente(String nome) {
		for (Dependente d : dependentes) {
			if (d.getNomeDependente().contains(nome))
				return d.getNomeDependente();
		}
		return null;
	}

	/**
	 * Método que retorna o grau de parentesco para um dado dependente, caso ele
	 * conste na lista de dependentes
	 * 
	 * @param dependente nome do dependente
	 * @return grau de parentesco, nulo caso nao exista o dependente
	 */
	public String getParentesco(String dependente) {
		for (Dependente d : dependentes) {
			if (d.getNomeDependente().equalsIgnoreCase(dependente))
				return d.getParentescoDependente();
		}
		return null;
	}

	/**
	 * Realiza o cadastro de uma pensao alimenticia para um dos dependentes do
	 * contribuinte, caso ele seja um filho ou alimentando.
	 * 
	 * @param dependente nome do dependente
	 * @param valor      valor da pensao alimenticia
	 */
	public void cadastrarPensaoAlimenticia(String dependente, float valor) {
		String parentesco = getParentesco(dependente);
		if (parentesco.toLowerCase().contains("filh") ||
				parentesco.toLowerCase().contains("alimentand")) {
			pensoesAlimenticias.add(new PensaoAlimenticia(valor, dependente));
		}
	}

	/**
	 * Retorna o valor total pago em pensões alimentícias pelo contribuinte.
	 * 
	 * @return valor total de pensoes alimenticias
	 */
	public float getTotalPensaoAlimenticia() {
		float totalPensaoAlimenticia = 0;
		for (PensaoAlimenticia p : pensoesAlimenticias) {
			totalPensaoAlimenticia += p.getValor();
		}
		return totalPensaoAlimenticia;
	}

	/**
	 * Metodo para cadastrar deduções integrais para o contribuinte. Para cada
	 * dedução é informado seu nome e valor.
	 * 
	 * @param nome         nome da deducao
	 * @param valorDeducao valor da deducao
	 */
	public void cadastrarDeducaoIntegral(String nome, float valorDeducao) {
		deducoes.add(new DeducaoIntegral(valorDeducao, nome));
	}

	/**
	 * Método para pesquisar uma deducao pelo seu nome.
	 * 
	 * @param substring do nome da deducao a ser pesquisada
	 * @return nome da deducao, ou null caso na esteja cadastrada
	 */
	public String getOutrasDeducoes(String nome) {
		for (DeducaoIntegral d : deducoes) {
			if (d.getNome().toLowerCase().contains(nome.toLowerCase()))
				return d.getNome();
		}
		return null;
	}

	/**
	 * Obtem o valor da deducao à partir de seu nome
	 * 
	 * @param nome nome da deducao para a qual se busca seu valor
	 * @return valor da deducao
	 */
	public float getDeducao(String nome) {
		for (int i = 0; i < deducoes.size(); i++) {
			if (deducoes.get(i).getNome().toLowerCase().contains(nome.toLowerCase()))
				return deducoes.get(i).getValor();
		}
		
		return 0;
	}

	/**
	 * Obtem o valor total de todas as deduções que nao sao do tipo
	 * contribuicoes previdenciarias ou por dependentes
	 * 
	 * @return valor total das outras deducoes
	 */
	public float getTotalOutrasDeducoes() {
		float soma = 0;
		for (DeducaoIntegral d : deducoes) {
			soma += d.getValor();
		}
		return soma;
	}

	/**
	 * Calcula a base de cálculo do imposto de renda, subtraindo do total de
	 * rendimentos tributáveis
	 * as deduções tributárias.
	 * 
	 * @return valor da base de cálculo
	 */
	public float baseCalculo() {
		float valor = getTotalRendimentosTributaveis();

		valor -= getTotalContribuicoesPrevidenciarias();
		valor -= getTotalPensaoAlimenticia();
		valor -= getTotalOutrasDeducoes();
		valor -= getNumDependentes() * 189.59f;

		if (valor < 0f)
			valor = 0f;

		return valor;
	}

	/**
	 * Calcula o imposto a partir da base de cálculo, com base nas faixas do IRPF.
	 * 
	 * @return valor total do imposto.
	 */
	public float calcularImposto() {
		float base = baseCalculo();
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
			base = 2259.20f;
		}

		return imposto;
	}

	/**
	 * Calcula a alíquota efetiva do contribuinte, a partir do total de rendimentos
	 * tributáveis.
	 * 
	 * @return valor da alíquota efetiva.
	 */
	public float calcularAliquotaEfetiva() {
		float contribuicoes = getTotalRendimentosTributaveis();
		if (contribuicoes > 0)
			return (calcularImposto() / contribuicoes);
		else
			return 0.0f;
	}
}
