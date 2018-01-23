/**
 * The pawn on the chessboard
 * 
 * @author Gurveer Dhindsa
 * @version 2016-11-27
 */
public class Pawn extends ChessPiece 
{
    public boolean first_move;
    private int initial_row;

    /**
     * Constructor for objects of class Pawn
     */
    public Pawn(String player, ChessLocation location, ChessGame game)
    {
        // initialise instance variables
        super(player,location,game);
        first_move = false;
        initial_row = location.getRow();

        if (player.equals("Player1"))
        {
            super.id = 'P';
        }
        else
        {
            super.id = 'p';
        }
    }

    /**
     * Checks the legality of the desired move. If legal, pawn is moved to that location.
     */    
    public boolean moveTo(ChessLocation new_location, boolean status)
    {
        ChessPiece piece = getGame().getBoard().getPieceAt(new_location);

        if(Math.abs(this.getLoc().getRow() - new_location.getRow()) == 2 && Math.abs(this.getLoc().getCol() - new_location.getCol()) == 0)
        {
            if(first_move != false)
            {
                System.out.println("Sorry can't move two rows again");
                return false;
            }
            else
            {
                if(initial_row == 1)
                {
                    if((new_location.getRow() - this.getLoc().getRow()) < 0)
                    {
                        System.out.println("Can't move backwards");
                        return false;
                    }
                    else
                    {
                        if (status == true)
                        {
                            if (super.moveTo(new_location,status) == true)
                            {
                                first_move = true;
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
                }

                else
                {
                    if(new_location.getRow() - this.getLoc().getRow() > 0)
                    {   
                        System.out.println("Can't move backwards");
                        return false;
                    }
                    else
                    {
                        if (status == true)
                        {
                            if (super.moveTo(new_location,status) == true)
                            {
                                first_move = true;
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
                }
            }
        }
        else if(Math.abs(this.getLoc().getRow() - new_location.getRow()) == 1 && Math.abs(this.getLoc().getCol() - new_location.getCol())==0)
        {
            if(initial_row == 1)
            {
                if((new_location.getRow() - this.getLoc().getRow()) < 0)
                {
                    System.out.println("Can't move backwards");
                    return false;
                }
                else
                {
                    if (status == true)
                    {
                        if (super.moveTo(new_location,status) == true)
                        {
                            first_move = true;
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
            }
            else
            {
                if(new_location.getRow() - this.getLoc().getRow() > 0)
                {
                    System.out.println("Can't move backwards");
                    return false;
                }
                else
                {
                    if (status == true)
                    {
                        if (super.moveTo(new_location,status) == true)
                        {
                            first_move = true;
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
            }
        }

        else if (Math.abs(this.getLoc().getRow() - new_location.getRow()) == 1 && Math.abs(this.getLoc().getCol() - new_location.getCol()) == 1 && piece!= null && !(piece.getPlayer().equals(this.getPlayer()))) 
        {
            if (status == true)
            {
                if (super.moveTo(new_location,status) == true)
                {
                    first_move = true;
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
            System.out.println("Invalid move for pawn");
            return false;
        }
    }

    /**
     * Overrides method in superclass to update threateningLocations array
     */
    protected void updateThreateningLocation(ChessLocation location)
    {
        super.eraseThreateningLocations();

        for (int i = 0; i<8; i++)
        {
            for (int j = 0; j<8; j++)
            {
                if (moveTo(location,false) == true && super.checkLineOfSight(this.getLoc(),new ChessLocation(i,j)) == false)
                {
                    super.addThreateningLocation(new ChessLocation (i,j));
                }
            }
        }
    }
}