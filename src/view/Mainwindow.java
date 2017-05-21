package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Cursor;

public class Mainwindow extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel label;
	
	//定义静态常量，方便其他窗体使用
	public static String username = null;
	public static String password = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainwindow frame = new Mainwindow();
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
	public Mainwindow() {
		username = LoginDialog.username;
		password = LoginDialog.password;
		setResizable(false);
		setTitle("长江大学门诊住院费用管理系统");
		setIconImage(new ImageIcon("img/yz.jpg").getImage());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int widthMargin = (screenWidth - 766)/2;
		int heightMargin = (screenHeight - 620)/2;
		setBounds(widthMargin, heightMargin, 766, 610);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		setJMenuBar(menuBar);
		
		JMenu menu1 = new JMenu("病人信息");
		menu1.setMargin(new Insets(5, 20, 5, 5));
		menu1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		menuBar.add(menu1);
		
		JMenuItem menuItem1_1 = new JMenuItem("录入病人信息");
		menuItem1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PatientInfoDlog patientInfoDlog = new PatientInfoDlog();
				patientInfoDlog.setVisible(true);
			}
		});
		menuItem1_1.setOpaque(true);
		menuItem1_1.setBackground(Color.WHITE);
		menuItem1_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		menu1.add(menuItem1_1);
		
		JMenuItem menuItem1_2 = new JMenuItem("维护病人信息");
		menuItem1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PatientList patientList = new PatientList();
				patientList.setVisible(true);
			}
		});
		menuItem1_2.setOpaque(true);
		menuItem1_2.setBackground(Color.WHITE);
		menuItem1_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		menu1.add(menuItem1_2);
		
		JMenu menu2 = new JMenu("病人出院费用处理");
		menu2.setMargin(new Insets(5, 5, 5, 5));
		menu2.setFont(new Font("微软雅黑", Font.BOLD, 18));
		menuBar.add(menu2);
		
		JMenuItem menuItem2_1 = new JMenuItem("出院费用录入");
		menuItem2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatientCostEnter patientCostEnter = new PatientCostEnter();
				patientCostEnter.setVisible(true);
			}
		});
		menuItem2_1.setBackground(Color.WHITE);
		menuItem2_1.setOpaque(true);
		menuItem2_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		menu2.add(menuItem2_1);
		
		JMenuItem menuItem2_2 = new JMenuItem("出院费用维护");
		menuItem2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatientCostManage patientCostManage = new PatientCostManage();
				patientCostManage.setVisible(true);
				//dispose();
			}
		});
		menuItem2_2.setBackground(Color.WHITE);
		menuItem2_2.setOpaque(true);
		menuItem2_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		menu2.add(menuItem2_2);
		
		JSeparator separator1 = new JSeparator();
		menu2.add(separator1);
		
		JMenuItem menuItem2_3 = new JMenuItem("出院费用清单查询打印");
		menuItem2_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PrintListDialog printListDialog = new PrintListDialog();
				printListDialog.setVisible(true);
			}
		});
		menuItem2_3.setBackground(Color.WHITE);
		menuItem2_3.setOpaque(true);
		menuItem2_3.setFont(new Font("微软雅黑", Font.BOLD, 15));
		menu2.add(menuItem2_3);
		
		JSeparator separator2 = new JSeparator();
		menu2.add(separator2);
		
		JMenuItem menuItem2_4 = new JMenuItem("出院费用发票查询打印");
		menuItem2_4.setBackground(Color.WHITE);
		menuItem2_4.setOpaque(true);
		menuItem2_4.setFont(new Font("微软雅黑", Font.BOLD, 15));
		menu2.add(menuItem2_4);
		
		JMenu menu3 = new JMenu("汇总统计");
		menu3.setHorizontalAlignment(SwingConstants.LEFT);
		menu3.setMargin(new Insets(5, 5, 5, 5));
		menu3.setFont(new Font("微软雅黑", Font.BOLD, 18));
		menuBar.add(menu3);
		
		JMenu menu4 = new JMenu("系统维护");
		menu4.setMargin(new Insets(5, 5, 5, 5));
		menu4.setFont(new Font("微软雅黑", Font.BOLD, 18));
		menuBar.add(menu4);
		
		JMenu menu4_1 = new JMenu("数据库维护");
		menu4_1.setOpaque(true);
		menu4_1.setBackground(Color.WHITE);
		menu4.add(menu4_1);
		menu4_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JMenuItem menuItem4_1_1 = new JMenuItem("备份数据库");
		menuItem4_1_1.setBackground(Color.WHITE);
		menu4_1.add(menuItem4_1_1);
		
		JMenuItem menuItem4_1_2 = new JMenuItem("恢复数据库");
		menuItem4_1_2.setBackground(Color.WHITE);
		menu4_1.add(menuItem4_1_2);
		
		JSeparator separator3 = new JSeparator();
		menu4.add(separator3);
		
		JMenu menu4_2 = new JMenu("数据字典维护");
		menu4_2.setOpaque(true);
		menu4_2.setBackground(Color.WHITE);
		menu4.add(menu4_2);
		menu4_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		
		JMenu menu4_2_1 = new JMenu("房间类型维护");
		menu4_2_1.setBackground(Color.WHITE);
		menu4_2.add(menu4_2_1);
		
		JMenuItem menuItem4_2_1_1 = new JMenuItem("维护房间类型");
		menu4_2_1.add(menuItem4_2_1_1);
		
		JMenuItem menuItem4_2_1_2 = new JMenuItem("批量导入房间类型");
		menu4_2_1.add(menuItem4_2_1_2);
		
		JMenu menu4_2_2 = new JMenu("病区床号维护");
		menu4_2_2.setBackground(Color.WHITE);
		menu4_2.add(menu4_2_2);
		
		JMenu menu4_2_3 = new JMenu("费用项目维护");
		menu4_2_3.setBackground(Color.WHITE);
		menu4_2.add(menu4_2_3);
		
		JMenu menu4_2_4 = new JMenu("具体费用维护");
		menu4_2_4.setBackground(Color.WHITE);
		menu4_2.add(menu4_2_4);
		
		JMenu menu5 = new JMenu("用户信息维护");
		menu5.setBackground(Color.WHITE);
		menu5.setMargin(new Insets(5, 5, 5, 5));
		menu5.setFont(new Font("微软雅黑", Font.BOLD, 18));
		menuBar.add(menu5);
		
		JMenuItem menuItem5_1 = new JMenuItem("修改密码");
		menuItem5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChangePassword changePassword = new ChangePassword();
				changePassword.setVisible(true);
				//dispose();
			}
		});
		
		menuItem5_1.setOpaque(true);
		menuItem5_1.setBackground(Color.WHITE);
		menuItem5_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		menu5.add(menuItem5_1);
		
		JMenuItem menuItem5_2 = new JMenuItem("注册新用户");
		menuItem5_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterUser registerUser = new RegisterUser();
				registerUser.setVisible(true);
				//dispose();
			}
		});
		menuItem5_2.setBackground(Color.WHITE);
		menuItem5_2.setOpaque(true);
		menuItem5_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		menu5.add(menuItem5_2);
		
		JMenu menu6 = new JMenu("退出系统");
		menu6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		menu6.setMargin(new Insets(5, 5, 5, 5));
		menu6.setFont(new Font("微软雅黑", Font.BOLD, 18));
		menuBar.add(menu6);
		contentPane = new JPanel();
		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.NORTH);
		
		ImageIcon bg = new ImageIcon("img/bg.jpg");
		label = new JLabel(bg);
		panel.add(label);
	}

}
