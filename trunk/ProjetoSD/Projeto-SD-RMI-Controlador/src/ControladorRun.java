import java.rmi.Naming;
import java.rmi.RemoteException;


public class ControladorRun {

	public ControladorRun() {
		try {
			ControladorImpl controlImpl = new ControladorImpl();
			System.out.println("Disponibilizando serviço controlador.");
			Naming.rebind("rmi://localhost/controlador", controlImpl);
			System.out.println("Serviço Disponivel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ControladorRun();
	}

}
