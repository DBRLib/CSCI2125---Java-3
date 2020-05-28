/**
@author Deven Ronquillo
@version 1/11/2017
*/

import java.util.ArrayList;

/**
* This static helper class can be used to print out an AVL Tree.
* <p>
* Usage:
* <p>
* {@code AVLTree tree = new AVLTree();}<br>
* {@code tree.add(8);}<br>
* {@code tree.add(5);}<br>
* {@code tree.add(2);}<br>
* {@code tree.add(3);}<br>
* {@code tree.add(4);}<br>
* {@code tree.add(13);}<br>
* {@code tree.add(17);}<br>
* {@code tree.add(11);}<br>
* {@code tree.add(7);}<br>
* {@code tree.add(9);}<br>
* {@code PrintAVLTree.printTree(tree.getRoot());}<br>
* <p>
* This class is provided as is with no guarantees or warrantees.
*/
public class PrintAVLTree{

  /**
  * Print out a BST in the same way that we would draw one.
  *
  *	@param root an AVLNode that allows direct access to its element, height, and left and right
  *	children. See the AVLNode class below as an example.
  * @param <E> must be Comparable or inherit being Comparable
  */
  public static <E extends Comparable<? super E>> void printTree(AVLNode<E> root){
    System.out.println("PRINTING THE TREE");
    int treeHeight = height( root );
    System.out.println("tree height: " + treeHeight);

    // First initialize an ArrayList of levels. In order to print out all the nodes on a level,
    // this method needs a list of all the nodes on that level and a list of all these levels.
    ArrayList<ArrayList<E> > levels = new ArrayList<>(treeHeight+1);
    for(int i = 0; i < treeHeight+1; i++){
      int sizeOfList = (int)(Math.pow(2,i)); // level 0 holds 1 element (the root),
      // level 1 holds 2 elements, level 2 holds
      // 4 elements.
      levels.add(new ArrayList<E>(sizeOfList));
      for(int j = 0; j < sizeOfList; j++){
        levels.get(i).add(null); // initialize all elements to be null
      }
    }

    // Add all the nodes to the levels ArrayList. Perform an in order traversal of the tree,
    // adding each node to the proper level at the proper place.
    buildTreeArray(root.left, levels, 0, 1);
    levels.get(0).set(0,root.element);
    buildTreeArray(root.right, levels, 1, 1);

    int width = 3;
    int space = 0;
    // Build the strings to represent all the levels and the edges between them.
    // WARNING: THERE BE DRAGONS BEYOND HERE. Venture at your own risk.
    ArrayList<String> treeStrings = new ArrayList<>();
    for(int i = levels.size()-1; i >= 0 ; i--){
      int indent = space+1;
      int depth = treeHeight-i;
      space = (((int)Math.pow(2,depth+1))-1)*width;
      int halfWidth = width/2 + 1;
      int halfSpace = space/2 + 1;

      // The following mess is in order to be able to change the the width and spacing of the
      // tree.
      // First create a format string specifying the proper length.
      // Then create the precise spacing strings needed to draw the tree.
      // If width = 3, format = "%3s"
      // A width of 3 implies the largest length of the string representation of a number
      // (including negative signs) should be no more than 3 characters.
      String format = String.format("%s%ds", "%",indent);
      String indentStr = String.format(format,"");
      format = String.format("%s%ds", "%", space);
      String spaceStr = String.format(format,"");
      format = String.format("%s%ds", "%", halfWidth);
      String halfWidthStr = String.format(format,"");
      format = String.format("%s%ds", "%", halfSpace);
      String halfSpaceStr = String.format(format,"");
      // This last format string is used to format the nodes
      format = String.format("%s%ds", "%", width);
      String widthStr = String.format(format,"");

      ArrayList<E> level = levels.get(i);

      String levelStr = "";
      String edgesStr = "";
      levelStr = levelStr + indentStr;
      edgesStr = edgesStr + halfSpaceStr + halfWidthStr;
      // For each node on the level
      for(int j = 0; j < level.size(); j++){
        // If this element is not null, concatenate this element to the level string
        if(level.get(j) != null){
          levelStr = levelStr + String.format(format,level.get(j)) + spaceStr;
        } else { // else concatenate the symbol for a null leaf.
          levelStr = levelStr + String.format(format,"|-|") + spaceStr;
        }
        // Concatenate to the edges string the edges to this nodes children
        if(j%2==0){ // The even indices of level hold the left children, draw left edge
          edgesStr = edgesStr + "/" + spaceStr;
        } else { // else draw right edge
          edgesStr = edgesStr + "\\" + halfWidthStr + spaceStr + halfWidthStr;
        }
      }
      treeStrings.add(levelStr);
      treeStrings.add(edgesStr);
    }
    // Finally, print out all the strings
    for(int i = treeStrings.size()-2; i >= 0; i--){
      System.out.println(treeStrings.get(i));
    }
  }

  /**
  * Return the height of this node or -1 if the node is null
  * @param t the node to get the heigh of
  * @param <E> must be Comparable or inherit being Comparable
  * @return the heigh of the node or -1 if the node is null
  */
  protected static <E extends Comparable<? super E>> int height(AVLNode<E> t)
  {
    return t == null ? -1 : t.height;
  }

  /**
  * Recursively perform an in order traversal to build the levels of the tree.
  *
  * @param node the node from which we are traversing
  * @param list the list of levels that we are building
  * @param levelIndex the index into node's level of its position in that level
  * @param depth the depth of node into the tree (also the index of its level)
  * @param <E> must be Comparable or inherit being Comparable
  */
  protected static <E extends Comparable<? super E>> void buildTreeArray(AVLNode<E> node,
      ArrayList<ArrayList<E> > list,
      int levelIndex,
      int depth) {

    if(node != null){
      // left child is at the next depth and levelIndex*2
      buildTreeArray(node.left, list, levelIndex*2, depth+1);
      list.get(depth).set(levelIndex, node.element);
      // right child is at the next depth and levelIndex*2+1
      buildTreeArray(node.right, list, levelIndex*2+1, depth+1);
    }
    // to illustrate the level index progression, consider the following:
    // The values of the nodes represent their indices into the list
    // that holds the nodes at that depth.
    // level 0:                   0
    //                      /           \
    // level 1:           0               1
    //                  /    \         /     \
    // level 2:       0       1       2       3
    //              /  \     / \     / \     / \
    // level 3:    0    1   2   3   4   5   6   7
  }

}
