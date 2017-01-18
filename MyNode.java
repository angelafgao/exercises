import java.util.ArrayList;

public class MyNode implements GNode {
	private String name;
	private ArrayList<GNode> children = new ArrayList<GNode>(); // cannot be null children
	
	public MyNode(String x){
		name = x;
	}
	
	public String getName(){
		return name;
	}
	
	public GNode[] getChildren(){
		GNode[] retval = new GNode[children.size()];
		children.toArray(retval);
		return retval;
	}
	
	public void addChildren(GNode g) {
		children.add(g);
	}
	
	public static ArrayList<GNode> walkGraph(GNode node){
		Graph nodegraph = new Graph(node);
		return nodegraph.walkGraph();
	}
	
	public static ArrayList<ArrayList<GNode>> paths(GNode node) {
		Graph nodegraph = new Graph(node);
		return nodegraph.paths();
	}
}


