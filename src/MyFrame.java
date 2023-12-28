import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.CountDownLatch;


 interface CoordinatesCallback {
    void onCoordinatesChanged(int startX, int startY, int endX, int endY);
}




public class MyFrame extends JFrame implements ActionListener, MouseListener,CoordinatesCallback
{

       static int startX;
       static int startY;
      static int endX;
      static int endY;

    private CoordinatesCallback coordinatesCallback;
    static Board boardMyFrame;

    private static volatile boolean mouseReleased;
    private volatile boolean mousePressed;


    //protected  Main mainClass;



    //private CountDownLatch latch;

    private  CountDownLatch pressLatch=null;
    private   CountDownLatch releaseLatch=null;

    /*static {
        pressLatch = new CountDownLatch(1);
        releaseLatch = new CountDownLatch(1);
    }*/

    public void setCoordinatesCallback(CoordinatesCallback callback) {
        this.coordinatesCallback = callback;
    }

    private void notifyCoordinatesChanged() {
        if (coordinatesCallback != null) {
            coordinatesCallback.onCoordinatesChanged(startX, startY, endX, endY);
        }
    }

    public void onCoordinatesChanged(int startX, int startY, int endX, int endY) {
        // Handle the coordinates change in the MyFrame class
        // You might not need to do anything specific here if the
        // handling is done in the Main class.
    }



    MyFrame()
    {
        //this.mainClass = new Main();  // Initialize mainClass

        this.setTitle("CHESS BOARD");                        // sets title of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
        this.setSize(1000, 500);                             // sets the x ,y dimensions
        setLayout(null);                                     // Use absolute layout for precise positioning

        initializeBoard();

        this.pressLatch = new CountDownLatch(1); // Initialize with 1 for mouse press event
        this.releaseLatch = new CountDownLatch(1); // Initialize with 1 for mouse release event


       // this.latch = new CountDownLatch(2);

        // Add the mouse listener to the frame
        addMouseListener(this);

        // addComponentListener((ComponentListener) this);

        setVisible(true);



    }



    protected void initializeBoard()
    {
        JPanel chessPanel = new JPanel();
        chessPanel.setLayout(new GridLayout(8, 8));
        chessPanel.setBounds(50, 50, 400, 400);
        chessPanel.setBackground(new Color(0x5A5A5A));
        chessPanel.setOpaque(true);
        add(chessPanel);

        Board board = new Board();

        // Set boardMyFrame to the newly created board
        MyFrame.boardMyFrame = board;


        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {

                Spot spot = MyFrame.boardMyFrame.getBox(i, j);

                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setOpaque(true);

                spot.setLabel(label);


                if ((i + j) % 2 == 0)
                {
                    label.setBackground(new Color(0x4d0000));
                }
                else
                {
                    label.setBackground(new Color(0xffdacc));
                }

                label.setPreferredSize(new Dimension(50, 50)); // Adjust the size as needed

                if (i == 1)
                {
                    // label.setIcon(new ImageIcon("C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\black_soldier.png"));
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\Chess_pdt60.png";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 0 && j == 0)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\black_elephant.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 0 && j == 1)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\black_horse.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 0 && j == 2)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\black_camel.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 0 && j == 3)
                {
                    // ImageIcon img=new ImageIcon("C:\\Users\\rishi\\OneDrive\\Pictures\\chess.jpg");
                    // label.setIcon(img);
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\queen_black.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 0 && j == 4)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\black_king.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 0 && j == 5)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\black_camel.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 0 && j == 6)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\black_horse.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 0 && j == 7)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\black_elephant.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }

                else if (i == 6)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\Chess_plt60_soldier_white.png";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }

                else if (i == 7 && j == 0)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\white_elephant.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 7 && j == 1)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\white_horse.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 7 && j == 2)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\white_camel.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 7 && j == 3)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\white_queen.png";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 7 && j == 4)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\white_king.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 7 && j == 5)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\white_camel.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 7 && j == 6)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\white_horse.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }
                else if (i == 7 && j == 7)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\white_elephant.png\\";
                    ImageIcon originalIcon = new ImageIcon(imagePath);
                    ImageIcon scaledIcon = scaleIconToLabel(originalIcon, label);
                    label.setIcon(scaledIcon);
                }

                label.addMouseListener(this);
                chessPanel.add(label);
            }
        }
    }


    protected void displayBoardMyFrame(Game game)
    {
        JPanel chessPanel = new JPanel();
        chessPanel.setLayout(new GridLayout(8, 8));
        chessPanel.setBounds(50, 50, 400, 400);
        chessPanel.setBackground(new Color(0x5A5A5A));
        chessPanel.setOpaque(true);
        add(chessPanel);

        Board board = MyFrame.boardMyFrame;

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {

                Spot spot = board.getBox(i, j);

                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                label.setOpaque(true);

                spot.setLabel(label);

                if ((i + j) % 2 == 0)
                {
                    label.setBackground(new Color(0x4d0000));
                }
                else
                {
                    label.setBackground(new Color(0xffdacc));
                }

                Piece piece = spot.getPiece();

                if (piece != null)
                {
                    String imagePath = "C:\\Users\\rishi\\OneDrive\\Pictures\\chess piece\\" + getPieceImageFileName(piece);
                    label.setIcon(new ImageIcon(imagePath));
                }

                label.addMouseListener(this);
                chessPanel.add(label);
            }
        }
    }

    String getPieceImageFileName(Piece piece)
    {
        if (piece == null)
        {
            // Empty spot
            return null;
        }
        else
        {
            // Display the piece based on its type
            if (piece instanceof King)
            {
                return piece.isWhite() ? "white_king.png" : "black_king.png";
            }
            else if (piece instanceof Queen)
            {
                return piece.isWhite() ? "white_queen.png" : "queen_black.png";
            }
            else if (piece instanceof Elephant)
            {
                return piece.isWhite() ? "white_elephant.png" : "black_elephant.png";
            }
            else if (piece instanceof Camel)
            {
                return piece.isWhite() ? "white_camel.png" : "black_camel.png";
            }
            else if (piece instanceof Horse)
            {
                return piece.isWhite() ? "white_horse.png" : "black_horse_.png";
            }
            else if (piece instanceof Soldier)
            {
                return piece.isWhite() ? "Chess_plt60_soldier_white.png" : "Chess_pdt60.png";
            }
        }
        return null;
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





    protected static Spot getSpotFromLabel(JLabel label, Board board, int x, int y) {
        if(x<0||x>7||y<0||y>7){
            return null;
        }
        Spot spot = board.getBox(x, y);
        return spot;
    }




    @Override public void mousePressed(MouseEvent e)
    {

        try {
            System.out.println("this is mouse pressed");
            // System.out.println("startX and startY values before initialising " + (startX )  + (startY ));

            JLabel label = (JLabel) e.getSource();
            System.out.println("this is the label in mouse pressed " + (label.getY() / label.getHeight()) + " " + (label.getX() / label.getWidth()));
            Board board = MyFrame.boardMyFrame;

            Spot spot = getSpotFromLabel(label, board, label.getY() / label.getHeight(), label.getX() / label.getWidth());

            System.out.println("this is the spot in mouse pressed " + spot);

            if (spot != null) {
                int row = label.getY() / label.getHeight();
                int col = label.getX() / label.getWidth();


                //assert spot != null;
                int x = spot.getX();
                int y = spot.getY();
                Piece piece = spot.getPiece();

                //System.out.println("these are the values of spot we get from label in mouse pressed " + x + " " + y + " and the piece " + piece);


                this.startX = row;
                this.startY = col;

                //System.out.println("startX and startY values after initialising " + (startX )  + (startY ));

                //pressLatch = new CountDownLatch(1); // Initialize with 1 for mouse press event


                pressLatch.countDown();

            /*try{
            coordinateCallback.onCoordinatesChanged(startX, startY);}
            catch (NullPointerException x){
                System.out.println("null in coordinate callback");
            }*/

                System.out.println("these are the values of spot we get from label in mouse pressed " + x + " " + y + " and the piece " + piece);
                System.out.println("this is the value of startX and startY "+startX+" "+startY);

                //System.out.println("Label clicked at row: " + (row ) + ", col: " + (col));

                //this.mainClass.startX = this.startX;  // Use the existing instance of Main
                //this.mainClass.startY = this.startY;
                //System.out.println("this is the value of startX and startY "+ob.startX+" "+ob.startY);

                System.out.println("this is mouse pressed coordinate X "+ mousePressedCoordinateX());

                mousePressedCoordinateXSetter(row);
                mousePressedCoordinateYSetter(col);
                notifyCoordinatesChanged();
                mousePressed = true;
                System.out.println("the value of mouse pressed at end of loop "+ mousePressed);

            }
        }
        catch (NullPointerException o)
        {
            o.printStackTrace();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
    @Override public void mouseReleased(MouseEvent e)
    {
        try
        {
            System.out.println("this is mouse released");
            JLabel label = (JLabel)e.getSource();
            System.out.println("this is the label in mouse release "+ (e.getY() / label.getHeight() + label.getY() / label.getHeight())+" "+(e.getX() / label.getWidth() + label.getX() / label.getWidth()) );
            //System.out.println("Mouse released at row: " + (endX ) + ", col: " + (endY ));

            //System.out.println("Mouse pressed at row: " + (startX + 1) + ", col: " + (startY + 1));

            //Board board = BoardSingleton.getBoardInstance();
            Board board =MyFrame.boardMyFrame;

            Spot spot = getSpotFromLabel(label, board,e.getY() / label.getHeight() + label.getY() / label.getHeight(), e.getX() / label.getWidth() + label.getX() / label.getWidth() );

            //System.out.println("this is the spot in mouse release "+ spot);
            if(spot!=null){
            int row = e.getY() / label.getHeight() + label.getY() / label.getHeight();
            int col = e.getX() / label.getWidth() + label.getX() / label.getWidth();


            int x = spot.getX();
            int y = spot.getY();
            Piece piece = spot.getPiece();
            //System.out.println("these are the values of spot we get from label in mouseRelease " + x + " " + y+ "and the piece "+ piece );

            this.endX = row;
            this.endY = col;

            //System.out.println("Mouse released at row: " + (endX ) + ", col: " + (endY ));

                //releaseLatch = new CountDownLatch(1); // Initialize with 1 for mouse release event

                releaseLatch.countDown();

            System.out.println("these are the values of spot we get from label in mouseRelease " + x + " " + y+ "and the piece "+ piece );
                System.out.println("this is the value of endX and endY "+endX+" "+endY);
            //System.out.println("Label clicked at row: " + (row ) + ", col: " + (col ));
                //releaseLatch.countDown();

               // this.mainClass.endX = this.endX;  // Use the existing instance of Main
                //this.mainClass.endY = this.endY;

                //System.out.println("this is the value of endX and endY in Main class "+Main.endX+" "+ob.endY);
                System.out.println("this is mouse release coordinate X "+ mouseReleasedCoordinateX());
                notifyCoordinatesChanged();
                mouseReleased = true;

                mouseReleasedCoordinateXSetter(row);
                mouseReleasedCoordinateYSetter(col);

                System.out.println("this is the value of startX and startY from the mouse release function "+ startX+" "+startY);


                System.out.println("the value of mouse released at end of loop +mouse release "+ mousePressed+ " "+mouseReleased);
                //waitForMouseRelease();
            System.out.println();
            }
        }
        catch (NullPointerException o)
        {
            o.printStackTrace();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public  int mousePressedCoordinateX() throws InterruptedException {
        //System.out.println(MyFrame.startX);
        //MyFrame.waitForMousePress();
        //MyFrame mainClass = new MyFrame();
        //waitForMousePress();
        return startX;
    }
    public  void mousePressedCoordinateXSetter(int x) {
        //System.out.println(MyFrame.startX);
        //MyFrame.waitForMousePress();
        //MyFrame mainClass = new MyFrame();
        //waitForMousePress();
       this.startX=x;
    }
    public  int mousePressedCoordinateY() throws InterruptedException {
        //System.out.println(MyFrame.startY);
        //MyFrame mainClass = new MyFrame();
        //mainClass.waitForMousePress();
       // waitForMousePress();

        return startY;
    }

    public  void mousePressedCoordinateYSetter(int y) {
        //System.out.println(MyFrame.startX);
        //MyFrame.waitForMousePress();
        //MyFrame mainClass = new MyFrame();
        //waitForMousePress();
        this.startY=y;
    }


    public  int mouseReleasedCoordinateX() throws InterruptedException {
        //System.out.println(MyFrame.endX);
        //MyFrame mainClass = new MyFrame();
        //waitForMouseRelease();
        return endX;
    }

    public  void mouseReleasedCoordinateXSetter(int x) {
        //System.out.println(MyFrame.startX);
        //MyFrame.waitForMousePress();
        //MyFrame mainClass = new MyFrame();
        //waitForMousePress();
        this.endX=x;
    }

    public  int mouseReleasedCoordinateY() throws InterruptedException {
        //System.out.println(MyFrame.endY);
       // MyFrame mainClass = new MyFrame();
        //waitForMouseRelease();
        return endY;
    }

    public  void mouseReleasedCoordinateYSetter(int y) {
        //System.out.println(MyFrame.startX);
        //MyFrame.waitForMousePress();
        //MyFrame mainClass = new MyFrame();
        //waitForMousePress();
        this.endY=y;
    }

    public  void waitForMousePress() throws InterruptedException {
        //pressLatch.await();
        //Main.setterPressX(startX);
        //return new Spot(startX, startY, null);
        while (!isMousePressed()) {

            Thread.sleep(100); // Sleep for a short duration to avoid busy waiting

        }
        System.out.println("this is a statement before resetMousePressedFlag() method is called");
        resetMousePressedFlag();
        System.out.println("this is a statement after resetMousePressedFlag() method is called");

    }

    public  void waitForMouseRelease() throws InterruptedException {
        //releaseLatch.await();
        //return new Spot(endX, endY, null);

        while (!isMouseReleased()) {
            Thread.sleep(100); // Sleep for a short duration to avoid busy waiting
        }
        System.out.println("this is a statement before resetMouseReleaseFlag() method is called");

        resetMouseReleasedFlag();

        System.out.println("this is a statement after resetMouseReleaseFlag() method is called ");
        System.out.println("thsi is the value initialised in MyFrame class "+ this.startX+" "+this.startY);

        System.out.println("this is the mouse release cordinate "+ mouseReleasedCoordinateX()+" "+ mouseReleasedCoordinateY());
        Thread.sleep(1000);

    }

    private synchronized boolean isMousePressed() {
        return mousePressed;
    }

    private synchronized void resetMousePressedFlag() {
        mousePressed = false;
    }

    private synchronized boolean isMouseReleased() {
        return mouseReleased;
    }

    private synchronized void resetMouseReleasedFlag() {
        mouseReleased = false;
    }






    @Override public void mouseEntered(MouseEvent e)
    {
    }

    @Override public void mouseExited(MouseEvent e)
    {
    }

    @Override public void actionPerformed(ActionEvent e)
    {
        System.out.println("Button clicked: " + ((JButton)e.getSource()).getText());
    }

    public
    ImageIcon scaleIconToLabel(ImageIcon icon, JLabel label)
    {
        ComponentListener listener = new ComponentAdapter()
        {
            @Override public void componentResized(ComponentEvent e)
            {
                int labelWidth = label.getWidth();
                int labelHeight = label.getHeight();

                if (labelWidth <= 0 || labelHeight <= 0)
                {
                    // Width or height not yet determined, return the original icon
                    return;
                }

                Image originalImage = icon.getImage();
                Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

                label.setIcon(new ImageIcon(scaledImage));
                label.removeComponentListener(this); // Remove the listener after resizing
            }
        };

        label.addComponentListener(listener);

        return icon;
    }

    public Spot getBox(int startX, int startY) {
        return MyFrame.boardMyFrame.getBox(startX, startY);
    }

    @Override public void mouseClicked(MouseEvent e){
/*
    @Override public void mouseClicked(MouseEvent e)
    {
        try
        {
            System.out.println();
            System.out.println("this is mouse clicked");
            JLabel label = (JLabel)e.getSource();
            Board board = BoardSingleton.getBoardInstance();

            Spot spot = getSpotFromLabel(label, board);

            int row = label.getY() / label.getHeight();
            int col = label.getX() / label.getWidth();


            assert spot != null;
            int x = spot.getX();
            int y = spot.getY();
            Piece piece = spot.getPiece();



            MyFrame.labelX = row;
            MyFrame.labelY = col;

            //System.out.println("these are the values of spot we get from label " + x + " " + y);

            System.out.println(label.getHeight());
            System.out.println("Label clicked at row: " + (row + 1) + ", col: " + (col + 1));
        }
        catch (NullPointerException o)
        {
            o.printStackTrace();
        }
    }
    */
    }
}

