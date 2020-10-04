package loginexample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Home extends JFrame implements ActionListener
{
	JButton btn1, btn2;
	
	public void showHome()
	{
		super.setTitle("HOME");
		super.setBounds(200, 200, 700, 600);
		super.setResizable(false);
		
		btn1 = new JButton("Signup");
		btn1.setBounds(200, 200, 100, 30);
		super.add(btn1);
		btn1.addActionListener(this);
		
		btn2 = new JButton("Login");
		btn2.setBounds(320, 200, 100, 30);
		super.add(btn2);
		btn2.addActionListener(this);
		
		super.setLayout(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == btn1)
		{
			Signup signup = new Signup();
			signup.showSignupPage();
			super.dispose(); // close current frame
		}
		else if(e.getSource() == btn2)
		{
			Login login = new Login();
			login.showLoginPage();
			super.dispose();
		}
	}
}