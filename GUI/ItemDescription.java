package GUI;

import others.Database;
import others.Database.ITEM;
import others.purchaseException;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.text.SimpleAttributeSet;
import java.util.ListIterator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.border.SoftBevelBorder;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;

public class ItemDescription extends JDialog {
	
	private JungSinSa_Main parentframe;
	private int frameWidth = 600;
	private int frameHeight = 570;
	private int imgWidth = 400;
	private int imgHeight = 400;
	private int chartWidth = 450;
	private int chartHeigth = 400;
	private JLabel lbl_itempic;
	private JPanel panel_Color;
	private JPanel panel_Size;
	private JButton btn_addcart;
	private JLabel lbl_Color;
	private JLabel lbl_Size;
	private JComboBox combo_Color;
	private JComboBox combo_Size;
	private JScrollPane scrollPane;
	private JPanel mainPanel;
	private JPanel SideBar;
	private JScrollPane scrollPane_1;
	private JTextArea textArea_review;
	private JPanel panel_Review;
	private JLabel lblNewLabel;
	private JPanel panel_blank;
	private JLabel lbl_price;
	private JLabel label;
	private JTextPane textPane;
	private JLabel lbl_sizechart;
	private JButton btnWriteReview;
	
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
	//resize images
	private ImageIcon iconSetSize(String URL,int width,int height) { 
		ImageIcon origin = new ImageIcon(ItemDescription.class.getResource(URL));
		Image resizedImg = origin.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImg);
	}
	//display saved reviews for the item using iterater
	private void updateReviews(JTextArea reviewarea,Database data,ITEM item) {
		reviewarea.setText("");
		ListIterator<String> iter = data.getItemReview(item).listIterator();
		while(iter.hasNext()) {
			textArea_review.append(iter.next().toString()+"\n");
		}
		
	}
	
	/**
	 * Create the dialog.
	 */
	public ItemDescription(JFrame parentFrame,Database data,ITEM item,int sizeOption,int colorOption,String sizeChart) { //Need Image URL, SizeOption, ColorOption, Reviews, is_bought
		super(parentFrame,true);
		
		this.parentframe = (JungSinSa_Main) parentFrame;
		setBounds(130, 130, frameWidth+15, frameHeight);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(frameWidth,1200));
		scrollPane = new JScrollPane(mainPanel);
		scrollPane.setSize(new Dimension(frameWidth,frameHeight-150));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		lbl_itempic = new JLabel("");
		lbl_itempic.setBounds(5, 5, 400, 400);
		lbl_itempic.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_itempic.setPreferredSize(new Dimension(imgWidth,imgHeight));
		lbl_itempic.setIcon(iconSetSize(parentframe.data.getItemPics(item.getIC(),item.getII()),imgWidth,imgHeight));
		mainPanel.add(lbl_itempic);
		
		SideBar = new JPanel();
		FlowLayout fl_SideBar = (FlowLayout) SideBar.getLayout();
		fl_SideBar.setVgap(15);
		SideBar.setBounds(410, 5, 180, 400);
		mainPanel.add(SideBar);
		SideBar.setPreferredSize(new Dimension(180,frameHeight-150));
		
		panel_blank = new JPanel();
		panel_blank.setPreferredSize(new Dimension(150,120));
		SideBar.add(panel_blank);
		panel_blank.setLayout(null);
		
		label = new JLabel("");
		label.setBounds(0, 25, 0, 0);
		panel_blank.add(label);
		
		textPane = new JTextPane();
		textPane.setFont(setfont().deriveFont(Font.BOLD,18));
		textPane.setText(item.getItemName());
		textPane.setOpaque(false);
		textPane.setEditable(false);
		textPane.setBounds(0, 20, 150, 80);
		panel_blank.add(textPane);
		
		//align text to the center
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		
		
		panel_Color = new JPanel();
		SideBar.add(panel_Color);
		panel_Color.setPreferredSize(new Dimension(150,50));
		panel_Color.setLayout(new GridLayout(2, 0, 0, 0));
		
		//create combo boxes
		lbl_Color = new JLabel("Available Color");
		lbl_Color.setFont(setfont().deriveFont(Font.BOLD,16));
		lbl_Color.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Color.add(lbl_Color);
		
		
		combo_Color = new JComboBox(data.getColorOption(colorOption).toArray(new String[data.getColorOption(colorOption).size()]));
		panel_Color.add(combo_Color);
		
		panel_Size = new JPanel();
		SideBar.add(panel_Size);
		panel_Size.setPreferredSize(new Dimension(150,50));
		panel_Size.setLayout(new GridLayout(2, 0, 0, 0));
		
		lbl_Size = new JLabel("Available Size");
		lbl_Size.setFont(setfont().deriveFont(Font.BOLD,16));
		lbl_Size.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Size.add(lbl_Size);
		
		lbl_price = new JLabel(item.getItemPrice()+" won");
		lbl_price.setFont(setfont().deriveFont(Font.BOLD,20));
		lbl_price.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_price.setPreferredSize(new Dimension(150,40));
		SideBar.add(lbl_price);
		//if the item is not in the cart, insert available
		if(data.cart.isPresent(item.getIC(), item.getII()) == 0) {
			btn_addcart = new JButton("Add to Cart");
			btn_addcart.setFont(setfont().deriveFont(Font.BOLD,14));
			btn_addcart.setPreferredSize(new Dimension(100,50));
		}
		//if the item is already in the cart, delete available
		else {
			btn_addcart = new JButton("Remove Item");
			btn_addcart.setFont(setfont().deriveFont(Font.BOLD,14));
			btn_addcart.setPreferredSize(new Dimension(140,50));
		}
		SideBar.add(btn_addcart);
		
		
		combo_Size = new JComboBox(data.getSizeOption(sizeOption).toArray(new String[data.getSizeOption(sizeOption).size()]));
		panel_Size.add(combo_Size);
		btn_addcart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(data.cart.isPresent(item.getIC(), item.getII()) == 1) {
						JOptionPane.showMessageDialog(null,"Removed from Cart.","Information",JOptionPane.PLAIN_MESSAGE);
						data.cart.removeFromArray(item.getIC(), item.getII());
						btn_addcart.setText("Add to Cart");
						btn_addcart.setFont(setfont().deriveFont(Font.BOLD,14));
						btn_addcart.setPreferredSize(new Dimension(100,50));
					}
					///user did not choose either size or color -> throw exception
					else if(combo_Color.getSelectedIndex()==0 ||combo_Size.getSelectedIndex()==0)
						throw new purchaseException(); 
					else {
						//insert the item and save options to the array.
						JOptionPane.showMessageDialog(null,item.getItemName()+"\nSize: "+combo_Size.getSelectedItem().toString()+
								"\nColor: "+combo_Color.getSelectedItem().toString()+"\nAdded to Cart.","Information",JOptionPane.PLAIN_MESSAGE);
						data.cart.addItem(item.getIC(), item.getII(),sizeOption,combo_Size.getSelectedIndex(),colorOption,combo_Color.getSelectedIndex());
						btn_addcart.setText("Remove Item");
						btn_addcart.setFont(setfont().deriveFont(Font.BOLD,14));
						btn_addcart.setPreferredSize(new Dimension(140,50));
					}
				}
				//catch exception and show warning dialog
				catch(purchaseException pe) {
					JOptionPane.showMessageDialog(null,"You should choose both color and size.","Missing Option",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
		lbl_sizechart = new JLabel("");
		lbl_sizechart.setBounds(75, 460, 450, 400);
		mainPanel.add(lbl_sizechart);
		lbl_sizechart.setIcon(iconSetSize(sizeChart,chartWidth,chartHeigth));
		
		panel_Review = new JPanel();
		panel_Review.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_Review.setBounds(5, 900, 585, 250);
		mainPanel.add(panel_Review);
		panel_Review.setLayout(null);
		
		lblNewLabel = new JLabel("Reviews");
		lblNewLabel.setFont(setfont().deriveFont(Font.BOLD,19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(70, 0, 120, 25);
		panel_Review.add(lblNewLabel);
		
		textArea_review = new JTextArea();
		textArea_review.setEditable(false);
		textArea_review.setWrapStyleWord(true);
		textArea_review.setLineWrap(true);
		
		scrollPane_1 = new JScrollPane(textArea_review);
		scrollPane_1.setOpaque(false);
		scrollPane_1.setBounds(0, 25, 585, 240);
		panel_Review.add(scrollPane_1);
		
		//list all the reviews in the database.
		updateReviews(textArea_review,data,item);
		
		btnWriteReview = new JButton("Write Review");
		btnWriteReview.setFont(setfont().deriveFont(Font.BOLD,15));
		btnWriteReview.setBounds(430, 1155, 150, 40);
		mainPanel.add(btnWriteReview);
		btnWriteReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//check if the user has already bought the item.
				if(data.bought.getItemInfo(item.getIC(),item.getII())[0]==1) {
					WriteReview writeReview = new WriteReview(ItemDescription.this,data,item);
					writeReview.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(ItemDescription.this, "You can write a review if you purchase the item.", "Notice",JOptionPane.INFORMATION_MESSAGE);
				}
				//display again so that new review can show up.
				updateReviews(textArea_review, data, item);
			}
		});
		

	}
}
