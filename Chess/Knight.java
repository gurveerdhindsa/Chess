/**
 * The knight on the chessboard
 * 
 * @author Gurveer Dhindsa
 * @version 2016-11-27
 */
import java.lang.Math;
public class Knight extends ChessPiece 
{
    /**
     * Constructor for objects of class Knight
     */
    public Knight(String player, ChessLocation location, ChessGame game)
    {
        super(player,location,game);
        
        if (player.equals("Player1"))
        {
            super.id = 'N';
        }
        
        else
        {
            super.id = 'n';
        }
    }
    
    /**
     * Checks the legality of the desired move. If legal, knight is moved to that location.
     */
    public boolean moveTo(ChessLocation new_location, boolean status)
    {
        int rows = Math.abs(this.getLoc().getRow() - new_location.getRow());
        int columns = Math.abs(this.getLoc().getCol() - new_location.getCol());
        
        if(rows == 2 && columns == 1)
        {
            if (super.getGame().getBoard().isPieceAt(new_location) != true)
            {
                if (status == true)
                {
                    getGame().getBoard().removePiece(this.getLoc());
                    getGame().getBoard().placePieceAt(this, new_location);
                    super.setLoc(new_location);
                    return true;
                }
                
                else
                {
                    return true;
                }
            }
            else
            {
                System.out.println ("Another piece is occupying the desired space.");
                return false;
            }
        }
            
        else if(rows == 1 && columns == 2)
        {
            if (super.getGame().getBoard().isPieceAt(new_location) != true)
            {
                if (status == true)
                {
                    getGame().getBoard().removePiece(this.getLoc());
                    getGame().getBoard().placePieceAt(this, new_location);
                    super.setLoc(new_location);
                    return true;
                }
                
                else
                {
                    return true;
                }
            }
            else
            {
               System.out.println ("Another piece is occupying the desired space."); 
               return false;
            }
        }
        else
        {
            System.out.println("Invalid move for a Knight");
            return false;
        }
    }
    
    /**
     * Overrides method in superclass to update threateningLocations array
     */
    protected void updateThreateningLocation(ChessLocation newLocation)
    {
        super.eraseThreateningLocations();
        
        for(int i = 0; i <8; i++)
        {
            for(int j =0; j < 8; j++)
            {
                if(moveTo(newLocation,false) == true)
                {
                    super.addThreateningLocation(new ChessLocation(i,j));
                }
            }
        }
    }
    
}
