import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        

        Scanner in = new Scanner(System.in);
        System.out.print("Enter Player 1 Name:");

        String playerOneName = in.nextLine();
        System.out.println("1) One PLayer Game");
        System.out.println("2) Two PLayer Game");
        System.out.print("Select 1 or 2:");



        if (in.nextLine().equals("2")) {
            System.out.print("Enter Player 2 Name:");

            game = new Game(playerOneName, in.nextLine());
        } else {
            game = new Game(playerOneName);
        }
        do {
            System.out.print("Number of games you want to play:");
            game.outOff = in.nextInt();

            do {
                game.NewBoard();
                int gCon = 0;
                int pNo = 0;
                do {
                    if (pNo == 1) {
                        pNo = 2;
                    } else {
                        pNo = 1;
                    }
                    game.gBoard.Print();
                    System.out.println();
                    System.out.print(game.PlayerName(pNo) + "'s Move:");
                    if (game.twoPlayer == false && pNo == 2) {
                        AI a = (AI) game.player2;
                        int aiMove = a.NextMove(game.gBoard);
                        System.out.print(aiMove);
                        gCon = game.Move(aiMove);
                    } else {
                        gCon = game.Move(in.nextInt());

                    }

                    System.out.println();
                    if (gCon == 3) {
                        System.out.println(":::Wrong Move Try Again:::");
                        if (pNo == 1) {
                            pNo = 2;
                        } else {
                            pNo = 1;
                        }

                    } else if (gCon != 0) {
                        game.gBoard.Print();
                        System.out.println();
                        if (gCon == 1 || gCon == 2) {
                            System.out.println(game.PlayerName(pNo) + " Wins");
                            game.Win(pNo);
                        }
                        if (gCon == -1) {
                            System.out.println(" Draw ");
                        }
                        break;
                    }



                } while (true);
            } while (game.endGame());
            System.out.println(game.PlayerName(1) + " Wins: " + game.Score(1));
            System.out.println(game.PlayerName(2) + " Wins: " + game.Score(2));
            System.out.println("Out off : " + game.outOff + " games");
            game.Reset();
            System.out.println();
            System.out.println("Want lose more?");
            System.out.print("1 to continue or any other key to end:");
            in.nextLine();
        } while (in.nextLine().equals("1"));

    }
}