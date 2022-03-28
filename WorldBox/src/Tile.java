import java.awt.Color;

public class Tile 
{
	private Color tileColor;
	private double height;
	private TileType type;
	private BiomeType biome;

	
	//---- GETTERS AND SETTERS ----
	public Color getTileColor() {
		return tileColor;
	}
	public void setTileColor(Color tileColor) {
		this.tileColor = tileColor;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public TileType getType() {
		return type;
	}
	public void setType(TileType type) {
		this.type = type;
	}
	public BiomeType getBiome() {
		return biome;
	}
	public void setBiome(BiomeType biome) {
		this.biome = biome;
	}
	
	//----Constructors ----
	public Tile() 
	{
		tileColor = Color.green;
		type = TileType.Grass;
	}
	
	public Tile(Color tileColor, TileType type, double height) 
	{
		this.tileColor = tileColor;
		this.type = type;
		this.height = height;
	}
}
