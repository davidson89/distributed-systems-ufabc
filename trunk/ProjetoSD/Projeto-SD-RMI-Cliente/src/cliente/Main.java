package cliente;



public class Main {
	private static final String ENDERECO = "rmi://localhost:2029/controlador";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	         TelaCliente t = new TelaCliente();
	           t.setVisible(true);
	}

	public static void espera(int tempo) {
		try {
			Thread.sleep(tempo);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
