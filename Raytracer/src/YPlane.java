import java.awt.Color;

public class YPlane extends Renderable 
{
	double yValue;
	
	public YPlane(double yValue, double reflectivity, double refractivity, double refractionIndex)
	{
		this.yValue = yValue;
		this.reflectivity = reflectivity;
		this.refractivity = refractivity;
		this.refractionIndex = refractionIndex;
	}
	
	@Override
	public Color getColorAt(Point3D point) 
	{
		int adjX = (int)Math.floor(point.x / 100);
		int adjZ = (int)Math.floor(point.z / 100);
		
		if((((adjX % 2) + 2) % 2) != (((adjZ % 2) + 2) % 2))
		{
			return Color.WHITE;
		}
		else
		{
			return Color.BLACK;
		}
	}
	
	@Override
	public Vector3D getNormalVectorAt(Point3D point) 
	{
		return new Vector3D(0, 1, 0);
	}

	@Override
	public double getIntersectionValue(Ray ray) 
	{
		//Solve when yEqn = yValue;
		double tValue = ray.yEquation.SolveWhenSetEqualTo(yValue);
		if(tValue > Ray.LOWER_T_BOUND && Math.abs(ray.GetPointAt(tValue).x) < 10000 && Math.abs(ray.GetPointAt(tValue).z) < 10000)
		{
			return tValue;
		}
		else
		{
			return Ray.NO_INTERSECTION;
		}
	}

}
