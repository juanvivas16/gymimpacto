package data_model;


/**
 * Created by juancho on 04/12/16.
 */
public class Income_product
{
  private long quantity;
  private long income_id;
  private long product_id;
  
  
  public Income_product( ) {}
  
  public Income_product(long quantity, long income_id, long product_id)
  {
    this.quantity = quantity;
    this.income_id = income_id;
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
  
  public long getIncome_id( )
  {
    return income_id;
  }
  
  public void setIncome_id(long income_id)
  {
    this.income_id = income_id;
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
    return "Income_product{" + "quantity=" + quantity + ", income_id=" + income_id + ", product_id=" + product_id + '}';
  }
}


