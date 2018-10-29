package P05_Ficheros_XML;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class EJ_1_3_Crear_cancion_XML {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		File f = new File("D:\\eclipse-workspace\\AD_01_Fciheros\\src"
				+ "\\P05_Ficheros_XML\\Ficheros\\canciones.dat"); 
		FileInputStream filein = new FileInputStream(f);
		ObjectInputStream objectin = new ObjectInputStream(filein);
		
		Cancion c;  
		
		// Instancia para construir el parser
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			   
			DocumentBuilder builder = factory.newDocumentBuilder();
		    DOMImplementation implementation = builder.getDOMImplementation();
		    Document document = 
		    		implementation.createDocument(null, "Canciones", null);
		    document.setXmlVersion("1.0"); 		    
			
			try {
				while(true){
					c=(Cancion)objectin.readObject();
						
					if(c.getId()>0) {
							   
					Element raiz = 
					           document.createElement("cancion");//nodo cancion
					document.getDocumentElement().appendChild(raiz); 
					        
					// ID                       
					CrearElemento("id",Integer.toString(c.getId()), raiz, document); 
					// A�o                       
					CrearElemento("a�o",Integer.toString(c.getAnio()), raiz, document); 
					// Titulo
					CrearElemento("titulo",c.getTitulo().trim(), raiz, document); 
					// Artista
					CrearElemento("artista",c.getArtista().trim(), raiz, document);
					// Duracion
					CrearElemento("duracion",c.getDuracion().trim(), raiz, document);
					// Cancion_espaniola
					String c_esp=String.valueOf(c.getCancion_espaniola());
					CrearElemento("asd",c_esp.trim(), raiz, document);
					}						
				}
			}catch (EOFException eo) {
				System.out.println("");
			}				 
				
		    Source source = new DOMSource(document);
		    Result result = 
		           new StreamResult(new java.io.File("Empleados.xml"));        
		    Transformer transformer =
		           TransformerFactory.newInstance().newTransformer();
		    transformer.transform(source, result); // se transforma el documento al fichero
		    
		    // MOSTRAR EL DOCUMENTO POR CONSOLA
		    // Result console = new StreamResult(System.out);
		    // transformer.transform(source, console);	   
			   
		    }catch(Exception e){ System.err.println("Error: "+ e); }
		    
		objectin.close();  //cerrar fichero 	
		}//fin de main
	
		//Inserci�n de los datos de la cancion
	/**
	 * 
	 * @param datoCancion
	 * @param valor
	 * @param raiz
	 * @param document
	 */
	 	static void  CrearElemento(String datoCancion, String valor,
	                            Element raiz, Document document){
	 	Element elem = document.createElement(datoCancion); 
	    Text text = document.createTextNode(valor); //damos valor
	    raiz.appendChild(elem); //pegamos el elemento hijo a la raiz
	    elem.appendChild(text); //pegamos el valor		 	
	 	}
}//fin de la clase