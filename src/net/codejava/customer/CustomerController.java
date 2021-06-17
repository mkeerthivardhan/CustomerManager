package net.codejava.customer;
 
import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
 
@Controller
public class CustomerController {
	@Autowired
	public CustomerService service;
	
    @RequestMapping("/")
    public ModelAndView home() {
    	ModelAndView mav=new ModelAndView("index");
    	
    	List<Customer> listCustomer = service.listAll();
    	mav.addObject("listCustomer",listCustomer);
    	return mav;
    }
 
    @RequestMapping("/new")
    public String newCustomerForm(Map<String, Object> model) {
        Customer customer=new Customer();
        model.put("customer",customer);
        return "new_customer";
    }
    
 @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
    	service.save(customer);
        return "redirect:/";
    }
   /* @RequestMapping(value ="/save" ,method=RequestMethod.POST)
	public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer){
		
		getCustomerService().save(customer);
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("customer", customer);
		return modelAndView;
    }
   */
    /*public CustomerService getCustomerService() {
		// TODO Auto-generated method stub
		return service;
	}
*/
	@RequestMapping("/edit")
    public ModelAndView editCustomerForm(@RequestParam long id) {
        ModelAndView mav = new ModelAndView("edit_customer");
        Customer customer = service.get(id);
        mav.addObject("customer", customer);
     
        return mav;
    }
    
    @RequestMapping("/delete")
    public String deleteCustomerForm(@RequestParam long id) {
        service.delete(id);
        return "redirect:/";       
    }
    @RequestMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Customer> result = service.search(keyword);
        ModelAndView mav = new ModelAndView("search");
        mav.addObject("result", result);
     
        return mav;    
    }
    
}