package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.PrintJob;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import control.CostAction;
import control.PatientAction;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.Properties;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

public class PrintListDialog extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldPatientName;
	private JTable tableCost;
	private JTable tablePatientInfo;
	private JTextField textFieldActualCharge;
	private JTextField textFieldGiveChange;
	private JTextField textFieldBillName;
	
	private String patientName = null;
	private Object patient[][] = null;
	private String title1[] = new String[] {
			"序号", "病人姓名", "住院号", "病床区号", "医保类型", "住院时间", "出院时间"
		};

	private Object cost[][] = null;
	private String title2[] = new String[] {
			"序号", "费用类别", "费用"
		};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintListDialog frame = new PrintListDialog();
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
	public PrintListDialog() {
		setTitle("发票打印");
		setIconImage(new ImageIcon("img/yz.jpg").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int widthMargin = (screenWidth - 680)/2;
		int heightMargin = (screenHeight - 840)/2;
		setBounds(widthMargin, heightMargin, 686, 843);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u641C\u7D22\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setPreferredSize(new Dimension(686, 70));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("病人姓名：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
		lblNewLabel.setBounds(120, 31, 99, 21);
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
		
		textFieldPatientName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				patientName = textFieldPatientName.getText();
			}
		});
		textFieldPatientName.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldPatientName.setBounds(205, 31, 123, 24);
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
		btnNewButton.setBounds(342, 30, 74, 27);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u75C5\u4EBA\u4FE1\u606F\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setFont(new Font("微软雅黑", Font.BOLD, 18));
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		tablePatientInfo = new JTable();
		tablePatientInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int rowIndex = tablePatientInfo.getSelectedRow();
				String patientNumber = (String) tablePatientInfo.getModel().getValueAt(rowIndex, 2);
				CostAction costAction = new CostAction();
				try {
					cost = costAction.getCost(patientNumber);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				tableCost.setModel(new DefaultTableModel(cost, title2));
			}
		});
		tablePatientInfo.setRowHeight(20);
		tablePatientInfo.setFont(new Font("微软雅黑", Font.BOLD, 16));
		tablePatientInfo.setModel(new DefaultTableModel());
	
		scrollPane_1.setViewportView(tablePatientInfo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(686, 580));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "\u5404\u9879\u8D39\u7528\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setPreferredSize(new Dimension(686, 380));
		panel_2.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);
		
		tableCost = new JTable();
		tableCost.setRowHeight(20);
		tableCost.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		tableCost.setModel(new DefaultTableModel());
		scrollPane.setViewportView(tableCost);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "\u5176\u4ED6\u4FE1\u606F\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		panel_2.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(null);
		
		JLabel label = new JLabel("实收：");
		label.setBounds(134, 42, 72, 18);
		panel_4.add(label);
		
		textFieldActualCharge = new JTextField();
		textFieldActualCharge.setFont(new Font("微软雅黑", Font.BOLD, 16));
		textFieldActualCharge.setForeground(Color.BLACK);
		textFieldActualCharge.setBackground(Color.WHITE);
		textFieldActualCharge.setBounds(178, 39, 86, 24);
		panel_4.add(textFieldActualCharge);
		textFieldActualCharge.setColumns(10);
		
		JButton button_1 = new JButton("找零");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String actualCharge = textFieldActualCharge.getText();
				if(actualCharge.length() <= 0)
					JOptionPane.showMessageDialog(null, "请输入实际收取钱数");
				else {
					String totalCost = (String) tableCost.getModel().getValueAt(15, 2);
					float ftotalCost = Float.valueOf(totalCost);
					float factualCharge = Float.valueOf(actualCharge);
					float change = factualCharge - ftotalCost;
					DecimalFormat decimalFormat = new DecimalFormat(".00");
					String giveChange = decimalFormat.format(change);
					textFieldGiveChange.setText(giveChange);	
				}
			}
		});
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		button_1.setBackground(Color.YELLOW);
		button_1.setBounds(278, 38, 88, 27);
		panel_4.add(button_1);
		
		textFieldGiveChange = new JTextField();
		textFieldGiveChange.setForeground(Color.RED);
		textFieldGiveChange.setFont(new Font("微软雅黑", Font.BOLD, 16));
		textFieldGiveChange.setBounds(380, 39, 86, 24);
		panel_4.add(textFieldGiveChange);
		textFieldGiveChange.setColumns(10);
		
		JLabel label_1 = new JLabel("开票姓名：");
		label_1.setBounds(178, 90, 88, 18);
		panel_4.add(label_1);
		
		textFieldBillName = new JTextField();
		textFieldBillName.setFont(new Font("微软雅黑", Font.BOLD, 16));
		textFieldBillName.setText(Mainwindow.username);
		textFieldBillName.setEditable(false);
		textFieldBillName.setBounds(280, 87, 86, 24);
		panel_4.add(textFieldBillName);
		textFieldBillName.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(50);
		panel_5.setPreferredSize(new Dimension(686, 50));
		panel_2.add(panel_5, BorderLayout.SOUTH);
		
		JButton buttonPreview = new JButton("打印预览");
		buttonPreview.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					tableCost.print(PrintMode.FIT_WIDTH);
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonPreview.setFont(new Font("微软雅黑", Font.BOLD, 16));
		buttonPreview.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonPreview.setBackground(Color.WHITE);
		panel_5.add(buttonPreview);
		
		JButton buttonList = new JButton("打印清单");
		buttonList.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				startPrint();//打印窗体
			}
		});
		buttonList.setFont(new Font("微软雅黑", Font.BOLD, 16));
		buttonList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonList.setBackground(Color.WHITE);
		panel_5.add(buttonList);
		
		JButton buttonCancel = new JButton("取消");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		buttonCancel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		buttonCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonCancel.setBackground(Color.WHITE);
		panel_5.add(buttonCancel);
	}
	//打印整个窗体
  public void startPrint() {
      Toolkit kit = Toolkit.getDefaultToolkit(); //获取工具箱
      Properties props = new Properties();
      props.put("awt.print.printer", "durango"); //设置打印属性
      props.put("awt.print.numCopies", "2");
      if (kit != null) {
         //获取工具箱自带的打印对象
         PrintJob printJob = kit.getPrintJob(this, "Print View Frame", props);
         if (printJob != null) {
            Graphics pg = printJob.getGraphics(); //获取打印对象的图形环境
            if (pg != null) {
               try {
                  this.paintAll(pg); //打印该窗体及其所有的组件
               } finally {
                  pg.dispose(); //注销图形环境
               }
            }
            printJob.end(); //结束打印作业
         }
      }
   }
}
