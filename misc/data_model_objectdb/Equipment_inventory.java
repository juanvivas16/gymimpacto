package data_model;

import javax.persistence.*;
import java.sql.Date;

enum Estate {Activo, Inactivo}

/**
 * Created by juancho on 04/12/16.
 */
@Entity
public class Equipment_inventory
{
  @Id @GeneratedValue private long id;
  private String name;
  private String model;
  private Date ad_date;
  private double cost;
  @Enumerated(EnumType.STRING) private Estate state;
  
  public Equipment_inventory() {}
  
  public Equipment_inventory(String name, String model, Date ad_date, double cost, Estate state)
  {
    this.name = name;
    this.model = model;
    this.ad_date = ad_date;
    this.cost = cost;
    this.state = state;
  }
  
  public long getId( )
  {
    return id;
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
  
  public Estate getState( )
  {
    return state;
  }
  
  public void setState(Estate state)
  {
    this.state = state;
  }
  
  @Override
  public String toString( )
  {
    return "Equipment_inventory{" + "id=" + id + ", name='" + name + '\'' + ", model='" + model + '\'' + ", ad_date=" + ad_date + ", cost=" + cost + ", state=" + state + '}';
  }
}

