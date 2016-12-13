package controller;

import data_model.Enrol;
import db_helper.Db_connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class main_io_controller implements Initializable
{
  @FXML private TextField user_text_field;
  @FXML private TextField pass_text_field;
  @FXML private Db_connection db = new Db_connection();
  @FXML private boolean login;
  @FXML private Enrol rol;
  @FXML private Pane pane;
  
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    // todo temp setting to expedite login process.
    this.user_text_field.setText("javiers"); //gerente
    //this.user_text_field.setText("paovera"); //recepcion
    //this.user_text_field.setText("juanvivas"); //administrador
    this.pass_text_field.setText("1234");
  }
  
  @FXML
  protected void handle_login_button_action(ActionEvent event)
  {
    try
    {
      login = false;
      System.out.println("clicked Entrar");
      
      ResultSet rs = db.execute_query("SELECT username, pass, rol FROM user");
      
      while (rs.next())
      {
        String name = rs.getString("username");
        String pass = rs.getString("pass");
        rol = Enrol.valueOf(rs.getString("rol"));
        
        System.out.println("name: " + name);
        System.out.println("field: " + user_text_field.getText());
        System.out.println("rol: " + rol);
        
        if (user_text_field.getText().equals(name) && pass_text_field.getText().equals(pass))
        {
          System.out.println("User name registered");
          login = true;
          rol = Enrol.valueOf(rs.getString("rol"));
          
          if (rol.equals(Enrol.Recepcion))
          {
            try {
              
              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/reception_login_ui.fxml"));
              
              Parent root = (Parent)fxmlLoader.load();
              Reception_login_controller controller = fxmlLoader.<Reception_login_controller>getController();
              controller.setUsername(name);
              controller.setRol(rol);
              controller.initialize(null, null);
              
              Main.primary_stage.setTitle("Recepcion | Gimnasio Impacto (C) 2016");
              
              pane.getChildren().setAll(root);
              
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
          
          else if (rol.equals(Enrol.Gerente))
          {
            try {
              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/manager_login_ui.fxml"));
              
              Parent root = (Parent)fxmlLoader.load();
              Manager_login_controller controller = fxmlLoader.<Manager_login_controller>getController();
              controller.setUsername(name);
              controller.setRol(rol);
              controller.initialize(null, null);
              
              
              
              Main.primary_stage.setTitle("Gerente | Gimnasio Impacto (C) 2016");
              
              pane.getChildren().setAll(root);
              
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
          else if (rol.equals(Enrol.Administrador))
          {
            try {
              FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/admin_login_ui.fxml"));
              
              Parent root = (Parent)fxmlLoader.load();
              Admin_login_controller controller = fxmlLoader.<Admin_login_controller>getController();
              controller.setUsername(name);
              controller.setRol(rol);
              controller.initialize(null, null);
              
              Main.primary_stage.setTitle("Admin | Gimnasio Impacto (C) 2016");
              
              pane.getChildren().setAll(root);
              
            } catch (IOException e) {
              e.printStackTrace();
              
            }
          }
        }
      }
    } catch (SQLException err) {
      System.out.println(err);
    }
  }
  
  @FXML
  protected void handle_menu_item_exit_action(ActionEvent e)
  {
    System.exit(0);
  }
  
  @FXML
  protected void handle_exit_button_action(ActionEvent event) {System.exit(0);}
  
}
