package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import exceptions.NenhumServidorDisponivelException;
import exceptions.ObjetoNaoEncontradoException;

public interface InterfaceReplicacao extends Remote
{
  void replica(int id, Object obj) 
     throws RemoteException, NenhumServidorDisponivelException;
  void apaga(int id) 
     throws RemoteException, NenhumServidorDisponivelException, ObjetoNaoEncontradoException;
  
}