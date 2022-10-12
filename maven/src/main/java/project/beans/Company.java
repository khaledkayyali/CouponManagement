package project.beans;

import java.util.ArrayList;

public class Company {

    private int id;
    private String name;
    private String companyEmail;
    private String companyPassword;
    private ArrayList<Coupon> coupons;


    public Company(int id, String name, String email, String password, ArrayList<Coupon> coupons) {
        this.id = id;
        this.name = name;
        this.companyEmail = email;
        this.companyPassword = password;
        this.coupons = coupons;
    }

    public Company(int id, String name, String email, String password) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyPassword() {
        return companyPassword;
    }

    public void setCompanyPassword(String companyPassword) {
        this.companyPassword = companyPassword;
    }

    public ArrayList<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(ArrayList<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "com.facade.Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", companyPassword='" + companyPassword + '\'' +
                ", coupons=" + coupons +
                '}';
    }
}
