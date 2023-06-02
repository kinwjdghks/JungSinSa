package GUI;
import others.Database;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

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
	private JPanel panel;
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
	private JPanel topItem_1;
	private JLabel imgTopItem_1;
	private JLabel lblTopItem_1;
	private JPanel topItem_2;
	private JLabel imgTopItem_2;
	private JLabel lblTopItem_2;
	private JPanel topItem_3;
	private JLabel imgTopItem_3;
	private JLabel lblTopItem_3;
	private JPanel topItem_4;
	private JLabel imgTopItem_4;
	private JLabel lblTopItem_4;
	private JPanel topItem_5;
	private JLabel imgTopItem_5;
	private JLabel lblTopItem_5;
	
	private JPanel bottomItem_1;
	private JLabel imgBottomItem_1;
	private JLabel lblBottomItem_1;
	private JPanel bottomItem_2;
	private JLabel imgBottomItem_2;
	private JLabel lblBottomItem_2;
	private JPanel bottomItem_3;
	private JLabel imgBottomItem_3;
	private JLabel lblBottomItem_3;
	private JPanel bottomItem_4;
	private JLabel imgBottomItem_4;
	private JLabel lblBottomItem_4;
	private JPanel bottomItem_5;
	private JLabel imgBottomItem_5;
	private JLabel lblBottomItem_5;
	
	
	
	
	
	//Reviews
//	List<String> review = new LinkedList<String>();
//	DefaultListModel<String> reviewModel = new DefaultListModel<String>();
	
	
	private ImageIcon iconSetSize(String URL,int width,int height) { //return size adjusted ImageIcon.
		ImageIcon origin = new ImageIcon(JungSinSa_Main.class.getResource(URL));
		Image resizedImg = origin.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImg);
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
		data = new Database();
		//get all the datas.
		
		
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
		TopLogo.setIcon(iconSetSize("/ItemImages/toplogo.png",contentPane.getWidth(),150));
		TopLogo.setPreferredSize(new Dimension(contentPane.getWidth(),150));
		contentPane.add(TopLogo, BorderLayout.NORTH);
		
		
		bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(contentPane.getWidth(),60));
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		
		ViewCoupons = new JButton("My Coupons");
		ViewCoupons.setPreferredSize(new Dimension(120,50));
		bottomPanel.add(ViewCoupons);
		
		ViewMyCart = new JButton("My Cart");
		ViewMyCart.setPreferredSize(new Dimension(80,50));
		ViewMyCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyCart frame = new MyCart(JungSinSa_Main.this,data);
				frame.setVisible(true);
			}
		});
		bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 5));
		bottomPanel.add(ViewMyCart);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(this.getWidth(),1500));
		
		MainScrollPane = new JScrollPane(panel);
//		MainScrollPane.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()-160));
		MainScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(MainScrollPane, BorderLayout.CENTER);
		
		
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		
		lbl_Top = new JLabel("Top");
		lbl_Top.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lbl_Top.setPreferredSize(new Dimension(this.getWidth(),30));
		panel.add(lbl_Top);
		
		//category-TOP
		
		panel_top = new JPanel();
		FlowLayout fl_panel_top = (FlowLayout) panel_top.getLayout();
		fl_panel_top.setAlignment(FlowLayout.LEFT);
		fl_panel_top.setHgap(15);
		panel_top.setPreferredSize(new Dimension(categoryWidth,categoryHeight));
		scrollPane_1 = new JScrollPane(panel_top);
		
		//category-TOP/item1
		topItem_1 = new JPanel();
		topItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ItemDescription dialog = new ItemDescription(JungSinSa_Main.this,data,data.get_ITEM(0,0),0,0,0,0);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		topItem_1.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
		panel_top.add(topItem_1);
		topItem_1.setLayout(new BorderLayout(5, 5));
		
		imgTopItem_1 = new JLabel("");
		
		
		
		imgTopItem_1.setIcon(iconSetSize(data.get_itemPics(0,0),itemSlotWidth,itemSlotHeight));
		topItem_1.add(imgTopItem_1, BorderLayout.CENTER);
		lblTopItem_1 = new JLabel(data.get_ITEM(0,0).getItemName());
		lblTopItem_1.setHorizontalAlignment(SwingConstants.CENTER);
		topItem_1.add(lblTopItem_1, BorderLayout.SOUTH);
		
		//category-TOP/item2
		topItem_2 = new JPanel();
		topItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ItemDescription dialog = new ItemDescription(JungSinSa_Main.this,data,data.get_ITEM(0,1),0,0,0,1);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		topItem_2.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
		panel_top.add(topItem_2);
		topItem_2.setLayout(new BorderLayout(5, 5));
		
		imgTopItem_2 = new JLabel("");
		imgTopItem_2.setIcon(iconSetSize(data.get_itemPics(0,1),itemSlotWidth,itemSlotHeight));
		topItem_2.add(imgTopItem_2, BorderLayout.CENTER);
		lblTopItem_2 = new JLabel(data.get_ITEM(0,1).getItemName());
		lblTopItem_2.setHorizontalAlignment(SwingConstants.CENTER);
		topItem_2.add(lblTopItem_2, BorderLayout.SOUTH);
		
		//category-TOP/item3
		topItem_3 = new JPanel();
		topItem_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				TopItem_1 dialog = new TopItem_1(JungSinSa_Main.this,ITEMLIST.get(0).get(2),"/ItemImages/top_3.jpg",ColorOption2,SizeOption2);
//				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//				dialog.setVisible(true);
			}
		});
		topItem_3.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
		panel_top.add(topItem_3);
		topItem_3.setLayout(new BorderLayout(5, 5));
		
		imgTopItem_3 = new JLabel("");
		imgTopItem_3.setIcon(iconSetSize(data.get_itemPics(0,2),itemSlotWidth,itemSlotHeight));
		topItem_3.add(imgTopItem_3, BorderLayout.CENTER);
		lblTopItem_3 = new JLabel(data.get_ITEM(0,2).getItemName());
		lblTopItem_3.setHorizontalAlignment(SwingConstants.CENTER);
		topItem_3.add(lblTopItem_3, BorderLayout.SOUTH);
		
		//category-TOP/item4
		topItem_4 = new JPanel();
		topItem_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				TopItem_1 dialog = new TopItem_1(JungSinSa_Main.this,ITEMLIST.get(0).get(3),"/ItemImages/top_4.jpg",ColorOption2,SizeOption2);
//				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//				dialog.setVisible(true);
			}
		});
		topItem_4.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
		panel_top.add(topItem_4);
		topItem_4.setLayout(new BorderLayout(5, 5));
		
		imgTopItem_4 = new JLabel("");
		imgTopItem_4.setIcon(iconSetSize(data.get_itemPics(0,3),itemSlotWidth,itemSlotHeight));
		topItem_4.add(imgTopItem_4, BorderLayout.CENTER);
		lblTopItem_4 = new JLabel(data.get_ITEM(0,3).getItemName());
		lblTopItem_4.setHorizontalAlignment(SwingConstants.CENTER);
		topItem_4.add(lblTopItem_4, BorderLayout.SOUTH);
		
		//category-TOP/item5
		topItem_5 = new JPanel();
		topItem_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				TopItem_1 dialog = new TopItem_1(JungSinSa_Main.this,ITEMLIST.get(0).get(4),"/ItemImages/top_5.jpg",ColorOption2,SizeOption2);
//				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//				dialog.setVisible(true);
			}
		});
		topItem_5.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
		panel_top.add(topItem_5);
		topItem_5.setLayout(new BorderLayout(5, 5));
		
		imgTopItem_5 = new JLabel("");
		imgTopItem_5.setIcon(iconSetSize(data.get_itemPics(0,4),itemSlotWidth,itemSlotHeight));
		topItem_5.add(imgTopItem_5, BorderLayout.CENTER);
		lblTopItem_5 = new JLabel(data.get_ITEM(0,4).getItemName());
		lblTopItem_5.setHorizontalAlignment(SwingConstants.CENTER);
		topItem_5.add(lblTopItem_5, BorderLayout.SOUTH);
		
		
		panel_top.setPreferredSize(new Dimension(categoryWidth,categoryHeight));
		scrollPane_1 = new JScrollPane(panel_top);
		
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setPreferredSize(new Dimension(this.getWidth(),categoryHeight));
		panel.add(scrollPane_1);
		
		//
		lbl_Bottom = new JLabel("Bottom");
		lbl_Bottom.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lbl_Bottom.setPreferredSize(new Dimension(this.getWidth(),30));
		panel.add(lbl_Bottom);
		
		//category 2
		panel_bottom = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_bottom.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		flowLayout_1.setHgap(15);
		panel_bottom.setPreferredSize(new Dimension(categoryWidth,categoryHeight));
		scrollPane_2 = new JScrollPane(panel_bottom);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_2.setPreferredSize(new Dimension(this.getWidth(),categoryHeight));
		panel.add(scrollPane_2);
		
		//category-Bottom/item1
		bottomItem_1 = new JPanel();
		bottomItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ItemDescription dialog = new ItemDescription(JungSinSa_Main.this,data,data.get_ITEM(1, 0),0,0,1,0);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		bottomItem_1.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
		panel_bottom.add(bottomItem_1);
		bottomItem_1.setLayout(new BorderLayout(5, 5));
		
		imgBottomItem_1 = new JLabel("");
		
		
		
		imgBottomItem_1.setIcon(iconSetSize(data.get_itemPics(1,0),itemSlotWidth,itemSlotHeight));
		bottomItem_1.add(imgBottomItem_1, BorderLayout.CENTER);
		lblBottomItem_1 = new JLabel(data.get_ITEM(1,0).getItemName());
		lblBottomItem_1.setHorizontalAlignment(SwingConstants.CENTER);
		bottomItem_1.add(lblBottomItem_1, BorderLayout.SOUTH);
		
		//category-Bottom/item2
		bottomItem_2 = new JPanel();
		bottomItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				TopItem_1 dialog = new TopItem_1(JungSinSa_Main.this,ITEMLIST.get(0).get(1),"/ItemImages/top_2.jpg",ColorOption2,SizeOption2);
//				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//				dialog.setVisible(true);
			}
		});
		bottomItem_2.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
		panel_bottom.add(bottomItem_2);
		bottomItem_2.setLayout(new BorderLayout(5, 5));
		
		imgBottomItem_2 = new JLabel("");
		imgBottomItem_2.setIcon(iconSetSize(data.get_itemPics(1,1),itemSlotWidth,itemSlotHeight));
		bottomItem_2.add(imgBottomItem_2, BorderLayout.CENTER);
		lblBottomItem_2 = new JLabel(data.get_ITEM(1,1).getItemName());
		lblBottomItem_2.setHorizontalAlignment(SwingConstants.CENTER);
		bottomItem_2.add(lblBottomItem_2, BorderLayout.SOUTH);
		
		//category-TOP/item3
		bottomItem_3 = new JPanel();
		bottomItem_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				TopItem_1 dialog = new TopItem_1(JungSinSa_Main.this,ITEMLIST.get(0).get(2),"/ItemImages/top_3.jpg",ColorOption2,SizeOption2);
//				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//				dialog.setVisible(true);
			}
		});
		bottomItem_3.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
		panel_bottom.add(bottomItem_3);
		bottomItem_3.setLayout(new BorderLayout(5, 5));
		
		imgBottomItem_3 = new JLabel("");
		imgBottomItem_3.setIcon(iconSetSize(data.get_itemPics(1,2),itemSlotWidth,itemSlotHeight));
		bottomItem_3.add(imgBottomItem_3, BorderLayout.CENTER);
		lblBottomItem_3 = new JLabel(data.get_ITEM(1,2).getItemName());
		lblBottomItem_3.setHorizontalAlignment(SwingConstants.CENTER);
		bottomItem_3.add(lblBottomItem_3, BorderLayout.SOUTH);
		
		//category-TOP/item4
		bottomItem_4 = new JPanel();
		bottomItem_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				TopItem_1 dialog = new TopItem_1(JungSinSa_Main.this,ITEMLIST.get(0).get(3),"/ItemImages/top_4.jpg",ColorOption2,SizeOption2);
//				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//				dialog.setVisible(true);
			}
		});
		bottomItem_4.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
		panel_bottom.add(bottomItem_4);
		bottomItem_4.setLayout(new BorderLayout(5, 5));
		
		imgBottomItem_4 = new JLabel("");
		imgBottomItem_4.setIcon(iconSetSize(data.get_itemPics(1,3),itemSlotWidth,itemSlotHeight));
		bottomItem_4.add(imgBottomItem_4, BorderLayout.CENTER);
		lblBottomItem_4 = new JLabel(data.get_ITEM(1,3).getItemName());
		lblBottomItem_4.setHorizontalAlignment(SwingConstants.CENTER);
		bottomItem_4.add(lblBottomItem_4, BorderLayout.SOUTH);
		
		//category-TOP/item5
		bottomItem_5 = new JPanel();
		bottomItem_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				TopItem_1 dialog = new TopItem_1(JungSinSa_Main.this,ITEMLIST.get(0).get(4),"/ItemImages/top_5.jpg",ColorOption2,SizeOption2);
//				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//				dialog.setVisible(true);
			}
		});
		bottomItem_5.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
		panel_bottom.add(bottomItem_5);
		bottomItem_5.setLayout(new BorderLayout(5, 5));
		
		imgBottomItem_5 = new JLabel("");
		imgBottomItem_5.setIcon(iconSetSize(data.get_itemPics(1,4),itemSlotWidth,itemSlotHeight));
		bottomItem_5.add(imgBottomItem_5, BorderLayout.CENTER);
		lblBottomItem_5 = new JLabel(data.get_ITEM(1,4).getItemName());
		lblBottomItem_5.setHorizontalAlignment(SwingConstants.CENTER);
		bottomItem_5.add(lblBottomItem_5, BorderLayout.SOUTH);
		//
		
		lbl_Outer = new JLabel("Outer");
		lbl_Outer.setPreferredSize(new Dimension(this.getWidth(),30));
		lbl_Outer.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		panel.add(lbl_Outer);
		
		//category 3
		panel_outer = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_outer.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setHgap(15);
		panel_outer.setPreferredSize(new Dimension(categoryWidth,categoryHeight));
		scrollPane_3 = new JScrollPane(panel_outer);
		scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_3.setPreferredSize(new Dimension(this.getWidth(),categoryHeight));
		panel.add(scrollPane_3);
		
		int[] OuterSizeOptionArr = {0,1,2,0,1};
		int[] OuterColorOptionArr = {0,1,1,0,0};
		for(i=0;i<5;i++) {
			final int itemnum=i;
			JPanel outerItem = new JPanel();
			outerItem.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ItemDescription dialog = new ItemDescription(JungSinSa_Main.this,data,data.get_ITEM(1,itemnum),OuterSizeOptionArr[itemnum],OuterColorOptionArr[itemnum],1,itemnum);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
			});
			outerItem.setPreferredSize(new Dimension(itemSlotWidth,itemSlotHeight));
			panel_outer.add(outerItem);
			outerItem.setLayout(new BorderLayout(5, 5));
			
			JLabel imgouterItem = new JLabel("");
			imgouterItem.setIcon(iconSetSize(data.get_itemPics(1,itemnum),itemSlotWidth,itemSlotHeight));
			outerItem.add(imgouterItem, BorderLayout.CENTER);
			JLabel lblouterItem = new JLabel(data.get_ITEM(1,itemnum).getItemName());
			lblouterItem.setHorizontalAlignment(SwingConstants.CENTER);
			outerItem.add(lblouterItem, BorderLayout.SOUTH);
		
		}
		//
		lbl_Shoes = new JLabel("Shoes");
		lbl_Shoes.setPreferredSize(new Dimension(this.getWidth(),30));
		lbl_Shoes.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		panel.add(lbl_Shoes);
		//category 4
		panel_shoes = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_shoes.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		flowLayout_3.setHgap(15);
		panel_shoes.setPreferredSize(new Dimension(categoryWidth,categoryHeight));
		scrollPane_4 = new JScrollPane(panel_shoes);
		scrollPane_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
//		scrollPane_4.setPreferredSize(new Dimension(this.getWidth(),categoryHeight));
		panel.add(scrollPane_4);
		//

		
		
	}
}
