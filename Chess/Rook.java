
/**
 * The rook on the chessboard
 * 
 * @author Gurveer Dhindsa
 * @version 2016-11-27
 */
public class Rook extends ChessPiece 
{
    /**
     * Constructor for objects of class Rook
     */
    public Rook(String player, ChessLocation location, ChessGame game)
    {
        super(player,location,game);

        if (player.equals("Player1"))
        {
            super.id = 'R';
        }

        else
        {
            super.id = 'r';
        }
    }

    /**
     * Checks the legality of the desired move. If legal, rook is moved to that location.
     */    
    public boolean moveTo(ChessLocation new_location, boolean status)
    {
        if(new_location.getRow() == this.getLoc().getRow() && this.getLoc().equals(new_location)!= true)
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
        else if(new_location.getCol() == this.getLoc().getCol() && this.getLoc().equals(new_location)!=true)
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
            System.out.println("Invalid move for rook");
            return false;
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
}
