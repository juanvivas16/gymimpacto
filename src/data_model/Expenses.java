package data_model;

import java.sql.Date;

/**
 * Created by juancho on 04/12/16.
 */

public class Expenses
{
  private long id;
  private String description;
  private Date date;
  private double total;
  private String user_id;
  
  public Expenses() {}
  
  public Expenses(long id, String description, Date date, double total, String user_id)
  {
    this.id = id;
    this.description = description;
    this.date = date;
    this.total = total;
    this.user_id = user_id;
  }
  
  public long getId( )
  {
    return id;
  }
  
  public void setId(long id)
  {
    this.id = id;
  }
  
  public String getDescription( )
  {
    return description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
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

  public String getUser_id() {
    return user_id;
  }

  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }

  @Override
  public String toString() {
    return "Expenses{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", date=" + date +
            ", total=" + total +
            ", user_id='" + user_id + '\'' +
            '}';
  }
}
