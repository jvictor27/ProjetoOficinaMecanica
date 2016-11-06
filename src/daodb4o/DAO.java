/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Maranhão Ayres
 * Aluno João Victor de Oliveira Júnior
 **********************************/

package daodb4o;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.constraints.UniqueFieldValueConstraintViolationException;

import modelos.Avista;
import modelos.Cliente;
import modelos.Mecanico;
import modelos.OrdemServico;
import modelos.Parcelado;


import modelos.Veiculo;


public abstract class DAO<T> implements DAOInterface<T> {
	protected static ObjectContainer manager;

	public static void abrir(){
		if(manager==null){			
			
//			Backup.criar("banco.db4o");	//opcional
//			System.out.println("backup realizado com sucesso !");
//			new File("banco.db4o").delete();   // apagar o arquivo do banco
	
			
			/******************************************
			 * 
			 *  CONFIGURACAO PARA USO LOCAL
			 *  
			 ******************************************/
			EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
			config.common().messageLevel(0);   //0,1,2,3,4
			config.common().objectClass(Mecanico.class).cascadeOnUpdate(true);
			config.common().objectClass(Mecanico.class).cascadeOnDelete(true);
			config.common().objectClass(Mecanico.class).cascadeOnActivate(true);	
			config.common().objectClass(Cliente.class).cascadeOnUpdate(true);
			config.common().objectClass(Cliente.class).cascadeOnDelete(true);
			config.common().objectClass(Cliente.class).cascadeOnActivate(true);
			config.common().objectClass(Veiculo.class).cascadeOnUpdate(true);
			config.common().objectClass(Veiculo.class).cascadeOnDelete(true);
			config.common().objectClass(Veiculo.class).cascadeOnActivate(true);
			config.common().objectClass(OrdemServico.class).cascadeOnUpdate(true);
			config.common().objectClass(OrdemServico.class).cascadeOnDelete(true);
			config.common().objectClass(OrdemServico.class).cascadeOnActivate(true);
			config.common().objectClass(Avista.class).cascadeOnUpdate(true);
			config.common().objectClass(Avista.class).cascadeOnDelete(true);
			config.common().objectClass(Avista.class).cascadeOnActivate(true);
			config.common().objectClass(Parcelado.class).cascadeOnUpdate(true);
			config.common().objectClass(Parcelado.class).cascadeOnDelete(true);
			config.common().objectClass(Parcelado.class).cascadeOnActivate(true);
 			//indexacao de atributos para agilizar a busca
			config.common().objectClass(Mecanico.class).objectField("matricula").indexed(true);
			config.common().objectClass(Cliente.class).objectField("cpf").indexed(true);
			config.common().objectClass(Veiculo.class).objectField("placa").indexed(true);
			config.common().objectClass(OrdemServico.class).objectField("id").indexed(true);
			config.common().objectClass(Avista.class).objectField("id").indexed(true);
			config.common().objectClass(Parcelado.class).objectField("id").indexed(true);
			manager = Db4oEmbedded.openFile(config, "oficina.db4o");
			
			
			/******************************************
			 * 
			 *  CONFIGURACAO PARA USO CLIENTE-SERVIDOR
			 *  
			 ******************************************/
//			ClientConfiguration config = Db4oClientServer.newClientConfiguration( ) ;
//			config.common().messageLevel(0);   //0,1,2,3,4
//			config.common().objectClass(Prateleira.class).cascadeOnUpdate(true);
//			config.common().objectClass(Prateleira.class).cascadeOnDelete(true);
//			config.common().objectClass(Prateleira.class).cascadeOnActivate(true);	
//			config.common().objectClass(Produto.class).cascadeOnUpdate(true);
//			config.common().objectClass(Produto.class).cascadeOnDelete(true);
//			config.common().objectClass(Produto.class).cascadeOnActivate(true);
//			// 		indexacao de atributos para agilizar a busca
//			config.common().objectClass(Prateleira.class).objectField("id").indexed(true);
//			config.common().objectClass(Produto.class).objectField("nome").indexed(true);
//			//manager = Db4oClientServer.openClient(config, "localhost", 34000,"usuario1","senha1");	
//			manager = Db4oClientServer.openClient(config,"10.0.4.90",34000,"usuario1","senha1");	


			
			//--------------Inicializar o gerente de id-----------------
			//Apos a criação do banco
			AutoGenerateIDManager.inicializar(manager);
			//----------------------------------------------------------
		}
	}

	public static void fechar(){
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}

	@Override
	public void persistir(T obj){
		manager.store(obj);
	}
	
	@Override
	public  abstract T localizar(Object chave) ;  //implementado nos dao especificos
	
	@Override
	public T atualizar(T obj){
		manager.store(obj);
		return obj;
	}
	@Override
	public void apagar(T obj) {
		manager.delete(obj);
	}
	@Override
	public void reler(T obj){
		manager.ext().refresh(obj, Integer.MAX_VALUE);
	}
	
	//--------transação---------------
	public static void begin(){	}		// tem que ser vazio!
	
	public static void commit(){
		try {
			manager.commit();
		} 
		// Evitar de gravar objetos duplicados
		catch (UniqueFieldValueConstraintViolationException e) {
			throw new RuntimeException ("atributo duplicado  " + e.getMessage());
		}
	}
	public static void flush(){	//commit intermediario
		commit();
	}
	public static void rollback(){
		manager.rollback();
	}
	@SuppressWarnings("unchecked")
	public List<T> listar(){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
		return (List<T>) manager.query(type);
	}

	
}

