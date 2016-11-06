/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Aluno João Victor de Oliveira Júnior
 **********************************/

package modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import daodb4o.AutoGenerateIDInterface;

@Entity
@AttributeOverride(name="nome",
column=@Column(name="NOMEMEC"))
public class Mecanico extends Pessoa{

//	@OneToMany(mappedBy="mecanico", cascade=CascadeType.ALL)
//	ArrayList <Cliente> clientes = new ArrayList<Cliente>();
	
	@OneToMany(mappedBy="mecanico", cascade=CascadeType.ALL)
	protected ArrayList <OrdemServico> ordensservicos = new ArrayList<OrdemServico>();
	private int matricula;
	public Mecanico(String nome, int telefone, int matricula) {
		super(nome, telefone);
		this.matricula = matricula;
	}

	public Mecanico() {
		super();
	}
	

	public void adicionarOrdemServico(OrdemServico os){
		ordensservicos.add(os);
		os.setMecanico(this);
	}
//	public void adicionarCliente(Cliente c){
//		clientes.add(c);
//	}
//	
//	public String getOS() {
//		String texto = "";
//		
//		List<Cliente> cliente = clientes;
//
//		for (Cliente cli : cliente){
//			texto += cli + "\n";
//		}
//		return texto;
//	}
//	
//	public ArrayList<Cliente> getCliente() {
//		return clientes;
//	}

	public ArrayList<OrdemServico> getOrdemServico() {
		return ordensservicos;
	}

	public void setOrdemServico(ArrayList<OrdemServico>ordensservicos) {
		this.ordensservicos = ordensservicos;
	}

//	public void adicionarOrdemServico(OrdemServico os){
//		ordensservicos.add(os);
//	}

	public void removerOrdemServico(OrdemServico os){
		ordensservicos.remove(os);
	}
	
	public String listarOS(){
		String texto = ""; 
		List<OrdemServico> list = this.ordensservicos;
		for(OrdemServico  ordser : list){
			texto += ordser + "\n";
		}
		
		return texto;
	}
	
	@Override
	public String toString() {
		return "Matricula = " + matricula + super.toString() ;// + getCliente();
	}

}
