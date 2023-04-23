package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.edu.femass.diversos.Validador;

public class Paciente {
    private Long id;
    private String cpf;
    private String nome;
    private List<String> telefones = new ArrayList<String>();
    private String email;
    private PlanoSaude plano;
    private Boolean ativo;

    private static Long ultimoCodigo = 0L;

    public Paciente(){

    }
    public Paciente(
        String cpf,
        String nome,
        String telefone,
        PlanoSaude plano
    ){
        if(Validador.validarCPF(cpf)==false) throw new IllegalArgumentException("CPF Invalido");
        this.cpf = cpf;
        this.nome = nome;
        this.telefones.add(telefone);
        this.plano = plano;
        this.id = ultimoCodigo+1;
        ultimoCodigo++;
        this.ativo = true;
    }
    
    public Boolean getAtivo() {
        return ativo;
    }
    
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Long getID(){
        return id;
    }

    public String getCpf(){
        return cpf;
    }

    public String getNome(){
        return nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public PlanoSaude getPlano() {
        return plano;
    }

    public void setPlano(PlanoSaude plano) {
        this.plano = plano;
    }

    public void addTelefone(String telefone){
        this.telefones.add(telefone);
    }
    
    public void removeTelefone(String telefone) throws Exception{
        if (telefones.size()==1){
            throw new Exception("O cliente deve ter pelo menos 1 telefone");
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
        result = prime * result + ((id == null) ? 0 : cpf.hashCode());
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
        Paciente other = (Paciente) obj;
        if (id == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }

    public static void atualizarID(Set<Paciente> pacientes){
        for(Paciente paciente: pacientes){
            if(paciente.getID().longValue()>ultimoCodigo){
                ultimoCodigo = paciente.getID();
            }
        }
    }
}
