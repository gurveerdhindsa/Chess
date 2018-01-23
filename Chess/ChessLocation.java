/**
 * The location for the pieces
 * 
 * @author Gurveer Dhindsa
 * @version 2016-11-27
 */
public class ChessLocation
{
    
    private int row;
    private int col;

    /**
     * Constructor for objects of class ChessLocation
     */    
    public ChessLocation(int newRow, int newCol)
    {
       if (newRow >=0 && newRow < 8 && newCol >=0 && newCol <8)
       {
           row = newRow;
           col = newCol;
       }
       else
       {
           System.out.println ("Value is not in range. Enter valid values (0-7)");
       }
    }

    /**
     * @return     The current row of piece       
     */    
    public int getRow()
    {
        return row;
    }

    /**
     * @return     The current column of piece       
     */    
    public int getCol()
    {
        return col;
    }

    /**
     * @param  cp  The location being compared with
     * @return     Checks if given location has same row and column       
     */    
    public boolean checkEqual (ChessLocation cp)
    {
        if (this.row == cp.getRow() && this.col == cp.getCol())
        {
            return true;
        }
        return false;
    }
}
