package GUI;

import others.Database;
import others.Database.Coupon;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class ProceedPurchase extends JFrame {

	private JPanel contentPane;
	private JLabel lblPurchaseConfirmation;
	private ArrayList<String> model;
	private LinkedList<Coupon> myCouponList;
	private JComboBox comboBox_coupons;
	private JLabel lbl_useCoupon;
	private JLabel lblNewLabel;
	private JLabel lbl_original;
	private JLabel lbl_discounted;
	private JButton btn_cancel;
	private JButton btn_confirm;
	private int initialCost;
	private int tempCost;
	private String firstItemName;
	private String receipt;
	//create custom font
	private Font setfont() {
		try {
			Font font = Font.createFont(Font.TRUETYPE_FONT, JungSinSa_Main.class.getResourceAsStream("Quincy-Regular.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
			return font;
		}catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/**
	 * Create the frame.
	 */
	public ProceedPurchase(JFrame parentframe,Database data,int totalCost) {
		//get coupon list user have.
		myCouponList = data.getCouponList();
		//very initial cost. won't change.
		initialCost = totalCost;
		//possibly change due to discount
		tempCost = totalCost;
		firstItemName = data.getFirstItemName();
		
		setBounds(100, 100, 300, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPurchaseConfirmation = new JLabel("Purchase confirmation");
		lblPurchaseConfirmation.setBounds(25, 10, 250, 40);
		lblPurchaseConfirmation.setHorizontalAlignment(SwingConstants.CENTER);
		lblPurchaseConfirmation.setPreferredSize(new Dimension(250,40));
		lblPurchaseConfirmation.setFont(setfont().deriveFont(Font.BOLD,20));
		contentPane.add(lblPurchaseConfirmation);
		
		//user could choose to use discount coupon.
		lbl_useCoupon = new JLabel("If you want some discount...");
		lbl_useCoupon.setBounds(25, 55, 250, 30);
		lbl_useCoupon.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_useCoupon.setPreferredSize(new Dimension(250,30));
		lbl_useCoupon.setFont(setfont().deriveFont(Font.BOLD,15));
		contentPane.add(lbl_useCoupon);
		
		//make coupon selection combobox
		model = new ArrayList<String>();
		ListIterator<Coupon> iter = myCouponList.listIterator();
		model.add("----------------");
		while(iter.hasNext()) {
			model.add(iter.next().getCouponName());
		}
		comboBox_coupons = new JComboBox(model.toArray());
		comboBox_coupons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_coupons.getSelectedIndex() == 0) {
					lbl_original.setFont(setfont().deriveFont(Font.BOLD,16));
					tempCost = initialCost;
					lbl_discounted.setText("");
				}
				else {
					//visualize crossing out original price number and write over new price
					Font font = setfont().deriveFont(Font.BOLD, 16);
					Map  attributes = font.getAttributes();
					attributes.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
					Font newFont = new Font(attributes);
					lbl_original.setFont(newFont);
					tempCost = ((initialCost/100)*(100-myCouponList.get(comboBox_coupons.getSelectedIndex()-1).getCouponDiscount()));
					lbl_discounted.setText(tempCost +" won");
					
				}
			}
		});
		comboBox_coupons.setBounds(60, 90, 180, 30);
		contentPane.add(comboBox_coupons);
		
		lblNewLabel = new JLabel("Total Cost: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(setfont().deriveFont(Font.BOLD,16));
		lblNewLabel.setBounds(60, 140, 109, 30);
		contentPane.add(lblNewLabel);
		
		lbl_original = new JLabel(totalCost+" won");
		lbl_original.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_original.setFont(setfont().deriveFont(Font.BOLD,16));
		lbl_original.setBounds(166, 140, 109, 30);
		contentPane.add(lbl_original);
		
		lbl_discounted = new JLabel("");
		lbl_discounted.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_discounted.setFont(setfont().deriveFont(Font.BOLD,16));
		lbl_discounted.setBounds(166, 120, 109, 30);
		contentPane.add(lbl_discounted);
		
		btn_cancel = new JButton("Cancel");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_cancel.setFont(setfont().deriveFont(Font.BOLD,14));
		btn_cancel.setBounds(176, 182, 117, 29);
		contentPane.add(btn_cancel);
		
		btn_confirm = new JButton("Confirm");
		btn_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//make receipt right here and pass it to the DeliveringAnimation.
				receipt = data.makeReceipt(tempCost, comboBox_coupons.getSelectedIndex()-1);
				//open DeliveringAnimation Dialog
				DeliveringAnimation deliverComplete = new DeliveringAnimation(ProceedPurchase.this,data,tempCost,firstItemName,receipt);
				deliverComplete.setVisible(true);	
				//clear cart and update boughtlist
				data.purchase_complete(data.cart,data.bought);
				//delete used coupon
				if(comboBox_coupons.getSelectedIndex() != 0) {
					data.deleteCoupon(comboBox_coupons.getSelectedIndex()-1);
				}
				//close frame including parentframe.
				parentframe.dispose();
				dispose();
			}
		});
		btn_confirm.setFont(setfont().deriveFont(Font.BOLD,14));
		btn_confirm.setBounds(60, 182, 117, 29);
		contentPane.add(btn_confirm);
		

	}
}
