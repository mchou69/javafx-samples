/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import com.sun.media.jfxmedia.logging.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mchouarbi
 */
public class MainController implements Initializable {
    @FXML
    private AnchorPane main;
    
    Stage stage =null;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    private void close(){
        try {
             stage = (Stage) main.getScene().getWindow();     
             stage.close();
        } catch (Exception e) {
            Logger.logMsg(Logger.ERROR,"Error : "+e);
        }
    }
    public void doExit(ActionEvent event){
        close();   
    }
    public void doLogin(ActionEvent event){
        
    }
    
}
