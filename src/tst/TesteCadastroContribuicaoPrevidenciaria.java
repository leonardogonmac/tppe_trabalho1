package tst;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.*;

@RunWith(Parameterized.class)
public class TesteCadastroContribuicaoPrevidenciaria {

	IRPF irpf;
	private float[] contribuicoesPrevidenciarias;
	private int numContribuicoes;
	private float totalContribuicoes;
	private float deducao;
	
	public TesteCadastroContribuicaoPrevidenciaria(float[] contribuicoesPrevidenciarias, int numContribuicoes, float totalContribuicoes, float deducao){
		this.contribuicoesPrevidenciarias = contribuicoesPrevidenciarias;
		this.numContribuicoes = numContribuicoes; 
		this.totalContribuicoes = totalContribuicoes;
		this.deducao = deducao;
	}
	
	@Parameters 
	public static Collection<Object[]> getParam(){
		float[][] contribMat = {
			{1000f},
			{1000f, 500f},
			{1000f, 500f, 200f}
		};
		int[] numContrVetor = {1, 2, 3};
		float[] totalVetor = {1000f, 1500f, 1700f};
		float[] deducaoVetor = {1000f, 1500f, 1700f};

		return Arrays.asList(new Object[][]{
			{contribMat[0], numContrVetor[0], totalVetor[0], deducaoVetor[0]},
			{contribMat[1], numContrVetor[1], totalVetor[1], deducaoVetor[1]},
			{contribMat[2], numContrVetor[2], totalVetor[2], deducaoVetor[2]},
		});
	}

	@Before
	public void setup() {
		irpf = new IRPF();
		for(int i = 0; i < contribuicoesPrevidenciarias.length; i++){
            irpf.cadastrarContribuicaoPrevidenciaria(contribuicoesPrevidenciarias[i]);
        }
	}
	
	@Test 
	public void testeCadastrarContribuicaoPrevidenciaria(){
		assertEquals(numContribuicoes, irpf.getNumContribuicoesPrevidenciarias());
		assertEquals(totalContribuicoes, irpf.getTotalContribuicoesPrevidenciarias(), 0f);
		assertEquals(deducao, irpf.getDeducao(), 0f);
	}

	/*@Test
	public void cadastrarUmaContribuicaoPrevidenciaria() {
		irpf.cadastrarContribuicaoPrevidenciaria(1000);
		assertEquals(1, irpf.getNumContribuicoesPrevidenciarias());
		assertEquals(1000f, irpf.getTotalContribuicoesPrevidenciarias(), 0f);
		assertEquals(1000f, irpf.getDeducao(), 0f);
	}
	
	
	@Test
	public void cadastrarDuasContribuicoesPrevidenciarias() {
		irpf.cadastrarContribuicaoPrevidenciaria(1000);
		irpf.cadastrarContribuicaoPrevidenciaria(500);
		assertEquals(2, irpf.getNumContribuicoesPrevidenciarias());
		assertEquals(1500f, irpf.getTotalContribuicoesPrevidenciarias(), 0f);
		assertEquals(1500f, irpf.getDeducao(), 0f);
	}
	
	@Test
	public void cadastrarTresContribuicoesPrevidenciarias() {
		irpf.cadastrarContribuicaoPrevidenciaria(1000);
		irpf.cadastrarContribuicaoPrevidenciaria(500);
		irpf.cadastrarContribuicaoPrevidenciaria(200);
		assertEquals(3, irpf.getNumContribuicoesPrevidenciarias());
		assertEquals(1700f, irpf.getTotalContribuicoesPrevidenciarias(), 0f);
		assertEquals(1700f, irpf.getDeducao(), 0f);
	}*/
	
	
}
