package data_model;

import java.sql.Date;

/**
 * Created by juancho on 04/12/16.
 */
public class Customer extends Person
{
  private Date init_date;

  public Customer() {}

  public Customer(Date init_date)
  {
    this.init_date = init_date;
  }

  public Customer(String ci, String name, String last_name, Date birth_date, Engender gender, String dir, String phone, Date init_date)
  {
    super(ci, name, last_name, birth_date, gender, dir, phone);
    this.init_date = init_date;
  }

  public Date getInit_date()
  {
    return init_date;
  }

  public void setInit_date(Date init_date)
  {
    this.init_date = init_date;
  }

  @Override
  public String toString()
  {
    return super.toString() + "Customer{" + "init_date=" + init_date + "} ";
  }
}