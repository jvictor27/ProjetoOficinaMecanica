/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Aluno João Victor de Oliveira Júnior
 **********************************/

package modelos;


import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Veiculo {
	@Id
	private String placa;
	private String modelo;
	private boolean emServico=false;
	@ManyToOne 
	private Cliente cliente;

	public Veiculo(String placa, String modelo, Cliente cliente) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.cliente = cliente;
	}
	public Veiculo(String placa, String modelo) {
		super();
		this.placa = placa;
		this.modelo = modelo;
		this.emServico = this.emServico;

	}
	public Veiculo() {
		super();
	}
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public boolean isConsertado() {
		return emServico;
	}

	public void setEmConserto(boolean emServico) {
		this.emServico = emServico;
	}
	
//	--------------------RELACIONAMENTO--------------------------------
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Carro(s) Dono=" + cliente.getNome() + ", placa=" + placa + ", modelo=" + modelo + ", No conserto=" + emServico + "\n";
	}


}
