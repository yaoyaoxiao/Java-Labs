package CSC518Web.Lab3;

import java.util.Scanner;
import java.util.ArrayList;

public class WeeklyPayReport
{
	public void printReport(ArrayList<Employee> employees)
	{
		double total = 0.00;

		// println header
		System.out.println("Name Class Hours Sales Rate Weekly Pay Amount");
		System.out.println("===========================================================");

		//process each employee in the employees ArrayList
		for ( Employee currentEmployee : employees )
		{
			total += currentEmployee.weeklyPay();
			System.out.println( currentEmployee ); // invokes toString
		} 

		// println footer (the TOTAL and bonus status if needed)
		System.out.println("===========================================================");
		//System.out.println("TOTAL " + total);
		System.out.format( "TOTAL  $%,.2f%n", total);
		if (SalariedEmployee.bonusAwarded)
		{
			System.out.println("*A 10% bonus is awarded");
		}
	}
	

	public static void main( String[] args )
	{
		// create an ArrayList for multipul employees
		ArrayList<Employee> employees = new ArrayList<Employee>();

		Scanner in = new Scanner(System.in);
		WeeklyPayReport WeeklyReport = new WeeklyPayReport();
		boolean done = false;
		String name = "";
		char chr = ' ';
		Employee.EmpTypes type = null;

		do
		{
			name = "";
			type = null;
			chr = ' ';

			// get employee's name
			while(name == "")
			{
				System.out.println("Please enter the employee's name（First Last）: ");
				if (in.hasNextLine())
				{
					name = in.nextLine();
				}
				else
				{
					System.out.println ("Invalid input!");
				}
			}
			

			while(type == null)
			{
				System.out.println("Please enter the employee type (Enter s for salaried, h for hourly, c for commissioned): ");
				if (in.hasNextLine())
				{
					chr = in.nextLine().toLowerCase().charAt(0); //Convert to lowercase and then use the first char
				}
				if (chr == 's')
				{
					type = Employee.EmpTypes.SALARIED;
				}
				else if (chr == 'h')
				{
					type = Employee.EmpTypes.HOURLY;
				}
				else if (chr == 'c')
				{
					type = Employee.EmpTypes.COMMISSIONED;
				}
				else
				{
					System.out.println ("Invalid input!");
				}
			}

			switch (type)
    		{
      			case SALARIED: 
      				double weelySalary = 0.00;
      				boolean reward = false;
      				System.out.print("Please enter the employee's weekly salary: ");
					if (in.hasNextDouble())
					{
						weelySalary = in.nextDouble();
					}
					if (in.hasNextLine())
					{
						in.nextLine();
					}

					System.out.print("Do you want to reward this employee by adding 10% to their salaries? (Y/N): ");
					if (in.hasNext())
					{
						chr = in.next().toUpperCase().charAt(0);
						if (chr == 'Y') 
						{
							reward = true;
						}
					}
					if (in.hasNextLine())
					{
						in.nextLine();
					}

					// create a new instance of SalariedEmployee and add it into the Arraylist
					employees.add(new SalariedEmployee(name, type, weelySalary, reward));
					break;

				case HOURLY: 
					double hourlyRate = 0.00;
					int hours = 0;

					System.out.print("Please enter the employee's hourly salary: ");
					if (in.hasNextDouble())
					{
						hourlyRate = in.nextDouble();
					}
					if (in.hasNextLine())
					{
						String useless = in.nextLine();
					}

					System.out.print("Please enter the number of hours this employee worked: ");
					if (in.hasNextInt())
					{
						hours = in.nextInt();
					}
					if (in.hasNextLine())
					{
						in.nextLine();
					}

					// create a new instance of HourlyEmployee  and add it into the Arraylist
					employees.add(new HourlyEmployee(name, type, hourlyRate, hours));
					break;

				case COMMISSIONED: 
					double weeklySales = 0.00;
					System.out.print("Please enter the weekly sales: ");
					if (in.hasNextDouble())
					{
						weeklySales = in.nextDouble();
					}
					if (in.hasNextLine())
					{
						String useless = in.nextLine();
					}

					// create a new instance of CommissionedEmployee  and add it into the Arraylist
					employees.add(new CommissionedEmployee(name, type, weeklySales));
					break; 

				default:        
					System.out.println("unknown employee type.");
			}

			if (!done)
			{
				System.out.print("Do you want to add another employee? (Y/N): ");
				if (in.hasNext())
				{
					chr = in.next().toUpperCase().charAt(0);
					if (chr == 'N')
					{
						done = true;
					}
				}
				if (in.hasNextLine())
				{
					in.nextLine();
				}
			} 
		}while(!done); // end while

		// println the report
		if (employees.size() > 0)
		{
			WeeklyReport.printReport(employees);
		}

	} // end main
} // end class WeeklyPayReport
