import java.util.ArrayList;

public class Graph {
	private GNode root; // this is the root node of the graph or subgraph
	
	public Graph(GNode g){  // the root of the graph defines the graph
		root = g;
	}
	
	public ArrayList<GNode> walkGraph(){ // wrapper for walkGraphFun
		ArrayList<GNode> walk = new ArrayList<GNode>();
		if (root == null) { 
			return walk;
		}
		walkGraphFun(walk, root);
		return walk;
	}
	
	// prevents creation of new walk each time walkGraph is called
	public void walkGraphFun(ArrayList<GNode> walk, GNode g ){
		GNode[] children = g.getChildren();
		walk.add(g);
		if (children.length == 0){ // base case
			return;
		}
		else{
			for (int i = 0; i < children.length; i++){ // recursive case
				walkGraphFun(walk, children[i]);
			}
		}
	}
	
	public ArrayList<ArrayList<GNode>> paths(){ // wrapper for pathsFun
		ArrayList<ArrayList<GNode>> allpaths = new ArrayList<ArrayList<GNode>>();
		if (root == null) {
			return allpaths;
		}
		ArrayList<GNode> currpath = new ArrayList<GNode>();
		pathsFun(allpaths, currpath, root);
		return allpaths;
	}

	public void pathsFun( ArrayList<ArrayList<GNode>> allpaths, ArrayList<GNode> currpath, GNode g){
		// create a local currpath so that it can be modified for different paths
		ArrayList<GNode> local_currpath = (ArrayList<GNode>)currpath.clone(); 
		GNode[] children = g.getChildren();
		local_currpath.add(g); // add current node to path
		if (children.length == 0){ // base case
			allpaths.add(local_currpath);
			return;
		}
		else{
			for (int i = 0; i < children.length; i++){ // recurse through children
				pathsFun(allpaths, local_currpath, children[i]);
			}
		}
	}
	
	public static void main(String[] args) {
		MyNode m = new MyNode("a");
		MyNode n = new MyNode("b");
		MyNode o = new MyNode("c");	
		MyNode p = new MyNode("d");
		
		// null root
		System.out.println("null root walk");
		Graph null_graph = new Graph(null);
		ArrayList<GNode> nullroot_nodes = null_graph.walkGraph();
		System.out.println(nullroot_nodes.size()); // should be 0
		for (int i = 0; i < nullroot_nodes.size(); i++) { // doesn't run
			System.out.println(nullroot_nodes.get(i).getName()); 
		}
		System.out.println("paths with null root");
		ArrayList<ArrayList<GNode>> nullroot_paths = null_graph.paths();
		System.out.println(nullroot_paths.size()); // should be 0
		for (int i = 0; i < nullroot_paths.size(); i++) { // doesn't run
			System.out.println(nullroot_paths.get(i).size()); 
			for (int j = 0; j < nullroot_paths.get(i).size(); j++){
				System.out.println(nullroot_paths.get(i).get(j).getName());
			}
		}
		
		// m is root
		System.out.println("only 'a' as root walk");
		Graph onlym_graph = new Graph(m);
		ArrayList<GNode> onlym_nodes = onlym_graph.walkGraph();
		for (int i = 0; i < onlym_nodes.size(); i++) {
			System.out.println(onlym_nodes.get(i).getName()); //run once just a
		}
		System.out.println("paths with only 'a' as root");
		ArrayList<ArrayList<GNode>> onlym_paths = onlym_graph.paths();
		for (int i = 0; i < onlym_paths.size(); i++) {
			System.out.println(onlym_paths.get(i).size()); // prints 1
			for (int j = 0; j < onlym_paths.get(i).size(); j++){
				System.out.println(onlym_paths.get(i).get(j).getName()); // prints a
			}
		}
		
		// m is root and n is child of m
		m.addChildren(n);
		System.out.println("only 'a' as root and 'b' as a's child walk");
		Graph onlymn_graph = new Graph(m);
		ArrayList<GNode> onlymn_nodes = onlymn_graph.walkGraph();
		for (int i = 0; i < onlymn_nodes.size(); i++) {
			System.out.println(onlymn_nodes.get(i).getName()); //run once just a, b
		}
		
		// m is root and n is left child, o is right child
		m.addChildren(o);
		System.out.println("only 'a' as root and 'b', 'c' as children walk");
		Graph onlymno_graph = new Graph(m);
		ArrayList<GNode> onlymno_nodes = onlymno_graph.walkGraph();
		for (int i = 0; i < onlymno_nodes.size(); i++) {
			System.out.println(onlymno_nodes.get(i).getName()); 
//run once just a, b, c
		}
		
		// m is root and n, o as m's children, p is o's child
		o.addChildren(p);
		System.out.println("only a as root and b, c as children and d as b's child walk");
		Graph onlymnop_graph = new Graph(m);
		ArrayList<GNode> onlymnop_nodes = onlymnop_graph.walkGraph();
		for (int i = 0; i < onlymnop_nodes.size(); i++) {
			System.out.println(onlymnop_nodes.get(i).getName()); 
//run once just a, b, d, c
		}
		System.out.println("all paths of a as root, b, c as a's children, d as b's child");
		ArrayList<ArrayList<GNode>> onlymnop_paths = onlymnop_graph.paths();
		for (int i = 0; i < onlymnop_paths.size(); i++) {
			System.out.println(onlymnop_paths.get(i).size());
			for (int j = 0; j < onlymnop_paths.get(i).size(); j++)
				System.out.println(onlymnop_paths.get(i).get(j).getName()); 
// ((a b) (a c d)
		}
		
		MyNode a = new MyNode("a");
		MyNode b = new MyNode("b");
		MyNode c = new MyNode("c");	
		MyNode d = new MyNode("d");
		MyNode e = new MyNode("e");
		MyNode f = new MyNode("f");
		MyNode g = new MyNode("g");
		MyNode h = new MyNode("h");
		MyNode i = new MyNode("i");
	
		// new graph
		Graph newgraph = new Graph(a);
		a.addChildren(b);
		a.addChildren(c);
		a.addChildren(d);
		b.addChildren(e);
		b.addChildren(f);
		c.addChildren(g);
		d.addChildren(h);
		d.addChildren(i);
		// (a (b (e f) c (g) d (h i)))
		
		System.out.println("(a (b (e f) c (g) d (h i))) walk");
		ArrayList<GNode> newgraph_nodes = newgraph.walkGraph();
		for (int z = 0; z < newgraph_nodes.size(); z++) {
			System.out.println(newgraph_nodes.get(z).getName()); 
//run once just a, b, c, d, e, f, g, h, i
		}
		System.out.println("all paths (a (b (e f) c (g) d (h i)))");
		ArrayList<ArrayList<GNode>> newgraph_paths = newgraph.paths();
		System.out.println(newgraph_paths.size()); // print 5
		for (int x = 0; x < newgraph_paths.size(); x++) {
			System.out.println(newgraph_paths.get(x).size()); // all 3
			for (int y = 0; y < newgraph_paths.get(x).size(); y++)
				System.out.println(newgraph_paths.get(x).get(y).getName()); 
// ((a b e) (a b f) (a c g) (a d h) (a d i))
		}
		
	}
}





