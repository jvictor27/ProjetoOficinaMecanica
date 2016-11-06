/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Aluno João Victor de Oliveira Júnior
 **********************************/

package modelos;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import daodb4o.AutoGenerateIDInterface;


@Entity
public class OrdemServico implements AutoGenerateIDInterface{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Calendar datainicio;
	private Calendar datafim;
	private double valor;
	private boolean finalizado=false;
//	@ManyToOne
	private Veiculo veiculo;
	@ManyToOne
	private Mecanico mecanico;
	@ManyToOne
	private Cliente cliente;
	@OneToOne
	private Parcelado pagamentoParc;
	
	@OneToOne
	private Avista pagamentoAv;
//	@OneToOne
//	private Parcelado parcelado;

	public OrdemServico() {
		super();
	}

	public OrdemServico(Calendar datainicio, Veiculo veiculo,
			Pessoa mecanico, Cliente cliente, Avista avista, double valor) {
		super();
		this.datainicio = datainicio;
		this.datafim = datafim;

		this.valor = valor;
		this.pagamentoAv = avista;
		this.veiculo = veiculo;
		this.mecanico = (Mecanico) mecanico;
		this.cliente =  cliente;
	}
	
	public OrdemServico(Calendar datainicio, Veiculo veiculo,
			Pessoa mecanico, Pessoa cliente, Parcelado parcelado) {
		super();
		this.datainicio = datainicio;
		this.datafim = datafim;

		//this.valor = this.pagamento.valorPagamento;
		this.pagamentoParc = parcelado;
		this.veiculo = veiculo;
		this.mecanico = (Mecanico) mecanico;
		this.cliente = (Cliente) cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDatainicio() {
		return datainicio;
	}

	public String getDatainicioStr() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(datainicio.getTime());
	}

	public void setDatainicio(Calendar datainicio) {
		this.datainicio = datainicio;
	}

	public Calendar getDatafim() {
		return datafim;
	}

	public void setDatafim(Calendar date) {
		this.datafim = date;
	}

	public String getDatafimStr() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(datafim.getTime());
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Veiculo getCarro() {
		return veiculo;
	}


	public void setCarro(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Pessoa getCliente() {
		return mecanico;
	}

	public void setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public void setPagamentoParc (Pagamento pagamento) {
		this.pagamentoParc = pagamentoParc;
	}
	
	public Pagamento getPagamentoParc() {
		return this.pagamentoParc;
	}
	
	public void setPagamentoAv (Pagamento pagamento) {
		this.pagamentoAv = pagamentoAv;
	}
	
	public Pagamento getPagamentoAv() {
		return this.pagamentoAv;
	}
	@Override
	public String toString() {
		if(this.datafim == null){
		return "Ordem de serviço [id=" + id + ", datainicio=" + getDatainicioStr() + ", finalizado=" + finalizado + ", veiculo=" + veiculo.getPlaca() + ", mecanico=" + mecanico.getNome() + ", Cliente=" + cliente.getNome() + "\n]";
	}
		return "Ordem de serviço [id=" + id + ", datainicio=" + getDatainicioStr() + ", datafim=" + getDatafimStr() + ", finalizado=" + finalizado + ", veiculo=" + veiculo.getPlaca() + ", mecanico=" + mecanico.getNome() + ", Cliente=" + cliente.getNome() + "\n]";
	}

}
