package sistema;

import conexion.Conexion;
import jdk.dynalink.support.AbstractRelinkableCallSite;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main{
	static ArrayList<Estudiante> listaEstudiante;
	static ArrayList<Profesores> listaProfesor;
	private static Estudiante estudiante1;
	private static Profesores profesor1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		listaEstudiante=new ArrayList<Estudiante>();
		listaProfesor=new ArrayList<Profesores>();
		Menu();
	}
	
	public static void cargarEstudiantes(int cantAgregar) {
		for(int i=listaEstudiante.size();i<cantAgregar;i++) {
			estudiante1=new Estudiante();
			estudiante1.crearEstudiante(listaEstudiante);
			listaEstudiante.add(i, estudiante1);
			for(int j=0;j<listaEstudiante.get(i).getNombre().length();j++) {			//sirve para verificar si el nombre fue ingresado bien, deberia hacer lo mismo para los demas datos
				char caracterNom=listaEstudiante.get(i).getNombre().toUpperCase().charAt(j);
				int NomEnASCII=(int)caracterNom;
					if(NomEnASCII<65||NomEnASCII>90) {
							System.out.println("los datos ingresados fueron:"+
												"\nNombre: "+listaEstudiante.get(i).getNombre()+
												"\ningrese un nombre sin numeros ni espacios por favor\n");
							listaEstudiante.remove(i);
							cargarEstudiantes(cantAgregar);		
					}
				}
			for(int j=0;j<listaEstudiante.get(i).getApellido().length();j++) {			//verifica que el apellido no tenga espacios ni numeros
				char caraterApell=listaEstudiante.get(i).getApellido().toUpperCase().charAt(j);
				int ApellEnASCII=(int)caraterApell;
				if(ApellEnASCII<65||ApellEnASCII>90) {
					System.out.println("los datos ingresados fueron:"+
										"\nApellido: "+listaEstudiante.get(i).getApellido()+
										"\nIngrese un apellido sin numeros ni espacios por favor\n\n");
					listaEstudiante.remove(i);
					cargarEstudiantes(cantAgregar);
					}
				}
			}
		for(int i=0;i<listaEstudiante.size();i++) {
			if(listaEstudiante.get(i).getIdAlumn()==0) {
				listaEstudiante.remove(i);
				}
			}
		}

	public static void cargarProfesores(int cantAgregarP) {
		for(int i=listaProfesor.size();i<cantAgregarP;i++) {
			profesor1=new Profesores();
			profesor1.crearProfesor(listaProfesor);
			listaProfesor.add(i, profesor1);
			for(int j=0;j<listaProfesor.get(i).getNombre().length();j++) {			//sirve para verificar si el nombre fue ingresado bien, deberia hacer lo mismo para los demas datos
				char caracterNom=listaProfesor.get(i).getNombre().toUpperCase().charAt(j);
				int NomEnASCII=(int)caracterNom;
					if(NomEnASCII<65||NomEnASCII>90) {
							System.out.println("los datos ingresados fueron:"+
												"\nNombre: "+listaProfesor.get(i).getNombre()+
												"\ningrese un nombre sin numeros ni espacios por favor\n");
							listaProfesor.remove(i);
							cargarEstudiantes(cantAgregarP);		
					}
				}
			for(int j=0;j<listaProfesor.get(i).getApellido().length();j++) {			//verifica que el apellido no tenga espacios ni numeros
				char caraterApell=listaProfesor.get(i).getApellido().toUpperCase().charAt(j);
				int ApellEnASCII=(int)caraterApell;
				if(ApellEnASCII<65||ApellEnASCII>90) {
					System.out.println("los datos ingresados fueron:"+
										"\nApellido: "+listaProfesor.get(i).getApellido()+
										"\nIngrese un apellido sin numeros ni espacios por favor\n");
					listaProfesor.remove(i);
					cargarEstudiantes(cantAgregarP);
					}
				}
			}
		}
	
	public static void ordenarAlumnos() {
		Estudiante aux;
		for(int i=0;i<listaEstudiante.size()-1;i++) {
			for(int j=0;j<listaEstudiante.size()-1;j++) {
				if(listaEstudiante.get(j).getDni()>listaEstudiante.get(j+1).getDni()) {
					aux=listaEstudiante.get(j);
					listaEstudiante.set(j, listaEstudiante.get(j+1));
					listaEstudiante.set(j+1, aux);
				}
			}
		}
	}
	
	public static void consultasBD() {
		try {
			Conexion conexion=new Conexion();
			Connection cn=null;
			Statement stm=null;
			ResultSet rs=null;
			cn=conexion.conectar();
			
			System.out.println("Ingrese la Query que desea ejecutar para la tabla alumnos");
			String query=null;
			Scanner sc1=new Scanner(System.in);
			query=sc1.nextLine();
			
			System.out.println("la sentencia a ejecutar sera: "+
								"\n"+query+"\n");
			
			stm=cn.createStatement();
			rs=stm.executeQuery(query);
			
			
			while(rs.next()) {
				int ida=rs.getInt(1);
				int dni=rs.getInt(2);
				String nom=rs.getString(3);
				String apell=rs.getString(4);
				int edad=rs.getInt(5);
				Date ingreso=rs.getDate(6);
				Date egreso=rs.getDate(7);
				String institucion=rs.getString(8);
				String notas=rs.getString(9);

				
				System.out.println("ID del Alumno: "+ida+
									"\ndni: "+dni+
									"\nNombre: "+nom+
									"\nApellido: "+apell+
									"\nEdad: "+edad+
									"\nAnio de ingreso: "+ingreso+
									"\nAnio de egreso: "+egreso+
									"\nInstitucion: "+institucion+
									"\nNotas: "+notas+
									"\n----------------------------------------------------------------------\n");
			}
			
			conexion.desconectar(cn, rs, stm);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void Menu() {
		boolean salir=false;
		int opcion;
		Scanner sc=new Scanner(System.in);
		while(!salir) {
			System.out.println("1: Cargar Estudiantes: ");
			System.out.println("2: Mostrar Estudiantes");
			System.out.println("3: Ordenar por DNI");
			System.out.println("4: hacer consultas a la Bda");
			System.out.println("5: agregar los alumnos listados a la Bda");
			System.out.println("6: cargar Profesores");
			System.out.println("7: agregar los profesores listados a la Bda");
			System.out.println("8: Agregar los alumnos listados a un txt");
			System.out.println("0: Salir");
				try {
					System.out.println("Escribe alguna de las opciones\n");
					opcion=sc.nextInt();
			switch(opcion) {
			case 1:
				int cantAgregar=0;
				System.out.println("Ingrese cantidad de alumnos que quiere agregar: ");
				cantAgregar=sc.nextInt();
				cantAgregar=cantAgregar+listaEstudiante.size();
				cargarEstudiantes(cantAgregar);
				break;
			case 2:
				for(int i=0;i<listaEstudiante.size();i++) {
					System.out.println(listaEstudiante.get(i).mostrarEstudiante());
				}
				break;
			case 3: //Orgenamiento en una lista
				ordenarAlumnos();
				break;
			case 4:
				consultasBD();
				break;
			case 5:
				
				if(listaEstudiante.isEmpty()) {
					System.out.println("ingrese algun estudiante antes por favor\n");
					break;
				}
				else {
					
						for(int i=0;i<listaEstudiante.size();i++) {
						PreparedStatement stm=null;
						Conexion conexion1=new Conexion();
						Connection cn1=null;
						ResultSet rs=null;
						cn1=conexion1.conectar();
						
						try{
							stm=cn1.prepareStatement("INSERT INTO alumnos VALUES(?,?,?,?,?,?,?,?,?)");//query
							//pasar los gatos a variables
							
							int ida=listaEstudiante.get(i).getIdAlumn();
							int dni=listaEstudiante.get(i).getDni();
							String nom=listaEstudiante.get(i).getNombre();
							String apell=listaEstudiante.get(i).getApellido();
							int edad=listaEstudiante.get(i).getEdad();
							Date ingreso=listaEstudiante.get(i).getAnioIngreso();
							Date egreso=listaEstudiante.get(i).getAnioEgreso();
							String institucion=listaEstudiante.get(i).getInstitucion();
							String notas=listaEstudiante.get(i).getNotas();
							
							//asignar las variables a la tabla de SQL
							stm.setInt(1, ida);
							stm.setInt(2, dni);
							stm.setString(3, nom);
							stm.setString(4, apell);
							stm.setInt(5, edad);
							stm.setDate(6, ingreso);
							stm.setDate(7, egreso);
							stm.setString(8, institucion);
							stm.setString(9, notas);
							
							stm.executeUpdate();
							
							//cierro las conexiones
							conexion1.desconectar(cn1, rs, stm);
							
						}catch(SQLException e) {
							e.printStackTrace();
						}
				}
			}
			break;
			case 6:
				int cantAgregarP=0;
				System.out.println("Ingrese cantidad de Profesores que desea agregar: ");
				cantAgregarP=sc.nextInt();
				cantAgregarP=cantAgregarP+listaProfesor.size();
				cargarProfesores(cantAgregarP);
				break;
			case 7:
				if(listaProfesor.isEmpty()) {
					System.out.println("ingrese algun estudiante antes por favor\n");
					break;
				}
				else {
					
						for(int i=0;i<listaProfesor.size();i++) {
						PreparedStatement stm=null;
						Conexion conexion1=new Conexion();
						Connection cn1=null;
						cn1=conexion1.conectar();
						
						try{
							stm=cn1.prepareStatement("INSERT INTO profesores VALUES(?,?,?,?,?,?,?)");//query
							//pasar los gatos a variables
							
							int idp=listaProfesor.get(i).getIdProfresor();
							int dni=listaProfesor.get(i).getDni();
							String nom=listaProfesor.get(i).getNombre();
							String apell=listaProfesor.get(i).getApellido();
							int edad=listaProfesor.get(i).getEdad();
							Date ingreso=listaProfesor.get(i).getIngreso();
							String materia=listaProfesor.get(i).getMateria();
							
							//asignar las variables a la tabla de SQL
							stm.setInt(1, idp);
							stm.setInt(2, dni);
							stm.setString(3, nom);
							stm.setString(4, apell);
							stm.setInt(5, edad);
							stm.setDate(6, ingreso);
							stm.setString(7, materia);
							
							stm.executeUpdate();
							
							//cierro las conexiones
							conexion1.desconectar(cn1, null, stm);
							
						}catch(SQLException e) {
							e.printStackTrace();
						}
				}
			break;
			}
			case 8:
				try {
					FileWriter fichero=new FileWriter("C:/Users/Nahuu/Desktop/txt/fichero.txt");//carpeta donde se va a guardar el archivo
					for(int i=0;i<listaEstudiante.size();i++) {
						fichero.write(listaEstudiante.get(i).mostrarEstudiante());
					}
				System.out.println("desea tambien agregar los alumnos en la Base de datos? "
								+ "\n 1: si."
								+ "\n 2: no.");
				Scanner sc1=new Scanner(System.in);
				int seleccion=sc1.nextInt();
				if(seleccion==1) {
					Conexion conexion=new Conexion();
					Statement stm=null;
					Connection cn1=null;
					ResultSet rs=null;
					cn1=conexion.conectar();
					try {
						stm=cn1.createStatement();
						rs=stm.executeQuery("select* from alumnos");
						fichero.write("Alumnos de la Base de datos: ");
						while(rs.next()) {
							int ida=rs.getInt(1);
							int dni=rs.getInt(2);
							String nom=rs.getString(3);
							String apell=rs.getString(4);
							int edad=rs.getInt(5);
							Date ingreso=rs.getDate(6);
							Date egreso=rs.getDate(7);
							String institucion=rs.getString(8);
							String notas=rs.getString(9);
							
							fichero.write("\n------------------------------"
										+"\nID alum: "+ida
										+"\nDNI: "+dni
										+"\nNombre: "+nom
										+"\nApellido: "+apell
										+"\nEdad: "+edad
										+"\nIngreso: "+ingreso
										+"\nEgreso: "+egreso
										+"\nInstitucion: "+institucion
										+"\nNotas: "+notas);
							
						}
						conexion.desconectar(cn1, rs, stm);
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					System.out.println("ok");
				}
			fichero.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
				break;
			case 0:
				sc.close();
                salir = true;
                break;
			default:
				System.out.println("Debe ingresar solo numeros del 0 al 8 porfavor\n");
					}
				}catch(InputMismatchException e) {
	                System.out.println("Debe ingresar solo numeros del 0 al 8 porfavor\n");
	                sc.next();
			}
		}
		sc.close();
	}
}