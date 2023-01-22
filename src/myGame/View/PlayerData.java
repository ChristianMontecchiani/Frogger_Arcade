package myGame.View;

import java.util.LinkedList;

public class PlayerData {

    private final LinkedList<Player> playerLst;

    public PlayerData() {
        this.playerLst = new LinkedList<>();
    }

    public void add(Player player) {
        this.playerLst.add(player);
    }

    public LinkedList<Player> getListOfPlayers() {
        return this.playerLst;
    }

    public LinkedList<String[]> asListOfStringArray() {
        LinkedList<String[]> lstSA = null;

        lstSA = new LinkedList<>();
        String[] sArr = null;
        for (Player p : this.playerLst) {
            sArr = new String[2];
            sArr[0] = p.getName();
            sArr[1] = String.valueOf(p.getScore());
            lstSA.add(sArr);
        }

        return lstSA;
    }
    public Player get(int index){
        return this.playerLst.get(index);
    }

    public void set(int index,Player player){
        this.playerLst.set(index,player);
    }
    public void remove(int index){
        this.playerLst.remove(index);
    }
    public int size(){
        return   this.playerLst.size();
    }
}
