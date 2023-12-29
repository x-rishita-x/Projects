import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.ArrayList;



class Spot {
    private  Piece piece;
    private int x;
    private int y;

    private JLabel label;


    public Spot( int x, int y, Piece piece) {
        this.piece = piece;
        this.x = x;
        this.y = y;
        this.label = new JLabel();
    }

    public Piece getPiece() {
        return this.piece;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setPiece(Piece p) {
        this.piece = p;
    }

    public JLabel getLabel() {
        return this.label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    protected JLabel getLabelFromSpot(Spot spot, Board board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Spot currentSpot = board.getBox(i, j);
                if (currentSpot != null && currentSpot.equals(spot)) {
                    return currentSpot.getLabel();
                }
            }
        }
        return null;
    }


}

abstract class Piece {
    private boolean killed = false;
    private boolean white = false;

    public Piece(boolean white) {
        this.setWhite(white);
    }

    public boolean isWhite() {
        return this.white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isKilled() {
        return this.killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public Piece getPieceFromLabel(JLabel label, Board board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Spot spot = board.getBox(i, j);
                if (spot != null && spot.getLabel().equals(label)) {
                    return spot.getPiece();
                }
            }
        }
        return null;

    }


    public abstract boolean validMove(Board board, Spot start, Spot end);
}

class King extends Piece {
    public King(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        if (Math.abs(x1 - x2) == 1 && y1 == y2) {
            return true;
        } else if (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 1) {
            return true;
        } else {
            return false;
        }
    }
}

class Horse extends Piece {
    public Horse(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        int deltaX = Math.abs(x1 - x2);
        int deltaY = Math.abs(y1 - y2);


        return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
    }
}

class Elephant extends Piece {
    public Elephant(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();


        if (x1 == x2 && y1 != y2) {

            int minY = Math.min(y1, y2) + 1;
            int maxY = Math.max(y1, y2);
            for (int y = minY; y < maxY; y++) {
                if (board.getBox(x1, y).getPiece() != null) {
                    return false;
                }
            }
        } else if (y1 == y2 && x1 != x2) {

            int minX = Math.min(x1, x2) + 1;
            int maxX = Math.max(x1, x2);
            for (int x = minX; x < maxX; x++) {
                if (board.getBox(x, y1).getPiece() != null) {
                    return false;
                }
            }
        } else {
            return false;
        }


        if (end.getPiece() == null || end.getPiece().isWhite() != start.getPiece().isWhite()) {
            return true;
        }

        return false;
    }

}

class Soldier extends Piece {
    public Soldier(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        boolean isWhite = start.getPiece().isWhite();


        if (x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7) {
            return false;
        }

        if(isWhite){
            if(y1==y2){
                if(x2-x1==1){
                    return true;
                }
            }
            else{
                if(x2-x1==1&&(y2-y1==1||y2-y1==-1)){
                    return true;
                }
            }
        }
        else{
            if(y1==y2){
                if(x2-x1==-1){
                    return true;
                }
            }
            else{
                if(x2-x1==-1&&(y2-y1==1||y2-y1==-1)){
                    return true;
                }
            }
        }

        return false;
    }

}

class Camel extends Piece {
    public Camel(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
            return true;
        } else {
            return false;
        }
    }
}

class Queen extends Piece {
    public Queen(boolean white) {
        super(white);
    }

    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        int x1 = start.getX();
        int y1 = start.getY();
        int x2 = end.getX();
        int y2 = end.getY();

        if (x1 == x2 && y1 != y2) {
            return true;
        } else if (x1 != x2 && y1 == y2) {
            return true;
        } else if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
            return true;
        } else {
            return false;
        }
    }
}


class Board {
    Spot[][] checks;

    public Board() {
        checks = new Spot[8][8];
        resetBoard();
    }


    public Spot getBox(int x, int y) {
        try {
            return checks[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
        catch (NullPointerException q){
            System.out.println("the getBox method is returning null");
            return null;

        }
    }

    public Spot[][] getBoxes() {
        return this.checks;
    }

    public void resetBoard() {
        checks[0][0] = new Spot(0, 0, new Elephant(true));
        checks[0][1] = new Spot(0, 1, new Horse(true));
        checks[0][2] = new Spot(0, 2, new Camel(true));
        checks[0][3] = new Spot(0, 3, new Queen(true));
        checks[0][4] = new Spot(0, 4, new King(true));
        checks[0][5] = new Spot(0, 5, new Camel(true));
        checks[0][6] = new Spot(0, 6, new Horse(true));
        checks[0][7] = new Spot(0, 7, new Elephant(true));

        checks[1][0] = new Spot(1, 0, new Soldier(true));
        checks[1][1] = new Spot(1, 1, new Soldier(true));
        checks[1][2] = new Spot(1, 2, new Soldier(true));
        checks[1][3] = new Spot(1, 3, new Soldier(true));
        checks[1][4] = new Spot(1, 4, new Soldier(true));
        checks[1][5] = new Spot(1, 5, new Soldier(true));
        checks[1][6] = new Spot(1, 6, new Soldier(true));
        checks[1][7] = new Spot(1, 7, new Soldier(true));

        checks[7][0] = new Spot(7, 0, new Elephant(false));
        checks[7][1] = new Spot(7, 1, new Horse(false));
        checks[7][2] = new Spot(7, 2, new Camel(false));
        checks[7][3] = new Spot(7, 3, new Queen(false));
        checks[7][4] = new Spot(7, 4, new King(false));
        checks[7][5] = new Spot(7, 5, new Camel(false));
        checks[7][6] = new Spot(7, 6, new Horse(false));
        checks[7][7] = new Spot(7, 7, new Elephant(false));

        checks[6][0] = new Spot(6, 0, new Soldier(false));
        checks[6][1] = new Spot(6, 1, new Soldier(false));
        checks[6][2] = new Spot(6, 2, new Soldier(false));
        checks[6][3] = new Spot(6, 3, new Soldier(false));
        checks[6][4] = new Spot(6, 4, new Soldier(false));
        checks[6][5] = new Spot(6, 5, new Soldier(false));
        checks[6][6] = new Spot(6, 6, new Soldier(false));
        checks[6][7] = new Spot(6, 7, new Soldier(false));

        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                checks[i][j] = new Spot(i, j, null);
            }
        }
    }


}



enum PlayerType {
    HUMAN,
    COMPUTER
}

class Player {
    public boolean whiteSide;
    protected PlayerType playerType;
    protected boolean isCheckmated;

    public Player(boolean whiteSide, PlayerType playerType) {
        this.whiteSide = whiteSide;
        this.playerType = playerType;
    }

    public boolean isWhiteSide() {
        return this.whiteSide;
    }

    public PlayerType getPlayerType() {
        return this.playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public boolean isCheckmated() {
        return isCheckmated;
    }

    public void setCheckmated(boolean checkmated) {
        isCheckmated = checkmated;
    }
}

class HumanPlayer extends Player {
    public HumanPlayer(boolean whiteSide) {
        super(whiteSide, PlayerType.HUMAN);
    }
}

class ComputerPlayer extends Player {
    public ComputerPlayer(boolean whiteSide) {
        super(whiteSide, PlayerType.COMPUTER);
    }
}

class Move {
    private Player player;

    private Spot start;
    private JLabel labelStart;

    private Spot end;
    private JLabel labelEnd;
    private Piece pieceMoved;
    private Piece pieceKilled;
    boolean castlingMove = false;



    public Move(Player player, Spot start, Spot end, JLabel labelStart, JLabel labelEnd) {
        this.player = player;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
        this.labelStart=labelStart;
        this.labelEnd=labelEnd;
    }


    public boolean isCastlingMove() {
        return this.castlingMove;
    }

    public void setCastlingMove(boolean castlingMove) {
        this.castlingMove = castlingMove;
    }

    public Spot getStart() {
        return start;
    }

    public Spot getEnd() {
        return end;
    }

    public void setPieceKilled(Piece restPiece) {
        this.pieceKilled = restPiece;
    }
}

enum GameStatus {
    ACTIVE,
    BLACK_WIN,
    WHITE_WIN,
    FORFEIT,
    STALEMATE,
    RESIGNATION
}

class Game {
    private Player[] players = new Player[2];
    public  Board board;
    MyFrame ob=new MyFrame();
    private Player currentTurn;
    private GameStatus status;
    private ArrayList<Move> movesPlayed = new ArrayList<Move>();

    public Game() {
        this.board = new Board();
        ob.initializeBoard();
    }

    void initialize(Player p1, Player p2) {
        players[0] = p1;
        players[1] = p2;

        ob.initializeBoard();
        board.resetBoard();

        if (p1.isWhiteSide()) {
            this.currentTurn = p1;
        } else {
            this.currentTurn = p2;
        }

        movesPlayed.clear();
    }



    public GameStatus getStatus() {
        return this.status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public boolean isEnd() {
        return this.getStatus() != GameStatus.ACTIVE;
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) {

        Spot start = board.getBox(startX, startY);
        Spot end = board.getBox(endX, endY);

        JLabel labelStart=start.getLabelFromSpot(start, board);
        JLabel  labelEnd=end.getLabelFromSpot(end,board);

        Spot startBox= MyFrame.getSpotFromLabel(labelStart, board, startX, startY);
        Spot  endBox= MyFrame.getSpotFromLabel(labelEnd, board, endX, endY);


        //Spot startBox=ob.getBoxStart(MyFrame.startX, MyFrame.startY);
        //Spot endBox=ob.getBoxEnd(MyFrame.endX, MyFrame.endY);
        Move move = new Move(player, startBox, endBox, labelStart, labelEnd);


        return this.makeMove(move, player);
    }

    private boolean makeMove(Move move, Player player) {
        Piece sourcePiece = move.getStart().getPiece();
        Spot source = move.getStart();

        Piece labelSourcePiece;
        JLabel label;
        try {
             label= source.getLabel();
            labelSourcePiece = sourcePiece.getPieceFromLabel(label, board);
        } catch (NullPointerException e) {
            return false;
        }

        try {
            if (sourcePiece == null) {
                return false;
            }
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }

        if (labelSourcePiece == null) {
            return false;
        }

        if (player != currentTurn) {
            return false;
        }

        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            return false;
        }

        if (labelSourcePiece.isWhite() != player.isWhiteSide()) {
            return false;
        }

        if (!sourcePiece.validMove(board, move.getStart(), move.getEnd())) {
            return false;
        }

        Piece restPiece = move.getEnd().getPiece();
        Spot end = move.getEnd();
        JLabel labelEnd = end.getLabel();
        Piece labelRestPiece = (restPiece != null) ? restPiece.getPieceFromLabel(labelEnd, board) : null;

        if (!labelSourcePiece.validMove(board, source, end)) {
            return false;
        }

        // If restPiece is null, move sourcePiece to the destination
        if (restPiece == null) {
            movesPlayed.add(move);

            // Set destination spot to sourcePiece
            move.getEnd().setPiece(move.getStart().getPiece());

            // Set source spot to null
            move.getStart().setPiece(null);

            // Check if moving the sourcePiece results in a win
            if (sourcePiece instanceof King) {
                if (player.isWhiteSide()) {
                    this.setStatus(GameStatus.BLACK_WIN);
                } else {
                    this.setStatus(GameStatus.WHITE_WIN);
                }
            }

            // Change the turn
            if (this.currentTurn == players[0]) {
                this.currentTurn = players[1];
            } else {
                this.currentTurn = players[0];
            }

            return true;
        }

        // Continue with the rest of the code for the case when restPiece is not null
        if (restPiece != null) {
            restPiece.setKilled(true);
            move.setPieceKilled(restPiece);
        }

        if (labelRestPiece != null) {
            labelRestPiece.setKilled(true);
            move.setPieceKilled(labelRestPiece);
        }

        movesPlayed.add(move);

        move.getEnd().setPiece(move.getStart().getPiece());
        move.getStart().setPiece(null);

        // Check if killing the restPiece results in a win
        if (restPiece instanceof King) {
            if (player.isWhiteSide()) {
                this.setStatus(GameStatus.WHITE_WIN);
            } else {
                this.setStatus(GameStatus.BLACK_WIN);
            }
        }

        if (restPiece != null && restPiece.isWhite() == player.isWhiteSide()) {
            return false;
        }

        if (restPiece != null) {
            restPiece.getPieceFromLabel(label, board);
        } else {
            System.out.println("restPiece is null");
        }

        if (this.currentTurn == players[0]) {
            this.currentTurn = players[1];
        } else {
            this.currentTurn = players[0];
        }

        return true;
    }


    public Player getCurrentTurn() {
        return this.currentTurn;
    }

    public Board getBoard(Game game){
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


}

public class Main extends MyFrame implements  CoordinatesCallback {

    public static MyFrame mainClass;




    public void onCoordinatesChanged(int startX, int startY, int endX, int endY) {
        // Handle the updated coordinates in the Main class
        // You can access startX, startY, endX, and endY here
        // and perform any additional processing.
        this.startX=startX;
        this.startY=startY;
        this.endX=endX;
        this.endY=endY;

    }



    private static void displayBoard(Game game) {
        Spot[][] spots = game.board.getBoxes();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Spot spot = spots[i][j];
                Piece piece = spot.getPiece();

                if (piece == null) {
                    // Empty spot
                    System.out.print("- ");
                } else {
                    // Display the piece based on its type
                    if (piece instanceof King) {
                        System.out.print(piece.isWhite() ? "K " : "k ");
                    } else if (piece instanceof Queen) {
                        System.out.print(piece.isWhite() ? "Q " : "q ");
                    } else if (piece instanceof Elephant) {
                        System.out.print(piece.isWhite() ? "E " : "e ");
                    } else if (piece instanceof Camel) {
                        System.out.print(piece.isWhite() ? "C " : "c ");
                    } else if (piece instanceof Horse) {
                        System.out.print(piece.isWhite() ? "H " : "h ");
                    } else if (piece instanceof Soldier) {
                        System.out.print(piece.isWhite() ? "S " : "s ");
                    }
                }
            }
            System.out.println(); // Move to the next row
        }
    }

    //public  int getterPressX(){}
    //public static void setterPressX(int x){}






    public static void main(String[] args) {



        MyFrame mainClass=new MyFrame();



        Game game = new Game();
        Player player1 = new HumanPlayer(true);
        Player player2 = new HumanPlayer(false);

        Board board = new Board();
        mainClass.initializeBoard();



        game.initialize(player1, player2);
        game.setStatus(GameStatus.ACTIVE);

        game.setBoard(board);
        MyFrame.boardMyFrame=board;




        mainClass.setCoordinatesCallback((CoordinatesCallback) mainClass);


        while (!game.isEnd()) {
            mainClass.displayBoardMyFrame(game);
            displayBoard(game);

            System.out.println("Player " + (game.getCurrentTurn() == player1 ? "1 (White)" : "2 (Black)") + ", enter your move:");
            System.out.println("Enter the position of the piece to move (e.g., startX startY):");


            try {
                System.out.println("Before waiting for press: " + mainClass.startX + ", " + mainClass.startY);

              // mainClass.waitForMousePress();

                System.out.println("the after the wait mouse ");
                System.out.println("Mouse pressed at row: " + mainClass.mousePressedCoordinateX() + ", col: " + mainClass.mousePressedCoordinateY());

                //System.out.println("Start coordinates: " + startX + " " + startY);
                mainClass.waitForMouseRelease();

                Thread.sleep(1000);



                System.out.println("Mouse released at row: " + mainClass.mouseReleasedCoordinateX() + ", col: " + mainClass.mouseReleasedCoordinateY());
                System.out.println("Enter the position to move the piece to (e.g., endX endY):");


                System.out.println("Before waiting for release: " + mainClass.endX + ", " + mainClass.endY);

                int startX= mainClass.mousePressedCoordinateX(); ;
                int startY= mainClass.mousePressedCoordinateY(); ;
                int endX = mainClass.mouseReleasedCoordinateX();
                int endY = mainClass.mouseReleasedCoordinateY();



                System.out.println("End coordinates: " + endX + " " + endY);
                System.out.println("Start coordinates: "+ startX+" "+startY);

                boolean validMove = game.playerMove(game.getCurrentTurn(), startX, startY, endX, endY);


                System.out.println(validMove);

                if (validMove) {
                    mainClass.onCoordinatesChanged(startX, startY, endX, endY);
                    System.out.println("Valid move. Continuing to the next turn.");

                   /* MyFrame.startX = -1;
                    MyFrame.startY = -1;
                    MyFrame.endX = -1;
                    MyFrame.endY = -1;*/
                } else {
                    System.out.println("Invalid move. Try again.");
                    System.out.println();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        mainClass.displayBoardMyFrame(game);
        displayBoard(game);
        System.out.println("Game over! Result: " + game.getStatus());

    }
}