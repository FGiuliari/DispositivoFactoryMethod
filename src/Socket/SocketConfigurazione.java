package Socket;

import java.io.IOException;
import java.net.ServerSocket;


public class SocketConfigurazione implements Runnable{
	ServerSocket serversocket;
	
	public SocketConfigurazione(ServerSocket ss2) {
		// TODO Auto-generated constructor stub
		this.serversocket=ss2;
	}

	@Override
	public void run() {
		while (true) {
			try {
				new Thread(new socketThread(serversocket.accept())).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}
}
