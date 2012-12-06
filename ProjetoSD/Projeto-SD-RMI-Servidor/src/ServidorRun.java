
import interfaces.InterfaceRegistro;
import interfaces.InterfaceReplicacao;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ServidorRun {

    private String PORT_REGISTRO = "2028";
    private String PORT_REPLICAO = "2131";
    private String PORT_ACESSO = "2132";
    private String URL_REGISTER_SERVICE = "rmi://localhost:" + PORT_REGISTRO + "/registro";
    private String URL_REPLICACAO_SERVICE = "rmi://localhost:" + PORT_REPLICAO + "/replicacao";
    private String URL_ACESSO_SERVICE = "rmi://localhost:" + PORT_ACESSO + "/acesso";

    public ServidorRun(String portaAcesso, String portaReplicacao) {

        this.PORT_ACESSO = portaAcesso;
        this.PORT_REPLICAO = portaReplicacao;
        URL_REPLICACAO_SERVICE = "rmi://localhost:" + PORT_REPLICAO + "/replicacao";
        URL_ACESSO_SERVICE = "rmi://localhost:" + PORT_ACESSO + "/acesso";
        InterfaceRegistro registerImpl = this.startRegisterService();
        this.startReplicaoService();
        this.startAcessoService(registerImpl);
    }

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
            JOptionPane.showMessageDialog(null, "Disponibilizando serviço de acesso....");
            LocateRegistry.createRegistry(Integer.parseInt(PORT_ACESSO));
            Naming.rebind(URL_ACESSO_SERVICE, acessoimpl);
            JOptionPane.showMessageDialog(null, "Serviço Disponivel");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro de Conexãoeeeee.");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Endereço incorreto ou mal formado.");
            e.printStackTrace();
        }
    }

    private void startReplicaoService() {
        try {
            ReplicacaoImpl replicacaoImpl = new ReplicacaoImpl();
            JOptionPane.showMessageDialog(null, "Disponibilizando serviço de replicação....");
              LocateRegistry.createRegistry(Integer.parseInt(PORT_REPLICAO));
              Naming.rebind(URL_REPLICACAO_SERVICE, replicacaoImpl);
            JOptionPane.showMessageDialog(null, "Serviço Disponivel");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro de Conexãowwwww.");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Endereço incorreto ou mal formado.");
            e.printStackTrace();
        }
    }

    private InterfaceRegistro startRegisterService() {
        InterfaceRegistro register = null;
        try {
            JOptionPane.showMessageDialog(null, "Iniciando serviço de registro de servidor....");
            register = (InterfaceRegistro) Naming.lookup(URL_REGISTER_SERVICE);
            JOptionPane.showMessageDialog(null, "Serviço iniciado com sucesso!");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro de Conexãoeeeeee.");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Endereço incorreto ou mal formado.");
            e.printStackTrace();
        } catch (NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível criar conexão com servidor no endereço: " + URL_REGISTER_SERVICE);
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Solicitando registro com ip: " + ToolsHelp.catchIpMachine() + " na porta:" + PORT_REPLICAO + "...");
        try {
            Map<Integer, Object> mapaAtual = register.registraServidor(ToolsHelp.catchIpMachine() + ":" + PORT_REPLICAO, PORT_ACESSO);
            ObjectContainer.getInstancia().setMapIdObject(mapaAtual);
            JOptionPane.showMessageDialog(null, "Ip: " + ToolsHelp.catchIpMachine() + " registrado com sucesso!");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro de Conexão ao registrar ip: " + ToolsHelp.catchIpMachine());
            e.printStackTrace();
        }
        return register;
    }

    void unbind() {
        try {

            Naming.unbind(URL_REPLICACAO_SERVICE);
            Naming.unbind(URL_ACESSO_SERVICE);


        } catch (RemoteException ex) {
            JOptionPane.showMessageDialog(null, "Erro de conexão");
        } catch (NotBoundException ex) {
            JOptionPane.showMessageDialog(null, "Não há serviço no endereço especificado");
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, "Endereço errado ou mal formado");
        }
        try {
            this.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(ServidorRun.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
