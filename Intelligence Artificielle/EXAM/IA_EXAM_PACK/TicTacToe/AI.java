public class AI extends Player{
    public AI(String PlayerName){
        super(PlayerName);
        
    }
    public int NextMove(Board gBoard){
        Inteligence intel= new Inteligence(gBoard);
        return intel.Move();
    }

}