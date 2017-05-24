package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import control.ChangeCheck;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePassword extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldOld;
	private JPasswordField passwordFieldNew1;
	private JPasswordField passwordFieldNew2;
	private JButton confirm;
	private JButton cancle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword();
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
	public ChangePassword() {
		setResizable(false);
		setTitle("用户密码修改");
		setIconImage(new ImageIcon("img/yz.jpg").getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int widthMargin = (screenWidth - 460)/2;
		int heightMargin = (screenHeight - 310)/2;
		setBounds(widthMargin, heightMargin, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setFont(new Font("微软雅黑", Font.BOLD, 24));
		panel.setBackground(SystemColor.control);
		panel.setBorder(new TitledBorder(null, "\u5BC6\u7801\u4FEE\u6539\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel1 = new JLabel("用户名：");
		lblNewLabel1.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel1.setBounds(136, 56, 60, 18);
		panel.add(lblNewLabel1);
		
		JLabel lblNewLabel2 = new JLabel("请输入原密码：");
		lblNewLabel2.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel2.setBounds(90, 87, 105, 18);
		panel.add(lblNewLabel2);
		
		JLabel lblNewLabel3 = new JLabel("再次输入新密码：");
		lblNewLabel3.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel3.setBounds(76, 149, 120, 18);
		panel.add(lblNewLabel3);
		
		JLabel lblNewLabel4 = new JLabel("请输入新密码：");
		lblNewLabel4.setFont(new Font("微软雅黑", Font.BOLD, 14));
		lblNewLabel4.setBounds(90, 118, 106, 18);
		panel.add(lblNewLabel4);
		
		textFieldUsername = new JTextField(Mainwindow.USERNAME);
		textFieldUsername.setEditable(false);
		textFieldUsername.setBounds(210, 54, 111, 24);
		panel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		passwordFieldOld = new JPasswordField();
		passwordFieldOld.setBounds(209, 85, 112, 24);
		panel.add(passwordFieldOld);
		
		passwordFieldNew1 = new JPasswordField();
		passwordFieldNew1.setBounds(209, 116, 112, 24);
		panel.add(passwordFieldNew1);
		
		passwordFieldNew2 = new JPasswordField();
		passwordFieldNew2.setBounds(209, 147, 112, 24);
		panel.add(passwordFieldNew2);
		
		confirm = new JButton("确定");
		confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = Mainwindow.USERNAME;
				String oldpassword = new String(passwordFieldOld.getPassword());
				String newpassword1 = new String(passwordFieldNew1.getPassword());
				String newpassword2 = new String(passwordFieldNew2.getPassword());
				if(newpassword1.equals(newpassword2)){
					String newpassword =  new String(passwordFieldNew1.getPassword());
					ChangeCheck changeCheck = new ChangeCheck();
					changeCheck.ischange(username,oldpassword,newpassword);
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "两次输入的密码不一致！");
				}
				
			}
		});
		confirm.setAlignmentY(Component.TOP_ALIGNMENT);
		confirm.setBackground(Color.WHITE);
		confirm.setHorizontalTextPosition(SwingConstants.CENTER);
		confirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		confirm.setMargin(new Insets(2, 0, 2, 0));
		confirm.setBounds(105, 192, 71, 27);
		panel.add(confirm);
		
		cancle = new JButton("取消");
		cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancle.setAlignmentY(Component.TOP_ALIGNMENT);
		cancle.setBackground(Color.WHITE);
		cancle.setHorizontalTextPosition(SwingConstants.CENTER);
		cancle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cancle.setMargin(new Insets(2, 0, 2, 0));
		cancle.setBounds(231, 192, 71, 27);
		panel.add(cancle);
	}
}
