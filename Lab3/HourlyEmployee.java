package CSC518Web.Lab3;

// HourlyEmployee class extends the abstract Employee class.
public class HourlyEmployee extends Employee
{
	private double hourlySalary;
	private int numOfHours;

    // constructor
    public HourlyEmployee( String name, EmpTypes type, double salary, int hours)
    {
    	super(name, type);
    	setHourlySalary(salary);
    	setNumOfHours(hours);
	}
	
	// set hourly salary
	public void setHourlySalary(double salary)
	{
		if ( salary >= 0.0 )
		{
			this.hourlySalary = salary;
		}
		else
		{
			throw new IllegalArgumentException("Salary must be >= 0.0");
		}
	} 
	
	// get hourly salary
	public double getHourlySalary()
	{
		return hourlySalary;
	} 

	// set number of hours
	public void setNumOfHours(int hours)
	{
		if ( hours >= 0 )
		{
			this.numOfHours = hours;
		}
		else
		{
			throw new IllegalArgumentException("Work hours must be >= 0.");
		}
	}

	// get number of hours
	public int getNumOfHours()
	{
		return this.numOfHours;
	} 

	// return String representation of HourlyEmployee object
	@Override
	public String toString()
	{
		return String.format( "%s %d $%,.2f $%,.2f", super.toString(), this.numOfHours, 
			this.hourlySalary, weeklyPay());
	} 

	// calculate weekly pay; override abstract method weeklyPay in Employee    
	public double weeklyPay()
	{	
		int extra = this.numOfHours - 40;
		if (extra <= 0)
		{
			return this.hourlySalary * this.numOfHours;
		}
		else
		{
			return this.hourlySalary * 40 + this.hourlySalary * 2 * extra;
		}
	}
}

