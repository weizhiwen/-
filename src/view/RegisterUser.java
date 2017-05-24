package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import control.ChangeCheck;
import control.RegisterCheck;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterUser extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordField1;
	private JPasswordField passwordField2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUser frame = new RegisterUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterUser() {
		setTitle("注册新用户");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/yz.jpg").getImage());
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int widthMargin = (screenWidth - 460)/2;
		int heightMargin = (screenHeight - 310)/2;
		setBounds(widthMargin, heightMargin, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7528\u6237\u6CE8\u518C\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, Color.MAGENTA));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("请输入您的姓名：");
		lblNewLabel.setBounds(78, 58, 120, 18);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("请输入您的密码：");
		label.setBounds(78, 92, 120, 18);
		panel.add(label);
		
		JLabel label_1 = new JLabel("再次输入您的密码：");
		label_1.setBounds(62, 128, 136, 18);
		panel.add(label_1);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(211, 55, 120, 24);
		panel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordField1 = new JPasswordField();
		passwordField1.setBounds(212, 89, 119, 24);
		panel.add(passwordField1);
		
		passwordField2 = new JPasswordField();
		passwordField2.setBounds(212, 125, 119, 24);
		panel.add(passwordField2);
		
		JButton Confirm = new JButton("确定");
		Confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = textFieldUsername.getText();
				String newpassword1 = new String(passwordField1.getPassword());
				String newpassword2 = new String(passwordField2.getPassword());
				if(newpassword1.equals(newpassword2)){
					String password =  new String(passwordField1.getPassword());
					RegisterCheck registerCheck = new RegisterCheck();
					registerCheck.isAdd(username, password);
				}else{
					JOptionPane.showMessageDialog(null, "两次输入的密码不一致！");
				}
			}
		});
		Confirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Confirm.setMargin(new Insets(2, 0, 2, 0));
		Confirm.setHorizontalTextPosition(SwingConstants.CENTER);
		Confirm.setBackground(Color.WHITE);
		Confirm.setAlignmentY(0.0f);
		Confirm.setBounds(76, 186, 86, 27);
		panel.add(Confirm);
		
		JButton Cancle = new JButton("取消");
		Cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		Cancle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Cancle.setMargin(new Insets(2, 0, 2, 0));
		Cancle.setHorizontalTextPosition(SwingConstants.CENTER);
		Cancle.setBackground(Color.WHITE);
		Cancle.setAlignmentY(0.0f);
		Cancle.setBounds(245, 186, 86, 27);
		panel.add(Cancle);
	}

}
