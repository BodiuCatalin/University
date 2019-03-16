public class MyIt implements java.util.Iterator<Double> {
    private SortedLinkedList.Node current;

    public MyIt(SortedLinkedList list){
        this.current = list.tryGetStart();
    }

    @Override
    public boolean hasNext() {
        return current.getLink() != null;
    }

    @Override
    public Double next() {
        current = current.getLink();
        Writer.writeToFile("4", "ITERATION", current.getData());
        return current.getData();
    }
}