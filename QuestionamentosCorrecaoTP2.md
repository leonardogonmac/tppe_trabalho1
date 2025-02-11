# Questionamentos

#### Grupo 19

Alunos:
* Arthur Grandão de Mello - 211039250
* Leonardo Gonçalves Machado - 211029405
* Paulo Victor Fonseca Sousa - 211043718

> Link do Repositório: [https://github.com/leonardogonmac/tppe_trabalho1](https://github.com/leonardogonmac/tppe_trabalho1)

## Refatoração Extrair Método

Na correção do trabalho, o senhor afirma que a operação não foi realizada, apesar de a termos feito.

* Código original:
```java
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
```

* Resultado:
```java
    public float calcularImposto() {
        float base = baseCalculo();
        return calcularFaixasImposto(base);
    }
```

**RESPOSTA DO PROFESSOR:**
A refatoração ``Extrair método'' é utilizada para transformar **fragmentos de código** em seu próprio método e tem como resultado a simplificação do método original, pois este se transforma em uma sequencia de chamadas à métodos extraídos. A refatoração proposta pelo grupo não extraiu fragmentos do método mas todo o corpo do método original foi movido para um método chamado baseCalculo(). Com isso foi criada uma indireção e não uma simplificação do método de origem.


> Link do commit: [https://github.com/leonardogonmac/tppe_trabalho1/commit/5da9b559ece6f174605bff6b1cc45f177ac0e074](https://github.com/leonardogonmac/tppe_trabalho1/commit/5da9b559ece6f174605bff6b1cc45f177ac0e074)

## Refatoração Substituir Método por Objeto-Método

Nesse ponto o senhor comentou que a operação não foi bem sucedida em remover a variável temporária do método `baseCalculo()`. Entretanto, nós acreditamos que as nossas alterações foram efetivas nesse quesito.

* Código original:
```java
    public float baseCalculo(){
		float valor = getTotalRendimentosTributaveis();

		valor -= getTotalContribuicoesPrevidenciarias();
		valor -= getTotalPensaoAlimenticia();
		valor -= getTotalOutrasDeducoes();
		valor -= getNumDependentes() * 189.59f;

		if (valor < 0f) valor = 0f;

		return valor;
	}
```

* Resultado:
```java
    public float baseCalculo() {
        BaseCalculo baseCalculo = new BaseCalculo(this);
        return baseCalculo.calcular();
    }
```

**RESPOSTA DO PROFESSOR:**
A refatoração ''Substituir método por objeto-método'' é utilizada para remover variáveis temporárias do método original, ao transformar tais variáveis em atributos do objeto-método. O método baseCalculo continua com variável temporária definida em seu corpo, no caso a variável ```float valor```. 


> Link do commit: [https://github.com/leonardogonmac/tppe_trabalho1/commit/5da9b559ece6f174605bff6b1cc45f177ac0e074](https://github.com/leonardogonmac/tppe_trabalho1/commit/5da9b559ece6f174605bff6b1cc45f177ac0e074)

## Testes

O critério 1 não foi satisfeito em duas refatorações, porém todos os casos de testes continuam passando sem qualquer erro, mesmo após as alterações.

```sh
$ ./tests.sh AllTests
JUnit version 4.13.2
..................................................
Time: 0,009

OK (50 tests)

```

> Testado na versão final da entrega.

