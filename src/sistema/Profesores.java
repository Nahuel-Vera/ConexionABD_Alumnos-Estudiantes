package sistema;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.Conexion;

public class Profesores extends Personas {
	private int IdProfresor;
	private String materia;
	private Date ingreso;
	
	//constructor vacio para llenar
	public Profesores() {
		
	}
	
	public Profesores(int dni,int edad,String nombre,String apellido,int IdProfesor,String materia,Date ingreso) {
		super(dni,edad,nombre,apellido);
		this.IdProfresor=IdProfesor;
		this.materia=materia;
		this.ingreso=ingreso;
	}
	//sets heredados
	@Override
	public void setApellido(String apellido) {
		// TODO Auto-generated method stub
		super.setApellido(apellido);
	}
	@Override
	public void setDni(int dni) {
		// TODO Auto-generated method stub
		super.setDni(dni);
	}
	@Override
	public void setEdad(int edad) {
		// TODO Auto-generated method stub
		super.setEdad(edad);
	}
	@Override
	public void setNombre(String nombre) {
		// TODO Auto-generated method stub
		super.setNombre(nombre);
	}
	//sets de la clase
	public void setIdProfresor(int idProfresor) {
		IdProfresor = idProfresor;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public void setIngreso(Date ingreso) {
		this.ingreso = ingreso;
	}
	
	//gets heredados
	@Override
	public String getApellido() {
		// TODO Auto-generated method stub
		return super.getApellido();
	}
	@Override
	public int getDni() {
		// TODO Auto-generated method stub
		return super.getDni();
	}
	@Override
	public int getEdad() {
		// TODO Auto-generated method stub
		return super.getEdad();
	}
	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return super.getNombre();
	}
	
	//gets de la clase
	public int getIdProfresor() {
		return IdProfresor;
	}
	public String getMateria() {
		return materia;
	}
	public Date getIngreso() {
		return ingreso;
	}
	
	public void crearProfesor(ArrayList<Profesores> listProf) {
		crearPersona();
		Scanner sc=new Scanner(System.in);
		Conexion conexion=new Conexion();
		Connection cn=null;
		ResultSet rs=null;
		Statement stm=null;
		cn=conexion.conectar();
		try {
			stm=cn.createStatement();
			rs=stm.executeQuery("select* from profesores");
			IdProfresor=1;
			while(rs.next()) {
				IdProfresor=rs.getInt(1);
			}
			if(listProf.size()==0) {
				IdProfresor=IdProfresor+1;
			}
			else {
				IdProfresor=IdProfresor+listProf.size()+1;
			}
			
			conexion.desconectar(cn, rs, stm);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("ingrese materia asignada para el profesor "+getNombre()+": ");
		materia=sc.nextLine();
		System.out.println("Ingrese fecha de ingreso del Profesor "
				+ "\ndebera escribirlo asi, ej: "
				+ "\nyyyy-mm-dd");
		String ingre=sc.nextLine();
		while(ingre.length()<7||ingre.charAt(4)!='-'||(ingre.charAt(6)!='-'&&ingre.charAt(7)!='-')) {
			System.out.println("porfavor ingrese una fecha ingreso con este formato:"
					+ "\n yyyy-mm-dd");
		}
		ingreso=Date.valueOf(ingre);
	}
}
