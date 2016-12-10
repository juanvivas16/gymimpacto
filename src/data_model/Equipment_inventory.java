package data_model;

import java.sql.Date;

/**
 * Created by juancho on 04/12/16.
 */

public class Equipment_inventory
{
  private long id;
  private String name;
  private String model;
  private String desc;
  private Date ad_date;
  private double cost;
  private int quantity;
  private Enstate state;
  private String user_id;
  
  public Equipment_inventory() {}
  
  public Equipment_inventory(long id, String name, String model, String desc, Date ad_date, double cost, int quantity,
                             Enstate state, String user_id)
  {
    this.id = id;
    this.name = name;
    this.model = model;
    this.desc = desc;
    this.ad_date = ad_date;
    this.cost = cost;
    this.quantity = quantity;
    this.state = state;
    this.user_id = user_id;
  }
  
  public long getId( )
  {
    return id;
  }
  
  public void setId(long id)
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
  
  public String getModel( )
  {
    return model;
  }
  
  public void setModel(String model)
  {
    this.model = model;
  }
  
  public String getDesc( )
  {
    return desc;
  }
  
  public void setDesc(String desc)
  {
    this.desc = desc;
  }
  
  public Date getAd_date( )
  {
    return ad_date;
  }
  
  public void setAd_date(Date ad_date)
  {
    this.ad_date = ad_date;
  }
  
  public double getCost( )
  {
    return cost;
  }
  
  public void setCost(double cost)
  {
    this.cost = cost;
  }
  
  public int getQuantity( )
  {
    return quantity;
  }
  
  public void setQuantity(int quantity)
  {
    this.quantity = quantity;
  }
  
  public Enstate getState( )
  {
    return state;
  }
  
  public void setState(Enstate state)
  {
    this.state = state;
  }

  public String getUser_id() {
    return user_id;
  }

  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }

  @Override
  public String toString() {
    return "Equipment_inventory{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", model='" + model + '\'' +
            ", desc='" + desc + '\'' +
            ", ad_date=" + ad_date +
            ", cost=" + cost +
            ", quantity=" + quantity +
            ", state=" + state +
            ", user_id='" + user_id + '\'' +
            '}';
  }
}

