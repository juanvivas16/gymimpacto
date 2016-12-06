package data_model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by juancho on 04/12/16.
 */
@Entity
public class Expenses
{
  @Id @GeneratedValue private long id;
  private String name;
  private Date date;
  private double total;
  
  public Expenses() {}
  
  public Expenses(String name, Date date, double total)
  {
    this.name = name;
    this.date = date;
    this.total = total;
  }
  
  public long getId( )
  {
    return id;
  }
  
  public String getName( )
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public Date getDate( )
  {
    return date;
  }
  
  public void setDate(Date date)
  {
    this.date = date;
  }
  
  public double getTotal( )
  {
    return total;
  }
  
  public void setTotal(double total)
  {
    this.total = total;
  }
  
  @Override
  public String toString( )
  {
    return "Expenses{" + "id=" + id + ", name='" + name + '\'' + ", date=" + date + ", total=" + total + '}';
  }
}
