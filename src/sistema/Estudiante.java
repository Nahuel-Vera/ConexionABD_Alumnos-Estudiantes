package sistema;


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import conexion.Conexion;


public class Estudiante extends Personas{
	private int IdAlumn;
	private Date anioIngreso;
	private Date anioEgreso;
	private String institucion;
	private String notas;
	
	//constructor para llenar
	public Estudiante() {
		
	}
	
	//constructor Estudiante heredando de clase persona
	public Estudiante(int dni, int edad, String nombre, String apellido,int idAlumn,Date anioIngreso,Date anioEgreso,String institucion,String notas) {
		super(dni,edad,nombre,apellido);
		this.IdAlumn=idAlumn;
		this.anioIngreso=anioIngreso;
		this.anioEgreso=anioEgreso;
		this.institucion=institucion;
		this.notas=notas;
	}

	//gets
	public Date getAnioIngreso() {
		return anioIngreso;
	}
	public Date getAnioEgreso() {
		return anioEgreso;
	}
	public String getInstitucion() {
		return institucion;
	}
	public String getNotas() {
		return notas;
	}
	public int getIdAlumn() {
		return IdAlumn;
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
	
	//sets
	public void setAnioIngreso(Date anioIngreso) {
		this.anioIngreso = anioIngreso;
	}
	public void setAnioEgreso(Date anioEgreso) {
		this.anioEgreso = anioEgreso;
	}
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	public void setIdAlumn(int idAlumn) {
		IdAlumn = idAlumn;
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

	public String mostrarEstudiante() {
		
		return  "ID del Alumno: "+IdAlumn+
				"\nNombre: "+getNombre()+
				"\nApellido: "+getApellido()+
				"\nDNI: "+getDni()+
				"\nEdad: "+getEdad()+
				"\nAnio ingreso: "+anioIngreso+
				"\nAnio egreso: "+anioEgreso+
				"\nInstitucion: "+institucion+
				"\nNotas: "+notas+
				"\n\n";
	}
	public void crearEstudiante(ArrayList<Estudiante> listEstudiante) {
		try {
		crearPersona();
		Scanner sc=new Scanner(System.in);
		System.out.println("Ingrese fecha de ingreso del Alumno "
						+ "\ndebera escribirlo asi, ej: "
						+ "\n yyyy-mm-dd");
		String ingreso=sc.nextLine();
		while(ingreso.length()<7||ingreso.charAt(4)!='-'||(ingreso.charAt(7)!='-'&&ingreso.charAt(6)!='-')) {
			System.out.println("porfavor ingrese una fecha ingreso con este formato:"
					+ "\n yyyy-mm-dd");
			ingreso=sc.nextLine();
			}
		
		anioIngreso=Date.valueOf(ingreso);
		System.out.println("Ingrese fecha de egreso del Alumno "
						+ "\ndebera escribirlo asi, ej: "
						+ "\n yyyy-mm-dd");
		String egreso=sc.nextLine();
		while(egreso.length()<7||egreso.charAt(4)!='-'||(egreso.charAt(7)!='-'&&ingreso.charAt(6)!='-')) {
			System.out.println("porfavor ingrese una fecha egreso con este formato:"
					+ "\n yyyy-mm-dd");
			egreso=sc.nextLine();
		}
		anioEgreso=Date.valueOf(egreso);
		System.out.println("Ingrese la institucion del alumno: ");
		institucion=sc.nextLine();
		System.out.println("Ingrese las notas del alumno: ");
		notas=sc.nextLine();
		Conexion conexion1=new Conexion();
		Connection cn1=null;
		ResultSet rs=null;
		Statement stm=null;
		cn1=conexion1.conectar();
		try {
		stm=cn1.createStatement();
		rs=stm.executeQuery("select* from alumnos");
		IdAlumn=1;
			while(rs.next()) {
					IdAlumn=rs.getInt(1);
			}
			if(listEstudiante.size()==0) {
				IdAlumn=IdAlumn+1;
			}
			else {
				IdAlumn=IdAlumn+listEstudiante.size()+1;
			}
			
			conexion1.desconectar(cn1, rs, stm);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n\n");
		}catch(Exception e) {
			System.out.println("\n\ningrese correctamente los datos porfavor\n");
		}
	}
}
