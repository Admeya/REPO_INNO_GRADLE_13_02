package Day_2_Generics.Example_2;

public class BoxNumber<T extends Comparable> {
    private T value;
    
    public int comp(T value2){
       return value.compareTo(value2);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    
  
}
