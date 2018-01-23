/**
 * The bishop on the chessboard
 * 
 * @author Gurveer Dhindsa
 * @version 2016-11-27
 */

import java.util.*;
public class Bishop extends ChessPiece
{ 
    /**
     * Constructor for objects of class Bishop
     */
    public Bishop(String player,ChessLocation location, ChessGame game_no)
    {
        super(player,location,game_no);

        if (player.equals("Player1"))
        {
            super.id = 'B';
        }

        else
        {
            super.id = 'b';
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
     * Checks the legality of the desired move. If legal, bishop is moved to that location.
     */
    public boolean moveTo(ChessLocation new_location, boolean status)
    {
        if(this.getLoc().checkEqual(new_location) == true)
        {
            System.out.println("you did not perform a move");
            return false;
        }

        if(Math.abs(new_location.getRow() - this.getLoc().getRow()) == Math.abs(new_location.getCol() - this.getLoc().getCol()))
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
                return true;
            }
        }

        else
        {
            System.out.println("Invalid move for bishop");
            return false;
        }
    }
}
