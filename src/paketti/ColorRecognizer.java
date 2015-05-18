package paketti;
import java.util.HashMap;
import java.util.Map.Entry;

public class ColorRecognizer {
	private HashMap<String, int[]> calib = new HashMap<String, int[]>();

	public String tunnista(int[] color) {
		String vari = "";
		double lowestValue = 5;		
		for(Entry<String, int[]> derp : calib.entrySet()) {
			double distance = etaisyys(derp.getValue(), color);
			if(distance < lowestValue) {
				lowestValue = distance;
				vari = derp.getKey();
			}		
		}
		return vari;
	}
	
	public void calibroi(String varinNimi, int[] vari) {
		calib.put(varinNimi, vari);
	}

	private double etaisyys(int[] calibrationColor, int[] color) {
		double red = Math.pow((double)(color[0] - (double)calibrationColor[0]), 2);
		double green = Math.pow((double)(color[1] - (double)calibrationColor[1]), 2);
		double blue= Math.pow((double)(color[2] - (double)calibrationColor[2]), 2);
		double etaisyys = Math.sqrt(red + green + blue);
		return etaisyys;
	}	
}