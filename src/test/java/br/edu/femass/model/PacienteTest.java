package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PacienteTest {

    private Paciente paciente;
    private PlanoSaude bradesco;

    @BeforeEach
    void getUp(){
        bradesco = new PlanoSaude("Bradesco");
        paciente = new Paciente(
            "35281554000",
            "Teste", 
            "22 99999-9999",
            bradesco
        );
    }
   
    @Test
    void construtorCpfInCorreto() {
        assertThrows(
            IllegalArgumentException.class, 
            () -> new Paciente(
                "123456789", 
                "Teste", 
                "22 99999-9999",
                bradesco
            )
        );
    }

    @Test 
    void pacienteCriadoComUmTelefone() {
        assertEquals(1, paciente.getTelefones().size());

    }

    @Test
    void pacienteRemoverUmTelefone() throws Exception{
        assertThrows(
            Exception.class, 
            () -> paciente.removeTelefone("22 99999-9999")
        );        
    }

    @Test 
    void pacienteComDoisTelefones() {
        paciente.addTelefone("1111111111");
        assertEquals(2, paciente.getTelefones().size());
    }   
    
    @Test
    void clienteRemoverUmTelefoneTendoDois() throws Exception{
        paciente.addTelefone("1234567890");   
        paciente.removeTelefone("22 99999-9999");

        assertEquals(1, paciente.getTelefones().size());
       
    }
}
