/**
 * Purpose : To create controller for employee class
 */
package com.bridgelabz.epa

class EmployeeController {

    static boolean departmentHR =false
    static boolean departmentSales = false
    static boolean departmentFinance = false
    static boolean departmentEngineer = false
    static boolean departmentOthers = false

    EmployeeService employeeService

    /**
     * Purpose : To collect the data from the service layer i.e collect the list of employee
     *            and return it on UI
     * @return
     */
    def index() {
        println("Inside index")
        def response = employeeService.list(params)
        [employeeList: response.list, total:response.count]
    }

    /**
     * Purpose : To collect data of particular employee with the help of id
     *
     * @param id of the particular employee
     * @return data of that employee
     */
    def details(Integer id) {
        println("Inside get Employee by ID = "+id)
        def response = employeeService.getById(id)
        if (!response){
            redirect(controller: "employee", action: "index")
        }else{
            [employee: response]
        }
    }

    /**
     * Purpose : TO redirect to params which will be used in save method
     * @return
     */
    def create(){
        println("Inside create")
        [employee: flash.redirectParams]
    }

    /**
     * Purpose : To save the data if response is valid and it has no errors and redirect to index
     *           if it has errors it will be redirected to create page
     *
     * @return
     */
    def save() {
        println("Inside save")
        def response = employeeService.save(params)
        if (!response.isSuccess) {
            flash.redirectParams = response.model
            redirect(controller: "employee", action: "create")
        }else{
            redirect(controller: "employee", action: "index")
        }
    }

    /**
     * Purpose : To get the data of the employee with the particular id and then redirect to index page
     *
     * @param id whose data that needs to be fetched
     * @return
     */
    def edit(Integer id) {
        println("Inside edit")
        if (flash.redirectParams) {
            [employee: flash.redirectParams]
        } else {
            def response = employeeService.getById(id)
            if (!response) {
                flash.message = AppUtil.infoMessage(g.message(code: "invalid.entity"), false)
                redirect(controller: "employee", action: "index")
            } else {
                for(item in response.getDepartment()){
                    println item
                    if(item.equalsIgnoreCase("HR")){
                        departmentHR = true
                    }
                    if(item.equalsIgnoreCase("Sales")){
                        departmentSales = true
                    }
                    if(item.equalsIgnoreCase("Finance")){
                        departmentFinance = true
                    }
                    if(item.equalsIgnoreCase("Engineer")){
                        departmentEngineer = true
                    }
                    if(item.equalsIgnoreCase("Others")){
                        departmentOthers = true
                    }
                }
                [employee: response, departmentHR: departmentHR, departmentSales: departmentSales,
                 departmentFinance: departmentFinance,departmentEngineer: departmentEngineer, departmentOthers: departmentOthers]
            }
        }
    }

    /**
     * Purpose : To Update the data of the employee with the help of service layer method
     *
     * @return
     */
    def update() {
        println("Inside update")
        def response = employeeService.getById(params.id)
        print("Update = "+response.toString())
        if (!response){
            flash.message = AppUtil.infoMessage(g.message(code: "invalid.entity"), false)
            redirect(controller: "employee", action: "index")
        }else{
            response = employeeService.update(response, params)
            if (!response.isSuccess){
                flash.redirectParams = response.model
                flash.message = AppUtil.infoMessage(g.message(code: "unable.to.update"), false)
                redirect(controller: "employee", action: "edit")
            }else{
                flash.message = AppUtil.infoMessage(g.message(code: "updated"))
                redirect(controller: "employee", action: "index")
            }
        }
    }

    /**
     * Purpose : To delete data of employee by using the service layer delete method
     *
     * @param id is employee id of the employee whose data needs to be deleted
     * @return
     */
    def delete(Integer id) {
        println("Inside delete Employee by ID = "+id)
        def response = employeeService.getById(id)
        if (!response){
            flash.message = AppUtil.infoMessage(g.message(code: "invalid.entity"), false)
            redirect(controller: "employee", action: "index")
        }else{
            response = employeeService.delete(response)
            if (!response){
                flash.message = AppUtil.infoMessage(g.message(code: "unable.to.delete"), false)
            }else{
                flash.message = AppUtil.infoMessage(g.message(code: "deleted"))
            }
            redirect(controller: "employee", action: "index")
        }
    }
}
