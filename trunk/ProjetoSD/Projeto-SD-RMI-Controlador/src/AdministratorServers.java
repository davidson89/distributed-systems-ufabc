import java.util.List;

import exceptions.NenhumServidorDisponivelException;

public class AdministratorServers {

	private ObjectContainerAndServers instancia;
	
	public AdministratorServers() {
		this.instancia = ObjectContainerAndServers.getInstance();
	}

	public String getServerDisp() throws NenhumServidorDisponivelException {
		System.out.println("Solicitando primeiro servidor disponivel..");
		String servidorDisp = this.instancia.getServidoresDisp().poll();
		if(servidorDisp == null) {
			System.out.println("Não existe servidor disponivel no momento!");
			throw new NenhumServidorDisponivelException();
		}
		System.out.println("Servidor disponivel: " + servidorDisp);
		System.out.println("Transferindo servidor da lista de servidores disponiveis para a lista de servidores em uso...");
		this.instancia.addServidorEmUso(servidorDisp);
		System.out.println("Servidor transferido!");
		return servidorDisp;
	}
	
	public void excluirServidor(String servidor) {
		System.out.println("Excluindo servidor da lista de servidores disponiveis...");
		this.instancia.excluirServidor(servidor);
		System.out.println("Servidor excluido");
	}
	
	public String getURLAcesso(String servidor) {
		String portaAcesso = this.instancia.getPortaServidor(servidor);
		String[] ip = servidor.split(":");
		String url = ip[0] + ":" + portaAcesso;
		return url;
	}
	
	public void liberaServidor(String endereco) {
		System.out.println("Liberando servidor:" + endereco + "para uso.....");
		this.instancia.liberaServidorParaUso(endereco);
		System.out.println("Servidor Liberado!");
	}
}
