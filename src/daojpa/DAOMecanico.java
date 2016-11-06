/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 * Aluno João Victor de Oliveira Júnior
 **********************************/
package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelos.Cliente;
import modelos.Mecanico;
import modelos.OrdemServico;


public class DAOMecanico  extends DAO<Mecanico>{
	
	public DAOMecanico(){
		super();
	}
	
	
	public Mecanico localizarPelaMatricula (int mat){
		try{
			Query q = manager.createQuery("select m from Mecanico m where m.matricula= '" + mat +"'");
			return (Mecanico) q.getSingleResult();

		}catch(NoResultException e){			
			return null;
		}
	}
	
	public List<OrdemServico> listaOSMecanico (int mat){
	try{
		Query q = manager.createQuery("select os from Mecanico m JOIN m.ordensservicos os WHERE m.matricula= '" + mat +"'");
		List<OrdemServico> lista = q.getResultList();
		return   lista;

	}catch(NoResultException e){			
		return null;
	}
}

}
