package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Enumeration;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import control.CostAction;
import control.MedicineAction;
import control.PatientAction;
import model.CostFun;

import java.awt.Insets;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PatientCostEnter extends JFrame {

	private JPanel contentPane;
	private JPanel panelBottom;
	private JButton buttonConfirm;
	private JButton buttonCancel;
	private JTextField textFieldPatientName;
	private JTextField textFieldPatientNumber;
	private JTable tablePatientInfo;
	private JScrollPane scrollPane_1;
	private JTable tableCostType;
	private JPanel panel_1;
	private JLabel label_2;
	private JTextField textFieldMedicineSerach;
	private JScrollPane scrollPane_2;
	private JTable tableMedicineItem;
	private JPanel panel_2;
	private JButton buttonIn;
	private JButton buttonOut;
	private JScrollPane scrollPane_3;
	private JTable tableMedicineNumber;
	
	
	private String medicineType = null;
	private Object medicines[][] = null;
	private MedicineAction medicineAction = new MedicineAction();
	
	private PatientAction patientAction = new PatientAction();
	private CostFun costFun = new CostFun();
	private Object patient[][] = null;
	private String patientName = null;
	
	private int amount = 0;
	private float preprice = 0;
	private String cost = null;
	
	
	//设置列名
	String title1[] =  {"序号", "病人姓名", "住院号", "病床区号", "医保类型", "住院时间", "出院时间"};
	String title3[] = {"项目名称", "费用类别", "类型", "单位", "单价"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientCostEnter frame = new PatientCostEnter();
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
	public PatientCostEnter() {
		addWindowListener(new WindowAdapter() {
			@Override
			//重写关闭窗体方法
			public void windowClosing(WindowEvent arg0) {
				int flag = JOptionPane.showConfirmDialog(contentPane, "确认退出吗？", "警告", JOptionPane.YES_NO_OPTION);
				if(flag == JOptionPane.YES_OPTION)
					dispose();
				else
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		});
		setResizable(false);
		setTitle("病人出院费用录入");
		setIconImage(new ImageIcon("img/yz.jpg").getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int widthMargin = (screenWidth - 1350)/2;
		int heightMargin = (screenHeight - 940)/2;
		setBounds(widthMargin, heightMargin, 1233, 940);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		panelTop.setBackground(Color.WHITE);
		panelTop.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u75C5\u4EBA\u4FE1\u606F\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		panelTop.setFont(new Font("微软雅黑", Font.BOLD, 18));
		panelTop.setPreferredSize(new Dimension(1178, 200));
		contentPane.add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(null);
		
		JLabel label = new JLabel("输入病人姓名：");
		label.setBounds(259, 27, 110, 18);
		panelTop.add(label);
		
		textFieldPatientName = new JTextField();
		textFieldPatientName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
				{
					patientName = textFieldPatientName.getText(); 
					try {
						patient = patientAction.getPatient(patientName);
						tablePatientInfo.setModel(new DefaultTableModel(patient,title1));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		textFieldPatientName.addFocusListener(new FocusAdapter() {
			@Override
			//利用失去光标后获取文本框的值，这种方法好像不太好
			public void focusLost(FocusEvent e) {
				patientName = textFieldPatientName.getText(); 
				try {
					patient = patientAction.getPatient(patientName);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				tablePatientInfo.setModel(new DefaultTableModel(patient,title1));
			}
		});
		textFieldPatientName.setForeground(Color.RED);
		textFieldPatientName.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textFieldPatientName.setBounds(359, 24, 110, 24);
		panelTop.add(textFieldPatientName);
		textFieldPatientName.setColumns(10);
		
		JButton btnConfirmName = new JButton("确定");
		btnConfirmName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//按钮按下从数据库中读取数据
					try {
						patient = patientAction.getPatient(patientName);
						tablePatientInfo.setModel(new DefaultTableModel(patient,title1));
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
		});
		btnConfirmName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnConfirmName.setBackground(Color.WHITE);
		btnConfirmName.setBounds(494, 23, 63, 27);
		panelTop.add(btnConfirmName);
		
		JLabel label_1 = new JLabel("您选择的病人住院号为：");
		label_1.setBounds(621, 27, 165, 18);
		panelTop.add(label_1);
		
		textFieldPatientNumber = new JTextField();
		textFieldPatientNumber.setFont(new Font("宋体", Font.PLAIN, 18));
		textFieldPatientNumber.setEditable(false);
		textFieldPatientNumber.setBounds(785, 24, 152, 25);
		panelTop.add(textFieldPatientNumber);
		textFieldPatientNumber.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("微软雅黑", Font.BOLD, 18));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(207, 72, 799, 115);
		panelTop.add(scrollPane);
		
		tablePatientInfo = new JTable();
		tablePatientInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablePatientInfo.addMouseListener(new MouseAdapter() {
			@Override
			//经过尝试使用mousePressed事件比使用mouseClicked事件要好，mouseClick会在第一次单击时获取不到选项值，给人的感觉是反应慢
			public void mousePressed(MouseEvent e) {
				//根据选择的病人来设置住院号文本框的内容，而不是在根据病人的姓名后就设定，避免重名情况
				int rowIndex = tablePatientInfo.getSelectedRow();
				if(rowIndex == -1)
				{
					JOptionPane.showMessageDialog(null, "没有选中病人的数据");
				}
				else
				{
					String number = (String) tablePatientInfo.getModel().getValueAt(rowIndex, 2);
					textFieldPatientNumber.setText(number);
					if(textFieldPatientName.getText().length() == 0)
						textFieldPatientName.setText((String) tablePatientInfo.getModel().getValueAt(rowIndex, 1));
				}
			}
		});
		tablePatientInfo.setRowHeight(25);
		tablePatientInfo.getTableHeader().setReorderingAllowed(false);//设置列与列之间不能交换位置
		tablePatientInfo.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		tablePatientInfo.setModel(new DefaultTableModel(patient,title1));
		//this.FitTableColumns(tablePatientInfo);
		scrollPane.setViewportView(tablePatientInfo);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(Color.WHITE);
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "\u8D39\u7528\u7C7B\u522B\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		panel.setBounds(14, 34, 156, 615);
		panelCenter.add(panel);
		panel.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBackground(Color.WHITE);
		scrollPane_1.setBounds(14, 33, 128, 569);
		panel.add(scrollPane_1);
		
		tableCostType = new JTable();
		tableCostType.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int rowIndex = tableCostType.getSelectedRow();
				medicineType = (String) tableCostType.getModel().getValueAt(rowIndex, 1);
				try {
					medicines = medicineAction.getMedicine(medicineType);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				tableMedicineItem.setModel(new DefaultTableModel(medicines,title3));
			}
		});
		tableCostType.setRowHeight(20);
		tableCostType.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		tableCostType.setAlignmentX(Component.RIGHT_ALIGNMENT);
		tableCostType.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		tableCostType.getTableHeader().setReorderingAllowed(false);//设置列与列之间不能交换位置
		
		String title2[] = {"序号", "费用类别"};
		//从数据库中静态读取数据
		Object costType[][] = null;
		try {
			costType = costFun.query();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tableCostType.setModel(new DefaultTableModel(costType,title2));
		this.FitTableColumns(tableCostType);
		scrollPane_1.setViewportView(tableCostType);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "\u5BF9\u5E94\u9879\u76EE\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		panel_1.setBounds(184, 34, 490, 615);
		panelCenter.add(panel_1);
		panel_1.setLayout(null);
		
		label_2 = new JLabel("模糊搜索：");
		label_2.setBounds(47, 34, 81, 18);
		panel_1.add(label_2);
		
		textFieldMedicineSerach = new JTextField();
		textFieldMedicineSerach.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
				{
					String medicineName = textFieldMedicineSerach.getText(); 
					int rowIndex = tableCostType.getSelectedRow();
					//先判断是否选择了费用类别
					if(rowIndex == -1)
					{
						JOptionPane.showMessageDialog(null, "请先选择费用类别");
					}
					else
					{
						medicineType = (String) tableCostType.getModel().getValueAt(rowIndex, 1);
						try {
							medicines = medicineAction.searchMedicine(medicineType,medicineName);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						tableMedicineItem.setModel(new DefaultTableModel(medicines,title3));
					}
				}
			}
		});
		textFieldMedicineSerach.setForeground(Color.RED);
		textFieldMedicineSerach.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		textFieldMedicineSerach.setCaretColor(Color.RED);
		textFieldMedicineSerach.setToolTipText("搜索其实很简单");
		textFieldMedicineSerach.setBounds(127, 31, 248, 24);
		panel_1.add(textFieldMedicineSerach);
		textFieldMedicineSerach.setColumns(10);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBackground(Color.WHITE);
		scrollPane_2.setBounds(14, 77, 460, 525);
		panel_1.add(scrollPane_2);
		
		tableMedicineItem = new JTable();
		tableMedicineItem.setRowHeight(22);
		tableMedicineItem.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		tableMedicineItem.getTableHeader().setReorderingAllowed(false);//设置列与列之间不能交换位置
		tableMedicineItem.setModel(new DefaultTableModel(medicines,title3));
		this.FitTableColumns(tableMedicineItem);
		scrollPane_2.setViewportView(tableMedicineItem);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(null, "\u6570\u91CF\u5F55\u5165\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(718, 34, 485, 615);
		panelCenter.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBackground(Color.WHITE);
		scrollPane_3.setBounds(14, 29, 457, 573);
		panel_2.add(scrollPane_3);
		
		tableMedicineNumber = new JTable();
		tableMedicineNumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int row = tableMedicineNumber.getSelectedRow();
				int col = tableMedicineNumber.getSelectedColumn();
				cost = String.valueOf(preprice * amount);
				tableMedicineNumber.setValueAt(cost, row, col+3);
				System.out.println(cost);
				tableMedicineNumber.updateUI();
			}
		});
		tableMedicineNumber.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int row = tableMedicineNumber.getSelectedRow();
				int col = tableMedicineNumber.getSelectedColumn();
				amount = Integer.valueOf((String) tableMedicineNumber.getValueAt(row, col));//得到数量值
				//System.out.println(amount);
				preprice = Float.valueOf((String) tableMedicineNumber.getValueAt(row, col+2));//得到单价值
				//System.out.println(preprice);
				//String cost = String.valueOf(preprice * amount);
				//System.out.println(cost);
				//tableMedicineNumber.setValueAt(cost, row, col+3);
			}
		});
		//数量录入栏中，数量变化时，该行总费用也随之改变，未实现
//		tableMedicineNumber.getModel().addTableModelListener(new TableModelEvent() {
//				public void tableChanged(TableModelEvent e){
//					int row = e.getFirstRow();
//					int col = e.getColumn();
//					TableModel model = (TableModel)e.getSource();
//					int amount = (int) model.getValueAt(row, col);//得到数量值
//					System.out.println(amount);
//					float preprice = (float) model.getValueAt(row, col+1);//得到单价值
//					System.out.println(preprice);
//					String cost = String.valueOf(preprice * amount);
//					System.out.println(cost);
//					model.setValueAt(cost, row, col+3);
//				}
//		});
			
		tableMedicineNumber.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMedicineNumber.setSelectionBackground(Color.RED);
		tableMedicineNumber.setRowHeight(22);
		tableMedicineNumber.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		tableMedicineNumber.getTableHeader().setReorderingAllowed(false);//设置列与列之间不能交换位置
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"项目名称", "费用类别", "类型", "数量", "单位", "单价", "费用"
				}
			);
		tableMedicineNumber.setModel(tableModel);
		//this.FitTableColumns(tableMedicineNumber);
		scrollPane_3.setViewportView(tableMedicineNumber);
		
		buttonIn = new JButton(">>");
		buttonIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//利用选择后的索引值做判断，而不是在另外单独设置一个判断变量，改善用户使用体验
				int rowIndex = tableMedicineItem.getSelectedRow();
				if(rowIndex == -1)
				{
					JOptionPane.showMessageDialog(null, "请先选择费用类型");
				}
				else{
					String col0 = (String) tableMedicineItem.getModel().getValueAt(rowIndex, 0);
					String col1 = (String) tableMedicineItem.getModel().getValueAt(rowIndex, 1);
					String col2 = (String) tableMedicineItem.getModel().getValueAt(rowIndex, 2);
					String col3 = "1";
					String col4 = (String) tableMedicineItem.getModel().getValueAt(rowIndex, 3);
					String col5 = (String) tableMedicineItem.getModel().getValueAt(rowIndex, 4);
					//动态改变值，未实现
					float preprice = Float.valueOf(col5);//单价
					int amount = Integer.valueOf(col3);//数量
					String col6 = String.valueOf(preprice * amount);
					
					String[] rowValues = {col0, col1, col2, col3, col4, col5, col6};
					tableModel.addRow(rowValues);
				}
			}
		});
		buttonIn.setToolTipText("录入");
		buttonIn.setMargin(new Insets(2, 2, 2, 2));
		buttonIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonIn.setBackground(Color.WHITE);
		buttonIn.setBounds(675, 174, 40, 27);
		panelCenter.add(buttonIn);
		
		buttonOut = new JButton("<<");
		buttonOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int rowIndex = tableMedicineNumber.getSelectedRow(); 
				if(rowIndex == -1)
				{
					JOptionPane.showMessageDialog(null, "请选择数量录入栏的行");
				}
				else {
					tableModel.removeRow(rowIndex);
				}
			}
		});
		buttonOut.setToolTipText("删除");
		buttonOut.setMargin(new Insets(2, 2, 2, 2));
		buttonOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonOut.setBackground(Color.WHITE);
		buttonOut.setBounds(676, 294, 40, 27);
		panelCenter.add(buttonOut);
		
		panelBottom = new JPanel();
		panelBottom.setBackground(Color.WHITE);
		panelBottom.setPreferredSize(new Dimension(1200, 35));
		contentPane.add(panelBottom, BorderLayout.SOUTH);
		panelBottom.setLayout(null);
		
		buttonConfirm = new JButton("确定");
		buttonConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//循环得到病人药物及费用表的每一行的数据,放到一个二维数组中，而不是一条一条的插入数据库，减少和数据库连接的次数
				String patientName = textFieldPatientName.getText();
				String PatientNumber = textFieldPatientNumber.getText();
				if(patientName.length() == 0 || PatientNumber.length() == 0){
					JOptionPane.showMessageDialog(null, "请选择病人信息");
					//设置一下光标所在的位置为病人的姓名位置
					textFieldPatientName.requestFocus();
				}
				else {
					int rows = tableModel.getRowCount();
					Object cost[][] = new Object[rows][7];
					for(int i= 0; i < rows; i++)
					{
						cost[i][0] = tableMedicineNumber.getModel().getValueAt(i, 0);
						cost[i][1] = tableMedicineNumber.getModel().getValueAt(i, 1);
						cost[i][2] = tableMedicineNumber.getModel().getValueAt(i, 2);
						cost[i][3] = tableMedicineNumber.getModel().getValueAt(i, 3);
						cost[i][4] = tableMedicineNumber.getModel().getValueAt(i, 4);
						cost[i][5] = tableMedicineNumber.getModel().getValueAt(i, 5);
						cost[i][6] = tableMedicineNumber.getModel().getValueAt(i, 6);
					}
					//对以上数据操作，还需传入四个参数，病人姓名，住院号，行数，单行的费用信息
					CostAction costAction = new CostAction();
					try {
						costAction.addPatientCost(patientName, PatientNumber, rows, cost);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		buttonConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonConfirm.setBackground(Color.WHITE);
		buttonConfirm.setBounds(796, 0, 88, 27);
		panelBottom.add(buttonConfirm);
		
		buttonCancel = new JButton("取消");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((DefaultTableModel) tableMedicineNumber.getModel()).getDataVector().clear();//清除表格数据
				((DefaultTableModel) tableMedicineNumber.getModel()).fireTableDataChanged();//通知模型更新
				tableMedicineNumber.updateUI();//刷新表格 
			}
		});
		buttonCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonCancel.setBackground(Color.WHITE);
		buttonCancel.setBounds(988, 0, 82, 27);
		panelBottom.add(buttonCancel);
	}
	
	
	
	//表格自适应单元格大小
	public void FitTableColumns(JTable myTable) {               
	    JTableHeader header = myTable.getTableHeader();
	    int rowCount = myTable.getRowCount();
	    Enumeration columns = myTable.getColumnModel().getColumns();
	    while (columns.hasMoreElements()) {
	        TableColumn column = (TableColumn) columns.nextElement();
	        int col = header.getColumnModel().getColumnIndex(
	                column.getIdentifier());
	        int width = (int) myTable.getTableHeader().getDefaultRenderer()
	                .getTableCellRendererComponent(myTable,
	                        column.getIdentifier(), false, false, -1, col)
	                .getPreferredSize().getWidth();
	        for (int row = 0; row < rowCount; row++) {
	            int preferedWidth = (int) myTable.getCellRenderer(row, col)
	                    .getTableCellRendererComponent(myTable,
	                            myTable.getValueAt(row, col), false, false,
	                            row, col).getPreferredSize().getWidth();
	            width = Math.max(width, preferedWidth+10);
	        }
	        header.setResizingColumn(column);
	        column.setWidth(width + myTable.getIntercellSpacing().width);
	    }
	}
}
