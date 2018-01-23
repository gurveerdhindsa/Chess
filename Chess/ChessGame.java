/**
 * Manages the board used for chess pieces
 * 
 * @author Gurveer Dhindsa
 * @version 2016-11-27
 */
import java.util.*;
public class ChessGame
{
    private ChessBoard board;
    private String p1;
    private String p2;
    private ArrayList<ChessPiece> arrayKings;
    /**
     * Constructor for objects of class ChessGame
     */
    public ChessGame(String player1, String player2)
    {
        board = new ChessBoard();
        arrayKings = new ArrayList<>(); 
        p1 = player1;
        p2 = player2;

        new King(p1, new ChessLocation(0,4),this);
        new Queen(p1, new ChessLocation(0,3),this);
        new Bishop(p1, new ChessLocation(0,2), this);
        new Bishop(p1, new ChessLocation(0,5), this);
        new Knight(p1, new ChessLocation(0,1), this);
        new Knight(p1, new ChessLocation(0,6), this);
        new Rook(p1, new ChessLocation(0,0), this);
        new Rook(p1, new ChessLocation(0,7), this);
        new Pawn(p1, new ChessLocation(1,0), this);
        new Pawn(p1, new ChessLocation(1,1),this);
        new Pawn(p1, new ChessLocation(1,2), this);
        new Pawn(p1, new ChessLocation(1,3), this);
        new Pawn(p1, new ChessLocation(1,4), this);
        new Pawn(p1, new ChessLocation(1,5), this);
        new Pawn(p1, new ChessLocation(1,6), this);
        new Pawn(p1, new ChessLocation(1,7), this);

        new King(p2, new ChessLocation(7,4),this);
        new Queen(p2, new ChessLocation(7,3),this);
        new Bishop(p2, new ChessLocation(7,2), this);
        new Bishop(p2, new ChessLocation(7,5), this);
        new Knight(p2, new ChessLocation(7,1), this);
        new Knight(p2, new ChessLocation(7,6), this);
        new Rook(p2, new ChessLocation(7,0), this);
        new Rook(p2, new ChessLocation(7,7), this);
        new Pawn(p2, new ChessLocation(6,0), this);
        new Pawn(p2, new ChessLocation(6,1),this);
        new Pawn(p2, new ChessLocation(6,2), this);
        new Pawn(p2, new ChessLocation(6,3), this);
        new Pawn(p2, new ChessLocation(6,4), this);
        new Pawn(p2, new ChessLocation(6,5), this);
        new Pawn(p2, new ChessLocation(6,6), this);
        new Pawn(p2, new ChessLocation(6,7), this);
    }

    /**
     * @return     The chessboard    
     */
    public ChessBoard getBoard()
    {
        return board;
    }
    
    /**
     * Adds king to the list containing the 2 kings for the chess game then returns the list
     */
    public ArrayList<ChessPiece> checkKings()
    {
        arrayKings.clear();
        for(int x =0; x<8; x++)
        {
            for(int y=0; y<8; y++)
            {
                if(this.getBoard().getPieceAt(new ChessLocation(x,y))!=null)
                {
                    ChessPiece piece = this.getBoard().getPieceAt(new ChessLocation(x,y));
                    if(piece instanceof King)
                    {
                        arrayKings.add(piece);
                    }
                }
            }
        }
        return arrayKings;
    }
}
