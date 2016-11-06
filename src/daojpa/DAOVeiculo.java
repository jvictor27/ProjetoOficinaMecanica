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

import modelos.Veiculo;

public class DAOVeiculo  extends DAO<Veiculo>{
	
	public DAOVeiculo(){
		super();
	}
	
	public Veiculo localizarPelaPlaca (String placa){
		try{
			Query q = manager.createQuery("select v from Veiculo v where v.placa= '" + placa +"'");
			return (Veiculo) q.getSingleResult();

		}catch(NoResultException e){			
			return null;
		}
	}
	
	public boolean EmServico (String placa){
			Query q = manager.createQuery("select v from Veiculo v where v.placa = '" + placa + "'");	
			List<Veiculo> veiculo = q.getResultList();
			if(veiculo.get(veiculo.size() -1).isConsertado() == true){
				return true;
			}
			return false;
	}
	
	public List<Veiculo> listarVeiculoSemOS (){
		Query q = manager.createQuery("select v from Veiculo v where v.placa not in (select v.placa from OrdemServico o JOIN o.veiculo v)");
		List<Veiculo> lista = q.getResultList();
		return lista;
	}
	
	public List<Veiculo> listarVeiculoSemOSFechada (){
		Query q = manager.createQuery("select v from Veiculo v where v.placa in (select v.placa from OrdemServico o JOIN o.veiculo v)");
		List<Veiculo> lista = q.getResultList();
		return lista;
	}
	
	public List<OrdemServico> listaOSVeiculo (String placa){
		try{
			Query q = manager.createQuery("select os from Veiculo v JOIN v.ordensservicos os WHERE v.placa= '" + placa +"'");
			List<OrdemServico> lista = q.getResultList();
			return   lista;

		}catch(NoResultException e){			
			return null;
		}
	}
	
}
