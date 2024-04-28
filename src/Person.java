import java.util.Calendar;

public abstract class Person {

    private String name;
    private int ID;

    protected void setID(int iD) {
        ID = iD;
    }

    private SqltoArray sqltoArray = new SqltoArray();
    private Popup popup = new Popup();
    private MySQLConnect cc = new MySQLConnect();

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
        int rest = 0;
        try {

            if (isStudent) {

                generatedID += 1;

                rest = Integer.parseInt(sqltoArray.fill("Select StudentId from ids where id=1")[0]);

            } else {
                rest = Integer.parseInt(sqltoArray.fill("Select TeacherId from ids where id=1")[0]);
            }
        } catch (Exception e) {
            popup.showError("Error while retriving id from database");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                popup.showError("Error in thread");
                System.exit(-1);
            }
            System.exit(-1);
        }
        generatedID *= 1000;
        generatedID += rest;
        return generatedID;
    }

    protected void updateId(boolean isStudent) {
        int idToBeUpdated = this.ID + 1;
        String query = "";
        cc.url = "jdbc:mysql://localhost:3306/oop2_project";
        cc.user = "root";
        cc.pass = "0000";
        cc.connect();
        if (isStudent) {
            query = "UPDATE ids SET StudentId = " + idToBeUpdated + "where id=1";
        } else {
            query = "UPDATE ids SET TeacherId = " + idToBeUpdated + "where id=1";
        }

        int result;
        try {
            result = cc.InsertUpdateDelete(query);
            if (result == 1) {
                popup.showInfo("ID updated!");
            } else {
                popup.showError("Error 404! not found");
            }
        } catch (Exception e1) {
            popup.showError("Error while updating database");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                popup.showError("Error in thread");
                System.exit(-1);
            }
            System.exit(-1);
        }
    };
}