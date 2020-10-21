import java.util.*;

public class Payment 
{
    public String ID;
    public String Date;
    public Integer Value;

    public PaymentType paymentType;
    public ArrayList<Payment> payments = new ArrayList<>();

    public Payment(String paymentID, String paymentDate, Integer paymentPrice)
    {
        ID = paymentID;
        Date = paymentDate;
        Value = paymentPrice;
    }

    public void setPaymentType(PaymentType type)
    {
        paymentType = type;
    }

}
