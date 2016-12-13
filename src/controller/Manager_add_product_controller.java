package controller;

import data_model.Enrol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by juancho on 11/12/16.
 */
public class Manager_add_product_controller implements Initializable
{
  @FXML private Label username_label;
  @FXML private Pane pane;
  @FXML private String username = new String("vacio");
  @FXML private Enrol rol;

  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    username_label.setText(getUsername());
  }

  @FXML
  protected void handle_edit_product_action(ActionEvent event)
  {

  }

  @FXML
  protected void handle_new_product_action(ActionEvent event)
  {

  }

  @FXML
  protected void handle_search_code_action(ActionEvent event)
  {

  }

  @FXML
  protected void handle_back_action(ActionEvent event) throws IOException
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
