package CSC518Web.Lab3;

// Employee abstract superclass.
public abstract class Employee
{
	// define an enum for employee types
	public static enum EmpTypes {SALARIED, HOURLY, COMMISSIONED};

	private String empName;
    private EmpTypes empType;

    // constructor
    public Employee( String name, EmpTypes type)
    {
    	this.empName = name;
    	this.empType = type;
	}
	
	// set name
	public void setName( String name )
	{
		this.empName = name; 
	} 
	
	// get name
	public String getName()
	{
		return empName;
	} 

	// set type
	public void setType( EmpTypes type )
	{
		this.empType = type; 
	}

    // get type
	public EmpTypes getType()
	{
		return empType;
	} 

	// return String representation of Employee object
	@Override
	public String toString()
	{
		String type;
		switch (empType)
    	{
      		case SALARIED: 
      			type = "Salaried";
                break;

      		case HOURLY: 
      			type = "Hourly";
                break;

      		case COMMISSIONED: 
      			type = "Commissioned";
                break; 

      		default:
      		   type = "";        
      			System.out.println("unknown employee type.");;
    	}

		return this.empName + " " + type;
	} 

	// abstract method overridden by subclasses
	// no implementation here     
	public abstract double weeklyPay(); 
}

