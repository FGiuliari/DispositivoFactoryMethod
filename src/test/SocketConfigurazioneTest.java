package test;

import java.io.IOException;
import java.net.ServerSocket;


public class SocketConfigurazioneTest implements Runnable{
	ServerSocket serversocket;
	
	public SocketConfigurazioneTest(ServerSocket ss2) {
		// TODO Auto-generated constructor stub
		this.serversocket=ss2;
	}

	@Override
	public void run() {
		System.out.println("avviato socket su cui ricevere");
		while (true) {
			try {
				new Thread(new socketThreadTest(serversocket.accept())).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}
}
