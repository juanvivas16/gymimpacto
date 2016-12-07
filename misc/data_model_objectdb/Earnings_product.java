package data_model;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Created by juancho on 04/12/16.
 */
@Entity
public class Earnings_product
{
  @EmbeddedId private EarningproductID id;
  private long quantity;
  
  public Earnings_product( ) {}
  
  public Earnings_product(EarningproductID id, long quantity)
  {
    this.id = id;
    this.quantity = quantity;
  }
  
  public EarningproductID getId( )
  {
    return id;
  }
  
  public void setId(EarningproductID id)
  {
    this.id = id;
  }
  
  public long getQuantity( )
  {
    return quantity;
  }
  
  public void setQuantity(long quantity)
  {
    this.quantity = quantity;
  }
  
  @Override
  public String toString( )
  {
    return "Income_product{" + "id=" + id + ", quantity=" + quantity + '}';
  }
}

@Embeddable class EarningproductID
{
  long earning_id;
  long product_id;
  
  public EarningproductID( ) {}
  
  public EarningproductID(long earning_id, long product_id)
  {
    this.earning_id = earning_id;
    this.product_id = product_id;
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
    return "EarningproductID{" + "earning_id=" + earning_id + ", product_id=" + product_id + '}';
  }
}
