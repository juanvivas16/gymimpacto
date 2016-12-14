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
  private String supplier_rif;
  
  
  public Product( ) {}
  
  public Product(long id, String description, double price, long quantity_available, String supplier_rif)
  {
    this.id = id;
    this.description = description;
    this.price = price;
    this.quantity_available = quantity_available;
    this.supplier_rif = supplier_rif;
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
  
  public String getSupplier_rif( )
  {
    return supplier_rif;
  }
  
  public void setSupplier_rif(String supplier_rif)
  {
    this.supplier_rif = supplier_rif;
  }
  
  @Override
  public String toString( )
  {
    return "Product{" + "id=" + id + ", description='" + description + '\'' + ", price=" + price + ", quantity_available=" + quantity_available + ", supplier_rif='" + supplier_rif + '\'' + '}';
  }
}
