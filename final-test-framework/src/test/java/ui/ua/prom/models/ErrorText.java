package ui.ua.prom.models;

public enum ErrorText {
    EMAIL_NOT_REGISTERED("Пользователь с таким email не зарегистрирован в системе"),
    WRONG_USER_CREDENTIALS("Проверьте правильность написания или попробуйте ввести другой email," +
            " номер телефона или пароль"),
    WRONG_EMAIL_FORMAT("Введите email в формате example@email.com");

    public final String message;

    ErrorText(String message) {
        this.message = message;
    }
}
