package derelict;
import java.util.*;

public class Path {
	
	LinkedList<Room> RoomContainer;
	Integer Length, orX, orY, dX, dY, mW, mH, Iterations;
	PathNode[][] NodeArr;
	PathNode originNode, destNode;
	
	public Path(Room origin, Room dest, Gameboard B) {
		Length = 0;
		Iterations = 0;
		mW = B.getW();
		mH = B.getH();
		NodeArr = new PathNode[mW][mH];
		RoomContainer = new LinkedList<Room>();

		for (int x = 0; x < mW; x++) {
			for (int y = 0; y < mH; y++) {
				NodeArr[x][y] = new PathNode(x,y);
				if ((B.getRoom(x, y).getID() == 2)){
					NodeArr[x][y].setBlocked(true); //If this is open space, consider it unpathable
				}
				
			}
		}
		
		originNode = NodeArr[origin.getX()][origin.getY()];
		destNode = NodeArr[dest.getX()][dest.getY()];
	
		for (int x = 0; x < mW; x++) {
			for (int y = 0; y < mH; y++) {
				
				if (y>0) {
					NodeArr[x][y].addAdjacent(NodeArr[x][y-1]);
				}

				if (y<mH-1) {
					NodeArr[x][y].addAdjacent(NodeArr[x][y+1]);
				}

				if (x>0) {
					NodeArr[x][y].addAdjacent(NodeArr[x-1][y]);
				}
				if (x<mW-1) {
					NodeArr[x][y].addAdjacent(NodeArr[x+1][y]);
				}
				
			}
		}
	
		Solver(NodeArr);
		for (int x = 0; x < mW; x++) {
			for (int y = 0; y < mH; y++) {
				if(NodeArr[x][y].isChecked()) {Iterations+=1;}
			}
		}

		if (destNode.getParent() != null) {
			PathNode tracer = destNode;
			while (tracer !=null) {
				RoomContainer.add(B.getRoom(tracer.getX(), tracer.getY()));
				tracer = tracer.getParent();
				if (tracer !=originNode) {Length+=1;} else {
				}
			}
		} else {
			Length = -1;
		}
	}
	
	public boolean Solver(PathNode[][] Nodes) {
		for (int x = 0; x < mW; x++) {
			for (int y = 0; y < mH; y++) {
				Nodes[x][y].setChecked(false);
				Nodes[x][y].globalgoal = Double.POSITIVE_INFINITY;
				Nodes[x][y].localgoal = Double.POSITIVE_INFINITY;
				Nodes[x][y].nullParent();
			}
		}
		PathNode currNode = originNode;
		currNode.localgoal = 0.0;
		currNode.globalgoal = distance(originNode,destNode);
		LinkedList<PathNode> untested = new LinkedList<PathNode>();
		untested.add(currNode);
		while(!untested.isEmpty()&&(currNode!=destNode)) {
			//System.out.print("Testing Node");
			untested.sort(new NodeCompare());
			while((!untested.isEmpty())&&(untested.getFirst().isChecked())){
				untested.removeFirst();
			}
			if(untested.isEmpty()) {
				break;
			}
			currNode = untested.getFirst();
			currNode.setChecked(true);
			for (final PathNode adjNode: currNode.getAdjacentRooms()) {
				if ((!adjNode.isChecked())&&(!adjNode.isBlocked())) {
					untested.addLast(adjNode);
				}
				double pgoal = (currNode.localgoal + distance(currNode,adjNode));
				if (pgoal < adjNode.localgoal) {
					adjNode.setParent(currNode);
					adjNode.localgoal = pgoal;
					adjNode.globalgoal = (adjNode.localgoal + distance(adjNode, destNode));

				}
				
			}
		}
		return true;
	}
	
	public double distance(PathNode a, PathNode b) {
		return Math.sqrt((a.getX()-b.getX())*(a.getX()-b.getX()) + (a.getY()-b.getY())*(a.getY()-b.getY()));
	}
	
	public class NodeCompare implements Comparator<PathNode>{
		@Override
		public int compare(PathNode a, PathNode b) {
			return Double.compare(a.globalgoal,b.globalgoal);
		}
	}
}