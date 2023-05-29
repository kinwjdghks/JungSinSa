package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import others.Database;
import java.awt.Window.Type;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyCart extends JFrame {

	private JPanel contentPane;
	private JPanel panel_bottom;
	private JButton btn_purchase;
	private JButton btn_close;
	private JScrollPane scrollPane;
	private JPanel panel_main;
	
	private class createDynamicList implements Runnable{
		private ImageIcon iconSetSize(String URL,int width,int height) { //return size adjusted ImageIcon.
			ImageIcon origin = new ImageIcon(JungSinSa_Main.class.getResource(URL));
			Image resizedImg = origin.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
			return new ImageIcon(resizedImg);
		}
		private Database data;
		public createDynamicList(Database data,JPanel panel) {
			this.data = data;
		}
		@Override
		public void run() {
			int i,j;
			if(data.get_cartnum()==0) {
				JLabel lbl_empty = new JLabel("There are no items in your cart.");
				lbl_empty.setFont(new Font("Heiti TC", Font.BOLD, 20));
				panel_main.add(lbl_empty);
			}
			for(i=0;i<4;i++) {
				for(j=0;j<data.get_itemAmount(i);j++) {
					if(data.is_cart(i, j)==true) {
						
						JPanel itembox = new JPanel();
						itembox.setPreferredSize(new Dimension(380,120));
						panel_main.add(itembox);
						itembox.setLayout(null);
						
						JLabel lbl_itemPic = new JLabel("");
						lbl_itemPic.setBounds(6, 6, 110, 108);
						lbl_itemPic.setIcon(iconSetSize(data.get_itemPics(i, j), 110, 108));
						itembox.add(lbl_itemPic);
						
						JLabel lbl_itemName = new JLabel(data.get_ITEM(i, j).getItemName());
						lbl_itemName.setBounds(122, 6, 252, 40);
						itembox.add(lbl_itemName);
						
						JLabel lbl_itemSize = new JLabel("XS"); //미
						lbl_itemSize.setBounds(122, 74, 110, 40);
						itembox.add(lbl_itemSize);
						
						JLabel lbl_itemCost = new JLabel(Integer.toString(data.get_ITEM(i, j).getItemPrice())+"won");
						lbl_itemCost.setBounds(247, 74, 120, 40);
						itembox.add(lbl_itemCost);
						
						
					}
				}
			}

		}
	}
	
	/**
	 * Create the frame.
	 */	
	public MyCart(JFrame parentFrame,Database data) {
		
		
		setTitle("My Cart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int frameheight = Math.min(650,650);
		setBounds(100, 100, 400, frameheight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_bottom = new JPanel();
		panel_bottom.setBorder(new LineBorder(new Color(0, 0, 0), 1));
		FlowLayout fl_panel_bottom = (FlowLayout) panel_bottom.getLayout();
		fl_panel_bottom.setHgap(10);
		fl_panel_bottom.setVgap(6);
		fl_panel_bottom.setAlignment(FlowLayout.RIGHT);
		panel_bottom.setBounds(0, frameheight-78, 400, 50);
		contentPane.add(panel_bottom);
		
		btn_purchase = new JButton("Purchase");
		btn_purchase.setPreferredSize(new Dimension(80,35));
		panel_bottom.add(btn_purchase);
		
		btn_close = new JButton("Close");
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyCart.this.dispose();
			}
		});
		btn_close.setPreferredSize(new Dimension(80,35));
		panel_bottom.add(btn_close);
		
		panel_main = new JPanel();
		panel_main.setPreferredSize(new Dimension(400,frameheight));
		FlowLayout flowLayout = (FlowLayout) panel_main.getLayout();
		panel_main.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scrollPane = new JScrollPane(panel_main);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 400, frameheight-78);
		contentPane.add(scrollPane);
		
		createDynamicList create = new createDynamicList(data,panel_main);
		
		
		create.run();
		
		
	}
}
