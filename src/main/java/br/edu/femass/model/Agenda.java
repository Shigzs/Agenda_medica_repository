package br.edu.femass.model;

import java.util.Set;

public class Agenda {
    private Long id;
    private String data;
    private String hora;
    private Medico medico;
    private Especialidade especialidade;
    private Paciente paciente;
    private Boolean ativo;

    private static Long ultimoCodigoAgenda = 0L;

    public Agenda(){
        
    }

    public Agenda(
        String data,
        String hora,
        Medico medico,
        Especialidade especialidade,
        Paciente paciente
    ){
        this.data = data;
        this.hora = hora;
        this.medico = medico;
        this.especialidade = especialidade;
        this.paciente = paciente;
        this.id = ultimoCodigoAgenda+1;
        ultimoCodigoAgenda++;
        this.ativo = true;
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

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    @Override
    public String toString() {
        return this.paciente.getNome() + " - " + this.data + " - " + this.hora;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((hora == null) ? 0 : hora.hashCode());
        result = prime * result + ((medico == null) ? 0 : medico.hashCode());
        result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
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
        Agenda other = (Agenda) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (hora == null) {
            if (other.hora != null)
                return false;
        } else if (!hora.equals(other.hora))
            return false;
        if (medico == null) {
            if (other.medico != null)
                return false;
        } else if (!medico.equals(other.medico))
            return false;
        if (paciente == null) {
            if (other.paciente != null)
                return false;
        } else if (!paciente.equals(other.paciente))
            return false;
        return true;
    }

    public static void atualizarID(Set<Agenda> agendas){
        for(Agenda agenda: agendas){
            if(agenda.getId().longValue()>ultimoCodigoAgenda){
                ultimoCodigoAgenda = agenda.getId();
            }
        }
    }

    
}
