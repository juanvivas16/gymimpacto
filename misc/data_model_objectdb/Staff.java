package data_model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Date;

enum type_contract { Tiempo_completo, Medio_tiempo, Por_horas }
/**
 * Created by juancho on 04/12/16.
 */
@Entity
public class Staff extends Person
{
  @Enumerated(EnumType.STRING) private type_contract type_c;
  private double pay;
  private String activity;
  
  public Staff() {}
  
  public Staff(String ci, String name, String last_name, Date birth_date, String gender, String dir, String phone,
               type_contract type_c, double pay, String activity)
  {
    super(ci, name, last_name, birth_date, gender, dir, phone);
    this.type_c = type_c;
    this.pay = pay;
    this.activity = activity;
  }
  
  public type_contract getType_c( )
  {
    return type_c;
  }
  
  public Staff setType_c(type_contract type_c)
  {
    this.type_c = type_c;
    return this;
  }
  
  public double getPay( )
  {
    return pay;
  }
  
  public Staff setPay(double pay)
  {
    this.pay = pay;
    return this;
  }
  
  public String getActivity( )
  {
    return activity;
  }
  
  public Staff setActivity(String activity)
  {
    this.activity = activity;
    return this;
  }
  
  @Override
  public String toString( )
  {
    return super.toString() + "Employee{" + "type_c=" + type_c + ", pay=" + pay + ", activity='" + activity + '\'' + "} ";
  }
}

