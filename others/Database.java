package others;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import GUI.JungSinSa_Main.ITEM;

public class Database {
	
	//structure for item
	public class ITEM{
		private String name;
		private int price;
		
		public ITEM(String name,int price) {
			this.name = name;
			this.price = price;
		}
		public String getItemName() {
			return name;
		}
		public int getItemPrice() {
			return price;
		}
	};
	//item data
	private List<List<ITEM>> ITEMLIST = new ArrayList<>(); //one list of entire items. 
	public ITEM get_ITEM(int cate,int num) {
		return this.ITEMLIST.get(cate).get(num);
	}
	
	private void set_items() {
		List<ITEM> list_top = new ArrayList<ITEM>();
		List<ITEM> list_bottom = new ArrayList<ITEM>();
		List<ITEM> list_outer = new ArrayList<ITEM>();
		List<ITEM> list_shoes = new ArrayList<ITEM>();
		list_top.add(new ITEM("Checked Long Shirts",32000));
		list_top.add(new ITEM("C Logo SweatShirt",25000));
		list_top.add(new ITEM("Blue Oxford Shirt",30000));
		list_top.add(new ITEM("Grey Sport Hoody",29000));
		list_top.add(new ITEM("Denim Short Shirt",37000));
		list_top.add(new ITEM("Grey Summer Cargo Pants",32000));
		list_top.add(new ITEM("LightBlue Jeans",39000));
		list_top.add(new ITEM("Melange Training Half Pants",30000));
		list_top.add(new ITEM("White Denim Pants",29000));
		list_top.add(new ITEM("Wide Bending Beige Pants",48000));
		ITEMLIST.add(list_top);
		ITEMLIST.add(list_bottom);
	}
	//picture data
	private List<List<String>> itemPics = new ArrayList<>();
	
	public void set_itemPics() {
		itemPics.add(Arrays.asList("/ItemImages/top_1.jpg","/ItemImages/top_2.jpg","/ItemImages/top_3.jpg","/ItemImages/top_4.jpg","/ItemImages/top_5.jpg"));
		itemPics.add(Arrays.asList("/ItemImages/bottom_1.jpg","/ItemImages/bottom_2.jpg","/ItemImages/bottom_3.jpg","/ItemImages/bottom_4.jpg","/ItemImages/bottom_5.jpg"));
	}
	public String get_itemPics(int cate,int num) {
		return this.itemPics.get(cate).get(num);
	}
	
	//Options data
	private	String[] ColorOption1 = {"Choose Color","Black","White","Blue","Yellow"};
	private	String[] ColorOption2 = {"Choose Color","Black","White"};
	private	String[] SizeOption1 = {"Choose Size","L","M","S"};
	private	String[] SizeOption2 = {"Choose Size","XL","L","M","S","XS"};
	private	String[] SizeOption3 = {"Choose Size","freesize"};
	
	
	
	
	
}
