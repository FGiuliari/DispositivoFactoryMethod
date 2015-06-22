package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Models.Dispositivo;

public class SendMessaggio implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (Dispositivo.getInstance().getConfig().isEmailEnabled()) {
				try (Socket socket = new Socket("localhost", 4002);
						PrintWriter out = new PrintWriter(
								socket.getOutputStream(), true);
						BufferedReader in = new BufferedReader(
								new InputStreamReader(socket.getInputStream()));) {
					out.write(Dispositivo.getInstance().getId() + "\n");
					out.flush();
					socket.close();
				} catch (IOException e) {
				}

				try {
					Thread.sleep(Dispositivo.getInstance().getConfig()
							.getfSms() * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
