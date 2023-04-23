package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;

public class MedicoDao extends Persist implements Dao<Medico>{

    public MedicoDao(){
        super("Medicos.json");
    }

    public boolean gravar(Medico objeto) throws StreamWriteException, IOException {
        Set<Medico> medicos = buscar();
        boolean gravou = medicos.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
        return gravou;
    }

    public boolean gravarEspecialidade(Medico objeto, Especialidade especialidade) throws StreamWriteException, IOException {
        Set<Medico> medicos = buscar();
        boolean gravou = objeto.addEspecialidade(especialidade);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
        return gravou;
    }

    public boolean excluir(Medico objeto) throws StreamWriteException, IOException {
        Set<Medico> medicos = buscar();
        for (Medico medicoSelecionado : medicos) {
            if (medicoSelecionado.equals(objeto)) {
                medicoSelecionado.setAtivo(false);
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
        return true;
    }

    public Set<Medico> buscar() throws DatabindException {
        try {
            Set<Medico> medicos = objectMapper.readValue(arquivo, new TypeReference<Set<Medico>>() {
            });
            Medico.atualizarID(medicos);
            return medicos;
        } catch (IOException ex) {
            return new HashSet<Medico>();
        }
    }

    public List<Medico> buscarAtivos() throws DatabindException {
        Set<Medico> medicos = buscar();

        List<Medico> medicosAtivos = medicos
                .stream().filter(medico -> medico.getAtivo().equals(true))
                .collect(Collectors.toList());
        return medicosAtivos;
    }

    @Override
    public boolean atualizar(Medico objeto) throws StreamWriteException, IOException {
        Set<Medico> medicos = this.buscar();
        Set<Medico> medicosAtualizado = new HashSet<>();
        medicosAtualizado.add(objeto);
        for (Medico medico : medicos) {
            if (!medico.equals(objeto)) medicosAtualizado.add(medico);
        }
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicosAtualizado);
        return true;
    }

    public List<Medico> buscaEspecialistas(Especialidade especialidade) throws DatabindException {
        List<Medico> medicos = this.buscarAtivos();
        List<Medico> especialistas = medicos
                .stream()
                .filter(medico -> medico.getEspecialidades().contains(especialidade))
                .collect(Collectors.toList());
        return especialistas;
    }
}
