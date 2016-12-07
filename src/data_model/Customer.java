package data_model;

import java.sql.Date;

/**
 * Created by juancho on 04/12/16.
 */
public class Customer extends Person
{
  private Date income_date;
  
  public Customer( ) {}
  
  public Customer(String ci, String name, String last_name, Date birth_date, Engender gender, String dir, String phone,
                  Date income_date)
  {
    super(ci, name, last_name, birth_date, gender, dir, phone);
    this.income_date = income_date;
  }
  
  public Date getIncome_date( )
  {
    return income_date;
  }
  
  public void setIncome_date(Date income_date)
  {
    this.income_date = income_date;
  }
  
  @Override
  public String toString( )
  {
    return super.toString() + "Customer{" + "income_date=" + income_date + "} ";
  }
}