
public class Camera 
{
	Point3D center;
	double angleHoriz;
	double angleVert;
	
	public Camera(Point3D center, double angleHoriz, double angleVert) 
	{
		this.center = center;
		this.angleHoriz = angleHoriz;
		this.angleVert = angleVert;
	}
	
	public Camera(Point3D center, Point3D lookingAt) 
	{
		this.center = center;
		
		Vector3D diffFromCenter = Point3D.Subtract(lookingAt, center);
		
		this.angleHoriz = Math.sin(Math.atan2(diffFromCenter.x, diffFromCenter.z));
		this.angleVert = Math.sin(Math.atan2(diffFromCenter.y, diffFromCenter.z));
	}
	
	public Point3D GetAdjustedForCameraRotation(Point3D point)
	{
		return Utils.RotatePointAroundPoint(center, point, angleHoriz, angleVert);
	}
	
	public Vector3D GetAdjustedForCameraRotation(Vector3D vector)
	{
		return Utils.RotateVectorAroundCenter(vector, angleHoriz, angleVert);
	}
}
