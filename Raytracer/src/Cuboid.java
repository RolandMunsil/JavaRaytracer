import java.awt.Color;


public class Cuboid extends Renderable
{
	Color color;
	
	double xBoundHigh;
	double xBoundLow;
	double yBoundHigh;
	double yBoundLow;
	double zBoundHigh;
	double zBoundLow;
	
	public Cuboid(Point3D center, double width, double height, double depth, Color color, double reflectivity)
	{
		xBoundHigh = center.x + (width / 2);
		xBoundLow = center.x - (width / 2);
		yBoundHigh = center.y + (height / 2);
		yBoundLow = center.y - (height / 2);
		zBoundHigh = center.z + (depth / 2);
		zBoundLow = center.z - (depth / 2);
		
		this.color = color;
		this.reflectivity = reflectivity;
	}
	
	@Override
	public Color getColorAt(Point3D point) 
	{
		Vector3D vector = getNormalVectorAt(point);
		if(vector.x != 0)
		{
			return Color.RED;
		}
		if(vector.y != 0)
		{
			return Color.BLUE;
		}
		if(vector.z != 0)
		{
			return Color.GREEN;
		}
		
		return color;
	}
	
	@Override
	public Vector3D getNormalVectorAt(Point3D point) 
	{
		if(Math.abs(point.x - xBoundHigh) < .0001)
		{
			return new Vector3D(1, 0, 0);
		}
		if(Math.abs(point.x - xBoundLow) < .0001)
		{
			return new Vector3D(-1, 0, 0);
		}
		if(Math.abs(point.y - yBoundHigh) < .0001)
		{
			return new Vector3D(0, 1, 0);
		}
		if(Math.abs(point.y - yBoundLow) < .0001)
		{
			return new Vector3D(0, -1, 0);
		}
		if(Math.abs(point.z - zBoundHigh) < .0001)
		{
			return new Vector3D(0, 0, 1);
		}
		if(Math.abs(point.z - zBoundLow) < .0001)
		{
			return new Vector3D(0, 0, -1);
		}
		
		return null;
	}

	@Override
	public double getIntersectionValue(Ray ray) 
	{
		double closestTValue = Double.MAX_VALUE;
		
		//X values
		double xHighTVal = ray.xEquation.SolveWhenSetEqualTo(xBoundHigh);
		if(xHighTVal < closestTValue && xHighTVal > Ray.LOWER_T_BOUND)
		{
			Point3D pointAtVal = ray.GetPointAt(xHighTVal);
			if(pointAtVal.y <= yBoundHigh && pointAtVal.y >= yBoundLow && 
			   pointAtVal.z <= zBoundHigh && pointAtVal.z >= zBoundLow)
			{
				closestTValue = xHighTVal;
			}
		}
		
		double xLowTVal = ray.xEquation.SolveWhenSetEqualTo(xBoundLow);
		if(xLowTVal < closestTValue && xLowTVal > Ray.LOWER_T_BOUND)
		{
			Point3D pointAtVal = ray.GetPointAt(xLowTVal);
			if(pointAtVal.y <= yBoundHigh && pointAtVal.y >= yBoundLow && 
			   pointAtVal.z <= zBoundHigh && pointAtVal.z >= zBoundLow)
			{
				closestTValue = xLowTVal;
			}
		}
		
		
		//Y values
		double yHighTVal = ray.yEquation.SolveWhenSetEqualTo(yBoundHigh);
		if(yHighTVal < closestTValue && yHighTVal > Ray.LOWER_T_BOUND)
		{
			Point3D pointAtVal = ray.GetPointAt(yHighTVal);
			if(pointAtVal.x <= xBoundHigh && pointAtVal.x >= xBoundLow && 
			   pointAtVal.z <= zBoundHigh && pointAtVal.z >= zBoundLow)
			{
				closestTValue = yHighTVal;
			}
		}
		
		double yLowTVal = ray.yEquation.SolveWhenSetEqualTo(yBoundLow);
		if(yLowTVal < closestTValue && yLowTVal > Ray.LOWER_T_BOUND)
		{
			Point3D pointAtVal = ray.GetPointAt(yLowTVal);
			if(pointAtVal.x <= xBoundHigh && pointAtVal.x >= xBoundLow && 
			   pointAtVal.z <= zBoundHigh && pointAtVal.z >= zBoundLow)
			{
				closestTValue = yLowTVal;
			}
		}
		
		
		//Z values
		double zHighTVal = ray.zEquation.SolveWhenSetEqualTo(zBoundHigh);
		if(zHighTVal < closestTValue && zHighTVal > Ray.LOWER_T_BOUND)
		{
			Point3D pointAtVal = ray.GetPointAt(zHighTVal);
			if(pointAtVal.y <= yBoundHigh && pointAtVal.y >= yBoundLow && 
			   pointAtVal.x <= xBoundHigh && pointAtVal.x >= xBoundLow)
			{
				closestTValue = zHighTVal;
			}
		}
		
		double zLowTVal = ray.zEquation.SolveWhenSetEqualTo(zBoundLow);
		if(zLowTVal < closestTValue && zLowTVal > Ray.LOWER_T_BOUND)
		{
			Point3D pointAtVal = ray.GetPointAt(zLowTVal);
			if(pointAtVal.y <= yBoundHigh && pointAtVal.y >= yBoundLow && 
			   pointAtVal.x <= xBoundHigh && pointAtVal.x >= xBoundLow)
			{
				closestTValue = zLowTVal;
			}
		}
		
		return closestTValue == Double.MAX_VALUE ? Ray.NO_INTERSECTION : closestTValue;
	}
}
