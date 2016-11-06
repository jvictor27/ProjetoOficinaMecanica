/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Aluno João Victor de Oliveira Júnior
 **********************************/

package modelos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import daodb4o.AutoGenerateIDInterface;

@MappedSuperclass
public abstract class Pagamento implements AutoGenerateIDInterface {
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	protected double valorPagamento;
	protected double valorOS;
	
	public Pagamento (){}
	
	public Pagamento(double valor) {
		this.valorOS = valor;
		this.valorPagamento = valor;
	}
	
//	public void setValorOS(){
//		this.valorOS = this.getOrdemservico().getValor();
//	}
	
	public double getValorOS(){
		return valorOS;
	}
	
//	public OrdemServico getOrdemservico() {
//		return ordemservico;
//	}
//
//	public void setOrdemservico(OrdemServico ordemservico) {
//		this.ordemservico = ordemservico;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValorPagamento() {
		return this.valorPagamento;
	}
//	
//	public void setValorPagamento(double valorPagamento) {
//		this.valorPagamento = valorPagamento;
//	}
	
//	public OrdemServico getORdemServico() {
//		return this.ordemservico;
//	}
}
