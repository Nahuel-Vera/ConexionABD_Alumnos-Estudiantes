package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	private static final String CONTROLADOR="com.mysql.cj.jdbc.Driver"; //el controlador del jdbc
	private static final String URL="jdbc:mysql://localhost:3306/colegio"; //la base de datos a la que quiere acceder
	private static final String USUARIO="root"; //va su usuario de Workbench
	private static final String CLAVE=/*Ej:	"pepito"	*/; //va su contraseña
	
	static {
		try {
			Class.forName(CONTROLADOR);
		}catch(ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	public Connection conectar() {
		Connection conexion=null;
		try {
			
			conexion=DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexion OK");
		}catch(SQLException e) {
			System.out.println("Error en la conexion");
			e.printStackTrace();
		}
		return conexion;
	}
	//para desconectar
	public void desconectar(Connection cn1,ResultSet rs,Statement stm) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(stm!=null) {
				stm.close();
			}
			if(cn1!=null) {
				cn1.close();
			}
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
}