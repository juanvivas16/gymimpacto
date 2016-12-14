package controller;

import data_model.Enrol;
import data_model.Product;
import db_helper.Db_connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by juancho on 11/12/16.
 */
public class Manager_add_product_controller implements Initializable
{
  @FXML private Label username_label;
  @FXML private Label status_label;
  @FXML private Pane pane;
  @FXML private String username = new String("vacio");
  @FXML private Enrol rol;
  @FXML private TextField code_textfield;
  @FXML private TextField desc_textfield;
  @FXML private TextField price_textfield;
  @FXML private TextField quantity_textfield;
  @FXML private ComboBox supplier_combobox;
  @FXML private Button new_product_button;
  @FXML private Button edit_product_button;
  @FXML private Button add_product_button;
  
  private Product product = new Product();
  private boolean is_id = true;
  private boolean is_new_product = false;
  private Db_connection db = new Db_connection();
  
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    this.username_label.setText(getUsername());
    this.desc_textfield.setDisable(true);
    this.price_textfield.setDisable(true);
    this.quantity_textfield.setDisable(true);
    this.supplier_combobox.setDisable(true);
    this.new_product_button.setDisable(true);
    this.edit_product_button.setDisable(true);
    this.add_product_button.setDisable(true);
    
  }
  
  @FXML
  protected void handle_edit_product_action(ActionEvent event)
  {
    
  }
  
  @FXML
  protected void handle_new_product_action(ActionEvent event) throws SQLException
  {
    this.is_new_product = true;
    this.add_product_button.setDisable(false);
    this.desc_textfield.setDisable(false);
    this.price_textfield.setDisable(false);
    this.quantity_textfield.setDisable(false);
    this.supplier_combobox.setDisable(false);
    
    supplier_combobox.getItems().addAll(db.get_supplier_name());
    
    this.new_product_button.setDisable(true);
    this.code_textfield.setDisable(true);
    
  }
  
  @FXML
  protected void handle_search_code_action(ActionEvent event) throws SQLException
  {
    if(is_id)
    {
      long id = Long.parseLong(code_textfield.getText());
      Product tmp_product = new Product();
      tmp_product = db.get_product_by_id(id);
      
      if(tmp_product != null)
      {
        this.product = tmp_product;
        this.desc_textfield.setText(product.getDescription());
        this.price_textfield.setText(String.valueOf(product.getPrice()));
        this.quantity_textfield.setText(String.valueOf(product.getQuantity_available()));
        this.supplier_combobox.setValue(product.getSupplier_rif());
        
        
        this.edit_product_button.setDisable(false);
        this.desc_textfield.setDisable(true);
        this.price_textfield.setDisable(true);
        this.quantity_textfield.setDisable(true);
        this.supplier_combobox.setDisable(true);
        this.new_product_button.setDisable(true);
      } else
      {
        this.new_product_button.setDisable(false);
        this.desc_textfield.clear();
        this.price_textfield.clear();
        this.quantity_textfield.clear();
        this.status_label.setText("Producto no existe.");
        this.edit_product_button.setDisable(true);
        this.add_product_button.setDisable(true);
        this.supplier_combobox.setValue(" ");
        
      }
    }
    
  }
  
  @FXML
  protected void handle_save_product_action(ActionEvent event)
  {
    if(db.set_product_sql(desc_textfield.getText(),Double.parseDouble(price_textfield.getText()), Integer.parseInt(quantity_textfield.getText()),supplier_combobox.getValue().toString()))
      status_label.setText("Producto insertado con exito");
    else
      status_label.setText("Error al insertar producto");
  }
  
  @FXML protected  void handle_id_text_changed_action(ActionEvent event)
  {
    is_id = false;
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
