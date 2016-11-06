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


public class DAOCliente  extends DAO<Cliente>{
	
	public DAOCliente(){
		super();
	}
	
	public Cliente localizarPeloCpf (int cpf){
		try{
			Query q = manager.createQuery("select c from Cliente c where c.cpf= '" + cpf +"'");
			return (Cliente) q.getSingleResult();

		}catch(NoResultException e){			
			return null;
		}
	}

	
//	public OrdemServico listaOSCliente (){
//		try{
//			Query q = manager.createQuery("select os from Cliente c JOIN c.ordensservicos os WHERE c.cpf= '" + cpf +"'");
//			List<OrdemServico> lista = q.getResultList();
//			return  (OrdemServico) lista;
//
//		}catch(NoResultException e){			
//			return null;
//		}
//	}
	
	public List<OrdemServico> listaOSCliente (int cpf){
	try{
		Query q = manager.createQuery("select os from Cliente c JOIN c.ordensservicos os WHERE c.cpf= '" + cpf +"'");
		List<OrdemServico> lista = q.getResultList();
		return   lista;

	}catch(NoResultException e){			
		return null;
	}
}
	
}
