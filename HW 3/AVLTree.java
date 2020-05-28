/**
 * A Dank AVLTree, does AVL Stuff.
 *
 * @author Deven Ronquillo
 * @version 1/11/17
 */
public class AVLTree<E extends Comparable<? super E>> {

    AVLNode<E> treeRoot;

    /**
     * constructor for avl tree, calls super and initiates tree root as null.
     */
    public AVLTree(){

        super();
        treeRoot = null;
    }

    /**
     * Exposed method for Adding.
     *
     * @param item The item of type E to Add.
     * @return  AVLNode<>   The root of the tree the item was Added to.
     */
    public AVLNode<E> Add(E item){

        this.treeRoot = insert(item, this.treeRoot);
        return treeRoot;
    }
    /**
     * Exposed method for removing.
     *
     * @param item The item of type E to Remove.
     * @return  AVLNode<>   The root of the tree the item was Removed from.
     */
    public AVLNode<E> Remove(E item){

        return Remove(item, this.treeRoot);
    }
    /**
     * Exposed method for searching.
     *
     * @param item The item of type E to find.
     * @return  AVLNode<>   The root of the tree to search.
     */
    public boolean Contains(E item){

        return Contains(item, this.treeRoot);
    }

    /**
     * gets the height of a given node.
     *
     * @param node  The desired AVLNode<>.
     * @return int  the given nodes height.
     */
    public int Height(AVLNode<E> node){

        return ((node == null)? -1 : node.height);
    }

    private AVLNode<E> insert(E item, AVLNode<E> treeRoot){

        if (treeRoot == null) {

            return new AVLNode<>(item);
        }

        int cmpValue = item.compareTo(treeRoot.element);

        if (cmpValue < 0) {

            treeRoot.left = insert(item, treeRoot.left);
        } else if (cmpValue > 0) {

            treeRoot.right = insert(item, treeRoot.right);
        } else {

        }

        return Balance(treeRoot);
    }

    /**
     * determines if our tree is unBalanced and calls proper rotations.
     * @param treeRoot The root node of the tree to be Balanced.
     * @return int Root of the Balanced tree.
     */
    private AVLNode<E> Balance(AVLNode<E> treeRoot) {

        final int heightEpsilon = 1;

        if (treeRoot == null) {

            return treeRoot;
        }

        if (Height(treeRoot.left) - Height(treeRoot.right) > heightEpsilon) {

            if (Height(treeRoot.left.left) >= Height(treeRoot.left.right)) {

                treeRoot = SingleRLeft(treeRoot);
            } else {

                treeRoot = DoubleRLeft(treeRoot);
            }
        } else {

            if (Height(treeRoot.right) - Height(treeRoot.left) > heightEpsilon) {

                if (Height(treeRoot.right.right) >= Height(treeRoot.right.left)) {

                    treeRoot = SingleRRight(treeRoot);
                } else {

                    treeRoot = DoubleRRight(treeRoot);
                }
            }
        }

        treeRoot.height = Math.max(Height(treeRoot.left), Height(treeRoot.right)) + 1;

        return treeRoot;
    }

    /**
     * performs our single rotation with a tree containing a left child.
     * @param treeRoot The root of the tree to be rotated.
     * @return AVLNode<> The left child of the root.
     */
    private AVLNode<E> SingleRLeft(AVLNode<E> treeRoot) {

        AVLNode<E> treeRootLeft = treeRoot.left;

        treeRoot.left = treeRootLeft.right;
        treeRootLeft.right = treeRoot;

        treeRoot.height = Math.max(Height(treeRoot.left), Height(treeRoot.right)) + 1;
        treeRootLeft.height = Math.max(Height(treeRootLeft.left), Height(treeRoot)) + 1;

        return treeRootLeft;
    }
    /**
     * performs a double rotation on a tree with an inner left child.
     *
     * @param treeRoot The root of the tree to be rotated.
     * @return The rotated tree.
     */
    private AVLNode<E> DoubleRLeft(AVLNode<E> treeRoot) {

        treeRoot.left = SingleRRight(treeRoot.left);

        return SingleRLeft(treeRoot);
    }
    /**
     * performs our single rotation with a tree containing a right child.
     * @param treeRoot The root of the tree to be rotated.
     * @return AVLNode<> The right child of the root.
     */
    private AVLNode<E> SingleRRight(AVLNode<E> treeRoot) {

        AVLNode<E> treeRootRight = treeRoot.right;

        treeRoot.right = treeRootRight.left;
        treeRootRight.left = treeRoot;

        treeRoot.height = Math.max(Height(treeRoot.left), Height(treeRoot.right)) + 1;
        treeRootRight.height = Math.max(Height(treeRootRight.right), Height(treeRoot)) + 1;

        return treeRootRight;
    }

    /**
     * performs a double rotation on a tree with an inner right child.
     *
     * @param treeRoot The root of the tree to be rotated.
     * @return The rotated tree.
     */
    private AVLNode<E> DoubleRRight(AVLNode<E> treeRoot) {

        treeRoot.right = SingleRLeft(treeRoot.right);

        return SingleRRight(treeRoot);
    }

    /**
     * Removes the desired node if found.
     * @param item  The object to be Removed.
     * @param treeRoot  The root of the tree to search.
     * @return AVLNode<>    The untouched tree if the value was not found or the now Balanced tree after removal.
     */
    private AVLNode<E> Remove(E item, AVLNode<E> treeRoot) {

        if (treeRoot == null) {

            return treeRoot;
        }

        int cmpValue = item.compareTo(treeRoot.element);

        if (cmpValue < 0) {

            treeRoot.left = Remove(item, treeRoot.left);
        } else if (cmpValue > 0) {

            treeRoot.right = Remove(item, treeRoot.right);
      /*} else if (treeRoot.right != null && treeRoot.left != null) {

        treeRoot.element = findMin(treeRoot.right).element;
        treeRoot.right = Remove(treeRoot.element, treeRoot.right);
      */} else {

            treeRoot = (treeRoot.left != null) ? treeRoot.left : treeRoot.right;
        }

        return Balance(treeRoot);
    }

    /**
     * Runs through the tree looking for the item specified.
     *
     * @param item  The item to search for.
     * @param treeRoot  The root of the tre to search.
     * @return  boolean Weather or not the item was found.
     */
     private boolean Contains(E item, AVLNode<E> treeRoot){

        if (treeRoot == null){

            return false;

        } else if (item.compareTo(treeRoot.element) < 0){

            return Contains(item, treeRoot.left);
        } else if (item.compareTo(treeRoot.element) > 0){

            return Contains(item, treeRoot.right);
        }

        return true;
    }
}
