package Game;

public class Move {
    private Board board;
    private Player currentPlayer, otherPlayer;
    private Coordinates coordinates;
    private Piece_Color currentColor;

    public Move(Board board, Player currentPlayer, Player otherPlayer){
        this.board = board;
        this.currentPlayer = currentPlayer;
        this.otherPlayer = otherPlayer;
        this.currentColor = currentPlayer.getColor();
    }


    //getters
    public Coordinates getCoordinates() { return this.coordinates; }


    public void fillBoardAndUpdateGraph(Coordinates coordinates)
    {
        board.getPos(coordinates).setPieceColor(currentColor);
        currentPlayer.getGraph().updateBoard(board,coordinates);
    }

    private boolean isEscortFilled(Coordinates coordinates){
        if (!board.isValidPos(coordinates)) return Boolean.FALSE;
        if (board.getPos(coordinates).getPieceColor() == currentPlayer.getColor()) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public void fillEscorts(){
        Pos_Color color = board.getPos(coordinates).getPosColor();
        if(color == Pos_Color.LIGHT){
            if (isEscortFilled(coordinates.getDiagUp())) fillBoardAndUpdateGraph(coordinates.getUp());
            if (isEscortFilled(coordinates.getDiagDown())) fillBoardAndUpdateGraph(coordinates.getLeft());
        }
        else {
            if (isEscortFilled(coordinates.getDiagUp())) fillBoardAndUpdateGraph(coordinates.getRight());
            if (isEscortFilled(coordinates.getDiagDown())) fillBoardAndUpdateGraph(coordinates.getDown());
        }
        
    }

    public boolean makeMove(Coordinates coordinates){
        if (board.isValidPos(coordinates) && board.getPos(coordinates).getPieceColor() == Piece_Color.BLANK){
            this.coordinates = coordinates;
            fillBoardAndUpdateGraph(coordinates);
            fillEscorts();
            currentPlayer.setActive(Boolean.FALSE);
            otherPlayer.setActive(Boolean.TRUE);
            return Boolean.TRUE;
        }
        else
            return Boolean.FALSE;
    }

}
