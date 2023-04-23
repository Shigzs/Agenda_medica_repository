package br.edu.femass.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.edu.femass.diversos.Validador;

public class Medico implements Serializable{
    private Long id;
    private String nome;
    private String cpf;
    private Set<Especialidade> especialidades = new HashSet<Especialidade>();
    private List<String> telefones = new ArrayList<String>();
    private Boolean ativo;
    
    private static Long ultimoCodigoMed = 0L;

    public Medico(){
        
    }

    public Medico(
        String cpf,
        String nome,
        String telefone
    ){
        if(Validador.validarCPF(cpf)==false) throw new IllegalArgumentException("CPF Invalido");
        this.cpf = cpf;
        this.nome = nome;
        this.telefones.add(telefone);
        this.ativo = true;

        this.id = ultimoCodigoMed+1;
        ultimoCodigoMed++;
    }

    public Medico(
        String cpf,
        String nome,
        String telefone,
        Long id
    ){
        if(Validador.validarCPF(cpf)==false) throw new IllegalArgumentException("CPF Invalido");
        this.cpf = cpf;
        this.nome = nome;
        this.telefones.add(telefone);
        this.ativo = true;
        this.id = id;
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

    public String getCpf(){
        return cpf;
    }

    public String getNome(){
        return nome;
    }

    public Boolean addEspecialidade(Especialidade especialidade){
        Boolean gravou = this.especialidades.add(especialidade);
        return gravou;
    }

    public void removeEspecialidade(Especialidade especialidade) throws Exception{
        if(especialidades.size()==1){
            throw new Exception("O Médico deve ter pelo menos 1 especialidade");
        }
        this.especialidades.add(especialidade);
    }

    public Set<Especialidade> getEspecialidades() {
        return especialidades;
    }

    public void addTelefone(String telefone){
        this.telefones.add(telefone);
    }
    
    public void removeTelefone(String telefone) throws Exception{
        if (telefones.size()==1){
            throw new Exception("O Médico deve ter pelo menos 1 telefone");
        }
        this.telefones.remove(telefone);
    }

    public List<String> getTelefones() {
        return telefones;
    }

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
        Medico other = (Medico) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    public static void atualizarID(Set<Medico> medicos){
        for(Medico medico: medicos){
            if(medico.getId().longValue()>ultimoCodigoMed){
                ultimoCodigoMed = medico.getId();
            }
        }
    }
}
