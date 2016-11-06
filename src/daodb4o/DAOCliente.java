/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 * Aluno João Victor de Oliveira Júnior
 **********************************/
package daodb4o;


import java.util.List;

import javax.persistence.NoResultException;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelos.Cliente;
import modelos.OrdemServico;



public class DAOCliente  extends DAO<Cliente>{
	public DAOCliente(){
		super();
	}

	
	public Cliente localizarPeloCpf (Object chave){
		try{
			int cpf =  (Integer) chave;
			Query q = manager.query();
			q.constrain(Cliente.class);
			q.descend("cpf").constrain(cpf);
			List<Cliente> resultados = q.execute();
			if (resultados.size()>0)
				return resultados.get(0);
			else
				return null;
		}
		catch(ClassCastException e){
			throw new RuntimeException("campo de busca invalido");
		}
	}


	
	public List<OrdemServico> listaOSCliente (int cpf){
	try{
		Query q = manager.query();
		q.constrain(OrdemServico.class);
		q.descend("cliente").descend("cpf").constrain(cpf);
		List<OrdemServico> lista = q.execute();
		return   lista;

	}catch(NoResultException e){			
		return null;
	}
}

	@Override
	public Cliente localizar(Object chave) {
		// TODO Auto-generated method stub
		return null;
	}


}
