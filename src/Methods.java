public class Methods {
    private static Popup popup = new Popup();
    private static SqlInteract sql = new SqlInteract();

    public Methods() {
    }

    public static String getNameString(boolean isStudent, String id) {

        String Name;
        String query = "Select fullname from ";
        if (isStudent) {
            query += "students where Student_id = ";
        } else {
            query += "teachers where Teacher_id = ";
        }
        query += id;
        try {
            Name = sql.fill(query)[0];
        } catch (Exception e) {
            Name = "exception";
        }
        return Name;
    }

    public static boolean canSignIn(String idSignIn, String passwordSignIn, String personSignin) {
        int result = 0;
        final String adminId = "admin";
        final String adminPassword = "admin";
        try {
            switch (personSignin) {
                case "Admin":
                    if (idSignIn.equals(adminId) && passwordSignIn.equals(adminPassword))
                        result = 1;
                    break;
                case "Student":
                    String[] check = sql.fill("Select fullname from students where Student_id=\"" + idSignIn
                            + "\" And Student_Password =\"" + passwordSignIn + "\"");
                    result = check.length;
                    break;
                case "Teacher":
                    String[] check2 = sql.fill("Select fullname from teachers where Teacher_id=\"" + idSignIn
                            + "\" And Teacher_Password =\"" + passwordSignIn + "\"");
                    result = check2.length;
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            popup.showError("Exception Error Can't Sign in");
            return false;
        }
        return (1 == result);
    }
}