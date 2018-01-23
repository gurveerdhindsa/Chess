import java.util.*;
/**
 * Allows the pieces to know where they are being moved.
 * 
 * @author Gurveer Dhindsa
 * @version 2016-11-27
 */


public abstract class ChessPiece implements ChessPieceInterface
{
    protected char id; 
    private String player;
    private ChessGame game; 
    private ChessLocation location;
    private ArrayList<ChessLocation> threateningLocations;
    
    /**
     * Constructor for objects of class ChessPiece
     */
    public ChessPiece(String owner, ChessLocation initialLocation, ChessGame game)
    {
        this.game = game;
        player = owner;
        location = null;
        this.game.getBoard().placePieceAt(this,initialLocation);
        threateningLocations = new ArrayList<>();
    }
    
    /**
     * Allows subclasses to update the ThreateningLocations array based on the type of piece
     */
    protected abstract void updateThreateningLocation(ChessLocation newLocation);
    
    /**
     * Returns the threats
     */
    public ArrayList<ChessLocation> getThreats()
    {
        return threateningLocations;
    }
    
    /**
     * Adds to the threats array
     */
    public void addThreateningLocation(ChessLocation newLocation)
    {
        threateningLocations.add(newLocation);
    }
    
    /**
     * Resets the threats array
     */
    public void eraseThreateningLocations()
    {
        if (threateningLocations != null)
        {
            threateningLocations.clear();
        }
    }
    
    /**
     * @return id    The ID of the piece
     */
    public char getId()
    {
        return id;
    }
    
    public String getPlayer()
    {
        return player;
    }
    
    /**
     * @return location   current location of the piece
     */
    public ChessLocation getLoc()
    {
        return location;
    }
    
    public void setLoc(ChessLocation new_location)
    {
        location = new_location;
    }
    
    /**
     * @return game  The game object for ChessGame
     */    
    public ChessGame getGame()
    {
        return game;
    }
    
   /**
     * Updates the current location of the chess piece
     */
    public void updateLoc(ChessLocation newLoc)
    {
        this.location = newLoc;
    }

    /**
     * Checks the legality of the move
     */
    public boolean moveTo(ChessLocation newLocation, boolean status)
    {
        if(this.checkLineOfSight(this.getLoc(), newLocation) == true)
        {
            System.out.println("There is a piece in the way!");
            return false;
        }
        else if(game.getBoard().isPieceAt(newLocation)== true)
        {
            if (game.getBoard().getPieceAt(newLocation).player.equals(this.player))
            {
                System.out.println("A friendly piece is in that location. You cannot move a piece there.");
                return false;
            }
            else
            {
               getGame().getBoard().removePiece(this.getLoc());
               getGame().getBoard().placePieceAt(this, newLocation);
               this.setLoc(newLocation);
               return true;
            }
            
        }
        else
        {
            getGame().getBoard().removePiece(this.getLoc());
            getGame().getBoard().placePieceAt(this, newLocation);
            this.setLoc(newLocation);
            return true;
        }
    }
    
     /**
     * @return   whether or not there are any pieces from the initial to end position of the chess piece
     */
    protected boolean checkLineOfSight(ChessLocation initial, ChessLocation end)
    {
        int startCol = initial.getCol(); 
        int startRow = initial.getRow();
        int endCol = end.getCol();
        int endRow = end.getRow();
        
        //Horizontal movement
        if (startRow - endRow == 0)
        {
            //Moving left
            if (startCol - endCol > 0)
            {
                for (int x = startCol - 1; x>endCol; x--)
                {
                    if (game.getBoard().isPieceAt(new ChessLocation(startRow, x)))
                    {
                        return true;
                    }
                }
            } 
            
            //Moving right
            if (startCol - endCol < 0)
            {
                for (int x = startCol + 1; x<endCol; x++)
                {
                    if (game.getBoard().isPieceAt(new ChessLocation (startRow, x)))
                    {
                        return true;
                    }
                }       
                return false;       
            }
            return false;               
        }
        
        
        //Vertical movement
        if (startCol - endCol == 0)
        {
            //Moving up
            if (startRow - endRow > 0)
            {
                for (int x = startRow - 1; x>endRow; x--)
                {
                    if (game.getBoard().isPieceAt(new ChessLocation(x, startCol)))
                    {
                        return true;
                    }
                }   
                return false;           
            }
            //Moving down
            if (startRow - endRow < 0)
            {
                for (int x = startRow + 1; x<endRow; x++)
                {
                    if (game.getBoard().isPieceAt(new ChessLocation(x, startCol)))
                    {
                        return true;
                    }
                }
                return false;               
            }
        }
        
        //Diagonal movement
        if (Math.abs(startCol - endCol) == Math.abs(startRow - endRow))
        {
            //Diagonally up left
            if(startRow - endRow > 0 && startCol - endCol > 0)
            {
                for (int x = startRow-1,y = startCol-1; x >endRow; x--, y--)
                {
                    if(game.getBoard().isPieceAt(new ChessLocation(x, y)))
                    {
                        return true;   
                    }
                }
                return false;
            }
            //Diagonally up right
            if(startRow - endRow > 0 && startCol - endCol < 0)
            {
                for (int x = startRow-1, y = startCol+1; x <endRow; x--, y++)
                {
                    if(game.getBoard().isPieceAt(new ChessLocation(x, y)))
                    {
                        return true;   
                    }
                }
                return false;
            }
            //Diagonally down right
            if(startRow - endRow < 0 && startCol - endCol < 0)
            {
                for (int x = startRow+1, y = startCol+1; x <endRow; x++, y++)
                {
                    if(game.getBoard().isPieceAt(new ChessLocation(x, y)))
                    {
                        return true;   
                    }
                }
                 return false;
            }
            //Diagonally down left
            if(startRow - endRow < 0 && startCol - endCol > 0)
            {
                for (int x = startRow+1, y = startCol-1; x < endRow; x++, y--)
                {
                    if(game.getBoard().isPieceAt(new ChessLocation (x, y)))
                    {
                        return true;   
                    }
                }
                return false;
            }
        } 
        return true;
    }
}
