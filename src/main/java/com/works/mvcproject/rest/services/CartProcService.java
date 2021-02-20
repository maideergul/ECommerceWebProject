package com.works.mvcproject.rest.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.works.mvcproject.rest.models.cartproc.CartProcRest;
import com.works.mvcproject.rest.models.cartproc.ResultRest;

@Service
public class CartProcService {

	public List<ResultRest> restResult()
	{
		String url = "http://localhost:7090/cartProc/getCart";
		RestTemplate restTemplate = new RestTemplate();
		CartProcRest dataObj = restTemplate.getForObject(url, CartProcRest.class);			
		List<ResultRest> resultList = dataObj.getResult();
		
		return resultList;		
	}
	
}