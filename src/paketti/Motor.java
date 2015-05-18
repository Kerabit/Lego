package paketti;

import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;

public class Motor {
	
	RegulatedMotor mA = new EV3LargeRegulatedMotor(BrickFinder.getDefault().getPort("A"));
	RegulatedMotor mD = new EV3LargeRegulatedMotor(BrickFinder.getDefault().getPort("D"));
	
	public Motor(){
		mA.setSpeed(0);
		mD.setSpeed(0);
	}
	
	public void forward(){
		mA.synchronizeWith(new RegulatedMotor[] {mD});
		mA.startSynchronization();
		mA.forward();
		mD.forward();
		mA.endSynchronization();
	}
	
	public void backward(){
		mA.synchronizeWith(new RegulatedMotor[] {mD});
		mA.startSynchronization();
		mA.backward();
		mD.backward();
		mA.endSynchronization();
	}
	
	public void stop(){
		mA.synchronizeWith(new RegulatedMotor[] {mD});
		mA.startSynchronization();
		mA.stop(true);
		mD.stop(true);
		mA.endSynchronization();
	}
	
	public void speed(int i){
		mA.setSpeed(i);
		mD.setSpeed(i);
	}
}
