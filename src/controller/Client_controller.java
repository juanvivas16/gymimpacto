package controller;

import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;


/**
 * Created by juancho on 11/12/16.
 */
public class Client_controller implements Initializable
{
    @FXML private Label username_label;
    @FXML private Label status_label;
    @FXML private Button search_button;
    @FXML private Button new_client_data_button;
    @FXML private Button edit_patient_data_button;
    @FXML private Button save_client_data_button;
    @FXML private Button back_button;
    @FXML private TextField id_text_field;
    @FXML private TextField name_text_field;
    @FXML private TextField last_name_text_field;
    @FXML private DatePicker birth_date_date_picker;
    @FXML private TextField telephone_text_field;
    @FXML private ComboBox gender_combo_box;
    @FXML private TextArea direction_text_area;

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
    protected void handle_save_client_data_button_action(ActionEvent event)
    {

    }

    @FXML
    protected void handle_search_button_action(ActionEvent event)
    {

    }

    @FXML
    protected void handle_new_client_button_action(ActionEvent event)
    {

    }

    @FXML
    protected void handle_edit_client_data_button_action(ActionEvent event)
    {

    }

    @FXML
    protected void handle_back_button_action(ActionEvent event)
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
    protected void handle_menu_item_exit_action(ActionEvent e) {System.exit(0);}

    @FXML public String getUsername() {
        return username;
    }

    @FXML public void setUsername(String username) {
        this.username = username;
    }
}
