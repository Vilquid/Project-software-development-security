package ku.message.controller;

import ku.message.service.JwtAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/product")
public class ProductController
{
	@Autowired
	private JwtAccessTokenService jwtService;

	@GetMapping
	public String getProductPage(Model model)
	{
		String jwtResponse = jwtService.requestAccessToken();
		System.out.println("Token: " + jwtResponse);

		return "product";
	}
}
