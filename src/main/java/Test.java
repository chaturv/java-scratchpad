import java.util.*;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(5);

        //System.out.println(list);
        Random rnd = new Random();

        int i = 0;
        while (i < 5) {
            int nextInt = rnd.nextInt(20);
            System.out.println(nextInt);
            i++;
        }

        int frequency = Collections.frequency(list, 5);
        System.out.println("--");
        System.out.println(frequency);

        System.out.println(Collections.disjoint(list, Arrays.asList(11,12)));


//        ListIterator<Integer> listIterator = list.listIterator();
//        while (listIterator.hasNext()) {
//            Integer next = listIterator.next();
//            if (next == 4) {
//                listIterator.previous();
//                listIterator.add(3);
//                break;
//            }
//        }
//
//        ListIterator<Integer> listIterator = list.listIterator(list.size());
//        while (listIterator.hasPrevious()) {
//            Integer previous = listIterator.previous();
//            if (previous == 4) {
//                listIterator.add(3);
//                break;
//            }
//        }


        //System.out.println(list);
    }
}