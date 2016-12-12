/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author javier
 */
public class Controlador {
    
    void insertar(String nombre, String apellido, String ci,String email,
            String rif, String telHab, String telMov, String pass, String tiempo, JFrame marco){
        
        Modelo m = new Modelo();
        
        
        boolean bien = false;
        int esta = _buscar(ci);
        
        if(esta==0){
            bien = m.insertar(ci,nombre,apellido,email,rif,telHab,telMov,pass,tiempo);
        }else{
            if(esta == 1){
                bien = m.actualizar(ci,nombre,apellido,email,rif,telHab,telMov,pass,tiempo);
            }
        }
        if(bien){
            JOptionPane.showMessageDialog(marco, "Doctor Insertado Exitosamente", "Éxito", JOptionPane.PLAIN_MESSAGE);
        }else{
            if(esta == 2)
                JOptionPane.showMessageDialog(marco, "No se pudo Insertar al Doctor (Ya esta Registrado(a))", "Fallo", JOptionPane.ERROR_MESSAGE);
            else
                JOptionPane.showMessageDialog(marco, "No se pudo Insertar al Doctor", "Fallo", JOptionPane.ERROR_MESSAGE);
        }
        
        m.cerrarConexion();
    }
    
    int _buscar(String ci){
       
        Modelo m = new Modelo();
        ResultSet r=null;
        r= m.buscar(ci);
        
        int ret= 0;
        try{
            if(!r.next()){
                
            }else{
                if(r.getBoolean("eliminado") == true)
                    ret =1;
                else
                    ret=2;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            m.cerrarConexion();
        }
        return ret;
    }
    
    
    
    
    ResultSet buscar(String ci,JFrame marco){
       
        Modelo m = new Modelo();
        ResultSet r=null;
        r= m.buscar(ci);
        
        
        try{
            if(!r.next() || r.getBoolean("eliminado")==true){
                
                JOptionPane.showMessageDialog(marco, "Doctor no Encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                r.beforeFirst();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    void eliminar(String ci,JFrame marco){
        Modelo m = new Modelo();
        boolean bien = m.eliminar(ci);
        //Falta validar cuandoel ususario ya este eliminado
        if(bien){
            JOptionPane.showMessageDialog(marco, "Doctor Eliminado Exitosamente", "Éxito", JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(marco, "No se pudo Eliminar al Doctor", "Fallo", JOptionPane.ERROR_MESSAGE);
        }
        m.cerrarConexion();
    }
    
    
    ResultSet listar(JFrame marco){
       
        Modelo m = new Modelo();
        ResultSet r=null;
        r= m.listar();
        
        
        try{
            if(!r.next()){
                
                JOptionPane.showMessageDialog(marco, "No hay doctores Registrados", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                r.beforeFirst();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    
    void actualizar(String nombre, String apellido, String ci,String email,
            String rif, String telHab, String telMov, String pass, String tiempo, JFrame marco){
        
        int b = _buscar(ci);
        
        Modelo m = new Modelo();
        boolean bien = false;
        
        if(b == 2 ){
            bien = m.actualizar(ci,nombre,apellido,email,rif,telHab,telMov,pass,tiempo);
        }
        
        if(bien){
            JOptionPane.showMessageDialog(marco, "Doctor Actualizado Exitosamente", "Éxito", JOptionPane.PLAIN_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(marco, "No se pudo Actializar al Doctor", "Fallo", JOptionPane.ERROR_MESSAGE);
        }
        m.cerrarConexion();
    }
}
