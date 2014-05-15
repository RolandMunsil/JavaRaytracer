
public class Point3D 
{
	double x;
	double y;
	double z;
	
	public Point3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static Vector3D Subtract(Point3D point1, Point3D point2)
	{
		return new Vector3D(
				point1.x - point2.x,
				point1.y - point2.y,
				point1.z - point2.z
				);
	}
}
