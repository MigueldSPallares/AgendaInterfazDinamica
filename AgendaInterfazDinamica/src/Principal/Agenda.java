package Principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Agenda extends JFrame {

	//Ejemplo
	private JPanel contentPane;
	private JLabel lblNombre;
	private JLabel lblTelefono;
	private JTextField textNombre;
	private JTextField textTelefono;
	private JTextPane textArea;
	private JLabel lblContactos;
	private JLabel lblSexo;
	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private JButton btnGuardar;
	private DefaultComboBoxModel modeloComboBox;
	private ArrayList<Contactos> vContactos;
	private JComboBox comboBox;
	private JLabel lblInformacion;
	private JLabel lblAgenda;
	private JButton btnEliminar;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JList list;
	private JButton btnEditar;
	private String usuario;
	private JButton btnCerrarSesion;
	private DefaultListModel listModel;
	private JTextField txtBuscar;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda frame = new Agenda("");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Agenda(String usuario) {
		this.usuario = usuario;
		vContactos = IODatos.cargarContacto(usuario);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 594, 446);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(28, 103, 70, 14);
		contentPane.add(lblNombre);

		lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(28, 142, 70, 14);
		contentPane.add(lblTelefono);

		textNombre = new JTextField();
		textNombre.setBounds(108, 100, 111, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		textNombre.requestFocus();

		textTelefono = new JTextField();
		textTelefono.setBounds(108, 139, 111, 20);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);

		lblContactos = new JLabel("Mostrar contactos");
		lblContactos.setBounds(243, 103, 89, 14);
		contentPane.add(lblContactos);

		lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(28, 181, 46, 14);
		contentPane.add(lblSexo);

		rdbtnHombre = new JRadioButton("Hombre");
		buttonGroup.add(rdbtnHombre);
		rdbtnHombre.setBounds(108, 177, 109, 23);
		contentPane.add(rdbtnHombre);

		rdbtnMujer = new JRadioButton("Mujer");
		buttonGroup.add(rdbtnMujer);
		rdbtnMujer.setBounds(108, 203, 109, 23);
		contentPane.add(rdbtnMujer);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new BtnGuardarMouseListener());
		btnGuardar.setBounds(28, 259, 89, 23);
		contentPane.add(btnGuardar);
		modeloComboBox = new DefaultComboBoxModel();
		modeloComboBox.addElement("Contactos:");

		lblInformacion = new JLabel("Informacion");
		lblInformacion.setBounds(28, 310, 265, 14);
		contentPane.add(lblInformacion);

		lblAgenda = new JLabel("AGENDA");
		lblAgenda.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAgenda.setBounds(249, 11, 79, 35);
		contentPane.add(lblAgenda);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new BtnEliminarMouseListener());
		btnEliminar.setBounds(353, 345, 111, 23);
		contentPane.add(btnEliminar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new BtnNewButtonActionListener());
		btnEditar.setBounds(140, 259, 111, 23);
		contentPane.add(btnEditar);
		
		list = new JList();
		listModel = new DefaultListModel<String>();
		list.setModel(listModel);
		list.setBounds(342, 102, 151, 179);
		contentPane.add(list);
		
		btnCerrarSesion = new JButton("Cerrar sesion");
		btnCerrarSesion.addMouseListener(new BtnCerrarSesionMouseListener());
		btnCerrarSesion.setBounds(28, 345, 103, 23);
		contentPane.add(btnCerrarSesion);
		
		txtBuscar = new JTextField();
		txtBuscar.addFocusListener(new TxtBuscarFocusListener());
		txtBuscar.setText("Buscar");
		txtBuscar.setBounds(353, 71, 111, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		actualizarVector(vContactos);
	}
	private void actualizarVector(ArrayList<Contactos> vContactos) {
		for (int i = 0; i < vContactos.size(); i++) {
			listModel.addElement(vContactos.get(i).getNombre());
		}
	}
	private class BtnGuardarMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Contactos cont = new Contactos(textNombre.getText(), textTelefono.getText());
			if (!rdbtnHombre.isSelected() && !rdbtnMujer.isSelected()) {
				cont.setSexo(true);
			} else {
				if (rdbtnHombre.isSelected()) {
					cont.setSexo(true);
				} else {
					cont.setSexo(false);
				}
			}
			vContactos.add(cont);
			listModel.addElement(textNombre.getText());
			textNombre.setText("");
			textTelefono.setText("");
			
		}
	}

	private class BtnEliminarMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			String nombre = (String) comboBox.getSelectedItem();
			for (int i = 0; i < vContactos.size(); i++) {
				if (vContactos.get(i) != null && nombre.equalsIgnoreCase(vContactos.get(i).getNombre())) {
					vContactos.remove(i);
					lblInformacion.setText("Informacion");
					break;
				}
			}
			String salida = "";
			for (Contactos contactos : vContactos) {
				if (contactos != null) {
					salida += "\n" + contactos.toString();
				}
			}
			textArea.setText(salida);
			if (!nombre.equalsIgnoreCase("Contactos:")) {
				modeloComboBox.removeElement(comboBox.getSelectedItem());
			}
		}
	}

	private class BtnNewButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Contactos cont = new Contactos(textNombre.getText(), textTelefono.getText());
			String nombre = (String) comboBox.getSelectedItem();
			for (int i = 0; i < vContactos.size(); i++) {
				if(vContactos.get(i)!=null && nombre.equalsIgnoreCase(vContactos.get(i).getNombre())) {
					vContactos.add(cont);
					lblInformacion.setText("Informacion");
					break;
				}
			}
			textNombre.setText("");
			textTelefono.setText("");
		}
	}
	private class BtnCerrarSesionMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Login l = new Login();
			l.setVisible(true);
			dispose();
			IODatos.guardarContactos(usuario, vContactos);
		}
	}
	private class TxtBuscarFocusListener extends FocusAdapter {
		@Override
		public void focusGained(FocusEvent arg0) {
			if(txtBuscar.getText().equalsIgnoreCase("Buscar")) {
				txtBuscar.setText("");
			}
		}
		
		@Override
		public void focusLost(FocusEvent arg0) {
			if(txtBuscar.getText().equalsIgnoreCase("")) {
				txtBuscar.setText("Buscar");
			}
		}
	}
}