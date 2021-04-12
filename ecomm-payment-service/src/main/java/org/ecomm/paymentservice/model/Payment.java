package org.ecomm.paymentservice.model;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Payment2")
@Table(schema = "customer", name = "Payment2")
public class Payment {
    private int id;
    private String type;
    private String cardholder;
    private String cardNum;
    private String provider;
    private Date expiryDate;
    private int cvv;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "customer.payment_id_seq",
            strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "customer.payment_id_seq",
            sequenceName = "customer.payment_id_seq",
            allocationSize = 1
    )
    public int getId() {
        return id;
    }

    public Payment setId(int id) {
        this.id = id;
        return this;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 20)
    public String getType() {
        return type;
    }

    public Payment setType(String type) {
        this.type = type;
        return this;
    }

    @Basic
    @Column(name = "cardholder", nullable = false, length = 100)
    public String getCardholder() {
        return cardholder;
    }

    public Payment setCardholder(String cardholder) {
        this.cardholder = cardholder;
        return this;
    }

    @Basic
    @Column(name = "card_num", nullable = false, length = 20)
    public String getCardNum() {
        return cardNum;
    }

    public Payment setCardNum(String cardNum) {
        this.cardNum = cardNum;
        return this;
    }

    @Basic
    @Column(name = "provider", nullable = false, length = 20)
    public String getProvider() {
        return provider;
    }

    public Payment setProvider(String provider) {
        this.provider = provider;
        return this;
    }

    @Basic
    @Column(name = "expiry_date", nullable = false)
    public Date getExpiryDate() {
        return expiryDate;
    }

    public Payment setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
        return this;
    }

    @Basic
    @Column(name = "cvv", nullable = false)
    public int getCvv() {
        return cvv;
    }

    public Payment setCvv(int cvv) {
        this.cvv = cvv;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != payment.id) return false;
        if (cvv != payment.cvv) return false;
        if (type != null ? !type.equals(payment.type) : payment.type != null) return false;
        if (cardholder != null ? !cardholder.equals(payment.cardholder) : payment.cardholder != null) return false;
        if (cardNum != null ? !cardNum.equals(payment.cardNum) : payment.cardNum != null) return false;
        if (provider != null ? !provider.equals(payment.provider) : payment.provider != null) return false;
        if (expiryDate != null ? !expiryDate.equals(payment.expiryDate) : payment.expiryDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (cardholder != null ? cardholder.hashCode() : 0);
        result = 31 * result + (cardNum != null ? cardNum.hashCode() : 0);
        result = 31 * result + (provider != null ? provider.hashCode() : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        result = 31 * result + cvv;
        return result;
    }
}
