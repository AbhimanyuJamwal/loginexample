package loginexample;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener
{
	JTextField tf1;
	JPasswordField pf;
	JButton btn;
	
	public void showLoginPage()
	{
		super.setTitle("LOGIN");
		super.setBounds(200, 200, 700, 600);
		super.setResizable(false);
		
		// create a font
		Font font = new Font("", Font.BOLD, 16);
		
		tf1 = new JTextField();
		tf1.setBounds(100, 100, 300, 30);
		super.add(tf1);
		tf1.setFont(font);
		
		pf = new JPasswordField();
		pf.setBounds(100, 220, 300, 30);
		super.add(pf);
		pf.setFont(font);

		btn = new JButton("Login");
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
				String email = tf1.getText(), 
						password = new String(pf.getPassword(), 0, pf.getPassword().length);
				
				// load driver class
				Class.forName("com.mysql.jdbc.Driver");
				
				// get connection
				Connection co = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/register", "root", "root");
				
				// get statement
				Statement st = co.createStatement();
				
				// execute query
				ResultSet rs = st.executeQuery
				("select * from login where email='"+email+"' and password='"+password+"'");
				
				// check if there is a record in result-set
				if(rs.next())
				{
					JOptionPane.showMessageDialog
					(null, "Name is "+rs.getString("name")+"\n"
							+ "Mobile is "+rs.getString("mobile")+"\n"
									+ "Address is "+rs.getString("address"));
				}
				else
				{
					JOptionPane.showMessageDialog
					(null, "Sorry, your information not available in our database\n"
							+ "you will be redirected to home page");
					
				}
				
				// close connection
				co.close();
								
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
