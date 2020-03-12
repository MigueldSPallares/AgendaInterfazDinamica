package Principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class IODatos {

	public static boolean comprobarLogin(String usu, String pass) {
		File fichero = new File("Usuarios.txt");
		boolean passwordCorrecto = false;
		if(!fichero.exists()) {
			try {
				fichero.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			Scanner leer = new Scanner(fichero);
			while (leer.hasNext()) {
				String[] linea = leer.nextLine().split("-");
				if(linea[0].equalsIgnoreCase(usu) && linea[1].equalsIgnoreCase(pass)) {
					passwordCorrecto = true;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passwordCorrecto;
	}
	public static void guardarNuevoUsuario(String nombre, String pass) {
		File f = new File("Usuarios.txt");
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(f, true);
			pw = new PrintWriter(fw);
			pw.println(nombre+"-"+pass);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fw.close();
				pw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void guardarContactos(String usuario, ArrayList<Contactos> vContactos) {
		File f = new File(usuario+".txt");
		FileOutputStream fo = null;
		ObjectOutputStream escribir = null;
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fo = new FileOutputStream(f);
			escribir = new ObjectOutputStream(fo);
			escribir.writeObject(vContactos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fo.close();
				escribir.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static ArrayList<Contactos> cargarContacto(String usuario){
		ArrayList<Contactos> vContactos = new ArrayList<Contactos>();
		File f = new File(usuario+".txt");
		FileInputStream fi = null;
		ObjectInputStream leer = null;
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fi = new FileInputStream(f);
			leer = new ObjectInputStream(fi);
			
			vContactos = (ArrayList<Contactos>) leer.readObject();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fi.close();
				leer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vContactos;
	}
}
