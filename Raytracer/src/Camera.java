
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
	
	public Point3D GetAdjustedForCameraRotation(Point3D point)
	{
		return Utils.RotatePointAroundPoint(center, point, angleHoriz, angleVert);
	}
	
	public Vector3D GetAdjustedForCameraRotation(Vector3D vector)
	{
		return Utils.RotateVectorAroundCenter(vector, angleHoriz, angleVert);
	}
}
