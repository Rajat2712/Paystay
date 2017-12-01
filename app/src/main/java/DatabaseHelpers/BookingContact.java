package DatabaseHelpers;

/**
 * Created by Rajat on 4/27/2017.
 */

public class BookingContact {


    String pgName,name,email,phone,amount;


    public BookingContact(){};


    public BookingContact(String pgName,String name,String email,String phone, String amount) {

        this.pgName=pgName;
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.amount=amount;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPgName() {
        return pgName;
    }

    public void setPgName(String pgName) {
        this.pgName = pgName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
