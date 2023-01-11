import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;


public class Menu extends JFrame implements ActionListener {
	
	Board board;
	JRadioButton beginner, intermediate, expert;
	JButton start, exit;
	JPanel upper, lower, messagePanel; 
	ButtonGroup buttongroup;
	JLabel message; 
	
	public Menu() {
		super("Minesweeper");
		beginner = new JRadioButton("Beginner (9x9) ", true);
		intermediate = new JRadioButton("Intermediate (16x16)");
		expert = new JRadioButton("Expert (30x16)");
		buttongroup = new ButtonGroup(); //make selection exclusive
		buttongroup.add(beginner);
		buttongroup.add(intermediate);
	    buttongroup.add(expert);
		
		start = new JButton("Start");
	    exit = new JButton("Exit");
	    start.addActionListener(this);
	    exit.addActionListener(this);
	   
	    upper = new JPanel();
	    upper.add(beginner); 
	    upper.add(intermediate); 
	    upper.add(expert); 
	    lower = new JPanel();
	    lower.add(start); 
	    lower.add(exit); 
	    
	    messagePanel = new JPanel(); 
	    message = new JLabel("Select a Game:");
	    messagePanel.add(message, BorderLayout.LINE_END);
		
	    add(messagePanel, BorderLayout.NORTH);
	    add(upper, BorderLayout.CENTER);
	    add(lower, BorderLayout.SOUTH);


	    setVisible(true);
	    setSize(400, 300);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    
	}
	
	 public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == exit) {
	        	System.exit(0);
	        }
	        if (e.getSource() == start) {
	        	if (beginner.isSelected() ) {
	        		setVisible(false);
	        		if (board != null) {
	        			board.frame.setVisible(false); 
	        		}
	        		board = new Board("Beginner", this); 
	        	}
	        	if (intermediate.isSelected() ) {
	        		setVisible(false);
	        		if (board != null) {
	        			board.frame.setVisible(false); 
	        		}
	        		board = new Board("Intermediate", this); 
	        	}
	        	if (expert.isSelected() ) {
	        		setVisible(false);
	        		if (board != null) {
	        			board.frame.setVisible(false); 
	        		}
	        		board = new Board("Expert", this); 
	        	}
	        }
	    }
	
	 public void gameOver() {
		 int randomNum = ThreadLocalRandom.current().nextInt(0, 6);
		 
		 if (randomNum == 0) {
			 message.setText("Mine sweeping done differently.");
		 }
		 if (randomNum == 1) {
			 message.setText("Get a new job.");
		 }
		 if (randomNum == 2) {
			 message.setText("Boom!");
		 }
		 if (randomNum == 3) {
			 message.setText("Try to avoid bombs in the future!");
		 }
		 if (randomNum == 4) {
			 message.setText("Procrastinating, huh?");
		 }
		 if (randomNum == 5) {
			 message.setText("Bad Luck!");
		 }
		 if (randomNum == 6) {
			 message.setText("Why not do something useful?");
		 }
	 
	 setVisible(true);
	 }
	 
	 public void victory(String time) {
		 int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
		 
		 if (randomNum == 0) {
			 message.setText("Congrats! This took you " + time + " minutes. ");
		 }
		 if (randomNum == 1) {
			 message.setText("You took " + time + " minutes, can you beat that time?");
		 }
		 if (randomNum == 2) {
			 message.setText("With " + time + " minutes, this is a new record!");
		 }

	 
	 setVisible(true);
	 }
}
