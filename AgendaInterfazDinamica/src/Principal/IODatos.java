package Principal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passwordCorrecto;
	}
	public static void guadarNuevoUsuario(String nombre, String pass) {
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
		}
	}
}
