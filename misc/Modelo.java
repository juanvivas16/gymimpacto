/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javier
 */
public class Modelo {
    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado = null;
    
    public Modelo(){
        
        try {				
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/consultorio", "root", "javier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	
    }
    
    
    boolean insertar(String ci, String nombre, String apellido, String email, 
            String rif, String telHab, String telMov, String pass, String tiempo) {
    
        boolean res = true;
        int dev0, dev1;
        try {
            conexion.setAutoCommit(false);
            sentencia = conexion.prepareStatement("insert into persona (ci, nombre,apellido,rif,email, telHab, telMov) "
                    + "values ('"+ci+"','"+nombre+"','"+apellido+"', '"+rif+"', '"+email+
                    "','"+telHab+"', '"+telMov+"')");
            
            dev0 = sentencia.executeUpdate();
            
            
             sentencia = conexion.prepareStatement("insert into doctor (ci, password,tiempoPromedio) values "
                     +"('"+ci+"','"+pass+"','"+ tiempo+"')");
            
            dev1=sentencia.executeUpdate();
            
            if(dev0==0 || dev1 ==0)
                res=false;
            
            conexion.commit();
        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            res=false;
        }
        
        return res;
    }
    
    void cerrarConexion(){
        try {
            if(resultado != null)
                resultado.close();
            conexion.close();
            if(sentencia != null)
                sentencia.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    ResultSet buscar(String ci) {
        ResultSet r=null;
        try {
            sentencia = conexion.prepareStatement("select * from persona Pe, doctor Pa"
                    + " where Pe.ci = Pa.ci and Pe.ci=?");
            
            sentencia.setString(1,ci);
            
            r = sentencia.executeQuery();
             
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return r;
    }

    boolean eliminar(String ci){
        boolean res = true;
        int b =0 ;
        
        try {
            sentencia = conexion.prepareStatement("update persona set eliminado='1' where ci=? and eliminado='0'");
            sentencia.setString(1,ci);
            b=sentencia.executeUpdate();
            
            if(b==0)
                res=false;
        } catch (SQLException ex) {
            //Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println(ex.getMessage());
            res=false;
        }
            
        return res;
    }

    boolean actualizar(String ci, String nombre, String apellido, String email, String rif, String telHab, String telMov, String pass, String tiempo) {
        boolean res = true;
        int dev0, dev1;
        try {
            conexion.setAutoCommit(false);
            sentencia = conexion.prepareStatement("update persona set nombre=?, apellido=?,"
                    + " rif=?,email=?, telHab=?, telMov=?, eliminado=? where ci=?");
            
            sentencia.setString(1,nombre);
            sentencia.setString(2,apellido);
            sentencia.setString(3,rif);
            sentencia.setString(4,email);
            sentencia.setString(5,telHab);
            sentencia.setString(6,telMov);
            sentencia.setString(7,"0");
            sentencia.setString(8,ci);
            
            dev0 = sentencia.executeUpdate();
            
            
            sentencia = conexion.prepareStatement("update doctor set password=?,tiempoPromedio=?  where ci=?");
            sentencia.setString(1,pass);
            sentencia.setString(2,tiempo);
            sentencia.setString(3,ci);
            
            dev1=sentencia.executeUpdate();
            
            if(dev0==0 || dev1 ==0)
                res=false;
            
            conexion.commit();
        } catch (SQLException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            res=false;
        }
        
        return res;
    }

    ResultSet listar() {
        ResultSet r=null;
        try {
            sentencia = conexion.prepareStatement("select * from persona Pe, doctor Pa"
                    + " where Pe.ci = Pa.ci and Pe.eliminado='0'");
            
            r = sentencia.executeQuery();
             
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return r;
    }

    
}
