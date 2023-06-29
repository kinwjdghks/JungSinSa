package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import others.Database;

import java.awt.Image;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MyCart extends JFrame {

	private int totalCost;
	private JPanel contentPane;
	private JPanel panel_bottom;
	private JButton btn_purchase;
	private JButton btn_close;
	private JScrollPane scrollPane;
	private JPanel panel_main;
	private JLabel lblcost;
	private JLabel lblcostnum;
	
	//create a custom font.
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
	//resize image
	private ImageIcon iconSetSize(String URL,int width,int height) { //return size adjusted ImageIcon.
		ImageIcon origin = new ImageIcon(JungSinSa_Main.class.getResource(URL));
		Image resizedImg = origin.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImg);
	}
	
	/**
	 * Create the frame.
	 */	
	public MyCart(JFrame parentFrame,Database data) {
	
		
		setTitle("My Cart");
		int frameheight = Math.max(650,650);
		setBounds(140, 140, 400, frameheight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_main = new JPanel();
		panel_main.setPreferredSize(new Dimension(400,Math.max(512, 5+125*data.cart.getItemCount())));
		FlowLayout flowLayout = (FlowLayout) panel_main.getLayout();
		panel_main.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane = new JScrollPane(panel_main);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 400, frameheight-128);
		contentPane.add(scrollPane);
		
		//if the cart is empty, notice.
		if(data.cart.getItemCount()==0) {
			JLabel lbl_empty = new JLabel("Cart is empty!");
			lbl_empty.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_empty.setPreferredSize(new Dimension(300,200));
			lbl_empty.setFont(setfont().deriveFont(Font.BOLD,20));
			panel_main.add(lbl_empty);
		}
		totalCost = 0;
		//dynamically list added items.
		for(int i=0;i<4;i++) {
			for(int j=0;j<data.getItemAmount(i);j++) {
				if(data.cart.isPresent(i, j) == 1) {
					totalCost += data.getITEM(i, j).getItemPrice();
					
					JPanel itembox = new JPanel();
					itembox.setPreferredSize(new Dimension(380,120));
					panel_main.add(itembox);
					itembox.setLayout(null);
					
					JLabel lbl_itemPic = new JLabel("");
					lbl_itemPic.setBounds(6, 6, 110, 108);
					lbl_itemPic.setIcon(iconSetSize(data.getItemPics(i, j), 110, 108));
					itembox.add(lbl_itemPic);
					
					JLabel lbl_itemName = new JLabel(data.getITEM(i, j).getItemName());
					lbl_itemName.setFont(setfont().deriveFont(Font.BOLD,17));
					lbl_itemName.setBounds(130, 6, 262, 50);
					itembox.add(lbl_itemName);		
					
					JLabel lbl_itemSize = new JLabel("Size: "+data.getSizeOption(data.cart.getItemInfo(i, j)[1]).get(data.cart.getItemInfo(i, j)[2])); 
					lbl_itemSize.setFont(setfont().deriveFont(Font.BOLD,16));
					lbl_itemSize.setBounds(140, 48, 100, 40);
					itembox.add(lbl_itemSize);
					
					JLabel lbl_itemColor = new JLabel("Color: "+ data.getColorOption(data.cart.getItemInfo(i, j)[3]).get(data.cart.getItemInfo(i, j)[4])); 
					lbl_itemColor.setFont(setfont().deriveFont(Font.BOLD,16));
					lbl_itemColor.setBounds(140, 74, 100, 40);
					itembox.add(lbl_itemColor);
					
					JLabel lbl_itemCost = new JLabel(Integer.toString(data.getITEM(i, j).getItemPrice())+" won");
					lbl_itemCost.setFont(setfont().deriveFont(Font.BOLD,16));
					lbl_itemCost.setBounds(290, 74, 110, 40);
					itembox.add(lbl_itemCost);
				}
			}
		}
		panel_bottom = new JPanel();
		panel_bottom.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		panel_bottom.setBounds(0, 522, 400, 100);
		contentPane.add(panel_bottom);
		panel_bottom.setLayout(null);
		
		btn_purchase = new JButton("Purchase");
		//goto purchase state
		btn_purchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//failure if there is no item
				if(data.cart.getItemCount() == 0) {
					JOptionPane.showMessageDialog(MyCart.this,"There is no item to purchase!", "Information", JOptionPane.WARNING_MESSAGE);
				}
				else{
					//open Proceedpurchase frame
					ProceedPurchase proceedpurchase = new ProceedPurchase(MyCart.this,data,totalCost);
					proceedpurchase.setVisible(true);
				}
			}
		});
		btn_purchase.setFont(setfont().deriveFont(Font.BOLD,14));
		btn_purchase.setBounds(217, 57, 80, 35);
		btn_purchase.setPreferredSize(new Dimension(80,35));
		panel_bottom.add(btn_purchase);
		
		btn_close = new JButton("Close");
		btn_close.setFont(setfont().deriveFont(Font.BOLD,14));
		btn_close.setBounds(309, 57, 80, 35);
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyCart.this.dispose();
			}
		});
		
		btn_close.setPreferredSize(new Dimension(80,35));
		panel_bottom.add(btn_close);
		
		lblcost = new JLabel("Total cost: ");
		lblcost.setFont(setfont().deriveFont(Font.BOLD,18));
		lblcost.setHorizontalAlignment(SwingConstants.CENTER);
		lblcost.setBounds(171, 16, 100, 29);
		panel_bottom.add(lblcost);
		
		lblcostnum = new JLabel(totalCost+" won");
		lblcostnum.setFont(setfont().deriveFont(Font.BOLD,18));
		lblcostnum.setHorizontalAlignment(SwingConstants.CENTER);
		lblcostnum.setBounds(277, 16, 112, 29);
		panel_bottom.add(lblcostnum);

	}
}
