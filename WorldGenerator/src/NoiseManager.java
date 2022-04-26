
import java.util.ArrayList;

import Utility.NoiseNode;

public class NoiseManager 
{
	public static ArrayList<NoiseNode> noiseNodes = new ArrayList<>();

	public static double GetTile(double x, double y) 
	{
		double resultValue = 1;
		
		for(NoiseNode n : noiseNodes) 
		{
			double noiseVal = WorldGeneration.noise.eval((double)x / (double)n.getScaleX(), (double)y / (double)n.getScaleY(), 1);
			
			if(n.isInvert()) 
			{
				noiseVal *= -noiseVal; 
			}
			
			switch(n.getNoiseMode()) 
			{
			case Add:
				resultValue = Clamp(resultValue + noiseVal, -1d, 1d);
				break;
				
			case Lerp:
				resultValue = Lerp(resultValue, noiseVal, n.getLerpAmount());
				break;
				
			case Multiply:
				resultValue = Clamp(resultValue * Math.abs(noiseVal), -1d, 1d);
				break;
				
			case Substract:
				resultValue = Clamp(resultValue - Math.abs(noiseVal), -1d, 1d);
				break;
				
			default:
				break;
			}
		}
		
		return resultValue;
	}
	
	//---- CLAMPING ----
	//Clamps value between minimal and maximal value
	public static double Clamp(double a, double min, double max) 
	{
		if(a < min) a = min;
		if(a > max) a = max;
			
		return a;
	}	
	public static int Clamp(int a, int min, int max) 
	{
		if(a < min) a = min;
		if(a > max) a = max;
			
		return a;
	}	

	//---- LERPING ----
	//Linear interpolation, used for smoothing noise maps
	public static float Lerp(float a, float b, float f)
	{
		return a + f * (b - a);
	}
	public static double Lerp(double a, double b, double f)
	{
		return a + f * (b - a);
	}	
}
