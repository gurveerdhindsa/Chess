import java.util.*;
/**
 * Runs the chessboard program and prompts user with menu options
 * @author Gurveer Dhindsa
 * @version 2016-11-27
 */
public class PlayGame
{
    private static Scanner in = new Scanner(System.in);

    /**
     * Prints the introduction of the game
     */
    public static void menu()
    {
        System.out.println("Welcome to Chess!");
        System.out.println ("--------Game specifics--------");
        System.out.println ("Rows and Columns range from 0-7.");
        System.out.println ("Player1 goes first, then Player2.");
        System.out.println();
    }

    /**
     * Verifies that user input is valid on the checkboard
     */
    public static boolean checkIndices(int x, int y)
    {
        if(x < 0 || x > 7 || y < 0 || y > 7)
        {
            return true;
        }
        return false;
    }

    /**
     * Quits the game
     */
    public static void quitGame(String turn)
    {
        System.out.println(turn + "loses");
        System.exit(0);

    }

    /**
     * Verifies owner of piece that is being moved
     */
    public static boolean checkOwner(String turn,ChessGame game, ChessLocation location)
    {
        if(game.getBoard().getPieceAt(location).getPlayer().equals(turn))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Asks for initial and final coordinates and moves the desired piece
     */
    public static boolean move(ChessGame game, String turn)
    {
        System.out.println("Enter initial location: (x,y)");
        String start_position = in.nextLine();
        int x = Integer.parseInt(start_position.substring(0,1));
        int y = Integer.parseInt(start_position.substring(2));

        //Out of bounds
        while(checkIndices(x,y) == true)
        {
            System.out.println("Value is out of bounds.");
            System.out.println("Enter initial location: (x,y)");
            start_position = in.nextLine();
            x = Integer.parseInt(start_position.substring(0,1));
            y = Integer.parseInt(start_position.substring(2));
        }
        //Moving a piece that doesn't exist at that location
        while(game.getBoard().isPieceAt((new ChessLocation(x,y))) == false)
        {
            System.out.println("There is no piece at that location!");
            System.out.println("Enter initial location: (x,y)");
            start_position = in.nextLine();
            x = Integer.parseInt(start_position.substring(0,1));
            y = Integer.parseInt(start_position.substring(2));
        }
        //Moving the opponents piece
        if(checkOwner(turn,game,new ChessLocation(x,y)) == false)
        {
            System.out.println("You are trying to move a piece that is not yours!");
            return false;
        }

        System.out.println("Enter destination location: (x,y)");
        String end_position = in.nextLine();
        int new_x = Integer.parseInt(end_position.substring(0,1));
        int new_y = Integer.parseInt(end_position.substring(2));

        //Out of bounds
        while(checkIndices(new_x,new_y) == true)
        {
            System.out.println("Enter destination location: (x,y)");
            end_position = in.nextLine();
            new_x = Integer.parseInt(end_position.substring(0,1));
            new_y = Integer.parseInt(end_position.substring(2));
        }
        return game.getBoard().getPieceAt(new ChessLocation(x,y)).moveTo(new ChessLocation(new_x,new_y),true);
    }

    /**
     * Main method
     */
    public static void main(String[] args)
    {
        menu();
        ArrayList<ChessPiece> kings = new ArrayList<>();
        ChessGame game;
        game = new ChessGame("Player1","Player2");
        game.getBoard().printBoard();
        System.out.println();
        String turn = "Player1";
        boolean move_status;

        while(true)
        {
            kings = game.checkKings();

            System.out.println("TURN: " + turn);
            System.out.println("Would you like to: move/quit/restart");
            String menu_input = in.nextLine();

            if(menu_input.equalsIgnoreCase("quit"))
            {
                if (turn.equalsIgnoreCase("Player1"))
                {
                    System.out.println ("Player2 has won!");
                }
                else
                {
                    System.out.println ("Player1 has won!");
                }
                System.out.println ("Thank you for playing CHESS!");
                System.exit(0);

            }

            else if(menu_input.equalsIgnoreCase("move"))
            {
                move_status = move(game,turn);
                if(move_status && turn.equals("Player1"))
                {
                    turn = "Player2";
                    kings = game.checkKings();
                    checkKingCapture(kings);
                }    

                else if (move_status && turn.equals("Player2"))
                {
                    turn = "Player1";
                    kings = game.checkKings();
                    checkKingCapture(kings);    
                }

                game.getBoard().printBoard();
            }

            else if (menu_input.equalsIgnoreCase("restart"))
            {
                System.out.println("Restarting...");
                System.out.println ("----------------------");
                game = new ChessGame("Player1","Player2");
                game.getBoard().printBoard();
                turn = "Player1";
                System.out.println();
            }

            else
            {
                System.out.println(turn + " has lost.");
                System.exit(0);
            }
        }
    }

    /**
     * Checks if the king has been captured for both players. If it is captured the other player wins and the game ends.
     */
    public static void checkKingCapture(ArrayList<ChessPiece> kings)
    {
        if(kings.size() <2)
        {
            ChessPiece piece = kings.get(0);
            System.out.println(piece.getPlayer() + " wins the game!!!");
            System.exit(0);
        }
    }
}
