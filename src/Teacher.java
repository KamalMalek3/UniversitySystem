public class Teacher extends Person {

    public Teacher(){
        super.setID(generateID());
    }
    private int generateID() {
        return super.generateID(false);
    }
    public void updateId(){
        super.updateId(false);
    }
}   