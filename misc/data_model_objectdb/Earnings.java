package data_model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by juancho on 04/12/16.
 */
@Entity
public class Earnings
{
  @Id @GeneratedValue private long id;
  private String desc;
  private Date date;
  private double sub_total;
  private double iva;
  private double total;
  private String customer_id;
  private String user_id;
  
  public Earnings( ) {}
  
  public Earnings(String desc, Date date, double sub_total, double iva, double total, String customer_id, String user_id)
  {
    this.desc = desc;
    this.date = date;
    this.sub_total = sub_total;
    this.iva = iva;
    this.total = total;
    this.customer_id = customer_id;
    this.user_id = user_id;
  }
  
  public long getId( )
  {
    return id;
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
  
  public double getSub_total( )
  {
    return sub_total;
  }
  
  public void setSub_total(double sub_total)
  {
    this.sub_total = sub_total;
  }
  
  public double getIva( )
  {
    return iva;
  }
  
  public void setIva(double iva)
  {
    iva = 0.12;
    this.iva = iva;
  }
  
  public double getTotal( )
  {
    return total;
  }
  
  public void setTotal(double total)
  {
    this.total = total;
  }
  
  public String getCustomer_id( )
  {
    return customer_id;
  }
  
  public void setCustomer_id(String customer_id)
  {
    this.customer_id = customer_id;
  }
  
  public String getUser_id( )
  {
    return user_id;
  }
  
  public void setUser_id(String user_id)
  {
    this.user_id = user_id;
  }
  
  @Override
  public String toString( )
  {
    return "Earnings{" + "id=" + id + ", desc='" + desc + '\'' + ", date=" + date + ", sub_total=" + sub_total + ", iva=" + iva + ", total=" + total + ", customer_id='" + customer_id + '\'' + ", user_id='" + user_id + '\'' + '}';
  }
}
