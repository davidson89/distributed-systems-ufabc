import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class ObjectContainerAndServers {

	private int lastId = 0;

	private Map<String, Integer> mapKeyId = new HashMap<String, Integer>();

	private Map<Integer, Object> mapIdObject = new HashMap<Integer, Object>();
	
	private Queue<String> servidoresDisp = new PriorityQueue<String>();
	
	private List<String> servidoresEmUso = new ArrayList<String>();
	
	private Map<String, String> todosServidores = new HashMap<String, String>();

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
	
	public void removeObjeto(String key) {
		Integer id = this.mapKeyId.get(key);
		this.mapKeyId.remove(key);
		this.mapIdObject.remove(id);
		System.out.println("Objeto de chave " + key + " removido do controlador...");
	}
	
	public final Queue<String> getServidoresDisp() {
		return servidoresDisp;
	}

	public void addServidorDisp(String ip, String portaAcess) {
		System.out.println("Adicionando servidor de ip:" + ip +" na fila de servidores disponiveis...");
		this.servidoresDisp.add(ip);
		this.todosServidores.put(ip, portaAcess);
		System.out.println("Servidor adicionado!");
	}
	
	public void addServidorEmUso(String ip) {
		System.out.println("Adicionando servidor de ip:" + ip +" na lista de servidores em uso...");
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
		this.todosServidores.remove(servidor);
	}
	
	public final List<String> getServidoresEmUso() {
		return servidoresEmUso;
	}
	
	public void liberaServidorParaUso(String servidor) {
		this.servidoresEmUso.remove(servidor);
		this.servidoresDisp.add(servidor);
	}
	
	public Set<String> getTodosServidores() {
		return todosServidores.keySet();
	}
	
	public String getPortaServidor(String servidor) {
		return this.todosServidores.get(servidor);
	}

}
