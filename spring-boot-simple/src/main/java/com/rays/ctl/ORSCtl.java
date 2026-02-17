package com.rays.ctl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.ORSResponse;
import com.rays.dto.TestDTO;


/**
 * This is a controller used to test response from ORSResponse
 * @author Nidhi
 *
 */
@RestController
@RequestMapping(value="ORS")
public class ORSCtl {
	
	@GetMapping
	public ORSResponse display() {
		ORSResponse res=new ORSResponse();
		return res;
	}
	
	
	/**
	 * Send test error message
	 * @return
	 */
	@GetMapping("display1")
	public ORSResponse display1() {
		ORSResponse res=new ORSResponse();
		res.addMessage("Invalid login and password");
		return res;
	}
	
	/**
	 * Send test login validations using addInputErrors
	 * @return
	 */
	@GetMapping("display2")
	public ORSResponse display2() {
		ORSResponse res=new ORSResponse();
		Map<String,String> errors=new HashMap<String,String>();
		errors.put("firstname","first name is required");
		errors.put("lastname","last name is required");
		errors.put("login","login id is required");
		errors.put("password","password is required");
		res.addInputError(errors);
		return res;
	}
	
	
	/**
	 * SEnd test list of bean using addData
	 * @return
	 */
	@GetMapping("display3")
	public ORSResponse display3() {
		ORSResponse res=new ORSResponse();
		
		List list=new ArrayList();
		
		TestDTO dto1=new TestDTO();
		dto1.setFirstName("Nidhi");
		dto1.setLastName("Agrawal");
		dto1.setLogin("nidhi@gmail.com");
		dto1.setPassword("nidhi123");
		dto1.setAddress("Indore");
		
		TestDTO dto2=new TestDTO();
		dto2.setFirstName("YAsh");
		dto2.setLastName("Agrawal");
		dto2.setLogin("yash@gmail.com");
		dto2.setPassword("yash123");
		dto2.setAddress("USA");
		
		list.add(dto1);
		list.add(dto2);
		
		res.addData(list);
		
		return res;
				
	}
	
	
	/**
	 * Send test role list using addResult
	 * @return
	 */
	@GetMapping("display4")
	public ORSResponse display4() {
		ORSResponse res=new ORSResponse();
		List roleList=new ArrayList();
		
		roleList.add("Admin");
		roleList.add("Student");
		roleList.add("College");
		roleList.add("Faculty");
		
		res.addResult("roleList", roleList);
		return res;
	}
}
