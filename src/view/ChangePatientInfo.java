package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import control.PatientAction;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

import model.PatientFun;

public class ChangePatientInfo extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPatientName;
	private JTextField textFieldPatientNumber;
	private JComboBox comboBoxBedNumber;
	private JComboBox<String> comboBoxMedicalType;
	private JComboBox comboBoxInHospitalTime;
	private JComboBox comboBoxOutHospitalTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePatientInfo frame = new ChangePatientInfo();
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
	public ChangePatientInfo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/yz.jpg").getImage());
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int widthMargin = (screenWidth - 510)/2;
		int heightMargin = (screenHeight - 400)/2;
		setBounds(widthMargin, heightMargin, 500, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u75C5\u4EBA\u4FE1\u606F\u4FEE\u6539\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 64, 64)));
		panel.setBackground(Color.WHITE);
		panel.setAlignmentY(0.0f);
		panel.setAlignmentX(0.0f);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("病人姓名");
		label.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label.setBounds(106, 37, 85, 21);
		panel.add(label);
		
		textFieldPatientName = new JTextField();
		textFieldPatientName.setText(PatientList.patientName);
		textFieldPatientName.setFont(new Font("微软雅黑", Font.BOLD, 18));
		textFieldPatientName.setColumns(10);
		textFieldPatientName.setBounds(217, 37, 114, 24);
		panel.add(textFieldPatientName);
		
		JLabel label_1 = new JLabel("住院号");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_1.setBounds(116, 71, 75, 18);
		panel.add(label_1);
		
		textFieldPatientNumber = new JTextField();
		textFieldPatientNumber.setText(PatientList.patientNumebr);
		textFieldPatientNumber.setEditable(false);
		textFieldPatientNumber.setFont(new Font("宋体", Font.BOLD, 15));
		textFieldPatientNumber.setColumns(10);
		textFieldPatientNumber.setBounds(217, 68, 114, 24);
		panel.add(textFieldPatientNumber);
		
		JLabel label_2 = new JLabel("病床区号");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_2.setBounds(106, 102, 85, 18);
		panel.add(label_2);
		
		comboBoxBedNumber = new JComboBox();
		comboBoxBedNumber.addItem(PatientList.bedNumber);
		comboBoxBedNumber.setFont(new Font("宋体", Font.BOLD, 15));
		comboBoxBedNumber.setEditable(true);
		comboBoxBedNumber.setBackground(Color.WHITE);
		comboBoxBedNumber.setBounds(217, 101, 114, 24);
		panel.add(comboBoxBedNumber);
		
		JLabel label_3 = new JLabel("医保类型");
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_3.setBounds(106, 139, 85, 18);
		panel.add(label_3);
		
		comboBoxMedicalType = new JComboBox();
		comboBoxMedicalType.addItem(PatientList.medicalType);
		PatientFun patientFun = new PatientFun();
		//还要筛选出除了这个医保类型外的其他医保类型
//		String string[];	
//		try {
//			string = patientFun.selectMedicalType();
//			comboBoxMedicalType.setModel(new DefaultComboBoxModel<String>(string));
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
		comboBoxMedicalType.setEditable(true);
		comboBoxMedicalType.setBackground(Color.WHITE);
		comboBoxMedicalType.setBounds(217, 138, 114, 24);
		panel.add(comboBoxMedicalType);
		
		JLabel label_4 = new JLabel("入院时间");
		label_4.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_4.setBounds(106, 182, 85, 21);
		panel.add(label_4);
		
		comboBoxInHospitalTime = new JComboBox();
		comboBoxInHospitalTime.addItem(PatientList.inHospitalTime);
		comboBoxInHospitalTime.setFont(new Font("宋体", Font.BOLD, 15));
		comboBoxInHospitalTime.setEditable(true);
		comboBoxInHospitalTime.setBackground(Color.WHITE);
		comboBoxInHospitalTime.setBounds(217, 182, 114, 24);
		panel.add(comboBoxInHospitalTime);
		
		JLabel label_5 = new JLabel("出院时间");
		label_5.setFont(new Font("微软雅黑", Font.BOLD, 18));
		label_5.setBounds(106, 224, 85, 24);
		panel.add(label_5);
		
		comboBoxOutHospitalTime = new JComboBox();
		comboBoxOutHospitalTime.addItem(PatientList.outHospitalTime);
		comboBoxOutHospitalTime.setFont(new Font("宋体", Font.BOLD, 15));
		comboBoxOutHospitalTime.setEditable(true);
		comboBoxOutHospitalTime.setBackground(Color.WHITE);
		comboBoxOutHospitalTime.setBounds(217, 226, 114, 24);
		panel.add(comboBoxOutHospitalTime);
		
		JPanel panel_1 = new JPanel();
		panel_1.setAlignmentY(0.0f);
		panel_1.setAlignmentX(0.0f);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnConfirm = new JButton("确定");
		btnConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String patientName = textFieldPatientName.getText();
				String patientNumebr = textFieldPatientNumber.getText();
				String bedNumber = (String) comboBoxBedNumber.getSelectedItem();
				String medicalType = (String) comboBoxMedicalType.getSelectedItem();
				String inHospitalTime = (String) comboBoxInHospitalTime.getSelectedItem();
				String outHospitalTime = (String) comboBoxOutHospitalTime.getSelectedItem();
				//判断是否是合法的输入且无空值
				if(patientName.length() != 0 && patientNumebr.length() != 0 && bedNumber.length() != 0 && medicalType.length() != 0 && inHospitalTime.length() != 0 && outHospitalTime.length() != 0){
					PatientAction patientAction = new PatientAction();
					//修改操作
					try {
						patientAction.updatePatient(patientName, patientNumebr, bedNumber, medicalType, inHospitalTime, outHospitalTime);
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
		btnConfirm.setMargin(new Insets(2, 10, 2, 10));
		btnConfirm.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnConfirm.setBackground(Color.WHITE);
		btnConfirm.setAlignmentY(0.0f);
		panel_1.add(btnConfirm);
		
		JButton btnCancel = new JButton("取消");
		btnCancel.setMargin(new Insets(2, 10, 2, 10));
		btnCancel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnCancel.setBackground(Color.WHITE);
		panel_1.add(btnCancel);
	}
}
