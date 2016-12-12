package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by juancho on 11/12/16.
 */
public class Employee_controller implements Initializable
{
  @FXML private Label username_label;
  @FXML private Pane pane;
  @FXML private String username = new String("vacio");

  private boolean is_id = true;
  private boolean is_name = false;


  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    username_label.setText(getUsername());

  }

  @FXML
  protected void handle_save_employee_data_button_action(ActionEvent event)
  {

  }

  @FXML
  protected void handle_search_button_action(ActionEvent event)
  {

  }

  @FXML
  protected void handle_new_employee_button_action(ActionEvent event)
  {

  }

  @FXML
  protected void handle_edit_employee_data_button_action(ActionEvent event)
  {

  }

  @FXML
  protected void handle_back_button_action(ActionEvent event)
  {

  }

  @FXML
  protected void handle_cancel_button_action(ActionEvent event)
  {

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
