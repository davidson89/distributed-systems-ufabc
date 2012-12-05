import interfaces.InterfaceRegistro;
import interfaces.InterfaceReplicacao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Map;

public class ServidorRun {

	
	private static final String PORT_REGISTRO = "2028";
	private static final String PORT_REPLICAO = "2131";
	private static final String PORT_ACESSO   = "2132";
	private static final String URL_REGISTER_SERVICE = "rmi://localhost:"+PORT_REGISTRO+"/registro";
	private static final String URL_REPLICACAO_SERVICE = "rmi://localhost:"+PORT_REPLICAO+"/replicacao";
	private static final String URL_ACESSO_SERVICE = "rmi://localhost:"+PORT_ACESSO+"/acesso";


	public ServidorRun() {
		InterfaceRegistro registerImpl = this.startRegisterService();
		this.startReplicaoService();
		this.startAcessoService(registerImpl);
	}

	/**
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		new ServidorRun(); 
	}

	private void startAcessoService(InterfaceRegistro registerImpl) {
		try {
		
			AcessoImpl acessoimpl = new AcessoImpl(registerImpl, PORT_REPLICAO);
			System.out.println("Disponibilizando serviço de acesso....");
			LocateRegistry.createRegistry(Integer.parseInt(PORT_ACESSO));
			Naming.rebind(URL_ACESSO_SERVICE, acessoimpl);
			System.out.println("Serviço Disponivel");
		} catch (RemoteException e) {
			System.out.println("Erro de Conexão.");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("Endereço incorreto ou mal formado.");
			e.printStackTrace();
		}
	}
	
	private void startReplicaoService() {
		try {
			ReplicacaoImpl replicacaoImpl = new ReplicacaoImpl();
			System.out.println("Disponibilizando serviço de replicação....");
			LocateRegistry.createRegistry(Integer.parseInt(PORT_REPLICAO));
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

	private InterfaceRegistro startRegisterService() {
		InterfaceRegistro register = null;
		try {
			System.out.println("Iniciando serviço de registro de servidor....");
			register = (InterfaceRegistro) Naming.lookup(URL_REGISTER_SERVICE);
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

		System.out.println("Solicitando registro com ip: " + ToolsHelp.catchIpMachine() + " na porta:"+ PORT_REPLICAO +"...");
		try {
			Map<Integer, Object> mapaAtual = register.registraServidor(ToolsHelp.catchIpMachine() + ":" + PORT_REPLICAO, PORT_ACESSO);
			ObjectContainer.getInstancia().setMapIdObject(mapaAtual);
			System.out.println("Ip: " + ToolsHelp.catchIpMachine() + " registrado com sucesso!");
		} catch (RemoteException e) {
			System.out.println("Erro de Conexão ao registrar ip: " + ToolsHelp.catchIpMachine());
			e.printStackTrace();
		}
		return register;
	}

}
