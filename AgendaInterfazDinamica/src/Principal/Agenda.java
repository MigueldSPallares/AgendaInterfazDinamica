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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class Agenda extends JFrame {

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
	private JButton btnBuscar;
	private JLabel lblInformacion;
	private JLabel lblAgenda;
	private JButton btnEliminar;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnEditar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agenda frame = new Agenda();
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
	public Agenda() {
		vContactos = new ArrayList<Contactos>();
		
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

		textTelefono = new JTextField();
		textTelefono.setBounds(108, 139, 111, 20);
		contentPane.add(textTelefono);
		textTelefono.setColumns(10);

		textArea = new JTextPane();
		textArea.setBounds(308, 100, 220, 200);
		contentPane.add(textArea);

		lblContactos = new JLabel("Mostrar contactos");
		lblContactos.setBounds(373, 75, 95, 14);
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

		comboBox = new JComboBox();
		modeloComboBox = new DefaultComboBoxModel();
		modeloComboBox.addElement("Contactos:");
		comboBox.setModel(modeloComboBox);
		comboBox.setBounds(140, 307, 111, 56);
		contentPane.add(comboBox);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addMouseListener(new BtnBuscarMouseListener());
		btnBuscar.setBounds(28, 306, 89, 23);
		contentPane.add(btnBuscar);

		lblInformacion = new JLabel("Informacion");
		lblInformacion.setBounds(283, 310, 268, 14);
		contentPane.add(lblInformacion);

		lblAgenda = new JLabel("AGENDA");
		lblAgenda.setFont(new Font("Arial", Font.PLAIN, 16));
		lblAgenda.setBounds(249, 11, 79, 35);
		contentPane.add(lblAgenda);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addMouseListener(new BtnEliminarMouseListener());
		btnEliminar.setBounds(28, 340, 89, 23);
		contentPane.add(btnEliminar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new BtnNewButtonActionListener());
		btnEditar.setBounds(140, 259, 111, 23);
		contentPane.add(btnEditar);
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
			
			textNombre.setText("");
			textTelefono.setText("");

			String salida = "";
			for (Contactos contactos : vContactos) {
				if (contactos != null) {
					salida += "\n" + contactos.toString();
				}
			}
			textArea.setText(salida);
			modeloComboBox.removeAllElements();
			modeloComboBox.addElement("Contactos:");
			for (Contactos contactos : vContactos) {
				if (contactos != null) {
					modeloComboBox.addElement(contactos.getNombre());
				}
			}
		}
	}

	private class BtnBuscarMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				int i = comboBox.getSelectedIndex();
				lblInformacion.setText(vContactos.get(i-1).toString());
			} catch (Exception e2) {
				// TODO: handle exception
			}
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
			
			String salida = "";
			for (Contactos contactos : vContactos) {
				if (contactos != null) {
					salida += "\n" + contactos.toString();
				}
			}
			textArea.setText(salida);
			modeloComboBox.removeAllElements();
			modeloComboBox.addElement("Contactos:");
			for (Contactos contactos : vContactos) {
				if (contactos != null) {
					modeloComboBox.addElement(contactos.getNombre());
				}
			}
		}
	}

}
