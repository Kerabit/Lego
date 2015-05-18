package paketti;

import lejos.hardware.device.PSPNXController;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

public class Turn extends Thread{
	
	PSPNXController controller;
	RegulatedMotor sB = new EV3MediumRegulatedMotor(MotorPort.B);
	private boolean left;
	private boolean right;
	
	public Turn(PSPNXController controller){
		this.controller=controller;
		sB.setSpeed(300);
		left=false;
		right=false;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				while ((controller.getLeftX() > 0)||right) {
					sB.forward();
				}
				sB.stop(true);
				while ((controller.getLeftX() < 0)||left) {
					sB.backward();
				}
				sB.stop(true);
			} catch (Exception e) {
			}
		}
	}
	
	public void demoLeft(){
		left=true;
	}
	public void demoLeftOff(){
		left=false;
	}
	public void demoRight(){
		right=true;
	}
	public void demoRightOff(){
		right=false;
	}
}
