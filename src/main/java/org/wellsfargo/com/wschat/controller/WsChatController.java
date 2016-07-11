package org.wellsfargo.com.wschat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.wellsfargo.com.wschat.model.FileUpload;
import org.wellsfargo.com.wschat.model.Greeting;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class WsChatController {
	@RequestMapping(value = "/greetings", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getGreetingPage(Model model) {
		model.addAttribute("myGreeting", new Greeting());
		return new ModelAndView("nodeChat", "response", new Greeting());
	}

	@RequestMapping(value = "/chat/greetings", method = RequestMethod.POST)
	@ResponseBody
	public  ModelAndView getGreetingResponse(@RequestParam HttpRequest req) throws IOException {
		/*
		 * String json = "{" + "  \"object \":  \"msg \" ," + " \"entry \": [" +
		 * "  {" + " \"messaging \": [" + "          {" + " \"message \": {" +
		 * "  \"text \": " + req.get + "" + "           }" + "      }" + "  ]" +
		 * "  }]} \")";
		 */

		// JSONObject request = new JSONObject();
		// request.put("chat", greet.getChat());
		// ObjectMapper mapper = new ObjectMapper();
		// Map<String, Object> map = new HashMap<String, Object>();
		// map = mapper.readValue(request.toString(),new
		// TypeReference<Map<String, String>>() { });

		/*
		 * RestTemplate restTemplate = new RestTemplate(); String url =
		 * "http://localhost:8080/SpringMVCJson/countries"; String response =
		 * restTemplate.postForObject(url, greet.getChat(), String.class);
		 * model.addAttribute("chat", greet.getChat()); return new
		 * ModelAndView("nodeChat", response, new Greeting());
		 */

		RestTemplate restTemplate = new RestTemplate();
		String urlString = "https://nodewitclient.herokuapp.com/callwit";
		// JSONObject request = new JSONObject(json);
		// restTemplate.getMessageConverters().add(new
		// MappingJacksonHttpMessageConverter());

		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("cache-control", "no-cache");
		headers.add("postman-token", "89cf831c-86f0-946d-d1ce-d01c044c1398");

		// headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		HttpEntity<String> entity = new HttpEntity<String>(req.toString(), headers);
		System.out.println(entity);
		ResponseEntity<String> response = restTemplate.exchange(urlString, HttpMethod.PUT, entity, String.class);
		System.out.println(response);
		if (response.getStatusCode() == HttpStatus.OK) {
			String userJson = response.getBody();
		} else if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
			// nono... bad credentials
		}
		// check the response, e.g. Location header, Status, and body
		response.getHeaders().getLocation();
		response.getStatusCode();
		String responseBody = response.getBody();

		//model.addAttribute("chat", greet.getChat());
		// String response = restTemplate.postForObject(urlString,
		// greet.getChat(), String.class);

		return new ModelAndView("nodeChat", responseBody, new Greeting());
	}

	@RequestMapping(value = "/chat/fileUpload", method = RequestMethod.POST)
	public @ResponseBody ModelAndView uploadFileHandler(@ModelAttribute FileUpload pUploadDTO) {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
		mvm.add("name", pUploadDTO.getFile().getName());
		mvm.add("file", new FileSystemResource(pUploadDTO.getFile()));
		// mvm.add("file", pUploadDTO.getFile()); // MultipartFile
		String url = "http://localhost:8080/SpringMVCJson/upload";
		String result = restTemplate.postForObject(url, mvm, String.class);
		return new ModelAndView("chatResponse", result, new Greeting());
	}

}
