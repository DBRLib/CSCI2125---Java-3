/**
@author Deven Ronquillo
@version fall 2017
*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList <T> implements Iterable<T>{

    private Node headNode;
    private Node tailNode;

    public SinglyLinkedList(){

      super();
      this.headNode = new Node(null);
      this.tailNode = new Node(null);

      this.headNode.nextNode = this.tailNode;
      this.tailNode.nextNode = null;
    }

    public void add(T element){

        SinglyLinkedListIterator newIterator = iterator();

        while(newIterator.hasNext()){

            newIterator.next();
        }

        newIterator.add(element);
        this.tailNode = newIterator.currentNode;
    }

    public void remove(T element){

        Iterator<T> newIterator = new SinglyLinkedListIterator(this.headNode);

        boolean removed = false;

        while(newIterator.hasNext() && !removed){

            if (newIterator.next().equals(element)){

                newIterator.remove();

                removed = true;
            }
        }
    }

    public void clear(){

        headNode.nextNode = tailNode;
    }

    public T getNthToLast(int index){

        Iterator<T> newIterator = iterator();

        int size = 0;

        while(newIterator.hasNext()){

            newIterator.next();
            size++;
        }

        newIterator = iterator();

        for(int x = 0; x <= (size - index - 1); x++){

            newIterator.next();
        }

        return newIterator.next();
    }

    public SinglyLinkedListIterator iterator(){

        return new SinglyLinkedListIterator(headNode);

    }

    class Node{//node class

        T data;
        Node nextNode;

        Node(T data){

            super();

            this.data = data;
            this.nextNode = null;
        }
    }

    public class SinglyLinkedListIterator implements Iterator{

         Node currentNode;
         Node previousNode;

        private SinglyLinkedListIterator(Node headNode){

            this.currentNode = headNode;
            this.previousNode = headNode;
        }

        public boolean hasNext(){

            if(currentNode.nextNode.nextNode == null && currentNode.nextNode.data == null){

                return false;
            }
            else{

                return true;
            }
        }

        public T next() throws NoSuchElementException{

            if(currentNode.nextNode.nextNode == null && currentNode.nextNode.data == null){

                throw new NoSuchElementException();

            }
            else{

                previousNode = currentNode;
                currentNode = currentNode.nextNode;
            }

            return currentNode.data;
        }

        public void remove() throws IllegalStateException{

            if(currentNode.nextNode.nextNode == null && currentNode.data == null){

                throw new IllegalStateException();
            }
            else{

                previousNode.nextNode = currentNode.nextNode;
                currentNode = previousNode;
            }
        }

        public void add(T element){

            Node newNode = new Node(element);

            newNode.nextNode = currentNode.nextNode;
            currentNode.nextNode = newNode;
        }

    }

}




