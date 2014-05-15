import java.awt.Color;

public class YPlane implements Renderable 
{
	double yValue;
	
	public YPlane(double yValue)
	{
		this.yValue = yValue;
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
	public double getIntersectionValue(Ray ray) 
	{
		//Solve when yEqn = yValue;
		double tValue = (yValue - ray.yEquation.constant) / ray.yEquation.coefficient;
		if(tValue >= 0)
		{
			return tValue;
		}
		else
		{
			return Ray.NO_INTERSECTION;
		}
	}

}