package GUI;
import others.Database;
import others.Database.ITEM;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.View;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JungSinSa_Main extends JFrame {
	Database data;
	//frame size values
	private int itemSlotWidth = 250;
	private int itemSlotHeight = 275;
	private int categoryHeight = 300;
	private int categoryWidth = 1400;
	//components
	private JPanel contentPane;
	private JPanel bottomPanel;
	private JButton ViewMyCart;
	private JButton ViewCoupons;
	private JLabel TopLogo;
	private JScrollPane MainScrollPane;
	private JPanel Mainpanel;
	private JScrollPane scrollPane_1;
	private JPanel panel_top;
	private JScrollPane scrollPane_2;
	private JPanel panel_bottom;
	private JScrollPane scrollPane_3;
	private JPanel panel_outer;
	private JScrollPane scrollPane_4;
	private JPanel panel_shoes;
	private JLabel lbl_Top;
	private JLabel lbl_Bottom;
	private JLabel lbl_Outer;
	private JLabel lbl_Shoes;
	
	//resize image
	private ImageIcon iconSetSize(String URL,int width,int height) { //return size adjusted ImageIcon.
		ImageIcon origin = new ImageIcon(JungSinSa_Main.class.getResource(URL));
		Image resizedImg = origin.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImg);
	}
	//create custom font
	private Font setfont() {
		try {
			//create a custom font.
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JungSinSa_Main frame = new JungSinSa_Main();
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
	public JungSinSa_Main() {
		int i;
		//make user database.
		data = new Database();
		
		setResizable(false);
		setTitle("JungSinSa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setSize(this.getSize());
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		TopLogo = new JLabel("");
		TopLogo.setIcon(iconSetSize("/ItemImages/toplogo.jpg",contentPane.getWidth(),200));
		TopLogo.setPreferredSize(new Dimension(contentPane.getWidth(),150));
		contentPane.add(TopLogo, BorderLayout.NORTH);
		
		bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(contentPane.getWidth(),60));
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		
		ViewCoupons = new JButton("My Coupons");
		ViewCoupons.setFont(setfont().deriveFont(Font.BOLD,16));
		ViewCoupons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//view coupon list
				MyCouponList frame = new MyCouponList(data);
				frame.setVisible(true);
			}
		});
		ViewCoupons.setPreferredSize(new Dimension(120,50));
		bottomPanel.add(ViewCoupons);
		
		ViewMyCart = new JButton("My Cart");
		ViewMyCart.setFont(setfont().deriveFont(Font.BOLD,16));
		ViewMyCart.setPreferredSize(new Dimension(80,50));
		ViewMyCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//view my cart
				MyCart frame = new MyCart(JungSinSa_Main.this,data);
				frame.setVisible(true);
			}
		});
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 5));
		bottomPanel.add(ViewMyCart);
		
		Mainpanel = new JPanel();
		Mainpanel.setPreferredSize(new Dimension(this.getWidth(),1420));
		
		MainScrollPane = new JScrollPane(Mainpanel);
		MainScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(MainScrollPane, BorderLayout.CENTER);
		
		
		FlowLayout fl_Mainpanel = (FlowLayout) Mainpanel.getLayout();
		fl_Mainpanel.setHgap(0);
		fl_Mainpanel.setVgap(0);
		
		/*
		 * category-top
		 */
		
		lbl_Top = new JLabel("  Top");
		lbl_Top.setFont(setfont().deriveFont(Font.BOLD,30));
		
		lbl_Top.setPreferredSize(new Dimension(this.getWidth(),50));
		Mainpanel.add(lbl_Top);
				
		panel_top = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_top.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout_1.setHgap(15);
		panel_top.setPreferredSize(new Dimension(categoryWidth,categoryHeight));
		scrollPane_1 = new JScrollPane(panel_top);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setPreferredSize(new Dimension(this.getWidth(),categoryHeight));
		Mainpanel.add(scrollPane_1);
		
		//this array stores size and color options for each items.
		int[] TopSizeOptionArr = {0,0,0,2,1};
		int[] TopColorOptionArr = {2,2,5,8,5};
		//dynamically create item boxes.
		for(i=0;i<data.getItemAmount(0);i++) {
			final int itemnum=i;
			JPanel topItem = new JPanel();
			topItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//go to specific item description
					ItemDescription dialog = new ItemDescription(JungSinSa_Main.this,data,data.getITEM(0,itemnum),TopSizeOptionArr[itemnum],TopColorOptionArr[itemnum],"/ItemImages/topsizechart.jpg");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			});
			topItem.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
			panel_top.add(topItem);
			topItem.setLayout(new BorderLayout(5, 5));
			
			JLabel imgTopItem = new JLabel("");
			imgTopItem.setIcon(iconSetSize(data.getItemPics(0,itemnum),itemSlotWidth,itemSlotHeight));
			topItem.add(imgTopItem, BorderLayout.CENTER);
			JLabel lbltopItem = new JLabel(data.getITEM(0, itemnum).getItemName());
			lbltopItem.setFont(setfont().deriveFont(Font.BOLD,14));
			lbltopItem.setHorizontalAlignment(SwingConstants.CENTER);
			topItem.add(lbltopItem, BorderLayout.SOUTH);
			
		}
		/*
		 * category-bottom
		 */
		lbl_Bottom = new JLabel("  Bottom");
		lbl_Bottom.setFont(setfont().deriveFont(Font.BOLD,30));
		lbl_Bottom.setPreferredSize(new Dimension(this.getWidth(),50));
		Mainpanel.add(lbl_Bottom);
				
		panel_bottom = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_bottom.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setHgap(15);
		panel_bottom.setPreferredSize(new Dimension(categoryWidth,categoryHeight));
		scrollPane_2 = new JScrollPane(panel_bottom);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_2.setPreferredSize(new Dimension(this.getWidth(),categoryHeight));
		Mainpanel.add(scrollPane_2);
		
		int[] BottomSizeOptionArr = {2,0,2,1,1};
		int[] BottomColorOptionArr = {8,5,2,4,6};
		for(i=0;i<data.getItemAmount(1);i++) {
			final int itemnum=i;
			
			JPanel bottomItem = new JPanel();
			bottomItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ItemDescription dialog = new ItemDescription(JungSinSa_Main.this,data,data.getITEM(1,itemnum),BottomSizeOptionArr[itemnum],BottomColorOptionArr[itemnum],"/ItemImages/pantssizechart.jpg");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			});
			bottomItem.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
			panel_bottom.add(bottomItem);
			bottomItem.setLayout(new BorderLayout(5, 5));
			
			JLabel imgBottomItem = new JLabel("");
			imgBottomItem.setIcon(iconSetSize(data.getItemPics(1,itemnum),itemSlotWidth,itemSlotHeight));
			bottomItem.add(imgBottomItem, BorderLayout.CENTER);
			JLabel lblbottomItem = new JLabel(data.getITEM(1, itemnum).getItemName());
			lblbottomItem.setFont(setfont().deriveFont(Font.BOLD,14));
			lblbottomItem.setHorizontalAlignment(SwingConstants.CENTER);
			bottomItem.add(lblbottomItem, BorderLayout.SOUTH);
			
		}
		
		/*
		 * category-outer
		 */
		lbl_Outer = new JLabel("  Outer");
		lbl_Outer.setPreferredSize(new Dimension(this.getWidth(),50));
		lbl_Outer.setFont(setfont().deriveFont(Font.BOLD,30));
		Mainpanel.add(lbl_Outer);
		
		panel_outer = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_outer.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		flowLayout_3.setHgap(15);
		panel_outer.setPreferredSize(new Dimension(categoryWidth+230,categoryHeight));
		scrollPane_3 = new JScrollPane(panel_outer);
		scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_3.setPreferredSize(new Dimension(this.getWidth(),categoryHeight));
		Mainpanel.add(scrollPane_3);
		
		int[] OuterSizeOptionArr = {1,1,0,2,0,0};
		int[] OuterColorOptionArr = {3,8,5,3,4,8};
		for(i=0;i<data.getItemAmount(2);i++) {
			final int itemnum=i;
			JPanel outerItem = new JPanel();
			outerItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ItemDescription dialog = new ItemDescription(JungSinSa_Main.this,data,data.getITEM(2,itemnum),OuterSizeOptionArr[itemnum],OuterColorOptionArr[itemnum],"/ItemImages/topsizechart.jpg");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			});
			outerItem.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
			panel_outer.add(outerItem);
			outerItem.setLayout(new BorderLayout(5, 5));
			
			JLabel imgouterItem = new JLabel("");
			imgouterItem.setIcon(iconSetSize(data.getItemPics(2,itemnum),itemSlotWidth,itemSlotHeight));
			outerItem.add(imgouterItem, BorderLayout.CENTER);
			JLabel lblouterItem = new JLabel(data.getITEM(2, itemnum).getItemName());
			lblouterItem.setFont(setfont().deriveFont(Font.BOLD,14));
			lblouterItem.setHorizontalAlignment(SwingConstants.CENTER);
			outerItem.add(lblouterItem, BorderLayout.SOUTH);
		
		}
		
		/*
		 * category-shoes
		 */
		lbl_Shoes = new JLabel("  Shoes");
		lbl_Shoes.setPreferredSize(new Dimension(this.getWidth(),50));
		lbl_Shoes.setFont(setfont().deriveFont(Font.BOLD,30));
		Mainpanel.add(lbl_Shoes);
		
		
		panel_shoes = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_shoes.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		flowLayout_4.setHgap(15);
		panel_shoes.setPreferredSize(new Dimension(categoryWidth+230,categoryHeight));
		scrollPane_4 = new JScrollPane(panel_shoes);
		scrollPane_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_4.setPreferredSize(new Dimension(this.getWidth(),categoryHeight));
		Mainpanel.add(scrollPane_4);
		
		int[] ShoesSizeOptionArr = {3,3,3,3,2,3};
		int[] ShoesColorOptionArr = {7,3,7,3,3,6};
		for(i=0;i<data.getItemAmount(3);i++) {
			final int itemnum=i;
			JPanel shoesItem = new JPanel();
			shoesItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ItemDescription dialog = new ItemDescription(JungSinSa_Main.this,data,data.getITEM(3,itemnum),ShoesSizeOptionArr[itemnum],ShoesColorOptionArr[itemnum],"/ItemImages/shoesizechart.jpg");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			});
			shoesItem.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
			panel_shoes.add(shoesItem);
			shoesItem.setLayout(new BorderLayout(5, 5));
			
			JLabel imgshoesItem = new JLabel("");
			imgshoesItem.setIcon(iconSetSize(data.getItemPics(3,itemnum),itemSlotWidth,itemSlotHeight));
			shoesItem.add(imgshoesItem, BorderLayout.CENTER);
			JLabel lblshoesItem = new JLabel(data.getITEM(3, itemnum).getItemName());
			lblshoesItem.setFont(setfont().deriveFont(Font.BOLD,14));
			lblshoesItem.setHorizontalAlignment(SwingConstants.CENTER);
			shoesItem.add(lblshoesItem, BorderLayout.SOUTH);
		}
	}
}
