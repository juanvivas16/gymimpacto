package controller;

import data_model.Enrol;
import data_model.Expenses;
import db_helper.Db_connection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by juancho on 11/12/16.
 */
public class Expenses_controller implements Initializable
{
  @FXML private Label username_label;
  @FXML private String username = new String("vacio");
  @FXML private Pane pane;
  @FXML private Enrol rol;
  @FXML private TextArea description_text_area;
  @FXML private DatePicker date_datepicker;
  @FXML private TextField total_textfield;
  @FXML private Label status_label;
  @FXML private Button save_button;
  @FXML private Button exit_button;
  
  private Db_connection db = new Db_connection();
  private Expenses expenses = new Expenses();
  
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    username_label.setText(getUsername());
    date_datepicker.addEventFilter(KeyEvent.KEY_TYPED, date_Validation(10));
    total_textfield.addEventFilter(KeyEvent.KEY_TYPED, num_Validation(10));
    
    date_datepicker.setValue(LocalDate.now());
  }
  
  @FXML
  protected void handle_save_action(ActionEvent event) throws IOException
  {
    
    if(!description_text_area.getText().isEmpty())
      this.expenses.setDescription(description_text_area.getText());
    else
      status_label.setText("Error en descripcion");
    
    
    if(!date_datepicker.getValue().toString().isEmpty() || date_datepicker.getValue().isAfter(LocalDate.now()))
      this.expenses.setDate(Date.valueOf(date_datepicker.getValue()));
    else
      status_label.setText("Error en Fecha");
    
    if(!total_textfield.getText().isEmpty())
      this.expenses.setTotal(Double.parseDouble(total_textfield.getText()));
    else
      status_label.setText("Error en el monto");
    
    if(db.set_expenses_sql(expenses.getDescription(), expenses.getDate(), expenses.getTotal(), username))
    {
      System.out.println("Egreso insertado");
      if(rol.equals(Enrol.Gerente))
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
    
  }
  
  @FXML
  protected void handle_exit_action(ActionEvent event) throws IOException
  {
    if(rol.equals(Enrol.Gerente))
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
  
  
  public EventHandler<KeyEvent> num_Validation(final Integer max_Lengh)
  {
    return e -> {
      TextField txt_TextField = (TextField) e.getSource();
      if (txt_TextField.getText().length() >= max_Lengh)
      {
        e.consume();
      }
      if(e.getCharacter().matches("[0-9]"))
      {
      }
      else
      {
        e.consume();
      }
    };
  }
  
  
  public EventHandler<KeyEvent> date_Validation(final Integer max_Lengh)
  {
    return e -> {
      DatePicker txt_TextField = (DatePicker) e.getSource();
      String str = txt_TextField.getValue().toString();
      
      System.out.println(str);
      if ( str.isEmpty() )
        return;
      
      if (txt_TextField.getValue().toString().length() > max_Lengh)
      {
        e.consume();
      }
      if (str.length() == max_Lengh)
      {
        // 3/26/2016
        if (str.matches("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}") )
        {
          
        }
        else
          txt_TextField.setValue(LocalDate.parse("1970-01-01"));
      }
      
      else if (e.getCharacter().matches("[0-9/]"))
      {
      }
      else
      {
        e.consume();
      }
    };
  }
  
  
}
