package paketti;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class ColorSensor
{ 
	private SampleProvider colorProvider;
	private EV3ColorSensor sensor;
	private Port port;

	public ColorSensor(){
		port = LocalEV3.get().getPort("S4");
		SensorModes sensor = new EV3ColorSensor(port);
		this.colorProvider = ((EV3ColorSensor)sensor).getRGBMode();
		this.sensor = ((EV3ColorSensor)sensor);
	}
	
	public int[] scan() {
		float[] sample = new float[colorProvider.sampleSize()];
		colorProvider.fetchSample(sample, 0);
		int[] colors = muutaArvot(sample);
		return colors;
	}
	
	public void toggleFloodLight(boolean state) {
		sensor.setFloodlight(state);
	}
	
	private int[] muutaArvot(float[] sample) {
		int red = Math.round(sample[0] * 255);
		int green = Math.round(sample[1] * 255);
		int blue = Math.round(sample[2] * 255);
		return new int[]{red, green, blue};
	}
	
	public EV3ColorSensor getSensor(){
		return sensor;
	}
}