/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 * Aluno João Victor de Oliveira Júnior
 **********************************/
package daojpa;

import java.util.List;

public interface DAOInterface<T> {
	public void persistir(T obj);
	public T localizar(Object chave) throws Exception;
	public T atualizar(T obj);
	public void apagar(T obj) ;
	public void reler(T obj);
	public List<T> listar();
}
