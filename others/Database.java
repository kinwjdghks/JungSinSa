package others;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
	
	public void set_items() {
		List<ITEM> list_top = new ArrayList<ITEM>();
		List<ITEM> list_bottom = new ArrayList<ITEM>();
		List<ITEM> list_outer = new ArrayList<ITEM>();
		List<ITEM> list_shoes = new ArrayList<ITEM>();
		list_top.add(new ITEM("Checked Long Shirts",32000));
		list_top.add(new ITEM("C Logo SweatShirt",25000));
		list_top.add(new ITEM("Blue Oxford Shirt",30000));
		list_top.add(new ITEM("Grey Sport Hoody",29000));
		list_top.add(new ITEM("Denim Short Shirt",37000));
		list_bottom.add(new ITEM("Grey Summer Cargo Pants",36000));
		list_bottom.add(new ITEM("LightBlue Jeans",39000));
		list_bottom.add(new ITEM("Melange Training Half Pants",26000));
		list_bottom.add(new ITEM("White Denim Pants",29000));
		list_bottom.add(new ITEM("Wide Bending Beige Pants",48000));
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
	private List<List<String>> sizeOption = new ArrayList<>();
		
		public void set_sizeOption() {
			sizeOption.add(Arrays.asList("Choose Color","Black","White","Blue","Yellow"));
			sizeOption.add(Arrays.asList("Choose Color","Black","White"));
		}
		public List<String> get_sizeOption(int num) {
			return this.sizeOption.get(num);
		}
	private List<List<String>> colorOption = new ArrayList<>();
		
		public void set_colorOption() {
			colorOption.add(Arrays.asList("Choose Size","L","M","S"));
			colorOption.add(Arrays.asList("Choose Size","XL","L","M","S","XS"));
			colorOption.add(Arrays.asList("Choose Size","freesize"));
		}
		public List<String> get_colorOption(int num) {
			return this.colorOption.get(num);
		}
	private int[] itemAmount = {5,5,0,0}; //item amount at each categories
	public int get_itemAmount(int cate) {
		return this.itemAmount[cate];
	}
	private boolean[][] cart = new boolean[4][]; //items in the cart
	private int cart_num;
	public int get_cartnum() {
		return cart_num;
	}
	private void set_cart() {
		for(int i=0;i<4;i++) {
			cart[i] = new boolean[this.itemAmount[i]];
		}
		cart_num = 0;
	}
	public void add_cart(int cate,int idx) {
		this.cart[cate][idx] = true;
		cart_num++;
	}
	public void remove_cart(int cate,int idx) {
		this.cart[cate][idx] = false;
		cart_num--;
	}
	public boolean is_cart(int cate,int idx) {
		return this.cart[cate][idx];
	}
	private boolean[][] is_bought = new boolean[4][]; //items user has already bought
	
	public void set_is_bought() {
		for(int i=0;i<4;i++) {
			is_bought[i] = new boolean[itemAmount[i]];
		}
	}
	public boolean get_is_bought(int cate,int idx) {
		return this.is_bought[cate][idx];
	}
	public void purchase_complete() {
		//renew is_bought array & empty cart
		for(int i=0;i<4;i++) {
			for(int j=0;j<get_itemAmount(i);j++) {
				this.is_bought[i][j] = this.cart[i][j];
				cart[i][j] = false;
			}
		}
	}
	public Database() {
		set_itemPics();
		set_items();
		set_sizeOption();
		set_colorOption();
		set_cart();
		set_is_bought();
	}
	
	
	
}
