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
	public Cliente(String linkController)
	{
		try{
		ConexaoControler = (InterfaceControlador)Naming.lookup(linkController);
		}catch(Exception ex)
		{
			
		}
		
		
	}
	void armazena(String nome, Object obj)
	{
		
	}
	String procura(String nome)
	{
		
	
			try {
				return  this.ConexaoControler.procura(nome);
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NenhumServidorDisponivelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ObjetoNaoEncontradoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   return "";
	}	
	String[] lista()
	{
		return new String[1];
	} 
	void apaga(String nome){} 
	Object recupera(String nome)
	{
		String campos[] = nome.split("@");
		Object  retorno= null;
		try {
			ConexaoServidor = (InterfaceAcesso)Naming.lookup(campos[1]);
			  retorno = ConexaoServidor.recupera(campos[1]);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ObjetoNaoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}

}
