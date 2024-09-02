public class Leaf {

    //This class stores all the data we need for leaf components, which is just bound coordinates.
    public String bounds;
    public Leaf(String bounds) {
        this.bounds = bounds;
    }

    //Method to put the numbers from the bounds attribute into an array, to make handling them easier later.
    public int[] coordinates() {
        String[] values = bounds.split("(]\\[|\\[|]|,)", 6);//Since the bounds are formatted as [1,2][3,4], the values array will have empty cells in postition 0 and 5, so the limit needs to be 6.
        int[] coors = new int[4];
        coors[0] = Integer.parseInt(values[1]);
        coors[1] = Integer.parseInt(values[2]);
        coors[2] = Integer.parseInt(values[3]);
        coors[3] = Integer.parseInt(values[4]);
        return coors;
    }
}