package br.edu.femass.model;

public class PlanoSaude {
    private String nome;
    private Boolean ativo;

    public PlanoSaude(){
        
    }

    public PlanoSaude(
        String nome
    ){
        this.nome = nome;
        this.ativo = true;
    }

    public String getNome() {
        return nome;
    }
    
    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
        PlanoSaude other = (PlanoSaude) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }
}
