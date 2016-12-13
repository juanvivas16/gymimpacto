package controller;

import data_model.Enrol;
import data_model.Enstate;
import data_model.Equipment_inventory;
import db_helper.Db_connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
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
      System.out.println("Inserto en inventario");

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
