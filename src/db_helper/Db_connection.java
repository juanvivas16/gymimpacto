package db_helper;


import data_model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;


public class Db_connection
{
  private Connection _con = null;
  private String _url;
  private String _user;
  private String _pass;
  
  
  public Db_connection( )
  {
    this.init_connection();
  }
  
  private boolean get_data_from_prop( )
  {
    Properties __prop = new Properties();
    FileInputStream __in = null;
    try
    {
      __in = new FileInputStream("src/etc/db_config.properties");
      //System.out.println("properties readed");
    } catch(FileNotFoundException e)
    {
      e.printStackTrace();
      //System.out.println("properties faild");
      return false;
    }
    
    try
    {
      __prop.load(__in);
      this._url = __prop.getProperty("url");
      this._user = __prop.getProperty("user");
      this._pass = __prop.getProperty("pass");
      
      __in.close();
      
      //System.out.println("properties loaded"+ _url + _user + _pass);
    } catch(IOException e)
    {
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  
  private boolean init_connection( )
  {
    if(get_data_from_prop())
    {
      try
      {
        _con = DriverManager.getConnection(this._url, this._user, this._pass);
        
        //System.out.println("connected to db");
        
        
      } catch(SQLException e)
      {
        //System.out.println("Faild to connect to db");
        
        e.printStackTrace();
        return false;
      }
      return true;
    } else
      return false;
  }
  
  
  public Connection get_connection( )
  {
    if(init_connection())
    {
      return this._con;
    } else
      return null;
  }
  
  
  public boolean close_connection( )
  {
    if(this._con != null)
    {
      try
      {
        _con.close();
      } catch(SQLException e)
      {
        e.printStackTrace();
        return false;
      }
      return true;
    }
    return true;
  }
  
  
  public ResultSet execute_query(String sql_query)
  {
    try
    {
      Statement __st = this._con.createStatement();
      return __st.executeQuery(sql_query);
      
    } catch(SQLException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  
  public int execute_update(String sql_update_query)
  {
    try
    {
      Statement __st = this._con.createStatement();
      return __st.executeUpdate(sql_update_query);
      
    } catch(SQLException e)
    {
      e.printStackTrace();
    }
    return -1;
  }
  
  
  /***CREACION DE LOS CRUD
   *
   * @param id
   * @return
   * @throws SQLException
   */
  
  /**
   * CRUD PERSONA
   *
   * @param id
   * @return
   * @throws SQLException
   */
  
  public Person get_person_by_id(String id) throws SQLException
  {
    ResultSet rs = this.execute_query("SELECT * from person WHERE person.ci='" + id + "'");
    if(rs.next())
    {
      String ci = rs.getString("ci");
      String name = rs.getString("name");
      String last_name = rs.getString("last_name");
      Date birth_date = rs.getDate("birth_date");
      Engender gender = Engender.Masculino;
      String g = rs.getString("gender");
      
      if(g.equals("Masculino"))
        gender = Engender.Masculino;
      else if(g.equals("Femenino"))
        gender = Engender.Femenino;
      
      String dir = rs.getString("dir");
      String phone = rs.getString("phone");
      
      return new Person(ci, name, last_name, birth_date, gender, dir, phone);
    }
    return null;
  }
  
  public Employee get_employee_by_id(String id) throws SQLException
  {
    ResultSet rs = this.execute_query(
        "SELECT * FROM person p JOIN employee em ON p.ci = em.employee_ci WHERE em.employee_ci ='" + id + "'");
    
    if(rs.next())
    {
      String ci = rs.getString("ci");
      String name = rs.getString("name");
      String last_name = rs.getString("last_name");
      Date birth_date = rs.getDate("birth_date");
      Engender gender = Engender.Masculino;
      String g = rs.getString("gender");
      
      if(g.equals("Masculino"))
        gender = Engender.Masculino;
      else if(g.equals("Femenino"))
        gender = Engender.Femenino;
      
      String dir = rs.getString("dir");
      String phone = rs.getString("phone");
      Type_contract type_c = Type_contract.Medio_tiempo;
      
      if(rs.getString("type_c").equals("Tiempo_completo"))
        type_c = Type_contract.Tiempo_completo;
      else if(rs.getString("type_c").equals("Medio_tiempo"))
        type_c = Type_contract.Medio_tiempo;
      else if(rs.getString("type_c").equals("Por_horas"))
        type_c = Type_contract.Por_horas;
      
      double pay = rs.getDouble("pay");
      String activity = rs.getString("activity");
      
      return new Employee(ci, name, last_name, birth_date, gender, dir, phone, type_c, pay, activity);
    }
    
    return null;
  }
  
  public User get_user_by_username(String id) throws SQLException
  {
    ResultSet rs = this.execute_query("SELECT username, pass, rol FROM user WHERE username =" + id);
    
    if(rs.next())
    {
      String username = rs.getString("username");
      String pass = rs.getString("pass");
      Enrol rol = Enrol.Administrador;
      
      if(rs.getString("rol").equals("Administrador"))
        rol = Enrol.Administrador;
      else if(rs.getString("rol").equals("Gerente"))
        rol = Enrol.Gerente;
      else if(rs.getString("rol").equals("Recepcion"))
        rol = Enrol.Recepcion;
      
      return new User(username, pass, rol);
    }
    
    return null;
  }
  
  // obtener facturas por id de clientes
  
  public Income get_income_by_Customer(String ci) throws SQLException
  {
    ResultSet rs = this.execute_query("SELECT * FROM income WHERE customer_id ='" + ci + "'");
    
    if(rs.next())
    {
      long id = rs.getLong("id");
      String customer_id = rs.getString("customer_id");
      String desc = rs.getString("desc");
      Date date = rs.getDate("date");
      double sub_total = rs.getDouble("sub_total");
      double iva = rs.getDouble("iva");
      double total = rs.getDouble("total");
      String user_id = rs.getString("user_id");
      
      //public Income(long id, String desc, Date date, double sub_total, double iva, double total, String customer_id, String user_id)
      
      return new Income(id, desc, date, sub_total, iva, total, customer_id, user_id);
    }
    
    return null;
  }
  
  // obtener inventario de maquinas por id
  public Equipment_inventory get_Equipment_by_id(int id) throws SQLException
  {
    ResultSet rs = this.execute_query("select * from equipment_inventory where id=" + id);
    
    if(rs.next())
    {
      int id2 = rs.getInt("id");
      String name = rs.getString("name");
      String model = rs.getString("model");
      String desc = rs.getString("desc");
      Date ad_date = rs.getDate("ad_date");
      double cost = rs.getDouble("cost");
      Enstate state = Enstate.Activo;
      
      if(rs.getString("state").equals("Activo"))
        state = Enstate.Activo;
      else if(rs.getString("state").equals("Inactivo"))
        state = Enstate.Inactivo;
      
      int quantity = rs.getInt("quantity");
      String user_id = rs.getString("user_id");
      
      return new Equipment_inventory(id2, name, model, desc, ad_date, cost, quantity, state, user_id);
    }
    
    return null;
  }
  
  //obtener cliente por id
  
  public Customer get_Customer_by_id(String ci) throws SQLException
  {
    ResultSet rs = this.execute_query(
        "select p.ci, p.name, p.last_name, p.birth_date, p.gender, p.dir, p.phone, c.init_date from customer c join person p on c.ci = p.ci where c.ci = '" + ci + "'");
    
    if(rs.next())
    {
      String ci1 = rs.getString("ci");
      String name = rs.getString("name");
      String last_name = rs.getString("last_name");
      Date birth_date = rs.getDate("birth_date");
      Engender gender = Engender.Masculino;
      //String g = rs.getString("gender");
      
      if(rs.getString("gender").equals("Masculino"))
        gender = Engender.Masculino;
      else if(rs.getString("gender").equals("Femenino"))
        gender = Engender.Femenino;
      
      String dir = rs.getString("dir");
      String phone = rs.getString("phone");
      Date init_date = rs.getDate("init_date");
      
      
      return new Customer(ci1, name, last_name, birth_date, gender, dir, phone, init_date);
    }
    
    return null;
  }
  
  public boolean set_person_sql(String ci, String name, String last_name, Date birth_date, Engender gender, String dir,
                                String phone)
  {
    try
    {
      _con.setAutoCommit(false);
      PreparedStatement statement;
      
      statement = _con.prepareStatement(
          "insert into persona (ci, name, last_name, birth_date, gender, dir, phone) " + "values ('" + ci + "','" + name + "','" + last_name + "', '" + birth_date + "', '" + gender
              .toString() + "','" + dir + "', '" + phone + "')");
      
      statement.executeUpdate();
      
      _con.commit();
    } catch(SQLException ex)
    {
      try
      {
        _con.rollback();
      } catch(SQLException ex1)
      {
        System.err.println("Transaction failed");
        return false;
      }
    }
    
    return true;
  }
  
  public boolean set_customer_sql(String ci, String name, String last_name, Date birth_date, Engender gender,
                                  String dir, String phone, Date init_date)
  {
    try
    {
      _con.setAutoCommit(false);
      PreparedStatement statement;
      System.out.println("Antes de commit1");
      statement = _con.prepareStatement(
          "insert into person (ci, name, last_name, birth_date, gender, dir, phone)  values ('" + ci + "', '" + name + "', '" + last_name + "', '" + birth_date + "','" + gender
              .toString() + "' , '" + dir + "', '" + phone + "')");
      
      
      statement.executeUpdate();
      System.out.println("Antes de commit2");
      
      statement =
          _con.prepareStatement("insert into customer (ci, init_date) values " + "('" + ci + "','" + init_date + "')");
      
      statement.executeUpdate();
      
      System.out.println("Antes de commit3");
      _con.commit();
    } catch(SQLException ex)
    {
      try
      {
        _con.rollback();
      } catch(SQLException ex1)
      {
        System.err.println("Transaction failed");
        return false;
      }
      
      System.out.println(ex.getMessage());
    }
    return true;
  }
  
  public boolean set_inventory_sql(String name, String model, String description, Date ad_date, double cost,
                                   int quantity, Enstate state, String user_id)
  {
    try
    {
      _con.setAutoCommit(false);
      PreparedStatement statement;
      
      statement = _con.prepareStatement(
          "insert into equipment_inventory (name, model, description, ad_date, cost, quantity, state, user_id)  values ('" + name + "', '" + model + "', '" + description + "','" + ad_date + "' , '" + cost + "', '" + quantity + "','" + state.toString() + "', '" + user_id + "')");
      
      statement.executeUpdate();
      
      _con.commit();
    } catch(SQLException ex)
    {
      try
      {
        _con.rollback();
      } catch(SQLException ex1)
      {
        System.err.println("Transaction failed");
        return false;
      }
      
      System.out.println(ex.getMessage());
    }
    return true;
  }
  
  public boolean set_expenses_sql(String description, Date date, double total, String user_id)
  {
    try
    {
      _con.setAutoCommit(false);
      PreparedStatement statement;
      
      //INSERT INTO expenses (`desc`, date, total, user_id) VALUES ("Prueba de engreso insertadoooo", "2016-10-02", 13450.56, "javiers" )
      
      statement = _con.prepareStatement(
          "insert into expenses (description, date, total, user_id)  values ('" + description + "', '" + date + "', '" + total + "', '" + user_id + "')");
      
      statement.executeUpdate();
      
      _con.commit();
    } catch(SQLException ex)
    {
      try
      {
        _con.rollback();
      } catch(SQLException ex1)
      {
        System.err.println("Transaction failed");
        return false;
      }
      
      System.out.println(ex.getMessage());
    }
    return true;
  }
  
  
  public Service search_service_by_customer_id(String ci) throws SQLException
  {
    ResultSet rs = this.execute_query("SELECT * FROM service WHERE customer_id ='" + ci + "'" + "ORDER BY income_id LIMIT 1");
    
    
    if(rs.next())
    {
      long id = rs.getLong("income_id");
      Date init_date = rs.getDate("init_date");
      String customer_id = rs.getString("customer_id");
      Service_type type_s = Service_type.Quincenal;
      
      if(rs.getString("type_s").equals("Diario"))
        type_s = Service_type.Diario;
      else if(rs.getString("type_s").equals("Mensual"))
        type_s = Service_type.Mensual;
      else if(rs.getString("type_s").equals("Quincenal"))
        type_s = Service_type.Quincenal;
      
      return new Service(id, type_s, init_date, customer_id);
    }
    return null;
  }
  
  public Supplier get_supplier_by_rif(String rif) throws SQLException
  {
    ResultSet rs = this.execute_query("SELECT * FROM supplier WHERE supplier.id = '" + rif + "'");
    
    if(rs.next())
    {
      String id = rs.getString("id");
      String name = rs.getString("name");
      String phone = rs.getString("phone");
      String dir = rs.getString("dir");
      String product_desc = rs.getString("product_desc");
      String user_id = rs.getString("user_id");
      
      return  new Supplier(id,name,phone,dir,product_desc,user_id);
    }
    return null;
  }
  
  
  public boolean set_supplier_sql(String id, String name, String phone, String dir, String product_desc, String user_id)
  {
    try
    {
      _con.setAutoCommit(false);
      PreparedStatement statement;
      
      statement = _con.prepareStatement("INSERT INTO supplier(id, name, phone, dir, product_desc, user_id)" + "VALUES ('" + id + "','" + name + "','" + phone + "', '" + dir + "', '" + product_desc + "','" + user_id + "')");
      
      statement.executeUpdate();
      _con.commit();
      
    }catch(SQLException ex)
    {
      try
      {
        _con.rollback();
        return false;
      } catch(SQLException ex1)
      {
        System.err.println("Transaction failed");
      }
    }
    return true;
  }
  
  public Product get_product_by_id(long id) throws SQLException
  {
    ResultSet rs = this.execute_query(" SELECT p.id, p.description, p.price, p.quantity_available, sup.name FROM product p\n" + "JOIN supplier sup ON p.supplier_rif = sup.id\n" + "WHERE p.id =" + id);
    
    if(rs.next())
    {
      String description = rs.getString("description");
      double price = rs.getDouble("price");
      int quantity_available = rs.getInt("quantity_available");
      String name = rs.getString("name");
      
      return new Product(id, description, price, quantity_available, name);
    }
    return null;
  }
  
  public List<String> get_supplier_name() throws SQLException
  {
    List<String> names = new ArrayList<String>();
    ResultSet rs = this.execute_query("SELECT name FROM supplier GROUP BY name");
    
    while(rs.next())
    {
      names.add(rs.getString("name"));
    }
    return names;
  }
  
  public boolean set_product_sql(String description, double price, long quantity_available, String supplier)
  {
    try
    {
      _con.setAutoCommit(false);
      PreparedStatement statement;
      
      statement = _con.prepareStatement(
          "insert into product (description, price, quantity_available, supplier_rif) " + "values ('" + description + "','" + price + "','" + quantity_available + "','" + supplier +"')");
      
      statement.executeUpdate();
      
      _con.commit();
    } catch(SQLException ex)
    {
      
      try
      {
        _con.rollback();
        return false;
      } catch(SQLException ex1)
      {
        System.err.println("Transaction failed");
        return false;
      }
    }
    
    return true;
  }
  
  public boolean set_employee_sql(String ci, String name, String last_name, Date birth_date, Engender gender, String dir, String phone,
                                  Type_contract type_c, double pay, String activity)
  {
    try
    {
      _con.setAutoCommit(false);
      PreparedStatement statement;
      System.out.println("Antes de commit1");
      statement = _con.prepareStatement(
          "insert into person (ci, name, last_name, birth_date, gender, dir, phone)  values ('" + ci + "', '" + name + "', '" + last_name + "', '" + birth_date + "','" + gender
              .toString() + "' , '" + dir + "', '" + phone + "')");
      
      
      statement.executeUpdate();
      System.out.println("Antes de commit2");
      
      statement =
          _con.prepareStatement("insert into employee (type_c, pay, activity) values " + "('" + type_c + "','" + pay + "','" + activity + "')");
      
      statement.executeUpdate();
      
      System.out.println("Antes de commit3");
      _con.commit();
    } catch(SQLException ex)
    {
      try
      {
        _con.rollback();
      } catch(SQLException ex1)
      {
        System.err.println("Transaction failed");
        return false;
      }
      
      System.out.println(ex.getMessage());
    }
    return true;
  }
  
  public boolean set_user_sql(String ci, String name, String last_name, Date birth_date, Engender gender, String dir, String phone,
                              String username, String password, Enrol rol, String user_ci)
  {
    try
    {
      _con.setAutoCommit(false);
      PreparedStatement statement;
      System.out.println("Antes de commit1");
      statement = _con.prepareStatement(
          "insert into person (ci, name, last_name, birth_date, gender, dir, phone)  values ('" + ci + "', '" + name + "', '" + last_name + "', '" + birth_date + "','" + gender
              .toString() + "' , '" + dir + "', '" + phone + "')");
      
      
      statement.executeUpdate();
      System.out.println("Antes de commit2");
      
      statement =
          _con.prepareStatement("insert into user (username, password, rol, user_ci) values " + "('" + username + "','" + password + "','" + rol + "','" + user_ci + "')");
      
      statement.executeUpdate();
      
      System.out.println("Antes de commit3");
      _con.commit();
    } catch(SQLException ex)
    {
      try
      {
        _con.rollback();
      } catch(SQLException ex1)
      {
        System.err.println("Transaction failed");
        return false;
      }
      
      System.out.println(ex.getMessage());
    }
    return true;
  }
  
  public User get_user_by_ci(String ci) throws SQLException
  {
    
    ResultSet rs = this.execute_query(
        "select p.ci, p.name, p.last_name, p.birth_date, p.gender, p.dir, p.phone, u.username, u.pass, u.rol from user u join person p on u.user_ci = p.ci where u.user_ci = '" + ci + "'");
    
    if(rs.next())
    {
      //String ci1 = rs.getString("ci");
      String name = rs.getString("name");
      String last_name = rs.getString("last_name");
      Date birth_date = rs.getDate("birth_date");
      Engender gender = Engender.Masculino;
      // String g = rs.getString("gender");
      
      if(rs.getString("gender").equals("Masculino"))
        gender = Engender.Masculino;
      else if(rs.getString("gender").equals("Femenino"))
        gender = Engender.Femenino;
      
      String dir = rs.getString("dir");
      String phone = rs.getString("phone");
      
      String username = rs.getString("username");
      String pass = rs.getString("pass");
      Enrol rol = Enrol.Administrador;
      
      if(rs.getString("rol").equals("Administrador"))
        rol = Enrol.Administrador;
      else if(rs.getString("rol").equals("Gerente"))
        rol = Enrol.Gerente;
      else if(rs.getString("rol").equals("Recepcion"))
        rol = Enrol.Recepcion;
      
      
      return new User(ci, name, last_name, birth_date, gender, dir, phone, username, pass, rol);
      
    }
    return null;
  }
  
  
  
  
  
  
  
  
}
