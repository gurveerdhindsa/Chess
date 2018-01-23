/**
 * The King on the chessboard
 * 
 * @author Gurveer Dhindsa
 * @version 2016-11-27
 */
import java.util.*;
public class King extends ChessPiece 
{
    /**
     * Constructor for objects of class King
     */
    public King(String player,ChessLocation c_location,ChessGame game_id)
    {
        // initialise instance variables
        super(player,c_location,game_id);

        if (player.equalsIgnoreCase("Player1"))
        {
            super.id = 'K';
        }

        else
        {
            super.id = 'k';
        }
    }
    
    /**
     * Overrides method in superclass to update threateningLocations array
     */
    protected void updateThreateningLocation (ChessLocation newLocation)
    {
        super.eraseThreateningLocations();

        for(int i = 0; i <8; i++)
        {
            for(int j =0; j < 8; j++)
            {
                if(moveTo(newLocation,false) == true && super.checkLineOfSight(this.getLoc(),new ChessLocation(i,j)) == false)
                {
                    super.addThreateningLocation(new ChessLocation(i,j));
                }
            }
        }
    }  

    /**
     * Checks the legality of the desired move. If legal, king is moved to that location.
     */     
    public boolean moveTo(ChessLocation new_location, boolean status)
    {
        if((Math.abs(new_location.getRow() - this.getLoc().getRow()) == 1 && Math.abs(new_location.getCol() - this.getLoc().getCol()) == 0) || (Math.abs(new_location.getCol() - this.getLoc().getCol()) == 1 && Math.abs(new_location.getRow() - this.getLoc().getRow())==0))
        {
            if (locationInDanger(new_location) == null)
            {
                if (status == true)
                {
                    if (super.moveTo(new_location,status) == true)
                    {
                        return true;
                    }

                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }

            else
            {
                return false;
            }
        }
        else
        {
            System.out.println("Invalid move for King");
            return false;
        }
    }
    
    /**
     * Updates threatening locations for all opponent pieces
     */
    public ChessPiece locationInDanger (ChessLocation destination)
    {
        for(int i=0; i<8; i++)
        {
            for(int j=0; j<8; j++)
            {
                Object piece = getGame().getBoard().getPieceAt(new ChessLocation(i,j));
                if(piece != null)
                {
                    ChessPiece piece2 = (ChessPiece)piece;
                    if(!(piece2.getPlayer().equalsIgnoreCase(super.getPlayer())))
                    {
                        piece2.updateThreateningLocation(destination);
                        for(ChessLocation loc : piece2.getThreats())
                        {
                            if(loc.checkEqual(destination))
                            {
                                return piece2;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * Returns boolean of whether or not King has any non-threatening moves
     */
    public boolean anyMovesLeft()
    {
        for(int i=0; i<8; i++)
        {
            for(int j=0; j < 8; j++)
            {
                ChessPiece piece = locationInDanger(new ChessLocation(i,j));
                if((piece == null) && this.moveTo((new ChessLocation(i,j)),false) && super.checkLineOfSight(this.getLoc(),new ChessLocation(i,j)) == false)
                {
                    return true;
                }
            }
        }
        return false; 
    }
    
    /**
     * Returns the piece which is currently threatening the King
     */
    public ChessPiece check()
    {
        ChessPiece piece = locationInDanger(this.getLoc());
        
        return piece;
    }
}