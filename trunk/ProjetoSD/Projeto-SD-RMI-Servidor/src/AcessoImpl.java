import interfaces.InterfaceAcesso;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import exceptions.ObjetoNaoEncontradoException;

public class AcessoImpl extends UnicastRemoteObject implements InterfaceAcesso {

	private ObjectContainer instacia;

	protected AcessoImpl() throws RemoteException {
		super();
		this.instacia = ObjectContainer.getInstancia();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object recupera(int id) throws RemoteException,
			ObjetoNaoEncontradoException {

		Object ob = this.instacia.retornaObjeto(id);
		if (ob == null)
			throw new ObjetoNaoEncontradoException("Objeto de id " + id
					+ " não encontrado");
		return ob;
	}

}
