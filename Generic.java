
public class Generic extends BoardTile{
	private final double collectSum;
    private final String name;
    
    public Generic(int position, int collectSum, String name){
        super(position);
        this.collectSum = collectSum;
        this.name = name;
    }

    public void updateSum(Player player){
        player.editCash(collectSum);
        System.out.println("$"+collectSum+" added to your account!");
    }

    public String getName(){
        return name;
    }

}
