package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by juancho on 11/12/16.
 */
public class Invoice_product_controller implements Initializable
{
  @FXML private Label username_label;
  @FXML private String username = new String("vacio");

  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    username_label.setText(getUsername());

  }

  @FXML
  protected void handle_add_product_action(ActionEvent event)
  {

  }

  @FXML
  protected void handle_back_buttom(ActionEvent event)
  {

  }

  @FXML
  protected void handle_invoice_action(ActionEvent event)
  {

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

}
