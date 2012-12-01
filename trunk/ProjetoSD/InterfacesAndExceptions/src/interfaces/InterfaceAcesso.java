package interfaces;

import java.rmi.*;

import exceptions.ObjetoNaoEncontradoException;

public interface InterfaceAcesso extends Remote
{
  Object recupera(int id)
     throws RemoteException, ObjetoNaoEncontradoException;
}