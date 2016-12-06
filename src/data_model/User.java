package data_model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by juancho on 04/12/16.
 */
@Entity
public class User extends Person
{
  @Id private String username;
  private String password;
  private int rol;
  
  public User( ) {}
  
  public User(String ci, String name, String last_name, Date birth_date, String gender, String dir, String phone,
              String username, String password, int rol)
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
  
  public int getRol( )
  {
    return rol;
  }
  
  public User setRol(int rol)
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
