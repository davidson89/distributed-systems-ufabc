import interfaces.InterfaceRegistro;
import interfaces.InterfaceReplicacao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ServidorRun {

	private static final String URL_REGISTER_SERVICE = "rmi://localhost/registro";
	
	private static final String URL_REPLICACAO_SERVICE = "rmi://localhost/replicacao";

	public ServidorRun() {
		this.startRegisterService();
		this.startReplicaoService();
	}

	/**
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		new ServidorRun(); 
	}

	private void startReplicaoService() {
		try {
			ReplicacaoImpl replicacaoImpl = new ReplicacaoImpl();
			System.out.println("Disponibilizando serviço de replicação....");
			Naming.rebind(URL_REPLICACAO_SERVICE, replicacaoImpl);
			System.out.println("Serviço Disponivel");
		} catch (RemoteException e) {
			System.out.println("Erro de Conexão.");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("Endereço incorreto ou mal formado.");
			e.printStackTrace();
		}
	}

	private void startRegisterService() {
		InterfaceRegistro replicacao = null;
		try {
			System.out.println("Iniciando serviço de registro de servidor....");
			replicacao = (InterfaceRegistro) Naming.lookup(URL_REGISTER_SERVICE);
			System.out.println("Serviço iniciado com sucesso!");
		} catch (RemoteException e) {
			System.out.println("Erro de Conexão.");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("Endereço incorreto ou mal formado.");
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println("Não foi possível criar conexão com servidor no endereço: " + URL_REGISTER_SERVICE);
			e.printStackTrace();
		}

		System.out.println("Solicitando registro com ip: " + ToolsHelp.catchIpMachine() + "...");
		try {
			replicacao.registraServidor(ToolsHelp.catchIpMachine());
			System.out.println("Ip: " + ToolsHelp.catchIpMachine() + "registrado com sucesso!");
		} catch (RemoteException e) {
			System.out.println("Erro de Conexão ao registrar ip: " + ToolsHelp.catchIpMachine());
			e.printStackTrace();
		}
	}

}
