package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 * Aluno João Victor de Oliveira Júnior
 **********************************/


import fachada.Sistema;

import modelos.Cliente;
import modelos.Mecanico;
import modelos.OrdemServico;

import modelos.Veiculo;

public class Teste {

	public Teste() {
		Sistema.inicializar();

		try {
			System.out.println("\n\n----Cadastro-----");
			Mecanico m;
			Cliente c;
			Veiculo v;
			OrdemServico os;
//			m=Sistema.cadastrarMecanico("João", 99999999, 12345);
//			m=Sistema.cadastrarMecanico("Thiago", 87999999, 12346);
//			c=Sistema.cadastrarCliente("Ivson", 88888888, 1234567890, "ABC4467", "Fox");
//			c=Sistema.cadastrarCliente("Vicente", 88899888, 1234567899, "ABC4477", "Cadete");
//			v=Sistema.cadastrarCarro("DEF9876", "Onix", 1234567890);
//			v=Sistema.cadastrarCarro("GHI2222", "Mercedes", 1234567890);
//			v=Sistema.cadastrarCarro("DEF9877", "Prisma", 1234567899);
////			
//			os = Sistema.abrirOrdemServico(12345, "ABC4467", 1234567890, 50.0, 0);
//			os = Sistema.abrirOrdemServico(12345, "DEF9876", 1234567890, 50.0, 0);
//			os = Sistema.abrirOrdemServico(12345, "ABC4477", 1234567899, 50.0, 0);
//			os = Sistema.abrirOrdemServico(12346, "DEF9877", 1234567899, 50.0, 0);
////			
			Sistema.encerrarOrdemservico("DEF9876", 1234567890);
//	
//////			
//			System.out.println("\n\n----Listagem-----");
//			System.out.println(Sistema.listarClientes());
//			System.out.println(Sistema.listarCarros());
//			System.out.println(Sistema.listarMecanicos());
			
			System.out.println("\n\n----Listagem OS CLiente-----");
			System.out.println(Sistema.ConsultarOSCliente(1234567890));
			
			System.out.println("\n\n----Listagem OS Mecanico-----");
			System.out.println(Sistema.ConsultarOSMecanico(12346));
			
			System.out.println("\n\n----Listagem OS's-----");
			System.out.println(Sistema.listarOrdensServicos());
//			System.out.println(Sistema2.listarProdutos());
			
			System.out.println(Sistema.listarCarros());

//				
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Sistema.finalizar();
		System.out.println("fim do programa");
	}


	//  ***********************************************
	public static void main (String[] args)
	//  ***********************************************
	{
		Teste ap1 = new Teste();
	}

}
