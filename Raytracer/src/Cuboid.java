import java.awt.Color;


public class Cuboid extends Renderable
{
	Color color;
	
	public Cuboid(Color color, double reflectivity)
	{
		this.color = color;
		this.reflectivity = reflectivity;
	}
	
	@Override
	public Color getColorAt(Point3D point) 
	{
		return color;
	}
	
	@Override
	public Vector3D getNormalVectorAt(Point3D point) 
	{
		return null;
	}

	@Override
	public double getIntersectionValue(Ray ray) 
	{
		return Ray.NO_INTERSECTION;
	}
}
