package testchess;

/**
 *
 * @author tondeur-h
 */
public class TestChess {

    
    public TestChess()  {
        
        //test with stockfish 6 chess engine
        //UCIChess uci=new UCIChess("C:/perso/javafx/TP/stockfish-6-win/Windows/stockfish-6-32.exe");
        //test with protector chess engine
        UCIChess uci=new UCIChess("C:\\Users\\tondeur-h.CHV\\Downloads\\Protector_1_6_0\\bin\\Protector_Win32.exe");
    
            //ask uci infos
           uci.send_cmd(UCIChess.UCI);
           //is uci ok ?
            System.out.println("uciok = "+uci.get_uciOk(false));
            //engine name and author(s)
            System.out.println("Engine Name = "+uci.getEngineName());
            System.out.println("Engine Author(s) = "+uci.getEngineAuthor());
            //number of options in uci engine
            System.out.println("Numbers of options = "+uci.get_number_options());
            //list all uci options (names, type, values)
            System.out.format("%-30s %-10s %-20s\n","Name(id)","type","values");
            System.out.println("------------------------------------------------------------");
            for (int i=0;i<uci.get_number_options();i++)
            {
                System.out.format("%-30s %-10s %-20s\n",uci.get_option(i).getId(),uci.get_option(i).getType(),uci.get_option(i).getValues() );
            }
            System.out.println("------------------------------------------------------------");
            //is engine ready?
            uci.send_cmd(UCIChess.ISREADY);
            System.out.println("isready = "+uci.get_readyOk(false));
            
            //white play e2e4
            System.out.println("White play = e2e4");
            uci.move_FromSTART("e2e4 "); 
            System.out.println("-------------------------------------------------------");
            //is engine ready for next move?
            uci.send_cmd(UCIChess.ISREADY);
            System.out.println("isready = "+uci.get_readyOk(false));
            
            //black move (engine play)
            uci.send_cmd(UCIChess.GOTHINK); //think for best move
            String rep=uci.get_bestMove(false);  //read response
            System.out.println("Black play = "+rep); //draw best move
            System.out.println("Black ponder = "+uci.getPonder()); //best white next move
            uci.move_FromSTART("e2e4 "+rep); //make move
            System.out.println("-------------------------------------------------------");
            
            //is engine ready for next move?
            uci.send_cmd(UCIChess.ISREADY);
            System.out.println("isready = "+uci.get_readyOk(false));
            
            //white play g1f3
            System.out.println("White play = g1f3");
            uci.move_FromSTART("e2e4 "+rep+" g1f3 ");
            System.out.println("-------------------------------------------------------");
            //is engine ready for next move?
            uci.send_cmd(UCIChess.ISREADY);
            System.out.println("isready = "+uci.get_readyOk(false));
            
            //black play
            uci.send_cmd(UCIChess.GOTHINK); //search next move
            String rep2=uci.get_bestMove(false);  //read best move
            System.out.println("Black play = "+rep2); //draw black turn
            System.out.println("Black ponder = "+uci.getPonder()); //best white next move
            uci.move_FromSTART("e2e4 "+rep+" g1f3 "+rep2); //make move
            System.out.println("-------------------------------------------------------");
            //bye bye...
            System.out.println("Bye Bye!");
            uci.stop_Engine();
}

    
    public static void main(String[] args) {
        TestChess testChess = new TestChess();
    }
    
}