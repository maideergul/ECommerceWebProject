package com.works.mvcproject.rest.services;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.works.mvcproject.rest.models.category.CategoryByIDResult;
import com.works.mvcproject.rest.models.category.CategoryInsertPush;
import com.works.mvcproject.rest.models.category.CategoryResultList;
import com.works.mvcproject.rest.models.category.CategoryIntegerResult;
import com.works.mvcproject.rest.models.category.CategoryUpdatePush;
import com.works.mvcproject.rest.models.category.ResultRest;

@Service
public class CategoryService {

	public List<ResultRest> categoryList()
	{
		String url = "http://localhost:7090/category/list";
		RestTemplate restTemplate = new RestTemplate();
		CategoryResultList dataObj = restTemplate.getForObject(url, CategoryResultList.class);
		List<ResultRest> resultList = dataObj.getResult();
		
		return resultList;
	}
	
	public void insert(String categoryname, String iconPath)
	{
		String url = "http://localhost:7090/category/insert";
		CategoryInsertPush categoryInsertPush = new CategoryInsertPush();
		categoryInsertPush.setCategoryname(categoryname);
		categoryInsertPush.setIconPath(iconPath);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, categoryInsertPush, String.class);		
	}
	
	public ResultRest findCategoryById(int cid) 
	{			
		String url = "http://localhost:7090/category/findCategoryById?cid="+cid; 
		RestTemplate restTemplate = new RestTemplate();
		CategoryByIDResult dataObj = restTemplate.getForObject(url, CategoryByIDResult.class);
		ResultRest result = dataObj.getResult();
		
		return result;
	}
	
	public void deleteSingle(int cid)
	{
		String url = "http://localhost:7090/category/deleteSingle";
		
		CategoryUpdatePush cat = new CategoryUpdatePush();
		cat.setCid(cid);
		
		HttpEntity<CategoryUpdatePush> request = new HttpEntity<CategoryUpdatePush>(cat);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
	}
	
	public void updateSingle(int cid,String categoryname, String iconPath)
	{
		String url = "http://localhost:7090/category/updateSingle";
		CategoryUpdatePush categoryUpdatePush = new CategoryUpdatePush();
		categoryUpdatePush.setCid(cid);
		categoryUpdatePush.setCategoryname(categoryname);
		categoryUpdatePush.setIconPath(iconPath);
		
		HttpEntity<CategoryUpdatePush> request = new HttpEntity<CategoryUpdatePush>(categoryUpdatePush);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
	}
	
	public List<ResultRest> search(String data)
	{
		String url = "http://localhost:7090/category/search?data="+data;
		RestTemplate restTemplate = new RestTemplate();
		CategoryResultList dataObj = restTemplate.getForObject(url, CategoryResultList.class);
		List<ResultRest> resultList = dataObj.getResult();
		
		return resultList;				
	}
}