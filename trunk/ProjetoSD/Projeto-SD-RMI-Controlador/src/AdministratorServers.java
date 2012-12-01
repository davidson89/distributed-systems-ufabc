import java.util.List;

import exceptions.NenhumServidorDisponivelException;

public class AdministratorServers {

	private ObjectContainerAndServers objectServers;
	
	public AdministratorServers() {
		this.objectServers = ObjectContainerAndServers.getInstance();
	}

	public String getServerDisp() throws NenhumServidorDisponivelException {
		System.out.println("Solicitando primeiro servidor disponivel..");
		String servidorDisp = this.objectServers.getServidoresDisp().poll();
		if(servidorDisp == null) {
			System.out.println("Não existe servidor disponivel no momento!");
			throw new NenhumServidorDisponivelException();
		}
		System.out.println("Servidor disponivel: " + servidorDisp);
		System.out.println("Transferindo servidor da lista de servidores disponiveis para a lista de servidores em uso...");
		this.objectServers.addServidorEmUso(servidorDisp);
		System.out.println("Servidor transferido!");
		return servidorDisp;
	}
	
	public void excluirServidor(String servidor) {
		System.out.println("Excluindo servidor da lista de servidores disponiveis...");
		this.objectServers.excluirServidor(servidor);
		System.out.println("Servidor excluido");
	}
}
