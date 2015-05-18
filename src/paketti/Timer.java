package paketti;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.Sound;

public class Timer extends Thread{

	private DataOutputStream out;
	ColorSensor sens;
	ColorRecognizer rekt;
	int lapse;
	
	public Timer(ColorSensor s, ColorRecognizer r){
		this.sens=s;
		this.rekt=r;
		lapse = 1;
	}
	
	@Override
	public void run(){
		try{
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(6211);
			System.out.println("Odotetaan yhteyttä...");
			Socket s = server.accept();
			System.out.println("Yhteys muodostettu!");
			out = new DataOutputStream(s.getOutputStream());
		} catch(IOException e){
			System.out.println("Cannot connect");
		}
		while(true){
			if(rekt.tunnista(sens.scan()).equals("red")){
				Sound.beep();
				try{
					out.writeInt(lapse);
				} catch(IOException e){
					System.out.println("Cannot read info");
				}
				lapse++;
			}	
		}
	}
	
	public void resetLapse(){
		lapse=1;
	}
}
