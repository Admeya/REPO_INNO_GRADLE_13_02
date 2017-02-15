package Day_8_ClassLoader;

/**
 * Created by Ирина on 15.02.2017.
 */
public class Animal {
    private String type;
    private String classAnimal;
    private String famiy;
    private String genus;
    private String kind;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassAnimal() {
        return classAnimal;
    }

    public void setClassAnimal(String classAnimal) {
        this.classAnimal = classAnimal;
    }

    public String getFamiy() {
        return famiy;
    }

    public void setFamiy(String famiy) {
        this.famiy = famiy;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Animal(String type, String classAnimal, String famiy, String genus, String kind) {
        this.type = type;
        this.classAnimal = classAnimal;
        this.famiy = famiy;
        this.genus = genus;
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                ", classAnimal='" + classAnimal + '\'' +
                ", famiy='" + famiy + '\'' +
                ", genus='" + genus + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }
}
