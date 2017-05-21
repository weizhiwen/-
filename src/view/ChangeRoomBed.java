package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import control.RoomAction;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ChangeRoomBed extends JFrame {

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
					ChangeRoomBed frame = new ChangeRoomBed();
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
	public ChangeRoomBed() {
		setTitle("修改床位信息");
		setIconImage(new ImageIcon("img/yz.jpg").getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 443, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5E8A\u4F4D\u4FE1\u606F\u4FEE\u6539\u680F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("房间号：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label.setBounds(65, 45, 72, 18);
		panel.add(label);
		
		textFieldRoomNo = new JTextField(DDRoomType.roomNo);
		textFieldRoomNo.setEditable(false);
		textFieldRoomNo.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldRoomNo.setColumns(10);
		textFieldRoomNo.setBounds(156, 43, 86, 24);
		panel.add(textFieldRoomNo);
		
		JLabel label_1 = new JLabel("床位号：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_1.setBounds(65, 96, 72, 18);
		panel.add(label_1);
		
		textFieldBedNo = new JTextField(DDRoomType.bedNo);
		textFieldBedNo.setEditable(false);
		textFieldBedNo.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldBedNo.setColumns(10);
		textFieldBedNo.setBounds(156, 94, 86, 24);
		panel.add(textFieldBedNo);
		
		JLabel label_2 = new JLabel("房间类型：");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_2.setBounds(48, 146, 86, 18);
		panel.add(label_2);
		
		textFieldRoomType = new JTextField(DDRoomType.roomType);
		textFieldRoomType.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldRoomType.setColumns(10);
		textFieldRoomType.setBounds(156, 142, 201, 24);
		panel.add(textFieldRoomType);
		
		JLabel label_3 = new JLabel("床位价格：");
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_3.setBounds(48, 195, 89, 18);
		panel.add(label_3);
		
		textFieldBedPrice = new JTextField(DDRoomType.bedPrice);
		textFieldBedPrice.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldBedPrice.setColumns(10);
		textFieldBedPrice.setBounds(156, 193, 86, 24);
		panel.add(textFieldBedPrice);
		
		JLabel label_4 = new JLabel("楼层：");
		label_4.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_4.setBounds(79, 247, 63, 18);
		panel.add(label_4);
		
		textFieldFloors = new JTextField(DDRoomType.floors);
		textFieldFloors.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldFloors.setColumns(10);
		textFieldFloors.setBounds(156, 243, 86, 24);
		panel.add(textFieldFloors);
		
		JLabel label_5 = new JLabel("备注：");
		label_5.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_5.setBounds(79, 297, 63, 18);
		panel.add(label_5);
		
		textFieldRemark = new JTextField(DDRoomType.remark);
		textFieldRemark.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textFieldRemark.setColumns(10);
		textFieldRemark.setBounds(156, 293, 176, 24);
		panel.add(textFieldRemark);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(80);
		panel_1.setPreferredSize(new Dimension(498, 50));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton buttonConfirm = new JButton("确定");
		buttonConfirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String roomNo = textFieldRoomNo.getText();
				String bedNo = textFieldBedNo.getText();
				String roomType = textFieldRoomType.getText();
				String bedPrice = textFieldBedPrice.getText();
				String floors = textFieldFloors.getText();
				String remark = textFieldRemark.getText();
				RoomAction roomAction = new RoomAction();
				try {
					roomAction.changeRoomInfo(roomNo, bedNo, roomType, bedPrice, floors, remark);
				} catch (SQLException e1) {
					e1.printStackTrace();
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
