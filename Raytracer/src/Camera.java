
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
		//Shift the point
		Vector3D diff = Point3D.Subtract(point, center);
		
		Vector3D newDiff = GetAdjustedForCameraRotation(diff);
		
		return Point3D.Add(center, newDiff);
	}
	
	public Vector3D GetAdjustedForCameraRotation(Vector3D vector)
	{
		Vector3D copyVec = new Vector3D(vector.x, vector.y, vector.z);
		
		//Rotate the point around the center
		
		
		
		if(angleVert != 0)
		{
			//Now do vertical
			double lengthYZ = Math.sqrt(copyVec.y * copyVec.y + copyVec.z * copyVec.z);
			double angleYZ = Math.atan2(copyVec.y, copyVec.z);
			
			double newAngle = angleYZ + angleVert;
			copyVec.y = Math.sin(newAngle) * lengthYZ;
			copyVec.z = Math.cos(newAngle) * lengthYZ;
		}
		
		if(angleHoriz != 0)
		{
			//First do horizontal
			double lengthXZ = Math.sqrt(copyVec.x * copyVec.x + copyVec.z * copyVec.z);
			double angleXZ = Math.atan2(copyVec.x, copyVec.z);
			
			double newAngle = angleXZ + angleHoriz;
			copyVec.x = Math.sin(newAngle) * lengthXZ;
			copyVec.z = Math.cos(newAngle) * lengthXZ;
		}
		
		return copyVec;
	}
}
