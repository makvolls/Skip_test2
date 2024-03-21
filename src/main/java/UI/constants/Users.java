package UI.constants;

public enum Users {
    TEST ("authorization_test","crjhjcnm","Проверка Авторизации СУДИС");
    private final String login, password, name;

    Users(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
