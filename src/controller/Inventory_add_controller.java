package controller;

import data_model.Enrol;
import data_model.Enstate;
import data_model.Equipment_inventory;
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
public class Inventory_add_controller implements Initializable
{
  @FXML private Label username_label;
  @FXML private Pane pane;
  @FXML private String username = new String("vacio");
  @FXML private Enrol rol;
  @FXML private Label status_label;
  @FXML private TextField name_textfield;
  @FXML private TextField model_textfield;
  @FXML private TextField quantity_textfield;
  @FXML private DatePicker ad_date_datepicker;
  @FXML private TextField cost_textfield;
  @FXML private ComboBox state_combobox;
  @FXML private TextArea description_textarea;
  @FXML private Button save_button;
  @FXML private Button exit_button;
  
  private Equipment_inventory eq_i = new Equipment_inventory();
  private Db_connection db = new Db_connection();
  
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    username_label.setText(getUsername());
    state_combobox.getItems().setAll(Enstate.values());
    state_combobox.getSelectionModel().selectFirst();
    ad_date_datepicker.setValue(LocalDate.now());
    description_textarea.setPrefColumnCount(1);
  
    name_textfield.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(20));
    model_textfield.addEventFilter(KeyEvent.KEY_TYPED, alphaTF_Validation(20));
    quantity_textfield.addEventFilter(KeyEvent.KEY_TYPED, num_Validation(3));
    ad_date_datepicker.addEventFilter(KeyEvent.KEY_TYPED, date_Validation(10));
    cost_textfield.addEventFilter(KeyEvent.KEY_TYPED, num_Validation(8));
    description_textarea.addEventFilter(KeyEvent.KEY_TYPED, alphaTA_Validation(50));
  }
  
  @FXML
  protected void handle_save_action(ActionEvent event)
  {
    this.eq_i.setName(name_textfield.getText());
    this.eq_i.setModel(model_textfield.getText());
    this.eq_i.setQuantity(Integer.parseInt(quantity_textfield.getText()));
    this.eq_i.setAd_date(Date.valueOf(ad_date_datepicker.getValue()));
    this.eq_i.setCost(Double.parseDouble(cost_textfield.getText()));
    this.eq_i.setDescription(description_textarea.getText());
    
    if(state_combobox.getValue().equals(Enstate.Activo))
      this.eq_i.setState(Enstate.Activo);
    else if(state_combobox.getValue().equals(Enstate.Inactivo))
      this.eq_i.setState(Enstate.Inactivo);
    
    if(db.set_inventory_sql(eq_i.getName(), eq_i.getModel(), eq_i.getDescription(), eq_i.getAd_date(), eq_i.getCost(), eq_i.getQuantity(), eq_i.getState(), username))
    {
      System.out.println("Inserto en inventario");
      this.status_label.setText("Inserto en inventario");
      this.save_button.setDisable(true);
    }
  }
  
  @FXML
  protected void handle_exit_action(ActionEvent event) throws IOException
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
  
  public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh)
  {
    return e -> {
      TextField txt_TextField = (TextField) e.getSource();
      if (txt_TextField.getText().length() >= max_Lengh)
      {
        e.consume();
      }
      if(e.getCharacter().matches("[A-Za-z ]"))
      {
      }
      else
      {
        e.consume();
      }
    };
  }
  
  public EventHandler<KeyEvent> num_Validation(final Integer max_Lengh)
  {
    return e -> {
      TextField txt_TextField = (TextField) e.getSource();
      if (txt_TextField.getText().length() >= max_Lengh)
      {
        e.consume();
      }
      if(e.getCharacter().matches("[0-9.]"))
      {
      }
      else
      {
        e.consume();
      }
    };
  }
  
  public EventHandler<KeyEvent> alphaTA_Validation(final Integer max_Lengh)
  {
    return e -> {
      TextArea txt_TextField = (TextArea) e.getSource();
      if (txt_TextField.getText().length() >= max_Lengh)
      {
        e.consume();
      }
      if(e.getCharacter().matches("[A-Za-z 0-9/]"))
      {
      }
      else
      {
        e.consume();
      }
    };
  }
  
  public EventHandler<KeyEvent> alphaTF_Validation(final Integer max_Lengh)
  {
    return e -> {
      TextField txt_TextField = (TextField) e.getSource();
      if (txt_TextField.getText().length() >= max_Lengh)
      {
        e.consume();
      }
      if(e.getCharacter().matches("[A-Za-z 0-9/]"))
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
