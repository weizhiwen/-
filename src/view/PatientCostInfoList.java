package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import control.CostAction;
import control.PatientAction;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PatientCostInfoList extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPatientName;
	private JTextField textFieldPatientNumber;
	private JTable tablePatientInfo;
	private JTable tablePatientCostDetail;
	
	private String patientName = null;
	private Object patient[][] = null;
	private String title1[] = new String[] {
			"序号", "病人姓名", "住院号", "病床区号", "医保类型", "住院时间", "出院时间"
		};

	private Object patientCost[][] = null;
	private String title2[] = new String[] {
			"序号", "项目名称", "费用类别", "类型", "数量", "单位", "单价", "费用"
		};
	
	private DefaultTableModel tableModel = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientCostInfoList frame = new PatientCostInfoList();
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
	public PatientCostInfoList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("img/yz.jpg").getImage());
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int widthMargin = (screenWidth - 960)/2;
		int heightMargin = (screenHeight - 820)/2;
		setBounds(widthMargin, heightMargin, 960, 820);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setPreferredSize(new Dimension(812, 70));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel label = new JLabel("请输入要查询的病人名字：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label.setBounds(154, 34, 198, 18);
		panel.add(label);
		
		textFieldPatientName = new JTextField();
		textFieldPatientName.setForeground(Color.RED);
		textFieldPatientName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				patientName = textFieldPatientName.getText();
			}
		});
		textFieldPatientName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
				{
					patientName = textFieldPatientName.getText();
					PatientAction patientAction = new PatientAction();
					try {
						patient = patientAction.getPatient(patientName);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					tablePatientInfo.setModel(new DefaultTableModel(patient, title1));
				}
			}
		});
		textFieldPatientName.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textFieldPatientName.setBounds(330, 31, 108, 24);
		panel.add(textFieldPatientName);
		textFieldPatientName.setColumns(10);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PatientAction patientAction = new PatientAction();
				try {
					patient = patientAction.getPatient(patientName);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				tablePatientInfo.setModel(new DefaultTableModel(patient, title1));
			}
		});
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("微软雅黑", Font.BOLD, 16));
		btnNewButton.setBounds(452, 30, 74, 27);
		panel.add(btnNewButton);
		
		textFieldPatientNumber = new JTextField();
		textFieldPatientNumber.setFont(new Font("微软雅黑", Font.BOLD, 16));
		textFieldPatientNumber.setEditable(false);
		textFieldPatientNumber.setBounds(573, 32, 125, 24);
		panel.add(textFieldPatientNumber);
		textFieldPatientNumber.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u75C5\u4EBA\u4FE1\u606F\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setForeground(Color.DARK_GRAY);
		scrollPane.setFont(new Font("微软雅黑", Font.BOLD, 18));
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		tablePatientInfo = new JTable();
		tablePatientInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int rowIndex = tablePatientInfo.getSelectedRow();
				String patientNumber = (String) tablePatientInfo.getModel().getValueAt(rowIndex, 2);
				textFieldPatientNumber.setText(patientNumber);
				CostAction costAction = new CostAction();
				try {
					patientCost = costAction.getPatientCost(patientNumber);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				tableModel = new DefaultTableModel(patientCost, title2);
				tablePatientCostDetail.setModel(tableModel);
			}
		});
		tablePatientInfo.setFont(new Font("微软雅黑", Font.BOLD, 16));
		tablePatientInfo.setRowHeight(20);
		tablePatientInfo.setModel(new DefaultTableModel(
		));
		tablePatientInfo.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tablePatientInfo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(812, 480));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(812, 50));
		panel_2.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 10));
		
		JButton buttonPreview = new JButton("打印预览");
		buttonPreview.setBackground(Color.WHITE);
		buttonPreview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonPreview.setFont(new Font("微软雅黑", Font.BOLD, 16));
		panel_3.add(buttonPreview);
		
		JButton buttonList = new JButton("打印清单");
		buttonList.setBackground(Color.WHITE);
		buttonList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonList.setFont(new Font("微软雅黑", Font.BOLD, 16));
		panel_3.add(buttonList);
		
		JButton buttonCancel = new JButton("取消");
		buttonCancel.setBackground(Color.WHITE);
		buttonCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonCancel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		panel_3.add(buttonCancel);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "\u4F7F\u7528\u9879\u76EE\u660E\u7EC6\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_4.add(scrollPane_1, BorderLayout.CENTER);
		
		tablePatientCostDetail = new JTable();
		tablePatientCostDetail.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		tablePatientCostDetail.setRowHeight(20);
		tablePatientCostDetail.setModel(new DefaultTableModel(
			
		));
		tablePatientCostDetail.getTableHeader().setReorderingAllowed(false);
		scrollPane_1.setViewportView(tablePatientCostDetail);
	}

}
