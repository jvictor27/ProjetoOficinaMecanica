/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Aluno João Victor de Oliveira Júnior
 **********************************/

package fachada;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import daodb4o.DAO;
import daodb4o.DAOCliente;
import daodb4o.DAOMecanico;
import daodb4o.DAOOrdemServico;
import daodb4o.DAOVeiculo;

//import daojpa.DAO;
//import daojpa.DAOCliente;
//import daojpa.DAOMecanico;
//import daojpa.DAOOrdemServico;
//import daojpa.DAOVeiculo;

import modelos.Avista;
import modelos.Cliente;
import modelos.Mecanico;
import modelos.OrdemServico;
import modelos.Pagamento;
import modelos.Parcelado;
import modelos.Veiculo;

public class Sistema {
	private static SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
	private static DAOCliente daocliente = new DAOCliente();
	private static DAOMecanico daomecanico = new DAOMecanico();
	private static DAOVeiculo daoveiculo = new DAOVeiculo();
	private static DAOOrdemServico daoordemservico = new DAOOrdemServico();
	//private static DAOProduto daoproduto = new DAOProduto() ;

	public static void inicializar(){
		DAO.abrir();
	}
	
	public static void finalizar(){
		DAO.fechar();
	}

	public static Mecanico cadastrarMecanico(String nome, int telefone, int matricula) throws Exception{
		DAO.begin();
		Mecanico m = daomecanico.localizarPelaMatricula(matricula);
		if (matricula < 1)
			throw new Exception("Matricula incorreta");
		if (nome.isEmpty())
			throw new Exception("Preencha nome");
		if (m != null)
			throw new Exception("Mecânico ja cadastrado");
		
		m = new Mecanico(nome, telefone, matricula);
		
		daomecanico.persistir(m);
		DAO.commit();

		return m;
	}

	public static Cliente cadastrarCliente(String nome, int telefone, int cpf, String placa, String modelo) throws Exception{
		DAO.begin();
		Cliente c = daocliente.localizarPeloCpf(cpf);
		Veiculo v = daoveiculo.localizarPelaPlaca(placa);
		if (nome.isEmpty())
			throw new Exception("Preencha nome");
		if (placa.isEmpty())
			throw new Exception("Preencha placa");
		if (modelo.isEmpty())
			throw new Exception("Preencha modelo");
		if (c != null)
			throw new Exception("Cliente ja cadastrado");
		if (v != null)
			throw new Exception("Veiculo ja cadastrado");
		
		
		c = new Cliente(nome, telefone, cpf);
		v = new Veiculo(placa, modelo);
		c.adicionarVeiculo(v);
		
		
		
		daocliente.persistir(c);
		daoveiculo.persistir(v);
		DAO.commit();

		return c;

	}

	public static Veiculo cadastrarCarro(String placa, String modelo, int cpf) throws Exception{
		DAO.begin();
		Cliente c = daocliente.localizarPeloCpf(cpf);
		Veiculo v = daoveiculo.localizarPelaPlaca(placa);
		if (placa.isEmpty())
			throw new Exception("Preencha placa");
		if (modelo.isEmpty())
			throw new Exception("Preencha modelo");
		if (c == null)
			throw new Exception("Esse cliente não existe");
		if (v != null)
			throw new Exception("Veiculo ja cadastrado");
		
		v = new Veiculo(placa, modelo);
		c.adicionarVeiculo(v);
		
		
		daoveiculo.persistir(v);
		daocliente.atualizar(c);
		
		DAO.commit();

		return v;

	}

	public static OrdemServico abrirOrdemServico(int matricula, String placa, int cpf, double valor, int qtdparc) throws Exception{
		DAO.begin();
		
//		GregorianCalendar datainicio = new GregorianCalendar();
//		GregorianCalendar datafinal = new GregorianCalendar();
		
//		Date d = new Date();
		GregorianCalendar datainicio = new GregorianCalendar();
		datainicio.getTime();
//		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//		datainicio.setTime(formatador.parse(datainicio));
//		datafinal.setTime(formatador.parse(data2));
		
		Veiculo v = daoveiculo.localizarPelaPlaca(placa);
		Mecanico m = daomecanico.localizarPelaMatricula(matricula);
		Cliente c = daocliente.localizarPeloCpf(cpf);
		if(v==null | m==null | c==null) throw new Exception ("dados incorretos para ordem de serviço");

		if(daoveiculo.EmServico(placa)){
			throw new Exception("Este carro já está no conserto");
		}
		
		if(v.getCliente().getCpf() !=  c.getCpf()){
			throw new Exception("Cliente não possui carro com essa placa");
		}
		
			
		Pagamento pagamento;
		OrdemServico ordemservico;
		if(qtdparc > 0){
			pagamento = new Parcelado(valor, qtdparc);
			Parcelado parcelado = (Parcelado) pagamento;
			ordemservico = new OrdemServico(datainicio, v, m, c, parcelado);
			parcelado.setOrdemservico(ordemservico);
		}else{
			pagamento = new Avista(valor);
			Avista avista = (Avista) pagamento;
			ordemservico = new OrdemServico(datainicio, v, m, c, avista, valor);
			avista.setOrdemservico(ordemservico);
		}
		
		v.setEmConserto(true);
		c.adicionarOrdemServico(ordemservico);

		m.adicionarOrdemServico(ordemservico);
		
		daocliente.atualizar(c);
		daoveiculo.atualizar(v);
		daomecanico.atualizar(m);
		daoordemservico.persistir(ordemservico);
		DAO.commit();

		return ordemservico;
	}

	public static OrdemServico encerrarOrdemservico(String placa, int cpf)throws Exception{
		DAO.begin();
		Cliente c = daocliente.localizarPeloCpf(cpf);
		
		if(c.localizarVeiculo(placa) == null){
		throw new Exception ("Cliente não tem carro com essa placa");
		}
		
		Veiculo v = c.localizarVeiculo(placa);
		
		OrdemServico ordemservico = null;
		
		List<OrdemServico> ordensservicos = c.getOrdemServico();
		
		if (!daoveiculo.EmServico(placa)) {
			throw new Exception("Este carro não tem OS a ser fechada");
		}
		
		for(OrdemServico ordser : ordensservicos){
			if(placa.equals(ordser.getCarro().getPlaca())){
				ordemservico = ordser;
			}
		}
		
		GregorianCalendar hj = new GregorianCalendar();
		hj.getTime();

		ordemservico.setDatafim(hj);
		ordemservico.setFinalizado(true);

		v.setEmConserto(false);
		
		daoveiculo.atualizar(v);
		daoordemservico.atualizar(ordemservico);
		
		DAO.commit();
		return ordemservico;
	}
	
	public static void apagarCarroSemOS() throws Exception{
		DAO.begin();
		
		List<Veiculo> lista = daoveiculo.listarVeiculoSemOS();
		if(lista.size() < 0){
		throw new Exception ("Não existe carros sem OS");	
		}
		
		for(Veiculo car : lista ){
	    Cliente cli = car.getCliente();
	    cli.removerVeiculo(car);
		daoveiculo.apagar(car);
		
	}

	DAO.commit();	
	}


	public static String listarClientes(){
		List<Cliente> aux = daocliente.listar();
		String texto = "Listagem de Clientes: \n";
		
		if (aux.isEmpty())
			texto += "não tem clientes cadastrados";
		else {	
			for(Cliente c: aux) {
				texto += "\n" + c; 
			}
		}

		return texto;
	} 

	public static String listarCarros(){
		List<Veiculo> aux = daoveiculo.listar();
		String texto = "Listagem de Veículos: \n";
		
		if (aux.isEmpty())
			texto += "não tem veículos cadastrados";
		else {	
			for(Veiculo v: aux) {
				texto += "\n" + v; 
			}
		}
		return texto;
	}
	
	public static String listarOrdensServicos(){
		List<OrdemServico> aux = daoordemservico.listar();
		String texto = "Listagem de OS's: \n";
		
		if (aux.isEmpty())
			texto += "não tem os's abertas";
		else {	
			for(OrdemServico v: aux) {
				texto += "\n" + v; 
			}
		}
		return texto;
	}
	
	public static String listarMecanicos(){
		List<Mecanico> aux = daomecanico.listar();
		String texto = "Listagem de Mecânicos: \n";
		
		if (aux.isEmpty())
			texto += "não tem mecânicos cadastrados";
		else {	
			for(Mecanico m: aux) {
				texto += "\n" + m; 
			}
		}

		return texto;
	}

	public static String ConsultarCarroOSFechada() throws Exception{
		DAO.begin();
		String texto = "";
		
		List<Veiculo> lista = daoveiculo.listarVeiculoSemOSFechada();
		
		if(lista.size() < 0){
		throw new Exception ("Não existe carros com OS");	
		}

		for(Veiculo car : lista ){
			if(car.isConsertado() == false){
				texto += car + "\n";
			}
		}
		DAO.commit();
		return texto;
	}
//	
	public static String ConsultarCarroOSAberta() throws Exception{
		DAO.begin();
		String texto = "";
		
		List<Veiculo> lista = daoveiculo.listarVeiculoSemOSFechada();
		
		if(lista.size() < 0){
		throw new Exception ("Não existe carros com OS");	
		}

		for(Veiculo car : lista ){
			if(car.isConsertado() != false){
				texto += car + "\n";
			}
		}
		DAO.commit();
		return texto;
	}
//	
	public static String ConsultarCarroSemOS() throws Exception{
		String texto = "";
		DAO.begin();
		List<Veiculo> lista = daoveiculo.listarVeiculoSemOS();

		if(lista.size() < 0){
				throw new Exception ("Não existe carros com OS");	
		}

		for(Veiculo car : lista ){
			
				texto += car + "\n";
			
		}

		DAO.commit();
		return texto;
	}
	
	
	
	public static String ConsultarOSCliente(int cpf) throws Exception{
		String texto = "";
		DAO.begin();

		
		List<OrdemServico> lista =  daocliente.listaOSCliente(cpf);

		if(lista.size() < 0){
				throw new Exception ("Não existem os's desse cliente");	
		}

		for(OrdemServico os : lista ){
			
			texto += os + "\n";
		}
		return texto;
	}
	
	public static String ConsultarOSMecanico(int mat) throws Exception{
		String texto = "";
		DAO.begin();

		
		List<OrdemServico> lista =  daomecanico.listaOSMecanico(mat);

		if(lista.size() < 0){
				throw new Exception ("Não existem os's desse cliente");	
		}

		for(OrdemServico os : lista ){
			
			texto += os + "\n";
		}
		return texto;
	}
	
	public static String ConsultarOSVeiculo(String placa) throws Exception{
		String texto = "";
		DAO.begin();

		
		List<OrdemServico> lista =  daoveiculo.listaOSVeiculo(placa);

		if(lista.size() < 0){
				throw new Exception ("Não existem os's desse veiculo");	
		}

		for(OrdemServico os : lista ){
			
			texto += os + "\n";
		}
		return texto;
	}
//
//	public static String ConsultarPagamentosAVista() throws Exception{
//		String texto = "";
//		manager.getTransaction().begin();
//
//		Query q = manager.createQuery("select p from Avista p");
//		List<Avista> lista = q.getResultList();
//
//		if(lista.size() < 0){
//				throw new Exception ("Não existem pagamentos feitos a vista");	
//		}
//
//		for(Avista pag : lista ){
//			
//			texto += pag + "\n";
//		
//	}
//
//		manager.getTransaction().commit();
//		return texto;
//	}
//
//	public static String ConsultarPagamentosParcelado() throws Exception{
//		String texto = "";
//		manager.getTransaction().begin();
//
//		Query q = manager.createQuery("select p from Parcelado p");
//		List<Parcelado> lista = q.getResultList();
//
//		if(lista.size() < 0){
//				throw new Exception ("Não existem pagamentos feitos parcelados");	
//		}
//
//		for(Parcelado pag : lista ){
//			
//				texto += pag + "\n";
//			
//		}
//
//		manager.getTransaction().commit();
//		return texto;
//	}
//	
}
