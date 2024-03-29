package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import exceptions.NenhumServidorDisponivelException;
import exceptions.ObjetoExistenteException;
import exceptions.ObjetoNaoEncontradoException;

public interface InterfaceControlador extends Remote
{
  void armazena(String nome, Object obj) 
     throws RemoteException, NenhumServidorDisponivelException, ObjetoExistenteException;
  String procura(String nome) 
     throws RemoteException, NenhumServidorDisponivelException, ObjetoNaoEncontradoException;
  String[] lista() 
     throws RemoteException;
  void apaga(String nome) 
     throws RemoteException, NenhumServidorDisponivelException, ObjetoNaoEncontradoException;
}