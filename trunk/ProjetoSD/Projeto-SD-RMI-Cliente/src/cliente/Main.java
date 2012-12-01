package cliente;

public class Main {
private static final String ENDERECO ="rmi://localhost/controlador";
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cliente cliente = new Cliente(ENDERECO);
		
		//loop principal
		
		while (true){
		
			cliente.armazena("teste", new Arquivo("teste"));
			
			  espera(3000);

		    String[] lista =cliente.lista();
		   
		    for(int i=0;i<lista.length;i++)
		    {
		    	System.out.println(lista[i]);
		    }
		    
		    espera(3000);
		    
		  
		    
		    
		}
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
