package data_model;

import java.sql.Date;

enum type_contract { Tiempo_completo, Medio_tiempo, Por_horas }
/**
 * Created by juancho on 04/12/16.
 */

public class Employee extends Person
{
  private type_contract type_c;
  private double pay;
  private String activity;
  
  public Employee() {}
  
  public Employee(String ci, String name, String last_name, Date birth_date, Egender gender, String dir, String phone,
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
  
  public Employee setType_c(type_contract type_c)
  {
    this.type_c = type_c;
    return this;
  }
  
  public double getPay( )
  {
    return pay;
  }
  
  public Employee setPay(double pay)
  {
    this.pay = pay;
    return this;
  }
  
  public String getActivity( )
  {
    return activity;
  }
  
  public Employee setActivity(String activity)
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

