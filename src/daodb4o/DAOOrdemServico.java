/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 * Aluno João Victor de Oliveira Júnior
 **********************************/
package daodb4o;


import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelos.Cliente;
import modelos.OrdemServico;



public class DAOOrdemServico  extends DAO<OrdemServico>{
	public DAOOrdemServico(){
		super();
	}

	@Override
	public OrdemServico localizar (Object chave){
		try{
			int id =  (Integer) chave;
			Query q = manager.query();
			q.constrain(OrdemServico.class);
			q.descend("id").constrain(id);
			List<OrdemServico> resultados = q.execute();
			if (resultados.size()>0)
				return resultados.get(0);
			else
				return null;
		}
		catch(ClassCastException e){
			throw new RuntimeException("campo de busca invalido");
		}
	}


}
