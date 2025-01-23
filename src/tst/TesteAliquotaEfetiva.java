package tst;

import static org.junit.Assert.*;

import java.beans.Transient;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.*;

@RunWith(Parameterized.class)
public class TesteAliquotaEfetiva {
    IRPF irpf;
    String[] nomeRendimento;
    boolean[] rendimentoTributavel;
    float[] valorRendimento;
    float[] contribuicoesPrevidenciarias;
    String[] nomesDependentes;
    String[] parentescosDependentes;
    float[] pensoesAlimenticias;
    float aliquota;

    public TesteAliquotaEfetiva(String[] nomeRendimento, boolean[] rendimentoTributavel, float[] valorRendimento, float[] contribuicoesPrevidenciarias, String[] nomesDependentes, String[] parentescosDependentes, float[] pensoesAlimenticias, float aliquota){
        this.nomeRendimento  = nomeRendimento;
        this.rendimentoTributavel = rendimentoTributavel;
        this.valorRendimento = valorRendimento;
        this.contribuicoesPrevidenciarias = contribuicoesPrevidenciarias;
        this.nomesDependentes = nomesDependentes;
        this.parentescosDependentes = parentescosDependentes;
        this.pensoesAlimenticias = pensoesAlimenticias;
        this.aliquota = aliquota;
    }

    @Parameters 
    public static Collection<Object[]> getParam(){
        String[][] nomeRendMat = {
            {"Salário", "Aluguel", "Bolsa"},
            {"Salário"},
            {}
        };
        boolean[][] rendTribMat = {
            {Rendimento.TRIBUTAVEL, Rendimento.TRIBUTAVEL, Rendimento.NAOTRIBUTAVEL},
            {Rendimento.TRIBUTAVEL},
            {}
        };
        float[][] valorRendMat = {
            {8000f, 2000f, 1500f},
            {2259.19f},
            {}
        };
        float[][] contrPrevMat = {
            {500f, 1000f},
            {},
            {}
        };
        String[][] nomesDepMat = {
            {"João"},
            {},
            {}
        };
        String[][] parenDepMat = {
            {"Filho"},
            {},
            {}
        };
        float[][] pensoesAliMat = {
            {1500f},
            {},
            {}
        };

        float[] aliquotaVetor = {0.097f, 0.0f, 0.0f};

        return Arrays.asList(new Object[][]{
            {nomeRendMat[0], rendTribMat[0], valorRendMat[0], contrPrevMat[0], nomesDepMat[0], parenDepMat[0], pensoesAliMat[0], aliquotaVetor[0]},
            
            {nomeRendMat[1], rendTribMat[1], valorRendMat[1], contrPrevMat[1], nomesDepMat[1], parenDepMat[1], pensoesAliMat[1], aliquotaVetor[1]},
            
            {nomeRendMat[2], rendTribMat[2], valorRendMat[2], contrPrevMat[2], nomesDepMat[2], parenDepMat[2], pensoesAliMat[2], aliquotaVetor[2]}
        });
    }

    @Before
    public void setup(){
        irpf = new IRPF();
        for(int i = 0; i < valorRendimento.length; i++){
            irpf.criarRendimento(nomeRendimento[i], rendimentoTributavel[i], valorRendimento[i]);
        }
        for(int i = 0; i < nomesDependentes.length; i++){
            irpf.cadastrarDependente(nomesDependentes[i], parentescosDependentes[i]);
        }
        for(int i = 0; i < nomesDependentes.length; i++){
            irpf.cadastrarPensaoAlimenticia(nomesDependentes[i], pensoesAlimenticias[i]);
        }
        for(int i = 0; i < contribuicoesPrevidenciarias.length; i++){
            irpf.cadastrarContribuicaoPrevidenciaria(contribuicoesPrevidenciarias[i]);
        }
    }

    @Test 
    public void testeAliquotaEfetiva(){
        assertEquals(aliquota, irpf.calcularAliquotaEfetiva(), 0.01f);
    }    
}


/* Versão não parametrizada
    @Test
    public void testeAliquotaEfetiva() {
        irpf.criarRendimento("Salário", Rendimento.TRIBUTAVEL, 8000f);
        irpf.criarRendimento("Aluguel", Rendimento.TRIBUTAVEL, 2000f);
        irpf.criarRendimento("Bolsa", Rendimento.NAOTRIBUTAVEL, 1500f);
        irpf.cadastrarContribuicaoPrevidenciaria(500f);
        irpf.cadastrarContribuicaoPrevidenciaria(1000f);
        irpf.cadastrarDependente("João", "Filho");
        irpf.cadastrarPensaoAlimenticia("João", 1500f);

        assertEquals(0.097f, irpf.calcularAliquotaEfetiva(), 0.01f);
    }

    @Test
    public void testeAliquotaEfetivaPrimeiraFaixa() {
        irpf.criarRendimento("Salário", Rendimento.TRIBUTAVEL, 2259.19f);
        assertEquals(0.0f, irpf.calcularAliquotaEfetiva(), 0.00f);
    }

    @Test
    public void testeAliquotaEfetivaSemRendimentos() {
        assertEquals(0.0f, irpf.calcularAliquotaEfetiva(), 0.00f);
    }
*/    
