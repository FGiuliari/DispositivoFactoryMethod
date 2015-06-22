package Socket;

import java.util.Random;
import Models.Dispositivo;

public class RilevamentoPosizione implements Runnable {
	double lastPosition[];
	double position[] = { 10.991621500000065, 45.43838419999999 };

	public RilevamentoPosizione() {
		// TODO Auto-generated constructor stub
	}

	public void generateRandomPosition() {
		Random random = new Random();
		double x0, y0;
		int radius;
		lastPosition = position;
		x0 = lastPosition[0];
		y0 = lastPosition[1];
		radius = 25 * Dispositivo.getInstance().getConfig().getfPos();

		// Convert radius from meters to degrees
		double radiusInDegrees = radius / 111300f;

		double u = random.nextDouble();
		double v = random.nextDouble();
		double w = radiusInDegrees * Math.sqrt(1);//u
		double t = 2 * Math.PI * 1; //*v 
		double x = w * Math.cos(t);
		double y = w * Math.sin(t);

		// Adjust the x-coordinate for the shrinking of the east-west distances
		double new_x = x / Math.cos(y0);

		double foundLongitude = new_x + x0;
		double foundLatitude = y + y0;
		position[0] = foundLongitude;
		position[1] = foundLatitude;
	}

	@Override
	public void run() {
		while (true) {
			generateRandomPosition();
			long time = System.currentTimeMillis();
			new Thread(new sendPositionToServer(position, time)).start();

			try {
				Thread.sleep(Dispositivo.getInstance().getConfig().getfPos() * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
