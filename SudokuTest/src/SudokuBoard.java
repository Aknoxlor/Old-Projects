
public class SudokuBoard
{
	private static int boardSize = 9;
	
	private int[][] board = new int[boardSize][boardSize]; //[left - right][up - down]
	private columnNode[] headers = new columnNode[boardSize * boardSize * 4]; //Size^2 * 4
	private columnNode superHeader = new columnNode();
	
	private class boardNode
	{
		public boardNode up;
		public boardNode down;
		public boardNode right;
		public boardNode left;

		public boardNode column;
	}
	
	private class columnNode extends boardNode
	{
		public int name;
		public int nodeNum;
	}
	
	private ColumnChooser activeChooser;
	private Solver activeSolver;

	public SudokuBoard()
	{
		//Generate Solving Board
		
		int column = 0;
		boardNode[] tempRows = new boardNode[boardSize * boardSize * 9]; 
		//first constraint - numbers
		while(column < boardSize * boardSize)
		{
			boardNode active = headers[column];
			headers[column].right = headers[column + 1];
			if(column != 0)
				headers[column].left = headers[column - 1];
			//headers[column].name = 
			
			//Each column has 9 nodes
			for(int row = 0; row < 9; row++)
			{
				boardNode newNode = new boardNode();
				active.down = newNode;
				newNode.up = active;
				tempRows[(column * 9) + row].right = newNode;
				newNode.left = tempRows[(column * 9) + row];
				active = newNode;
			}
			active.down = headers[column];
			
			column++;
		}
		
		//second constraint - rows
		while(column < boardSize * boardSize * 2)
		{
			boardNode active = headers[column];
			headers[column].right = headers[column + 1];
			headers[column].left = headers[column - 1];
			int specificColumn = column - (boardSize * boardSize);
			
			//Each column has 9 nodes
			for(int row = 0; row < 9; row++)
			{
				boardNode newNode = new boardNode();
				active.down = newNode;
				newNode.up = active;
				tempRows[specificColumn + (row * 9)].right.right = newNode;
				newNode.left = tempRows[specificColumn + (row * 9)].right;
				active = newNode;
			}
			column++;
		}
		
		//third constraint - columns
		while(column < boardSize * boardSize * 3)
		{
			boardNode active = headers[column];
			headers[column].right = headers[column + 1];
			headers[column].left = headers[column - 1];
			int specificColumn = column - (boardSize * boardSize * 2);
			
			//Each column has 9 nodes
			for(int row = 0; row < 9; row++)
			{
				boardNode newNode = new boardNode();
				active.down = newNode;
				newNode.up = active;
				tempRows[specificColumn + (row * 81)].right.right.right = newNode;
				newNode.left = tempRows[specificColumn + (row * 81)].right.right;
				active = newNode;
			}
			column++;
		}
		
		//fourth constraint - blocks
		while(column < boardSize * boardSize * 4)
		{
			boardNode active = headers[column];
			if(column != (boardSize * boardSize * 4) - 1)
				headers[column].right = headers[column + 1];
			headers[column].left = headers[column - 1];
			int specificColumn = column - (boardSize * boardSize * 3);
			
			//Each column has 9 nodes
			for(int row = 0; row < 9; row++)
			{
				boardNode newNode = new boardNode();
				boardNode rowNode = tempRows[specificColumn + (row*9) + (specificColumn/3)]; //FIX FIX FIX FIX FIX
				active.down = newNode;
				newNode.up = active;
				rowNode.right.right.right.right = newNode;
				newNode.left = rowNode.right.right.right;
				newNode.right = rowNode.right;
				rowNode.right.left = newNode;
				active = newNode;
			}
			column++;
		}
		superHeader.right = headers[0];
		headers[0].left = superHeader;
		superHeader.left = headers[(boardSize * boardSize * 4) - 1];	
		headers[(boardSize * boardSize * 4) - 1].right = superHeader;

		//Randomly generate board
		Solve(0);
		
		
		
	}

	private void Solve(int k)
	{
		if(superHeader == superHeader.right)
		{
			return;
		}
		else
		{
			boardNode column = activeChooser.operation(k);
			Cover(column);
			
			for(boardNode row = column.down; row != column; row = row.down)
			{
				activeSolver.operation(row);
				for(boardNode right = row.right; right != row; right = right.right)
					Cover(right);
				
				Solve(k + 1);
				
				//column = row.header
				for(boardNode left = row.left; left != row; left = left.left)
					Uncover(left);
			}
			Uncover(column);
		}
	}

	private void Cover(boardNode node)
	{
		node.right.left = node.left;
		node.left.right = node.right;
		for(boardNode iii = node.down; iii != node; iii = iii.down)
		{
			for(boardNode jjj = iii.right; jjj != iii; jjj = jjj.down)
			{
				jjj.up.down = jjj.down;
				jjj.down.up = jjj.up;
			}
		}	
	}

	private void Uncover(boardNode node)
	{
		for(boardNode iii = node.up; iii != node; iii = iii.up)
		{
			for(boardNode jjj = iii.left; jjj != iii; jjj = jjj.left)
			{
				jjj.up.down = jjj;
				jjj.down.up = jjj;
			}
		}
		node.right.left = node;
		node.left.right = node;
	}

	private interface ColumnChooser
	{
		boardNode operation(int a);
	}
	
	private interface Solver
	{
		boardNode operation(boardNode a);
	}
}


















