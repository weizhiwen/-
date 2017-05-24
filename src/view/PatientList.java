package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;

import javax.swing.table.DefaultTableModel;

import control.PatientAction;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.SystemColor;

public class PatientList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnAdd;
	private JButton btnChange;
	private JButton btnDelete;
	private JButton btnCancel;
	private boolean isSelect = false;
	private PatientAction patientAction = new PatientAction();
	
	//定义类静态常量
	public static String patientName = null; 
	public static String patientNumebr = null;
	public static String bedNumber = null;
	public static String medicalType = null;
	public static String inHospitalTime = null;
	public static String outHospitalTime = null;
	
	//设定列名
	String[] title = {"序号","病人姓名","住院号","病床区号","病床区号","住院时间","出院时间"};
	Object patients[][] = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientList frame = new PatientList();
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
	public PatientList() {
		setTitle("病人信息维护");
		setIconImage(new ImageIcon("img/yz.jpg").getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int widthMargin = (screenWidth - 1100)/2;
		int heightMargin = (screenHeight - 600)/2;
		setBounds(widthMargin, heightMargin, 1060, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("微软雅黑", Font.BOLD, 18));
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setBackground(Color.WHITE);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		//从数据库中打印出数据
		try {
			patients = patientAction.getPatient();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DefaultTableModel tableModel = new DefaultTableModel(patients,title);
		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//JOptionPane.showMessageDialog(table, "鼠标按下");
				isSelect = true;
			}
		});
		
		table.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 20));
		table.setSurrendersFocusOnKeystroke(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);//设置列与列之间不能交换位置
		table.setRowMargin(3);
		table.setRowHeight(25);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		table.setBackground(Color.WHITE);
		table.setAlignmentY(Component.TOP_ALIGNMENT);
		table.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setViewportView(table);
		
		panel = new JPanel();
		panel.setAlignmentY(Component.TOP_ALIGNMENT);
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(50);
		panel.setBackground(SystemColor.controlHighlight);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnAdd = new JButton("添加");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					//添加病人信息时不需要表格的某一行被选中
					PatientInfoDlog patientInfoDlog = new PatientInfoDlog();
					patientInfoDlog.setVisible(true);
					try {
						patients = patientAction.getPatient();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					tableModel.fireTableDataChanged();
					table.setModel(new DefaultTableModel(patients,title));
			}
		});
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(btnAdd);
		
		btnChange = new JButton("修改");
		btnChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isSelect){
					int rowIndex = table.getSelectedRow();
					if(rowIndex == -1)
						JOptionPane.showMessageDialog(null, "请先选择病人信息");
					//JOptionPane.showMessageDialog(table, "修改按钮被按下！"+rowIndex);
					//从界面上获取数据到病人信息修改框中
					else {
						patientName = (String) table.getModel().getValueAt(rowIndex, 1);
						patientNumebr = (String) table.getModel().getValueAt(rowIndex, 2);
						bedNumber = (String) table.getModel().getValueAt(rowIndex, 3);
						medicalType = (String) table.getModel().getValueAt(rowIndex, 4);
						inHospitalTime = (String) table.getModel().getValueAt(rowIndex, 5);
						outHospitalTime = (String) table.getModel().getValueAt(rowIndex, 6);
						ChangePatientInfo changePatientInfo = new ChangePatientInfo();
						changePatientInfo.setVisible(true);
						try {
							patients = patientAction.getPatient();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						table.setModel(new DefaultTableModel(patients,title));
					}
				}
			}
		});
		btnChange.setBackground(Color.WHITE);
		btnChange.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnChange.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(btnChange);
		
		btnDelete = new JButton("删除");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(isSelect){
					int rowIndex = table.getSelectedRow();
					if (rowIndex == -1)
						JOptionPane.showMessageDialog(null, "请先选择病人信息");
					//JOptionPane.showMessageDialog(table, "删除按钮被按下！"+rowIndex);
					//先数据库删除然后在界面上更新删除，避免在数据库删除失败
					//根据病人的住院号条件删除，因为住院号是唯一的
					else{
						patientNumebr = (String) table.getModel().getValueAt(rowIndex, 2);
						PatientAction patientAction = new PatientAction();
						try {
							patientAction.delPatient(patientNumebr);
						} catch (SQLException e1) {
							e1.printStackTrace();
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
						tableModel.removeRow(rowIndex);
					}
				}
			}
		});
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(btnDelete);
		
		btnCancel = new JButton("取消");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(btnCancel);
	}
	
}
