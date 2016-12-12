package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import db_helper.Db_connection;
import javafx.scene.layout.Pane;
import main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by juancho on 11/12/16.
 */

public class Reception_login_controller implements Initializable
{
    @FXML private Label username_label;
    @FXML private Button create_client_button;
    @FXML private Button check_status_button;
    @FXML private Button income_service_button;
    @FXML private Button income_product_button;
    @FXML private Button exit_button;

    @FXML private String username = new String("vacio");

    @FXML private Pane pane;
    @FXML private Db_connection db = new Db_connection();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        username_label.setText(getUsername());

    }

    @FXML protected void handle_create_client_action(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/customer_ui.fxml"));

        Parent root = (Parent)fxmlLoader.load();
        Customer_controller controller = fxmlLoader.<Customer_controller>getController();
        controller.setUsername(getUsername());
        controller.initialize(null, null);

        Main.primary_stage.setTitle("Clientes | Gimnasio Impacto (C) 2016");
        pane.getChildren().setAll(root);
    }

    @FXML protected void handle_check_status_action(ActionEvent event)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/status_ui.fxml"));

            Parent root = (Parent)fxmlLoader.load();
            Status_controller controller = fxmlLoader.<Status_controller>getController();
            controller.setUsername(getUsername());
            controller.initialize(null, null);

            Main.primary_stage.setTitle("Estado de Servicio | Gimnasio Impacto (C) 2016");

            pane.getChildren().setAll(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML protected void handle_income_service_action(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/invoice_service_ui.fxml"));

        Parent root = fxmlLoader.load();

        Invoice_service_controller controller = fxmlLoader.<Invoice_service_controller>getController();
        controller.setUsername(getUsername());
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
      controller.initialize(null,null);

      Main.primary_stage.setTitle("Cobrar Servicio | Gimnasio Impacto (C) 2016");
      pane.getChildren().setAll(root);

    }

    @FXML protected void  handle_exit_button_action(ActionEvent event) {System.exit(0);}

    @FXML protected void handle_menu_item_exit_action(ActionEvent e)
    {
        System.exit(0);
    }


    @FXML public String getUsername() {
        return username;
    }

    @FXML public void setUsername(String username) {
        this.username = username;
    }
}
