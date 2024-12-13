package tst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.IRPF;

@RunWith(Parameterized.class)
public class TesteCadastroOutrasDeducoes {

    private String[] nomesDeducoes;
    private float[] valoresDeducoes;
    private String deducaoConsulta;
    private Float valorEsperadoConsulta;
    private float totalEsperado;

    private IRPF irpf;

    public TesteCadastroOutrasDeducoes(String[] nomesDeducoes, float[] valoresDeducoes, String deducaoConsulta, Float valorEsperadoConsulta, float totalEsperado) {
        this.nomesDeducoes = nomesDeducoes;
        this.valoresDeducoes = valoresDeducoes;
        this.deducaoConsulta = deducaoConsulta;
        this.valorEsperadoConsulta = valorEsperadoConsulta;
        this.totalEsperado = totalEsperado;
    }

    @Before
    public void setup() {
        irpf = new IRPF();
    }

    @Parameters
    public static Collection<Object[]> getParameters() {
        return Arrays.asList(new Object[][]{
           
            {new String[]{"Funpresp"}, new float[]{1000f}, "Funpresp", 1000f, 1000f},
            
            {new String[]{"Funpresp", "Carne-leao"}, new float[]{1000f, 500f}, "Carne-leao", 500f, 1500f},
           
            {new String[]{"Funpresp", "Carne-leao", "PGBL"}, new float[]{1000f, 500f, 500f}, "PGBL", 500f, 2000f},
           
            {new String[]{"Funpresp"}, new float[]{1000f}, "Carne-leao", 0f, 1000f}
        });
    }

    @Test
    public void testeCadastroDeducoes() {
        // Cadastro das deduções
        for (int i = 0; i < nomesDeducoes.length; i++) {
            irpf.cadastrarDeducaoIntegral(nomesDeducoes[i], valoresDeducoes[i]);
        }

        // Verificação de dedução específica
        if (valorEsperadoConsulta == 0f) {
            assertNull(irpf.getOutrasDeducoes(deducaoConsulta));
        } else {
            assertNotNull(irpf.getOutrasDeducoes(deducaoConsulta));
            assertEquals(valorEsperadoConsulta, irpf.getDeducao(deducaoConsulta), 0f);
        }

        // Verificação do total de deduções
        assertEquals(totalEsperado, irpf.getTotalOutrasDeducoes(), 0f);
    }
}




/*package tst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import app.IRPF;

public class TesteCadastroOutrasDeducoes {

	IRPF irpf; 
	
	@Before
	public void setup() {
		irpf = new IRPF();
	}
	
	
	@Test
	public void cadastroUmaDeducao() {
		irpf.cadastrarDeducaoIntegral("Funpresp", 1000f);
		assertNotNull(irpf.getOutrasDeducoes("Funpresp")); 
		assertEquals(1000f, irpf.getDeducao("Funpresp"), 0f);
		assertEquals(1000f, irpf.getTotalOutrasDeducoes(), 0f);
	}
	
	@Test
	public void cadastroDuasDeducoes() {
		irpf.cadastrarDeducaoIntegral("Funpresp", 1000f);
		irpf.cadastrarDeducaoIntegral("Carne-leao", 500f);
		assertNotNull(irpf.getOutrasDeducoes("Funpresp")); 
		assertEquals(1000f, irpf.getDeducao("Funpresp"), 0f);
		assertEquals(500f, irpf.getDeducao("Carne-leao"), 0f);
		assertEquals(1500f, irpf.getTotalOutrasDeducoes(), 0f);
	}
	
	@Test
	public void cadastroTresDeducoes() {
		irpf.cadastrarDeducaoIntegral("Funpresp", 1000f);
		irpf.cadastrarDeducaoIntegral("Carne-leao", 500f);
		irpf.cadastrarDeducaoIntegral("PGBL", 500f);
		assertNotNull(irpf.getOutrasDeducoes("Funpresp")); 
		assertEquals(1000f, irpf.getDeducao("Funpresp"), 0f);
		assertEquals(500f, irpf.getDeducao("Carne-leao"), 0f);
		assertEquals(500f, irpf.getDeducao("PGBL"), 0f);
		assertEquals(2000f, irpf.getTotalOutrasDeducoes(), 0f);
	}
	
	@Test
	public void deducaoInexistente() {
		String nomeDeducao = "Funpresp";
		float valorDeducao = 1000f;
		irpf.cadastrarDeducaoIntegral(nomeDeducao, valorDeducao);
		
		
		assertNull(irpf.getOutrasDeducoes("Carne-leao"));
		assertEquals(0f, irpf.getDeducao("Carne-leao"), 0f);
	}
}
*/