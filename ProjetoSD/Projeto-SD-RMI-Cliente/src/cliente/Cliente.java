package cliente;
import interfaces.*;
import exceptions.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class Cliente {
	InterfaceControlador ConexaoControler;
	InterfaceAcesso ConexaoServidor;
	String linkController;
	public Cliente(String linkController)
	{
		   this.linkController = linkController;
	}
	void armazena(String nome, Object obj)
	{
		try {
			ConexaoControler = (InterfaceControlador)Naming.lookup(linkController);	 
			  this.ConexaoControler.armazena(nome, obj);
		} catch (RemoteException e) {
			System.out.println("Erro de conexão" + e.getMessage());
		} catch (NenhumServidorDisponivelException e) {
			System.out.println("Nenhum servidor de objetos está disponivel no momento");
		} catch (MalformedURLException e) {
			System.out.println("Endereço incorreto ou mal formado.");
		} catch (NotBoundException e) {
			System.out.println("não foi possível criar conexão com servidor no endereço: " + linkController);
			
		}

	}
	String procura(String nome)
	{
			try {
				ConexaoControler = (InterfaceControlador)Naming.lookup(linkController);	
				return  this.ConexaoControler.procura(nome);
			} catch (RemoteException e) {
				System.out.println("Erro de conexão");
			} catch (NenhumServidorDisponivelException e) {
				System.out.println("Nenhum servidor de objetos está disponivel no momento");
			} catch (ObjetoNaoEncontradoException e) {
				System.out.println("Objeto de nome: " + nome + " não pode ser encontrado.");	
			} catch (MalformedURLException e) {
				System.out.println("Endereço incorreto ou mal formado.");
			} catch (NotBoundException e) {
				System.out.println("não foi possível criar conexão com servidor no endereço: " + linkController);
				
			}
   return "";
}	
	String[] lista()
	{
		String[] retorno = null;
		try {
			ConexaoControler = (InterfaceControlador)Naming.lookup(linkController);
			retorno =  ConexaoControler.lista();	
		} catch (MalformedURLException e) {
			System.out.println("Endereço incorreto ou mal formado.");
		} catch (RemoteException e) {
			System.out.println("Erro de conexão");
		} catch (NotBoundException e) {
			System.out.println("não foi possível criar conexão com servidor no endereço: " + linkController);
		}	

		return retorno;
	} 
	void apaga(String nome)
	{
		try {
			ConexaoControler = (InterfaceControlador)Naming.lookup(linkController);
			ConexaoControler.apaga(nome);
		} catch (MalformedURLException e) {
			System.out.println("Endereço incorreto ou mal formado.");
		} catch (RemoteException e) {
			System.out.println("Erro de conexão");
				} catch (NotBoundException e) {
			System.out.println("Erro de conexão");
		} catch (NenhumServidorDisponivelException e) {
			System.out.println("Nenhum servidor de objetos está disponivel no momento");	
	    } catch (ObjetoNaoEncontradoException e) {
			System.out.println("Objeto de nome: " + nome + " não pode ser encontrado.");
		}
		
	} 
	Object recupera(String nome)
	{
		String campos[] = nome.split("@");
		Object  retorno= null;
		try {
			ConexaoServidor = (InterfaceAcesso)Naming.lookup(campos[1]);
		    retorno = ConexaoServidor.recupera(Integer.parseInt(campos[0]));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("Endereço incorreto ou mal formado.");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro de conexão");
		} catch (NotBoundException e) {
			System.out.println("não foi possível criar conexão com servidor no endereço: " + campos[1]);
			
		} catch (ObjetoNaoEncontradoException e) {
			System.out.println("Objeto de id: " + campos[0] + " não pode ser encontrado.");
		}
		return retorno;
	}

}
