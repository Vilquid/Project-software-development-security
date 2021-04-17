package ku.message.controller;

import ku.message.model.Product;
import ku.message.service.JwtAccessTokenService;
import ku.message.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/product")
public class ProductController
{
	@Autowired
	private ProductService productService;

	@GetMapping
	public String getProductPage(Model model)
	{
		model.addAttribute("products", productService.getAllProducts());

		return "product";
	}

	@GetMapping("/add")
	public String getAddProductPage(Model model)
	{
		return "product-add";
	}

	@PostMapping
	public String createProduct(@ModelAttribute Product product, Model model)
	{
		productService.createProduct(product);

		return "redirect:/product";
	}

}
