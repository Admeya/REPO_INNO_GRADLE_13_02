package Day_2_Generics.Example_2;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainBox {

    public static void main(String[] args) {
       Generics<Integer> box = new Generics<Integer>();
       box.setValue(54);
       System.out.println((box.getValue()) + " our  ");
       
       
       //BoxNumber<Number> boxNumber = new BoxNumber<>(); //не ковариантны
       BoxNumber<Integer> boxNumberInt = new BoxNumber<Integer>();
       //boxNumber = boxNumberInt;
       boxNumberInt.setValue((Integer) 24);
       System.out.println(boxNumberInt.comp(24));
       
       LinkedList<String> ar = new LinkedList<String>();
       ar.addFirst("ab");
    }

}
