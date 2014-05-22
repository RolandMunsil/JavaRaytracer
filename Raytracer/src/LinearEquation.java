public class LinearEquation 
{
	public double coefficient;
	public double constant;
	
	public LinearEquation(double coefficient, double constant)
	{
		this.coefficient = coefficient;
		this.constant = constant;
	}
	
	public double GetValueAt(double tValue)
	{
		return (coefficient * tValue) + constant;
	}
	
	public double SolveWhenSetEqualTo(double value)
	{
		return (value - constant) / coefficient;
	}
	
	public static LinearEquation Add(LinearEquation eqn1, LinearEquation eqn2)
	{
		return new LinearEquation(eqn1.coefficient + eqn2.coefficient, eqn1.constant + eqn2.constant);
	}
	
	public static LinearEquation Add(LinearEquation eqn1, double constant)
	{
		return new LinearEquation(eqn1.coefficient, eqn1.constant + constant);
	}
	
	public static QuadraticEquation Multiply(LinearEquation eqn1, LinearEquation eqn2)
	{
		return new QuadraticEquation(
				eqn1.coefficient * eqn2.coefficient,
				(eqn1.coefficient * eqn2.constant) + (eqn2.coefficient * eqn1.constant),
				eqn1.constant * eqn2.constant);
		
	}
}
