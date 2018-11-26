/*Clase que tiene acceso a la base de datos empleados y contiene diferentes 
 * métodos para controlar errores y utilizar sentencias preparadas*/

package P01_MySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Activity_3 {
	
	static Connection conexion;
	
	public static void main(String[] args) {
	    
	    System.out.println("Iniciando");	    
	    try {	    	
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conexion = DriverManager.getConnection(
	        				"jdbc:mysql://www.db4free.net:3306/jorgedam",
	        				"jorgedam",	"12345678");     
	       
	        //////////--------MÉTODOS--------------///////////////////
	        
	        //insertarDepartamento(1,"1","1");
	        /*departamento dept = new departamento(2,"2","2");
	        insertarDepartamento(dept);
	        mostrarDepartamentos();*/
	        consultarDepartamentos(1);
	        
	       //////////////////////////////////////////// 
	       conexion.close();
	       System.out.println("Se ha cerrado la conexión");
	    }	    
	    catch ( Exception e){
	        e.printStackTrace();
	    }
	}
	
	/*Método que inserta un departamento. El método recibe tres argumentos (número, nombre y localidad).*/
	public static void insertarDepartamento(int numero,String nombre, String localidad) {
		String query = "INSERT INTO `DEPT`(`DEPTNO`, `DNAME`, `LOC`) "
				+ "VALUES ("+numero+","+nombre+","+localidad+")";
		Statement stmt = null;
        try {
            stmt = conexion.createStatement();        
            stmt.execute(query);
            stmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();        
        } 
	}
	
	/*Método igual que el anterior pero recibiendo un solo argumento, un objeto de la clase departamento.*/
	public static void insertarDepartamento(departamento d) {
		String query = "INSERT INTO `DEPT`(`DEPTNO`, `DNAME`, `LOC`) "
				+ "VALUES ("+d.getNumero()+","+d.getNombre()+","+d.getLocalidad()+")";
		Statement stmt = null;
        try {
            stmt = conexion.createStatement();        
            stmt.execute(query);
            stmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();        
        } 
	}
	
	/*Método que devuelve un ArrayList de objetos departamento ante la consulta
	 * de todas las columnas de todos los departamentos de la tabla dept*/
	public static List<departamento> mostrarDepartamentos() {
		List<departamento> lista = new ArrayList<departamento>();
		String query = "SELECT * FROM `DEPT`";
		Statement stmt = null;
        try {
        	stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int numero;
            String nombre, localidad;
            while (rs.next()){
            	numero = rs.getInt("DEPTNO");
                nombre = rs.getString("DNAME");
                localidad = rs.getString("LOC");
                departamento dept = new departamento(numero,nombre,localidad);
                lista.add(dept);
                //System.out.println("Numero: " + numero + " Nombre: " + nombre+" Localidad: " + localidad);               
            }
            stmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();        
        } 
        return lista;
	}	 
	
	/*Método que recibe un número de departamento y devuelva sus datos mediante un objeto.*/
	public static departamento consultarDepartamentos(int num) {
		String query = "SELECT * FROM `DEPT` WHERE `DEPTNO` = "+num+"";
		Statement stmt = null;
		 departamento dept = new departamento(); ; 
        try {
        	stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int numero;
            String nombre, localidad;   
            while (rs.next()){
	            numero = rs.getInt("DEPTNO");
	            nombre = rs.getString("DNAME");
	            localidad = rs.getString("LOC");
	            dept = new departamento(numero,nombre,localidad);          
	            //System.out.println("Numero: " + numero + " Nombre: " + nombre+" Localidad: " + localidad);                       
            }
            stmt.close();
        }
        catch (SQLException e){
            e.printStackTrace();        
        } 
        return dept;
	}
	
}

class departamento{
	int numero;
	String nombre;
	String localidad;
	
	public departamento() {
		 
	 }
	
	public departamento(int num, String nom, String loca) {
		this.numero=num;
		this.nombre=nom;
		this.localidad=loca;
	}
	 
	 
	 public int getNumero() {
		 return numero;
	 }
	 public String getNombre() {
		 return nombre;
	 }
	 public String getLocalidad() {
		 return localidad;
	 }
	
	 public void setNumero(int numero) {
		 this.numero = numero;
	 }
	 public void setNombre(String nombre) {
		 this.nombre = nombre;
	 }
	 public void setLocalidad(String localidad) {
		 this.localidad = localidad;
	 }	
	 
	 public String mostrarDepartamento() {
		 return "Numero: " + numero + " Nombre: " + nombre+" Localidad: " + localidad;
	 }
}