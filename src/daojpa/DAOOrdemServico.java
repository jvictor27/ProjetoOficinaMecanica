/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Aluno João Victor de Oliveira Júnior
 **********************************/
package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelos.Cliente;
import modelos.Mecanico;
import modelos.OrdemServico;
import modelos.Veiculo;

public class DAOOrdemServico  extends DAO<OrdemServico>{
	
	public DAOOrdemServico(){
		super();
	}
	
//	public List<OrdemServico> listarOSFechada (){
//		Query q = manager.createQuery("select v from Veiculo v where v.placa in (select v.placa from OrdemServico o JOIN o.veiculo v)");
//		List<Veiculo> lista = q.getResultList();
//		return lista;
//	}

	
//	public List<Cliente> listaClienteOSfechada (){
//		try{
//			Query q = manager.createQuery("select c from OrdemServico os JOIN os.cliente c WHERE os.finalizado = true ");
//			 List<Cliente> lista = q.getResultList();
//			return  lista;
//
//		}catch(NoResultException e){			
//			return null;
//		}
//	}
	
//	public List<OrdemServico> listaOSCliente (int cpf){
//	try{
//		Query q = manager.createQuery("select os from Cliente c JOIN c.ordensservicos os WHERE c.cpf= '" + cpf +"'");
//		List<OrdemServico> lista = q.getResultList();
//		return   lista;
//
//	}catch(NoResultException e){			
//		return null;
//	}
//}
}
