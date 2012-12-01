import java.awt.geom.Area;
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
	
	public String[] getObjetosCadastrados(){
		System.out.println("Verificando se existe objetos cadastrados...");
		int size = this.objectServers.getMapKeyId().size();
		if(size == 0){
			System.out.println("Não exite objetos cadastrados.");
			return new String[0];
		}
		System.out.println("Retornando lista de objetos cadastrados.");
		return (String[]) this.objectServers.getMapKeyId().keySet().toArray(new String[size]);
	}
}
