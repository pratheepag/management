 package com.booking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.booking.model.Packages;
import com.booking.service.PackageService;

@Controller
public class PackageController {

	@Autowired
	private PackageService packageService;
	
	@RequestMapping(value = "addPackage", method = RequestMethod.GET)
	public String addPackage(@ModelAttribute("addPackage") Packages packages, Model model, HttpSession session, BindingResult result) {
		return "addPackage";
	}
	
	@RequestMapping(value = "addPackage", method = RequestMethod.POST)
	public void addPackage(@Valid @ModelAttribute("addPackage") Packages packages, Model model, BindingResult result, HttpServletResponse response) throws IOException {
		
		if(result.hasErrors()) {
			response.sendRedirect("addPackage.html");
		}
		packageService.save(packages);
		
		response.sendRedirect("listPackages.html");
	}
	
	@RequestMapping(value = "deletePackage/{id}", method = RequestMethod.GET)
	public void deletePackage(@PathVariable("id") Long id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
		packageService.delete(id);
		response.sendRedirect("listPackages.html");
	}
	
	@RequestMapping(value = "listPackages", method = RequestMethod.GET)
	public String listPackages(Model model, HttpSession session) {
		List<Packages> packageList = packageService.findAllPackages();
		model.addAttribute("packageList", packageList);
		return "listPackages";
	}
	
	@RequestMapping(value = "editPackage/{id}", method = RequestMethod.GET)
	public String editPackage(@PathVariable("id") Long id, @ModelAttribute("editPackage") Packages packages, Model model, HttpSession session) {
		packages = packageService.findPackage(id);
		System.out.println(packages);
		model.addAttribute("packages", packages);
		return "editPackage";
	}
	
	@RequestMapping(value = "editPackage/{id}", method = RequestMethod.POST)
	public String editPackage(@PathVariable("id") Long id, @ModelAttribute("editPackage") Packages packages, Model model, HttpSession session, BindingResult result, HttpServletResponse response) throws IOException {
		if(result.hasErrors()) {
			response.sendRedirect("editPackage/"+id+".html");
		}
		packageService.save(packages);
		
		response.sendRedirect("listPackages.html");
		return "editPackage";
	}
}
