import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ControladorRun {

	private static final String URL_REGISTER_SERVICE = "rmi://localhost/registro";

	public ControladorRun() {
		startRegisterService();
		startControladorService();
	}

	private void startRegisterService() {
		try {
			RegistroImpl registro = new RegistroImpl();
			System.out.println("Disponibilizando serviço registro.");
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
			Naming.rebind("rmi://localhost/controlador", controlImpl);
			System.out.println("Serviço Disponivel");
		} catch (RemoteException e) {
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
