package com.works.mvcproject.rest.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.works.mvcproject.rest.models.productcidview.ProductCidViewRest;
import com.works.mvcproject.rest.models.productcidview.ResultRest;

@Service
public class ProductCidViewService {

	public List<ResultRest> restResult()
	{
		String url = "http://localhost:7090/productCidView/list";
		RestTemplate restTemplate = new RestTemplate();
		ProductCidViewRest dataObj = restTemplate.getForObject(url, ProductCidViewRest.class);			
		List<ResultRest> resultList = dataObj.getResult();
		
		return resultList;		
	}
	
	public List<ResultRest> search(String data)
	{
		String url = "http://localhost:7090/productCidView/searchData?data="+data;
		RestTemplate restTemplate = new RestTemplate();
		ProductCidViewRest dataObj = restTemplate.getForObject(url, ProductCidViewRest.class);
		List<ResultRest> resultList = dataObj.getResult();
		
		return resultList;				
	}
	
}