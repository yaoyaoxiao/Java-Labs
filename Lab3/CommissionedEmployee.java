package CSC518Web.Lab3;

// CommissionedEmployee class extends the abstract Employee class.
public class CommissionedEmployee extends Employee
{
	private double weeklySales;

    // constructor
    public CommissionedEmployee( String name, EmpTypes type, double sales)
    {
    	super(name, type);
    	setWeeklySales(sales);
	}
	
	// set weekly sales
	public void setWeeklySales(double sales)
	{
		if ( sales >= 0.0 )
		{
			this.weeklySales = sales;
		}
		else
		{
			throw new IllegalArgumentException("Weekly sales must be >= 0.0");
		}
	} 
	
	// get weekly sales
	public double getWeeklySales()
	{
		return this.weeklySales;
	} 

	// return String representation of CommissionedEmployee object
	@Override
	public String toString()
	{
		return String.format( "%s  $%,.2f $%,.2f", super.toString(), this.weeklySales, weeklyPay());
	} 

	// calculate weekly pay; override abstract method weeklyPay in Employee    
	public double weeklyPay()
	{	
		return 0.2 * this.weeklySales;
	}
}

