import java.lang.Math;
/**
 * Represents the queen piece in a chessgame.
 * @author Gurveer Dhindsa
 * @version 2016-11-27
 */
public class Queen extends ChessPiece 
{
    /**
     * Constructor for objects of class Queen
     */
    public Queen(String player, ChessLocation c_location, ChessGame game_id)
    {
        // initialise instance variables
        super(player,c_location,game_id);

        if (player.equals("Player1"))
        {
            super.id = 'Q';
        }

        else
        {
            super.id = 'q';
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
     * Checks the legality of the desired move. If legal, queen is moved to that location.
     */ 
    public boolean moveTo(ChessLocation new_location, boolean status)
    {
        if(this.getLoc().checkEqual(new_location) == true)
        {
            System.out.println("you did not perform a move");
            return false;
        }
        if(Math.abs(new_location.getRow() - this.getLoc().getRow()) == Math.abs(new_location.getCol() - this.getLoc().getCol()))// checks if its a diagonal move
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
        else if(new_location.getRow() == this.getLoc().getRow() && new_location.equals(this.getLoc()) != true)// checks if its a horizontal move
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
        else if(new_location.getCol() == this.getLoc().getCol() && new_location.equals(this.getLoc()) != true)// checks if its a vertical move
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
            System.out.println("Invalid move for queen");
            return false;
        }
    }
}
