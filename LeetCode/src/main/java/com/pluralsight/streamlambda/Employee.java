package com.pluralsight.streamlambda;

public class Employee {

  public String emplName;
  public Integer empSalary;

  public String getEmplName() {
    return emplName;
  }

  public void setEmplName(String emplName) {
    this.emplName = emplName;
  }

  public Integer getEmpSalary() {
    return empSalary;
  }

  public void setEmpSalary(Integer empSalary) {
    this.empSalary = empSalary;
  }

  public Employee(String emplName, Integer empSalary) {
    this.emplName = emplName;
    this.empSalary = empSalary;
  }

  public String getTitle() {
    return null;
  }

  public int getSalary() {
    return 0;
  }
}
