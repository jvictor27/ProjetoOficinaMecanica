/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Aluno João Victor de Oliveira Júnior
 **********************************/

package modelos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import daodb4o.AutoGenerateIDInterface;
@Entity
@AttributeOverride(name="nome",
column=@Column(name="NOMECLI"))
public class Cliente extends Pessoa implements AutoGenerateIDInterface{
	
	
//	@ManyToOne
//	private Mecanico mecanico;
	
	@OneToMany(mappedBy="cliente", cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
	
	@OneToMany(mappedBy="cliente", cascade=CascadeType.ALL)
	protected ArrayList <OrdemServico> ordensservicos = new ArrayList<OrdemServico>();
	private int cpf;
	
	public Cliente() {
		super();
	}

	public Cliente(String nome, int telefone, int cpf) {
		super(nome, telefone);
		this.cpf = cpf;
		
	}

	public void adicionarVeiculo(Veiculo v){
		veiculos.add(v);
		v.setCliente(this);
	}

	public void removerVeiculo(Veiculo v){
		veiculos.remove(v);
	}
	
	public Veiculo localizarVeiculo(String placa){
		for(Veiculo v : veiculos)
			if(v.getPlaca().equals(placa)) 
				return v;
		
		return null;			
	}

	
	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	
//	public void setMecanico(Mecanico mecanico) {
//		this.mecanico = mecanico;
//		//mecanico.adicionarCliente(this);
//	}
//	
//	public Mecanico getMecanico(){
//		return mecanico;
//	}

	public ArrayList<OrdemServico> getOrdemServico() {
		return ordensservicos;
	}

	public void setOrdemServico(ArrayList<OrdemServico>ordensservicos) {
		this.ordensservicos = ordensservicos;
	}

	public void adicionarOrdemServico(OrdemServico os){
		ordensservicos.add(os);
	}

	public void removerOrdemServico(OrdemServico os){
		ordensservicos.remove(os);
	}
	
	public int getCpf(){
		return this.cpf;
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
		return "Cliente cpf = " + cpf + super.toString() + "\n" + veiculos + "\n";
	}

}
