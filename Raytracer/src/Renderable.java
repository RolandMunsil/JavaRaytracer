import java.awt.Color;

public interface Renderable 
{
	public Color getColorAt(Point3D point);
	public Vector3D getNormalVectorAt(Point3D point);
	public double getIntersectionValue(Ray ray);
}
