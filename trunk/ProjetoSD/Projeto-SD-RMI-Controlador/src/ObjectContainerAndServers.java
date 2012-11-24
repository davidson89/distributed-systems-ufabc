import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectContainerAndServers {

	private int lastId = 0;

	private Map<String, Integer> mapKeyId = new HashMap<String, Integer>();

	private Map<Integer, Object> mapIdObject = new HashMap<Integer, Object>();
	
	private List<String> servidoresDisp = new ArrayList<String>();

	private static ObjectContainerAndServers instanceObjectCont;

	private ObjectContainerAndServers() {
		super();
	}

	public static ObjectContainerAndServers getInstance() {
		if (instanceObjectCont == null) {
			instanceObjectCont = new ObjectContainerAndServers();
		}
		return instanceObjectCont;
	}

	public Map<String, Integer> getMapKeyId() {
		return mapKeyId;
	}
	
	public Map<Integer, Object> getMapIdObject(){
		return mapIdObject;
	}
	
	public void guardaObjeto(String key, Object objeto) {
		Integer id = this.getId();
		System.out.println("Cadastrando objeto de chave: " + key + "...");
		this.mapKeyId.put(key, id);
		this.mapIdObject.put(id, objeto);
		System.out.println("Objeto cadastrado!");
	}
	
	public final List<String> getServidoresDisp() {
		return servidoresDisp;
	}

	public void addServidorDisp(String ip) {
		System.out.println("Adicionando novo servidor na lista de servidores...");
		servidoresDisp.add(ip);
		System.out.println("Servidor adicionado!");
	}
	
	public Integer getId(){
		this.lastId ++;
		System.out.printf("Gerando no id: %d \n", this.lastId);
		return new Integer(this.lastId);
	}
	
	

}
