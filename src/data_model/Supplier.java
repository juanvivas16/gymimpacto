package data_model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by juancho on 04/12/16.
 */
@Entity
public class Supplier
{
  @Id private String rif;
  private String name;
  private String phone;
  private String dir;
  private String product_desc;
  
  public Supplier() {}
  
  public Supplier(String rif, String name, String phone, String dir, String product_desc)
  {
    this.rif = rif;
    this.name = name;
    this.phone = phone;
    this.dir = dir;
    this.product_desc = product_desc;
  }
  
  public String getRif( )
  {
    return rif;
  }
  
  public void setRif(String rif)
  {
    this.rif = rif;
  }
  
  public String getName( )
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getPhone( )
  {
    return phone;
  }
  
  public void setPhone(String phone)
  {
    this.phone = phone;
  }
  
  public String getDir( )
  {
    return dir;
  }
  
  public void setDir(String dir)
  {
    this.dir = dir;
  }
  
  public String getProduct_desc( )
  {
    return product_desc;
  }
  
  public void setProduct_desc(String product_desc)
  {
    this.product_desc = product_desc;
  }
  
  @Override
  public String toString( )
  {
    return "Supplier{" + "rif='" + rif + '\'' + ", name='" + name + '\'' + ", phone='" + phone + '\'' + ", dir='" + dir + '\'' + ", product_desc='" + product_desc + '\'' + '}';
  }
}

