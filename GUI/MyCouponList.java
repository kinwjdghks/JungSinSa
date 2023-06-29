package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import others.Database;
import others.Database.Coupon;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class MyCouponList extends JFrame {

	private JPanel contentPane;
	private JButton btn_close;
	private LinkedList myCouponList;
	private JPanel mainPanel;
	private JPanel singlecoupon;
	private JLabel lbl_name;
	private JLabel lbl_percent;
	private JLabel lbl_empty;

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
	
	/**
	 * Create the frame.
	 */
	public MyCouponList(Database data) {
		myCouponList = data.getCouponList();
		
		setBounds(180, 180, 315, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btn_close = new JButton("Close");
		btn_close.setFont(setfont().deriveFont(Font.BOLD,16));
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_close.setBounds(215, 237, 79, 29);
		contentPane.add(btn_close);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(10, 10, 280, 220);
		contentPane.add(mainPanel);
		
		ListIterator<Coupon> iter = myCouponList.listIterator();
		//notice if there is no coupon
		if(!iter.hasNext()) {
			lbl_empty = new JLabel("You don't have any Coupons!");
			lbl_empty.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_empty.setFont(setfont().deriveFont(Font.BOLD,15));
			lbl_empty.setPreferredSize(new Dimension(250,70));
			mainPanel.add(lbl_empty);
		}
		//dynamically list coupons user have.
		while(iter.hasNext()) {
			Coupon coupon = iter.next();
			singlecoupon = new JPanel();
			mainPanel.add(singlecoupon);
			singlecoupon.setPreferredSize(new Dimension(270,60));
			singlecoupon.setLayout(null);
			singlecoupon.setBorder(new LineBorder(new Color(0, 0, 0)));
			
			lbl_name = new JLabel(coupon.getCouponName());
			lbl_name.setBounds(5, 5, 250, 30);
			singlecoupon.add(lbl_name);
			lbl_name.setFont(setfont().deriveFont(Font.BOLD,16));
			
			lbl_percent = new JLabel(coupon.getCouponDiscount()+" percent");
			lbl_percent.setBounds(165, 32, 90, 20);
			lbl_percent.setFont(setfont().deriveFont(Font.BOLD,14));
			lbl_percent.setHorizontalAlignment(SwingConstants.RIGHT);
			singlecoupon.add(lbl_percent);
		}
	}
}
