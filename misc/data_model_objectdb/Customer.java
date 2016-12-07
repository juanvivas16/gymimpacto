package data_model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by juancho on 04/12/16.
 */
@Entity
public class Customer extends Person
{
  @Id private String ci;
  private Date ingress_date;
  
  public Customer( ) {}
  
  public Customer(String ci, String name, String last_name, Date birth_date, String gender, String dir, String phone,
                  Date ingress_date)
  {
    super(ci, name, last_name, birth_date, gender, dir, phone);
    this.ingress_date = ingress_date;
  }
  
  public Date getIngress_date( )
  {
    return ingress_date;
  }
  
  public Customer setIngress_date(Date ingress_date)
  {
    this.ingress_date = ingress_date;
    return this;
  }
  
  @Override
  public String toString( )
  {
    return super.toString() + "Customer{" + "ingress_date=" + ingress_date + "} ";
  }
  
  
}