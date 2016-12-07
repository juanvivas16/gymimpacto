package data_model;


/**
 * Created by juancho on 04/12/16.
 */
public class Earnings_product
{
  private long quantity;
  private long earning_id;
  private long product_id;
  
  
  public Earnings_product( ) {}
  
  public Earnings_product(long quantity, long earning_id, long product_id)
  {
    this.quantity = quantity;
    this.earning_id = earning_id;
    this.product_id = product_id;
  }
  
  public long getQuantity( )
  {
    return quantity;
  }
  
  public void setQuantity(long quantity)
  {
    this.quantity = quantity;
  }
  
  public long getEarning_id( )
  {
    return earning_id;
  }
  
  public void setEarning_id(long earning_id)
  {
    this.earning_id = earning_id;
  }
  
  public long getProduct_id( )
  {
    return product_id;
  }
  
  public void setProduct_id(long product_id)
  {
    this.product_id = product_id;
  }
  
  @Override
  public String toString( )
  {
    return "Earnings_product{" + "quantity=" + quantity + ", earning_id=" + earning_id + ", product_id=" + product_id + '}';
  }
}


