/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 * Aluno João Victor de Oliveira Júnior
 **********************************/


package daojpa;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class DAO<T> implements DAOInterface<T> {
	protected static EntityManager manager;

	public DAO(){}
	
	public static void abrir(){
		if(manager==null){
			//propriedades do persistence.xml			
			HashMap<String,String> properties = new HashMap<String,String>();		
//			properties.put(PersistenceUnitProperties.JDBC_DRIVER, "org.postgresql.Driver" );	
//			properties.put(PersistenceUnitProperties.JDBC_URL, "jdbc:postgresql://localhost:5432/loja");
//			properties.put(PersistenceUnitProperties.JDBC_USER, "postgres");
//			properties.put(PersistenceUnitProperties.JDBC_PASSWORD, "ifpb");
//			properties.put(PersistenceUnitProperties.DDL_GENERATION, "create-tables");
//			properties.put(PersistenceUnitProperties.DDL_GENERATION_MODE, "database");
//			properties.put(PersistenceUnitProperties.LOGGING_LEVEL, "fine");
			
			EntityManagerFactory factory = 
					Persistence.createEntityManagerFactory("oficina",properties);
			manager = factory.createEntityManager();
		}
	}
	public static void fechar(){
		if(manager != null)
			manager = null;
	}
	

	public void persistir(T obj){
		manager.persist(obj);
	}
	
	@SuppressWarnings("unchecked")
	public T localizar(Object chave){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return manager.find(type, chave);
	}
	
	public T atualizar(T obj){
		return manager.merge(obj);
	}
	public void apagar(T obj) {
		manager.remove(obj);
	}
	public void reler(T obj){
		manager.refresh(obj);
	}	
	@SuppressWarnings("unchecked")
	public List<T> listar(){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		Query query = manager.createQuery("select x from " + type.getSimpleName() + " x");
		return (List<T>) query.getResultList();

	}

	//----------------------- TRANSAÇÃO   ----------------------
	public static void begin(){
		if(!manager.getTransaction().isActive())
			manager.getTransaction().begin();
	}
	public static void commit(){
		if(manager.getTransaction().isActive()){
			manager.getTransaction().commit();
			manager.clear();		// esvaziar o cache de objetos
		}
	}
	public static void rollback(){
		if(manager.getTransaction().isActive())
			manager.getTransaction().rollback();
	}
	public static void flush(){	//commit parcial
		manager.flush();
	}

}

