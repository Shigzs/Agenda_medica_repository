package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EspecialidadeTest {

    private Especialidade especialidade;

    @BeforeEach
    void getUp(){
        especialidade = new Especialidade(
        "Dermatologista",
        "2111", 
        "CRM"
        );
    }

    @Test
    void testEquals() {
        Especialidade especialidade2 = new Especialidade(
            "Dermatologista",
            "2111", 
            "CRM"
        );
        assertEquals(true, especialidade.equals(especialidade2));
    }

    @Test
    void testGetAtivo() {
        assertEquals(true, especialidade.getAtivo());
    }

    @Test
    void testGetConselho() {
        assertEquals("2111", especialidade.getConselho());
    }

    @Test
    void testGetDescricao() {
        assertEquals("Dermatologista", especialidade.getDescricao());
    }

    @Test
    void testGetSigla() {
        assertEquals("CRM", especialidade.getSigla());
    }
}
