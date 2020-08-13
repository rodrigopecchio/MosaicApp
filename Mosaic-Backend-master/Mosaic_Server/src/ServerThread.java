import java.net.Socket;

public class ServerThread implements Runnable {

	private Main main;
	private Socket socket;
	
	public ServerThread(Socket s, Main main) {
		this.main = main;
		this.socket = s;
	}
	
	public void run() {
		
	}
	
}
