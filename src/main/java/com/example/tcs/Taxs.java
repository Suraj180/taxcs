package com.example.tcs;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Taxs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String PName;
    private String status;

    private int salary;

    private int bonus;
    private int allowance;
    private int others;

    private int fund;
    private int CIT;
    private int Ins;
    private int time;
    private int totalIncome;
    private double totalTax;
    //    private int taxRate;
//
//
//    public int getTax() {
//        if (Objects.equals(status, "Married")){
//            taxRate = 2;
//        }else{
//            taxRate = 3;
//        }
//        return taxRate;
//    }
//
//    public void setTax(int tax) {
//        this.taxRate = taxRate;
//    }
    public String getPName() {
        return PName;
    }

    public void setPName(String pName) {
        PName = pName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getFund() {
        return fund;
    }

    public void setFund(int fund) {
        this.fund = fund;
    }

    public int getCIT() {
        return CIT;
    }

    public void setCIT(int CIT) {
        this.CIT = CIT;
    }

    public int getIns() {
        return Ins;
    }

    public void setIns(int ins) {
        Ins = ins;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }




    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public int getTotalIncome() {
        totalIncome = (salary + bonus +allowance + others) - (fund+CIT+Ins);
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalTax() {
        if (Objects.equals(status, "Unmarried")) {
            if (totalIncome <= 400000) {
                totalTax = totalIncome * 0.01;
                return totalTax;

            } else if (totalIncome > 400001 && totalIncome <= 500000) {
                totalTax = ((0.1 * (totalIncome - 400000)) + (0.01 * 400000));
                return totalTax;

            } else if (totalIncome > 500000 && totalIncome <= 700000) {
                totalTax = ((0.2 * (totalIncome - 500000)) + (0.01 * 400000) + (0.1 * 100000));
                return totalTax;

            }else if (totalIncome > 700000 && totalIncome <= 2000000) {
                totalTax = ((0.3 * (totalIncome - 700000)) + (0.01 * 400000) + (0.1 * 100000)+ (0.2 * 200000));
                return totalTax;

            }else if (totalIncome > 2000000) {
                totalTax = ((0.36 * (totalIncome - 2000000)) + (0.01 * 400000) + (0.1 * 100000)+ (0.2 * 200000)+ (0.3 * 1300000));
                return totalTax;
            }
        } else if (Objects.equals(status, "Married")){
            if (totalIncome <= 450000) {
                totalTax = totalIncome * 0.01;
                return totalTax;

            } else if (totalIncome > 450001 && totalIncome <= 550000) {
                totalTax = ((0.1 * (totalIncome - 400000)) + (0.01 * 400000));
                return totalTax;

            } else if (totalIncome > 550000 && totalIncome <= 750000) {
                totalTax = ((0.2 * (totalIncome - 500000)) + (0.01 * 400000) + (0.1 * 100000));
                return totalTax;

            }else if (totalIncome > 750000 && totalIncome <= 2000000) {
                totalTax = ((0.3 * (totalIncome - 700000)) + (0.01 * 400000) + (0.1 * 100000)+ (0.2 * 200000));
                return totalTax;

            }else if (totalIncome > 2000000) {
                totalTax = ((0.36 * (totalIncome - 2000000)) + (0.01 * 400000) + (0.1 * 100000)+ (0.2 * 200000)+ (0.3 * 1300000));
                return totalTax;
            }

        }

        return 0;
    }

    public void setTotalTax(int totalTax) {
        this.totalTax = totalTax;
    }
}
