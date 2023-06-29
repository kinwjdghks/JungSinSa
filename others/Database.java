package others;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import java.util.LinkedList;

public class Database {
	
	//structure for single item
	public class ITEM{
		private String name;
		private int price;
		private int cate;
		private int idx;
		
		//includes category index and the number in which the item is placed.
		public ITEM(String name,int price,int cate,int idx) { 
			this.name = name;
			this.price = price;
			this.cate = cate;
			this.idx = idx;
		}
		public int getIC() { //get item category(0: top, 1: bottom, 2: outer, 3: shoes)
			return this.cate;
		}
		public int getII(){ //get item index (0,1,2, ...)
			return this.idx;
		}
		public String getItemName() { 
			return this.name;
		}
		public int getItemPrice() {
			return this.price;
		}
	};
	
	//strucure for single coupon 
	public class Coupon{
		private String name;
		private int discountRate;
		
		public Coupon(String name,int discountRate) {
			this.name = name;
			this.discountRate = discountRate;
		}
		
		public String getCouponName() {
			return name;
		}
		public int getCouponDiscount() {
			return discountRate;
		}
		
	};
	//linked list for coupons so that deletion is easy
	private LinkedList<Coupon> myCoupons = new LinkedList();
	public LinkedList<Coupon> getCouponList() {
		return myCoupons;
	}
	//given coupons at the first time
	private void giveInitialCoupons() {
		myCoupons.add(new Coupon("Welcome Coupon",15));
		myCoupons.add(new Coupon("Monthly discount Coupon",5));
	}
	public void deleteCoupon(int index) {
		myCoupons.remove(index);
	}
	
	//2-dim arraylist for the entire item
	private List<List<ITEM>> ITEMLIST = new ArrayList<>(); //single list of entire items. 
	public ITEM getITEM(int cate,int num) {
		return this.ITEMLIST.get(cate).get(num);
	}
	
	public void setItems() {
		List<ITEM> list_top = new ArrayList<ITEM>();
		List<ITEM> list_bottom = new ArrayList<ITEM>();
		List<ITEM> list_outer = new ArrayList<ITEM>();
		List<ITEM> list_shoes = new ArrayList<ITEM>();
		list_top.add(new ITEM("Checked Long Shirts",32000,0,0));
		list_top.add(new ITEM("C Logo SweatShirt",25900,0,1));
		list_top.add(new ITEM("Blue Oxford Shirt",33000,0,2));
		list_top.add(new ITEM("Grey Sport Hoody",29900,0,3));
		list_top.add(new ITEM("Denim Short Shirt",37000,0,4));
		list_bottom.add(new ITEM("Grey Summer Cargo Pants",36000,1,0));
		list_bottom.add(new ITEM("LightBlue Jeans",39200,1,1));
		list_bottom.add(new ITEM("Melange Training Half Pants",26900,1,2));
		list_bottom.add(new ITEM("White Denim Pants",29000,1,3));
		list_bottom.add(new ITEM("Wide Bending Beige Pants",48300,1,4));
		list_outer.add(new ITEM("Cord Hoody ZipUp",68000,2,0));
		list_outer.add(new ITEM("Coach WindBreaker",51900,2,1));
		list_outer.add(new ITEM("Denim Trucker Jacket",57400,2,2));
		list_outer.add(new ITEM("Gaff Cardigan",49900,2,3));
		list_outer.add(new ITEM("2-Way ZipUp Knit",54900,2,4));
		list_outer.add(new ITEM("Short Cotton Padding",68000,2,5));
		list_shoes.add(new ITEM("Old School 36 DX",78900,3,0));
		list_shoes.add(new ITEM("Chuck 70 Classic",69900,3,1));
		list_shoes.add(new ITEM("Vans Authentic",63100,3,2));
		list_shoes.add(new ITEM("Square Derby Shoes",81900,3,3));
		list_shoes.add(new ITEM("Black Leather Sandle",62000,3,4));
		list_shoes.add(new ITEM("Wheat Timberland Boots",95000,3,5));
		ITEMLIST.add(list_top);
		ITEMLIST.add(list_bottom);
		ITEMLIST.add(list_outer);
		ITEMLIST.add(list_shoes);
	}
	
	//getting first item name in the cart. this will be used in the DeliveringAnimation dialog.
	public String getFirstItemName() {		
		for(int i=0;i<4;i++) {
			for(int j=0;j<getItemAmount(i);j++) {
				if(cart.isPresent(i, j) == 1) {
					return getITEM(i,j).getItemName();	
				}
			}
		}
		return null;
	}
	//2-dim array for images
	private List<List<String>> itemPics = new ArrayList<>();
	
	public void setItemPics() {
		itemPics.add(Arrays.asList("/ItemImages/top_1.jpg","/ItemImages/top_2.jpg","/ItemImages/top_3.jpg","/ItemImages/top_4.jpg","/ItemImages/top_5.jpg"));
		itemPics.add(Arrays.asList("/ItemImages/bottom_1.jpg","/ItemImages/bottom_2.jpg","/ItemImages/bottom_3.jpg","/ItemImages/bottom_4.jpg","/ItemImages/bottom_5.jpg"));
		itemPics.add(Arrays.asList("/ItemImages/outer_1.jpg","/ItemImages/outer_2.jpg","/ItemImages/outer_3.jpg","/ItemImages/outer_4.jpg","/ItemImages/outer_5.jpg","/ItemImages/outer_6.jpg"));
		itemPics.add(Arrays.asList("/ItemImages/shoes_1.jpg","/ItemImages/shoes_2.jpg","/ItemImages/shoes_3.jpg","/ItemImages/shoes_4.jpg","/ItemImages/shoes_5.jpg","/ItemImages/shoes_6.jpg"));
	}
	public String getItemPics(int cate,int num) {
		return this.itemPics.get(cate).get(num);
	}
	
	//2-dim array for size options.
	private List<List<String>> sizeOption = new ArrayList<>();
	
	public void setSizeOption() {
		sizeOption.add(Arrays.asList("Choose Size","L","M","S"));
		sizeOption.add(Arrays.asList("Choose Size","XL","L","M","S","XS"));
		sizeOption.add(Arrays.asList("Choose Size","free"));
		sizeOption.add(Arrays.asList("Choose Size","160","165","170","175","180","185"));
		
	}
	public List<String> getSizeOption(int num) {
		return this.sizeOption.get(num);
	}
	
	//2-dim array for color options
	private List<List<String>> colorOption = new ArrayList<>();
		
		public void setColorOption() {
			colorOption.add(Arrays.asList("Choose Color","Black","White","Blue","Yellow"));
			colorOption.add(Arrays.asList("Choose Color","Black","White"));
			colorOption.add(Arrays.asList("Choose Color","Grey","Black","White"));
			colorOption.add(Arrays.asList("Choose Color","Black"));
			colorOption.add(Arrays.asList("Choose Color","White"));
			colorOption.add(Arrays.asList("Choose Color","Blue"));
			colorOption.add(Arrays.asList("Choose Color","Camel"));
			colorOption.add(Arrays.asList("Choose Color","Black","Red"));
			colorOption.add(Arrays.asList("Choose Color","Grey"));
		}
	public List<String> getColorOption(int num) {
		return this.colorOption.get(num);
	}
	//number of items in each categories
	private int[] itemAmount = {5,5,6,6};
	
	public int getItemAmount(int cate) {
		return this.itemAmount[cate];
	}
	
	/**
	 * Class which stores informations of user history.(Cart, purchased items)
	 * first index corresponds to the category, second to the item number,
	 * and third index stores {existence, sizearr, sizeidx, colorarr, coloridx}
	 * 0: existence : whether the item exists 
	 * 1: form of sizeoption 
	 * 2: sizeOption idx 
	 * 3: form of colorOption 
	 * 4: colorOption idx  
	 */
	public class storeSelection{
		private int[][][] array =  new int[4][][];
		private int itemCount; //number of items present in the array
		
		//
		public storeSelection() {
			for(int i=0;i<4;i++) {
				array[i] = new int[itemAmount[i]][];
				for(int j=0;j<itemAmount[i];j++) {
					array[i][j] = new int[5];
				}
			}
			itemCount = 0;
		}
		
		//insert item to the list. 
		public void addItem(int cate,int idx,int sizearr,int sizeidx,int colorarr,int coloridx) {
			this.array[cate][idx][0] = 1;
			this.array[cate][idx][1] = sizearr;
			this.array[cate][idx][2] = sizeidx;
			this.array[cate][idx][3] = colorarr;
			this.array[cate][idx][4] = coloridx;
			itemCount++;
		}
		
		public int getItemCount() {
			return this.itemCount;
		}
		public int[] getItemInfo(int cate,int idx) {
			return this.array[cate][idx];
		}
		public void removeFromArray(int cate,int idx) {
			this.array[cate][idx][0] = 0;
			itemCount--;
		}
		public int isPresent(int cate,int idx) {
			return this.array[cate][idx][0];
		}
		
	}
	// store items users put into the cart
	public storeSelection cart = new storeSelection(); 
	//store items users have actually bought
	public storeSelection bought = new storeSelection(); 
	
	/*
	 * create receipt String. This will be printed to the txt file.
	 */
	public String makeReceipt(int totalcost,int couponindex) {
		// Get the current date and time
		LocalDateTime currentDateTime = LocalDateTime.now();
		// Format the current date and time as a string
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String formattedDateTime = currentDateTime.format(formatter);
		
		String receipt = "******** JungSinSa ********\n\n";
		receipt = receipt.concat("Date: "+formattedDateTime+"\n\n");
		for(int i=0;i<4;i++) {
			for(int j=0;j<getItemAmount(i);j++) {
				//if the item is in the cart, add to the receipt.
				if(cart.array[i][j][0]==1) { 
					receipt = receipt.concat(ITEMLIST.get(i).get(j).getItemName()+" - "+ITEMLIST.get(i).get(j).getItemPrice()+"₩\n");
				}
			}
		}
		receipt = receipt.concat("\n\n");
		//if user used any coupon.
		if(couponindex != 0 && couponindex != -1) { 
			receipt = receipt.concat("Coupon used: "+myCoupons.get(couponindex).getCouponName()+" - "+myCoupons.get(couponindex).getCouponDiscount()+"% discount\n\n");
		}
		receipt = receipt.concat("Total cost: "+totalcost+"₩\n\n");
		receipt = receipt.concat("Thank you for purchasing.\n");
		receipt = receipt.concat("***************************");
		
		return receipt;
	}
	
	//renew bought array and empty the cart
	public void purchase_complete(storeSelection cart, storeSelection bought) {
		for(int i=0;i<4;i++) {
			for(int j=0;j<getItemAmount(i);j++) {
				if(cart.array[i][j][0]==1) { //if the item is in the cart,
					System.arraycopy(cart.array[i][j], 0, bought.array[i][j], 0, 5);
					cart.removeFromArray(i,j);//and delete items from the cart.
				}
			}
		}
		bought.itemCount = cart.itemCount;
		cart.itemCount = 0;
	}
	
	/*
	 * arraylist for reviews of specific items.
	 * each item has their own arraylist for the review Strings.
	 */
	private List<List<ArrayList<String>>> itemReview;
		private void setItemReview() {
	        itemReview = new ArrayList<>();
	
	        for (int i=0;i<4;i++) { // number of categories
	            List<ArrayList<String>> category = new ArrayList<>();
	            for (int j=0;j<getItemAmount(i);j++) {
	                ArrayList<String> review = new ArrayList<>();
	                category.add(review);
	            }
	            itemReview.add(category);
	        }
	    }
		public void write_review(ITEM item,String comment) {
			itemReview.get(item.getIC()).get(item.getII()).add(comment);
			return;
		}
		public ArrayList<String> getItemReview(ITEM item){
			return itemReview.get(item.getIC()).get(item.getII());
		}
	
	public Database() {
		//init database for one user
		setItemPics();
		setItems();
		setSizeOption();
		setColorOption();
		setItemReview();
		giveInitialCoupons();
		
	}
	
	
	
}
