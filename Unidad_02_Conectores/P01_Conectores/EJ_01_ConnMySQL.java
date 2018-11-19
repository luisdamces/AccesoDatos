package P01_Conectores;

import java.sql.*;

public class EJ_01_ConnMySQL {
	
	public static void main(String[] args) {
	    
	    System.out.println("Iniciando");
	    
	    // Creando la conexión
	    try {
	    	
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection con = DriverManager.getConnection(
	        				"jdbc:mysql://www.db4free.net:3306/tubalcain",
	        				"tubalcain_dam",
	        				"12345678"
	        				); 
	        
	        String sql = "CREATE TABLE IF NOT EXISTS USUARIO " +
	               "(id INTEGER not NULL, " +
	               " nombre VARCHAR(255), " + 
	               " apellido VARCHAR(255), " + 
	               " edad INTEGER, " + 
	               " PRIMARY KEY ( id ))"; 
	        
	        String query = "SELECT nombre, edad from USUARIO";
	        
	        Statement stmt = null;
	        try {
	            stmt = con.createStatement();
	            stmt.executeUpdate(sql);
	            ResultSet rs = stmt.executeQuery(query);
	            String nombre, edad;
	            while (rs.next()){
	                nombre = rs.getString("nombre");
	                edad = rs.getString("edad");
	                System.out.println("Usuario: " + nombre + " Edad: " + edad);
	            }
	        }
	        catch (SQLException e){
	            e.printStackTrace();
	            // System.out.printf("Mensaje     : %s %n", e.getMessage());
	            // System.out.printf("SQL estado  : %s %n", e.getSQLState());
	            // System.out.printf("Código error: %s %n", e.getErrorCode());
	        
	        } finally {
	            stmt.close();
	            con.close();
	            System.out.println("Se ha cerrado la conexión");
	        }
	       
	    }
	    catch (ClassNotFoundException ex) {
	        ex.printStackTrace();
	    }
	    catch ( Exception e){
	        e.printStackTrace();
	    }
	}
	    
}
/* int numero=0;
do {
	System.out.println("_____________________________________ ");
	System.out.println("|       LISTA DE REPRODUCCI�N        |");
	System.out.println("|------------------------------------|");	
	System.out.println("|1.-Generar fichero                  |");
	System.out.println("|2.-Insertar datos                   |");
	System.out.println("|3.-Consultar datos de una canci�n   |");
	System.out.println("|4.-Modificar a�o de la canci�n      |");
	System.out.println("|8.-Salir                            |");
	System.out.println("|____________________________________|");	

	
	
	switch (numero) {
	
	case 1: 				
		
		break;
		
	case 2: 				
						
		break;
		
	case 3: 
					
		break;
		
	case 4: 
		
		break;
		
	case 5:
		System.out.println("Has salido del programa");
		break; 				
	default:
		System.out.println("ERROR, debe introducir un número entre el 1 y el 5");
	} 			
}while(numero!=5);	*/