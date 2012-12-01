import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Parser;

public class ControladorRun {

	private static final String PORT_REGISTRO = "2028";
	
	private static final String URL_REGISTER_SERVICE = "rmi://localhost:"+ PORT_REGISTRO + "/registro";
	
	private static final String PORT_CONTROLADOR = "2029";
	
	private static final String URL_CONTROLADOR_SERVICE= "rmi://localhost:"+ PORT_CONTROLADOR + "/controlador";
	
	public ControladorRun() {
		 startRegisterService();
		startControladorService();
	}

	private void startRegisterService() {
		try {
			RegistroImpl registro = new RegistroImpl();
			System.out.println("Disponibilizando serviço registro.");
			LocateRegistry.createRegistry(Integer.parseInt(PORT_REGISTRO));
			Naming.rebind(URL_REGISTER_SERVICE, registro);
			System.out.println("Serviço Disponivel");
		} catch (RemoteException e) {
			System.out.println("Erro de conexão");
		} catch (MalformedURLException e) {
			System.out.println("Endereço incorreto ou mal formado.");
		}
	}

	private void startControladorService() {
		ControladorImpl controlImpl;
		try {
			controlImpl = new ControladorImpl();
			System.out.println("Disponibilizando serviço controlador.");
			LocateRegistry.createRegistry(Integer.parseInt(PORT_CONTROLADOR));
 			Naming.rebind(URL_CONTROLADOR_SERVICE,controlImpl);
			System.out.println("Serviço Disponivel");
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
			System.out.println("Erro de conexão");
		} catch (MalformedURLException e) {
			System.out.println("Endereço incorreto ou mal formado.");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ControladorRun();
	}

}
