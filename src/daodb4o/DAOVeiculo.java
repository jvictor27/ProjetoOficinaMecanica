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

import modelos.OrdemServico;

import modelos.Veiculo;

public class DAOVeiculo  extends DAO<Veiculo>{
	public DAOVeiculo(){
		super();
	}

	public Veiculo localizarPelaPlaca (String placa){
		try{
			Query q = manager.query();
			q.constrain(Veiculo.class);
			q.descend("placa").constrain(placa);
			List<Veiculo> resultados = q.execute();
			if (resultados.size()>0)
				return resultados.get(0);
			else
				return null;
		}
		catch(ClassCastException e){
			throw new RuntimeException("campo de busca invalido");
		}
	}
	
	public boolean EmServico (String placa){
		Query q = manager.query();
		q.constrain(Veiculo.class);
		q.descend("placa").constrain(placa);
		List<Veiculo> veiculo = q.execute();
		if(veiculo.get(veiculo.size() -1).isConsertado() == true){
			return true;
		}
		return false;
	}

	public  List<Veiculo> listarVeiculoSemOS() {
		//consulta SODA
		Query q = manager.query();
		q.constrain(Veiculo.class);
		q.descend("cliente").descend("ordensservicos").constrain(null);
		return q.execute(); 
	}
	
	public  List<Veiculo> listarVeiculoSemOSFechada() {
		//consulta SODA
		Query q = manager.query();
		q.constrain(Veiculo.class);
		q.descend("cliente").descend("ordensservicos");
		return q.execute(); 
	}

	@Override
	public Veiculo localizar(Object chave) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OrdemServico> listaOSVeiculo (String placa){
	try{
		Query q = manager.query();
		q.constrain(OrdemServico.class);
		q.descend("veiculo").descend("placa").constrain(placa);
		List<OrdemServico> lista = q.execute();
		return   lista;

	}catch(NoResultException e){			
		return null;
	}
}

}
