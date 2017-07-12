package im.assetmanagement.data.Utils;

/**
 * Created by pkota on 11-07-2017.
 */

public class User {

    private String firstName;
    private String lastName;
    private String username;
    private String pin;
    private String confirmPin;
    private String id;
    private String MACId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getConfirmPin() {
        return confirmPin;
    }

    public void setConfirmPin(String confirmPin) {
        this.confirmPin = confirmPin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMACId() {
        return MACId;
    }

    public void setMACId(String MACId) {
        this.MACId = MACId;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", pin='" + pin + '\'' +
                ", confirmPin='" + confirmPin + '\'' +
                ", id='" + id + '\'' +
                ", MACId='" + MACId + '\'' +
                '}';
    }
}
