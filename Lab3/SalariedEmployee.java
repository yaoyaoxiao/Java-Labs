package CSC518Web.Lab3;

// SalariedEmployee class extends the abstract Employee class.
public class SalariedEmployee extends Employee
{

	public static boolean bonusAwarded;
	private double weeklySalary;
	private boolean rewarded;

    // constructor
    public SalariedEmployee( String name, EmpTypes type, double salary, boolean reward)
    {
    	super(name, type);
    	setWeeklySalary(salary);
    	this.rewarded = reward;
    	if (!bonusAwarded && reward) 
    	{
    		bonusAwarded = reward;
    	}
	}
	
	// set weekly salary
	public void setWeeklySalary(double salary)
	{
		if ( salary >= 0.0 )
		{
			this.weeklySalary = salary;
		}
		else
		{
			throw new IllegalArgumentException("Salary must be >= 0.0");
		}
 
	} 
	
	// get weekly salary
	public double getWeeklySalary()
	{
		return this.weeklySalary;
	} 

	// set rewarded
	public void setRewarded (boolean reward)
	{
		this.rewarded = reward;
		if (!bonusAwarded && reward) 
    	{
    		bonusAwarded = reward;
    	}
	}

	// get rewarded
	public boolean getReworded()
	{
		return this.rewarded;
	}


	// return String representation of SalariedEmployee object
	@Override
	public String toString()
	{
		String rewardSign = this.rewarded? "*":"";
		return String.format( "%s  $%,.2f%s", super.toString(), this.weeklyPay(), rewardSign);
	} 

	// calculate weekly pay; override abstract method weeklyPay in Employee    
	public double weeklyPay()
	{
		double res = 0;
		if (!this.rewarded)
		{
			res = getWeeklySalary();
		}
		else
		{
			res =  (1 + 0.1) * getWeeklySalary();
		}
		return res;
	}
}

