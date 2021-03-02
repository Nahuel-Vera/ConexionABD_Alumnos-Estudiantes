package sistema;

import java.util.Scanner;

public class Personas {
	private int dni;
	private int edad;
	private String nombre;
	private String apellido;
	
	//constructor vacio
	public Personas() {
		
	}
	
	//
	public Personas(int dni,int edad,String nombre,String apellido) {
		this.dni=dni;
		this.edad=edad;
		this.nombre=nombre;
		this.apellido=apellido;
	}
	//metodos sets
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//metodos gets
	public String getApellido() {
		return apellido;
	}
	public int getDni() {
		return dni;
	}
	public int getEdad() {
		return edad;
	}
	public String getNombre() {
		return nombre;
	}
	public void crearPersona() {
			System.out.println("Ingrese nombre de la persona: ");
			Scanner sc=new Scanner(System.in);
			nombre=sc.next();
			System.out.println("Ingrese Apellido de la persona: ");
			apellido=sc.next();
			System.out.println("Ingrese edad de la persona: ");
			edad=sc.nextInt();
			System.out.println("Ingrese dni de la persona: ");
			dni=sc.nextInt();
	}
}
