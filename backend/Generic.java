package backend;

public class Generic extends BoardTile{
    private final double collectSum;
    private final String name;
    public Generic(int position, int collectSum, String name, int x, int y){
        super(position, x, y);
        this.collectSum = collectSum;
        this.name = name;
    }

    public void updateSum(Player player){
        player.editCash(collectSum);
    }

    public String getName(){
        return name;
    }
}
