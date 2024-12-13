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
public class TesteCadastroPensaoAlimenticia {
	
	IRPF irpf; 
	private String[] nomesDependentes;
    private String[] parentescosDependentes;
	private float[] valorPensao;
	private float totalPensao;

	public TesteCadastroPensaoAlimenticia(String[] nomesDependentes, String[] parentescosDependentes, float[] valorPensao,float totalPensao){
		this.nomesDependentes = nomesDependentes;
		this.parentescosDependentes = parentescosDependentes;
		this.valorPensao = valorPensao;
		this.totalPensao = totalPensao;
	}

	@Parameters 
	public static Collection<Object[]> getParam(){
		String[][] nomesDepMat = {
			{"Jose da Silva"},
			{"Jose da Silva", "Laura da Silva", },
			{"Jose da Silva", "Laura da Silva", "Carlos da Silva", "Maria da Silva"}
		};

		String[][] parenMat = {
			{"filho"},
			{"filho", "filha"},
			{"filho", "filha", "Alimentando", "Mae"}
		};

		float[][] valorPenMat = {
			{1000f},
			{1000f, 1000f},
			{1000f, 1000f, 1000f, 1000f}
		};
		float[] totalvetor = {1000f, 2000f, 3000f};

		return Arrays.asList(new Object[][]{
			{nomesDepMat[0], parenMat[0], valorPenMat[0], totalvetor[0]},
			{nomesDepMat[1], parenMat[1], valorPenMat[1], totalvetor[1]},
			{nomesDepMat[2], parenMat[2], valorPenMat[2], totalvetor[2]}
		});
	}

	@Before
	public void setup() {
		irpf = new IRPF();
		for(int i = 0; i < nomesDependentes.length; i++){
			irpf.cadastrarDependente(nomesDependentes[i], parentescosDependentes[i]);
		}
		for(int i = 0; i < nomesDependentes.length; i++){
            irpf.cadastrarPensaoAlimenticia(nomesDependentes[i], valorPensao[i]);
        }
	}
	

	@Test 
	public void testeCadastroPensaoAlimenticia(){
		assertEquals(totalPensao, irpf.getTotalPensaoAlimenticia(), 0f);
	}
	
	/*@Test
	public void testeCadastroUmaPensaoAlimenticia() {
		String dependente = "Jose da Silva";
		String parentesco = "filho";
		irpf.cadastrarDependente(dependente, parentesco);
		irpf.cadastrarPensaoAlimenticia(dependente, 1000f);
		
		assertEquals(1000f, irpf.getTotalPensaoAlimenticia(), 0f);
	}
	
	@Test
	public void testeCadastroDuasPensoesAlimenticia() {
		String dependente = "Jose da Silva";
		String parentesco = "filho";
		irpf.cadastrarDependente(dependente, parentesco);
		irpf.cadastrarPensaoAlimenticia(dependente, 1000f);
		dependente = "Laura da Silva";
		parentesco = "filha";
		irpf.cadastrarDependente(dependente, parentesco);
		irpf.cadastrarPensaoAlimenticia(dependente, 1000f);
		
		assertEquals(2000f, irpf.getTotalPensaoAlimenticia(), 0f);
	}
	
	
	@Test
	public void testeCadastroQuatrosPensoesAlimenticia() {
		String dependente = "Jose da Silva";
		String parentesco = "filho";
		irpf.cadastrarDependente(dependente, parentesco);
		irpf.cadastrarPensaoAlimenticia(dependente, 1000f);
		dependente = "Laura da Silva";
		parentesco = "filha";
		irpf.cadastrarDependente(dependente, parentesco);
		irpf.cadastrarPensaoAlimenticia(dependente, 1000f);
		dependente = "Carlos da Silva";
		parentesco = "Alimentando";
		irpf.cadastrarDependente(dependente, parentesco);
		irpf.cadastrarPensaoAlimenticia(dependente, 1000f);
		dependente = "Maria da Silva";
		parentesco = "Mae";
		irpf.cadastrarDependente(dependente, parentesco);
		irpf.cadastrarPensaoAlimenticia(dependente, 1000f);
		
		assertEquals(3000f, irpf.getTotalPensaoAlimenticia(), 0f);
	}*/
}
