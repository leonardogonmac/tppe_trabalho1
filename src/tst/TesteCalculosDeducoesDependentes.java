package tst;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.IRPF;

@RunWith(Parameterized.class)
public class TesteCalculosDeducoesDependentes {

	IRPF irpf;
	private String[] nomesDependentes;
    private String[] parentescosDependentes;
	private float deducao;
	
	public TesteCalculosDeducoesDependentes(String[] nomesDependentes, String[] parentescosDependentes, float deducao){
		this.nomesDependentes = nomesDependentes;
		this.parentescosDependentes = parentescosDependentes;
		this.deducao = deducao;
	}
	
	@Parameters 
	public static Collection<Object[]> getParam(){
		String[][] nomesDepMat = {
			{"Maria da Silva"},
			{"Maria da Silva", "Carlos da Silva", },
			{"Maria da Silva", "Carlos da Silva", "Jose da Silva"}
		};
		String[][] parenDepMat = {
			{"Filho"},
			{"Filho", "Filho"},
			{"Filho", "Filho", "Filho"}
		};
		float[] deducaoVetor = {189.59f, 379.18f, 568.77f};

		return Arrays.asList(new Object[][]{
			{nomesDepMat[0], parenDepMat[0], deducaoVetor[0]},
			{nomesDepMat[1], parenDepMat[1], deducaoVetor[1]},
			{nomesDepMat[2], parenDepMat[2], deducaoVetor[2]},
		});

	}

	@Before
	public void setup() {
		irpf = new IRPF();
		for(int i = 0; i < nomesDependentes.length; i++){
			irpf.cadastrarDependente(nomesDependentes[i], parentescosDependentes[i]);
		}
	}
	
	@Test 
	public void testeDeducaoDependente(){
		assertEquals(deducao, irpf.getDeducao(), 0.0f);
	}

	/*@Test
	public void testDeducao1Dependente() {
		irpf.cadastrarDependente("Maria da Silva", "Filho");
		assertEquals(189.59f, irpf.getDeducao(), 0.0f);
	}
	
	@Test
	public void testDeducao2Dependente() {
		irpf.cadastrarDependente("Maria da Silva", "Filho");
		irpf.cadastrarDependente("Carlos da Silva", "Filho");
		assertEquals(379.18f, irpf.getDeducao(), 0.0f);
	}
	
	@Test
	public void testDeducao3Dependente() {
		irpf.cadastrarDependente("Maria da Silva", "Filho");
		irpf.cadastrarDependente("Carlos da Silva", "Filho");
		irpf.cadastrarDependente("Jose da Silva", "Filho");
		assertEquals(568.77f, irpf.getDeducao(), 0.0f);
	}*/

}
