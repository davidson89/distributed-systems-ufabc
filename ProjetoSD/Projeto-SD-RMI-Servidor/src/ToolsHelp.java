import java.net.InetAddress;
import java.net.UnknownHostException;


public class ToolsHelp {
	public static String catchIpMachine() {
		InetAddress end;
		try {
			end = InetAddress.getLocalHost();
			return end.getByName(end.getHostName()+".local").getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println("Não foi possivel pegar o ip local");
			e.printStackTrace();
			return "localhost";
		}
	}
}
