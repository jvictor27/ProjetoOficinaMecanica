/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Aluno João Victor de Oliveira Júnior
 **********************************/

package modelos;



/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import daodb4o.AutoGenerateIDInterface;


//@EntityListeners( Monitor.class )  // CLASSE QUE IMPLEMENTA OS EVENTOS
@MappedSuperclass
public abstract class Pessoa implements AutoGenerateIDInterface{
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String nome;
	private int telefone;
	
	@Temporal(TemporalType.DATE)
	private Date dtcadastro = new Date();
	
//	@OneToMany(mappedBy="pessoa", cascade=CascadeType.ALL)
//	protected ArrayList <OrdemServico> ordensservicos = new ArrayList<OrdemServico>();

	//construtor vazio
	public Pessoa (){}
	
	public Pessoa(String nome, int telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}
	
	public void setId(int id) {
	this.id = id;
}
	
	public int getId() {
		return id;
	}
	
	
	
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		return ", nome=" + nome + ", Data cadastro no sistema " +  
				f.format(dtcadastro.getTime());
	}
	
	
		//---------EVENTOS DA CLASE ---------
		//desabilitar quando usar o monitor.java
//		@PrePersist
//		public void exibir1(){
//			System.out.println("vai persistir a pessoa... " + this);
//		}
//		@PostUpdate
//		public void exibir2(){
//			System.out.println("ja gravou a pessoa... " + this);
//		}
//		@PreRemove
//		public void exibir3(){
//			System.out.println("vai remover a pessoa... " + this);
//		}

}
