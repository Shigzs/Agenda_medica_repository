package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.diversos.DiversosJavafx;
import br.edu.femass.model.Especialidade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class EspecialidadeController implements Initializable {

    @FXML
    private TextField Txt_descricao;

    @FXML
    private TextField Txt_conselho;

    @FXML
    private TextField Txt_sigla;

    @FXML
    private ListView<Especialidade> Lista_especialidade;

    private EspecialidadeDao especialidadedao = new EspecialidadeDao();

    @FXML
    private void Lista_especialidade_keypressed(KeyEvent event){
        Exibir_dados();
    }

    @FXML
    private void Lista_especialidade_mouseclicked(MouseEvent event){
        Exibir_dados();
    }

    private void Exibir_dados(){
        Especialidade especialidade = Lista_especialidade.getSelectionModel().getSelectedItem();
        if(especialidade == null) return;

        Txt_descricao.setText(especialidade.getDescricao());
        Txt_conselho.setText(especialidade.getConselho());
        Txt_sigla.setText(especialidade.getSigla());
    }

    @FXML
    private void Btn_excluir(ActionEvent event) {
        Especialidade especialidade = Lista_especialidade.getSelectionModel().getSelectedItem();
        
        if(especialidade == null) return;
        
        try{
            if(!(especialidadedao.excluir(especialidade))){
                DiversosJavafx.exibirMensagem("Não foi possível excluir o cliente selecionado");
            }
            exibirEspecialidade();
        }catch(Exception e){
            e.printStackTrace();
        }

        
    }

    @FXML
    private void Btn_gravar(ActionEvent event) {
        try {
            Especialidade especialidade = new Especialidade(
                Txt_descricao.getText(),
                Txt_conselho.getText(),
                Txt_sigla.getText()
            );

            if(!(especialidadedao.gravar(especialidade))){
                DiversosJavafx.exibirMensagem("Não foi possível gravar o Plano de Saude");
            }

            Txt_descricao.setText(null);
            Txt_conselho.setText(null);
            Txt_sigla.setText(null);

            exibirEspecialidade();
            
        } catch (Exception e) {
            DiversosJavafx.exibirMensagem(e.getLocalizedMessage());
        }

    }

    public void exibirEspecialidade(){
        try{
            ObservableList<Especialidade> data2 = FXCollections.observableArrayList(
                especialidadedao.buscarAtivos()
            );
            Lista_especialidade.setItems(data2);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        exibirEspecialidade();
    }
}
