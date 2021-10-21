package my.company.controllers;

import my.company.entities.Customer;
import my.company.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    //@GetMapping(name = "/list")
    public String listCustomers(Model model)   {
        List<Customer> theCustomers = customerService.getCustomers();
        model.addAttribute("customers", theCustomers);
        return "list-customers";
    }

    @RequestMapping(value = "/showFormForAdd", method = RequestMethod.GET)
    public String showFormForAdd(Model model)  {
        Customer theCustomer = new Customer();
        model.addAttribute("customer", theCustomer);
        return "customer-add-form";
    }

    @RequestMapping(value = "/saveCustomer", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)    {
        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";
    }

    @RequestMapping(value = "/showFormForUpdate", method = RequestMethod.GET)
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel)  {
        //Get customer from DB
        Customer theCustomer = customerService.getCustomer(theId);
        //set customer as model to pre-populate the form
        theModel.addAttribute("customer", theCustomer);
        //send over to the form
        return "customer-add-form";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String customerDelete(@RequestParam("customerId") int theId)  {
        customerService.deleteCustomer(theId);
        return "redirect:/customer/list";
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model theModel) {
        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";
    }



}
