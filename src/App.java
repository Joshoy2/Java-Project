import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("\n\n");

        System.out.print("Options:\n1: Tic-Tac-Toe\n2: Graph\n\nEnter a number: ");
        String user_option = System.console().readLine();

        int option = Integer.parseInt(user_option);
        if(option == 1){
            System.out.println("\nTic-Tac-Toe:");
            tic_tac_toe();
        } else if(option == 2){
            System.out.println("\nGraph:\n");
            graph();
        } else {
            System.out.println("\nTry again");
        }

        
    }

    public static void tic_tac_toe() throws Exception {
        //playing instructions
        String instruction_field = " 1 | 2 | 3 \n-----------\n 4 | 5 | 6 \n-----------\n 7 | 8 | 9 ";
        System.out.println("\nThe playing field: \n"+instruction_field);

        //variables
        String[] player_options = {" "," "," "," "," "," "," "," "," "};
        String active_playing_field = " "+player_options[0]+" | "+player_options[1]+" | "+player_options[2]+" \n-----------\n "+player_options[3]+" | "+player_options[4]+" | "+player_options[5]+" \n-----------\n "+player_options[6]+" | "+player_options[7]+" | "+player_options[8]+" ";
        String used_nums = "";

        //win vars
        String[] possible_wins = {"123", "456", "789", "147", "258", "369", "159", "357"};
        String player_nums = "";
        String bot_nums = "";
        int maybe_win= 0;

        for(int i= 0; i< 5; i++){

            //asks user for input
            System.out.print("\nWhere do you want to place your X? ");
            String user_input = System.console().readLine();    

            //user option validity check
            if(used_nums.contains(user_input)){
                while(used_nums.contains(user_input)){
                    System.out.print("That spot is already taken. Try again: ");
                    user_input = System.console().readLine();
                }
            }

            //adds user input to field and "used_nums"
            used_nums += user_input;
            player_nums += user_input;
            int input = Integer.parseInt(user_input);
            player_options[input-1] = "X";
            
            //prints out new field
            active_playing_field = " "+player_options[0]+" | "+player_options[1]+" | "+player_options[2]+" \n-----------\n "+player_options[3]+" | "+player_options[4]+" | "+player_options[5]+" \n-----------\n "+player_options[6]+" | "+player_options[7]+" | "+player_options[8]+" \n";
            System.out.println("\n"+active_playing_field);
        
            for(String possibility : possible_wins) {
                maybe_win= 0;
                for (char ch: possibility.toCharArray()) {
                    if(player_nums.contains(String.valueOf(ch))){
                        maybe_win++;
                    }
                }
                if(maybe_win==3){
                    System.out.println("You won :D\n\n");
                    break;
                }
            }
            if(maybe_win==3){
                break;
            }

            //automated opponent chooses answer
            Random rand = new Random();
            int random_answer = rand.nextInt(9);
            String random_answer_str = Integer.toString(random_answer+1);

            //automated opponent answer validation
            while(used_nums.contains(random_answer_str)){
                Random randi = new Random();
                random_answer = randi.nextInt(9);
                random_answer_str = Integer.toString(random_answer+1);
            }
            
            //automated opponent thinks :D
            Thread.sleep(1000);
            
            //adds automated opponent input to field and "used_nums"
            used_nums += random_answer_str;
            bot_nums += random_answer_str;
            player_options[random_answer] = "O";

            //prints out new field again
            active_playing_field = " "+player_options[0]+" | "+player_options[1]+" | "+player_options[2]+" \n-----------\n "+player_options[3]+" | "+player_options[4]+" | "+player_options[5]+" \n-----------\n "+player_options[6]+" | "+player_options[7]+" | "+player_options[8]+" \n";
            System.out.println("\033[A\033[A\033[A\033[A\033[A\033[A"+active_playing_field);

            for(String possibility : possible_wins) {
                maybe_win= 0;
                for (char ch: possibility.toCharArray()) {
                    if(bot_nums.contains(String.valueOf(ch))){
                        maybe_win++;
                    }
                }
                if(maybe_win==3){
                    System.out.println("You lost :C\n\n");
                    break;
                }
            }
            if(maybe_win==3){
                break;
            }
        }
    }

    public static void graph() throws Exception {
        int graph_size = 25;
        System.out.print("Size of Graph: ");
        graph_size = Integer.parseInt(System.console().readLine());
        String graph[][] = {{"#1","#2","#3","#4"}, {"+1","+2","+3","+4"}, {"-1","-2","-3","-4"}}; 

        System.out.println(graph[0][1]);

        for(int i= 0; i<2; i++){
            graph[0][i] = "%"+Integer.toString(i);
        }

        System.out.println(graph[0][1]);

        //for(int y_axis= 0; y_axis< graph_size; y_axis++){
        //    for(int x_axis= 0; x_axis< graph_size; x_axis++){
        //        graph[0][x_axis] = "#1";
        //    }
        //}

        //for (String[] strings : graph) {
        //    System.out.println(strings);
        //}
    }
}