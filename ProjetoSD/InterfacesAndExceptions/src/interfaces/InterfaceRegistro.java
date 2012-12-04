package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface InterfaceRegistro extends Remote {

	Map<Integer, Object> registraServidor(String ip, String portAcesso) throws RemoteException;

}
