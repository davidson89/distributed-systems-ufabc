import interfaces.InterfaceAcesso;
import interfaces.InterfaceRegistro;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import exceptions.ObjetoNaoEncontradoException;

public class AcessoImpl extends UnicastRemoteObject implements InterfaceAcesso {

	private ObjectContainer instacia;
	
	private InterfaceRegistro registroImpl;
	
	private String portaRegistro;

	protected AcessoImpl(InterfaceRegistro registroImpl, String portaRegistro) throws RemoteException {
		super();
		this.instacia = ObjectContainer.getInstancia();
		this.registroImpl = registroImpl;
		this.portaRegistro = portaRegistro;
	}

	@Override
	public Object recupera(int id) throws RemoteException,
			ObjetoNaoEncontradoException {

		Object ob = this.instacia.retornaObjeto(id);
		if (ob == null) {
			this.registroImpl.liberaServidorParaUso(ToolsHelp.catchIpMachine() + ":" + this.portaRegistro);
			throw new ObjetoNaoEncontradoException("Objeto de id " + id
					+ " não encontrado");
		}
		this.registroImpl.liberaServidorParaUso(ToolsHelp.catchIpMachine() + ":" + this.portaRegistro);
		return ob;
	}

}
