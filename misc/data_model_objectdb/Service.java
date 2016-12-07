package data_model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

enum service_type {Mensual, Quincenal, Diario}

/**
 * Created by juancho on 04/12/16.
 */
@Entity
public class Service implements Serializable
{
  @EmbeddedId private ServiceId id;
  @Enumerated(EnumType.STRING) private service_type type_s;
  private Date init_date;
  
  public Service( ) {}
  
  public Service(ServiceId id, service_type type_s, Date init_date)
  {
    this.id = id;
    this.type_s = type_s;
    this.init_date = init_date;
  }
  
  public ServiceId getId( )
  {
    return id;
  }
  
  public Service setId(ServiceId id)
  {
    this.id = id;
    return this;
  }
  
  public service_type getType_s( )
  {
    return type_s;
  }
  
  public Service setType_s(service_type type_s)
  {
    this.type_s = type_s;
    return this;
  }
  
  public Date getInit_date( )
  {
    return init_date;
  }
  
  public Service setInit_date(Date init_date)
  {
    this.init_date = init_date;
    return this;
  }
  
  @Override
  public String toString( )
  {
    return "Service{" + "id=" + id + ", type_s=" + type_s + ", init_date=" + init_date + '}';
  }
}

@Embeddable
class ServiceId {
  int earningId;
  String ci_customer;
  
  public ServiceId( ) {}
  
  public int getEarningId( )
  {
    return earningId;
  }
  
  public ServiceId setEarningId(int earningId)
  {
    this.earningId = earningId;
    return this;
  }
  
  public String getCi_customer( )
  {
    return ci_customer;
  }
  
  public ServiceId setCi_customer(String ci_customer)
  {
    this.ci_customer = ci_customer;
    return this;
  }
  
  @Override
  public String toString( )
  {
    return "ServiceId{" + "earningId=" + earningId + ", ci_customer='" + ci_customer + '\'' + '}';
  }
}