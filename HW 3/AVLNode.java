/**
 * node structure for avl tree
 * 
 * @author Deven Ronquillo
 * @version 1/11/2017
 *
 * @param <E> the type of data this node holds.
 */
public class AVLNode<E extends Comparable<? super E>> {

    E element;
    AVLNode<E> left;  // Left Child
    AVLNode<E> right; // Right Child
    int height;

    /**
     * Construct a Node holding element with no children
     *
     * @param element the element this node will contain
     */
    AVLNode(E element) {

        this.element = element;
        this.left = null;
        this.right = null;
        this.height = 0;
    }
}
