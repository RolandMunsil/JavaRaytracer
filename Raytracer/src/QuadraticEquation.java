
public class QuadraticEquation 
{
	public double quadCoefficient;
	public double linearCoefficient;
	public double constant;
	
	public QuadraticEquation(double quadCoefficient, double linearCoefficient, double constant)
	{
		this.quadCoefficient = quadCoefficient;
		this.linearCoefficient = linearCoefficient;
		this.constant = constant;
	}
	
	public static QuadraticEquation Add(QuadraticEquation eqn1, QuadraticEquation eqn2)
	{
		return new QuadraticEquation(
				eqn1.quadCoefficient   + eqn2.quadCoefficient,
				eqn1.linearCoefficient + eqn2.linearCoefficient, 
				eqn1.constant          + eqn2.constant);
	}
}
