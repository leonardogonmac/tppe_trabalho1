package tst;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


import app.*;

@RunWith(Parameterized.class)
public class TesteRendimentos {

	
	IRPF irpf;
	private String[] nomeRendimento;
    private boolean[] rendimentoTributavel;
	private float[] valorRendimento;
	private int numRendimentos;
	private float totalRendimentos;
	private float totalRendimentosTributaveis;

	public TesteRendimentos(String[] nomeRendimento, boolean[] rendimentoTributavel, float[] valorRendimento, int numRendimentos, float totalRendimentos, float totalRendimentosTributaveis){
		this.nomeRendimento = nomeRendimento;
		this.rendimentoTributavel = rendimentoTributavel;
		this.valorRendimento = valorRendimento;
		this.numRendimentos = numRendimentos;
		this.totalRendimentos = totalRendimentos;
		this.totalRendimentosTributaveis = totalRendimentosTributaveis;
	}
	
	@Parameters 
	public static Collection<Object[]> getParam(){
		String[][] nomeRendMat = {
            {"Salario"},
            {"Aluguel"},
            {"Salario", "Aluguel"},
			{"Salario", "Aluguel", "Bolsa de Pesquisa"}
        };
        float[][] valorRendMat = {
            {5000f},
            {4000f},
            {5000f, 4000f},
			{5000f, 4000f, 3000f}
        };
        boolean[][] rendTribMat = {
            {Rendimento.TRIBUTAVEL},
            {Rendimento.TRIBUTAVEL},
            {Rendimento.TRIBUTAVEL, Rendimento.TRIBUTAVEL},
			{Rendimento.TRIBUTAVEL, Rendimento.TRIBUTAVEL, Rendimento.NAOTRIBUTAVEL}
        };
		int[] numRendVetor = {1, 1, 2, 3};
		float[] totalRendVetor = {5000f, 4000f, 9000f, 12000f};
		float[] totalRendTriVetor = {5000f, 4000f, 9000f, 9000f};

		return Arrays.asList(new Object[][]{
			{nomeRendMat[0], rendTribMat[0], valorRendMat[0], numRendVetor[0], totalRendVetor[0], totalRendTriVetor[0]},
			{nomeRendMat[1], rendTribMat[1], valorRendMat[1], numRendVetor[1], totalRendVetor[1], totalRendTriVetor[1]},
			{nomeRendMat[2], rendTribMat[2], valorRendMat[2], numRendVetor[2], totalRendVetor[2], totalRendTriVetor[2]},
			{nomeRendMat[3], rendTribMat[3], valorRendMat[3], numRendVetor[3], totalRendVetor[3], totalRendTriVetor[3]}
		});
	}
	
	@Before
	public void setup() {
		irpf = new IRPF();
		for(int i = 0; i < valorRendimento.length; i++){
            irpf.criarRendimento(nomeRendimento[i], rendimentoTributavel[i], valorRendimento[i]);
        }
	}

	@Test 
	public void testRendimentos(){
		assertEquals(numRendimentos, irpf.getNumRendimentos());
		assertEquals(totalRendimentos, irpf.getTotalRendimentos(), 0);
		assertEquals(totalRendimentosTributaveis, irpf.getTotalRendimentosTributaveis(), 0);
	}

	/*@Test
	public void test1RendimentoTributavel() {
		irpf.criarRendimento("Salario", Rendimento.TRIBUTAVEL, 5000f);
		assertEquals(1, irpf.getNumRendimentos());
		assertEquals(5000f, irpf.getTotalRendimentos(), 0);
		assertEquals(5000f, irpf.getTotalRendimentosTributaveis(), 0);
	}
	
	@Test
	public void testeOutroRendimentoTributavel() {
		irpf.criarRendimento("Aluguel", Rendimento.TRIBUTAVEL, 4000f);
		assertEquals(1, irpf.getNumRendimentos());
		assertEquals(4000f, irpf.getTotalRendimentos(), 0);
		assertEquals(4000f, irpf.getTotalRendimentosTributaveis(), 0);
	}
	
	@Test
	public void testDoisRendimentosTributaveis() {
		irpf.criarRendimento("Salario", Rendimento.TRIBUTAVEL, 5000f);
		irpf.criarRendimento("Aluguel", Rendimento.TRIBUTAVEL, 4000f);
		assertEquals(2, irpf.getNumRendimentos());
		assertEquals(9000f, irpf.getTotalRendimentos(), 0);
		assertEquals(9000f, irpf.getTotalRendimentosTributaveis(), 0);
	}
	
	@Test
	public void testeTresRendimentos() {
		irpf.criarRendimento("Salario", Rendimento.TRIBUTAVEL, 5000f);
		irpf.criarRendimento("Aluguel", Rendimento.TRIBUTAVEL, 4000f);
		irpf.criarRendimento("Bolsa de pesquisa", Rendimento.NAOTRIBUTAVEL, 3000f);
		assertEquals(3, irpf.getNumRendimentos());
		assertEquals(12000f, irpf.getTotalRendimentos(), 0);
		assertEquals(9000f, irpf.getTotalRendimentosTributaveis(), 0);
	}*/

}
