package control;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.RoomFun;

public class RoomAction {
	//界面获取数据库中的房间信息
	public Object[][] getRoomInfo() throws SQLException {
		RoomFun roomFun = new RoomFun();
		int count = roomFun.getRoomTypeCount();
		Object result[][] = new Object[count][8];
		String rooms[][] = roomFun.query(count);
		for(int i= 0; i < count; i++)
		{
			result[i][0] = i+1;
			result[i][1] = rooms[i][0];
			result[i][2] = rooms[i][1];
			result[i][3] = rooms[i][2];
			result[i][4] = rooms[i][3];
			result[i][5] = rooms[i][4];
			result[i][6] = rooms[i][5];
			result[i][7] = rooms[i][6];
		}
		return result;
	}
	
	//删除界面上选中的房间信息
	public boolean delRoomInfo(String roomNo, String bedNo) throws SQLException
	{
		RoomFun roomFun = new RoomFun();
		if(roomFun.delRoom(roomNo, bedNo))
			JOptionPane.showMessageDialog(null, roomNo+"房间"+bedNo+"床位信息删除成功");
		else {
			JOptionPane.showMessageDialog(null, roomNo+"房间"+bedNo+"信息删除失败，请稍后再试");
		}
		return true;
	}
	
	//根据界面上的数据添加房间
	public void addRoomInfo(String roomNo, String bedNo, String bedType, String bedPrice, String floors, String remark) throws SQLException
	{
		RoomFun roomFun = new RoomFun();
		if(roomFun.addRoom(roomNo, bedNo, bedType, bedPrice, floors, remark))
		{
			JOptionPane.showMessageDialog(null, "新的房间信息添加成功");
		}else {
			JOptionPane.showMessageDialog(null, "新的房间信息添加失败，请稍后再试");
		}
	}
	
	//界面修改房间的信息
	public void changeRoomInfo(String roomNo, String bedNo, String bedType, String bedPrice, String floors, String remark) throws SQLException
	{
		RoomFun roomFun = new RoomFun();
		if(roomFun.changeRoom(roomNo, bedNo, bedType, bedPrice, floors, remark))
		{
			JOptionPane.showMessageDialog(null, "新的房间信息修改成功");
		}else {
			JOptionPane.showMessageDialog(null, "新的房间信息修改失败，请稍后再试");
		}
	}
}
