import interfaces.InterfaceRegistro;
import interfaces.InterfaceReplicacao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.NenhumServidorDisponivelException;
import exceptions.ObjetoNaoEncontradoException;


public class RegistroImpl extends UnicastRemoteObject implements InterfaceRegistro {
	
	private ObjectContainerAndServers instacia;
	
	protected RegistroImpl() throws RemoteException {
		super();
		this.instacia = ObjectContainerAndServers.getInstance();
	}

	@Override
	public Map<Integer,Object> registraServidor(String ip) throws RemoteException {
		this.instacia.addServidorDisp(ip);
		return this.instacia.getMapIdObject();
	}
}
