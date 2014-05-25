public class Ray 
{
	public static final double NO_INTERSECTION = Double.MAX_VALUE;
	public static final double LOWER_T_BOUND = 0.00000001;
	
	LinearEquation xEquation;
	LinearEquation yEquation;
	LinearEquation zEquation;
	
	public Ray(LinearEquation xEquation, LinearEquation yEquation, LinearEquation zEquation)
	{
		this.xEquation = xEquation;
		this.yEquation = yEquation;
		this.zEquation = zEquation;
	}
	
	public Ray(Vector3D direction, Point3D origin)
	{
		xEquation = new LinearEquation(direction.x, origin.x);
		yEquation = new LinearEquation(direction.y, origin.y);
		zEquation = new LinearEquation(direction.z, origin.z);
	}
	
	public Ray(Point3D from, Point3D to)
	{
		xEquation = new LinearEquation(to.x - from.x, from.x);
		yEquation = new LinearEquation(to.y - from.y, from.y);
		zEquation = new LinearEquation(to.z - from.z, from.z);
	}
	
	public Point3D GetPointAt(double tValue)
	{
		return new Point3D(
				xEquation.GetValueAt(tValue),
				yEquation.GetValueAt(tValue),
				zEquation.GetValueAt(tValue));
	}
	
	public Vector3D ToVector3D()
	{
		return new Vector3D(xEquation.coefficient, yEquation.coefficient, zEquation.coefficient);
	}
}
