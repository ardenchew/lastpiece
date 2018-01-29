package io.github.ardenchew.lastpiece;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    protected void onStart() {
        super.onStart();

        int[] boardSize = {7, 5, 3, 1};
        ArrayList<Player> players = new ArrayList<Player>();
        Player userPlayer1 = new UserPlayer("Player 1");
        players.add(userPlayer1);
        if (getIntent().hasExtra("io.github.ardenchew.lastpiece.multi")) {
            Player userPlayer2 = new UserPlayer(getIntent().getExtras().getString("io.github.ardenchew.lastpiece.multi"));
            players.add(userPlayer2);
        } else if (getIntent().hasExtra("io.github.ardenchew.lastpiece.single")) {
            Player computerPlayer1 = new ComputerPlayer(getIntent().getExtras().getString("io.github.ardenchew.lastpiece.single"));
            players.add(computerPlayer1);
        }

        TextView playerOne = (TextView) findViewById(R.id.playerOne);
        TextView playerTwo = (TextView) findViewById(R.id.playerTwo);
        playerOne.setText(players.get(0).getName());
        playerTwo.setText(players.get(1).getName());

        TextView score = (TextView) findViewById(R.id.score);
        PacketGameMaster game = new PacketGameMaster(boardSize, players);

        while(!game.isGameOver()) {
            if (game.isWhosTurn == 0) {
                playerOne.setHighlightColor(Color.parseColor("#219be5"));
                playerTwo.setHighlightColor(Color.parseColor("#ffffff"));
            } else {
                playerOne.setHighlightColor(Color.parseColor("#ffffff"));
                playerTwo.setHighlightColor(Color.parseColor("#219be5"));
            }

            Input in = game.getCurrentPlayer().getInput();

            switch
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //TODO
        }
        return true;
    }

    //public String updateScore(PacketGameMaster game) {}

    public class PacketGameMaster {

        public Move currentMove;
        public Board board;
        public ArrayList<Player> players;
        public int isWhosTurn;
        public Player winner; //TODO
        public boolean isGameOver;


        public PacketGameMaster(int[] boardSize, ArrayList<Player> playerList) {
            this.board = new Board(boardSize);
            this.isGameOver = false;
            this.players = playerList;
            this.currentMove = new Move();

            winner = this.players.get(1); //default for if user quits ?? should go somewhere else

        }

        public String getScore(){
            return (this.players.get(0).getPoints() + "-" + this.players.get(1).getPoints());
        }

        public Player getCurrentPlayer() { //TODO PLAYER ADJUSTMENT
            return this.players.get(isWhosTurn);
        }

        public boolean isGameOver() {
            return this.isGameOver;
        }

        public boolean hasMove() {
            return this.currentMove.isMoveComplete(); //this should return false as the user is building up move and return true when user is ready to provide move
        }

        public void handleInput(Input in) {
            switch (in.type) {
                case USERINPUT_GAMECOMMAND:
                    this.handleUserInput(in);
                    break;
                case CPUINPUT_GAMECOMMAND:
                    this.handleCpuInput(in);
                    break;
            }
        }

        public void handleCpuInput(Input in) {
            this.completeMove(in.move);
        }

        public void handleUserInput(Input in) {
            String inString = in.data;
            String[] inStringList = inString.split(" ");

            switch (inStringList[0]) {
                case "help":
                    this.printHelp();
                    break;
                case "quit":
                    this.changeTurn();
                    this.gameOver();
                    break;
                case "restart":
                    this.restartGame();
                    break;
                case "board":
                    this.printBoard();
                    break;
                case "add":
                    if (inStringList.length == 2) {
                        int[] addPiece = this.convertPiece(inStringList[1]);
                        if (addPiece != null) {
                            int pIdx = addPiece[0];
                            int cIdx = addPiece[1];
                            if (this.board.has(pIdx, cIdx)) {
                                if (this.currentMove.add(pIdx, cIdx)) {
                                    break;
                                }
                            }
                        }
                    }
                    System.out.println("Invalid input.");
                    break;
                case "remove":
                    if (inStringList.length == 2) {
                        int[] remPiece = this.convertPiece(inStringList[1]);
                        if (remPiece != null) {
                            int pIdx = remPiece[0];
                            int cIdx = remPiece[1];
                            if (this.board.has(pIdx, cIdx)) {
                                if (this.currentMove.remove(pIdx, cIdx)) {
                                    break;
                                }
                            }
                        }
                    }
                    System.out.println("Invalid input.");
                    break;
                case "complete":
                    if (this.currentMove.markAsComplete()) {
                        this.completeMove(this.currentMove);
                        break;
                    }
                    System.out.println("Invalid input.");
                    break;
                case "selected":
                    System.out.println(this.currentMove.toString());
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;

            }

            //deals with users input (not a full move)
            //help, complete, restart, quit, add, remove,
        }

        public void completeMove(Move m) { //for a computer player use as well
            this.handleMove(m);
            this.currentMove.reset();
            this.changeTurn();
            this.checkGameOver();
            if (!this.isGameOver) {
                this.printBoard();
            }
        }

        public void handleMove(Move m) {
            if (m.isMoveComplete()) {
                int cIdx = m.getColumnNum();
                ArrayList<Integer> removalPacketList = m.getPacketIdx();
                int pIdx;
                for (int i = 0; i < removalPacketList.size(); i++) {
                    pIdx = removalPacketList.get(i);
                    this.board.remove(pIdx, cIdx);
                    this.boardView.removeUpdate(cIdx, this.board);
                }
            } else {
                System.err.println("Move is incomplete.");
            }
        }

        private int[] convertPiece(String piece) {
            int[] pieceInt = new int[2];

            String[] firstTest = piece.split("p");
            if (!(firstTest[0].equals(""))) {
                return null;
            }
            String[] secondTest = firstTest[1].split("c");
            if (secondTest.length != 2) {
                return null;
            }
            try {
                pieceInt[0] = Integer.valueOf(secondTest[0]);
                pieceInt[1] = Integer.valueOf(secondTest[1]);
                return pieceInt;
            } catch (NumberFormatException e) {
                return null;
            }
        }


        public void changeTurn() {
            this.isWhosTurn = (this.isWhosTurn + 1) % 2;
        }

        public void checkGameOver() {
            if (this.board.getPacketNum() == 0) {
                this.gameOver();
            }
        }

        public void gameOver() {
            this.isGameOver = true;
        }

        public void restartGame() {
        }


        public void printBoard() {
        }


    }
}
