/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metodos;

import conexion.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *
 * @author mmoureperez
 */
public class MetodosMySQL {
    
    ConexionMySQL mysql = new ConexionMySQL();
  java.sql.Connection cn = mysql.Conectar();
    
    //ConexionMySQL cc=new ConexionMySQL();
   Connection con=mysql.Conectar();
    
   
    
    public void insertar(String nomTabla,String datos, String valores){
        try {
            PreparedStatement st= con.prepareStatement("INSERT INTO "+nomTabla+" ("+datos+") VALUES("+valores+")");
       
       st.executeUpdate();
       
        } catch (SQLException ex) {
            System.out.println("Error al insertar "+ex.getMessage());
        }
    }
    public void actualizar(String nomTabla, String ID,String datosActualizar){
        try {
            PreparedStatement st= con.prepareStatement("UPDATE "+ nomTabla+" SET "+datosActualizar+" WHERE ID='"+ID+"'");
            st.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error al actualizar "+ex.getMessage() );
        }
    }
    public void borrar(String nomTabla, String ID,int columnas,String datosMostrar){
         try {
            PreparedStatement st=con.prepareStatement("DELETE FROM " +nomTabla+" WHERE ID='"+ID+"'");
       st.executeUpdate();
       
        
        } catch (SQLException ex) {
            System.out.println("Error al borrar "+ex.getMessage());
        }
    }
    
    public void consultaDatos(String nomTabla,int columnas, String datosMostrar){
       
        try {
            Statement st= con.createStatement();
               ResultSet rs= st.executeQuery("SELECT "+datosMostrar+" FROM "+nomTabla);
              String [] datos= new String[columnas];
        while(rs.next()){
          
            for (int i = 0; i < datos.length; i++) {
                datos[i]=rs.getString(i+1);
            }
          }
            System.out.println(datos);
        
        } catch (SQLException ex) {
            System.out.println("Error en la visualizacion "+ex.getMessage());
        }
    }
}
