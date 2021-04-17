package ku.message.service;

import ku.message.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;


@Service
public class ProductService
{
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private JwtAccessTokenService tokenService;

	public List<Product> getAllProducts()
	{
		String token = tokenService.requestAccessToken();

		HttpHeaders headers = new HttpHeaders();
		headers.add("authorization", "Bearer " + token);
		HttpEntity entity = new HttpEntity(headers);

		String url = "http://localhost:8091/api/products";
		ResponseEntity<Product[]> response = restTemplate.exchange(url, HttpMethod.GET,	entity, Product[].class);
		Product[] products = response.getBody();

		return Arrays.asList(products);
	}

	public String createProduct(Product product)
	{
		String token = tokenService.requestAccessToken();

		HttpHeaders headers = new HttpHeaders();
		headers.add("authorization", "Bearer " + token);
		headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
		HttpEntity entity = new HttpEntity(product,headers);
		String url = "http://localhost:8091/api/products";

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

		return response.getBody();
	}
}