package controller;

import data_model.Engender;
import data_model.Person;
import db_helper.Db_connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
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
  @FXML private Button edit_client_data_button;
  @FXML private Button save_client_data_button;
  @FXML private Button back_button;
  @FXML private TextField id_text_field;
  @FXML private TextField name_text_field;
  @FXML private TextField last_name_text_field;
  @FXML private DatePicker birth_date_date_picker;
  @FXML private TextField telephone_text_field;
  @FXML private ComboBox gender_combo_box;
  @FXML private TextArea direction_text_area;
  @FXML private Db_connection db = new Db_connection();


  @FXML private Pane pane;
  @FXML private String username = new String("vacio");

  private static Person person = new Person();
  private boolean is_id = true;
  private boolean is_name = false;

  final Tooltip id_tooltip = new Tooltip();


  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    username_label.setText(getUsername());

    id_text_field.addEventFilter(KeyEvent.KEY_TYPED, ci_Validation(10));
    name_text_field.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(20));
    last_name_text_field.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(20));
    birth_date_date_picker.addEventFilter(KeyEvent.KEY_TYPED, date_Validation(10));
    telephone_text_field.addEventFilter(KeyEvent.KEY_TYPED, phone_Validation(12));
    direction_text_area.addEventFilter(KeyEvent.KEY_TYPED, direction_Validation(50));


    id_tooltip.setText("La cedula debe tener \n" +
        "almenos 6 numeros y maximo 11\n" );

    id_text_field.setTooltip(id_tooltip);

    ObservableList<String> type_list =
        FXCollections.observableArrayList(
            "Masculino",
            "Femenino"
        );

    this.gender_combo_box.getItems().clear();
    this.gender_combo_box.setItems(type_list);
    this.gender_combo_box.setDisable(true);
    this.direction_text_area.setDisable(true);

    this.gender_combo_box.getSelectionModel().selectFirst();

  }

  @FXML
  protected void handle_save_client_data_button_action(ActionEvent event)
  {

  }

  @FXML
  protected void handle_search_button_action(ActionEvent event) throws SQLException
  {
    if(id_text_field.getText().isEmpty() || ! checkCI(String.valueOf(id_text_field.getText())))
      status_label.setText("CI invalido.");

    else if (is_id)
    {
      String id = id_text_field.getText();
      Person tmp_person = db.get_person_by_id(id);

      if (tmp_person != null)
      {
        this.person = tmp_person;
        this.name_text_field.setText(person.getName());
        this.last_name_text_field.setText(person.getLast_name());
        this.direction_text_area.setText(person.getDir());

        Engender gender = person.getGender();

        if(gender.equals(Engender.Masculino))
          this.gender_combo_box.getSelectionModel().selectFirst();
        else if (gender.equals(Engender.Femenino))
          this.gender_combo_box.getSelectionModel().selectLast();

        this.birth_date_date_picker.setValue(LocalDate.parse(person.getBirth_date().toString()));
        this.telephone_text_field.setText(person.getPhone());

        this.status_label.setText("");
        this.edit_client_data_button.setDisable(false);
        this.new_client_data_button.setDisable(true);

      }
      else
      {
        this.name_text_field.clear();
        this.last_name_text_field.clear();
        this.direction_text_area.clear();
        this.birth_date_date_picker.setValue(LocalDate.now());
        this.telephone_text_field.clear();
        this.gender_combo_box.getSelectionModel().clearSelection();

        this.status_label.setText("Paciente no existe!");
        this.new_client_data_button.setDisable(false);
        this.edit_client_data_button.setDisable(true);

      }
    }
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


  /* Letters Validation Limit the  characters to maxLengh AND to ONLY Letters *************************************/
  public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh)
  {
    return e -> {
      TextField txt_TextField = (TextField) e.getSource();
      if (txt_TextField.getText().length() >= max_Lengh)
      {
        e.consume();
      }
      if(e.getCharacter().matches("[A-Za-z ]"))
      {
      }
      else
      {
        e.consume();
      }
    };
  }

  /* Letters Validation Limit the  characters to maxLengh AND to ONLY Letters *************************************/

  public EventHandler<KeyEvent> ci_Validation(final Integer max_Lengh)
  {
    return e -> {
      TextField txt_TextField = (TextField) e.getSource();
      if (txt_TextField.getText().length() >= max_Lengh)
      {
        e.consume();
      }
      if(e.getCharacter().matches("[V E J 0-9 -/]"))
      {
      }
      else
      {
        e.consume();
      }
    };
  }

  public EventHandler<KeyEvent> phone_Validation(final Integer max_Lengh)
  {
    return e -> {
      TextField txt_TextField = (TextField) e.getSource();
      if (txt_TextField.getText().length() >= max_Lengh)
      {
        e.consume();
      }
      if(e.getCharacter().matches("[A-Za-z 0-9/]"))
      {
      }
      else
      {
        e.consume();
      }
    };
  }

  public EventHandler<KeyEvent> direction_Validation(final Integer max_Lengh)
  {
    return e -> {
      TextArea txt_TextField = (TextArea) e.getSource();
      if (txt_TextField.getText().length() >= max_Lengh)
      {
        e.consume();
      }
      if(e.getCharacter().matches("[A-Za-z 0-9/]"))
      {
      }
      else
      {
        e.consume();
      }
    };
  }

  public EventHandler<KeyEvent> date_Validation(final Integer max_Lengh)
  {
    return e -> {
      DatePicker txt_TextField = (DatePicker) e.getSource();
      String str = txt_TextField.getValue().toString();

      System.out.println(str);
      if ( str.isEmpty() )
        return;

      if (txt_TextField.getValue().toString().length() > max_Lengh)
      {
        e.consume();
      }
      if (str.length() == max_Lengh)
      {
        // 3/26/2016
        if (str.matches("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}") )
        {

        }
        else
          txt_TextField.setValue(LocalDate.parse("1970-01-01"));
      }

      else if (e.getCharacter().matches("[0-9/]"))
      {
      }
      else
      {
        e.consume();
      }
    };
  }

  public static boolean checkCI(String str)
  {
    boolean respuesta = false;

    if (str.matches("([V-]|[0-9]| | \\s)+"))
      respuesta = true;

    return respuesta;
  }

}
