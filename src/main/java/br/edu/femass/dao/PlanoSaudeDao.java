package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.PlanoSaude;

public class PlanoSaudeDao extends Persist implements Dao<PlanoSaude> {

    public PlanoSaudeDao(){
        super("Planos.json");
    }

    @Override
    public boolean gravar(PlanoSaude objeto) throws StreamWriteException, IOException {
        Set<PlanoSaude> planos = buscar();
        boolean gravou = planos.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
        return gravou;
    }

    @Override
    public boolean excluir(PlanoSaude objeto) throws StreamWriteException, IOException {
        Set<PlanoSaude> planos = buscar();
        for (PlanoSaude planoSelecionado : planos) {
            if (planoSelecionado.equals(objeto)) {
                planoSelecionado.setAtivo(false);
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planos);
        return true;
    }

    @Override
    public Set<PlanoSaude> buscar() throws DatabindException {
        try {
            Set<PlanoSaude> planos = objectMapper.readValue(arquivo, new TypeReference<Set<PlanoSaude>>() {
            });
            return planos;
        } catch (IOException ex) {
            return new HashSet<PlanoSaude>();
        }
    }

    @Override
    public List<PlanoSaude> buscarAtivos() throws DatabindException {
        Set<PlanoSaude> planos = buscar();

        List<PlanoSaude> planosAtivos = planos
                .stream().filter(plano -> plano.getAtivo().equals(true))
                .collect(Collectors.toList());

        return planosAtivos;
    }
    
    @Override
    public boolean atualizar(PlanoSaude objeto) throws StreamWriteException, IOException {
        return true;
    }
}
