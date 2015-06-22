package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Models.Dispositivo;



public class socketThreadTest implements Runnable {
Socket socket;
	public socketThreadTest(Socket accept) {
		// TODO Auto-generated constructor stub
		this.socket=accept;
	}

	@Override
	public void run() {
		  try (
		            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);		            BufferedReader in = new BufferedReader(
		                new InputStreamReader(
		                    socket.getInputStream()));
		        ) {
		          String inputLine, outputLine;
		          while( !in.ready()){}
		          String action=in.readLine();
		          System.out.println("ricevuta richiesta di aggiornamento configurazione dal server");
		          if(action.equals("aggiorna"))
		        	  Dispositivo.getInstance().update();
		          socket.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		  ;
	}

}
