/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Maranh�o Ayres
 * Aluno Jo�o Victor de Oliveira J�nior
 **********************************/

package daodb4o;

import java.io.File;
import java.io.IOException;

import com.db4o.defragment.Defragment;

public class Backup {

	public static void criar (String arquivo){
		try {
			if (new File(arquivo).exists()) {	
				if (new File(arquivo+".bak").exists()) 
					new File(arquivo+".bak").delete();
				Defragment.defrag(arquivo, arquivo+".bak");
			}
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
}
