package com.nguyenthao.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nguyenthao.domain.Customer;
import com.nguyenthao.repository.CustomerJpaRepository;

@Controller
public class CustomerController {
	@Autowired
	CustomerJpaRepository customerJpaRepository;

	@GetMapping("/")
	public String index() {
		return "login";
	}

	@PostMapping("/")
	public String login(HttpServletRequest request, Model model) {
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		if (name.equals("nguyenthao") && pass.equals("123")) {
			return "redirect:/customer";
		}
		return "login";
	}

	@GetMapping("/customer")
	public String list(Model model) {
		model.addAttribute("customers", customerJpaRepository.findAll(new Sort(Sort.Direction.ASC, "id")));
		return "list";
	}
	
	@GetMapping("/customer/create")
	public String create(Model model) {
		model.addAttribute("customer", new Customer());
		return "form";
	}

	@GetMapping("/customer/{id}/edit")
	public String edit(@PathVariable int id, Model model) {
		model.addAttribute("customer", customerJpaRepository.findOne(id));
		return "form";
	}

	@PostMapping("/customer/save")
	public String save(@Valid Customer customer, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "form";
		}
		customerJpaRepository.save(customer);
		redirect.addFlashAttribute("success", "Saved customer successfully!");
		return "redirect:/customer";
	}

	@GetMapping("/customer/{id}/delete")
	public String delete(@PathVariable int id, RedirectAttributes redirect) {
		customerJpaRepository.delete(id);
		redirect.addFlashAttribute("success", "Deleted customer successfully!");
		return "redirect:/customer";
	}

	@GetMapping("/customer/search-name")
	public String searchName(@RequestParam("q") String name, Model model) {
		if (name.equals("")) {
			return "redirect:/customer";
		}
		model.addAttribute("customers", customerJpaRepository.findByNameContaining(name));
		return "list";
	}

//	@GetMapping("/customer/search-address")
//	public String searchAddress(@RequestParam("q") String address, Model model) {
//		if (address.equals("")) {
//			return "redirect:/customer";
//		}
//		model.addAttribute("customers", customerJpaRepository.findByAddressContaining(address));
//		return "list";
//	}
//
//	@GetMapping("/customer/search-email")
//	public String searchEmail(@RequestParam("q") String email, Model model) {
//		if (email.equals("")) {
//			return "redirect:/customer";
//		}
//		model.addAttribute("customers", customerJpaRepository.findByEmailContaining(email));
//		return "list";
//	}
//
//	@GetMapping("/customer/search-phone")
//	public String searchPhone(@RequestParam("q") String phone, Model model) {
//		if (phone.equals("")) {
//			return "redirect:/customer";
//		}
//		model.addAttribute("customers", customerJpaRepository.findByPhoneContaining(phone));
//		return "list";
//	}
}
