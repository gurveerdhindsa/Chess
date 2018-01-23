
/**
 * Allow the knight piece to be stored at different locations
 * 
 * @author Gurveer Dhindsa
 * @version 2016-11-27
 */
public class ChessBoard
{
    private ChessPiece chessBoard[][];

    /**
     * Constructor for objects of class ChessBoard
     */
    public ChessBoard()
    {
        chessBoard = new ChessPiece[8][8];
        drawBoard();
    }

    /**
     * @param location     the location of the desired chess piece
     * @return ChessPiece  returns the piece at the specified location
     */
    public ChessPiece getPieceAt(ChessLocation location)
    {
        return chessBoard[location.getRow()][location.getCol()];
    }


    /**
     * Determines if the piece is at the specified location
     * @param location  the location of the piece
     * @return       true if piece is located at location, false if piece does not exist
                     in that location
     */
    public boolean isPieceAt(ChessLocation location) //false if no piece on that location
    {
        if(chessBoard[location.getRow()][location.getCol()] == null)
        {
            return false;
        }
        
        else
        {
            return true;
        }
    }
    
    /**
     * Places piece at desired location while replacing existing pieces that may be there
     * @param  piece     The piece being moved
     * @param  location   The desired location to place the piece
     */
    public void placePieceAt(ChessPiece piece, ChessLocation location)
    {
        chessBoard[location.getRow()][location.getCol()] = piece;
        piece.setLoc(location);
    }
    
    /**
     * Constructs the 8x8 chess board without any pieces on it
     */
    public void drawBoard()
    {
        for(int x=0; x<chessBoard.length; x++)
        {
            for(int y=0; y<chessBoard.length; y++)
            {
                this.chessBoard[x][y] = null;
            }
        }
    }
    
    /**
     * Removes the piece at desired location (makes it null)
     * @param  location     The location where the piece is wanted to be removed
     */    
    public void removePiece(ChessLocation location)
    {
        chessBoard[location.getRow()][location.getCol()] = null;
    }
    
   /**
     * Prints the board (with pieces if applicable) to the terminal window
     */    
    public void printBoard()
    {
        for(int x=0; x<chessBoard.length; x++)
        {
            for(int y=0; y<chessBoard.length; y++)
            {   
                if(chessBoard[x][y] instanceof ChessPiece)
                {
                    System.out.print(chessBoard[x][y].id + "  ");
                }
                else
                {
                    System.out.print('-' + "  ");
                }
            }
            System.out.println("");
        }
    }
}
