
import java.rmi.*;

public interface InterfaceAcesso extends Remote {

    Object recupera(String link)
            throws RemoteException, ObjetoNaoEncontradoException;
}