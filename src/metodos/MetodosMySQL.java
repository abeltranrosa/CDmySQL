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
    
   // ConexionMySQL mysql = new ConexionMySQL();
  //java.sql.Connection con = Conectar();
        //ConexionMySQL cc=new ConexionMySQL();
   //Connection con=mysql.Conectar();
   
   
   public String bd="mmoureperez";
    public String url="jdbc:mysql://10.0.0.254/"+bd;
    public String user="mmoureperez";
    public String pass="mmoureperez";
    Connection con=null;
    Statement cmd = null;
    
   public void Conectar(){
       
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            con= (Connection) DriverManager.getConnection(this.url,this.user,this.pass);
        }catch(Exception ex){
            System.out.println("error "+ex.getLocalizedMessage());        
        }
        //return link;
    }
   
    
    public void insertar(String nomTabla,String nomColum, String valores){
        try {
            PreparedStatement st= con.prepareStatement("INSERT INTO "+nomTabla+" ("+nomColum+") VALUES("+valores+")");
       
       st.executeUpdate();
       
        } catch (SQLException ex) {
            System.out.println("Error al insertar "+ex.getMessage());
        }
    }
    public void actualizar(String nomTabla, String ID,String datosActualizar,String datosNuevos){
        try {
            PreparedStatement st= con.prepareStatement("UPDATE "+ nomTabla+" SET "+datosActualizar+"='"+datosNuevos+"' WHERE ID='"+ID+"'");
            st.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error al actualizar "+ex.getMessage() );
        }
    }
    public void borrar(String nomTabla, int ID){
         try {
            PreparedStatement st=con.prepareStatement("DELETE FROM " +nomTabla+" WHERE ID="+ID);
       st.executeUpdate();
       
        
        } catch (SQLException ex) {
            System.out.println("Error al borrar "+ex.getMessage());
        }
    }
    
    public void consultaDatos(String nomTabla,int columnas, String datosMostrar){
       String ac="";
        try {
            Statement st= con.createStatement();
               ResultSet rs= st.executeQuery("SELECT "+datosMostrar+" FROM "+nomTabla);
              String [] datos= new String[columnas];
        while(rs.next()){
          
            for (int i = 0; i < datos.length; i++) {
                datos[i]=rs.getString(i+1);
                 ac= ac +" "+datos[i];
                
            }
            System.out.println(ac);
              }
                   
        } catch (SQLException ex) {
            System.out.println("Error en la visualizacion "+ex.getMessage());
        }
    }
    public void desconectar(){
        try {
            con.close();
            //cn.close();
        } catch (SQLException ex) {
            System.out.println("error "+ex.getMessage());
        }
    }
}
