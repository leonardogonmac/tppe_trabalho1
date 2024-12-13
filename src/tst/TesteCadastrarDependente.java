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
public class TesteCadastrarDependente {

	IRPF irpf;
	private String[] nomesDependentes;
	private String[] parentescosDependentes;
	private String[] primeiroNome;
	private String[] nomesNaoDependentes;
	private int numDependentes;


	public TesteCadastrarDependente(String[] nomesDependentes, String[] parentescosDependentes, String[] primeiroNome, String[] nomesNaoDependentes, int numDependentes){
		this.nomesDependentes = nomesDependentes;
		this.parentescosDependentes = parentescosDependentes;
		this.numDependentes = numDependentes;
	}

	@Parameters 
	public static Collection<Object[]> getParam(){
		String[][] nomesDepMat = {
			{"Miguel"},
			{"Miguel", "Maria"},
			{"Miguel", "Maria", "Carlos"},
			{"Jose da Silva"},
			{"Jose da Silva", "Laura da Silva"},
			{}
		};
		String[][] parenDepMat = {
			{"Filho"},
			{"Filho", "Filho"},
			{"Filho", "Filho", "Filho"},
			{"filho"},
			{"filho", "filho"},
			{}
		};

		String[][] primNomeMat = {
			{"Miguel"},
			{"Miguel", "Maria"},
			{"Miguel", "Maria", "Carlos"},
			{"Jose"},
			{"Jose", "Laura"},
			{}
		};

		String[][] nomesNaoDepMat = {
			{},
			{},
			{},
			{},
			{},
			{"Jose"}
		};

		int[] numDepVetor = {1, 2, 3, 1, 2, 0};

		return Arrays.asList(new Object[][] {
			{nomesDepMat[0], parenDepMat[0], primNomeMat[0], nomesNaoDepMat[0], numDepVetor[0]},
			{nomesDepMat[1], parenDepMat[1], primNomeMat[1], nomesNaoDepMat[1], numDepVetor[1]},
			{nomesDepMat[2], parenDepMat[2], primNomeMat[2], nomesNaoDepMat[2], numDepVetor[2]},
			{nomesDepMat[3], parenDepMat[3], primNomeMat[3], nomesNaoDepMat[3], numDepVetor[3]},
			{nomesDepMat[4], parenDepMat[4], primNomeMat[4], nomesNaoDepMat[5], numDepVetor[4]},
			{nomesDepMat[5], parenDepMat[5], primNomeMat[5], nomesNaoDepMat[5], numDepVetor[5]}
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
	public void testCadastroDependente(){
		assertEquals(numDependentes, irpf.getNumDependentes());
		for(int i = 0; i < nomesDependentes.length; i++)
			assertTrue(irpf.getParentesco(nomesDependentes[i]).equalsIgnoreCase(parentescosDependentes[i].toLowerCase()));
	}

	@Test 
	public void obterDependentes(){
		for(int i = 0; primeiroNome != null && i < primeiroNome.length; i++){
			String dependente = irpf.getDependente(primeiroNome[i]);
			assertNotNull(dependente);
			assertTrue(irpf.getParentesco(dependente).equalsIgnoreCase(parentescosDependentes[i]));
		}
	}

	@Test 
	public void dependentesInexistentes(){
		for(int i = 0; primeiroNome != null && i < nomesNaoDependentes.length; i++){
			String dependente = irpf.getDependente(nomesDependentes[i]);
			String parentesco = irpf.getParentesco(dependente);
			assertNull(dependente);
		}
	}

	/*@Test
	public void testCadastro1Dependente() {
		irpf.cadastrarDependente("Miguel", "Filho");
		assertEquals(1, irpf.getNumDependentes());
		assertTrue(irpf.getParentesco("Miguel").equalsIgnoreCase("filho"));
	}
	
	
	@Test
	public void testCadastro2Dependente() {
		irpf.cadastrarDependente("Miguel", "Filho");
		irpf.cadastrarDependente("Maria", "Filho");
		assertEquals(2, irpf.getNumDependentes());
	}
	
	@Test
	public void testCadastro3Dependente() {
		irpf.cadastrarDependente("Miguel", "Filho");
		irpf.cadastrarDependente("Maria", "Filho");
		irpf.cadastrarDependente("Carlos", "Filho");
		assertEquals(3, irpf.getNumDependentes());
	}
	
	@Test
	public void obterUmDependente() {
		irpf.cadastrarDependente("Jose da Silva", "filho");
		String dependente = irpf.getDependente("Jose");
		assertNotNull(dependente);
		assertTrue(irpf.getParentesco(dependente).equalsIgnoreCase("filho"));
	}
	
	@Test
	public void obterDoisDependentes() {
		irpf.cadastrarDependente("Jose da Silva", "filho");
		irpf.cadastrarDependente("Laura da Silva", "filho");
		String dependente = irpf.getDependente("Jose");
		assertNotNull(dependente);
		assertTrue(irpf.getParentesco(dependente).equalsIgnoreCase("filho"));
		dependente = irpf.getDependente("Laura");
		assertTrue(irpf.getParentesco(dependente).equalsIgnoreCase("filho"));
	}
	
	@Test
	public void dependenteInexistente() {
		String dependente = irpf.getDependente("Jose");
		String parentesco = irpf.getParentesco(dependente);
		
		assertNull(dependente);
		assertNull(parentesco);
	}
*/
}
