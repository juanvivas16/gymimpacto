package data_model;

import java.sql.Date;

public class Person
{
  private String ci;
  private String name;
  private String last_name;
  private Date birth_date;
  private Engender gender;
  private String dir;
  private String phone;
  
  public Person( ) {}
  
  public Person(String ci, String name, String last_name, Date birth_date, Engender gender, String dir, String phone)
  {
    this.ci = ci;
    this.name = name;
    this.last_name = last_name;
    this.birth_date = birth_date;
    this.gender = gender;
    this.dir = dir;
    this.phone = phone;
  }
  
  public String getCi( )
  {
    return ci;
  }
  
  public Person setCi(String ci)
  {
    this.ci = ci;
    return this;
  }
  
  public String getName( )
  {
    return name;
  }
  
  public Person setName(String name)
  {
    this.name = name;
    return this;
  }
  
  public String getLast_name( )
  {
    return last_name;
  }
  
  public Person setLast_name(String last_name)
  {
    this.last_name = last_name;
    return this;
  }
  
  public Date getBirth_date( )
  {
    return birth_date;
  }
  
  public Person setBirth_date(Date birth_date)
  {
    this.birth_date = birth_date;
    return this;
  }
  
  public Engender getGender( )
  {
    return gender;
  }
  
  public Person setGender(Engender gender)
  {
    this.gender = gender;
    return this;
  }
  
  public String getDir( )
  {
    return dir;
  }
  
  public Person setDir(String dir)
  {
    this.dir = dir;
    return this;
  }
  
  public String getPhone( )
  {
    return phone;
  }
  
  public Person setPhone(String phone)
  {
    this.phone = phone;
    return this;
  }
  
  @Override
  public String toString( )
  {
    return "Person{" + "ci='" + ci + '\'' + ", name='" + name + '\'' + ", last_name='" + last_name + '\'' + ", birth_date=" + birth_date + ", gender='" + gender + '\'' + ", dir='" + dir + '\'' + ", phone='" + phone + '\'' + '}';
  }
}
