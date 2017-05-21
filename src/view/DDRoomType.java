package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import control.CostAction;
import control.RoomAction;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Enumeration;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;


public class DDRoomType extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public static String roomNo;
	public static String bedNo;
	public static String roomType;
	public static String bedPrice;
	public static String floors;
	public static String remark;
	
	private int rowIndex = -1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DDRoomType frame = new DDRoomType();
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
	public DDRoomType() {
		setResizable(false);
		setTitle("房间信息维护");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/yz.jpg").getImage());
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int widthMargin = (screenWidth - 654)/2;
		int heightMargin = (screenHeight - 460)/2;
		setBounds(widthMargin, heightMargin, 654, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				rowIndex = table.getSelectedRow();
				if(rowIndex != -1){
					roomNo = (String) table.getModel().getValueAt(rowIndex, 1);
					bedNo = (String) table.getModel().getValueAt(rowIndex, 2);
					roomType = (String) table.getModel().getValueAt(rowIndex, 3);
					bedPrice = (String) table.getModel().getValueAt(rowIndex, 4);
					floors = (String) table.getModel().getValueAt(rowIndex, 5);
					remark = (String) table.getModel().getValueAt(rowIndex, 7);	
				}
			}
		});
		table.setRowHeight(20);
		table.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		table.setBackground(Color.WHITE);
		Object roomtypes[][] = null;
		RoomAction roomAction = new RoomAction();
		try {
			roomtypes = roomAction.getRoomInfo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String title[] = new String[] {
				"序号", "房间号", "床位号", "房间类型", "床位价格", "楼层", "是否可以住", "备注"
		};
		DefaultTableModel tableModel = new DefaultTableModel(roomtypes, title);
		table.setModel(tableModel);
		table.getTableHeader().setReorderingAllowed(false);
		this.FitTableColumns(table);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(50);
		panel.setPreferredSize(new Dimension(640, 50));
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton buttonIncrease = new JButton("增加");
		buttonIncrease.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				AddRoomBed addRoomBed = new AddRoomBed();
				addRoomBed.setVisible(true);
			}
		});
		buttonIncrease.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonIncrease.setBackground(Color.WHITE);
		buttonIncrease.setFont(new Font("微软雅黑", Font.BOLD, 16));
		panel.add(buttonIncrease);
		
		JButton buttonDelete = new JButton("删除");
		buttonDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(rowIndex == -1){
					JOptionPane.showMessageDialog(null, "请先选择一条房间信息");
				}else{
					//删除操作，以房间号和床位号两个标志变量来删除相应的数据
					String roomNo = (String) table.getModel().getValueAt(rowIndex, 1);
					String bedNo = (String) table.getModel().getValueAt(rowIndex, 2);
					RoomAction roomAction = new RoomAction();
						boolean result = false;
						try {
							//界面内容的修改，放到数据库修改之后，避免数据库里面数据删除失败，界面上的内容就已经改变了
							result = roomAction.delRoomInfo(roomNo, bedNo);
							if(result)
								tableModel.removeRow(rowIndex);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
				}
			}
		});
		buttonDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonDelete.setFont(new Font("微软雅黑", Font.BOLD, 16));
		buttonDelete.setBackground(Color.WHITE);
		panel.add(buttonDelete);
		
		JButton buttonChange = new JButton("修改");
		buttonChange.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(rowIndex == -1){
					JOptionPane.showMessageDialog(null, "请先选择一条房间信息");
				}else{
					//获取所选中的信息，采用静态常量的方法传到修改界面上
					ChangeRoomBed changeRoomBed = new ChangeRoomBed();
					changeRoomBed.setVisible(true);
				}
			}
		});
		buttonChange.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonChange.setFont(new Font("微软雅黑", Font.BOLD, 16));
		buttonChange.setBackground(Color.WHITE);
		panel.add(buttonChange);
		
		JButton buttonCancel = new JButton("取消");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		buttonCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonCancel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		buttonCancel.setBackground(Color.WHITE);
		panel.add(buttonCancel);
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
