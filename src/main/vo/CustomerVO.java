package vo;

public class CustomerVO {
    private int customerId;
    private String custFirstName;
    private String custLastName;
    private String streetAddress1;
    private String streetAddress2;
    private String custCity;
    private String custState;
    private int custPostalCode;
    private String phoneNumber1;
    private String phoneNumber2;
    private int creaditLimit;
    private String custEmail;

    public CustomerVO(int customerId, String custFirstName, String custLastName, String streetAddress1,
                      String streetAddress2, String custCity, String custState, int custPostalCode, String phoneNumber1, String phoneNumber2, int creaditLimit, String custEmail) {
        this.customerId = customerId;
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.custCity = custCity;
        this.custState = custState;
        this.custPostalCode = custPostalCode;
        this.phoneNumber1 = phoneNumber1;
        this.phoneNumber2 = phoneNumber2;
        this.creaditLimit = creaditLimit;
        this.custEmail = custEmail;
    }

    public CustomerVO(String custFirstName, String custLastName, String streetAddress1, String streetAddress2,
                      String custCity, String custState, int custPostalCode, String phoneNumber1,
                      String custEmail)
    {
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.streetAddress1 = streetAddress1;
        this.streetAddress2 = streetAddress2;
        this.custCity = custCity;
        this.custState = custState;
        this.custPostalCode = custPostalCode;
        this.phoneNumber1 = phoneNumber1;
        this.custEmail = custEmail;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public String getStreetAddress1() {
        return streetAddress1;
    }

    public String getStreetAddress2() {
        return streetAddress2;
    }

    public String getCustCity() {
        return custCity;
    }

    public String getCustState() {
        return custState;
    }

    public int getCustPostalCode() {
        return custPostalCode;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public String getCustEmail() {
        return custEmail;
    }
}
