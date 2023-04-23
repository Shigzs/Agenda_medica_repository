package br.edu.femass.model;

import java.util.Set;

public class Especialidade {
    private Long id;
    private String descricao;
    private String conselho;
    private String sigla;
    private Boolean ativo;

    private static Long ultimoCodigoEsp = 0L;

    public Especialidade(){
        
    }

    public Especialidade(
        String descricao,
        String conselho,
        String sigla
    ){
        this.descricao = descricao;
        this.conselho = conselho;
        this.sigla = sigla;
        this.ativo = true;

        this.id = ultimoCodigoEsp+1;
        ultimoCodigoEsp++;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getConselho() {
        return conselho;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public String getSigla() {
        return sigla;
    }

    @Override
    public String toString() {
        return getDescricao();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((conselho == null) ? 0 : conselho.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Especialidade other = (Especialidade) obj;
        if (conselho == null) {
            if (other.conselho != null)
                return false;
        } else if (!conselho.equals(other.conselho))
            return false;
        return true;
    }

    public static void atualizarID(Set<Especialidade> especialidades){
        for(Especialidade especialidade: especialidades){
            if(especialidade.getId().longValue()>ultimoCodigoEsp){
                ultimoCodigoEsp = especialidade.getId();
            }
        }
    }
}
