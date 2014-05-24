import java.awt.Color;

public class Hyperbola extends Renderable
{
	private Color color;
	Point3D center;
	double radius;
	
	public Hyperbola(Color color, Point3D center, double radius, double reflectivity)
	{
		this.color = color;
		this.center = center;
		this.radius = radius;
		this.reflectivity = reflectivity;
	}
	
	@Override
	public Color getColorAt(Point3D point) 
	{
		return color;
	}
	
	@Override
	public Vector3D getNormalVectorAt(Point3D point) 
	{
		Vector3D diff = Point3D.Subtract(point, center);
		diff.y *= -1;
		return diff;
	}
	
	@Override
	public double getIntersectionValue(Ray ray) 
	{
		//hyperbola: r^2 = (x - x1)^2 - (y - y1)^2 + (z - z1)^2
		//Line: x = at + b
		//      y = ct + d
		//      z = et + g
		
		LinearEquation combinedX = LinearEquation.Add(ray.xEquation, -center.x);
		LinearEquation combinedY = LinearEquation.Add(ray.yEquation, -center.y);
		LinearEquation combinedZ = LinearEquation.Add(ray.zEquation, -center.z);
		
		QuadraticEquation combined = QuadraticEquation.Add(
				QuadraticEquation.Subtract(
						LinearEquation.Multiply(combinedX, combinedX), 
						LinearEquation.Multiply(combinedY, combinedY)), 
				LinearEquation.Multiply(combinedZ, combinedZ));
		
		combined.constant -= radius * radius;
		
		//Find zeroes using quadratic equation
		double a = combined.quadCoefficient;
		double b = combined.linearCoefficient;
		double c = combined.constant;
		
		double numToSqrt = (b * b) - (4 * a * c);
		if(numToSqrt < 0)
		{
			return Ray.NO_INTERSECTION;
		}
		
		if(a == 0) //Divide by zero not allowed
		{
			return Ray.NO_INTERSECTION;
		}
		double higherZero = (-b + Math.sqrt(numToSqrt))/(2 * a);
		double lowerZero = (-b - Math.sqrt(numToSqrt))/(2 * a);
		
		if(higherZero <= 0 && lowerZero <= 0)
		{
			return Ray.NO_INTERSECTION;
		}
		else if (lowerZero <= 0)
		{
			return higherZero;
		}
		else
		{
			return lowerZero;
		}
	}

	
}
