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
import modelos.OrdemServico;
//import DataInis.Cliente;
//import fachada.Sistema2;
//import DataInis.Mecanico;
//import DataInis.Produto;

public class TelaAbrirOS extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblMatricula;
	private JLabel lblPlaca;
	private JLabel lblCpf;
	private JLabel lblValor;
	private JLabel lblDataIni;
	private JLabel lblDataFim;
	private JLabel lblQtdParc;
	private JButton btnCriar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAbrirOS frame = new TelaAbrirOS();
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
	public TelaAbrirOS() {
		setTitle("Cadastrar Cliente");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 311, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(72, 11, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblMatricula = new JLabel("Matricula");
		lblMatricula.setBounds(10, 14, 46, 14);
		contentPane.add(lblMatricula);

		lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(10, 52, 46, 14);
		contentPane.add(lblPlaca);

		lblCpf = new JLabel("Cpf");
		lblCpf.setBounds(10, 83, 46, 14);
		contentPane.add(lblCpf);
		
		lblValor = new JLabel("Valor");
		lblValor.setBounds(10, 123, 46, 14);
		contentPane.add(lblValor);
		
//		lblDataIni = new JLabel("DataIni");
//		lblDataIni.setBounds(10, 162, 46, 14);
//		contentPane.add(lblDataIni);
//		
//		lblDataFim = new JLabel("DataFim");
//		lblDataFim.setBounds(10, 192, 46, 14);
//		contentPane.add(lblDataFim);
		
		lblQtdParc = new JLabel("QtdParc");
		lblQtdParc.setBounds(10, 159, 46, 14);
		contentPane.add(lblQtdParc);

		textField_1 = new JTextField();
		textField_1.setBounds(72, 49, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(72, 80, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(72, 120, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(72, 159, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
//		textField_5 = new JTextField();
//		textField_5.setBounds(72, 189, 86, 20);
//		contentPane.add(textField_5);
//		textField_5.setColumns(10);
//		
//		textField_6 = new JTextField();
//		textField_6.setBounds(72, 219, 86, 20);
//		contentPane.add(textField_6);
//		textField_6.setColumns(10);

		btnCriar = new JButton("Abrir OS");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int Matricula = Integer.parseInt(textField.getText());
					String Placa = textField_1.getText();
					int cpf = Integer.parseInt(textField_2.getText());
					double valor = Double.parseDouble(textField_3.getText());
//					String DataIni = textField_4.getText();
//					String DataFim = textField_5.getText();
					int QtdParc = Integer.parseInt(textField_4.getText());
					OrdemServico os = Sistema.abrirOrdemServico(Matricula, Placa, cpf, valor,QtdParc);
					JOptionPane.showMessageDialog(null,"aberta ");
					
					textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					textField_3.setText("");
//					textField_4.setText("");
//					textField_6.setText("");
					textField_4.setText("");
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
