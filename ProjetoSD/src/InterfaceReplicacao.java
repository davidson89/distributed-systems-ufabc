
import java.rmi.*;

public interface InterfaceReplicacao extends Remote {

    void replica(int id, Object obj)
            throws RemoteException, NenhumServidorDisponivelException;

    void apaga(int id)
            throws RemoteException, NenhumServidorDisponivelException, ObjetoNaoEncontradoException;
}