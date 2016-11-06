/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 * Aluno João Victor de Oliveira Júnior
 **********************************/

package modelos;

/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */


import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PrePersist;

// usada pelas classes Pessoa e Telefone

public class Monitor{
	
	@PrePersist
	public void exibirmsg1(Object obj) throws Exception {
		System.out.println("vai persistir... " + obj);
		//validar pessoa
//		if (obj instanceof Pessoa && ((Pessoa)obj).getTelefones().isEmpty())  
//			throw new PersistenceException("pessoa invalida =" +
//					((Pessoa)obj).getNome());
		
		//validar telefone
//		if (obj instanceof Telefone && ((Telefone)obj).getNumero().length() > 9)  
//			throw new PersistenceException("telefone invalido ="+
//					((Telefone)obj).getNumero() );
		
	}
	
	@PostPersist
	public void exibirmsg2(Object obj) {
		System.out.println("ja persistiu... " + obj);
	}
	
	@PostLoad
	public void exibirmsg3(Object obj) {
			System.out.println("carregando... " + obj);
	}	
	
	@PostRemove
	public void exibirmsg4(Object obj) {
	
		System.out.println("removeu.... " + obj);
	}
	
}
