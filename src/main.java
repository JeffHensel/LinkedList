
public class main {
    public static void main(String[] args) {
        LinkedList<String> l = new LinkedList<String>();
        l.addFirst("Test");
        l.addFirst("Test1");
        l.addLast("Testlast");
        l.add(2, "Inserted");
        System.out.println(l.toString());
        System.out.println(l.get(2));
    }
}