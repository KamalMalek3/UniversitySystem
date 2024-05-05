public class Student extends Person {

    public Student() {
        super.setID(generateID());
    }

    private int generateID() {
        return super.generateID(true);
    }

    public void updateId() {
        super.updateId(true);
    }
}