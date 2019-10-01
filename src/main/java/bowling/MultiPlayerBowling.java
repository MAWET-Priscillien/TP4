/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

/**
 *
 * @author pedago
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
    
    public Player[] players;
    public int currentPlayer;

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
        players[currentPlayer].partie.lancer(nombreDeQuillesAbattues);
        if (players[currentPlayer].partie.hasCompletedFrame()){
            currentPlayer = (currentPlayer+1)%players.length;
        }
        return print1+players[currentPlayer].name+print2+players[currentPlayer].partie.getFrameNumber()+print3+players[currentPlayer].partie.getNextBallNumber();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int scoreFor(String playerName) throws Exception {
        int nb = 0;
        for (int i=0; i<players.length;i++){
            if (players[i].name.equals(playerName)){
                nb = i;
            }
        }
        return players[nb].partie.score();
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

