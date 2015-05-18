package paketti;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;

public class IRsensor {
		
		private SampleProvider distance;
		private EV3IRSensor sensor;
		
		public IRsensor(){
			Port port = LocalEV3.get().getPort("S3");
			SensorModes sensor = new EV3IRSensor(port);
			this.distance = ((EV3IRSensor)sensor).getDistanceMode();
			this.sensor = ((EV3IRSensor)sensor);
		}
		
		public float[] closing(){
			float[] sample = new float[distance.sampleSize()];
			distance.fetchSample(sample, 0);
			return sample;		
		}
}
