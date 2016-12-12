package db_helper;


import data_model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;


public class Db_connection {
  private Connection _con = null;
  private String _url;
  private String _user;
  private String _pass;


  public Db_connection() {
    this.init_connection();
  }

  private boolean get_data_from_prop() {
    Properties __prop = new Properties();
    FileInputStream __in = null;
    try {
      __in = new FileInputStream("src/etc/db_config.properties");
      //System.out.println("properties readed");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      //System.out.println("properties faild");
      return false;
    }

    try {
      __prop.load(__in);
      this._url = __prop.getProperty("url");
      this._user = __prop.getProperty("user");
      this._pass = __prop.getProperty("pass");

      __in.close();

      //System.out.println("properties loaded"+ _url + _user + _pass);
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }


  private boolean init_connection() {
    if (get_data_from_prop()) {
      try {
        _con = DriverManager.getConnection(this._url, this._user, this._pass);

        //System.out.println("connected to db");


      } catch (SQLException e) {
        //System.out.println("Faild to connect to db");

        e.printStackTrace();
        return false;
      }
      return true;
    } else
      return false;
  }


  public Connection get_connection() {
    if (init_connection()) {
      return this._con;
    } else
      return null;
  }


  public boolean close_connection() {
    if (this._con != null) {
      try {
        _con.close();
      } catch (SQLException e) {
        e.printStackTrace();
        return false;
      }
      return true;
    }
    return true;
  }


  public ResultSet execute_query(String sql_query) {
    try {
      Statement __st = this._con.createStatement();
      return __st.executeQuery(sql_query);

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }


  public int execute_update(String sql_update_query) {
    try {
      Statement __st = this._con.createStatement();
      return __st.executeUpdate(sql_update_query);

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return -1;
  }

  public Person get_person_by_id(String id) throws SQLException {
    ResultSet rs = this.execute_query("SELECT * from person WHERE person.ci='" + id + "'");
    if (rs.next()) {
      String ci = rs.getString("ci");
      String name = rs.getString("name");
      String last_name = rs.getString("last_name");
      Date birth_date = rs.getDate("birth_date");
      Engender gender = Engender.Masculino;

      if (rs.getString("gender").equals("Masculino"))
        gender = Engender.Masculino;
      else if (rs.getString("gender").equals("Femenino"))
        gender = Engender.Femenino;

      String dir = rs.getString("dir");
      String phone = rs.getString("phone");

      return new Person(ci, name, last_name, birth_date, gender, dir, phone);
    }
    return null;
  }

  public Employee get_employee_by_id(String id) throws SQLException {
    ResultSet rs = this.execute_query("SELECT * FROM person p JOIN employee em ON p.ci = em.employee_ci WHERE em.employee_ci ='" + id + "'");

    if (rs.next()) {
      String ci = rs.getString("ci");
      String name = rs.getString("name");
      String last_name = rs.getString("last_name");
      Date birth_date = rs.getDate("birth_date");
      Engender gender = Engender.Masculino;

      if (rs.getString("gender").equals(Engender.Masculino))
        gender = Engender.Masculino;
      else if (rs.getString("gender").equals(Engender.Femenino))
        gender = Engender.Femenino;

      String dir = rs.getString("dir");
      String phone = rs.getString("phone");
      Type_contract type_c = Type_contract.Medio_tiempo;

      if (rs.getString("type_c").equals("Tiempo_completo"))
        type_c = Type_contract.Tiempo_completo;
      else if (rs.getString("type_c").equals("Medio_tiempo"))
        type_c = Type_contract.Medio_tiempo;
      else if (rs.getString("type_c").equals("Por_horas"))
        type_c = Type_contract.Por_horas;

      double pay = rs.getDouble("pay");
      String activity = rs.getString("activity");

      return new Employee(ci, name, last_name, birth_date, gender, dir, phone, type_c, pay, activity);
    }

    return null;
  }

  public User get_user_by_username(String id) throws SQLException {
    ResultSet rs = this.execute_query("SELECT username, pass, rol FROM user WHERE username =" + id);

    if (rs.next()) {
      String username = rs.getString("username");
      String pass = rs.getString("pass");
      Enrol rol = Enrol.Administrador;

      if (rs.getString("rol").equals(Enrol.Administrador))
        rol = Enrol.Administrador;
      else if (rs.getString("rol").equals(Enrol.Gerente))
        rol = Enrol.Gerente;
      else if (rs.getString("rol").equals(Enrol.Recepcion))
        rol = Enrol.Recepcion;

      return new User(username, pass, rol);
    }

    return null;
  }

// obtener facturas por id de clientes

  public Income get_income_by_Customer(int id) throws SQLException {
    ResultSet rs = this.execute_query("SELECT * FROM income WHERE customer_id =" + id);

    if (rs.next()) {
      int id1 = rs.getInt("id1");
      String desc = rs.getString("desc");
      String customer_id = rs.getString("customer_id");
      Date date = rs.getDate("date");
      String user_id = rs.getString("user_id");
      double sub_total = rs.getDouble("sub_total");
      double iva = rs.getDouble("iva");
      double total = rs.getDouble("total");


      // return new Income(id, desc, customer_id, date, sub_total, iva, total, user_id);
    }

    return null;
  }

  //obtener ingresos por ci de persona
  public Income get_income_by_ci(int ci) throws SQLException {
    ResultSet rs = this.execute_query("SELECT i.customer_id, p.name, p.last_name, i.total FROM income i JOIN person p ON p.ci = i.customer_id WHERE i.customer_id =" + ci);

    if (rs.next()) {
      String customer_id = rs.getString("customer_id");
      String name = rs.getString("name");
      String last_name = rs.getString("last_name");
      double total = rs.getDouble("total");


      //return new Income(customer_id, name, last_name, total);
    }

    return null;
  }


  // obtener inventario de maquinas por id
  /*public Equipment_inventory get_Equipment_by_id(int id) throws SQLException {
    ResultSet rs = this.execute_query("select * from equipment_inventory where id=" + id);

    if (rs.next()) {


      int id2 = rs.getInt("id2");
      String name = rs.getString("name");
      String model = rs.getString("model");
      String desc = rs.getString("desc");
      Date ad_date = rs.getDate("ad_date");
      double cost = rs.getDouble("cost");
      //enum state =
      int quantity = rs.getInt("quantity");
      String user_id = rs.getString("user_id");

      //return new Income(id, name, model, desc, ad_date, cost, state, quantity, user_id);


    }*/

  //obtener informacion de proveedores a traves de la descripcion de productos

  public Supplier get_Supplier_by_product_desc(String product_desc) throws SQLException {
    ResultSet rs = this.execute_query("select * from supplier where product_desc = " + product_desc);

    if (rs.next()) {
      String id = rs.getString("id");
      String name = rs.getString("name");
      String phone = rs.getString("phone");
      String dir = rs.getString("dir");
      String product_desc1 = rs.getString("product_desc1");
      String user_id = rs.getString("user_id");


      return new Supplier(id, name, phone, dir, product_desc, user_id);
    }

    return null;
  }




}

