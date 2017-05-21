package model;

public class Medicine {
	private String itemName; //项目名称
	private String costType; //费用类型
	private String kind; //种类
	private String unit; //单位
	private float prePrice; //单价
	//后来加上的数据成员
	private int amount; //数量
	private float cost; //费用
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public float getPrePrice() {
		return prePrice;
	}
	public void setPrePrice(float prePrice) {
		this.prePrice = prePrice;
	}
	
}
