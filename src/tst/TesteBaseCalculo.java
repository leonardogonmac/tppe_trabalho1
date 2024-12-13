package tst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.beans.Transient;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.IRPF;

@RunWith(Parameterized.class)
public class TesteBaseCalculo {
    private IRPF irpf;
    private String[] nomeRendimento;
    private float[] valorRendimento;
    private boolean[] rendimentoTributavel;
    private String[] nomesDeducoes;
    private float[] valoresDeducoes;
    private String[] nomesDependentes;
    private String[] parentescosDependentes;
    private float [] contribuicoesPrevidenciarias;

    private float base;
    
    public TesteBaseCalculo(String[] nomeRendimento, float[] valorRendimento, boolean[] rendimentoTributavel, String[] nomesDeducoes, float[] valoresDeducoes, String[] nomesDependentes, String[] parentescosDependentes, float[] contribuicoesPrevidenciarias, float base){
        this.nomeRendimento = nomeRendimento;
        this.valorRendimento = valorRendimento;
        this.rendimentoTributavel = rendimentoTributavel;
        this.nomesDeducoes = nomesDeducoes;
        this.valoresDeducoes = valoresDeducoes;
        this.nomesDependentes = nomesDependentes;
        this.parentescosDependentes = parentescosDependentes;
        this.contribuicoesPrevidenciarias = contribuicoesPrevidenciarias;
        this.base = base;
    }

    @Parameters
    public static Collection<Object[]> getParam(){
        String[][] nomeRendMat = {
            {"Rendimento1A", "Rendimento1B", "Rendimento1C"},
            {"Rendimento2A", "Rendimento2B", "Rendimento2C"},
            {"Rendimento3A", "Rendimento3B", "Rendimento3C"}
        };
        float[][] valorRendMat = {
            {1000.0f, 2000.0f, 3000.0f},
            {1100.0f, 2200.0f, 3300.0f},
            {700.0f, 200.0f, 460.0f}
        };
        boolean[][] rendTribMat = {
            {true, false, true},
            {false, true, false},
            {true, true, true}
        };
        String[][] nomeDedMat = {
            {"Deducao1A", "Deducao1B", "Deducao1C"},
            {"Deducao2A", "Deducao2B", "Deducao2C"},
            {"Deducao2A", "Deducao2B", "Deducao2C"},
            {"Deducao3A", "Deducao3B", "Deducao3C"}
        };
        float[][] valoresDedMat = {
            {180.0f, 300.0f, 50.0f},
            {330.0f, 68.0f, 120.0f},
            {90.0f, 34.0f, 56.0f}
        };
        String[][] nomesDepMat = {
            {"Dependente1A", "Dependente1B", "Dependente1C"},
            {"Dependente2A", "Dependente2B", "Dependente2C"},
            {"Dependente3A", "Dependente3B", "Dependente3C"}
        };
        String[][] parenDepMat = {
            {"filho", "filha", "outro"},
            {"alimentando", "outro", "alimentanda"},
            {"filha", "outro", "alimentanda"}
        };

        float[][] contribPrevMat = {
            {100.0f, 25.0f, 50.0f},
            {200.0f, 30.0f, 45.0f},
            {80.0f, 79.0f, 56.0f}
        };


        float[] baseVetor = {2626.23f, 738.23f, 296.23f};

        return Arrays.asList(new Object [][]{
            {nomeRendMat[0], valorRendMat[0], rendTribMat[0], nomeDedMat[0], valoresDedMat[0], nomesDepMat[0], parenDepMat[0], contribPrevMat[0], baseVetor[0]},
            {nomeRendMat[1], valorRendMat[1], rendTribMat[1], nomeDedMat[1], valoresDedMat[1], nomesDepMat[1], parenDepMat[1], contribPrevMat[1], baseVetor[1]},
            {nomeRendMat[2], valorRendMat[2], rendTribMat[2], nomeDedMat[2], valoresDedMat[2], nomesDepMat[2], parenDepMat[2], contribPrevMat[2], baseVetor[2]},
        });
    }

    @Before
    public void setup(){
        irpf = new IRPF();
        for(int i = 0; i < valorRendimento.length; i++){
            irpf.criarRendimento(nomeRendimento[i], rendimentoTributavel[i], valorRendimento[i]);
        }
        for(int i = 0; i < nomesDeducoes.length; i++){
            irpf.cadastrarDeducaoIntegral(nomesDeducoes[i], valoresDeducoes[i]);
        }
        for(int i = 0; i < nomesDependentes.length; i++){
            irpf.cadastrarDependente(nomesDependentes[i], parentescosDependentes[i]);
        }
        for(int i = 0; i < nomesDependentes.length; i++){
            irpf.cadastrarPensaoAlimenticia(nomesDependentes[i], 50.0f);
        }
        for(int i = 0; i < contribuicoesPrevidenciarias.length; i++){
            irpf.cadastrarContribuicaoPrevidenciaria(contribuicoesPrevidenciarias[i]);
        }
    }

    @Test 
    public void testBaseCalculo(){
        assertEquals(irpf.baseCalculo(), base, 0.01f);
    }

}