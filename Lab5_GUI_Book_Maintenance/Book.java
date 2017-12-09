public class Book
{
	private String code;
	private String title;
	private double price;

	public Book(String bookCode, String bookTitle, double bookPrice)
	{
		this.code = bookCode;
		this.title = bookTitle;
		this.price = bookPrice;
	}

	public String getCode()
	{
		return this.code;
	}

	public String getTitle()
	{
		return this.title;
	}

	public double getPrice()
	{
		return this.price;
	}

	public void setCode(String bookCode)
	{
		this.code = bookCode;
	}

	public void setTitle(String bookTitle)
	{
		this.title = bookTitle;
	}

	public void setPrice(double bookPrice)
	{
		this.price = bookPrice;
	}

	public void updateBook(String bookCode, String bookTitle, double bookPrice)
	{
		this.code = bookCode;
		this.title = bookTitle;
		this.price = bookPrice;
	}
}