package io.github.ardenchew.lastpiece;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Game2Activity extends AppCompatActivity implements View.OnClickListener {

    public PacketGameMaster game;

    public int[] boardSize;
    public ArrayList<Player> playerList = new ArrayList<Player>();
    public String[] buttonStrList;
    public ArrayList<Button> buttonList = new ArrayList<Button>();

    public TextView playerOne;
    public TextView playerTwo;
    public TextView score;

    public Button completeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.setBoardSize();
        this.setButtonStrList();
        this.setButtonList();
        this.setPlayerList();
        this.completeButton = (Button) findViewById(R.id.completeBtn);
        this.score = (TextView) findViewById(R.id.score);

        this.game = new PacketGameMaster(this.boardSize, this.playerList);

        this.updateBoardView();

    }



    public void setBoardSize() {
        this.boardSize = new int[]{7, 5, 3, 1};
    }

    public void setButtonStrList() {
        this.buttonStrList = new String[]{"p0c0", "p1c0", "p2c0", "p3c0", "p4c0", "p5c0", "p6c0", "p0c1", "p1c1", "p2c1", "p3c1", "p4c1", "p0c2", "p1c2", "p2c2", "p0c3"};
    }

    public void setButtonList() {
        this.buttonList.add((Button) findViewById(R.id.button1));
        this.buttonList.add((Button) findViewById(R.id.button2));
        this.buttonList.add((Button) findViewById(R.id.button3));
        this.buttonList.add((Button) findViewById(R.id.button4));
        this.buttonList.add((Button) findViewById(R.id.button5));
        this.buttonList.add((Button) findViewById(R.id.button6));
        this.buttonList.add((Button) findViewById(R.id.button7));
        this.buttonList.add((Button) findViewById(R.id.button8));
        this.buttonList.add((Button) findViewById(R.id.button9));
        this.buttonList.add((Button) findViewById(R.id.button10));
        this.buttonList.add((Button) findViewById(R.id.button11));
        this.buttonList.add((Button) findViewById(R.id.button12));
        this.buttonList.add((Button) findViewById(R.id.button13));
        this.buttonList.add((Button) findViewById(R.id.button14));
        this.buttonList.add((Button) findViewById(R.id.button15));
    }

    public void setPlayerList() {
        Player userPlayer1 = new UserPlayer("Player 1");
        this.playerList.add(userPlayer1);
        if (getIntent().hasExtra("io.github.ardenchew.lastpiece.multi")) {
            Player userPlayer2 = new UserPlayer(getIntent().getExtras().getString("io.github.ardenchew.lastpiece.multi"));
            this.playerList.add(userPlayer2);
        } else if (getIntent().hasExtra("io.github.ardenchew.lastpiece.single")) {
            Player computerPlayer1 = new ComputerPlayer(getIntent().getExtras().getString("io.github.ardenchew.lastpiece.single"));
            this.playerList.add(computerPlayer1);
        }

        this.playerOne = (TextView) findViewById(R.id.playerOne);
        this.playerTwo = (TextView) findViewById(R.id.playerTwo);
        playerOne.setText(this.playerList.get(0).getName());
        playerTwo.setText(this.playerList.get(1).getName());

    }

    @Override
    public void onClick(View v) {
        Input in;
        switch (v.getId()) {
            case R.id.button1:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[0]);
                break;
            case R.id.button2:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[1]);
                break;
            case R.id.button3:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[2]);
                break;
            case R.id.button4:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[3]);
                break;
            case R.id.button5:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[4]);
                break;
            case R.id.button6:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[5]);
                break;
            case R.id.button7:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[6]);
                break;
            case R.id.button8:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[7]);
                break;
            case R.id.button9:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[8]);
                break;
            case R.id.button10:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[9]);
                break;
            case R.id.button11:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[10]);
                break;
            case R.id.button12:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[11]);
                break;
            case R.id.button13:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[12]);
                break;
            case R.id.button14:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[13]);
                break;
            case R.id.button15:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[14]);
                break;
            case R.id.button16:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, this.buttonStrList[15]);
                break;
            case R.id.completeBtn:
                in = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, "complete");
                break;
            default:
                in = new Input(Input.INPUTTYPE.USERINPUT_APPCOMMAND);
                break;
        }
        this.game.handleInput(in);
        this.updateBoardView();
        if (this.game.getCurrentPlayer() instanceof ComputerPlayer) {
            //TODO create computer player move
        }
        if (this.game.isGameOver()) {
            //TODO
            this.game.restart();
        }
    }

    private int boardIdxHelperTo(int pIdx, int cIdx) {
        int r = pIdx;
        switch (cIdx) {
            case 0:
                return r;
            case 1:
                return (r + 7);
            case 2:
                return (r + 12);
            case 3:
                return 15;
            default:
                return -1;
        }
    }

    private int[] boardIdxHelperFrom(int idx) {
        int[] r = new int[2];
        if (idx < 7) {
            r[0] = idx;
            r[1] = 0;
        } else if (idx < 12) {
            r[0] = idx - 7;
            r[1] = 1;
        } else if (idx < 15) {
            r[0] = idx - 12;
            r[1] = 2;
        } else if (idx == 15) {
            r[0] = 0;
            r[1] = 3;
        } else {
            return null;
        }
        return r;
    }

    public void updateBoardView() {
        Move m = this.game.getMove();
        Board b = this.game.getBoard();
        int cIdx = m.getColumnNum();
        ArrayList<Integer> pIdxLst = m.getPacketIdx();

        if (this.game.getCurrentPlayerInt() == 0) {
            this.playerOne.setTextColor(Color.parseColor("#219be5"));
            this.playerTwo.setTextColor(Color.parseColor("#000000"));
        } else {
            this.playerOne.setTextColor(Color.parseColor("#000000"));
            this.playerTwo.setTextColor(Color.parseColor("#219be5"));
        }

        this.score.setText(this.game.getScore());

        for (int i = 0; i < this.buttonList.size(); i++) {
            int[] r = this.boardIdxHelperFrom(i);
            if (!b.has(r[0], r[1])) {
                this.buttonList.get(i).setOnClickListener(null);
                this.buttonList.get(i).setBackgroundColor(Color.parseColor("#ffffff"));
            } else if ((r[1] == cIdx) && (pIdxLst.indexOf(r[0]) != -1)) {
                this.buttonList.get(i).setBackgroundColor(Color.parseColor("#219be5"));
                this.buttonList.get(i).setOnClickListener(this);
            } else {
                this.buttonList.get(i).setBackgroundResource(android.R.drawable.btn_default);
                this.buttonList.get(i).setOnClickListener(this);
            }
        }

        if (pIdxLst.size() == 0) {
            this.completeButton.setBackgroundResource(android.R.drawable.btn_default);
        } else {
            this.completeButton.setBackgroundColor(Color.parseColor("#a0d2a4"));
        }
    }


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
        }

        public String getScore() {
            return (this.players.get(0).getPoints() + "-" + this.players.get(1).getPoints());
        }

        public Board getBoard() {
            return this.board;
        }

        public Move getMove() {
            return this.currentMove;
        }

        public Player getCurrentPlayer() {
            return this.players.get(this.isWhosTurn);
        }

        public int getCurrentPlayerInt() {
            return this.isWhosTurn;
        }

        public boolean isGameOver() {
            return this.isGameOver;
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
            String inStr = in.data;


            int[] piece = this.convertPiece(inStr);
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
                    return;
                }
            }
            switch (inStr) {
                case "complete":
                    if (this.currentMove.markAsComplete()) {
                        this.completeMove(this.currentMove);
                        break;
                    } else {
                        this.toastError();
                        break;
                    }
                case "restart":
                    // TODO;
                    this.restart();
                    break;
                default:
                    this.toastError();
                    break;
            }
        }

        public void toastError() {
            Toast.makeText(Game2Activity.this, "Invalid Input", Toast.LENGTH_LONG).show();
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

        public void completeMove(Move m) { //for a computer player use as well
            this.handleMove(m);
            this.currentMove.reset();
            this.changeTurn();
            this.checkGameOver();
        }

        public void handleMove(Move m) {
            //TODO
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
            this.winner = this.getCurrentPlayer();
            this.players.get(this.isWhosTurn).addPoint();
            this.isGameOver = true;
        }

        public void restart() {
            this.board.reset();
            this.isGameOver = false;
            this.currentMove = new Move();
        }

    }
}
