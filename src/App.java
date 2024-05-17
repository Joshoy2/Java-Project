public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("\n\n");

        System.out.print("Options:\n1: Tic-Tac-Toe\n2: Graph\n  Enter a number: ");
        String user_option = System.console().readLine();

        int option = Integer.parseInt(user_option);
        if(option == 1){
            System.out.println("\nTic-Tac-Toe:\n");
            tic_tac_toe();
        } else if(option == 2){
            System.out.println("\nGraph\n\n");
            graph();
        } else {
            System.out.println("\nTry again");
        }

        
    }

    public static void tic_tac_toe() throws Exception {
        
        String instruction_field = " 1 | 2 | 3 \n-----------\n 4 | 5 | 6 \n-----------\n 7 | 8 | 9 ";
        System.out.println("The field: \n"+instruction_field);

        String[] player_options = {" "," "," "," "," "," "," "," "," "};
        String playing_field = " "+player_options[0]+" | "+player_options[1]+" | "+player_options[2]+" \n-----------\n "+player_options[3]+" | "+player_options[4]+" | "+player_options[5]+" \n-----------\n "+player_options[6]+" | "+player_options[7]+" | "+player_options[8]+" ";

        System.out.print("\n\nWhere do you want to place your X? ");
        String user_input = System.console().readLine();
        int input = Integer.parseInt(user_input);

        player_options[input] = "X";

        System.out.println(playing_field);    


        //System.out.println("\n\n\n");

        //System.out.print("Enter something:");
        //String input = System.console().readLine();

        //int teest = Integer.parseInt(input);
        //System.out.println(teest);
    }

    public static void graph() throws Exception {
        String[] Graph_fields;

        System.out.print("Enter something:");
        String input = System.console().readLine();

        int teest = Integer.parseInt(input);
        System.out.println(teest);
    }
}

