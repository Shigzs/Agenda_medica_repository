package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AgendaTest {

    private Agenda agenda;
    private Medico medico;
    private Especialidade especialidade;
    private Paciente paciente;

    @BeforeEach
    void getUp(){
        agenda = new Agenda(
        "14/10", 
        "9:30", 
        medico, 
        especialidade, 
        paciente
        );
    }

    @Test
    void testEquals() {
        Agenda agenda2 = new Agenda(
            "14/10", 
            "9:30", 
            medico, 
            especialidade, 
            paciente
        );
        assertEquals(true, agenda.equals(agenda2));
    }

    @Test
    void testGetAtivo() {
        assertEquals(true, agenda.getAtivo());
    }

    @Test
    void testGetData() {
        assertEquals("14/10", agenda.getData());
    }

    @Test
    void testGetEspecialidade() {
        assertEquals(especialidade, agenda.getEspecialidade());
    }

    @Test
    void testGetHora() {
        assertEquals("9:30", agenda.getHora());
    }

    @Test
    void testGetMedico() {
        assertEquals(medico, agenda.getMedico());
    }

    @Test
    void testGetPaciente() {
        assertEquals(paciente, agenda.getPaciente());
    }
}
