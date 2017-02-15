package ua.org.hasper.Entity.Enums;

public enum UserRole {
    ROLE_ADMIN ("Администратор")
    , ROLE_STUDENT ("Студент")
    , ROLE_TEACHER ("Учитель");

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
    public static UserRole RoleByName(String name){
        UserRole userRole = null;
        for (UserRole r:
                UserRole.values()){
            if(r.toString().equals(name)){
                userRole=r;
            }
        }
        return userRole;
    }
}

