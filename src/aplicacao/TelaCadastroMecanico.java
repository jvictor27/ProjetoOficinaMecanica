package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 * Aluno João Victor de Oliveira Júnior
 **********************************/
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Sistema;
//import fachada.Sistema2;
import modelos.Mecanico;
//import modelos.Produto;

public class TelaCadastroMecanico extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblMatricula;
	private JButton btnCriar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroMecanico frame = new TelaCadastroMecanico();
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
	public TelaCadastroMecanico() {
		setTitle("Cadastrar Mecânico");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 311, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(72, 11, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 14, 46, 14);
		contentPane.add(lblNome);

		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 52, 46, 14);
		contentPane.add(lblTelefone);

		lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(10, 83, 46, 14);
		contentPane.add(lblMatricula);

		textField_1 = new JTextField();
		textField_1.setBounds(72, 49, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(72, 80, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		btnCriar = new JButton("Cadastrar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String nome = textField.getText();
					int telefone = Integer.parseInt(textField_1.getText());
					int matricula = Integer.parseInt(textField_2.getText());
					Mecanico m = Sistema.cadastrarMecanico(nome, telefone, matricula);
					JOptionPane.showMessageDialog(null,"cadastrado "+m.getNome());
					
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField.requestFocus();
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(168, 48, 115, 23);
		contentPane.add(btnCriar);
	}
}
