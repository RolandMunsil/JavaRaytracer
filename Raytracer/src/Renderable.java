import java.awt.Color;

public abstract class Renderable 
{
	public double reflectivity;
	public double refractivity;
	
	public double refractionIndex;
	
	public abstract Color getColorAt(Point3D point);
	public abstract Vector3D getNormalVectorAt(Point3D point);
	public abstract double getIntersectionValue(Ray ray);
}
