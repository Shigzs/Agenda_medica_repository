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

public class EspecialidadeDao extends Persist implements Dao<Especialidade> {

    public EspecialidadeDao(){
        super("Especialidade.json");
    }

    @Override
    public boolean gravar(Especialidade objeto) throws StreamWriteException, IOException {
        Set<Especialidade> especialidades = buscar();
        boolean gravou = especialidades.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, especialidades);
        return gravou;
    }

    @Override
    public boolean excluir(Especialidade objeto) throws StreamWriteException, IOException {
        Set<Especialidade> especialidades = buscar();
        for (Especialidade especialidadeSelecionada : especialidades) {
            if (especialidadeSelecionada.equals(objeto)) {
                especialidadeSelecionada.setAtivo(false);
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, especialidades);
        return true;
    }

    @Override
    public Set<Especialidade> buscar() throws DatabindException {
        try {
            Set<Especialidade> especialidades = objectMapper.readValue(arquivo, new TypeReference<Set<Especialidade>>() {
            });
            Especialidade.atualizarID(especialidades);
            return especialidades;
        } catch (IOException ex) {
            return new HashSet<Especialidade>();
        }
    }

    @Override
    public List<Especialidade> buscarAtivos() throws DatabindException {
        Set<Especialidade> especialidades = buscar();

        List<Especialidade> especialidadesAtivas = especialidades
                .stream().filter(especialidade -> especialidade.getAtivo().equals(true))
                .collect(Collectors.toList());

        return especialidadesAtivas;
    }

    @Override
    public boolean atualizar(Especialidade objeto) throws StreamWriteException, IOException {
        return true;
    }
    
}
