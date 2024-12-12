package tst;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import app.IRPF;

public class TesteFaixas {
    IRPF irpf;

    @Before
    public void setup() {
        irpf = new IRPF();
    }

    @Test
    public void testeSemRendimentosTributaveis() {
        assertEquals(0.0f, irpf.calcularImposto(), 0.01f);
    }


    @Test
    public void testeFaixaIsenta() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 2000f); // Base < 2259.20
        assertEquals(0.0f, irpf.calcularImposto(), 0.01f);
    }

    @Test
    public void testeSegundaFaixa() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 2500f); // Base dentro da segunda faixa
        assertEquals((2500f - 2259.20f) * 0.075f, irpf.calcularImposto(), 0.01f);
    }

    @Test
    public void testeTerceiraFaixa() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 3000f); // Base dentro da terceira faixa
        float expected = (2826.65f - 2259.20f) * 0.075f + (3000f - 2826.65f) * 0.15f;
        assertEquals(expected, irpf.calcularImposto(), 0.01f);
    }

    @Test
    public void testeQuartaFaixa() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 4000f); // Base dentro da quarta faixa
        float expected = (2826.65f - 2259.20f) * 0.075f + 
                         (3751.05f - 2826.65f) * 0.15f + 
                         (4000f - 3751.05f) * 0.225f;
        assertEquals(expected, irpf.calcularImposto(), 0.01f);
    }

    @Test
    public void testeQuintaFaixa() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 5000f); // Base dentro da quinta faixa
        float expected = (2826.65f - 2259.20f) * 0.075f + 
                         (3751.05f - 2826.65f) * 0.15f + 
                         (4664.68f - 3751.05f) * 0.225f + 
                         (5000f - 4664.68f) * 0.275f;
        assertEquals(expected, irpf.calcularImposto(), 0.01f);
    }

    @Test
    public void testeBordaFaixaIsenta() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 2259.20f); // Exatamente no limite da faixa isenta
        assertEquals(0.0f, irpf.calcularImposto(), 0.01f);
    }

    @Test
    public void testeBordaFaixaQuinta() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 4664.68f); // Exatamente no limite da quarta para quinta faixa
        float expected = (2826.65f - 2259.20f) * 0.075f + 
                         (3751.05f - 2826.65f) * 0.15f + 
                         (4664.68f - 3751.05f) * 0.225f;
        assertEquals(expected, irpf.calcularImposto(), 0.01f);
    }
}
