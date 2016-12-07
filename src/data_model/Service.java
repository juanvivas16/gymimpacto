package data_model;

import java.sql.Date;

/**
 * Created by juancho on 04/12/16.
 */

public class Service
{
  private long income_id;
  private Service_type type_s;
  private Date init_date;
  private String customer_ci;
  
  public Service( ) {}
  
  public Service(long income_id, Service_type type_s, Date init_date, String customer_ci)
  {
    this.income_id = income_id;
    this.type_s = type_s;
    this.init_date = init_date;
    this.customer_ci = customer_ci;
  }
  
  public long getIncome_id( )
  {
    return income_id;
  }
  
  public void setIncome_id(long income_id)
  {
    this.income_id = income_id;
  }
  
  public Service_type getType_s( )
  {
    return type_s;
  }
  
  public void setType_s(Service_type type_s)
  {
    this.type_s = type_s;
  }
  
  public Date getInit_date( )
  {
    return init_date;
  }
  
  public void setInit_date(Date init_date)
  {
    this.init_date = init_date;
  }
  
  public String getCustomer_ci( )
  {
    return customer_ci;
  }
  
  public void setCustomer_ci(String customer_ci)
  {
    this.customer_ci = customer_ci;
  }
  
  @Override
  public String toString( )
  {
    return "Service{" + "income_id=" + income_id + ", type_s=" + type_s + ", init_date=" + init_date + ", customer_ci='" + customer_ci + '\'' + '}';
  }
}

