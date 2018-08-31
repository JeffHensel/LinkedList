import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class LinkedListTest {

    @Test
    public void testBasic() {
        LinkedList<String> l = new LinkedList<String>();
        l.addFirst("Test");
        l.addFirst("Test1");
        l.addLast("Testlast");
        l.add(2, "Inserted");
        assertTrue("Test1".equals(l.getFirst()));
        assertTrue(l.get(0).equals(l.getFirst()));
        assertTrue("Test1".equals(l.get(0)));
        assertTrue("Test".equals(l.get(1)));
        assertTrue("Testlast".equals(l.getLast()));
        assertTrue("Inserted".equals(l.get(2)));
        System.out.println(l.get(3));
        assertTrue("Testlast".equals(l.get(3)));
        assertTrue(l.get(3).equals(l.getLast()));
        assertTrue(l.size() == 4);
        System.out.println(l.toString());
        System.out.println("~Test1 passed");
    }
    
    @Test
    public void test1k() {
        int elements = 1000;
        addGet(elements);
        System.out.println("~Test1k passed");
    }
    
    @Test
    public void test10k() {
        int elements = 10000;
        addGet(elements);
        System.out.println("~Test10k passed");
    }
    
    @Test
    public void test30k() {
        int elements = 30000;
        addGet(elements);
        System.out.println("~Test30k passed");
    }
    
    @Test
    public void test100k() {
        int elements = 100000;
        addGet(elements);
        System.out.println("~Test100k passed");
    }
    
   /* @Test
    public void test1m() {
        int elements = 1000000;
        addGet(elements);
        System.out.println("~Test1m passed");
    }*/
    
    @Test
    public void testSet() {
        LinkedList<String> l = new LinkedList<String>();
        l.addFirst("Test");
        l.addFirst("Test1");
        
        l.addLast("Testlast");
        l.add(2, "Inserted");
        assertTrue("Test1".equals(l.getFirst()));
        assertTrue(l.get(0).equals(l.getFirst()));
        assertTrue("Test1".equals(l.get(0)));
        assertTrue("Test".equals(l.get(1)));
        assertTrue("Testlast".equals(l.getLast()));
        assertTrue("Inserted".equals(l.get(2)));
        assertTrue("Testlast".equals(l.get(3)));
        assertTrue(l.get(3).equals(l.getLast()));
        assertTrue(l.size() == 4);
        // test set functionality
        String returnedVal = l.set(0, "Set");
        assertTrue("Set".equals(l.getFirst()));
        assertTrue(l.get(0).equals(l.getFirst()));
        assertTrue("Set".equals(l.get(0)));
        assertTrue(returnedVal.equals("Test1"));
        String returnedVal2 = l.set(1, "Set2");
        assertTrue("Set2".equals(l.get(1)));
        assertTrue(returnedVal2.equals("Test"));
        System.out.println("~TestOthers passed");
    }
    
    private void addGet(int elements) {
        LinkedList<Integer> l = new LinkedList<Integer>();
        long totalCount = 0;
        Random rand = new Random();
        int addCount = 0;
        int addFirstCount = 0;
        int addLastCount = 0;
        // build up the list and save the total count
        for (int i = 0; i < elements; i++) {
            int valToInsert = rand.nextInt(100);
            totalCount += valToInsert;
            switch (rand.nextInt(3)) {
                case 0:
                    l.add(valToInsert);
                    addCount++;
                    break;
                case 1:
                    l.addFirst(valToInsert);
                    addFirstCount++;
                    break;
                case 2:
                    l.addLast(valToInsert);
                    addLastCount++;
                    break;
            }
        }
        // Get each index element and make sure it matches the count we put in
        long returnedCount = 0;
        for (int i = 0; i < elements; i++) {
            returnedCount += l.get(i);
        }
        System.out.println("Add count: " + addCount);
        System.out.println("Add first count: " + addFirstCount);
        System.out.println("Add last count: " + addLastCount);
        System.out.println("Returned value: " + returnedCount);
        System.out.println("Total value: " + totalCount);
        assertTrue(returnedCount == totalCount);
        assertTrue(l.size() == elements);
    }
    

}
