package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Models.Dispositivo;


public class sendPositionToServer implements Runnable {

	double coord[];
	long time;

	public sendPositionToServer(double[] coord, long time) {
		this.coord = coord;
		this.time = time;
	}

	@Override
	public void run() {
		try (Socket socket = new Socket("localhost", 4001);
				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						true);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));) {
			out.write(Dispositivo.getInstance().getId() + "\n");
			out.write(Double.toString(coord[0])+"\n");
			out.write(Double.toString(coord[1])+"\n");
			out.write(Long.toString(time));
			out.flush();
			socket.close();
		} catch (IOException e) {
			System.out.println(Dispositivo.getInstance().getId()+":Errore : non sono riuscito a inviare la posizione");
		}
	}

}
