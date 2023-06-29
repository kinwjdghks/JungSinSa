package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import others.Database;
import others.Database.ITEM;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;


public class WriteReview extends JDialog {
	
	private String userInputReview;
	private final JPanel contentPanel = new JPanel();

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
	 * Create the dialog.
	 */
		public WriteReview(JDialog parentframe,Database data,ITEM item) {
		super(parentframe,true);
		setBounds(200, 200, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblItemName = new JLabel(item.getItemName());
		lblItemName.setFont(setfont().deriveFont(Font.BOLD,17));
		lblItemName.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemName.setPreferredSize(new Dimension(300,40));
			
		contentPanel.add(lblItemName);
		
		JLabel lblNewLabel = new JLabel("Please note that it is difficult to edit or delete after writing a review.");
		lblNewLabel.setFont(setfont().deriveFont(Font.BOLD,13));
		contentPanel.add(lblNewLabel);
	
	
		JTextArea writehere = new JTextArea();
		writehere.setFont(setfont().deriveFont(Font.BOLD,15));
		writehere.setLineWrap(true);
		writehere.setPreferredSize(new Dimension(400,175));
		contentPanel.add(writehere);
		
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		JButton okButton = new JButton("Submit");
		okButton.setFont(setfont().deriveFont(Font.BOLD,16));
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						userInputReview = writehere.getText();
						if(userInputReview.replace("\n", " ").split(" ").length<10) {
							JOptionPane.showMessageDialog(null,"You should write at least 10 words for the review.","Notice", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							// Get the current date and time
							LocalDateTime currentDateTime = LocalDateTime.now();

							// Format the current date and time as a string
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
							String formattedDateTime = currentDateTime.format(formatter);
							//replace all newlines with space and append time at the front.
							userInputReview = "["+formattedDateTime +"] - "+ "\""+userInputReview.replace("\n", " ")+"\""; //
							data.write_review(item, userInputReview);
							JOptionPane.showMessageDialog(null,"Thank you for your comment.","Thank you", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
						
			}
		});
				
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	
	
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(setfont().deriveFont(Font.BOLD,16));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(cancelButton);
			
		
	}

}
