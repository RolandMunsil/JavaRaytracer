
public class Light 
{
	Point3D center;
	double furthestLitDistance;
	double maxBrightness;
	
	public Light(Point3D center, double furthestLitDistance, double maxBrightness) 
	{
		this.center = center;
		this.furthestLitDistance = furthestLitDistance;
		this.maxBrightness = maxBrightness;
	}
	
	public double GetLightLevelAt(Point3D point)
	{
		Vector3D diff = Point3D.Subtract(point, center);
		double distance = diff.GetLength();
		
		if(distance > furthestLitDistance)
		{
			return 0;
		}
		else
		{
			return (1 - (distance / furthestLitDistance)) * maxBrightness;
		}
	}
}
