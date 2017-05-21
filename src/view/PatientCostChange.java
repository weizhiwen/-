package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import control.CostAction;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class PatientCostChange extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPatientName;
	private JTextField textFieldPatientNumber;
	private JTextField textFieldItemName;
	private JTextField textFieldCostType;
	private JTextField textFieldKind;
	private JTextField textFieldAmount;
	private JTextField textFieldUnit;
	private JTextField textFieldPrePrice;
	private JTextField textFieldCost;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientCostChange frame = new PatientCostChange();
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
	public PatientCostChange() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/yz.jpg").getImage());
		setBounds(100, 100, 427, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "\u4F7F\u7528\u9879\u76EE\u4FEE\u6539\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("病人姓名：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel.setBounds(80, 30, 81, 18);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("住院号：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_1.setBounds(80, 69, 81, 18);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("项目名称：");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_2.setBounds(80, 104, 81, 18);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("费用类别：");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_3.setBounds(80, 137, 81, 18);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("类型：");
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_4.setBounds(80, 170, 81, 18);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("数量：");
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_5.setBounds(80, 201, 81, 18);
		panel.add(lblNewLabel_5);
		
		JLabel label = new JLabel("单位：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label.setBounds(80, 232, 81, 18);
		panel.add(label);
		
		JLabel label_1 = new JLabel("单价：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label_1.setBounds(80, 263, 81, 18);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("费用：");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label_2.setBounds(80, 304, 81, 18);
		panel.add(label_2);
		
		textFieldPatientName = new JTextField();
		textFieldPatientName.setText(PatientCostManage.PATIENTNAME);
		textFieldPatientName.setEditable(false);
		textFieldPatientName.setBounds(175, 27, 136, 24);
		panel.add(textFieldPatientName);
		textFieldPatientName.setColumns(10);
		
		textFieldPatientNumber = new JTextField();
		textFieldPatientNumber.setText(PatientCostManage.PATIENTNUMBER);
		textFieldPatientNumber.setEditable(false);
		textFieldPatientNumber.setColumns(10);
		textFieldPatientNumber.setBounds(175, 66, 136, 24);
		panel.add(textFieldPatientNumber);
		
		textFieldItemName = new JTextField();
		textFieldItemName.setText(PatientCostManage.ITEMNAME);
		textFieldItemName.setEditable(false);
		textFieldItemName.setColumns(10);
		textFieldItemName.setBounds(175, 100, 136, 24);
		panel.add(textFieldItemName);
		
		textFieldCostType = new JTextField();
		textFieldCostType.setText(PatientCostManage.COSTTYPE);
		textFieldCostType.setEditable(false);
		textFieldCostType.setColumns(10);
		textFieldCostType.setBounds(175, 131, 136, 24);
		panel.add(textFieldCostType);
		
		textFieldKind = new JTextField();
		textFieldKind.setText(PatientCostManage.KIND);
		textFieldKind.setEditable(false);
		textFieldKind.setColumns(10);
		textFieldKind.setBounds(175, 167, 136, 24);
		panel.add(textFieldKind);
		
		textFieldAmount = new JTextField();
		textFieldAmount.setText(PatientCostManage.AMOUNT);
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(175, 198, 136, 24);
		panel.add(textFieldAmount);
		
		textFieldUnit = new JTextField();
		textFieldUnit.setText(PatientCostManage.UNIT);
		textFieldUnit.setEditable(false);
		textFieldUnit.setColumns(10);
		textFieldUnit.setBounds(175, 229, 136, 24);
		panel.add(textFieldUnit);
		
		textFieldPrePrice = new JTextField();
		textFieldPrePrice.setText(PatientCostManage.PREPRICE);
		textFieldPrePrice.setEditable(false);
		textFieldPrePrice.setColumns(10);
		textFieldPrePrice.setBounds(175, 260, 136, 24);
		panel.add(textFieldPrePrice);
		
		textFieldCost = new JTextField();
		textFieldCost.setText(PatientCostManage.COST);
		textFieldCost.setEditable(false);
		textFieldCost.setColumns(10);
		textFieldCost.setBounds(175, 301, 136, 24);
		panel.add(textFieldCost);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(60);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton button = new JButton("确定");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				//获取界面的值，住院号，项目名称，更改后的数量值
				String patientNumber = textFieldPatientNumber.getText();
				String itemName = textFieldItemName.getText();
				String amount = textFieldAmount.getText();
				CostAction costAction = new CostAction();
				try {
					//注意这个地方，不单单是改个数量呀，该项的费用也要改变
					costAction.changeCostAmount(patientNumber, itemName, amount);
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setFont(new Font("微软雅黑", Font.BOLD, 16));
		button.setBackground(Color.WHITE);
		panel_1.add(button);
		
		JButton button_1 = new JButton("取消");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		button_1.setBackground(Color.WHITE);
		panel_1.add(button_1);
	}

}
