package data_model;

/**
 * Created by juancho on 05/12/16.
 */

public class Supplier_product
{

  private long product_id;
  private String supplier_rif;
  
  public Supplier_product() {}
  
  public Supplier_product(long product_id, String supplier_rif)
  {
    this.product_id = product_id;
    this.supplier_rif = supplier_rif;
  }
  
  public long getProduct_id( )
  {
    return product_id;
  }
  
  public void setProduct_id(long product_id)
  {
    this.product_id = product_id;
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
    return "Supplier_product{" + "product_id=" + product_id + ", supplier_rif='" + supplier_rif + '\'' + '}';
  }
}
