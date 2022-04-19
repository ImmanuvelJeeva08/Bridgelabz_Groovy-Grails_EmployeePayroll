package com.bridgelabz.epa

import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap

@Transactional
class EmployeeService {

    def serviceMethod() {
    }

    /**
     * Purpose : To save employee data in to database
     *
     * @param params all fields of employee class
     * @return
     */
    def save(GrailsParameterMap params) {
        Employee employee = new Employee(params)
        def response = AppUtil.saveResponse(false, employee)
        if (employee.validate()) {
            employee.save(flush: true)
            if (!employee.hasErrors()) {
                response.isSuccess = true
            }
        }
        return response
    }

    /**
     * Purpose : To save updated data of employee in database
     *
     * @param employee user whose data needs to be updated
     * @param params  is updated data of employee
     * @return
     */
    def update(Employee employee, GrailsParameterMap params) {
        employee.properties = params
        def response = AppUtil.saveResponse(false, employee)
        if (employee.validate()) {
            employee.save(flush: true)
            if (!employee.hasErrors()) {
                response.isSuccess = true
            }
        }
        return response
    }

    /**
     * Purpose : To get data of employee with particular ID
     *
     * @param id of the employee whose data needs to be fetched
     * @return data of particular id
     */
    def getById(Serializable id) {
        return Employee.get(id)
    }

    /**
     * Purpose : To get list of all employees from the database of employee payroll app
     *
     * @param params
     * @return
     */
    def list(GrailsParameterMap params) {
        params.max = params.max ?: GlobalConfig.itemsPerPage()
        List<Employee> listOfEmployees = Employee.createCriteria().list(params) {
            if (params?.colName && params?.colValue) {
                like(params.colName, "%" + params.colValue + "%")
            }
            if (!params.sort) {
                order("id", "desc")
            }
        }
        return [list: listOfEmployees, count: listOfEmployees.totalCount]
    }

    /**
     * Purpose : To delete data of employee from the database of employee payroll app
     *
     * @param employee is data which needs to be deleted
     * @return
     */
    def delete(Employee employee) {
        try {
            employee.delete(flush: true)
        } catch (Exception e) {
            println(e.getMessage())
            return false
        }
        return true
    }

}
