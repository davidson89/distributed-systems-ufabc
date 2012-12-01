package cliente;

public class Main {
private static final String ENDERECO ="rmi://localhost:2029/controlador";
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cliente cliente = new Cliente(ENDERECO);
		
			System.out.println("Solicitando lista de objetos para o controlador...");
		    String[] lista = (String[]) cliente.lista();
		    System.out.println("Lista solicitada com sucesso!");
		    
		    System.out.println("Objetos armazenados no servidor: ");
		    
		    if(lista.length == 0) {
		    	System.out.println("Não existe objetos armazenados!");
		    }
		   
		    for(int i=0;i<lista.length;i++)
		    {
		    	System.out.println(lista[i]);
		    }
		    
		    cliente.armazena("teste", new String("FF"));
		    espera(3000);
		    String t = cliente.procura("teste");
		    System.out.println("end "+t);
		    
		    
		
	}

public static void  espera(int tempo)
{
try {
Thread.sleep(tempo);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}	
}
}
