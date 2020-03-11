package Principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JLabel lblNuevoUsuario;
	private JLabel lblNuevaContrasea;
	private JTextField textNombre;
	private JPasswordField txtPass;
	private JButton btnRegistrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Registro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNuevoUsuario = new JLabel("Nuevo Usuario");
		lblNuevoUsuario.setBounds(48, 38, 96, 14);
		contentPane.add(lblNuevoUsuario);
		
		lblNuevaContrasea = new JLabel("Nueva contrase\u00F1a");
		lblNuevaContrasea.setBounds(48, 75, 96, 14);
		contentPane.add(lblNuevaContrasea);
		
		textNombre = new JTextField();
		textNombre.setBounds(248, 35, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(248, 72, 86, 20);
		contentPane.add(txtPass);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addMouseListener(new BtnRegistrarMouseListener());
		btnRegistrar.setBounds(245, 189, 89, 23);
		contentPane.add(btnRegistrar);
	}
	private class BtnRegistrarMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(textNombre.getText().equalsIgnoreCase("") && txtPass.getText().equalsIgnoreCase("")) {
				IODatos.guardarNuevoUsuario(textNombre.getText(), txtPass.getText());
			}
		}
	}
}
