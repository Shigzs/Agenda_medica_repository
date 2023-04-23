package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.dao.PlanoSaudeDao;
import br.edu.femass.diversos.DiversosJavafx;
import br.edu.femass.model.Paciente;
import br.edu.femass.model.PlanoSaude;
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

public class PacienteController implements Initializable {

    @FXML
    private TextField Txt_id;

    @FXML
    private TextField Txt_nome;

    @FXML
    private TextField Txt_cpf;

    @FXML
    private TextField Txt_telefone;

    @FXML
    private TextField Txt_email;

    @FXML
    private ComboBox<PlanoSaude> ComboBox_plano;

    @FXML
    private ListView<Paciente> Lista_paciente;

    private PacienteDao pacientedao = new PacienteDao();
    private Dao<PlanoSaude> planosaudedao = new PlanoSaudeDao();

    @FXML
    private void Lista_paciente_keypressed(KeyEvent event){
        Exibir_dados();
    }

    @FXML
    private void Lista_paciente_mouseclicked(MouseEvent event){
        Exibir_dados();
    }

    private void Exibir_dados(){
        Paciente paciente = Lista_paciente.getSelectionModel().getSelectedItem();
        if(paciente == null) return;

        Txt_cpf.setText(paciente.getCpf());
        Txt_email.setText(paciente.getEmail());
        Txt_id.setText(paciente.getID().toString());
        Txt_nome.setText(paciente.getNome());
        ComboBox_plano.getSelectionModel().select(paciente.getPlano());
        Txt_telefone.setText(paciente.getTelefones().get(0));
    }

    @FXML
    private void Btn_excluir(ActionEvent event) {
        Paciente paciente = Lista_paciente.getSelectionModel().getSelectedItem();
        
        if(paciente == null) return;
        
        try{
            if(!(pacientedao.excluir(paciente))){
                DiversosJavafx.exibirMensagem("Não foi possível excluir o cliente selecionado");
            }
            exibirPaciente();
        }catch(Exception e){
            e.printStackTrace();
        }

        
    }

    @FXML
    private void Btn_gravar(ActionEvent event) {
        try {
            Paciente paciente = new Paciente(
                Txt_cpf.getText(),
                Txt_nome.getText(),
                Txt_telefone.getText(),
                ComboBox_plano.getSelectionModel().getSelectedItem()
            );
            paciente.setEmail(Txt_email.getText());

            Txt_id.setText(paciente.getID().toString());

            if(!(pacientedao.gravar(paciente))){
                DiversosJavafx.exibirMensagem("Não foi possível gravar o cliente");
            }

            Txt_cpf.setText("");
            Txt_email.setText("");
            Txt_id.setText("");
            Txt_nome.setText("");
            Txt_telefone.setText("");
            ComboBox_plano.getSelectionModel().select(null);

            exibirPaciente();
            
        } catch (Exception e) {
            DiversosJavafx.exibirMensagem(e.getLocalizedMessage());
        }

    }

    public void exibirPaciente(){
        try{
            ObservableList<Paciente> data = FXCollections.observableArrayList(
                pacientedao.buscarAtivos()
            );
            Lista_paciente.setItems(data);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void exibirPlano(){
        try{
            ObservableList<PlanoSaude> data2 = FXCollections.observableArrayList(
                planosaudedao.buscarAtivos()
            );
            ComboBox_plano.setItems(data2);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        exibirPaciente();
        exibirPlano();
    }
}
