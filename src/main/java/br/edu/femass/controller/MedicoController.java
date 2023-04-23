package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.core.exc.StreamWriteException;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.diversos.DiversosJavafx;
import br.edu.femass.model.Especialidade;
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

public class MedicoController implements Initializable {

    @FXML
    private TextField Txt_id;

    @FXML
    private TextField Txt_nome;

    @FXML
    private TextField Txt_cpf;

    @FXML
    private TextField Txt_telefone;

    @FXML
    private ComboBox<Especialidade> ComboBox_especialidade;

    @FXML
    private ListView<Medico> Lista_medico;

    @FXML
    private ListView<Especialidade> Lista_especialidade;

    private MedicoDao medicodao = new MedicoDao();
    private Dao<Especialidade> especialidadedao = new EspecialidadeDao();

    @FXML
    private void Lista_medico_keypressed(KeyEvent event) {
        Exibir_dados();
    }

    @FXML
    private void Lista_medico_mouseclicked(MouseEvent event) {
        Exibir_dados();
    }

    private void Exibir_dados() {
        Medico medico = Lista_medico.getSelectionModel().getSelectedItem();
        if (medico == null)
            return;

        Txt_cpf.setText(medico.getCpf());
        Txt_id.setText(medico.getId().toString());
        Txt_nome.setText(medico.getNome());
        Lista_especialidade.setItems(FXCollections.observableArrayList(medico.getEspecialidades()));
        Txt_telefone.setText(medico.getTelefones().get(0));
    }

    @FXML
    private void Btn_excluir(ActionEvent event) {
        Medico medico = Lista_medico.getSelectionModel().getSelectedItem();

        if (medico == null)
            return;

        try {
            if (!(medicodao.excluir(medico))) {
                DiversosJavafx.exibirMensagem("Não foi possível excluir o cliente selecionado");
            }
            exibirMedico();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Btn_gravar(ActionEvent event) throws StreamWriteException, IOException {
        if(Lista_especialidade.getItems().size() > 0){
            Medico medico = Lista_medico.getSelectionModel().getSelectedItem();
            if(medico == null){
                medico = new Medico(
                        Txt_cpf.getText(),
                        Txt_nome.getText(),
                        Txt_telefone.getText()
                );
                for(Especialidade especialidade : Lista_especialidade.getItems()){
                    medico.addEspecialidade(especialidade);
                }
                Txt_id.setText(medico.getId().toString());
                if(medicodao.gravar(medico)){
                    exibirMedico();
                }else DiversosJavafx.exibirMensagem("Não foi possível gravar o medico.");
            }else{
                medico = new Medico(
                        Txt_cpf.getText(),
                        Txt_nome.getText(),
                        Txt_telefone.getText(),
                        Long.parseLong(Txt_id.getText())
                );
                for(Especialidade especialidade : Lista_especialidade.getItems()){
                    medico.addEspecialidade(especialidade);
                }
                if(medicodao.atualizar(medico)){
                    exibirMedico();
                }else DiversosJavafx.exibirMensagem("Não foi possível atualizar o medico.");
            }
        }else{
            DiversosJavafx.exibirMensagem("É necessário cadastrar ao menos uma especialidade");
        }
    }

    public void exibirMedico() {
        try {
            ObservableList<Medico> data = FXCollections.observableArrayList(
                    medicodao.buscarAtivos());
            Lista_medico.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void exibirEspecialidade() {
        try {
            ObservableList<Especialidade> data2 = FXCollections.observableArrayList(
                    especialidadedao.buscarAtivos());
            Lista_especialidade.setItems(data2);
            ComboBox_especialidade.setItems(data2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void Btn_addEspecialidade(ActionEvent actionEvent) {
        Lista_especialidade.getItems().add(ComboBox_especialidade.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void Btn_removerEspecialidade(ActionEvent actionEvent) {
        Lista_especialidade.getItems().remove(Lista_especialidade.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        exibirMedico();
        exibirEspecialidade();
    }
}
