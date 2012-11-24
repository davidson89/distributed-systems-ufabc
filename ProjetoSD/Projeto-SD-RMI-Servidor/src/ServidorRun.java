import interfaces.InterfaceRegistro;
import interfaces.InterfaceReplicacao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class ServidorRun {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		InterfaceRegistro replicacao = null;
		try {
			replicacao = (InterfaceRegistro) Naming.lookup("rmi://localhost/registro");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		replicacao.registraServidor(ToolsHelp.catchIpMachine());
		
	}

	
}
