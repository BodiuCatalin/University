import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SortedLinkedList {

    class Node
    {
        protected double data;
        protected Node link;
        protected Lock lock = new ReentrantLock();


        public Node()
        {
            link = null;
            data = 0;
        }


        public Node(double d,Node n)
        {
            data = d;
            link = n;
        }


        public void setLink(Node n)
        {
            link = n;
        }



        public Node getLink()
        {
            return link;
        }

        public double getData()
        {
            return data;
        }
    }


    protected Node start;
    public int size;
    private Lock listLock = new ReentrantLock();

    public SortedLinkedList() {
        start=null;
        size = 0;
    }

    //la	nivel	de lista.
    public void insert(double val)
    {
        listLock.lock();

        Node nodeToInsert, nextNode, current = null;
        nodeToInsert = new Node(val, null);
        boolean ins = false;

        if (start == null) {
            start = nodeToInsert;
        }

        else if (val <= start.getData())
        {
            nodeToInsert.setLink(start);
            start = nodeToInsert;
        }

        else
        {
            current = start;
            nextNode = start.getLink();
            if(nextNode != null)
                while(nextNode != null)
                {

                    if (val >= current.getData() && val <= nextNode.getData())
                    {
                        current.setLink(nodeToInsert);
                        nodeToInsert.setLink(nextNode);
                        ins = true;
                        break;
                    }

                    else
                    {
                        current = nextNode;
                        nextNode = nextNode.getLink();
                    }
                }

            if (ins == false)
            {
                current.setLink(nodeToInsert);
            }
        }


        size++;
        listLock.unlock();
    }

    //la nivel de nod/portiune din lista
    public void inserts(double val)
    {

        Node nodeToInsert, nextNode, current = null;
        nodeToInsert = new Node(val, null);
        boolean ins = false;

        if (start == null) {
            start = nodeToInsert;
        }

        else if (val <= start.getData())
        {
            nodeToInsert.setLink(start);
            start = nodeToInsert;
        }

        else
        {
            current = start;
            current.lock.lock();
            nextNode = start.getLink();
            while(nextNode != null){
                nextNode.lock.tryLock();

                if (val >= current.getData() && val <= nextNode.getData())
                {
                    current.setLink(nodeToInsert);
                    nodeToInsert.setLink(nextNode);
                    ins = true;
                    break;
                }

                else
                {
                    Node temp = nextNode;
                    current.lock.unlock();
                    current = temp;
                    nextNode = nextNode.getLink();
                }


            }


            if (ins == false)
            {
                current.setLink(nodeToInsert);
            }
            if(nextNode != null){
                nextNode.lock.unlock();
            }
            current.lock.unlock();
        }


        size++;

    }


    //la nivel de nod/portiune din lista
    public void deleteElements(double elem){
        Node current = start;
        Node next = start.getLink();
        if(elem == start.getData()){
            start = start.link;
            --size;

            return;
        }
        current.lock.lock();
        while(next != null) {
            next.lock.tryLock();
            if (next.getData() == elem) {
                current.setLink(next.getLink());
                --size;

                return;
            } else {
                Node temp = next;
                current.lock.unlock();
                current = temp;
                next = next.getLink();
            }
        }
        if(next != null){
            next.lock.unlock();
        }
        current.lock.unlock();

    }
    //la nivel de lista
    public void deleteElement(double elem){
        listLock.lock();
        Node current = start;
        Node next = start.getLink();
        if(elem == start.getData()){
            start = start.link;
            --size;

            return;
        }
        while(next != null) {
            if (next.getData() == elem) {
                current.setLink(next.getLink());
                --size;

                return;
            } else {
                Node temp = next;
                current = temp;
                next = next.getLink();
            }
        }
        listLock.unlock();

    }

    public Node tryGetStart(){
        return start;
    }


    public synchronized MyIt getIterator(){
        return new MyIt(this);
    }
}