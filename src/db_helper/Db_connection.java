package db_helper;


import data_model.Egender;
import data_model.Person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.util.*;


public class Db_connection
{
  private Connection _con = null;
  private String _url;
  private String _user;
  private String _pass;
  
  
  public Db_connection()
  {
    this.init_connection();
  }
  
  private boolean get_data_from_prop()
  {
    Properties __prop = new Properties();
    FileInputStream __in = null;
    try
    {
      __in = new FileInputStream("src/etc/db_config.properties");
      //System.out.println("properties readed");
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
      //System.out.println("properties faild");
      return false;
    }
    
    try
    {
      __prop.load(__in);
      this._url  = __prop.getProperty("url");
      this._user = __prop.getProperty("user");
      this._pass = __prop.getProperty("pass");
      
      __in.close();
      
      //System.out.println("properties loaded"+ _url + _user + _pass);
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return false;
    }
    
    return true;
  }
  
  
  
  private boolean init_connection()
  {
    if (get_data_from_prop())
    {
      try
      {
        _con = DriverManager.getConnection(this._url, this._user, this._pass);
        
        //System.out.println("connected to db");
        
        
      } catch (SQLException e)
      {
        //System.out.println("Faild to connect to db");
        
        e.printStackTrace();
        return false;
      }
      return true;
    }
    else
      return false;
  }
  
  
  
  public Connection get_connection()
  {
    if (init_connection())
    {
      return this._con;
    }
    else
      return null;
  }
  
  
  public boolean close_connection()
  {
    if (this._con != null)
    {
      try
      {
        _con.close();
      }
      catch (SQLException e)
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
      Statement __st =  this._con.createStatement();
      return __st.executeQuery(sql_query);
      
    } catch (SQLException e)
    {
      e.printStackTrace();
    }
    return  null;
  }
  
  
  public int execute_update(String sql_update_query)
  {
    try
    {
      Statement __st =  this._con.createStatement();
      return __st.executeUpdate(sql_update_query);
      
    } catch (SQLException e)
    {
      e.printStackTrace();
    }
    return  -1;
  }

    public Person get_person_by_id(String id) throws SQLException
    {
      ResultSet rs = this.execute_query("SELECT * from Person WHERE Person.id=" + id);
      if(rs.next())
      {
         String ci = rs.getString("ci");
         String name = rs.getString("name");
         String last_name = rs.getString("last_name");
         Date birth_date = rs.getDate("birth_date");
         Egender gender = Egender.Masculino;
         
         if(rs.getString("gender").equals("Masculino"))
           gender = Egender.Masculino;
         else if(rs.getString("gender").equals("Femenino"))
           gender = Egender.Femenino;
        
         String dir = rs.getString("dir");
         String phone = rs.getString("phone");
  
         return new Person(ci, name, last_name, birth_date, gender, dir, phone);
      }
      return null;
    }

/* EJEMPLO MUDAFAR GALENO
    public Person get_person_by_id(String id) throws SQLException
    {
        ResultSet rs = this.execute_query("select * from Person where Person.id=" + id);

        if (rs.next())
        {
            Long id_long = rs.getLong("id");
            String name = rs.getString("name");
            String lname = rs.getString("last_name");
            String gender = rs.getString("gender");
            Date bdate = rs.getDate("birth_date");
            Date rdate = rs.getDate("reg_date");
            String direction = rs.getString("direction");
            String phone_num = rs.getString("phone_num");

            return new Person(id_long, name, lname, gender, bdate, rdate , direction, phone_num);

        }


        return null;

    }
*/
}
