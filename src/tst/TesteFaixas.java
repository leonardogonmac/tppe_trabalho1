package tst;

    import app.IRPF;
    import org.junit.Before;
    import org.junit.Test;
    import org.junit.runner.RunWith;
    import org.junit.runners.Parameterized;
    import org.junit.runners.Parameterized.Parameters;
    
    import java.util.Arrays;
    import java.util.Collection;
    
    import static org.junit.Assert.assertEquals;
    
    @RunWith(Parameterized.class)
    public class TesteFaixas {
    
        private float baseCalculo;
        private float impostoEsperado;
    
        private IRPF irpf;
    
        public TesteFaixas(float baseCalculo, float impostoEsperado) {
            this.baseCalculo = baseCalculo;
            this.impostoEsperado = impostoEsperado;
        }
    
        @Before
        public void setup() {
            irpf = new IRPF();
        }
    
        @Parameters
        public static Collection<Object[]> getParameters() {
            return Arrays.asList(new Object[][]{
                {6810.41f, 976.84f}, 
                {5000f, 478.99f},
                {4664.68f, 386.8f},   
                {4000f, 237.22f},    
                {3150f, 91.05f},     
                {2500f, 18.06f},
                {2259.20f, 0f},          
                {2000f, 0f}, 
                   
            });
        }
    
        @Test
        public void testeCalculoImpostoPorFaixas() {
            irpf.criarRendimento("Base de Calculo", true, baseCalculo);
            assertEquals(impostoEsperado, irpf.calcularImposto(), 0.03f);
        }
    }

 
 
 
 
 
 
    /*   @Test
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
 */   

