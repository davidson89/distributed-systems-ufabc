import interfaces.InterfaceReplicacao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import exceptions.NenhumServidorDisponivelException;
import exceptions.ObjetoNaoEncontradoException;


public class ReplicacaoImpl extends UnicastRemoteObject implements InterfaceReplicacao{

	private ObjectContainer instacia;
	
	protected ReplicacaoImpl() throws RemoteException {
		super();
		this.instacia = ObjectContainer.getInstancia();
	}

	@Override
	public void apaga(int id) throws RemoteException,
			NenhumServidorDisponivelException, ObjetoNaoEncontradoException {
		this.instacia.apaga(new Integer(id));
	}

	@Override
	public void replica(int id, Object objeto) throws RemoteException,
			NenhumServidorDisponivelException {
		this.instacia.armazenaObjeto(new Integer(id), objeto);
	}


}
