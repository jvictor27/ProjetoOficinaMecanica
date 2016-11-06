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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

//import fachada.Sistema2;
import fachada.Sistema;

public class TelaPrincipal {

	private JFrame frmPrincipal;
	private JMenuItem mntmCadastrar;
	private JMenuItem mntmListar;
	private JMenu mnMecanico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				Sistema.inicializar();
				JOptionPane.showMessageDialog(null, "sistema inicializado !");
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Sistema.finalizar();
				JOptionPane.showMessageDialog(null, "sistema finalizado !");
			}
		});
		frmPrincipal.setTitle("Ofina do Zé");
		frmPrincipal.setBounds(100, 100, 450, 300);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);
		
		mnMecanico = new JMenu("Mecanico");
		menuBar.add(mnMecanico);
		
		mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroMecanico j = new TelaCadastroMecanico();
				j.setVisible(true);
			}
		});
		mnMecanico.add(mntmCadastrar);
		
		mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagemMecanico j = new TelaListagemMecanico();
				j.setVisible(true);
			}
		});
		mnMecanico.add(mntmListar);
		
		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mntmConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultarOSMecanico j = new TelaConsultarOSMecanico();
				j.setVisible(true);
			}
		});
		mnMecanico.add(mntmConsultar);
		
		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);
		
		JMenuItem mntmCriar = new JMenuItem("Cadastrar");
		mntmCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroCliente j = new TelaCadastroCliente();
				j.setVisible(true);
			}
		});
		mnCliente.add(mntmCriar);
		
		JMenuItem mntmListar_1 = new JMenuItem("Listar");
		mntmListar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListagemCliente j = new TelaListagemCliente();
				j.setVisible(true);
			}
		});
		mnCliente.add(mntmListar_1);
		
		JMenuItem mntmConsultar_1 = new JMenuItem("Consultar");
		mntmConsultar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultarOSCliente j = new TelaConsultarOSCliente();
				j.setVisible(true);
			}
		});
		mnCliente.add(mntmConsultar_1);
		
		JMenu mnCarro = new JMenu("Carro");
		menuBar.add(mnCarro);
		
		JMenuItem mntmCriar_1 = new JMenuItem("Cadastrar");
		mntmCriar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroCarro j = new TelaCadastroCarro();
				j.setVisible(true);
			}
		});
		mnCarro.add(mntmCriar_1);
		
		JMenuItem mntmListar_2 = new JMenuItem("Listar");
		mntmListar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListagemCarro j = new TelaListagemCarro();
				j.setVisible(true);
			}
		});
		mnCarro.add(mntmListar_2);
		
		JMenuItem mntmConsultar_2 = new JMenuItem("Consultar");
		mntmConsultar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaConsultarOSVeiculo j = new TelaConsultarOSVeiculo();
				j.setVisible(true);
			}
		});
		mnCarro.add(mntmConsultar_2);
		
		JMenu mnOS = new JMenu("Ordem de Serviço");
		menuBar.add(mnOS);
		
		JMenuItem mntmCriar_2 = new JMenuItem("Abrir OS");
		mntmCriar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaAbrirOS j = new TelaAbrirOS();
				j.setVisible(true);
			}
		});
		mnOS.add(mntmCriar_2);
		
		JMenuItem mntmListar_3 = new JMenuItem("Listar OS");
		mntmListar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListagemOS j = new TelaListagemOS();
				j.setVisible(true);
			}
		});
		mnOS.add(mntmListar_3);
		
		JMenuItem mntmEncerrar = new JMenuItem("Encerrar OS");
		mntmEncerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaEncerrarOS j = new TelaEncerrarOS();
				j.setVisible(true);
			}
		});
		mnOS.add(mntmEncerrar);
		
	}
}
