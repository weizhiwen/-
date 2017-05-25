package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.LoginCheck;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginDialog extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButtonLogin;
	private JButton btnNewButtonCancel;
	private JTextField textFieldUserName;
	private JPasswordField passwordField;
	public static String USERNAME;
	public static String PASSWORD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginDialog frame = new LoginDialog();
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
	public LoginDialog() {
		setTitle("用户登陆-长大医疗系统");
		setIconImage(new ImageIcon("img/icon.jpg").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int widthMargin = (screenWidth - 510)/2;
		int heightMargin = (screenHeight - 310)/2;
		setBounds(widthMargin, heightMargin, 510, 380);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		ImageIcon bg = new ImageIcon("img/logo.jpg");
		JLabel label = new JLabel(bg);
		label.setPreferredSize(new Dimension(500, 150));
		contentPane.add(label, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel("用户名：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_1.setBounds(127, 30, 72, 18);
		panel.add(label_1);
		
		textFieldUserName = new JTextField();
		textFieldUserName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textFieldUserName.setBackground(Color.PINK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				textFieldUserName.setBackground(Color.WHITE);
			}
		});
		textFieldUserName.setFont(new Font("微软雅黑", Font.BOLD, 16));
		textFieldUserName.setBounds(226, 29, 117, 24);
		panel.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		JLabel label_2 = new JLabel("密码：");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_2.setBounds(127, 73, 72, 18);
		panel.add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					//获取界面上的输入信息
					USERNAME = textFieldUserName.getText();
					PASSWORD = new String(passwordField.getPassword());
					//判断用户是否已经填写完整信息
					if(USERNAME.length() <= 0 || PASSWORD.length() <= 0){
						JOptionPane.showMessageDialog(null, "用户名和密码填写不完整");
					}else{
						LoginCheck loginCheck = new LoginCheck();
						try {
							if(loginCheck.isLoginSuccess(USERNAME, PASSWORD)){
								JOptionPane.showMessageDialog(null, "登录成功");
								Mainwindow mainwindow = new Mainwindow();
								mainwindow.setVisible(true);
								dispose();
							}
							else {
								JOptionPane.showMessageDialog(null, "登录失败");
							}
						} catch (HeadlessException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}	
					}
				}
			}
		});
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passwordField.setBackground(Color.PINK);
			}
			@Override
			public void focusLost(FocusEvent e) {
				passwordField.setBackground(Color.WHITE);
			}
		});
		passwordField.setFont(new Font("微软雅黑", Font.BOLD, 16));
		passwordField.setBounds(226, 72, 117, 24);
		panel.add(passwordField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(80);
		flowLayout.setVgap(10);
		panel_1.setPreferredSize(new Dimension(510, 50));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btnNewButtonLogin = new JButton("登录");
		btnNewButtonLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//获取界面上的输入信息
				USERNAME = textFieldUserName.getText();
				PASSWORD = new String(passwordField.getPassword());
				//判断用户是否已经填写完整信息
				if(USERNAME.length() <= 0 || PASSWORD.length() <= 0){
					JOptionPane.showMessageDialog(null, "用户名和密码填写不完整");
				}else{
					LoginCheck loginCheck = new LoginCheck();
					try {
						if(loginCheck.isLoginSuccess(USERNAME, PASSWORD)){
							JOptionPane.showMessageDialog(null, "登录成功");
							Mainwindow mainwindow = new Mainwindow();
							mainwindow.setVisible(true);
							dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "登录失败");
						}
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}	
				}
			}
		});
		btnNewButtonLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButtonLogin.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnNewButtonLogin.setBackground(Color.WHITE);
		panel_1.add(btnNewButtonLogin);
		
		btnNewButtonCancel = new JButton("取消");
		btnNewButtonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		btnNewButtonCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButtonCancel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnNewButtonCancel.setBackground(Color.WHITE);
		panel_1.add(btnNewButtonCancel);
	}
}
