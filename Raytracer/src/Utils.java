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
