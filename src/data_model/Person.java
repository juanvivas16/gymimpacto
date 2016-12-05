package data_model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by juancho on 05/12/16.
 */

@Entity
public class Person
{
  @Id private String ci;
  private String name;
  private String last_name;
  private Date birth_date;
  private String gender;
  private String dir;
  private String phone;
  
  public Person( ) {}
  
  public Person(String ci, String name, String last_name, Date birth_date, String gender, String dir, String phone)
  {
    this.ci = ci;
    this.name = name;
    this.last_name = last_name;
    this.birth_date = birth_date;
    this.gender = gender;
    this.dir = dir;
    this.phone = phone;
  }
}
