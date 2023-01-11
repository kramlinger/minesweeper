import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 
public class Board implements ActionListener  {
	
	// computational attributes
	private int xTiles; 
	private int yTiles; 
	private int nBombs;
	private ArrayList<Tile> tiles = new ArrayList<>(); 
	
	// graphical attributes 
    ActionListener refresh = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	timeLabel.setText("Time: " + elapsedTime());
        }
    };
	public javax.swing.Timer timer;//= Timer(1000, refresh); 
	public long startTime = 0; 
	public String elapsedTime = "0:00"; 
	private Menu menu; 
	public JFrame frame;
	private JPanel topPanel;
	private JPanel gridPanel;
	private JLabel bombLabel; 
	private JLabel timeLabel; 
	private JButton restartButton; 
	
    public Board(String string, Menu menu) {
    	this.menu = menu; 
    	timer = new javax.swing.Timer(1000, refresh); 
    	if (string == "Beginner") {
    		xTiles = 9; 
    		yTiles = 9; 
    		nBombs = 10;
    	} 
    	if (string == "Intermediate") {
    		xTiles = 16; 
    		yTiles = 16; 
    		nBombs = 40;    		
    	} 
    	if (string == "Expert") {
    		xTiles = 30; 
    		yTiles = 16; 
    		nBombs = 99;
    	} 
    	
    	// set up computational board
    	// initiate tiles
    	for (int i = 0; i < xTiles * yTiles; i++) { 
    		tiles.add(new Tile(this));
    	}
    	
    	// set bombs 
		for (Tile tile : tiles.subList(0, nBombs)) {
			tile.setBomb();
		}
		java.util.Collections.shuffle(tiles);
		
		// set bombCount 
		for (Tile tile : tiles) {
			if (!tile.bomb) {
				tile.setBombCount(fetchNeighbours(tile));
			}
		}
  		
		// set up graphical board
		frame = new JFrame();
		topPanel = new JPanel();
	    gridPanel = new JPanel();

	    topPanel.setLayout(new BorderLayout(10, 10));

	    bombLabel = new JLabel(" Bombs: " + nBombs);
	    topPanel.add(bombLabel, BorderLayout.WEST);
	    timeLabel = new JLabel("Time: 0:00");
	    restartButton = new JButton("Menu");  
	    restartButton.addActionListener(this);
	    topPanel.add(timeLabel, BorderLayout.CENTER);
	    topPanel.add(restartButton, BorderLayout.LINE_END);

	    gridPanel.setLayout(new GridLayout(yTiles, xTiles));
	    
	    frame.add(topPanel, BorderLayout.PAGE_START);
	    frame.add(gridPanel, BorderLayout.CENTER);
	  	
	    for (Tile tile : tiles) {
	    		gridPanel.add(tile.button);
	    }
		
	    //make sure that board is running
	    tiles.get(1).running = true;
		
	    frame.setSize(241,297);
    	if (string == "Beginner") {
    		frame.setSize(241,297);
    	} 
    	if (string == "Intermediate") {
    		frame.setSize(421,478);  		
    	} 
    	if (string == "Expert") {
    		frame.setSize(799,487);
    	} 
	    frame.setVisible(true);
	    frame.setResizable(false);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e) {
    		menu.setVisible(true);
    		menu.message.setText("Restart?");
    		frame.dispose();
    }
    
    public ArrayList<Tile> fetchNeighbours(Tile tile) {
    	
    	
    	int index = tiles.indexOf(tile); 
    	int column = index % xTiles;
    	int row = (int)Math.floor(index / xTiles);
    	
    	ArrayList<Tile> neighbours = new ArrayList<>();     	
    	if (row == 0 && column == 0) {
    		neighbours.add(tiles.get(index + 1));
    		neighbours.add(tiles.get(index + xTiles));
    		neighbours.add(tiles.get(index + xTiles + 1)); 
    	}
    	else if (row == 0 && column < xTiles - 1) {
    		neighbours.add(tiles.get(index - 1));
    		neighbours.add(tiles.get(index + 1));
    		neighbours.add(tiles.get(index + xTiles - 1));
    		neighbours.add(tiles.get(index + xTiles));
    		neighbours.add(tiles.get(index + xTiles + 1)); 
    	}
    	else if (row == 0 && column == xTiles - 1) {
    		neighbours.add(tiles.get(index - 1));
    		neighbours.add(tiles.get(index + xTiles - 1));
    		neighbours.add(tiles.get(index + xTiles)); 
    	}
    	else if (row < yTiles - 1 && column == 0) {
    		neighbours.add(tiles.get(index - xTiles));
    		neighbours.add(tiles.get(index - xTiles + 1));
    		neighbours.add(tiles.get(index + 1));
    		neighbours.add(tiles.get(index + xTiles));
    		neighbours.add(tiles.get(index + xTiles + 1)); 
    	}
    	else if (row < yTiles - 1 && column < xTiles - 1) {
    		neighbours.add(tiles.get(index - xTiles - 1));
    		neighbours.add(tiles.get(index - xTiles));
    		neighbours.add(tiles.get(index - xTiles + 1));
    		neighbours.add(tiles.get(index - 1));
    		neighbours.add(tiles.get(index + 1));
    		neighbours.add(tiles.get(index + xTiles - 1));
    		neighbours.add(tiles.get(index + xTiles));
    		neighbours.add(tiles.get(index + xTiles + 1)); 
    	}
    	else if (row < yTiles - 1 && column == xTiles - 1) {
    		neighbours.add(tiles.get(index - xTiles - 1));
    		neighbours.add(tiles.get(index - xTiles)); 
    		neighbours.add(tiles.get(index - 1));
    		neighbours.add(tiles.get(index + xTiles - 1));
    		neighbours.add(tiles.get(index + xTiles)); 
    	}
    	else if (row == yTiles - 1 && column == 0) {
    		neighbours.add(tiles.get(index - xTiles));
    		neighbours.add(tiles.get(index - xTiles + 1));
    		neighbours.add(tiles.get(index + 1));
    	}
    	else if (row == yTiles - 1 && column < xTiles - 1) {
    		neighbours.add(tiles.get(index - xTiles - 1));
    		neighbours.add(tiles.get(index - xTiles));
    		neighbours.add(tiles.get(index - xTiles + 1));
    		neighbours.add(tiles.get(index - 1));
    		neighbours.add(tiles.get(index + 1));
    	}
    	else if (row == yTiles - 1 && column == xTiles - 1) {
    		neighbours.add(tiles.get(index - xTiles - 1));
    		neighbours.add(tiles.get(index - xTiles)); 
    		neighbours.add(tiles.get(index - 1));
    	}
    	return neighbours;
    }
    
    public void uncover(Tile x) {
    	Queue<Tile> queue = new LinkedList<>();  
    	queue.add(x);
    	while (!queue.isEmpty()) {  
    		Tile y = queue.remove();
    		if (y.bomb) {
    			y.select(); 
    			break; 
    		}
    		y.open = true; // uncover considered tile
    		y.flag = false; 
    		y.setIcon();
    		ArrayList<Tile> neighbours = fetchNeighbours(y); 
    	    int neighbourBombs = 0; 
    		for (Tile z : fetchNeighbours(y)) { 
    			if (z.flag) { // only uncover non-open tiles
    				neighbourBombs += 1; 
    			}
    		}
    		if (y.bombCount == neighbourBombs) { //proceed only if it has no bomb neighbours
	    		for (Tile z : neighbours) { 
	    			if (!z.open && !z.flag) { // only uncover non-open tiles
	    				queue.add(z);
	    			}
	    		}
    		}
    	}
    	int end = 0;
    	for (Tile y : tiles) {
    		if(y.open) {
    			end += 1;
    		}
    	}
    	if (end == xTiles * yTiles - nBombs) {
    		for (Tile y : tiles) {
    			y.reveal();
    		}
    		timer.stop();
    		menu.victory(elapsedTime());
    	}
    }

    
    public void showBoard() {
    	uncover(tiles.get(40));
    	for (int x = 0; x < xTiles; x++) {
    		ArrayList<Integer> line = new ArrayList<>();   
    		for (Tile tile : tiles) {
	    		int index = tiles.indexOf(tile); 
	        	int row = (int)Math.floor(index / xTiles);
	        	if (row == x) {
	        		if (tile.open) { 
	        			line.add(tile.bombCount);
	        		}
	        		else {
	        			line.add(10);
	        		}
	        	}
	    	}
    		System.out.println(line);
    	}
    }
 
    public void reveal(Tile bombTile) { 
    	for (Tile tile : tiles) {
    		if (tile != bombTile) {
    			tile.reveal();
    		}
    	}
    }
 
    public int remainingBombs() {
    	int remainingBombs = nBombs; 
    	for (Tile tile : tiles) {
    		if (tile.flag){
    			remainingBombs -= 1;
    		}
    	}
    	return(remainingBombs); 
    }
    
    public void flag() {
    	int remainingBombs = remainingBombs(); 
    	bombLabel.setText("Bombs: " + remainingBombs);
    }
    public void unflag() {
    	int remainingBombs = remainingBombs(); 
    	bombLabel.setText("Bombs: " + remainingBombs);
    }
    
    public void gameOver() {
    menu.gameOver(); 
    }
    
    public String elapsedTime() {
    	long elapsedTime = System.currentTimeMillis() - startTime;
	long elapsedSeconds = elapsedTime / 1000;
	long secondsDisplay = elapsedSeconds % 60;
	long elapsedMinutes = elapsedSeconds / 60;
	
	
	String time = "";   
	if (secondsDisplay < 10) {
		time = elapsedMinutes + ":0" + secondsDisplay;
	}
	else {
		time = elapsedMinutes + ":" + secondsDisplay;
	}
	return(time); 	
    }
    
//   public void displayTime () {
//   while (true) {
//	   try {
//		   TimeUnit.SECONDS.sleep(1);
//		   timeLabel.setText("Time: " + elapsedTime());
//		} catch(InterruptedException e) {
//		}
//   }
//   }
   
    
}