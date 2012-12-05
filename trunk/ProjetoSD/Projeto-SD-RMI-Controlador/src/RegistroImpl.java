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
	
	private AdministratorServers admServer = new AdministratorServers();
	
	public RegistroImpl() throws RemoteException {
		super();
		this.instacia = ObjectContainerAndServers.getInstance();
	}

	@Override
	public Map<Integer,Object> registraServidor(String ip, String porta) throws RemoteException {
		this.instacia.addServidorDisp(ip, porta);
		return this.instacia.getMapIdObject();
	}

	@Override
	public void liberaServidorParaUso(String endereco) {
		this.admServer.liberaServidor(endereco);
	}

}
