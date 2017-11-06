public class ProcessPhoneNum 
{ 
	/**
  		Format a ten-digit phone number (such as 9077777767) into a more readable string 
		with parentheses and dashes, like this: (907)777-7767.
  		@param  phoneNum  The original ten-digit string that need to be formatted.
  		@return  The formatted phone number
	*/
	public static String formatPhoneNum(String phoneNum)
	{
		// Handle the area code(the first three characters) by surrounding it with parentheses.

		String areaCode = "(" + phoneNum.substring(0,3) + ")";

		// Concatenate the area code, the substring consisting of the next three characters, 
		// a hyphen, and the substring consisting of the rest characters.
		// And return it.

		return areaCode 
			 + phoneNum.substring(3,6) 
			 + "-" 
			 + phoneNum.substring(6);
	}
 

	public static void main(String[] args) 
	{
		// prepare 5 input phone numbers
		String[] phoneNums = {"9077777767", "4155551212", "6082311717", "6502000515", "2069220886"};

		// assuming that all input are valid (strings composed of ten digits) and format each string
		for (int i = 0; i < phoneNums.length; i++)
		{
			String formattedStr = formatPhoneNum(phoneNums[i]);
			
			// output the formatted phone number
			System.out.println(formattedStr);
		}	 
   } 
}
