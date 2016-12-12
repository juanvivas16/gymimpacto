package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
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
  @FXML private Button invoice_button;
  @FXML private Button back_button;

  private String username = new String("vacio");

  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    username_label.setText(getUsername());
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
