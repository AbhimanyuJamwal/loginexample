package loginexample;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*; // import jdbc api
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Signup extends JFrame implements ActionListener
{
	JTextField tf1, tf2, tf3;
	JTextArea area;
	JScrollPane pane;
	JPasswordField pf;
	JButton btn;
	
	public void showSignupPage()
	{
		super.setTitle("SIGNUP");
		super.setBounds(200, 200, 700, 600);
		super.setResizable(false);
		
		// create a font
		Font font = new Font("", Font.BOLD, 16);
		
		tf1 = new JTextField();
		tf1.setBounds(100, 100, 300, 30);
		super.add(tf1);
		tf1.setFont(font);
		

		tf2 = new JTextField();
		tf2.setBounds(100, 140, 300, 30);
		super.add(tf2);
		tf2.setFont(font);

		tf3 = new JTextField();
		tf3.setBounds(100, 180, 300, 30);
		super.add(tf3);
		tf3.setFont(font);


		pf = new JPasswordField();
		pf.setBounds(100, 220, 300, 30);
		super.add(pf);
		pf.setFont(font);

		
		area = new JTextArea();
		pane = new JScrollPane(area);
		pane.setBounds(100, 260, 300, 250);
		super.add(pane);
		area.setFont(font);

		btn = new JButton("Signup");
		btn.setBounds(100, 520, 100, 30);
		super.add(btn);
		btn.addActionListener(this);
		
		super.setLayout(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try {
			if(e.getSource() == btn)
			{
				String name = tf1.getText(), email = tf2.getText(),
						mobile = tf3.getText(), 
						password = new String(pf.getPassword(), 0, pf.getPassword().length),
						address = area.getText();
				
				// load driver class
				Class.forName("com.mysql.jdbc.Driver");
				
				// get connection
				Connection co = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/register", "root", "root");
				
				// get statement
				Statement st = co.createStatement();
				
				// execute query
				st.executeUpdate
				("insert into userinfo values('"+name
						+"','"+email+"','"+mobile+"','"+password+"','"+address+"')");
				
				// close connection
				co.close();
				
				JOptionPane.showMessageDialog(null, "Hello dear "
				+name+" your data has been saved in database\n"
						+ "you will be moved to home page now");
				
				// goto home page
				Home home = new Home();
				home.showHome();
				super.dispose();
			}
		} 
		catch (Exception e1) 
		{
			System.out.println("error in signup "+e1);
		}
	}
}
