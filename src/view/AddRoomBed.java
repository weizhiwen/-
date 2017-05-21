package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;

import control.RoomAction;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class AddRoomBed extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRoomNo;
	private JTextField textFieldBedNo;
	private JTextField textFieldRoomType;
	private JTextField textFieldBedPrice;
	private JTextField textFieldFloors;
	private JTextField textFieldRemark;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRoomBed frame = new AddRoomBed();
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
	public AddRoomBed() {
		setTitle("增加房间床位");
		setIconImage(new ImageIcon("img/yz.jpg").getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 441, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5E8A\u4F4D\u4FE1\u606F\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("房间号：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		lblNewLabel.setBounds(65, 53, 72, 18);
		panel.add(lblNewLabel);
		
		textFieldRoomNo = new JTextField();
		textFieldRoomNo.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldRoomNo.setBounds(156, 51, 86, 24);
		panel.add(textFieldRoomNo);
		textFieldRoomNo.setColumns(10);
		
		JLabel label = new JLabel("床位号：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label.setBounds(65, 104, 72, 18);
		panel.add(label);
		
		textFieldBedNo = new JTextField();
		textFieldBedNo.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldBedNo.setColumns(10);
		textFieldBedNo.setBounds(156, 102, 86, 24);
		panel.add(textFieldBedNo);
		
		JLabel label_1 = new JLabel("房间类型：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_1.setBounds(48, 154, 86, 18);
		panel.add(label_1);
		
		textFieldRoomType = new JTextField();
		textFieldRoomType.setText("普通病床床位费");
		textFieldRoomType.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldRoomType.setColumns(10);
		textFieldRoomType.setBounds(156, 150, 201, 24);
		panel.add(textFieldRoomType);
		
		JLabel label_2 = new JLabel("床位价格：");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_2.setBounds(48, 203, 89, 18);
		panel.add(label_2);
		
		textFieldBedPrice = new JTextField();
		textFieldBedPrice.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldBedPrice.setColumns(10);
		textFieldBedPrice.setBounds(156, 201, 86, 24);
		panel.add(textFieldBedPrice);
		
		JLabel label_3 = new JLabel("楼层：");
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_3.setBounds(79, 255, 63, 18);
		panel.add(label_3);
		
		textFieldFloors = new JTextField();
		textFieldFloors.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldFloors.setColumns(10);
		textFieldFloors.setBounds(156, 251, 86, 24);
		panel.add(textFieldFloors);
		
		JLabel label_4 = new JLabel("备注：");
		label_4.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_4.setBounds(79, 305, 63, 18);
		panel.add(label_4);
		
		textFieldRemark = new JTextField();
		textFieldRemark.setText("价格按天计算");
		textFieldRemark.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldRemark.setColumns(10);
		textFieldRemark.setBounds(156, 301, 176, 24);
		panel.add(textFieldRemark);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(90);
		panel_1.setPreferredSize(new Dimension(536, 50));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton buttonConfirm = new JButton("确定");
		buttonConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//获取界面上的数据
				String roomNo = textFieldRoomNo.getText();
				String bedNo = textFieldBedNo.getText();
				String bedType = textFieldRoomType.getText();
				String bedPrice = textFieldBedPrice.getText();//这里还可以做下数字验证
				String floors =  textFieldFloors.getText();
				String remark = textFieldRemark.getText();
				if(roomNo.length() <= 0 || bedNo.length() <= 0 || bedType.length() <= 0 ||
				bedPrice.length() <= 0 || floors.length() <= 0 || remark.length() <= 0){
					JOptionPane.showMessageDialog(null, "输入信息不完整");
				}else {
					//插入数据操作
					RoomAction roomAction = new RoomAction();
					try {
						roomAction.addRoomInfo(roomNo, bedNo, bedType, bedPrice, floors, remark);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		buttonConfirm.setFont(new Font("微软雅黑", Font.BOLD, 16));
		buttonConfirm.setBackground(Color.WHITE);
		panel_1.add(buttonConfirm);
		
		JButton buttonCancel = new JButton("取消");
		buttonCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dispose();
			}
		});
		buttonCancel.setFont(new Font("微软雅黑", Font.BOLD, 16));
		buttonCancel.setBackground(Color.WHITE);
		panel_1.add(buttonCancel);
	}
}
