package io.github.ardenchew.lastpiece;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    public Input in;
    public int[] boardSize = {7, 5, 3, 1};
    public ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Button> btnList;
    public Button completeBtn;
    public PacketGameMaster game;

    public String[] btnStrList;
    public boolean restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Player userPlayer1 = new UserPlayer("Player 1");
        this.players.add(userPlayer1);
        if (getIntent().hasExtra("io.github.ardenchew.lastpiece.multi")) {
            Player userPlayer2 = new UserPlayer(getIntent().getExtras().getString("io.github.ardenchew.lastpiece.multi"));
            this.players.add(userPlayer2);
        } else if (getIntent().hasExtra("io.github.ardenchew.lastpiece.single")) {
            Player computerPlayer1 = new ComputerPlayer(getIntent().getExtras().getString("io.github.ardenchew.lastpiece.single"));
            this.players.add(computerPlayer1);
        }

        TextView playerOne = (TextView) findViewById(R.id.playerOne);
        TextView playerTwo = (TextView) findViewById(R.id.playerTwo);
        playerOne.setText(this.players.get(0).getName());
        playerTwo.setText(this.players.get(1).getName());

        getButtons();
        this.completeBtn = (Button) findViewById(R.id.completeBtn);
        this.btnStrList = new String[]{"p0c0", "p1c0", "p2c0", "p3c0", "p4c0", "p5c0", "p6c0", "p0c1", "p1c1", "p2c1", "p3c1", "p4c1", "p0c2", "p1c2", "p2c2", "p0c3"};
        TextView score = (TextView) findViewById(R.id.score);
        this.game = new PacketGameMaster(this.boardSize, players);
        score.setText(this.game.getScore());

        //this.restart = true;

        while (!game.isGameOver()) {
            if (game.isWhosTurn == 0) {
                playerOne.setHighlightColor(Color.parseColor("#219be5"));
                playerTwo.setHighlightColor(Color.parseColor("#ffffff"));
            } else {
                playerOne.setHighlightColor(Color.parseColor("#ffffff"));
                playerTwo.setHighlightColor(Color.parseColor("#219be5"));
            }

            if (game.getCurrentPlayer() instanceof UserPlayer) {
                this.completeBtn.setOnClickListener(this);
                for (int i = 0; i < this.btnList.size(); i++) {
                    this.btnList.get(i).setOnClickListener(this);
                }
            } else if (game.getCurrentPlayer() instanceof ComputerPlayer) {
                //TODO
                //Make sure to update board view
            }
            Board boardView = this.game.getBoard();
            Move moveView = this.game.getMove();
            updateBoard(boardView, moveView);
        }
        score.setText(this.game.getScore());
        String winner = this.game.winner.getName();
    }

    public void updateBoard(Board bv, Move mv) {
        int[] idx;
        for (int i = 0; i < this.btnStrList.length; i++) {
            idx = convertPiece(this.btnStrList[i]);
            if (!bv.has(idx[0], idx[1])) {
                this.btnList.get(i).setOnClickListener(null);
                this.btnList.get(i).setBackgroundColor(Color.parseColor("#ffffff"));
            }
        }
        if ((bv.getPacketNum() != 0) && (mv.itemCount != 0)) {
            this.completeBtn.setBackgroundColor(Color.parseColor("#91df8a"));
        } else {
            this.completeBtn.setBackgroundResource(android.R.drawable.btn_default);
        }

        int cIdx = mv.getColumnNum();
        ArrayList<Integer> pIdx = mv.getPacketIdx();
        int btnIdx;
        for (int i = 0; i < pIdx.size(); i++) {
            btnIdx = getButtonNum(pIdx.get(i), cIdx);
            this.btnList.get(btnIdx).setBackgroundColor(Color.parseColor("#219be5"));
        }
    }

    public int getButtonNum(int pIdx, int cIdx) {
        int btnNum = pIdx;
        if (cIdx == 1) {
            btnNum += 7;
        } else if (cIdx == 2) {
            btnNum += 12;
        } else if (cIdx == 3) {
            btnNum += 15;
        }
        return btnNum;
    }

    public int[] convertPiece(String piece) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[0]);
                break;
            case R.id.button2:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[1]);
                break;
            case R.id.button3:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[2]);
                break;
            case R.id.button4:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[3]);
                break;
            case R.id.button5:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[4]);
                break;
            case R.id.button6:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[5]);
                break;
            case R.id.button7:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[6]);
                break;
            case R.id.button8:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[7]);
                break;
            case R.id.button9:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[8]);
                break;
            case R.id.button10:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[9]);
                break;
            case R.id.button11:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[10]);
                break;
            case R.id.button12:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[11]);
                break;
            case R.id.button13:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[12]);
                break;
            case R.id.button14:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[13]);
                break;
            case R.id.button15:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[14]);
                break;
            case R.id.button16:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.btnStrList[15]);
                break;
            case R.id.completeBtn:
                this.in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, "complete");
                break;
        }
        this.game.handleInput(this.in);
    }

    public void getButtons() {
        this.btnList = new ArrayList<Button>();
        this.btnList.add((Button) findViewById(R.id.button1));
        this.btnList.add((Button) findViewById(R.id.button2));
        this.btnList.add((Button) findViewById(R.id.button3));
        this.btnList.add((Button) findViewById(R.id.button4));
        this.btnList.add((Button) findViewById(R.id.button5));
        this.btnList.add((Button) findViewById(R.id.button6));
        this.btnList.add((Button) findViewById(R.id.button7));
        this.btnList.add((Button) findViewById(R.id.button8));
        this.btnList.add((Button) findViewById(R.id.button9));
        this.btnList.add((Button) findViewById(R.id.button10));
        this.btnList.add((Button) findViewById(R.id.button11));
        this.btnList.add((Button) findViewById(R.id.button12));
        this.btnList.add((Button) findViewById(R.id.button13));
        this.btnList.add((Button) findViewById(R.id.button14));
        this.btnList.add((Button) findViewById(R.id.button15));
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
            this.isWhosTurn = 0;

            winner = this.players.get(1); //default for if user quits ?? should go somewhere else

        }

        public Board getBoard() {
            return this.board;
        }

        public Move getMove() {
            return this.currentMove;
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

            int[] piece = this.convertPiece(inString);
            if (piece != null) {
                int pIdx = piece[0];
                int cIdx = piece[1];
                if (this.board.has(pIdx, cIdx)) {
                    if (this.currentMove.add(pIdx, cIdx)) {
                        return;
                    } else if (this.currentMove.remove(pIdx, cIdx)) {
                        return;
                    }
                } else {
                    this.toastError();
                }
            } else {
                switch (inString) {
                    case "reset":
                        this.players.get(0).resetPoints();
                        this.players.get(1).resetPoints();
                    case "restart":
                        this.restartGame();
                        break;
                    case "quit":
                        Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(startIntent);
                    case "complete":
                        if (this.currentMove.markAsComplete()) {
                            this.completeMove(this.currentMove);
                            break;
                        }
                        this.toastError();
                        break;
                    default:
                        this.toastError();
                        break;
                }
            }
        }

        public void toastError() {
            Toast.makeText(GameActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();
        }

        public void completeMove(Move m) { //for a computer player use as well
            this.handleMove(m);
            this.currentMove.reset();
            this.changeTurn();
            this.checkGameOver();
            if (!this.isGameOver) {
                this.winner = this.getCurrentPlayer();
                this.players.get(this.isWhosTurn).addPoint();
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
                }
            } else {
                toastError();
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
            this.board.reset();
            this.currentMove = new Move();
            this.isGameOver = false;
        }


    }
}
