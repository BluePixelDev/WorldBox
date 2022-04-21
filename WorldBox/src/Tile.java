import java.awt.Color;

public class Tile 
{
	private Vector2 position;
	private Color color;
	private double height;
	private TileType type;
	private BiomeType biome;

	
	//---- GETTERS AND SETTERS ----
	public Color getColor() {
		return color;
	}
	public void setColor(Color tileColor) {
		this.color = tileColor;
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
	public Vector2 getPosition() {
		return position;
	}
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
	//---- Constructors ----
	public Tile() 
	{
		position = new Vector2(0, 0);
		color = Color.green;
		type = TileType.Grass;
		biome = BiomeType.Plains;
	}	
	public Tile(Color tileColor, TileType type, double height) 
	{
		this.color = tileColor;
		this.type = type;
		this.height = height;
	}	
}
