package com.subratacharya;

import java.util.Scanner;

public class Main {
    public static boolean smallTheatre;
    public static int cheapTicketsSold = 0;
    public static int expensiveTicketsSold = 0;
    public static float percentageSold = 0;
    public static int currentIncome = 0;
    public static int totalIncome = 0;

    public static void updateIncomePercentage(String seatmap[][]){
        float totalSeats = (seatmap.length-1) * (seatmap[0].length-1);
        float totalTickets = cheapTicketsSold + expensiveTicketsSold;
        percentageSold = totalTickets / totalSeats * 100;
        currentIncome = cheapTicketsSold * 8 + expensiveTicketsSold * 10;
    }

    public static void menu(String seatmap[][]){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Show the seats \n2. Buy a Ticket \n3. Show Statistics \n0. Exit");
        int selection = scanner.nextInt();
        switch (selection) {
            case 1:
                showSeats(seatmap);
                break;
            case 2:
                buySeat(seatmap);
                break;
            case 3:
                showStatistics(seatmap);
            case 0:
                exit();
        }

    }
    public static void setSeats(int numrows, int numseats) {
        String[][] seatmap = new String[numrows+1][numseats+1];
        for (int i =0; i<=numrows; i++){
            for (int j=0; j<=numseats; j++){
                seatmap[i][j] = "S";
            }
        }

        seatmap[0][0] = "";
        for(int i = 1; i<=numseats; i++) {
            seatmap[0][i] = Integer.toString(i);
        }
        for(int i = 1; i<=numrows; i++ ){
            seatmap[i][0] = Integer.toString(i);
        }
        smallTheatre = (seatmap.length - 1) * (seatmap[0].length - 1) <= 60;
        if(smallTheatre){
            totalIncome = (seatmap.length-1) * (seatmap[0].length-1) * 10;
        } else {
            totalIncome = (((seatmap.length-1) - (seatmap.length-1)/2) * 8 + (seatmap.length-1)/2 * 10) *  (seatmap[0].length-1);
        }

        showSeats(seatmap);
    }

    public static void showSeats(String[][] seatmap){
        System.out.println("Cinema:");
        System.out.print("\s");
        int numrows = seatmap.length -1;
        int numseats = seatmap[0].length - 1;
        for (int i = 0; i<=numrows; i++){
            for (int j=0; j<=numseats; j++){
                System.out.print(seatmap[i][j] + " ");
            }
            System.out.println(" ");
        }
        menu(seatmap);
    }
    public static void buySeat(String[][] seatmap){

        Scanner scanner = new Scanner(System.in);
        boolean validchoice = false;

        while(!validchoice) {
            System.out.println("Enter a row number:");
            int rownum = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatnum = scanner.nextInt();
            if(rownum >= seatmap.length | seatnum >= seatmap[0].length)
            {
                System.out.println("Wrong Input");
            } else {
                if (seatmap[rownum][seatnum].equals("S")){
                    seatmap[rownum][seatnum] = "B";
                    if (smallTheatre){
                        System.out.println("Ticket Price: $10");
                        expensiveTicketsSold++;
                    } else {
                        if (rownum <= (seatmap.length-1)/2) {
                            System.out.println("Ticket Price: $10");
                            expensiveTicketsSold++;
                        } else {
                            System.out.println("Ticket Price: $8");
                            cheapTicketsSold++;
                        }
                    }
                    validchoice = true;
                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            }
        }
        updateIncomePercentage(seatmap);
        menu(seatmap);
    }
    public static void showStatistics(String[][] seatmap){
        System.out.printf("Number of tickets purchased: %d%n", cheapTicketsSold+expensiveTicketsSold);
        System.out.printf("Percentage: %.2f%% %n", percentageSold);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
        menu(seatmap);
    }
    public static void exit(){


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numrows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numseats = scanner.nextInt();


        setSeats(numrows, numseats);

    }
}
