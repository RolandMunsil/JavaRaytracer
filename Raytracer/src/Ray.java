public class Ray 
{
	public static final double NO_INTERSECTION = Double.MAX_VALUE;
	public static final double LOWER_T_BOUND = 0.00000001;
	
	LinearEquation xEquation;
	LinearEquation yEquation;
	LinearEquation zEquation;
	
	public double containingMaterialRefractIndex;
	public static final double AIR_REFR_INDEX = 1;//.000277;
	public static final double UNDISTORTED_REFR_INDEX = AIR_REFR_INDEX;
	public static final double WATER_REFR_INDEX = 1.3330;
	public static final double PYREX_REFR_INDEX = 1.470;
	public static final double DIAMOND_REFR_INDEX = 2.419;
	
	public Ray(LinearEquation xEquation, LinearEquation yEquation, LinearEquation zEquation, double containingMaterialRefractIndex)
	{
		this.xEquation = xEquation;
		this.yEquation = yEquation;
		this.zEquation = zEquation;
		
		this.containingMaterialRefractIndex = containingMaterialRefractIndex;
	}
	
	public Ray(Vector3D direction, Point3D origin, double containingMaterialRefractIndex)
	{
		xEquation = new LinearEquation(direction.x, origin.x);
		yEquation = new LinearEquation(direction.y, origin.y);
		zEquation = new LinearEquation(direction.z, origin.z);
		
		this.containingMaterialRefractIndex = containingMaterialRefractIndex;
	}
	
	public Ray(Point3D from, Point3D to, double containingMaterialRefractIndex)
	{
		xEquation = new LinearEquation(to.x - from.x, from.x);
		yEquation = new LinearEquation(to.y - from.y, from.y);
		zEquation = new LinearEquation(to.z - from.z, from.z);
		
		this.containingMaterialRefractIndex = containingMaterialRefractIndex;
	}
	
	public Point3D GetPointAt(double tValue)
	{
		return new Point3D(
				xEquation.GetValueAt(tValue),
				yEquation.GetValueAt(tValue),
				zEquation.GetValueAt(tValue));
	}
	
	public Vector3D ToVector3D()
	{
		return new Vector3D(xEquation.coefficient, yEquation.coefficient, zEquation.coefficient);
	}
}
