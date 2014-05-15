public class Ray 
{
	public static final double NO_INTERSECTION = -1;
	
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
	
	public Point3D GetPointAt(double tValue)
	{
		return new Point3D(
				xEquation.GetValueAt(tValue),
				yEquation.GetValueAt(tValue),
				zEquation.GetValueAt(tValue));
	}
}
