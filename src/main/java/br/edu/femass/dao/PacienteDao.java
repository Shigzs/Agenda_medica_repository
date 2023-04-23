package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Paciente;

public class PacienteDao extends Persist implements Dao<Paciente>{

    public PacienteDao(){
        super("Pacientes.json");
    }

    public boolean gravar(Paciente objeto) throws StreamWriteException, IOException {
        Set<Paciente> pacientes = buscar();
        boolean gravou = pacientes.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, pacientes);
        return gravou;
    }

    public boolean excluir(Paciente objeto) throws StreamWriteException, IOException {
        Set<Paciente> pacientes = buscar();
        // boolean gravou = pacientes.remove(paciente);
        for (Paciente pacienteSelecionado : pacientes) {
            if (pacienteSelecionado.equals(objeto)) {
                pacienteSelecionado.setAtivo(false);
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, pacientes);
        return true;
    }

    public Set<Paciente> buscar() throws DatabindException {
        try {
            Set<Paciente> clientes = objectMapper.readValue(arquivo, new TypeReference<Set<Paciente>>() {
            });
            Paciente.atualizarID(clientes);
            return clientes;
        } catch (IOException ex) {
            return new HashSet<Paciente>();
        }
    }

    public List<Paciente> buscarAtivos() throws DatabindException {
        Set<Paciente> pacientes = buscar();

        List<Paciente> pacientesAtivos = pacientes
                .stream().filter(paciente -> paciente.getAtivo().equals(true))
                .collect(Collectors.toList());

        return pacientesAtivos;
    }

    @Override
    public boolean atualizar(Paciente objeto) throws StreamWriteException, IOException {
        Set<Paciente> pacientes = this.buscar();
        Set<Paciente> pacientesAtualizados = new HashSet<>();
        pacientesAtualizados.add(objeto);
        for (Paciente Paciente : pacientes) {
            if (!Paciente.equals(objeto)) pacientesAtualizados.add(objeto);
        }
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, pacientesAtualizados);
        return true;
    }
}
