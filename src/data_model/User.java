package data_model;

import java.sql.Date;

enum Erol_type {Recepcion, Administrador}
/**
 * Created by juancho on 04/12/16.
 */
public class User extends Person
{
  private String username;
  private String password;
  private Erol_type rol;
  
  public User( ) {}
  
  public User(String ci, String name, String last_name, Date birth_date, Egender gender, String dir, String phone,
              String username, String password, Erol_type rol)
  {
    super(ci, name, last_name, birth_date, gender, dir, phone);
    this.username = username;
    this.password = password;
    this.rol = rol;
  }
  
  public String getUsername( )
  {
    return username;
  }
  
  public User setUsername(String username)
  {
    this.username = username;
    return this;
  }
  
  public String getPassword( )
  {
    return password;
  }
  
  public User setPassword(String password)
  {
    this.password = password;
    return this;
  }
  
  public Erol_type getRol( )
  {
    return rol;
  }
  
  public User setRol(Erol_type rol)
  {
    this.rol = rol;
    return this;
  }
  
  @Override
  public String toString( )
  {
    return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", rol=" + rol + "} " + super
        .toString();
  }
}
