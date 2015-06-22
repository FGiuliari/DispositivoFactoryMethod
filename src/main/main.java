package main;

import test.DispositivoTest;
import Models.Dispositivo;
import Socket.RilevamentoPosizione;
import Socket.SendMessaggio;
import Socket.SocketConfigurazione;

public class main {

	public static void main(String[] args) {
		if (args.length == 1) {
			Dispositivo.Instantiate(Integer.parseInt(args[0]));
			System.out.println("avvio dispositivo " + args[0]);
			Dispositivo.getInstance().run();
		} else if (args.length == 2) {
			if (args[1].equals("test")) {
				DispositivoTest.Instantiate(Integer.parseInt(args[0]));
				System.out.println("avvio dispositivo di test " + args[0]);
				DispositivoTest.getInstance().run();

			} else {
				Dispositivo.Instantiate(Integer.parseInt(args[0]));
				System.out.println("avvio dispositivo " + args[0]);
				Dispositivo.getInstance().run();				
			}

		} else {
			System.out.println("errore");
		}
	}
}
