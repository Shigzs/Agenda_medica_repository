package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlanoSaudeTest {

    private PlanoSaude plano;

    @BeforeEach
    void getUp(){
        plano = new PlanoSaude("Bradesco");
    }

    @Test
    void testEquals() {
        PlanoSaude plano2 = new PlanoSaude("Bradesco");
        assertEquals(true, plano.equals(plano2));
    }

    @Test
    void testGetAtivo() {
        assertEquals(true, plano.getAtivo());
    }

    @Test
    void testGetNome() {
        assertEquals("Bradesco", plano.getNome());
    }

    @Test
    void testSetAtivo() {
        plano.setAtivo(false);
        assertEquals(false, plano.getAtivo());
    }
}
