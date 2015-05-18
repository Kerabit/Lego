package paketti;

import lejos.hardware.Sound;
import lejos.hardware.device.PSPNXController;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class Main {
	
	static Turn turn;					//Eturenkaita ohjaava säie
	static Motor motor;					//Takarenkaita liikuttava olio
	static PSPNXController controller;	//Ohjaimen signaaleja vastaanottava olio
	static Timer timer;					//Kierrosaikaa ottava säie
	static ColorSensor colsen;			//Värisensorin olio
	static ColorRecognizer rekt;		//Värintunnistuksen olio
	static IRsensor IR;
	static int speed;					//Robootin nopeus
	
	public static void main(String[] args){
		colsen = new ColorSensor();
		rekt = new ColorRecognizer();
		timer = new Timer(colsen,rekt);
		controller = new PSPNXController(SensorPort.S2);
		motor = new Motor();
		turn = new Turn(controller);
		IR = new IRsensor();
		timer.start();
		turn.start();
		speed = 0;
		
		//Robotin käynnistyessä maaliviivan väri kalibroidaan:
		System.out.println("Kalibroidaan maaliviiva");
		Delay.msDelay(1000);
		rekt.calibroi("red", colsen.scan());
		System.out.println("Kalibroitu!");
		Delay.msDelay(5000);
		
		//Robotin kaasutusta ja peruutusta kuunellaan loopissa:
			while(true){
				try{
					while(controller.getButtons()[9]==1){
						motor.forward(); //Kun X painetaa, kaasutetaan
						motor.speed(speed);
						if(speed<400)speed+=10;
					}
					motor.stop();
					speed=0;
					motor.speed(speed);
					while(controller.getButtons()[8]==1){
						motor.backward(); //Kun neliö painetaan peruutetaan
						motor.speed(speed);
						if(speed<400)speed+=10;
					}
					motor.stop();
					speed=0;
					motor.speed(speed);
					if(controller.getButtons()[11]==1){ //Jos KOLMIOTA painettu, kutsutaan demotilaa
						demo();
					}
				} catch(Exception e){}
			}
	}
	
	//DEMOTILA
	public static void demo(){
		int rnd;
		motor.speed(200);
//		timer.resetLapse();
		Sound.buzz();
        Sound.beepSequenceUp();
        while(controller.getButtons()[11]==0){
        	motor.forward();
        	if(IR.closing()[0]<=20){
        		rnd = ((int)(Math.random()*2))+1;
        		motor.stop();
        		if(rnd==1){
        			turn.demoLeft();
        			Delay.msDelay(300);
        			turn.demoLeftOff();
        			
        			motor.backward();
        			Delay.msDelay(600);
        			motor.stop();
        			
        			turn.demoRight();
        			Delay.msDelay(300);
        			turn.demoRightOff();
        		} else{
        			turn.demoRight();
        			Delay.msDelay(300);
        			turn.demoRightOff();
        			
        			motor.backward();
        			Delay.msDelay(600);
        			motor.stop();
        			
        			turn.demoLeft();
        			Delay.msDelay(300);
        			turn.demoLeftOff();
        		}
        	}
        }
        Sound.beepSequence();
        Sound.buzz();
	}
}
