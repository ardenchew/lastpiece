package io.github.ardenchew.lastpiece;

import java.util.Scanner;

public class UserPlayer extends Player {

    public String name;

    public UserPlayer(String n) {
        this.name = n;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Input getInput() {
        Scanner sc = new Scanner(System.in);
        String moveStr = sc.nextLine();
        Input ip = new Input(Input.INPUTTYPE.USERINPUT_GAMECOMMAND, moveStr);

        return ip;
    }

}