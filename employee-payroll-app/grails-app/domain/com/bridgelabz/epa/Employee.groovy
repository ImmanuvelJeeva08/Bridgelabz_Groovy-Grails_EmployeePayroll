/**
 * Purpose : To create a model of Employee and apply constraints on model
 *
 */
package com.bridgelabz.epa

import java.time.LocalDate

class Employee {

    Integer id
    String name
    String gender
    String profilePic
    List<String> department = ["HR", "Sales", "Finance", "Engineer", "Others"]
    Integer salary

    LocalDate startDate
    String notes

    static constraints = {
        id(unique: true, blank: false)
        name(blank: false, size: 4..40, nullable: true)
        gender(blank: false, nullable: true)
        profilePic(nullable: true)
        department(nullable : true)
        salary(min: 10000, blank: false, nullable: true)
        startDate(nullable: true)
        notes(nullable: true)
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                ", startDate=" + startDate +
                ", notes='" + notes + '\'' +
                '}';
    }
}
