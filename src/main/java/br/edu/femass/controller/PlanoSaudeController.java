package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.PlanoSaudeDao;
import br.edu.femass.diversos.DiversosJavafx;
import br.edu.femass.model.PlanoSaude;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PlanoSaudeController implements Initializable {

    @FXML
    private TextField Txt_nome;

    @FXML
    private ListView<PlanoSaude> Lista_plano;

    private PlanoSaudeDao planosaudedao = new PlanoSaudeDao();

    @FXML
    private void Lista_plano_keypressed(KeyEvent event){
        Exibir_dados();
    }

    @FXML
    private void Lista_plano_mouseclicked(MouseEvent event){
        Exibir_dados();
    }

    private void Exibir_dados(){
        PlanoSaude plano = Lista_plano.getSelectionModel().getSelectedItem();
        if(plano == null) return;

        Txt_nome.setText(plano.getNome());
    }

    @FXML
    private void Btn_excluir(ActionEvent event) {
        PlanoSaude plano = Lista_plano.getSelectionModel().getSelectedItem();
        
        if(plano == null) return;
        
        try{
            if(!(planosaudedao.excluir(plano))){
                DiversosJavafx.exibirMensagem("Não foi possível excluir o cliente selecionado");
            }
            exibirPlano();
        }catch(Exception e){
            e.printStackTrace();
        }

        
    }

    @FXML
    private void Btn_gravar(ActionEvent event) {
        try {
            PlanoSaude plano = new PlanoSaude (
                Txt_nome.getText()
            );

            if(!(planosaudedao.gravar(plano))){
                DiversosJavafx.exibirMensagem("Não foi possível gravar o Plano de Saude");
            }

            Txt_nome.setText("");

            exibirPlano();
            
        } catch (Exception e) {
            DiversosJavafx.exibirMensagem(e.getLocalizedMessage());
        }

    }

    public void exibirPlano(){
        try{
            ObservableList<PlanoSaude> data2 = FXCollections.observableArrayList(
                planosaudedao.buscarAtivos()
            );
            Lista_plano.setItems(data2);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        exibirPlano();
    }
}
