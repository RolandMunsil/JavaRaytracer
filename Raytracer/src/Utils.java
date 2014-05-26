public class Utils 
{
	public static Point3D RotatePointAroundPoint(Point3D pivot, Point3D toRotate, double angleHoriz, double angleVert)
	{
		//Shift the point
		Vector3D diff = Point3D.Subtract(toRotate, pivot);
		
		Vector3D newDiff = RotateVectorAroundCenter(diff, angleHoriz, angleVert);
		
		return Point3D.Add(pivot, newDiff);
	}
	
	public static Vector3D RotateVectorAroundCenter(Vector3D vector, double angleHoriz, double angleVert)
	{
		//Rotate the point around the center
		if(angleVert != 0)
		{
			//Now do vertical
			double lengthYZ = Math.sqrt(vector.y * vector.y + vector.z * vector.z);
			double angleYZ = Math.atan2(vector.y, vector.z);
			
			double newAngle = angleYZ + angleVert;
			vector.y = Math.sin(newAngle) * lengthYZ;
			vector.z = Math.cos(newAngle) * lengthYZ;
		}
		
		if(angleHoriz != 0)
		{
			//First do horizontal
			double lengthXZ = Math.sqrt(vector.x * vector.x + vector.z * vector.z);
			double angleXZ = Math.atan2(vector.x, vector.z);
			
			double newAngle = angleXZ + angleHoriz;
			vector.x = Math.sin(newAngle) * lengthXZ;
			vector.z = Math.cos(newAngle) * lengthXZ;
		}
		
		return vector;
	}
}
