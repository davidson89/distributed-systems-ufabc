package cliente;

import interfaces.*;
import exceptions.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Cliente {

    InterfaceControlador ConexaoControler;
    InterfaceAcesso ConexaoServidor;
    String linkController;

    public Cliente() {
    }

    public boolean Connect(String connectionString) {
        try {
            this.linkController = connectionString;
            ConexaoControler = (InterfaceControlador) Naming.lookup(linkController);
            return true;
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão, controlador inexistente no endereço especificado");
           return false;
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Endereço incorreto ou mal formado.");
           return false;
        } catch (NotBoundException e) {
            JOptionPane.showMessageDialog(null, "não foi possível criar conexão com servidor no endereço: " + linkController);
          return false;
        }
    }

    public boolean armazena(String nome, Object obj){
        boolean sucesso =false; 
        try {
            ConexaoControler = (InterfaceControlador) Naming.lookup(linkController);
            this.ConexaoControler.armazena(nome, obj);
            sucesso = true;
        } catch (ObjetoExistenteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão" + e.getMessage());
        } catch (NenhumServidorDisponivelException e) {
            JOptionPane.showMessageDialog(null, "Nenhum servidor de objetos está disponivel no momento");
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Endereço incorreto ou mal formado.");
        } catch (NotBoundException e) {
            JOptionPane.showMessageDialog(null, "não foi possível criar conexão com servidor no endereço: " + linkController);

        } finally
        {
        return sucesso;
        }

    }

  private  String procura(String nome) {
       String Retorno = null; 
      try {
            ConexaoControler = (InterfaceControlador) Naming.lookup(linkController);
            Retorno = this.ConexaoControler.procura(nome);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão");
        } catch (NenhumServidorDisponivelException e) {
            JOptionPane.showMessageDialog(null, "Nenhum servidor de objetos está disponivel no momento");
        } catch (ObjetoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, "Objeto de nome: " + nome + " não pode ser encontrado.");
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Endereço incorreto ou mal formado.");
        } catch (NotBoundException e) {
            JOptionPane.showMessageDialog(null, "não foi possível criar conexão com servidor no endereço: " + linkController);

        }
          finally
      {
      return Retorno;
      }
       
    }

    String[] lista() {
        String[] retorno = null;
        try {
            ConexaoControler = (InterfaceControlador) Naming.lookup(linkController);
            retorno = ConexaoControler.lista();
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Endereço incorreto ou mal formado.");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão");
        } catch (NotBoundException e) {
            JOptionPane.showMessageDialog(null, "não foi possível criar conexão com servidor no endereço: " + linkController);
        }
         finally
        {
         return retorno;
        }
        
    }

   public void  apaga(String nome) {
        try {
            ConexaoControler = (InterfaceControlador) Naming.lookup(linkController);
            ConexaoControler.apaga(nome);
             JOptionPane.showMessageDialog(null, "Arquivo armazenado com sucesso");
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Endereço incorreto ou mal formado.");
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão");
        } catch (NotBoundException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão");
        } catch (NenhumServidorDisponivelException e) {
            JOptionPane.showMessageDialog(null, "Nenhum servidor de objetos está disponivel no momento");
        } catch (ObjetoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, "Objeto de nome: " + nome + " não pode ser encontrado.");
        }

    }
        public String RecuperarArquivo(String nome)
        {
        String end = this.procura(nome);
        String retorno = "";
        if(end != null){
        retorno = (String) this.recupera(end);
        }
        return retorno;
        }
    private Object recupera(String nome) {
        String campos[] = nome.split("@");
        Object retorno = null;
        try {
            ConexaoServidor = (InterfaceAcesso) Naming.lookup(campos[1]);
            retorno = ConexaoServidor.recupera(Integer.parseInt(campos[0]));
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Endereço incorreto ou mal formado.");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Erro de conexão");
        } catch (NotBoundException e) {
            JOptionPane.showMessageDialog(null, "não foi possível criar conexão com servidor no endereço: " + campos[1]);

        } catch (ObjetoNaoEncontradoException e) {
            JOptionPane.showMessageDialog(null, "Objeto de id: " + campos[0] + " não pode ser encontrado.");
        }
        return retorno;
    }
}
