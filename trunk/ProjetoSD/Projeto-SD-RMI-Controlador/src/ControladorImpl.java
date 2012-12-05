import interfaces.InterfaceControlador;
import interfaces.InterfaceRegistro;
import interfaces.InterfaceReplicacao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import exceptions.NenhumServidorDisponivelException;
import exceptions.ObjetoExistenteException;
import exceptions.ObjetoNaoEncontradoException;

public class ControladorImpl extends UnicastRemoteObject implements InterfaceControlador {

	private ObjectContainerAndServers instance;

	private InterfaceReplicacao interfaceReplicao;

	private AdministratorServers admServers;

	private AdministratorObjects admObjects;

	protected ControladorImpl() throws RemoteException {
		super();
		this.instance = ObjectContainerAndServers.getInstance();
		this.admServers = new AdministratorServers();
		this.admObjects = new AdministratorObjects();
	}

	@Override
	public void apaga(String key) throws RemoteException,
			NenhumServidorDisponivelException, ObjetoNaoEncontradoException {
		if (this.instance.getMapKeyId().containsKey(key)) {
			int id = this.instance.getMapKeyId().get(key).intValue();
			// remover dos servidores (todos)
			for (String servidor : this.instance.getTodosServidores()) {
				try {
					this.interfaceReplicao = (InterfaceReplicacao) Naming
							.lookup("rmi://" + servidor + "/replicacao");
				} catch (MalformedURLException e) {
					System.out.println("Servidor não encontrado: " + servidor);
					this.admServers.excluirServidor(servidor);
				} catch (NotBoundException e) {
					e.printStackTrace();
				}
				this.interfaceReplicao.apaga(id);
			}
			// remover dos mapas (os 2)
			this.instance.removeObjeto(key);
		} else {
			throw new ObjetoNaoEncontradoException(key);
		}
	}

	@Override
	public void armazena(String key, Object objeto) throws RemoteException,
			NenhumServidorDisponivelException, ObjetoExistenteException {
		if (this.instance.getMapKeyId().containsKey(key)) {
			throw new ObjetoExistenteException(key);
		}
		if (this.instance.getTodosServidores().isEmpty()) {
			throw new NenhumServidorDisponivelException();
		}

		int id = this.instance.guardaObjeto(key, objeto);
		for (String servidor : this.instance.getTodosServidores()) {
			try {
				this.interfaceReplicao = (InterfaceReplicacao) Naming.lookup("rmi://" + servidor + "/replicacao");
			} catch (MalformedURLException e) {
				System.out.println("Servidor não encontrado: " + servidor);
				this.admServers.excluirServidor(servidor);
			} catch (NotBoundException e) {
				e.printStackTrace();
			}

			this.interfaceReplicao.replica(id, objeto);
		}
	}

	@Override
	public String[] lista() throws RemoteException {
		return this.admObjects.getObjetosCadastrados();
	}

	@Override
	public String procura(String key) throws RemoteException,
			ObjetoNaoEncontradoException, NenhumServidorDisponivelException {
		int identificador = this.admObjects.verificaExistenciaObjeto(key);

		String link = String.valueOf(identificador);
		link = link.concat("@rmi://");
		String serverDisp = admServers.getServerDisp();
		link = link.concat(admServers.getURLAcesso(serverDisp));
		link = link.concat("/acesso");

		return link;
	}

}
