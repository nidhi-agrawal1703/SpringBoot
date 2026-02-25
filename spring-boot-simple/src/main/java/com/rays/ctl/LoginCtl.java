package com.rays.ctl;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.UserDTO;
import com.rays.form.LoginForm;
import com.rays.service.UserService;

@RestController
@RequestMapping(value="Auth")
public class LoginCtl extends BaseCtl{
	
	@Autowired
	UserService userService;
	
	
	@PostMapping("login")
	public ORSResponse login(@RequestBody @Valid LoginForm form,BindingResult bindingResult,HttpSession session) {
		
		ORSResponse res=new ORSResponse();
		
		res=validate(bindingResult);
		
		if(!res.isSuccess()) {
			return res;
		}
		
		UserDTO dto=new UserDTO();
		
		dto=userService.authenticate(form.getLoginId(), form.getPassword());
		
		if(dto!=null) {
			session.setAttribute("user", dto);
			res.setSuccess(true);
			res.addMessage("User Login Successfully");
			res.addData(dto);
		}		
		return res;
	}
	
	@PostMapping("logout")
	public ORSResponse login(HttpSession session) {
		
		ORSResponse res=new ORSResponse();
		
		session.invalidate();
		
		res.addMessage("User Logout Successfully");
		
		return res;
	}

}
