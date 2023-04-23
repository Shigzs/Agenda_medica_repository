package br.edu.femass.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MedicoTest {
    private Medico medico;
    private Especialidade especialidade;
    private Especialidade especialidade2;

    @BeforeEach
    void getUp(){
        especialidade = new Especialidade(
            "Dermatologista", 
            "21011", 
            "CRM"
        );
        medico = new Medico(
            "35281554000",
            "MedicoTest",
            "22 99999-9999"
        );
        medico.addEspecialidade(especialidade);
    }

    @Test
    void construtorCpfInCorreto() {
        assertThrows(
            IllegalArgumentException.class, 
            () -> new Medico(
                "3528154000",
                "MedicoTest",
                "22 12345-6789"
            )
        );
    }

    @Test 
    void medicoCriadoComUmTelefone() {
        assertEquals(1, medico.getTelefones().size());
    }

    @Test
    void medicoRemoverUmTelefone() throws Exception{
        assertThrows(
            Exception.class, 
            () -> medico.removeTelefone("22 99999-9999")
        );        
    } 

    @Test 
    void medicoComDoisTelefones() {
        medico.addTelefone("1111111111");
        assertEquals(2, medico.getTelefones().size());
    }   
    
    @Test
    void medicoRemoverUmTelefoneTendoDois() throws Exception{
        medico.addTelefone("1234567890");   
        medico.removeTelefone("22 99999-9999");

        assertEquals(1, medico.getTelefones().size());
       
    }

    @Test 
    void medicoCriadoComUmaEspecialidade() {
        assertEquals(1, medico.getEspecialidades().size());
    }

    @Test
    void medicoRemoverUmaEspecialidade() throws Exception{
        assertThrows(
            Exception.class, 
            () -> medico.removeEspecialidade(especialidade)
        );        
    } 

    @Test 
    void medicoComDuasEspecialidades() {
        especialidade2 = new Especialidade(
            "Dermatologista", 
            "21011", 
            "CRM"
    );
        medico.addEspecialidade(especialidade2);
        assertEquals(1, medico.getEspecialidades().size());
    }   
}
