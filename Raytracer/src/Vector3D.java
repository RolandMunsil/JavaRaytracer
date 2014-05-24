
public class Vector3D 
{
	double x;
	double y;
	double z;
	
	public Vector3D(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static double DotProduct(Vector3D vector1, Vector3D vector2)
	{
		return (vector1.x * vector2.x) + (vector1.y * vector2.y) + (vector1.z * vector2.z);
	}
	
	public static Vector3D Multiply(Vector3D vector, double scalar)
	{
		return new Vector3D(vector.x * scalar, vector.y * scalar, vector.z * scalar);
	}
	
	public static Vector3D Subtract(Vector3D vector1, Vector3D vector2)
	{
		return new Vector3D(
				vector1.x - vector2.x,
				vector1.y - vector2.y,
				vector1.z - vector2.z
				);
	}
	
	public static Vector3D Add(Vector3D vector1, Vector3D vector2)
	{
		return new Vector3D(
				vector1.x + vector2.x,
				vector1.y + vector2.y,
				vector1.z + vector2.z
				);
	}
	
	public Point3D ToPoint3D()
	{
		return new Point3D(x, y, z);
	}
	
	public double GetLength()
	{
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	public Vector3D GetNormalized()
	{
		return new Vector3D(x / GetLength(), y / GetLength(), z / GetLength());
	}
	
	//http://math.stackexchange.com/questions/13261/how-to-get-a-reflection-vector
	public Vector3D GetReflected(Vector3D surfaceNormal)
	{
		Vector3D normalizedNormal = surfaceNormal.GetNormalized();
		double dotProduct = DotProduct(this, normalizedNormal);
		Vector3D dunno = Multiply(Multiply(normalizedNormal, 2), dotProduct);
		
		return Subtract(this, dunno);
	}
	
	//http://en.wikipedia.org/wiki/Snell's_law#Vector_form
	public RefractionInfo GetRefracted(Vector3D surfaceNormal, double refractIndexFrom, double refractIndexTo)
	{	
		Vector3D negNormal = new Vector3D(-surfaceNormal.x, -surfaceNormal.y, -surfaceNormal.z);
		
		double cos1 = Vector3D.DotProduct(this, negNormal);
		if(cos1 < 0)
		{
			int k = 1 / 0;
		}
		
		double refractRatio = refractIndexFrom / refractIndexTo;
		double otherSine = refractRatio * Math.sqrt(1 - (cos1 * cos1));
		
		if(otherSine > 1)
		{
			//Total internal reflection
			return new RefractionInfo(true, GetReflected(surfaceNormal));
		}
		else
		{
			double cos2 = Math.sqrt(1 - (otherSine * otherSine));
			double nMult = (refractRatio * cos1) - cos2;
			
			return new RefractionInfo(false,
					Vector3D.Add(
							Vector3D.Multiply(this, refractRatio), 
							Vector3D.Multiply(surfaceNormal, nMult)
							)
						);
		}
	}
}
