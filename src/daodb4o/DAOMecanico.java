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

import modelos.Mecanico;
import modelos.OrdemServico;


public class DAOMecanico  extends DAO<Mecanico>{
	
	public DAOMecanico(){
		super();
	}
	
	
	public Mecanico localizarPelaMatricula (Object chave){
		try{
			int matricula =  (Integer) chave;
			Query q = manager.query();
			q.constrain(Mecanico.class);
			q.descend("matricula").constrain(matricula);
			List<Mecanico> resultados = q.execute();
			if (resultados.size()>0)
				return resultados.get(0);
			else
				return null;
		}
		catch(ClassCastException e){
			throw new RuntimeException("campo de busca invalido");
		}
	}
	
	public List<OrdemServico> listaOSMecanico (int mat){
	try{
		Query q = manager.query();
		q.constrain(OrdemServico.class);
		q.descend("mecanico").descend("matricula").constrain(mat);
		List<OrdemServico> lista = q.execute();
		return   lista;

	}catch(NoResultException e){			
		return null;
	}
}
	
	@Override
	public Mecanico localizar(Object chave) {
		// TODO Auto-generated method stub
		return null;
	}
}//final da classe

//**************************************************************
//@SuppressWarnings("serial")
//class Filtro  implements Evaluation {
//	public void evaluate(Candidate candidate) {
//		Mecanico m = (Mecanico) candidate.getObject();
//		candidate.include(m.getOrdemServico().size()==0);
//	}
//}