import java.awt.Color;

public class Tile 
{
	private Color tileColor;
	private float brightness;
	private TileType type;

	
	//---- GETTERS AND SETTERS ----
	public Color getTileColor() {
		return tileColor;
	}

	public void setTileColor(Color tileColor) {
		this.tileColor = tileColor;
	}

	public float getBrightness() {
		return brightness;
	}

	public void setBrightness(float brightness) {
		this.brightness = brightness;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}
	
	//----Constructors ----
	public Tile() 
	{
		tileColor = Color.blue;
		brightness = 1;
		type = TileType.Water;
	}
	
	public Tile(Color tileColor, float brightness, TileType type) 
	{
		this.tileColor = tileColor;
		this.brightness = brightness;
		this.type = type;
	}
	
}
