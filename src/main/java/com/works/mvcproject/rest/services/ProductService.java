package com.works.mvcproject.rest.services;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.works.mvcproject.rest.models.product.ProductByIDResult;
import com.works.mvcproject.rest.models.product.ProductIntegerResultList;
import com.works.mvcproject.rest.models.product.ProductIntegerResult;
import com.works.mvcproject.rest.models.product.ProductListResult;
import com.works.mvcproject.rest.models.product.ProductInsertPush;
import com.works.mvcproject.rest.models.product.ProductUpdatePush;
import com.works.mvcproject.rest.models.product.ResultRest;

@Service
public class ProductService {		
	
	public void insert(String title, int price, String detail, String img, int cid, int pstatu, int quantity)
	{
		String url = "http://localhost:7090/product/insert";
		ProductInsertPush productInsertPush = new ProductInsertPush();
		productInsertPush.setTitle(title);
		productInsertPush.setPrice(price);
		productInsertPush.setDetail(detail);
		productInsertPush.setImg(img);
		productInsertPush.setCid(cid);
		productInsertPush.setPstatu(pstatu);
		productInsertPush.setQuantity(quantity);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, productInsertPush, String.class);				
	}

	public List<ResultRest> productList()
	{
		String url = "http://localhost:7090/product/list";
		RestTemplate restTemplate = new RestTemplate();
		ProductListResult dataObj = restTemplate.getForObject(url, ProductListResult.class);
		List<ResultRest> productList = dataObj.getResult();
		
		return productList;
	}

	public int cartProductCount()
	{
		String url = "http://localhost:7090/product/cartProductCount";
		RestTemplate restTemplate = new RestTemplate();
		ProductIntegerResult dataObj = restTemplate.getForObject(url, ProductIntegerResult.class);
		int result = dataObj.getResult();
		
		return result;
	}
	
	public List<Integer> totalPriceOfProducts() 
	{
		String url = "http://localhost:7090/product/totalPriceOfProducts";
		RestTemplate restTemplate = new RestTemplate();
		ProductIntegerResultList dataObj = restTemplate.getForObject(url, ProductIntegerResultList.class);
		List<Integer> resultList = dataObj.getResult();
		
		return resultList;
	}
	
	public int cartTotalAmount()
	{
		String url = "http://localhost:7090/product/cartTotalAmount";
		RestTemplate restTemplate = new RestTemplate();
		ProductIntegerResult dataObj = restTemplate.getForObject(url, ProductIntegerResult.class);
		int result = dataObj.getResult();
		
		if(result >= 0)
		{
			return result;
		}
		else
		{
			return 0;
		}	
		
	}	
	
	public List<ResultRest> productsInCart() 
	{
		String url = "http://localhost:7090/product/productsInCart";
		RestTemplate restTemplate = new RestTemplate();
		ProductListResult dataObj = restTemplate.getForObject(url, ProductListResult.class);
		List<ResultRest> resultList = dataObj.getResult();
		
		return resultList;
	} 
	
	public List<ResultRest> findProductByCid(int cid) 
	{
		String url = "http://localhost:7090/product/findProductByCid?cid="+cid;
		RestTemplate restTemplate = new RestTemplate();
		ProductListResult dataObj = restTemplate.getForObject(url, ProductListResult.class);
		List<ResultRest> resultList = dataObj.getResult();
		
		return resultList;
	}
	
	public ResultRest findProductById(int pid) 
	{			
		String url = "http://localhost:7090/product/findProductById?pid="+pid;
		RestTemplate restTemplate = new RestTemplate();
		ProductByIDResult dataObj = restTemplate.getForObject(url, ProductByIDResult.class);
		ResultRest resultList = dataObj.getResult();
		return resultList;
	}	
	
	public int deleteSingle(int pid) 
	{
		String url = "http://localhost:7090/product/deleteSingle?pid="+pid;
		RestTemplate restTemplate = new RestTemplate();
		ProductIntegerResult dataObj = restTemplate.getForObject(url, ProductIntegerResult.class);
		int result = dataObj.getResult();
		
		return result;
	}
	
	public void updateSingle(int pid,String title, int price, String detail, String img, int cid, int pstatu, int quantity)
	{
		String url = "http://localhost:7090/product/updateSingle";
		ProductUpdatePush productUpdatePush = new ProductUpdatePush();
		productUpdatePush.setPid(pid);
		productUpdatePush.setTitle(title);
		productUpdatePush.setPrice(price);
		productUpdatePush.setDetail(detail);
		productUpdatePush.setImg(img);
		productUpdatePush.setCid(cid);
		productUpdatePush.setPstatu(pstatu);
		productUpdatePush.setQuantity(quantity);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, productUpdatePush, String.class);
	}		
	
}