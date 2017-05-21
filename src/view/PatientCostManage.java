package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.management.loading.PrivateClassLoader;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Cursor;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JTable;
import java.awt.FlowLayout;
import javax.swing.table.DefaultTableModel;

import control.CostAction;
import control.PatientAction;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ListSelectionModel;

public class PatientCostManage extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPatientName;
	private JTable tablePatientInfo;
	private JTable tablePatientCostInfo;
	
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
	
	//定义窗体的静态常量，方便其他子窗体使用
	public static String PATIENTNAME = null;
	public static String PATIENTNUMBER = null;
	public static String ITEMNAME = null;
	public static String COSTTYPE = null;
	public static String KIND = null;
	public static String AMOUNT = null;
	public static String UNIT = null;
	public static String PREPRICE = null;
	public static String COST = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientCostManage frame = new PatientCostManage();
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
	public PatientCostManage() {
		setTitle("出院费用维护");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int widthMargin = (screenWidth - 960)/2;
		int heightMargin = (screenHeight - 820)/2;
		setBounds(widthMargin, heightMargin, 960, 820);
		setIconImage(new ImageIcon("img/yz.jpg").getImage());
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		panel.setPreferredSize(new Dimension(950, 130));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("请输入你想维护的病人的姓名：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel.setBounds(196, 17, 210, 18);
		panel.add(lblNewLabel);
		
		textFieldPatientName = new JTextField();
		textFieldPatientName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
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
		textFieldPatientName.setForeground(Color.RED);
		textFieldPatientName.setFont(new Font("微软雅黑", Font.BOLD, 16));
		textFieldPatientName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				patientName = textFieldPatientName.getText();
			}
		});

		textFieldPatientName.setBounds(420, 14, 115, 24);
		panel.add(textFieldPatientName);
		textFieldPatientName.setColumns(10);
		
		JButton buttonConfirm1 = new JButton("确定");
		buttonConfirm1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PatientAction patientAction = new PatientAction();
				try {
					patient = patientAction.getPatient(patientName);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				tablePatientInfo.setModel(new DefaultTableModel(patient, title1));
			}
		});
		buttonConfirm1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonConfirm1.setBackground(Color.WHITE);
		buttonConfirm1.setBounds(563, 13, 88, 27);
		panel.add(buttonConfirm1);
		
		JLabel label = new JLabel("维护住院时间在");
		label.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label.setBounds(196, 54, 115, 18);
		panel.add(label);
		
		JLabel label_1 = new JLabel("和");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label_1.setBounds(417, 54, 26, 18);
		panel.add(label_1);
		
		JComboBox comboBoxFirstTime = new JComboBox();
		comboBoxFirstTime.setEditable(true);
		comboBoxFirstTime.setBackground(Color.WHITE);
		comboBoxFirstTime.setBounds(313, 51, 97, 24);
		panel.add(comboBoxFirstTime);
		
		JComboBox comboBoxAfterTime = new JComboBox();
		comboBoxAfterTime.setEditable(true);
		comboBoxAfterTime.setBackground(Color.WHITE);
		comboBoxAfterTime.setBounds(438, 52, 97, 24);
		panel.add(comboBoxAfterTime);
		
		JLabel label_2 = new JLabel("之间的病人出院费用信息");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label_2.setBounds(549, 54, 187, 18);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("维护所有病人的出院费用信息");
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label_3.setBounds(196, 88, 210, 18);
		panel.add(label_3);
		
		JButton buttonConfirm2 = new JButton("确定");
		buttonConfirm2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//全部查找
				PatientAction patientAction = new PatientAction();
				try {
					patient = patientAction.getPatient("");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				tablePatientInfo.setModel(new DefaultTableModel(patient, title1));
			}
		});
		buttonConfirm2.setBackground(Color.WHITE);
		buttonConfirm2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonConfirm2.setBounds(418, 84, 88, 27);
		panel.add(buttonConfirm2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel_1.setBackground(Color.WHITE);
		panel_1.setPreferredSize(new Dimension(950, 100));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u75C5\u4EBA\u4FE1\u606F\u663E\u793A\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("宋体", Font.BOLD, 16));
		scrollPane.setPreferredSize(new Dimension(950, 100));
		scrollPane.setBackground(Color.WHITE);
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		tablePatientInfo = new JTable();
		tablePatientInfo.setRowHeight(20);
		tablePatientInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int rowIndex = tablePatientInfo.getSelectedRow();
				String patientNumber = (String) tablePatientInfo.getModel().getValueAt(rowIndex, 2);
				CostAction costAction = new CostAction();
				try {
					patientCost = costAction.getPatientCost(patientNumber);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				tableModel = new DefaultTableModel(patientCost, title2);
				tablePatientCostInfo.setModel(tableModel);
			}
		});
		tablePatientInfo.setFont(new Font("微软雅黑", Font.BOLD, 16));
		tablePatientInfo.setBackground(Color.WHITE);
		tablePatientInfo.getTableHeader().setReorderingAllowed(false);//设置列与列之间不能交换位置
		scrollPane.setViewportView(tablePatientInfo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setPreferredSize(new Dimension(950, 450));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(70);
		panel_3.setBackground(Color.WHITE);
		panel_3.setPreferredSize(new Dimension(950, 50));
		panel_2.add(panel_3, BorderLayout.SOUTH);
		
		JButton buttonAdd = new JButton("增加");
		buttonAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PatientCostEnter patientCostEnter = new PatientCostEnter();
				patientCostEnter.setVisible(true);
			}
		});
		buttonAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonAdd.setBackground(Color.WHITE);
		buttonAdd.setForeground(Color.DARK_GRAY);
		buttonAdd.setFont(new Font("微软雅黑", Font.BOLD, 16));
		panel_3.add(buttonAdd);
		
		JButton buttonDelete = new JButton("删除");
		buttonDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//只允许单项删除，考虑到删除的需求不是很大
				int patientrowIndex = tablePatientInfo.getSelectedRow();
				int costrowIndex = tablePatientCostInfo.getSelectedRow();
				if(patientrowIndex == -1 || costrowIndex == -1)
				{
					JOptionPane.showMessageDialog(null, "请先选择费用信息");
				}
				else
				{	
					//病人出院费用表中的记录修改，首先要获取病人住院号和费用名称，
					//这里不采用病人姓名的方法来删除，因为病人的姓名可能会重名
					String patientNumber = (String) tablePatientInfo.getModel().getValueAt(patientrowIndex, 2);
					String itemName = (String) tablePatientCostInfo.getModel().getValueAt(costrowIndex, 1);
					CostAction costAction = new CostAction();
					try {
						boolean result = costAction.delPatientCost(patientNumber, itemName);
						//界面内容的修改，放到数据库修改之后，避免数据库里面数据删除失败，界面上的内容就已经改变了
						if(result)
							tableModel.removeRow(costrowIndex);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		buttonDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonDelete.setBackground(Color.WHITE);
		buttonDelete.setForeground(Color.DARK_GRAY);
		buttonDelete.setFont(new Font("微软雅黑", Font.BOLD, 16));
		panel_3.add(buttonDelete);
		
		JButton buttonChange = new JButton("修改");
		buttonChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//要获取两个表选中的序列号哦
				int patientrowIndex = tablePatientInfo.getSelectedRow();
				int costrowIndex = tablePatientCostInfo.getSelectedRow();
				if(patientrowIndex == -1 || costrowIndex == -1)
				{
					JOptionPane.showMessageDialog(null, "请先选择病人信息和药用信息");
				}
				else
				{
					PatientCostManage.PATIENTNAME = (String) tablePatientInfo.getModel().getValueAt(patientrowIndex, 1);
					PatientCostManage.PATIENTNUMBER = (String) tablePatientInfo.getModel().getValueAt(patientrowIndex, 2);
					PatientCostManage.ITEMNAME = (String) tablePatientCostInfo.getModel().getValueAt(costrowIndex, 1);
					PatientCostManage.COSTTYPE = (String) tablePatientCostInfo.getModel().getValueAt(costrowIndex, 2);
					PatientCostManage.KIND = (String) tablePatientCostInfo.getModel().getValueAt(costrowIndex, 3);
					PatientCostManage.AMOUNT = new String().valueOf(tablePatientCostInfo.getModel().getValueAt(costrowIndex, 4));
					PatientCostManage.UNIT = (String) tablePatientCostInfo.getModel().getValueAt(costrowIndex, 5);
					PatientCostManage.PREPRICE = new String().valueOf(tablePatientCostInfo.getModel().getValueAt(costrowIndex, 6));
					PatientCostManage.COST = new String().valueOf(tablePatientCostInfo.getModel().getValueAt(costrowIndex, 7));
					PatientCostChange patientCostChange = new PatientCostChange();
					patientCostChange.setVisible(true);
				}
			}
		});
		buttonChange.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonChange.setBackground(Color.WHITE);
		buttonChange.setForeground(Color.DARK_GRAY);
		buttonChange.setFont(new Font("微软雅黑", Font.BOLD, 16));
		panel_3.add(buttonChange);
		
		JButton buttonCancel = new JButton("取消");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		buttonCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonCancel.setBackground(Color.WHITE);
		buttonCancel.setForeground(Color.DARK_GRAY);
		buttonCancel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		panel_3.add(buttonCancel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.WHITE);
		panel_2.add(scrollPane_1, BorderLayout.CENTER);
		
		tablePatientCostInfo = new JTable();
		tablePatientCostInfo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePatientCostInfo.setRowHeight(20);
		tablePatientCostInfo.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		tablePatientCostInfo.getTableHeader().setReorderingAllowed(false);
		scrollPane_1.setViewportView(tablePatientCostInfo);
	}
}
