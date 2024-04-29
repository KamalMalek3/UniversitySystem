import java.util.Calendar;

public abstract class Person {

    private String name;
    private int ID;
    private int rest;

    protected void setID(int iD) {
        ID = iD;
    }

    private static SqlInteract sql = new SqlInteract();
    private static Popup popup = new Popup();

    public Person() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    protected int generateID (boolean isStudent) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int generatedID = currentYear * 10;
        try {

            if (isStudent) {

                generatedID += 1;

                this.rest = Integer.parseInt(sql.fill("Select StudentId from ids where id=1")[0]);

            } else {
                this.rest = Integer.parseInt(sql.fill("Select TeacherId from ids where id=1")[0]);
            }
        } catch (Exception e) {
            popup.showError("Error while retriving id from database");
            System.exit(-1);
        }
        generatedID *= 1000;
        generatedID += this.rest;
        return generatedID;
    }

    protected void updateId(boolean isStudent) {
        int idToBeUpdated = this.rest + 1;
        String query = "";

        if (isStudent) {
            query = "UPDATE ids SET StudentId = " + idToBeUpdated + " where id=1";
        } else {
            query = "UPDATE ids SET TeacherId = " + idToBeUpdated + " where id=1";
        }

        int result=0;
        
            try {
                result = sql.perform(query);
            } catch (Exception e) {
                popup.showError("Error While performing query on database"+query);
            }
            if (result == 1) {
                popup.showInfo("ID updated!");
                this.ID = generateID(isStudent);
            } else {
                popup.showError("ID not found");
        } 
    }

}