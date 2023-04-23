package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController implements Initializable {
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Paciente.fxml"));
            
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            
            stage.setTitle("Paciente");
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleBtnPlanoSaudeAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/PlanoSaude.fxml"));
            
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            
            stage.setTitle("Plano de Saude");
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleBtnMedicoAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Medico.fxml"));
            
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            
            stage.setTitle("Medico");
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleBtnEspecialidadeAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Especialidade.fxml"));
            
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            
            stage.setTitle("Especialidade");
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleBtnAgendaAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Agenda.fxml"));
            
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            
            stage.setTitle("Agendamentos");
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleBtnAgendaShowAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/AgendaShow.fxml"));
            
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            
            stage.setTitle("Agendamentos");
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
}
