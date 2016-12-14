package controller;

import data_model.*;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

/**
 * Created by juancho on 11/12/16.
 */
public class Invoice_service_controller implements Initializable
{
  @FXML private Pane pane;
  @FXML private TextField ci_textfield;
  @FXML private ComboBox service_combobox;
  @FXML private DatePicker begin_date;
  @FXML private TextField price_textfield;
  @FXML private TextField iva_textfield;
  @FXML private TextField total_textfield;
  @FXML private Label username_label;
  @FXML private Label status_label;
  @FXML private Label customer_label;
  @FXML private Button income_button;
  @FXML private Button back_button;
  
  private String username = new String("vacio");
  private Enrol rol;
  private Db_connection db = new Db_connection();
  private Income income = new Income();
  private Service service = new Service();
  private Customer customer = new Customer();
  
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    this.username_label.setText(getUsername());
    this.service_combobox.setDisable(true);
    this.begin_date.setDisable(true);
    this.price_textfield.setDisable(true);
    this.iva_textfield.setDisable(true);
    this.total_textfield.setDisable(true);
    this.income_button.setDisable(true);
    this.status_label.setText(" ");
    this.customer_label.setText(" ");
    
    service_combobox.getItems().setAll(Service_type.values());
    service_combobox.getSelectionModel().selectFirst();
  
    ci_textfield.addEventFilter(KeyEvent.KEY_TYPED, ci_Validation(10));
    price_textfield.addEventFilter(KeyEvent.KEY_TYPED, num_Validation(10));
  
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
  protected void handle_income_action(ActionEvent event) throws SQLException
  {
    this.income.setCustomer_id(ci_textfield.getText());
    this.income.setDate(Date.valueOf(LocalDate.now()));
    this.income.setDescription("Servicio " + service_combobox.getValue().toString());
    this.income.setIva(Double.parseDouble(iva_textfield.getText()));
    this.income.setSub_total(Double.parseDouble(price_textfield.getText()));
    this.income.setTotal(Double.parseDouble(total_textfield.getText()));
    
    this.service.setCustomer_ci(ci_textfield.getText());
    this.service.setInit_date(Date.valueOf(begin_date.getValue()));
    
    if(service_combobox.getValue().toString().equals("Diario"))
      this.service.setType_s(Service_type.Diario);
    else if(service_combobox.getValue().toString().equals("Quincenal"))
      this.service.setType_s(Service_type.Quincenal);
    else if(service_combobox.getValue().toString().equals("Mensual"))
      this.service.setType_s(Service_type.Mensual);
    
    if(this.service.getInit_date().before(Date.valueOf(LocalDate.now())))
      this.status_label.setText("Fecha de inicio debe ser mayor a fecha actual");
    else if(db.set_income_service_sql(income.getDescription(), income.getDate(), income.getSub_total(), income.getIva(), income.getTotal(), income.getCustomer_id(), username, service.getInit_date(), service.getType_s()))
      this.status_label.setText("Ingreso insertado con exito.");
    else
      this.status_label.setText("Error insertando ingreso.");
    
  }
  
  @FXML
  protected void handle_search_button_action(ActionEvent event) throws SQLException
  {
    String ci = ci_textfield.getText();
    Customer tmp_customer = new Customer();
    tmp_customer = db.get_Customer_by_id(ci);
    
    Service tmp_service = new Service();
    tmp_service = db.get_service_active_by_customer_id(ci);
    LocalDate end = LocalDate.now();
    
    if(tmp_service != null)
    {
      if(tmp_service.getType_s().equals(Service_type.Quincenal))
        end = tmp_service.getInit_date().toLocalDate().plusDays(15);
  
      else if(tmp_service.getType_s().equals(Service_type.Mensual))
        end = tmp_service.getInit_date().toLocalDate().plusDays(30);
  
      else if(tmp_service.getType_s().equals(Service_type.Diario))
        end = tmp_service.getInit_date().toLocalDate().plusDays(1);
    } else
      end = LocalDate.of(1992,9,16);
    
    if(tmp_customer != null && end.isBefore(LocalDate.now()))
    {
      this.customer = tmp_customer;
      this.customer_label.setText(customer.getCi() + " " + customer.getName() + " " + customer.getLast_name());
      
      service_combobox.setDisable(false);
      begin_date.setDisable(false);
      price_textfield.setDisable(false);
      iva_textfield.setText(String.valueOf(12));
      //total_textfield.setDisable(false);
      income_button.setDisable(false);
      status_label.setText("Listo para generar ingreso");
      ci_textfield.setDisable(true);
      
    } else
    {
      //this.customer_label.setText(customer.getCi() + " " + customer.getName() + " " + customer.getLast_name());
      this.status_label.setText("Servicio aun activo.");
    }
    
  }
  
  @FXML protected void handle_subtotal_key_release_action(KeyEvent event) throws IOException, SQLException
  {
    
    if (this.price_textfield.getText().isEmpty())
      return;
    
    float sub_total =  Float.parseFloat(this.price_textfield.getText());
    float iva =  Float.parseFloat(this.iva_textfield.getText());
    
    float total = sub_total + sub_total * ( iva / 100 );
    
    this.total_textfield.setText(Float.toString(total));
    
  }
  
  public EventHandler<KeyEvent> ci_Validation(final Integer max_Lengh)
  {
    return e -> {
      TextField txt_TextField = (TextField) e.getSource();
      if (txt_TextField.getText().length() >= max_Lengh)
      {
        e.consume();
      }
      if(e.getCharacter().matches("[V E 0-9-]"))
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
      if(e.getCharacter().matches("[0-9]"))
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
