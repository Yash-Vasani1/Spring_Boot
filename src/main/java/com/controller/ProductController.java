package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.AddressEntity;
import com.entity.Product;
import com.entity.UserEntity;
import com.repository.AddressRepository;
import com.repository.ProductRepository;
import com.repository.UserRepository;

@RestController
public class ProductController {
	
	@Autowired
	ProductRepository product_repo; // ctrl + shift + o // how can we make an object of an interface
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AddressRepository addressRepo;
	
	@PostMapping("/addproduct")
	public Product AddProduct(@RequestBody Product item)
	{
		product_repo.save(item);
		return item;
	}
	
	@GetMapping("/showallproduct")
	public List<Product> ListAllProduct()
	{
		return product_repo.findAll();
	}
	
	@GetMapping("/getproductbyid/{id}")
	public Product GetProductById(@PathVariable("id") Integer id)
	{
		Optional<Product> opt = product_repo.findById(id);
		if(opt.isEmpty())
			return null;
		else
			return opt.get();
	}
	
	@GetMapping("/getproductbyname/{name}")
	public List<Product> GetProductByName(@PathVariable("name") String name)
	{
		return product_repo.findByName(name);
	}
	
	@DeleteMapping("/deleteproduct/{id}")
	public Product DeleteProductById(@PathVariable("id") Integer id)
	{
		
		Product item = GetProductById(id);
				//product_repo.findById(id).get();
		product_repo.deleteById(id);
		return item;
	}
	@PutMapping("/updateproduct_details")
	public Product UpdateProductDetails(@RequestBody Product item)
	{
		product_repo.save(item);
		return item;
	}
	
	
	
	
//	*******************************************************************************
	
	
	
	
	// 1) Add User and Address using UserTable(has Address entity object)
	
	@PostMapping("/addUser")
	public UserEntity AddUser(@RequestBody UserEntity user)
	{
//		UserEntity tuser = user.getAddress();
//		AddressEntity taddress = user.getAddress();
//		taddress.setUser(user);
		addressRepo.save(user.getAddress());
		userRepo.save(user);
		return user;
	}
	
	// 2) Get User by ID(id will taken from url)
	@GetMapping("/getuser/{id}")
	public UserEntity GetUserById(@PathVariable Integer id)
	{
		System.out.println("id: " + id);
		Optional<UserEntity> opt = userRepo.findById(id);
		System.out.println("opt: " + opt);
//		return user;
		if(opt.isPresent()) return opt.get();
//		return userRepo.findById(id).get();
		return null;
	}
	
	@GetMapping("/getallusers")
	public List<UserEntity> GetAllUsers()
	{
		return userRepo.findAll();
	}
//	**********************************************************************************
	@PostMapping("/addAddress")
	public AddressEntity addAddress(@RequestBody AddressEntity addressEntity) {
		userRepo.save(addressEntity.getUser());
		addressRepo.save(addressEntity);
		return addressEntity;
	}
}