package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Models.Dispositivo;
import Socket.RilevamentoPosizione;
import Socket.SendMessaggio;
import Socket.SocketConfigurazione;

public class DispositivoTest extends Dispositivo {

	protected DispositivoTest(Integer id) throws IOException {
		super(id);
	}

	public static void Instantiate(int i) {
		try {
			dis = new DispositivoTest(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update() {
		System.out.println("aggiorno la configurazione");
		try (Socket socket = new Socket("localhost", 4000);
				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						true);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));) {
			out.write(id + "\n");
			out.flush();
			while (!in.ready()) {
			}

			config.setfPos(Integer.parseInt(in.readLine()));
			config.setfSms(Integer.parseInt(in.readLine()));
			config.setspeedAlarm(Integer.parseInt(in.readLine()));
			config.setEmailEnabled(Boolean.parseBoolean(in.readLine()));
			config.setSmsEnabled(Boolean.parseBoolean(in.readLine()));
			System.out.println("Configurazione ricevuta: \n"
					+ config.toString());
			socket.close();

		} catch (IOException e) {
		}
	}

	protected void startSocketConfigurazione() {
		new Thread(new SocketConfigurazioneTest(Dispositivo.getInstance().getSs()))
				.start();
	}

	protected void startRilevamentoPosizione() {
		new Thread(new RilevamentoPosizioneTest()).start();

	}

	protected void startSocketSendNotifiche() {
		new Thread(new SendMessaggioTest()).start();
	}

}
