import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class ObjectContainerAndServers {

	private int lastId = 0;

	private Map<String, Integer> mapKeyId = new HashMap<String, Integer>();

	private Map<Integer, Object> mapIdObject = new HashMap<Integer, Object>();
	
	private Queue<String> servidoresDisp = new PriorityQueue<String>();
	
	private List<String> servidoresEmUso = new ArrayList<String>();

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
	
	public int guardaObjeto(String key, Object objeto) {
		Integer id = this.getId();
		System.out.println("Cadastrando objeto de chave: " + key + "...");
		this.mapKeyId.put(key, id);
		this.mapIdObject.put(id, objeto);
		System.out.println("Objeto cadastrado!");
		return id.intValue();
	}
	
	public final Queue<String> getServidoresDisp() {
		return servidoresDisp;
	}

	public void addServidorDisp(String ip) {
		System.out.println("Adicionando novo servidor na fila de servidores disponiveis...");
		servidoresDisp.add(ip);
		System.out.println("Servidor adicionado!");
	}
	
	public void addServidorEmUso(String ip) {
		System.out.println("Adicionando novo servidor na lista de servidores em uso...");
		servidoresEmUso.add(ip);
		System.out.println("Servidor adicionado!");
	}
	
	private Integer getId(){
		this.lastId ++;
		System.out.printf("Gerando no id: %d \n", this.lastId);
		return new Integer(this.lastId);
	}
	
	public void excluirServidor(String servidor){
		this.servidoresDisp.remove(servidor);
		this.servidoresEmUso.remove(servidor);
	}
	
	

}
