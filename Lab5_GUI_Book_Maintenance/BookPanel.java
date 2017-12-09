
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class BookPanel extends JPanel implements ActionListener, DocumentListener, KeyListener
{
	private BookDB bookDB;

	private JPanel panel;
	  
	// labels to the left of the testboxes
	private JLabel labelCode;
	private JLabel labelTitle;
	private JLabel labelPrice;

	// text fields for displaying/changing a book
	private JTextField fieldCode;
	private JTextField fieldTitle;
	private JTextField fieldPrice;

	// buttons in the first row for modifying 
	private JButton buttonAdd;
	private JButton buttonUpdate;
	private JButton buttonDelete;
	private JButton buttonExit;

	// buttons in the second row for navigating
	private JButton buttonFirst;
	private JButton buttonPrev;
	private JButton buttonNext;
	private JButton buttonLast;

	private boolean addNew;

	public BookPanel(String fileName)
	{
 		bookDB = new BookDB(fileName);
 		addNew = false; // set this to false by default
 		createComponents();

        // set the text filed value to display the current book
        Book thisBook = bookDB.getCurrent();
        displayBook(thisBook);
        enableButtons(true);
	}

    public JPanel getPanel()
    {
        return this.panel;
    }

	//create components
	private void createComponents()
  	{
  		// create the panel and use absolute layout
  		panel = new JPanel();
    	panel.setLayout(null);

    	// create lables and text fields
    	labelCode = new JLabel("Code:");
    	labelCode.setBounds(30,30,50,30);
    	fieldCode = new JTextField();
        fieldCode.setBounds(85,30,100,30);

        panel.add(labelCode);
        panel.add(fieldCode);


        labelTitle = new JLabel("Title:");
    	labelTitle.setBounds(30,70,50,30);
    	fieldTitle = new JTextField();
        fieldTitle.setBounds(85,70,300,30);

        panel.add(labelTitle);
        panel.add(fieldTitle);


        labelPrice = new JLabel("Price:");
    	labelPrice.setBounds(30,110,50,30);
    	fieldPrice = new JTextField();
        fieldPrice.setBounds(85,110,100,30);

        panel.add(labelPrice);
        panel.add(fieldPrice);


        // create modifying buttons
        buttonAdd = new JButton("Add");
        buttonAdd.addActionListener(this);
        buttonAdd.setBounds(70,155,70,35);
        panel.add(buttonAdd);

        buttonUpdate = new JButton("Update");
        buttonUpdate.addActionListener(this);
        buttonUpdate.setBounds(145,155,70,35);
        panel.add(buttonUpdate);

        buttonDelete = new JButton("Delete");
        buttonDelete.addActionListener(this);
        buttonDelete.setBounds(220,155,70,35);
        panel.add(buttonDelete);

        buttonExit = new JButton("Exit");
        buttonExit.addActionListener(this);
        buttonExit.setBounds(295,155,70,35);
        panel.add(buttonExit);


		// create navigating buttons
        buttonFirst = new JButton("First");
        buttonFirst.addActionListener(this);
        buttonFirst.setBounds(85,215,60,35);
        panel.add(buttonFirst);

        buttonPrev = new JButton("Prev");
        buttonPrev.addActionListener(this);
        buttonPrev.setBounds(150,215,60,35);
        panel.add(buttonPrev);

        buttonNext = new JButton("Next");
        buttonNext.addActionListener(this);
        buttonNext.setBounds(215,215,60,35);
        panel.add(buttonNext);

        buttonLast = new JButton("Last");
        buttonLast.addActionListener(this);
        buttonLast.setBounds(280,215,60,35);
        panel.add(buttonLast);

        fieldCode.getDocument().addDocumentListener(this);
        fieldCode.addKeyListener(this);
        fieldTitle.getDocument().addDocumentListener(this);
        fieldTitle.addKeyListener(this);
        fieldPrice.getDocument().addDocumentListener(this);
        fieldPrice.addKeyListener(this);

   	}

   	private void displayBook(Book book)
   	{
   		if (book != null)
   		{
   			fieldCode.setText(book.getCode());
   			fieldTitle.setText(book.getTitle());
   			String p = String.format( "$%,.2f", book.getPrice());
   			fieldPrice.setText(p);
   		}
   	}

   	private void cleanFields()
   	{
   		fieldCode.setText("");
   		fieldTitle.setText("");
   		fieldPrice.setText("");
   	}

   	public void actionPerformed(ActionEvent e) 
   	{
    	if(e.getSource() == buttonAdd)
        {
            cleanFields();
            enableButtons(false);
            addNew = true; // set this to true thus we know to add a new book when click Update.
        }
        else if (e.getSource() == buttonUpdate)
        {
            // TODO: check invalid input here if time permits
            String code = fieldCode.getText();
            String title = fieldTitle.getText();
            String s = fieldPrice.getText();
            if (s.charAt(0) == '$')
            {
                s = s.substring(1);
            }
            //s = s.replaceAll("$","");
            double price = Double.parseDouble(s);

            Book book = new Book(code,title,price);

            if (addNew)
            {
            	this.bookDB.addBook(book);
            	addNew = false; //set back to false
            }
            else
            {
            	this.bookDB.updateBook(book);
            }
            Book thisBook = bookDB.getCurrent();
            displayBook(thisBook);
            enableButtons(true);

        }
        else if (e.getSource() == buttonDelete)
        {
            this.bookDB.deleteBook(this.bookDB.getCurrent());
            Book thisBook = bookDB.getCurrent();
            displayBook(thisBook);
            enableButtons(true);
        }
        else if (e.getSource() == buttonExit)
        {
            System.exit(0);  // quit all the ongoing actions and exit without saving
        }
        else if (e.getSource() == buttonFirst)
        {
            Book thisBook = this.bookDB.moveFirst();
        	displayBook(thisBook);
            enableButtons(true);
        }
        else if (e.getSource() == buttonPrev)
        {
            Book thisBook = this.bookDB.movePrevious();
            displayBook(thisBook);
            enableButtons(true);
        }
        else if (e.getSource() == buttonNext)
        {
            Book thisBook = this.bookDB.moveNext();
            displayBook(thisBook);
            enableButtons(true);
        }
        else if (e.getSource() == buttonLast)
        {
            Book thisBook = this.bookDB.moveLast();
            displayBook(thisBook);
            enableButtons(true);
        }
    }

    private void enableButtons(boolean flag1)
    {
    	boolean flag2 = false;
 		if (!flag1)
 		{
 			flag2 = true;
 		}

        buttonUpdate.setEnabled(flag2);
		buttonAdd.setEnabled(flag1);
		buttonDelete.setEnabled(flag1);
		buttonFirst.setEnabled(flag1);
		buttonPrev.setEnabled(flag1);
		buttonNext.setEnabled(flag1);
		buttonLast.setEnabled(flag1);
    }

	public void keyPressed(KeyEvent e)
	{
		int keyCode = e.getKeyCode();
 		if (keyCode == KeyEvent.VK_ESCAPE) // cancel any add or update operation
 		{
 			fieldCode.requestFocus();
            Book thisBook = bookDB.getCurrent();
        	displayBook(thisBook);
        	enableButtons(true);
        	addNew = false;
 		}
	}
    public void keyReleased(KeyEvent e)
    {

    }

    public void keyTyped(KeyEvent e)
    {

    }

    @Override
	public void insertUpdate(DocumentEvent e)
	{
		enableButtons(false);
	}
    @Override
	public void removeUpdate(DocumentEvent e)
	{
		enableButtons(false);
	}
    @Override
	public void changedUpdate(DocumentEvent e)
	{
		enableButtons(false);
	}
}
