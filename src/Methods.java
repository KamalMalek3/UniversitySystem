public class Methods {
    private Popup popup = new Popup();
    private static SqlInteract sql = new SqlInteract();

    public Methods() {
    }

    public String getNameString (boolean isStudent,String id){

        String Name;
        String query="Select fullname from ";
        if (isStudent){
            query+="students where Student_id = ";
        }
        else{
            query+="teachers where Teacher_id = ";
        }
        query+=id;
        System.out.println(query);
        try {
            Name = sql.fill(query)[0];
        } catch (Exception e) {
            Name="exception";
        }
        return Name;
    }
    
    public boolean canSignIn(String idSignIn, String passwordSignIn, String personSignin){
        int result = 0;
        final String adminId="admin";
        final String adminPassword="admin";
        System.out.println(idSignIn+" "+passwordSignIn);
        try {
        switch (personSignin) {
            case "Admin":
                if (idSignIn.equals(adminId) && passwordSignIn.equals(adminPassword))
                    result=1;
                break;
            case "Student":
                String[] check = sql.fill("Select fullname from students where Student_id=\""+idSignIn+"\" And Student_Password =\""+passwordSignIn+"\"");
                result = check.length;
                break;
            case "Teacher":
                String[] check2 = sql.fill("Select fullname from teachers where Teacher_id=\""+idSignIn+"\" And Teacher_Password =\""+passwordSignIn+"\"");
                result = check2.length;
                break;
            default:
                break;
        }
        }catch(Exception e){
            popup.showError("Error Can't Sign in");
            return false;
        }
        if (result !=1){
            popup.showInfo("Wrong Id or Password");
        }
        return (1==result);
    }
}