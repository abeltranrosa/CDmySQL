/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


/**
 *
 * @author mmoureperez
 */
public class ConexionMySQL {
    public String bd="mmoureperez";
    public String url="jdbc:mysql://10.0.0.254/"+bd;
    public String user="mmoureperez";
    public String pass="mmoureperez";
    
     Connection link=null;
    Statement cmd = null; 
    
    public Connection Conectar(){
       
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            link= (Connection) DriverManager.getConnection(this.url,this.user,this.pass);
        }catch(Exception ex){
            System.out.println("Error al conectar "+ex.getMessage());
        
        }
        return link;
    }
}
