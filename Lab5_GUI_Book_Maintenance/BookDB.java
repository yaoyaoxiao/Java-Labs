import java.io.File;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

public class BookDB
{
	private LinkedList<Book> books;
	private int currIdx;
	private String dataFile;

	public BookDB(String fileName)
	{
		this.dataFile = fileName;
		this.books = new LinkedList<Book>();
		this.loadDB();
	}

	public Book getCurrent()
	{
		int n = this.books.size();
		Book res = null;
		if (n > 0 && this.currIdx != -1 && this.currIdx < n)
		{
			res =  this.books.get(this.currIdx);
		}
		return res;
	}

	//load data from a file
	public void loadDB()
	{
		try {
			File inputFile = new File(this.dataFile);
			Scanner in = new Scanner(inputFile);

			// read the file line by line, each line refers to a book
			while (in.hasNextLine()) 
			{
				String bookStr = in.nextLine();
				String bookInfo[]= bookStr.split("\\|");
				System.out.print(bookInfo);
				if (bookInfo.length >= 3)
				{
					String code = bookInfo[0];
					String title = bookInfo[1];
					String p = bookInfo[2];
					double price = Double.parseDouble(p);
					Book book = new Book(code,title,price);
					books.add(book);
				}
			}

			in.close();  // close the file

			if (this.books.size() > 0)
			{
				this.currIdx = 0;
			}
			else
			{
				this.currIdx = -1;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	// save data back to file
	public void saveDB()
	{
		try {
			File outputFile = new File(this.dataFile);
			PrintWriter out = new PrintWriter(new FileOutputStream(outputFile));
			for (Book book: this.books) 
			{
				String price = String.format( "%,.2f", book.getPrice());
				out.println(book.getCode() + "|" + book.getTitle() + "|" + price);
			}

			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Book moveFirst()
	{
		this.currIdx =  this.books.size() > 0? 0 : -1;
		return this.books.getFirst();
	}

	public Book moveLast()
	{
		int n = this.books.size();
		this.currIdx =  n > 0? n-1 : -1;
		return this.books.getLast();
	}

	public Book moveNext()
	{
		int n = this.books.size();
		if (n > 0)
		{
			if (this.currIdx < n-1)
			{
				this.currIdx++;
			}
			// if the current book is the last one, don't move (disable the "Next" button)			
		}
		return this.books.get(this.currIdx);
	}

	public Book movePrevious()
	{
		int n = this.books.size();
		if (n > 0)
		{
			if (this.currIdx > 0)
			{
				this.currIdx--;
			}
			// if the current book is the first one, don't move (disable the "Previous" button)
		}
		return this.books.get(this.currIdx);
	}

	public void addBook(Book book)
	{
		this.books.add(book);  // add the book to the end of the list
		this.currIdx = this.books.size() - 1;  

		/*
		// instead of update the whole file, use the append mode to add a new line to the end
		try 
		{
			File outputFile = new File(this.dataFile);
			PrintWriter out = new PrintWriter(new FileOutputStream(outputFile),true);
			out.println(book.getCode() + "|" + book.getTitle() + "|" + book.getPrice());

			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		saveDB();
	}

	public void deleteBook(Book book)
	{
		int idx = this.books.indexOf(book);
		this.books.remove(idx);

		// After it deletes the record from the file or database, the program displays 
		// the data for the next record (or the new last record if the deleted record was the last record).
		this.currIdx = (idx == this.books.size())? idx -1 : idx;

		// save the changes back to the file
		saveDB();
	}

	public void updateBook(Book book)
	{
		// update the element in the current position
		this.books.set(this.currIdx, book);

		// save the changes back to the file
		saveDB();
	}


}