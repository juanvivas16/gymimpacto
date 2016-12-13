package controller;

import data_model.Enrol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by juancho on 11/12/16.
 */
public class Supplier_controller implements Initializable
{
  @FXML private Label username_label;
  @FXML private Label status_label;
  @FXML private Pane pane;
  @FXML private String username = new String("vacio");
  @FXML private Enrol rol;
  @FXML private TextField rif_text_field;
  @FXML private TextField name_text_field;
  @FXML private TextField phone_text_field;
  @FXML private TextArea dir_text_area;
  @FXML private TextArea desc_text_area;
  @FXML private Button new_supplier_data_button;
  @FXML private Button edit_supplier_data_button;
  @FXML private Button save_button;
  
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    username_label.setText(getUsername());
    status_label.setText(" ");
    new_supplier_data_button.setDisable(true);
    edit_supplier_data_button.setDisable(true);
    save_button.setDisable(true);
  }

  @FXML
  protected void handle_save_action(ActionEvent event)
  {
    

  }
  
  @FXML
  protected void handle_search_action(ActionEvent event)
  {
    
  }
  @FXML
  protected void handle_new_supplier_button_action(ActionEvent event)
  {
    
  }
  
  @FXML
  protected void handle_edit_supplier_data_button_action(ActionEvent event)
  {
    
  }

  @FXML
  protected void handle_exit_button(ActionEvent event) throws IOException
  {
    if(rol.equals(Enrol.Recepcion))
    {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/reception_login_ui.fxml"));
    
      Parent root = (Parent) fxmlLoader.load();
      Reception_login_controller controller = fxmlLoader.<Reception_login_controller>getController();
      controller.setUsername(getUsername());
      controller.setRol(rol);
      controller.initialize(null, null);
    
      Main.primary_stage.setTitle("Recepcion | Gimnasio Impacto (C) 2016");
    
      pane.getChildren().setAll(root);
    
    } else if(rol.equals(Enrol.Gerente))
    {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/manager_login_ui.fxml"));
    
      Parent root = (Parent)fxmlLoader.load();
      Manager_login_controller controller = fxmlLoader.<Manager_login_controller>getController();
      controller.setUsername(getUsername());
      controller.setRol(rol);
      controller.initialize(null, null);
    
      Main.primary_stage.setTitle("Gerente | Gimnasio Impacto (C) 2016");
    
      pane.getChildren().setAll(root);
    } else if(rol.equals(Enrol.Administrador))
    {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/admin_login_ui.fxml"));
    
      Parent root = (Parent)fxmlLoader.load();
      Admin_login_controller controller = fxmlLoader.<Admin_login_controller>getController();
      controller.setUsername(getUsername());
      controller.setRol(rol);
      controller.initialize(null, null);
    
      Main.primary_stage.setTitle("Admin | Gimnasio Impacto (C) 2016");
    
      pane.getChildren().setAll(root);
    }
  }

  @FXML
  protected void handle_menu_item_exit_action(ActionEvent e)
  {
    System.exit(0);
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public Enrol getRol( )
  {
    return rol;
  }
  
  public void setRol(Enrol rol)
  {
    this.rol = rol;
  }
}
