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
public class Parcelado extends Pagamento {

	private int acrescimo = 10;
	private int qtdParcelas;
	private double valorParcelado;
	private double valorParcelas;
	
	@OneToOne
	private OrdemServico ordemservico;
	
//	@OneToOne
//	private OrdemServico ordemservico;
	
	
	
	public Parcelado(double valor,int qtdparc) {
		super(valor);
		this.qtdParcelas = qtdparc;
		setValorParcelado();// = this.valorOS + (this.valorOS * this.getAcrescimo()) / 100;
		setValorParcelas();// = this.valorPagamento / getQtdParcelas();
		this.valorPagamento = this.getValorParcelado();
		//this.valorParcelado = this.getValorParcelado();
		//this.valorParcelas = this.getValorParcelas();
	}
	public Parcelado (){}
	public int getQtdParcelas() {
		return qtdParcelas;
	}

//	public void setQtdParcelas(int qtdParcelas) {
//		this.qtdParcelas = qtdParcelas;
//	}

	public int getAcrescimo() {
		if(this.getQtdParcelas() > 6){
		return this.acrescimo;
		}
		return 0;
	}

	
	@Override
	public String toString() {
		return "Parcelado [QtdParcelas=" + this.getQtdParcelas() + ", Acrescimo=" + this.getAcrescimo()
				+ ", ValorParcelado=" + this.getValorParcelado() + ", ValorParcelas=" + this.getValorParcelas()+ ", valorOS=" + this.getValorOS() + "\n]";
	}

	public void setValorParcelado() {
		this.valorParcelado= this.valorPagamento + (this.valorPagamento * this.getAcrescimo()) / 100;
	}
	
	public void setValorParcelas() {
		 this.valorParcelas = this.valorPagamento / getQtdParcelas();
	}
	
	public double getValorParcelado() {
		//return this.valorParcelado = this.getValorPagamento() + (this.getValorPagamento() * this.getAcrescimo()) / 100;
		return this.valorParcelado;
	}
	
	public double getValorParcelas() {
		return this.valorParcelas ;
	}
	
	public OrdemServico getOrdemservico() {
		return ordemservico;
	}

	public void setOrdemservico(OrdemServico ordemservico) {
		this.ordemservico = ordemservico;
	}
}
