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
import modelos.OrdemServico;
import modelos.Veiculo;
//import modelos.Produto;

public class TelaEncerrarOS extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblPlaca;
	private JLabel lblCpf;
	private JButton btnCriar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEncerrarOS frame = new TelaEncerrarOS();
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
	public TelaEncerrarOS() {
		setTitle("Cadastrar Carro");
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

		lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(10, 14, 46, 14);
		contentPane.add(lblPlaca);


		lblCpf = new JLabel("Cpf");
		lblCpf.setBounds(10, 45, 46, 14);
		contentPane.add(lblCpf);

		textField_1 = new JTextField();
		textField_1.setBounds(72, 49, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		btnCriar = new JButton("Encerrar OS");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String placa = textField.getText();
					int cpf = Integer.parseInt(textField_1.getText());
					OrdemServico os = Sistema.encerrarOrdemservico(placa, cpf);
					JOptionPane.showMessageDialog(null,"encerrada ");
					
					textField.setText("");
					textField_1.setText("");
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
