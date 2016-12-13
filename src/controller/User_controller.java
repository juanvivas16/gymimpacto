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
public class User_controller implements Initializable
{
  @FXML private Label username_label;
  @FXML private Pane pane;
  @FXML private String username = new String("vacio");
  @FXML private Enrol rol;
  
  private boolean is_id = true;
  private boolean is_name = false;
  
  
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    username_label.setText(getUsername());
    
  }
  
  @FXML
  protected void handle_save_user_data_button_action(ActionEvent event)
  {
    
  }
  
  @FXML
  protected void handle_search_button_action(ActionEvent event)
  {
    
  }
  
  @FXML
  protected void handle_new_user_button_action(ActionEvent event)
  {
    
  }
  
  @FXML
  protected void handle_edit_user_data_button_action(ActionEvent event)
  {
    
  }
  
  @FXML
  protected void handle_cancel_button_action(ActionEvent event) throws IOException
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
  
  @FXML protected  void handle_id_text_changed_action(ActionEvent event)
  {
    is_id = true;
    is_name = false;
  }
  
  @FXML protected  void handle_name_text_changed_action(ActionEvent event)
  {
    is_id = false;
    is_name = true;
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
