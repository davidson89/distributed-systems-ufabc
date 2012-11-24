import exceptions.ObjetoNaoEncontradoException;


public class AdministratorObjects {

	private ObjectContainerAndServers objectServers;
	
	public AdministratorObjects () {
		this.objectServers = ObjectContainerAndServers.getInstance();
	}
	
	public int verificaExistenciaObjeto(String key) throws ObjetoNaoEncontradoException {
		System.out.println("Verificando existencia do objeto solicitado...");
		if(!this.objectServers.getMapKeyId().containsKey(key)){
			System.out.println("Objeto não encontrado");
			throw new ObjetoNaoEncontradoException(key);
		}
		System.out.println("Objeto encontrado.");
		
		return this.objectServers.getMapKeyId().get(key).intValue();
	}
}
