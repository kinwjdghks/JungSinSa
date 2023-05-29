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
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import java.awt.Font;
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
	private JButton btnNewButton;
	private JLabel lbl_Color;
	private JLabel lbl_Size;
	private JComboBox combo_Color;
	private JComboBox combo_Size;
	private JScrollPane scrollPane;
	private JPanel mainPanel;
	private JPanel SideBar;
	private JScrollPane scrollPane_1;
	private JTextArea textArea;
	private JPanel panel_Review;
	private JLabel lblNewLabel;
	private JPanel panel_blank;
	private JLabel lbl_price;
	private JLabel label;
	private JLabel lblNewLabel_1;
	private JTextPane textPane;
	private JLabel lbl_sizechart;
	private JButton btnNewButton_1;

	
	
	/**
	 * Create the dialog.
	 */
	ImageIcon iconSetSize(String URL,int width,int height) { //return size adjusted ImageIcon.
		
		ImageIcon origin = new ImageIcon(ItemDescription.class.getResource(URL));
		Image resizedImg = origin.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImg);
	}
	
	public ItemDescription(JFrame parentFrame,Database data,ITEM item,List<String> ColorOption,List<String> SizeOption,int category,int number) { //Need Image URL, SizeOption, ColorOption, Reviews, is_bought
		super(parentFrame,true);
		
		this.parentframe = (JungSinSa_Main) parentFrame;
		setBounds(100, 100, frameWidth, frameHeight);
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
		lbl_itempic.setIcon(iconSetSize(parentframe.data.get_itemPics(category,number),imgWidth,imgHeight));
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
		textPane.setFont(new Font("Heiti TC", Font.BOLD, 18));
		textPane.setText(item.getItemName());
		textPane.setOpaque(false);
		textPane.setEditable(false);
		textPane.setBounds(0, 20, 150, 80);
		panel_blank.add(textPane);
		
		StyledDocument doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		
		
		panel_Color = new JPanel();
		SideBar.add(panel_Color);
		panel_Color.setPreferredSize(new Dimension(150,50));
		panel_Color.setLayout(new GridLayout(2, 0, 0, 0));
		
		lbl_Color = new JLabel("Available Color");
		lbl_Color.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Color.add(lbl_Color);
		
		
		combo_Color = new JComboBox(ColorOption.toArray(new String[ColorOption.size()]));
		panel_Color.add(combo_Color);
		
		panel_Size = new JPanel();
		SideBar.add(panel_Size);
		panel_Size.setPreferredSize(new Dimension(150,50));
		panel_Size.setLayout(new GridLayout(2, 0, 0, 0));
		
		lbl_Size = new JLabel("Available Size");
		lbl_Size.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Size.add(lbl_Size);
		
		lbl_price = new JLabel(item.getItemPrice()+" won");
		lbl_price.setFont(new Font("Heiti TC", Font.BOLD, 20));
		lbl_price.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_price.setPreferredSize(new Dimension(150,40));
		SideBar.add(lbl_price);
		
		btnNewButton = new JButton("Add to Cart");
		SideBar.add(btnNewButton);
		btnNewButton.setPreferredSize(new Dimension(100,50));
		
		combo_Size = new JComboBox(SizeOption.toArray(new String[SizeOption.size()]));
		panel_Size.add(combo_Size);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(combo_Color.getSelectedIndex()==0 ||combo_Size.getSelectedIndex()==0)
						throw new purchaseException(); //user did not choose either size or color.
					JOptionPane.showMessageDialog(null,item.getItemName()+"\nSize: "+combo_Size.getSelectedItem().toString()+
					"\nColor: "+combo_Color.getSelectedItem().toString()+"\nAdded to Cart.","Information",JOptionPane.PLAIN_MESSAGE);
					
					data.add_cart(category, number);
				}
				catch(purchaseException pe) {
					JOptionPane.showMessageDialog(null,"You should choose both color and size.","Missing Option",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		
		lbl_sizechart = new JLabel("");
		lbl_sizechart.setBounds(75, 460, 450, 400);
		mainPanel.add(lbl_sizechart);
		lbl_sizechart.setIcon(iconSetSize("/ItemImages/topsizechart.jpg",chartWidth,chartHeigth));
		
		panel_Review = new JPanel();
		panel_Review.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_Review.setBounds(5, 900, 585, 250);
		mainPanel.add(panel_Review);
		panel_Review.setLayout(null);
		
		lblNewLabel = new JLabel("Reviews");
		lblNewLabel.setFont(new Font("Heiti TC", Font.BOLD, 19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(70, 0, 120, 25);
		panel_Review.add(lblNewLabel);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		
		
		scrollPane_1 = new JScrollPane(textArea);
		scrollPane_1.setOpaque(false);
		scrollPane_1.setBounds(0, 25, 585, 240);
		panel_Review.add(scrollPane_1);
		
		btnNewButton_1 = new JButton("Write Review");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton_1.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		btnNewButton_1.setBounds(400, 1150, 150, 40);
		mainPanel.add(btnNewButton_1);
		

	}
}
