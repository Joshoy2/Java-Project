import java.util.Random;

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
        String active_playing_field = " "+player_options[0]+" | "+player_options[1]+" | "+player_options[2]+" \n-----------\n "+player_options[3]+" | "+player_options[4]+" | "+player_options[5]+" \n-----------\n "+player_options[6]+" | "+player_options[7]+" | "+player_options[8]+" ";
        String used_nums = "";

        for(int i= 0; i< 5; i++){
            //asks user for input
            System.out.print("\nWhere do you want to place your X? ");
            String user_input = System.console().readLine();    

            //user option validity check
            if(used_nums.contains(user_input)){
                while(true){
                    System.out.print("That spot is already taken. Try again: ");
                    user_input = System.console().readLine();
                    if(!used_nums.contains(user_input)){
                        break;
                    }
                }
            }

            //adds user input to field and "used_nums"
            used_nums += user_input;
            int input = Integer.parseInt(user_input);
            player_options[input-1] = "X";
            
            //prints out new field
            active_playing_field = " "+player_options[0]+" | "+player_options[1]+" | "+player_options[2]+" \n-----------\n "+player_options[3]+" | "+player_options[4]+" | "+player_options[5]+" \n-----------\n "+player_options[6]+" | "+player_options[7]+" | "+player_options[8]+" \n";
            System.out.println(active_playing_field);
        
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
            Thread.sleep(1500);
            
            //adds automated opponent input to field and "used_nums"
            used_nums += random_answer_str;
            player_options[random_answer] = "O";

            //prints out new field again
            active_playing_field = " "+player_options[0]+" | "+player_options[1]+" | "+player_options[2]+" \n-----------\n "+player_options[3]+" | "+player_options[4]+" | "+player_options[5]+" \n-----------\n "+player_options[6]+" | "+player_options[7]+" | "+player_options[8]+" \n";
            System.out.println(active_playing_field);
        }
    }

    public static void graph() throws Exception {
        String[] Graph_fields;

        System.out.print("Enter something:");
        String input = System.console().readLine();

        int teest = Integer.parseInt(input);
        System.out.println(teest);
    }
}

