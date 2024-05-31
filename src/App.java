import java.util.Random;
//import javax.script.ScriptEngineManager;
//import javax.script.ScriptEngine;
//import javax.script.ScriptException;


public class App {

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;
            
            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }
            
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }
            
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }
            
            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName `(` expression `)` | functionName factor
            //        | factor `^` factor
            
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }
            
            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }
            
            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus
                
                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')' after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }
                
                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
                
                return x;
            }
        }.parse();
    }


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
        graph_size = Integer.parseInt(System.console().readLine()) + 1;
        String graph[][] = new String[graph_size][graph_size];
        for(int y= 0; y<graph_size; y++){
            for(int x= 0; x<graph_size; x++){
                if(x==0 && y==0){
                    graph[x][y] = "0+";
                } else if(x==0) {
                    graph[x][y] = "--";
                } else if(y==0) {
                    graph[x][y] = " |";
                } else {
                    graph[x][y] = "  ";
                }
            }
        }

        for(int x= graph_size-1; x>=0; x--){
            for(int y= 0; y<graph_size; y++){
                System.out.print(graph[x][y]);
            }
            System.out.println();
        }


        System.out.print("\nYour equation: Y= ");
        String user_equation = System.console().readLine();

        for(int x= 0; x<graph_size; x++){
            String user_equation2 = user_equation.replace("x", Integer.toString(x));
            Double res= eval(user_equation2);
            int result = res.intValue();
            if(result <graph_size){
                graph[result][x] = "[]";
            }
        }

        //System.out.print("\033[A\033[A\033[A\033[A");
        for(int i= 0; i<graph_size; i++){
            System.out.print("\033[A");
        }
            
        for(int x= graph_size-1; x>=0; x--){
            for(int y= 0; y<graph_size; y++){
                System.out.print(graph[x][y]);
            }
            System.out.println();
        }
    }
}