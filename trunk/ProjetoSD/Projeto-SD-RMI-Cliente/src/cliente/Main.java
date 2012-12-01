package cliente;

public class Main {
private static final String ENDERECO ="rmi://localhost:2029/controlador";
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cliente cliente = new Cliente(ENDERECO);
		
		//loop principal
		

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
