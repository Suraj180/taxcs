package com.example.tcs;




import javax.persistence.*;

@Entity
public class CTax {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


    private long id;
    public long getId() {
        return id;
    }
    private String CompanyName;
    private int AnnualIncome;
    private int others;
    private int TaxRate;
    private int TIncome;
    private double TTax;

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public int getAnnualIncome() {
        return AnnualIncome;
    }

    public void setAnnualIncome(int annualIncome) {
        AnnualIncome = annualIncome;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }

    public int getTaxRate() {
        return TaxRate;


    }

    public void setTaxRate(int TaxRate) {
        this.TaxRate = TaxRate;
    }

    public int getTIncome() {
        TIncome = (AnnualIncome + others);
        return TIncome;
    }

    public void setTIncome(int TIncome) {
        this.TIncome = TIncome;
    }

    public double getTTax() {
        if (TaxRate == 20){
            TTax= (TIncome * 0.2);
            return TTax;


        } else if(TaxRate == 25){
            TTax= (TIncome * 0.25);
            return TTax;

        }else if(TaxRate== 30){
            TTax= (TIncome * 0.3);
            return TTax;

        }
        return 0;
    }

    public void setTTax(double TTax) {
        this.TTax = TTax;
    }

}
