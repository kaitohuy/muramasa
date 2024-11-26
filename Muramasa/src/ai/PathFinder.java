package ai;

import java.util.ArrayList;

import entity.Entity;
import main.GamePanel;

public class PathFinder {

	GamePanel gp;
	Node[][] node;
	ArrayList<Node> openList = new ArrayList<>();
	public ArrayList<Node> pathList = new ArrayList<Node>();
	Node startNode, goalNode, currentNode;
	boolean goalReached = false;
	int step = 0;
	
	public PathFinder(GamePanel gp) {
		this.gp = gp;
		instantiateNodes();
	}
	
	public void instantiateNodes() {
		
		node = new Node[50][50];
		
		int col = 0;
		int row = 0;
		
		while(col < 50 && row < 50) {
			
			node[col][row] = new Node(col, row);
			
			col++;
			if(col == 50) {
				col = 0;
				row++;
			}
		}
		
	}
	
	public void resetNode() {
		
		int col = 0;
		int row = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			node[col][row].open = false;
			node[col][row].checked = false;
			node[col][row].solid = false;
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
		
		//reset other settings
		openList.clear();
		pathList.clear();
		goalReached = false;
		step = 0;
	}
	
	private void setNodeSolid() {

		for (int i = 0; i < gp.obj[gp.currentMap].length; i++) {
		    Entity object = gp.obj[gp.currentMap][i];
		    if (object != null && object.collision == true) {
		        // Calculate the object's top-left and bottom-right corners in terms of tile positions
		        int objLeftCol = (object.worldX + object.solidArea.x) / gp.tileSize;
		        int objRightCol = (object.worldX + object.solidArea.x + object.solidArea.width) / gp.tileSize;
		        int objTopRow = (object.worldY + object.solidArea.y) / gp.tileSize;
		        int objBottomRow = (object.worldY + object.solidArea.y + object.solidArea.height) / gp.tileSize;

		        // Mark all tiles covered by the object as solid
		        for (int objCol = objLeftCol; objCol <= objRightCol; objCol++) {
		            for (int objRow = objTopRow; objRow <= objBottomRow; objRow++) {
		               if(objCol < 50 && objRow < 50 && objCol >= 0 && objRow >= 0) {
		            	   node[objCol][objRow].solid = true;
		               }
		            }
		        }
		    }
		}
	}

	public void setNode(int startCol, int startRow, int goalCol, int goalRow) {
		
		resetNode();
		setNodeSolid();
		//set start and goal
		if(startCol >= 0 && startRow >= 0 && startCol < 50 && startRow < 50) {
			startNode = node[startCol][startRow];			
		}
		currentNode = startNode;
		goalNode = node[goalCol][goalRow];
		openList.add(currentNode);
		
		int col = 0;
		int row = 0;

		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			
			//set solid node
			
			//check tile
			int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
			if(gp.tileM.tile[tileNum].collision == true) {
				node[col][row].solid = true;
			}
			
			//set Cost
			getCost(node[col][row]);
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}

	public void getCost(Node node) {
		
		//g cost
		int xDistance = Math.abs(node.col - startNode.col);
		int yDistance = Math.abs(node.row - startNode.row);
		node.gCost  = xDistance + yDistance;
		
		//h cost
		xDistance = Math.abs(node.col - goalNode.col);
		yDistance = Math.abs(node.row - goalNode.row);
		node.hCost  = xDistance + yDistance;	
		
		//f cost
		node.fCost = node.gCost + node.hCost;
	}
	
	public boolean search() {
		
		while(goalReached == false && step < 500) {
			
			int col = currentNode.col;
			int row = currentNode.row;
			
			//check the current Node
			currentNode.checked = true;
			openList.remove(currentNode);
			
			//open the Up Node
			if(row - 1 >= 0) {
				openNode(node[col][row - 1]);
			}
			if(col - 1 >= 0) {
				openNode(node[col - 1][row]);
			}
			if(row + 1 < gp.maxWorldRow) {
				openNode(node[col][row + 1]);
			}
			if(col + 1 < gp.maxWorldCol) {
				openNode(node[col + 1][row]);
			}
			
			//find the best node
			int bestNodeIndex = 0;
			int bestNodefCost = 999;
			
			for(int i = 0; i < openList.size(); i++) {
				
				//check if  this node's F cost is better
				if(openList.get(i).fCost < bestNodefCost) {
					bestNodeIndex = i;
					bestNodefCost = openList.get(i).fCost;
				}
				//if f cost is equal, check the G cost
				else if(openList.get(i).fCost == bestNodefCost) {
					if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
						bestNodeIndex = i;
					}
				}
			}
			
			// if there is no node in the openList, end the loop
			if(openList.size()== 0) {
				break;
			}
			
			//after the loop, openList[bestNodeIndex] is the next step (= currentNode)
			currentNode = openList.get(bestNodeIndex);
			
			if(currentNode == goalNode) {
				goalReached = true;
				trackthePath();
			}
			step++;
		}
		
		return goalReached;
	}

	public void openNode(Node node) {
		
		if(node.open == false && node.checked == false && node.solid == false) {
			
			node.open = true;
			node.parent = currentNode;
			openList.add(node);
		}
	}
	
	public void trackthePath() {
		
		Node current = goalNode;
		
		while(current != startNode) {
			
			pathList.add(0, current);
			current  = current.parent;
		}
	}

}

