package data_model;


/**
 * Created by juancho on 04/12/16.
 */
public class Product
{
  private long id;
  private String desc;
  private double price;
  private long quantity_available;
  
  public Product( ) {}
  
  public Product(String desc, double price, long quantity_available)
  {
    this.desc = desc;
    this.price = price;
    this.quantity_available = quantity_available;
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
  
  public double getPrice( )
  {
    return price;
  }
  
  public void setPrice(double price)
  {
    this.price = price;
  }
  
  public long getQuantity_available( )
  {
    return quantity_available;
  }
  
  public void setQuantity_available(long quantity_available)
  {
    this.quantity_available = quantity_available;
  }
  
  @Override
  public String toString( )
  {
    return "Product{" + "id=" + id + ", desc='" + desc + '\'' + ", price=" + price + ", quantity=" + quantity_available + '}';
  }
}
