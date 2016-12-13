package controller;

import data_model.Customer;
import data_model.Engender;
import data_model.Enrol;
import db_helper.Db_connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import main.Main;


/**
 * Created by juancho on 11/12/16.
 */
public class Customer_controller implements Initializable
{
  @FXML private Label username_label;
  @FXML private Label status_label;
  @FXML private Button search_button;
  @FXML private Button new_customer_data_button;
  @FXML private Button edit_customer_data_button;
  @FXML private Button save_customer_data_button;
  @FXML private Button back_button;
  @FXML private TextField id_text_field;
  @FXML private TextField name_text_field;
  @FXML private TextField last_name_text_field;
  @FXML private DatePicker birth_date_date_picker;
  @FXML private TextField telephone_text_field;
  @FXML private ComboBox gender_combo_box;
  @FXML private TextArea direction_text_area;
  @FXML private Db_connection db = new Db_connection();
  
  @FXML private Pane pane;
  @FXML private String username = new String("vacio");
  
  private Customer customer = new Customer();
  private boolean is_id = true;
  private boolean is_name = false;
  private boolean is_new_customer = false;
  private Enrol rol;
  
  final Tooltip id_tooltip = new Tooltip();
  
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    username_label.setText(getUsername());
    
    id_text_field.addEventFilter(KeyEvent.KEY_TYPED, ci_Validation(10));
    name_text_field.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(20));
    last_name_text_field.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(20));
    birth_date_date_picker.addEventFilter(KeyEvent.KEY_TYPED, date_Validation(10));
    telephone_text_field.addEventFilter(KeyEvent.KEY_TYPED, phone_Validation(12));
    direction_text_area.addEventFilter(KeyEvent.KEY_TYPED, direction_Validation(50));
    
    id_tooltip.setText("La cedula debe tener empeza con V,E y debe tener \n" + "almenos 6 numeros y maximo 8\n" );
    id_text_field.setTooltip(id_tooltip);
    
    gender_combo_box.getItems().setAll(Engender.values());
    
    this.gender_combo_box.setDisable(true);
    this.direction_text_area.setDisable(true);
    this.gender_combo_box.getSelectionModel().selectFirst();
  }
  
  @FXML
  protected void handle_save_customer_data_button_action(ActionEvent event)
  {
    
    if(checkAlpha(name_text_field.getText()))
      this.customer.setName(name_text_field.getText());
    else
      this.status_label.setText("¡Error en nombre!");
    
    if(checkAlpha(last_name_text_field.getText()))
      this.customer.setLast_name(last_name_text_field.getText());
    else
      this.status_label.setText("¡Error en Apellido!");
    
    this.customer.setDir(direction_text_area.getText());
    
    if(checkPhone(telephone_text_field.getText()))
      this.customer.setPhone(telephone_text_field.getText());
    else
      this.status_label.setText("¡Error en Telefono!");
    
    this.customer.setBirth_date(Date.valueOf(birth_date_date_picker.getValue()));
    
    String temp_gender = this.gender_combo_box.getSelectionModel().getSelectedItem().toString();
    Engender gender = Engender.valueOf(temp_gender);
    if(temp_gender.equals("Masculino"))
      this.customer.setGender(gender);
    else if (temp_gender.equals("Femenino"))
      this.customer.setGender(gender);
    
    if (this.is_new_customer)
    {
      if(checkCI(id_text_field.getText()))
        this.customer.setCi(id_text_field.getText());
      
      this.customer.setInit_date(Date.valueOf(LocalDate.now()));
      
      if(customer.getPhone().isEmpty() || customer.getPhone() == null)
        System.out.println(customer.getCi() +" "+ customer.getName() +" "+ customer.getLast_name() +" "+ customer.getGender() +" "+ customer.getBirth_date() +" "+ customer.getDir() +" "+ customer.getPhone() +" "+ customer.getInit_date());
      
      if(db.set_customer_sql(customer.getCi(), customer.getName(), customer.getLast_name(), customer.getBirth_date(), customer.getGender(), customer.getDir(), customer.getPhone(), customer.getInit_date()))
        System.out.println("Inserto ok");
      
      if(checkPhone(telephone_text_field.getText()) && checkAlpha(name_text_field.getText()) && checkAlpha(last_name_text_field.getText()) && checkCI(id_text_field.getText()))
        this.status_label.setText("¡Cliente guardado con éxito!");
      else
        this.status_label.setText("Error al guardar. Verificar campos");
      
      
      //disable camps
      this.name_text_field.setDisable(true);
      this.last_name_text_field.setDisable(true);
      this.direction_text_area.setDisable(true);
      this.telephone_text_field.setDisable(true);
      this.birth_date_date_picker.setDisable(true);
      this.gender_combo_box.setDisable(true);
      this.save_customer_data_button.setDisable(true);
      this.new_customer_data_button.setDisable(true);
      this.edit_customer_data_button.setDisable(false);
      
      
      
    }
    else
    {
      String query = "UPDATE customer SET id=" + id_text_field.getText() +
          ", name='" + customer.getName() + "'" +
          ", last_name='" + customer.getLast_name() + "'" +
          ", gender = '" + customer.getGender() + "'" +
          ", birth_date = '" + customer.getBirth_date() + "'" +
          ", direction = '" + customer.getDir() + "'" +
          ", phone_num = '" + customer.getPhone() + "'" +
          "WHERE customer.id=" + customer.getCi();
      
      int a = db.execute_update(query);
      
      if(checkCI(id_text_field.getText()))
        this.customer.setCi(id_text_field.getText());
      
      if(checkPhone(telephone_text_field.getText()) && checkAlpha(name_text_field.getText()) && checkAlpha(last_name_text_field.getText()) && checkCI(id_text_field.getText()))
        this.status_label.setText("¡Editado con éxito!");
      else
        this.status_label.setText("Error al editar. Verificar campos");
    }
    
    this.id_text_field.setDisable(false);
  }
  
  @FXML
  protected void handle_search_button_action(ActionEvent event) throws SQLException
  {
    if(id_text_field.getText().isEmpty() || ! checkCI(String.valueOf(id_text_field.getText())))
      status_label.setText("CI invalido.");
    
    else if (is_id)
    {
      String id = id_text_field.getText();
      Customer tmp_customer = new Customer();
      tmp_customer = db.get_Customer_by_id(id);
      
      if (tmp_customer != null)
      {
        this.customer = tmp_customer;
        this.name_text_field.setText(customer.getName());
        this.last_name_text_field.setText(customer.getLast_name());
        this.direction_text_area.setText(customer.getDir());
        
        Engender gender = customer.getGender();
        
        if(gender.equals(Engender.Masculino))
          this.gender_combo_box.getSelectionModel().selectFirst();
        else if (gender.equals(Engender.Femenino))
          this.gender_combo_box.getSelectionModel().selectLast();
        
        this.birth_date_date_picker.setValue(LocalDate.parse(customer.getBirth_date().toString()));
        this.telephone_text_field.setText(customer.getPhone());
        
        this.status_label.setText("");
        this.new_customer_data_button.setDisable(true);
        
        if(rol.equals(Enrol.Administrador) || rol.equals(Enrol.Gerente))
          this.edit_customer_data_button.setDisable(false);
        else if(rol.equals(Enrol.Recepcion))
          this.edit_customer_data_button.setDisable(true);
        
      }
      else
      {
        this.name_text_field.clear();
        this.last_name_text_field.clear();
        this.direction_text_area.clear();
        this.birth_date_date_picker.setValue(LocalDate.now());
        this.telephone_text_field.clear();
        this.gender_combo_box.getSelectionModel().clearSelection();
        
        this.status_label.setText("Cliente no existe!");
        this.new_customer_data_button.setDisable(false);
        
        if(rol.equals(Enrol.Administrador) || rol.equals(Enrol.Gerente))
          this.edit_customer_data_button.setDisable(false);
        else if(rol.equals(Enrol.Recepcion))
          this.edit_customer_data_button.setDisable(true);
        
      }
    }
  }
  
  @FXML
  protected void handle_new_customer_button_action(ActionEvent event)
  {
    this.is_new_customer = true;
    this.gender_combo_box.getSelectionModel().selectFirst();
    this.handle_edit_customer_data_button_action(new ActionEvent());
    this.new_customer_data_button.setDisable(true);
    this.edit_customer_data_button.setDisable(true);
    this.id_text_field.setDisable(true);
  }
  
  @FXML
  protected void handle_edit_customer_data_button_action(ActionEvent event)
  {
    this.name_text_field.setDisable(false);
    this.last_name_text_field.setDisable(false);
    this.direction_text_area.setDisable(false);
    this.telephone_text_field.setDisable(false);
    this.birth_date_date_picker.setDisable(false);
    this.gender_combo_box.setDisable(false);
    this.save_customer_data_button.setDisable(false);
  }
  
  @FXML
  protected void handle_back_button_action(ActionEvent event) throws IOException
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
  protected void handle_menu_item_exit_action(ActionEvent e) {System.exit(0);}
  
  @FXML public String getUsername() {
    return username;
  }
  
  @FXML public void setUsername(String username) {
    this.username = username;
  }
  
  
  /* Letters Validation Limit the  characters to maxLengh AND to ONLY Letters *************************************/
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

  /* Letters Validation Limit the  characters to maxLengh AND to ONLY Letters *************************************/
  
  public EventHandler<KeyEvent> ci_Validation(final Integer max_Lengh)
  {
    return e -> {
      TextField txt_TextField = (TextField) e.getSource();
      if (txt_TextField.getText().length() >= max_Lengh)
      {
        e.consume();
      }
      if(e.getCharacter().matches("[A-Za-z 0-9/-]"))
      {
      }
      else
      {
        e.consume();
      }
    };
  }
  
  public EventHandler<KeyEvent> phone_Validation(final Integer max_Lengh)
  {
    return e -> {
      TextField txt_TextField = (TextField) e.getSource();
      if (txt_TextField.getText().length() >= max_Lengh)
      {
        e.consume();
      }
      if(e.getCharacter().matches("[0-9/-]"))
      {
      }
      else
      {
        e.consume();
      }
    };
  }
  
  public EventHandler<KeyEvent> direction_Validation(final Integer max_Lengh)
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
  
  public static boolean checkCI(String str)
  {
    boolean respuesta = false;
    
    if (str.matches("^[V|E|J]+[-]+\\d+\\d+\\d+\\d+\\d+\\d+\\d+\\d$"))
      respuesta = true;
    
    return respuesta;
  }
  
  public static boolean checkAlpha(String str)
  {
    boolean respuesta = false;
    
    if (str.matches("([a-z]|[A-Z]| | \\s)+"))
      respuesta = true;
    
    return respuesta;
  }
  
  public static boolean checkPhone(String str)
  {
    boolean respuesta = false;
    
    //if (str.matches("^\\d+\\d+\\d+\\d+[-]+\\d+\\d+\\d+\\d+\\d+\\d+\\d$"))
    if(str.matches("([0-9]-|\\-)+"))
      respuesta = true;
    
    return respuesta;
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
