 package com.booking.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.booking.utility.CommonUtility;

@Controller
public class PackageController {

	@Autowired
	private PackageService packageService;
	
	private CommonUtility commonUtility;
	
	@RequestMapping(value = "/admin/addPackage", method = RequestMethod.GET)
	public String addPackage(@ModelAttribute("addPackage") Packages packages, Model model, HttpSession session, BindingResult result, HttpServletRequest request) throws MalformedURLException {
		//System.out.println(commonUtility.getURLBase(request.getRequestURL().toString()));
		return "admin/addPackage";
	}
	
	@RequestMapping(value = "/admin/addPackage", method = RequestMethod.POST)
	public void addPackage(@Valid @ModelAttribute("addPackage") Packages packages, Model model, BindingResult result, HttpServletResponse response) throws IOException {
		
		if(result.hasErrors()) {
			response.sendRedirect("addPackage.html");
		}
		packageService.save(packages);
		
		response.sendRedirect("/admin/listPackages.html");
	}
	
	@RequestMapping(value = "/admin/deletePackage/{id}", method = RequestMethod.GET)
	public void deletePackage(@PathVariable("id") Long id, Model model, HttpSession session, HttpServletResponse response) throws IOException {
		packageService.delete(id);
		response.sendRedirect("/admin/listPackages.html");
	}
	
	@RequestMapping(value = "/admin/listPackages", method = RequestMethod.GET)
	public String listPackages(Model model, HttpSession session) {
		List<Packages> packageList = packageService.findAllPackages();
		model.addAttribute("packageList", packageList);
		return "/admin/listPackages";
	}
	
	@RequestMapping(value = "/admin/editPackage/{id}", method = RequestMethod.GET)
	public String editPackage(@PathVariable("id") Long id, @ModelAttribute("editPackage") Packages packages, Model model, HttpSession session) {
		packages = packageService.findPackage(id);
		System.out.println(packages);
		model.addAttribute("packages", packages);
		return "/admin/editPackage";
	}
	
	@RequestMapping(value = "/admin/editPackage/{id}", method = RequestMethod.POST)
	public String editPackage(@PathVariable("id") Long id, @ModelAttribute("editPackage") Packages packages, Model model, HttpSession session, BindingResult result, HttpServletResponse response) throws IOException {
		if(result.hasErrors()) {
			response.sendRedirect("/admin/editPackage/"+id+".html");
		}
		packageService.save(packages);
		
		response.sendRedirect("/admin/listPackages.html");
		return "editPackage";
	}
	
	
}
