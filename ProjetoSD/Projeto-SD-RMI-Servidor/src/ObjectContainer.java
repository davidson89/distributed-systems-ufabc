import java.util.HashMap;
import java.util.Map;


public class ObjectContainer {

	private static ObjectContainer instancia;
	
	private Map<Integer, Object> mapIdObject = new HashMap<Integer, Object>();
	
	public static ObjectContainer getInstancia(){
		if(instancia == null){
			instancia = new ObjectContainer();
		}
		return instancia;
	}
	
	public void apaga(Integer id) {
		System.out.println("Removendo objeto de id:" + String.valueOf(id) + "....");
		mapIdObject.remove(id);
		System.out.println("Objeto removido com sucesso!");
	}
	
	public void armazenaObjeto(Integer id, Object objeto) {
		System.out.println("Armazenando objeto de id:" + String.valueOf(id) + "....");
		this.mapIdObject.put(id, objeto);
		System.out.println("Objeto armazenado com sucesso!");
	}
	public Object retornaObjeto(Integer id) {
		System.out.println("Retornando objeto de id:" + String.valueOf(id) + "....");
		return this.mapIdObject.get(id);
	
	}
	
	
	

}