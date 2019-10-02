package bowling;

/**
 *
 * @author MAWET-Priscillien
 */
public class MultiPlayerBowling implements MultiPlayerGame {
    
    public String print1 = "Prochain tir : joueur ";
    public String print2 = ", tour n° ";
    public String print3 = ", boule n° ";
    
    public class Player {
        
        public String name;
        public SinglePlayerGame partie;
        
        public Player(String n){
            name = n;
            partie = new SinglePlayerGame();
        }
    }
    
    public Player[] players; // tab des joueurs
    public int currentPlayer = -1; // index du joueur en cours

    @Override
    public String startNewGame(String[] playerName) throws Exception {
        if (playerName.length==0){
            throw new java.lang.Exception("tableau vide");
        }
        players = new Player[playerName.length];
        for (int i=0; i<players.length;i++){
            players[i] = new Player(playerName[i]);
        }
        currentPlayer = 0;
        return print1+players[0].name+print2+"1"+print3+"1";
    }

    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        if (currentPlayer==-1){
            throw new java.lang.Exception("la partie n'est pas démarrée");
        }
        players[currentPlayer].partie.lancer(nombreDeQuillesAbattues);
        if (players[currentPlayer].partie.isFinished() || players[currentPlayer].partie.hasCompletedFrame()){
            currentPlayer = (currentPlayer+1)%players.length;
            if (players[currentPlayer].partie.isFinished()){
            return "Partie terminée";
            }
        }
        return print1+players[currentPlayer].name+print2+players[currentPlayer].partie.getFrameNumber()+print3+players[currentPlayer].partie.getNextBallNumber();
    }

    @Override
    public int scoreFor(String playerName) throws Exception {
        int nb = -1;
        for (int i=0; i<players.length;i++){
            if (players[i].name.equals(playerName)){
                nb = i;
            }
        }
        if (nb==-1){
            throw new java.lang.Exception(playerName+" ne joue pas dans cette partie");
        }
        return players[nb].partie.score();
    }
    
}

