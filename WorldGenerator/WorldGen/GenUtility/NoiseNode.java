package GenUtility;
public class NoiseNode 
{
	private double scaleX, scaleY;
	private double lerpAmount;
	private NoiseMode noiseMode;
	private boolean invert;
	
	public double getScaleX() {
		return scaleX;
	}
	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}
	public double getScaleY() {
		return scaleY;
	}
	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}
	public NoiseMode getNoiseMode() {
		return noiseMode;
	}
	public void setNoiseMode(NoiseMode noiseMode) {
		this.noiseMode = noiseMode;
	}
	public double getLerpAmount() {
		return lerpAmount;
	}
	public void setLerpAmount(double lerpAmount) {
		this.lerpAmount = lerpAmount;
	}
	public boolean isInvert() {
		return invert;
	}
	public void setInvert(boolean invert) {
		this.invert = invert;
	}
	
	public NoiseNode() {}
	public NoiseNode(double scaleX, double scaleY, NoiseMode mode) 
	{		
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		
		this.noiseMode = mode;
	}
	
}
