import java.util.*;
//import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 
//public class Tile implements ActionListener, KeyListener {
public class Tile implements MouseListener {
	static Board board;
	static boolean running = true; 
	boolean open; 
	boolean flag; 
	boolean bomb; 
	int bombCount;
	JButton button;
	
	public Tile(Board board) {
		this.board = board;
		open = false; 
		flag = false; 
		bomb = false; 
		bombCount = 0; 
		
        button = new JButton();  
        button.addMouseListener(this);
        button.setIcon(new StretchIcon(getClass().getResource("/tile77.png")));
        button.setPressedIcon(new StretchIcon(getClass().getResource("/tile77_pressed.png")));

        button.setBorder(null);
	}
	
	public void select() {
		if(board.startTime == 0) {
			board.startTime = System.currentTimeMillis();
			board.timer.start(); 
		}
		if (bomb == true) { //end game 
			setIcon(); 
			board.reveal(this);
			running = false; 
			board.timer.stop(); 
			board.gameOver(); 
		}
		else {
			board.uncover(this); 
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {  
		if (running == true) {
			if (e.getButton() == MouseEvent.BUTTON2 || e.isControlDown() ) {
			if (!flag) {
				flag();
			}
			else if (flag) {
				unflag();
			}
			}
			if (e.getButton() == MouseEvent.BUTTON1 && !e.isControlDown() ) {
				if (!flag) {
	        	select();
	        }
			}
		}
		
    }  
    public void mouseEntered(MouseEvent e) {    
    }  
    public void mouseExited(MouseEvent e) {  
    }  
    public void mouseReleased(MouseEvent e) {  
    }  
    public void mouseClicked(MouseEvent e) {  
    }  
	
	
	public void setIcon() {
		if (bomb) {
			button.setIcon(new StretchIcon(getClass().getResource("/tile99.png")));
			button.setPressedIcon(new StretchIcon(getClass().getResource("/tile99.png")));
		}
		else if (bombCount == 0) {
			button.setIcon(new StretchIcon(getClass().getResource("/tile0.png")));
			button.setPressedIcon(new StretchIcon(getClass().getResource("/tile0.png")));
		}
		else if (bombCount == 1) {
			button.setIcon(new StretchIcon(getClass().getResource("/tile1.png")));
			button.setPressedIcon(new StretchIcon(getClass().getResource("/tile1.png")));
		}
		else if (bombCount == 2) {
			button.setIcon(new StretchIcon(getClass().getResource("/tile2.png")));
			button.setPressedIcon(new StretchIcon(getClass().getResource("/tile2.png")));
		}
		else if (bombCount == 3) {
			button.setIcon(new StretchIcon(getClass().getResource("/tile3.png")));
			button.setPressedIcon(new StretchIcon(getClass().getResource("/tile3.png")));
		}
		else if (bombCount == 4) {
			button.setIcon(new StretchIcon(getClass().getResource("/tile4.png")));
			button.setPressedIcon(new StretchIcon(getClass().getResource("/tile4.png")));
		}
		else if (bombCount == 5) {
			button.setIcon(new StretchIcon(getClass().getResource("/tile5.png")));
			button.setPressedIcon(new StretchIcon(getClass().getResource("/tile5.png")));
		}
		else if (bombCount == 6) {
			button.setIcon(new StretchIcon(getClass().getResource("/tile6.png")));
			button.setPressedIcon(new StretchIcon(getClass().getResource("/tile6.png")));
		}
		else if (bombCount == 7) {
			button.setIcon(new StretchIcon(getClass().getResource("/tile7.png")));
			button.setPressedIcon(new StretchIcon(getClass().getResource("/tile7.png")));
		}
		else if (bombCount == 8) {
			button.setIcon(new StretchIcon(getClass().getResource("/tile8.png")));
			button.setPressedIcon(new StretchIcon(getClass().getResource("/tile8.png")));
		}
		button.setBorder(null);
	}
	
	public void flag() {
		if (!open) {
			this.flag = true;
			button.setIcon(new StretchIcon(getClass().getResource("/tile88.png")));
			button.setPressedIcon(new StretchIcon(getClass().getResource("/tile88.png")));
			button.setBorder(null);
			board.flag(); 
		}
	}
	public void unflag() {
		if (!open) {
			this.flag = false;
	        button.setIcon(new StretchIcon(getClass().getResource("/tile77.png")));
	        button.setPressedIcon(new StretchIcon(getClass().getResource("/tile77_pressed.png")));
	        button.setBorder(null);
	        board.unflag(); 
		}
	}
	
	public void setBomb() {
		bomb = true;
		bombCount = 9;
	}
	
	public void reveal() {
		if (bomb && flag) {
	        button.setIcon(new StretchIcon(getClass().getResource("/tile88b.png")));
	        button.setPressedIcon(new StretchIcon(getClass().getResource("/tile88b.png")));
		}
		if (bomb && !flag) {
	        button.setIcon(new StretchIcon(getClass().getResource("/tile9.png")));
	        button.setPressedIcon(new StretchIcon(getClass().getResource("/tile9.png")));
		}
		if (!bomb && flag) {
	        button.setIcon(new StretchIcon(getClass().getResource("/tile66.png")));
	        button.setPressedIcon(new StretchIcon(getClass().getResource("/tile66.png")));
		}
		button.setBorder(null);
	}
	
	public void setBombCount(ArrayList<Tile> neighbours) {
		for (Tile tile : neighbours) {
			if (tile.bomb) {
				bombCount += 1;
			}
		}
	}
}