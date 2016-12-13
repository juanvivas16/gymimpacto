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
public class Admin_login_controller implements Initializable
{
  @FXML private Pane pane;
  @FXML private Label username_label;
  @FXML private String username = new String("vacio");
  @FXML private Enrol rol;

  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    username_label.setText(getUsername());
  }

  @FXML protected void handle_client_action(ActionEvent event) throws IOException
  {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/admin_customer_ui.fxml"));

    Parent root = (Parent)fxmlLoader.load();
    Admin_client_controller controller = fxmlLoader.<Admin_client_controller>getController();
    controller.setUsername(getUsername());
    controller.setRol(rol);
    controller.initialize(null, null);

    Main.primary_stage.setTitle("Clientes | Gimnasio Impacto (C) 2016");
    pane.getChildren().setAll(root);
  }

  @FXML protected void handle_check_status_action(ActionEvent event) throws IOException
  {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/status_ui.fxml"));

    Parent root = (Parent)fxmlLoader.load();
    Status_controller controller = fxmlLoader.<Status_controller>getController();
    controller.setUsername(getUsername());
    controller.setRol(rol);
    controller.initialize(null, null);

    Main.primary_stage.setTitle("Estado de Servicio | Gimnasio Impacto (C) 2016");

    pane.getChildren().setAll(root);

  }

  @FXML protected void handle_income_service_action(ActionEvent event) throws IOException
  {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/invoice_service_ui.fxml"));

    Parent root = fxmlLoader.load();

    Invoice_service_controller controller = fxmlLoader.<Invoice_service_controller>getController();
    controller.setUsername(getUsername());
    controller.setRol(rol);
    controller.initialize(null,null);

    Main.primary_stage.setTitle("Cobrar Servicio | Gimnasio Impacto (C) 2016");
    pane.getChildren().setAll(root);

  }

  @FXML protected void handle_income_product_action(ActionEvent event) throws IOException
  {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/invoice_product_ui.fxml"));

    Parent root = fxmlLoader.load();
    Invoice_product_controller controller = fxmlLoader.<Invoice_product_controller>getController();
    controller.setUsername(getUsername());
    controller.setRol(rol);
    controller.initialize(null,null);

    Main.primary_stage.setTitle("Cobrar Servicio | Gimnasio Impacto (C) 2016");
    pane.getChildren().setAll(root);

  }

  @FXML protected void handle_expense_action(ActionEvent event) throws IOException
  {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/expenses_ui.fxml"));

    Parent root = fxmlLoader.load();
    Expenses_controller controller = fxmlLoader.<Expenses_controller>getController();
    controller.setUsername(getUsername());
    controller.setRol(rol);
    controller.initialize(null,null);

    Main.primary_stage.setTitle("Egresos | Gimnasio Impacto (C) 2016");
    pane.getChildren().setAll(root);
  }
  @FXML protected void handle_inventory_action(ActionEvent event) throws IOException
  {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/admin_inventory_ui.fxml"));

    Parent root = fxmlLoader.load();
    Inventory_controller controller = fxmlLoader.<Inventory_controller>getController();
    controller.setUsername(getUsername());
    controller.setRol(rol);
    controller.initialize(null,null);

    Main.primary_stage.setTitle("Inventario | Gimnasio Impacto (C) 2016");
    pane.getChildren().setAll(root);
  }

  @FXML protected void handle_supplier_action(ActionEvent event) throws IOException
  {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/supplier_ui.fxml"));

    Parent root = fxmlLoader.load();
    Supplier_controller controller = fxmlLoader.<Supplier_controller>getController();
    controller.setUsername(getUsername());
    controller.setRol(rol);
    controller.initialize(null,null);

    Main.primary_stage.setTitle("Agregar Proveedores | Gimnasio Impacto (C) 2016");
    pane.getChildren().setAll(root);

  }

  @FXML protected void handle_employee_action(ActionEvent event) throws IOException
  {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/employee_ui.fxml"));

    Parent root = fxmlLoader.load();
    Employee_controller controller = fxmlLoader.<Employee_controller>getController();
    controller.setUsername(getUsername());
    controller.setRol(rol);
    controller.initialize(null,null);

    Main.primary_stage.setTitle("Agregar Empleados | Gimnasio Impacto (C) 2016");
    pane.getChildren().setAll(root);

  }

  @FXML protected void handle_users_action(ActionEvent event) throws IOException
  {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/user_ui.fxml"));

    Parent root = fxmlLoader.load();
    User_controller controller = fxmlLoader.<User_controller>getController();
    controller.setUsername(getUsername());
    controller.setRol(rol);
    controller.initialize(null,null);

    Main.primary_stage.setTitle("Agregar Usuarios | Gimnasio Impacto (C) 2016");
    pane.getChildren().setAll(root);

  }

  @FXML protected void handle_product_action(ActionEvent event) throws IOException
  {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/admin_product_ui.fxml"));

    Parent root = fxmlLoader.load();
    Admin_product_controller controller = fxmlLoader.<Admin_product_controller>getController();
    controller.setUsername(getUsername());
    controller.setRol(rol);
    controller.initialize(null,null);

    Main.primary_stage.setTitle("Productos | Gimnasio Impacto (C) 2016");
    pane.getChildren().setAll(root);
  }

  @FXML protected void handle_menu_item_exit_action(ActionEvent e) {System.exit(0);}

  @FXML protected void handle_exit_button_action(ActionEvent event) {System.exit(0);}

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
