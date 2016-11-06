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
import javax.persistence.OneToOne;
import daodb4o.AutoGenerateIDInterface;
@Entity
public class Avista extends Pagamento {

	private int desconto = 10;
	private double valorAvista;
	
	@OneToOne
	private OrdemServico ordemservico;
//	@OneToOne
//	private OrdemServico ordemservico;
public Avista (){}
	
	public Avista(double valor) {
		super(valor);
		//this.setValorAvista();
		this.valorAvista = this.valorOS - (this.valorOS * this.desconto) / 100;
		this.valorPagamento = this.getValorAvista();
		//this.valorAvista = this.getValorAvista();
	}
	
	

	

	@Override
	public String toString() {
		return "Avista [desconto=" + this.getDesconto() + ", valor a vista=" + this.getValorAvista() + ", valorOS=" + this.getValorOS() + "\n]";
	}

	public int getDesconto() {
		return desconto;
	}
	
	public void setValorAvista() {
		this.valorPagamento = this.valorPagamento - (this.valorPagamento * this.desconto) / 100;
	}

	public double getValorAvista() {
		//this.valorAvista = this.getValorPagamento() - (this.getValorPagamento() * this.getDesconto()) / 100;
		return this.valorAvista ;
	}
	
	public OrdemServico getOrdemservico() {
		return ordemservico;
	}

	public void setOrdemservico(OrdemServico ordemservico) {
		this.ordemservico = ordemservico;
	}
	
}
