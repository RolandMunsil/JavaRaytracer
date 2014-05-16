public class HitInfo 
{
	boolean didHit;
	Renderable hitObject;
	double tValue;
	
	public HitInfo(boolean didHit, Renderable hitObject, double tValue) 
	{
		this.didHit = didHit;
		this.hitObject = hitObject;
		this.tValue = tValue;
	}
}
