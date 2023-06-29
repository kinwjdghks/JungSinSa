package GUI;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import others.Database;
import others.Database.ITEM;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.awt.event.ActionEvent;

public class DeliveringAnimation extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lbl_PurchaseDone;
	private JLabel lbl_text1;
	private JLabel lbl_text2;
	private JLabel lbl_truck;
	private JLabel lbl_home;
	private Thread thread;
	private JLabel lblItemsBought;
	private JButton btnPrintReceipt;
	private JButton btnClose;
	private JLabel lbltotalPrice;
	
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
	ImageIcon iconSetSize(String URL,int width,int height) { //return size adjusted ImageIcon.
			
			ImageIcon origin = new ImageIcon(ItemDescription.class.getResource(URL));
			Image resizedImg = origin.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
			return new ImageIcon(resizedImg);
	}

	/**
	 * Create the frame.
	 */
	public DeliveringAnimation(JFrame parentframe,Database data,int totalCost,String firstItemName,String receipt) {
		setTitle("Thank you for purchasing");
		setResizable(false);
		setBounds(150, 150, 460, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setOpaque(false);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lbl_PurchaseDone = new JLabel("Purchase Done");
		lbl_PurchaseDone.setFont(setfont().deriveFont(Font.BOLD,27));
		lbl_PurchaseDone.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_PurchaseDone.setBounds(55, 20, 340, 40);
		panel.add(lbl_PurchaseDone);
		
		lbl_text1 = new JLabel("Thank you for purchasing.");
		lbl_text1.setFont(setfont().deriveFont(Font.BOLD,18));
		lbl_text1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_text1.setBounds(55, 60, 340, 30);
		panel.add(lbl_text1);
		
		lbl_text2 = new JLabel("The items you ordered will be delivered within a few days...");
		lbl_text2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_text2.setFont(setfont().deriveFont(Font.BOLD,14));
		lbl_text2.setBounds(6, 85, 438, 30);
		panel.add(lbl_text2);
		
		lbl_truck = new JLabel("");
		
		lbl_truck.setLocation(20,150);
		lbl_truck.setSize(new Dimension(70,60));
		lbl_truck.setIcon(iconSetSize("/ItemImages/truckImg.jpg",70,60));
		panel.add(lbl_truck);
		
		lbl_home = new JLabel("");
		lbl_home.setLocation(355,130);
		lbl_home.setSize(new Dimension(80,80));
		lbl_home.setIcon(iconSetSize("/ItemImages/homeImg.jpg",80,80));
		panel.add(lbl_home);
		
		lblItemsBought = new JLabel("");
		lblItemsBought.setBounds(16, 240, 420, 35);
		panel.add(lblItemsBought);
		
		lbltotalPrice = new JLabel("Total Cost: "+totalCost+" won");
		lbltotalPrice.setFont(setfont().deriveFont(Font.BOLD,15));
		lbltotalPrice.setBounds(15, 280, 420, 35);
		panel.add(lbltotalPrice);
		
		btnPrintReceipt = new JButton("Print Receipt");
		btnPrintReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get the current date and time
				LocalDateTime currentDateTime = LocalDateTime.now();
				// Format the current date and time as a string
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				String orderSerial = currentDateTime.format(formatter);
				//generate random orderSerial number
				for (int i = 1; i <= 8; i++) {
				      char ch = (char)((Math.random() * 26) + 65);
				      orderSerial = orderSerial+ch;				      
				    }
				try {
					PrintWriter writer = new PrintWriter(new FileOutputStream(orderSerial+".txt",false));
					writer.print(receipt);
					writer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(DeliveringAnimation.this,"Order number "+orderSerial+": Receipt has successfully printed to your folder.", "Notice", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnPrintReceipt.setFont(setfont().deriveFont(Font.BOLD,13));
		btnPrintReceipt.setBounds(197, 327, 117, 29);
		panel.add(btnPrintReceipt);
		
		btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//close both parentframe and this frame.
				parentframe.dispose(); 
				dispose();
			}
		});
		btnClose.setBounds(314, 327, 117, 29);
		panel.add(btnClose);
		btnClose.setFont(setfont().deriveFont(Font.BOLD,13));
		int numItems = data.cart.getItemCount(); //Number of items User is buying right now.
		
		if(numItems==1) {
			lblItemsBought.setText("Item ordered: "+firstItemName);
			lblItemsBought.setFont(setfont().deriveFont(Font.BOLD,15));
		}
		//if there are more than one item, show the amount
		else{
			lblItemsBought.setText("Item ordered: "+firstItemName+" and "+(data.cart.getItemCount()-1)+" other item");
			lblItemsBought.setFont(setfont().deriveFont(Font.BOLD,15));
		}
		//show animation with separate thread
		thread = new animation();
		thread.start();
	
	}
	/*
	 * Making animation for each truck and home image.
	 * The Truck will go forward repeatedly and home will float and make a circle-movement.
	 */
	public class animation extends Thread{
		
		public void run() {
			int i=0;
			int j=0;
			while(true) {
				i = (i+1)%160;
				j = (j+1)%3141;
				try {
					Thread.sleep(8);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lbl_truck.setLocation(20+(int)(0.01*i*i),150);
				lbl_home.setLocation(355+(int)(4*Math.cos(0.05*j)),130+(int)(5*Math.sin(0.05*j)));
			}
			
		}
	}
}
