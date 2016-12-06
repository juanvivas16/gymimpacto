package data_model;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Created by juancho on 05/12/16.
 */
@Entity
public class Supplier_product
{
  @EmbeddedId SupplierProductID id;
  
  public Supplier_product() {}
  
  public Supplier_product(SupplierProductID id)
  {
    this.id = id;
  }
  
  public SupplierProductID getId( )
  {
    return id;
  }
  
  public void setId(SupplierProductID id)
  {
    this.id = id;
  }
  
  @Override
  public String toString( )
  {
    return "Supplier_product{" + "id=" + id + '}';
  }
}

@Embeddable class SupplierProductID
{
  long product_id;
  String supplier_rif;
  
  public SupplierProductID( ) {}
  
  public SupplierProductID(long product_id, String supplier_rif)
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
    return "SupplierProductID{" + "product_id=" + product_id + ", supplier_rif='" + supplier_rif + '\'' + '}';
  }
}