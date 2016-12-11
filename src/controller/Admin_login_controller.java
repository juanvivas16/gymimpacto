package controller;

import data_model.Enrol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by juancho on 11/12/16.
 */
public class Admin_login_controller implements Initializable
{
    private String username;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @FXML protected void handle_client_action(ActionEvent event)
    {

    }

    @FXML protected void handle_check_status_action(ActionEvent event)
    {

    }

    @FXML protected void handle_income_service_action(ActionEvent event)
    {

    }

    @FXML protected void handle_income_product_action(ActionEvent event)
    {

    }

    @FXML protected void handle_expense_action(ActionEvent event)
    {

    }
    @FXML protected void handle_inventory_action(ActionEvent event)
    {

    }

    @FXML protected void handle_supplier_action(ActionEvent event)
    {

    }

    @FXML protected void handle_employee_action(ActionEvent event)
    {

    }

    @FXML protected void handle_users_action(ActionEvent event)
    {

    }

    @FXML protected void handle_menu_item_exit_action(ActionEvent e) {System.exit(0);}

    @FXML protected void handle_exit_button_action(ActionEvent event) {System.exit(0);}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
