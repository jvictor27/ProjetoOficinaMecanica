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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import modelos.OrdemServico;
import fachada.Sistema;

public class TelaConsultarOSMecanico extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblMatricula;
	private JButton btnCriar;
	private JTextArea textArea;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultarOSMecanico frame = new TelaConsultarOSMecanico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public TelaConsultarOSMecanico() {
		setTitle("LISTAR OS's DO MECÂNICO");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 650, 300);
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
		
		btnCriar = new JButton("Consultar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int matricula = Integer.parseInt(textField.getText());
					
					textArea.setText(Sistema.ConsultarOSMecanico(matricula));
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(224, 149, 115, 23);
		contentPane.add(btnCriar);
		
		textArea = new JTextArea();		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(24, 29, 510, 109);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
		
	}
}
