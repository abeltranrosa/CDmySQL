/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package metodos; 


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




/**
 *
 * @author mmoureperez
 */
public class MetodosMySQL {
    
   
    Connection con=null;
    Statement cmd = null;
    /**
     * Metodo para conectar con la BD
     * @param url url de la base de datos
     * @param user usuario
     * @param pass contraseña
     */
   public void Conectar(String url, String user, String pass){
       
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            con= (Connection) DriverManager.getConnection(url,user,pass);
        }catch(Exception ex){
            System.out.println("error "+ex.getLocalizedMessage());        
        }
      
    }
   
    /**
     * Metodo para insertar filas en la tabla de la BD
     * @param nomTabla nombre de la tabla donde queremos insertar
     * @param nomColum nombre de los campos de la tabla separado por comas
     * @param valores valores para insertar separado por comas
     */
    public void insertar(String nomTabla,String nomColum, String valores){
        try {
            PreparedStatement st= con.prepareStatement("INSERT INTO "+nomTabla+" ("+nomColum+") VALUES("+valores+")");
       
       st.executeUpdate();
       
        } catch (SQLException ex) {
            System.out.println("Error al insertar "+ex.getMessage());
        }
    }
    /**
     * Metodo para actualizar algun cambio en las tablas de la BD
     * @param nomTabla nombre de la tabla para actualizar
     * @param ID identificador de la fila
     * @param datosActualizar nombre del campo a actualizar
     * @param datosNuevos  datos nuevos que queremos meter en el campo seleccionado
     */
    public void actualizar(String nomTabla, String ID,String datosActualizar,String datosNuevos){
        try {
            PreparedStatement st= con.prepareStatement("UPDATE "+ nomTabla+" SET "+datosActualizar+"='"+datosNuevos+"' WHERE ID='"+ID+"'");
            st.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error al actualizar "+ex.getMessage() );
        }
    }
    /**
     * Metodo para borrar alguna fila de la tabla
     * @param nomTabla nombre de la tabla de la cual queremos borrar alguna fila
     * @param ID identificador del la fila o filas que queremos borrar
     */
    public void borrar(String nomTabla, int ID){
         try {
            PreparedStatement st=con.prepareStatement("DELETE FROM " +nomTabla+" WHERE ID="+ID);
       st.executeUpdate();
       
        
        } catch (SQLException ex) {
            System.out.println("Error al borrar "+ex.getMessage());
        }
    }
    /**
     * Metodo para consultar datos de la tabla
     * @param nomTabla nombre de la tabla que queremos mostrar
     * @param columnas numero de columnas que tiene la tabla
     * @param datosMostrar nombre de los campos que queremos mostrar
     */
    public String consultaDatos(String nomTabla,int columnas, String datosMostrar){
       String ac="";
        try {
            Statement st= con.createStatement();
               ResultSet rs= st.executeQuery("SELECT "+datosMostrar+" FROM "+nomTabla);
              String [] datos= new String[columnas-1];
        while(rs.next()){
          
            for (int i = 0; i < datos.length; i++) {
                datos[i]=rs.getString(i+1);
                 ac= ac +" "+datos[i];
                
            }
                        }
                   
        } catch (SQLException ex) {
            System.out.println("Error en la visualizacion "+ex.getMessage());
        }
        return ac;
        
    }
    /**
     * Metodo para desconectar de la base de datos
     */
    public void desconectar(){
        try {
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("error "+ex.getMessage());
        }
    }
}
