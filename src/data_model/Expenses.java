package data_model;

import java.sql.Date;

/**
 * Created by juancho on 04/12/16.
 */

public class Expenses
{
  private long id;
  private String desc;
  private Date date;
  private double total;
  private String user_id;
  
  public Expenses() {}
  
  public Expenses(String desc, Date date, double total, String user_id)
  {
    this.desc = desc;
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
  
  public String getDesc( )
  {
    return desc;
  }
  
  public void setDesc(String desc)
  {
    this.desc = desc;
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
            ", desc='" + desc + '\'' +
            ", date=" + date +
            ", total=" + total +
            ", user_id='" + user_id + '\'' +
            '}';
  }
}
