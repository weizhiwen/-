package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import javax.swing.border.TitledBorder;

import control.PatientAction;
import model.PatientFun;
import model.RoomFun;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

public class PatientInfoDlog extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPatientName;
	private JTextField textFieldPatientNumber;
	private JComboBox comboBoxBedNumber;
	private JComboBox comboBoxMedicalType;
	private JComboBox comboBoxInHospitalTime;
	private JComboBox comboBoxOutHospitalTime;
	
	private RoomFun roomFun = new RoomFun();
	private PatientFun patientFun = new PatientFun();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientInfoDlog frame = new PatientInfoDlog();
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
	public PatientInfoDlog() {
		setTitle("病人信息录入");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/yz.jpg").getImage());
//		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
//		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
//		int widthMargin = (screenWidth - 510)/2;
//		int heightMargin = (screenHeight - 400)/2;
		//窗体居中的另一种简单方法
		setBounds(0, 0, 500, 380);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u75C5\u4EBA\u4FE1\u606F\u5F55\u5165\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("病人姓名");
		label.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label.setBounds(106, 37, 85, 21);
		panel.add(label);
		
		textFieldPatientName = new JTextField();
		textFieldPatientName.setFont(new Font("微软雅黑", Font.BOLD, 18));
		textFieldPatientName.setBounds(217, 37, 114, 24);
		panel.add(textFieldPatientName);
		textFieldPatientName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("住院号");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lblNewLabel.setBounds(116, 71, 75, 18);
		panel.add(lblNewLabel);
		
		//设置当前的时间为病人的住院号，如20170427174420 年月日时分秒
		//利用java的时间函数设置文本框
		String patientNumber = patientFun.getPatientNumber();
		textFieldPatientNumber = new JTextField(patientNumber);		
		textFieldPatientNumber.setFont(new Font("宋体", Font.BOLD, 15));
		textFieldPatientNumber.setColumns(10);
		textFieldPatientNumber.setBounds(217, 68, 114, 24);
		panel.add(textFieldPatientNumber);
		
		JLabel lblNewLabel_1 = new JLabel("病床区号");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lblNewLabel_1.setBounds(106, 102, 85, 18);
		panel.add(lblNewLabel_1);
		
		comboBoxBedNumber = new JComboBox();
		comboBoxBedNumber.setEditable(true);
		String bedNumber[];
		try {
			bedNumber = roomFun.selectRoomBed();
			comboBoxBedNumber.setModel(new DefaultComboBoxModel<String>(bedNumber));
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		comboBoxBedNumber.setFont(new Font("宋体", Font.BOLD, 15));
		comboBoxBedNumber.setBackground(Color.WHITE);
		comboBoxBedNumber.setBounds(217, 101, 114, 24);
		panel.add(comboBoxBedNumber);
		
		JLabel lblNewLabel_2 = new JLabel("医保类型");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lblNewLabel_2.setBounds(106, 139, 85, 18);
		panel.add(lblNewLabel_2);
		
		comboBoxMedicalType = new JComboBox();
		comboBoxMedicalType.setEditable(true);
		//从数据库中遍历医保类型，减少用户的直接输入
		try {
			String medicaltypes[] = patientFun.selectMedicalType();
			comboBoxMedicalType.setModel(new DefaultComboBoxModel<String>(medicaltypes));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		comboBoxMedicalType.setBackground(Color.WHITE);
		comboBoxMedicalType.setBounds(217, 138, 114, 24);
		panel.add(comboBoxMedicalType);
		
		JLabel lblNewLabel_3 = new JLabel("入院时间");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lblNewLabel_3.setBounds(106, 182, 85, 21);
		panel.add(lblNewLabel_3);
		
		comboBoxInHospitalTime = new JComboBox();
		comboBoxInHospitalTime.setFont(new Font("宋体", Font.BOLD, 15));
		comboBoxInHospitalTime.setEditable(true);
		String inHospitalTime = patientFun.getDate();
		comboBoxInHospitalTime.addItem(inHospitalTime);
		comboBoxInHospitalTime.setBackground(Color.WHITE);
		comboBoxInHospitalTime.setBounds(217, 182, 114, 24);
		panel.add(comboBoxInHospitalTime);
		
		JLabel label_1 = new JLabel("出院时间");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_1.setBounds(106, 224, 85, 24);
		panel.add(label_1);
		
		comboBoxOutHospitalTime = new JComboBox();
		comboBoxOutHospitalTime.setEditable(true);
		comboBoxOutHospitalTime.setFont(new Font("宋体", Font.BOLD, 15));
		comboBoxOutHospitalTime.setBackground(Color.WHITE);
		comboBoxOutHospitalTime.setBounds(217, 226, 114, 24);
		panel.add(comboBoxOutHospitalTime);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(100);
		panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnConfirm = new JButton("确定");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//获取界面值
				String patientName = textFieldPatientName.getText();
				String patientNumebr = textFieldPatientNumber.getText();
				String bedNumber = (String) comboBoxBedNumber.getSelectedItem();
				String medicalType = (String) comboBoxMedicalType.getSelectedItem();
				String inHospitalTime = (String) comboBoxInHospitalTime.getSelectedItem();
				String outHospitalTime = (String) comboBoxOutHospitalTime.getSelectedItem();
				//判断是否是合法的输入且无空值
				if(patientName.length() != 0 && patientNumebr.length() != 0 && bedNumber.length() != 0 && medicalType.length() != 0 && inHospitalTime.length() != 0 && outHospitalTime.length() != 0){
					PatientAction patientAction = new PatientAction();
					try {
						patientAction.addPatient(patientName, patientNumber, bedNumber, medicalType, inHospitalTime, outHospitalTime);
						dispose();
					} catch (HeadlessException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(contentPane, "输入病人信息不完整！");
				}
			}
		});
		
		btnConfirm.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnConfirm.setMargin(new Insets(2, 10, 2, 10));
		btnConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfirm.setBackground(Color.WHITE);
		btnConfirm.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_1.add(btnConfirm);
		
		JButton buttonCancel = new JButton("取消");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		buttonCancel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		buttonCancel.setMargin(new Insets(2, 10, 2, 10));
		buttonCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonCancel.setBackground(Color.WHITE);
		panel_1.add(buttonCancel);
	}
}
