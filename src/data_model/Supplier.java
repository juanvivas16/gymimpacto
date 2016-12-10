package data_model;


/**
 * Created by juancho on 04/12/16.
 */

public class Supplier
{
  private String id;
  private String name;
  private String phone;
  private String dir;
  private String product_desc;
  private String user_id;
  
  public Supplier() {}
  
  public Supplier(String id, String name, String phone, String dir, String product_desc, String user_id)
  {
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.dir = dir;
    this.product_desc = product_desc;
    this.user_id = user_id;
  }
  
  public String getId( )
  {
    return id;
  }
  
  public void setId(String id)
  {
    this.id = id;
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

  public String getUser_id() {
    return user_id;
  }

  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }

  @Override
  public String toString() {
    return "Supplier{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", dir='" + dir + '\'' +
            ", product_desc='" + product_desc + '\'' +
            ", user_id='" + user_id + '\'' +
            '}';
  }
}

