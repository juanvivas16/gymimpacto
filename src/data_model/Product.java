package data_model;


/**
 * Created by juancho on 04/12/16.
 */
public class Product
{
  private long id;
  private String description;
  private double price;
  private long quantity_available;
  
  public Product( ) {}
  
  public Product(String description, double price, long quantity_available)
  {
    this.description = description;
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
  
  public String getDescription( )
  {
    return description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
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
    return "Product{" + "id=" + id + ", description='" + description + '\'' + ", price=" + price + ", quantity=" + quantity_available + '}';
  }
}
