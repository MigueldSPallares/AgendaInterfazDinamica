package Principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Principal.Agenda;
import Principal.Login;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private JButton btnEntrar;
	private JButton btnSalir;
	private JButton btnRegistrarUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUsuario = new JTextField();
		txtUsuario.setText("Usuario");

		txtUsuario.setBounds(182, 282, 86, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNombreUsuario = new JLabel("Nombre Usuario:");
		lblNombreUsuario.setBounds(37, 285, 86, 14);
		contentPane.add(lblNombreUsuario);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		char passwordChar = passwordField.getEchoChar();
		passwordField.setBounds(182, 307, 86, 20);
		contentPane.add(passwordField);
		passwordField.setEchoChar((char)0);
		passwordField.setText("Contraseña");

		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(37, 310, 67, 14);
		contentPane.add(lblContrasea);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\migue\\git\\AgendaInterfazDinamica\\descarga.png"));
		lblNewLabel.setBounds(111, 39, 224, 221);
		contentPane.add(lblNewLabel);
		lblNewLabel.setFocusable(true);
		lblNewLabel.requestFocus();

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(182, 338, 89, 23);
		contentPane.add(btnEntrar);
		btnEntrar.setEnabled(false);
		
		btnSalir = new JButton("Salir");
		btnSalir.addMouseListener(new BtnSalirMouseListener());
		btnSalir.setBounds(324, 338, 89, 23);
		contentPane.add(btnSalir);
		
		btnRegistrarUsuario = new JButton("Registrar");
		btnRegistrarUsuario.addMouseListener(new BtnRegistrarUsuarioMouseListener());
		btnRegistrarUsuario.setBounds(37, 335, 96, 23);
		contentPane.add(btnRegistrarUsuario);

		lblNewLabel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				lblNewLabel.setFocusable(false);
			}
		});

		txtUsuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if (txtUsuario.getText().equalsIgnoreCase("Usuario")) {
					txtUsuario.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtUsuario.getText().equalsIgnoreCase("")) {
					txtUsuario.setText("Usuario");
				}
			}
		});
		
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(passwordField.getText().equalsIgnoreCase("Contraseña")) {
					passwordField.setText("");
					passwordField.setEchoChar(passwordChar);
				}
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if(passwordField.getText().equalsIgnoreCase("")) {
					passwordField.setText("Contraseña");
					passwordField.setEchoChar((char)0);
				}
			}
		});
		
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				comprobarBoton();
			}
		});
		
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				comprobarBoton();
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					if(IODatos.comprobarLogin(txtUsuario.getText(), passwordField.getText())) {
						//passwordField.getText().equalsIgnoreCase("1234");
						Agenda nueva = new Agenda(txtUsuario.getText());
						nueva.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Usuario Incorrecto");
						txtUsuario.setText("Usuario");
						passwordField.setText("Contraseña");
						passwordField.setEchoChar((char)0);
					}
				}
			}
		});

		btnEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(IODatos.comprobarLogin(txtUsuario.getText(), passwordField.getText())) {
					//passwordField.getText().equalsIgnoreCase("1234");
					Agenda nueva = new Agenda(txtUsuario.getText());
					nueva.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Usuario Incorrecto");
					txtUsuario.setText("Usuario");
					passwordField.setText("Contraseña");
					passwordField.setEchoChar((char)0);
				}
			}
		});
	}
	
	
	private void comprobarBoton() {
		if(txtUsuario.getText().equalsIgnoreCase("Usuario") || txtUsuario.getText().equalsIgnoreCase("") ||
				passwordField.getText().equalsIgnoreCase("Contraseña") || passwordField.getText().equalsIgnoreCase("")) {
			btnEntrar.setEnabled(false);
		}else {
			btnEntrar.setEnabled(true);
		}
	}
	private class BtnSalirMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			dispose();
		}
	}
	private class BtnRegistrarUsuarioMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			Registro r = new Registro();
			r.setVisible(true);
			dispose();
		}
	}
}
