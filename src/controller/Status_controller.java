package controller;

import data_model.Enrol;
import data_model.Service;
import data_model.Service_type;
import db_helper.Db_connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

/**
 * Created by juancho on 11/12/16.
 */
public class Status_controller implements Initializable
{
  @FXML private Pane pane;
  @FXML private Label username_label;
  @FXML private Label init_date_label;
  @FXML private Label end_service_label;
  @FXML private Label service_type_label;
  @FXML private TextField search_textfield;
  @FXML private Label status_label;
  
  @FXML private String username = new String("vacio");
  @FXML private Enrol rol;
  private Db_connection db = new Db_connection();
  private boolean is_id = true;
  Service service = new Service();
  
  
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    username_label.setText(getUsername());
    init_date_label.setText(" ");
    end_service_label.setText(" ");
    service_type_label.setText(" ");
    status_label.setText(" ");
  }
  
  @FXML
  protected void handle_search_action(ActionEvent event) throws SQLException
  {
    if(is_id)
    {
      String ci = search_textfield.getText();
      Service tmp_service = new Service();
      tmp_service = db.search_service_by_customer_id(ci);
      System.out.println(tmp_service);
      
      if(tmp_service != null)
      {
        this.service = tmp_service;
        this.init_date_label.setText(service.getInit_date().toString());
        this.service_type_label.setText(service.getType_s().toString());
        
        if(service.getType_s().equals(Service_type.Quincenal))
        {
          LocalDate end = service.getInit_date().toLocalDate().plusDays(15);
          this.end_service_label.setText(end.toString());
          
          Period period = Period.between(LocalDate.now(), end);
          
          if(period.getDays() > 0)
            this.status_label.setText("Quedan " + period.getDays() +"para culminar el servicio.");
          else
            this.status_label.setText("Servicio caducado.");
          
        } else if(service.getType_s().equals(Service_type.Mensual))
        {
          LocalDate end = service.getInit_date().toLocalDate().plusDays(30);
          this.end_service_label.setText(end.toString());
          
          Period period = Period.between(LocalDate.now(), end);
          
          if(period.getDays() > 0)
            this.status_label.setText("Quedan " + period.getDays() +"para culminar el servicio.");
          else
            this.status_label.setText("Servicio caducado.");
          
        }else if(service.getType_s().equals(Service_type.Diario))
        {
          LocalDate end = service.getInit_date().toLocalDate().plusDays(1);
          this.end_service_label.setText(end.toString());
          
          Period period = Period.between(LocalDate.now(), end);
          
          if(period.getDays() > 0)
            this.status_label.setText("Quedan " + period.getDays() +"para culminar el servicio.");
          else
            this.status_label.setText("Servicio caducado.");
        }
      }
    }
  }
  
  @FXML protected  void handle_id_text_changed_action(ActionEvent event)
  {
    is_id = true;
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
  protected void handle_menu_item_exit_action(ActionEvent e) {System.exit(0);}
  
  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
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
