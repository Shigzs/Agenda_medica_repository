package br.edu.femass.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import br.edu.femass.dao.AgendaDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.diversos.DiversosJavafx;
import br.edu.femass.model.Agenda;
import br.edu.femass.model.Medico;
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

public class AgendaShowController implements Initializable {

    @FXML
    private ListView<Agenda> Lista_agenda;

    @FXML
    private ComboBox<Medico> ComboBox_medico;

    @FXML
    private TextField Txt_dataPesquisa;

    @FXML
    private TextField Txt_medico;

    @FXML
    private TextField Txt_especialidade;

    @FXML
    private TextField Txt_paciente;

   @FXML
    private TextField Txt_data;

    @FXML
    private TextField Txt_hora;

    private String filtro;

    private AgendaDao agendadao = new AgendaDao();
    private MedicoDao medicodao = new MedicoDao();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exibir_Medicos();
    }

    @FXML
    public void Lista_agenda_keypressed(KeyEvent keyEvent) {
        exibir_Dados();
    }

    @FXML
    public void Lista_agenda_mouseclicked(MouseEvent event) {
        exibir_Dados();
    }


    @FXML
    public void pesquisar_data(ActionEvent actionEvent) {
        if (Txt_dataPesquisa.getText(0, 0) == null || Txt_dataPesquisa.getText(0, 0).equals(""))
            return;

        try {
            filtro = "Data";
            Txt_medico.setText(null);
            Txt_dataPesquisa.setText(null);
            Txt_hora.setText(null);
            Txt_paciente.setText(null);
            Txt_especialidade.setText(null);
            exibir_Agendas();
        } catch (Exception e) {
            e.printStackTrace();
            DiversosJavafx.exibirMensagem("Não foi possível buscar as agendas da data escolhida");
        }
    }

    @FXML
    public void pesquisar_medico(ActionEvent actionEvent) {
        Medico medico = ComboBox_medico.getSelectionModel().getSelectedItem();
        if (medico == null) return;

        try {
            filtro = "Médico";
            Txt_medico.setText(null);
            Txt_dataPesquisa.setText(null);
            Txt_hora.setText(null);
            Txt_paciente.setText(null);
            Txt_especialidade.setText(null);
            exibir_Agendas();
        } catch (Exception e) {
            e.printStackTrace();
            DiversosJavafx.exibirMensagem("Não foi possível buscar as agendas do médico selecionado");
        }
    }

    private void exibir_Agendas() {
        try {
            List<Agenda> agendas = agendadao.buscarAtivos();
            List<Agenda> agendasFiltradas = new ArrayList<>();
            if (filtro.equals("Data")) {
                agendasFiltradas = agendas
                        .stream()
                        .filter(agenda -> agenda.getData().equals(Txt_dataPesquisa.getText()))
                        .collect(Collectors.toList());
            } else if(filtro.equals("Médico")){
                agendasFiltradas = agendas
                        .stream()
                        .filter(agenda -> agenda.getMedico().equals(ComboBox_medico.getSelectionModel().getSelectedItem()))
                        .collect(Collectors.toList());
            }else agendasFiltradas = agendas;

            ObservableList<Agenda> list = FXCollections.observableArrayList(
                    agendasFiltradas
            );
            Lista_agenda.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
            DiversosJavafx.exibirMensagem("Não foi possível buscar as agendas da data selecionada");
        }
    }


    private void exibir_Medicos() {
        try {
            ObservableList<Medico> observableList = FXCollections.observableArrayList(
                    medicodao.buscarAtivos()
            );
            ComboBox_medico.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
            DiversosJavafx.exibirMensagem("Não foi possível buscar os médicos cadastrados");
        }
    }

    private void exibir_Dados() {
        Agenda agenda = Lista_agenda.getSelectionModel().getSelectedItem();
        if (agenda == null) return;

        Txt_medico.setText(agenda.getMedico().getNome());
        Txt_data.setText(agenda.getData());
        Txt_hora.setText(agenda.getHora());
        Txt_paciente.setText(agenda.getPaciente().getNome());
        Txt_especialidade.setText(agenda.getEspecialidade().getDescricao());
    }
}
