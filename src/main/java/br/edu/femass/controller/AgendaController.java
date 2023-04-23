package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.AgendaDao;
import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.diversos.DiversosJavafx;
import br.edu.femass.model.Agenda;
import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;
import br.edu.femass.model.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AgendaController implements Initializable {

    @FXML
    private TextField Txt_data;

    @FXML
    private TextField Txt_hora;

    @FXML
    private TextField Txt_paciente;

    @FXML
    private ListView<Agenda> Lista_agenda;

    @FXML
    private ComboBox<Medico> ComboBox_medico;

    @FXML
    private ComboBox<Paciente> ComboBox_paciente;

    @FXML
    private ComboBox<Especialidade> ComboBox_especialidade;

    private AgendaDao agendadao = new AgendaDao();
    private PacienteDao pacientedao = new PacienteDao();
    private MedicoDao medicodao = new MedicoDao();
    private EspecialidadeDao especialidadedao = new EspecialidadeDao();


    @FXML
    private void Lista_agenda_keypressed(KeyEvent event){
        Exibir_dados();
    }

    @FXML
    private void Lista_agenda_mouseclicked(MouseEvent event){
        Exibir_dados();
    }

    private void Exibir_dados(){
        Agenda agenda = Lista_agenda.getSelectionModel().getSelectedItem();
        if(agenda == null) return;

        Txt_data.setText(agenda.getData());
        Txt_hora.setText(agenda.getHora());
        ComboBox_paciente.getSelectionModel().select(agenda.getPaciente());
        ComboBox_medico.getSelectionModel().select(agenda.getMedico());
        ComboBox_especialidade.getSelectionModel().select(agenda.getEspecialidade());
    }

    @FXML
    private void Btn_excluir(ActionEvent event) {
        Agenda agenda = Lista_agenda.getSelectionModel().getSelectedItem();
        
        if(agenda == null) return;
        
        try{
            if(!(agendadao.excluir(agenda))){
                DiversosJavafx.exibirMensagem("Não foi possível excluir o cliente selecionado");
            }
            exibirAgenda();
        }catch(Exception e){
            e.printStackTrace();
        }

        
    }

    @FXML
    private void Btn_gravar(ActionEvent event) {
        try {
            Agenda agenda = new Agenda(
                Txt_data.getText(),
                Txt_hora.getText(),
                ComboBox_medico.getSelectionModel().getSelectedItem(),
                ComboBox_especialidade.getSelectionModel().getSelectedItem(),
                ComboBox_paciente.getSelectionModel().getSelectedItem()
            );

            if(!(agendadao.gravar(agenda))){
                DiversosJavafx.exibirMensagem("Não foi possível gravar o agendamento");
            }

            Txt_data.setText("");
            Txt_hora.setText("");
            ComboBox_medico.getSelectionModel().select(null);
            ComboBox_especialidade.getSelectionModel().select(null);
            ComboBox_paciente.getSelectionModel().select(null);

            exibirAgenda();
            
        } catch (Exception e) {
            DiversosJavafx.exibirMensagem(e.getLocalizedMessage());
        }

    }

    public void exibirAgenda(){
        try{
            ObservableList<Agenda> data = FXCollections.observableArrayList(
                agendadao.buscarAtivos()
            );
            Lista_agenda.setItems(data);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void exibirPaciente(){
        try{
            ObservableList<Paciente> data2 = FXCollections.observableArrayList(
                pacientedao.buscarAtivos()
            );
            ComboBox_paciente.setItems(data2);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void exibirMedico(Especialidade especialidade){
        try{
            ObservableList<Medico> data2 = FXCollections.observableArrayList(
                medicodao.buscaEspecialistas(especialidade)
            );
            ComboBox_medico.setItems(data2);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    public void ComboBox_especialidade_select(ActionEvent actionEvent) {
        Especialidade especialidade = ComboBox_especialidade.getSelectionModel().getSelectedItem();
        if (especialidade == null) return;

        exibirMedico(especialidade);
    }

    public void exibirEspecialidade(){
        try{
            ObservableList<Especialidade> data2 = FXCollections.observableArrayList(
                especialidadedao.buscarAtivos()
            );
            ComboBox_especialidade.setItems(data2);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        exibirAgenda();
        exibirPaciente();
        exibirEspecialidade();
    }
}
