package tst;

import static org.junit.Assert.*;

import java.beans.Transient;

import org.junit.Before;
import org.junit.Test;

import app.IRPF;

public class TesteAliquotaEfetiva {
    IRPF irpf;

    @Before
    public void setUp() {
        irpf = new IRPF();
    }

    @Test
    public void testeAliquotaEfetivaExemplo() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 8000f);
        irpf.criarRendimento("Aluguel", IRPF.TRIBUTAVEL, 2000f);
        irpf.criarRendimento("Bolsa", IRPF.NAOTRIBUTAVEL, 1500f);
        irpf.cadastrarContribuicaoPrevidenciaria(500f);
        irpf.cadastrarContribuicaoPrevidenciaria(1000f);
        irpf.cadastrarDependente("João", "Filho");
        irpf.cadastrarPensaoAlimenticia("João", 1500f);

        assertEquals(0.097f, irpf.calcularAliquotaEfetiva(), 0.01f);
    }

    @Test
    public void testeAliquotaEfetivaPrimeiraFaixa() {
        irpf.criarRendimento("Salário", IRPF.TRIBUTAVEL, 2259.19f);
        assertEquals(0.0f, irpf.calcularAliquotaEfetiva(), 0.00f);
    }

    @Test
    public void testeAliquotaEfetivaSemRendimentos() {
        assertEquals(0.0f, irpf.calcularAliquotaEfetiva(), 0.00f);
    }
    
}
