package fr.eservices.week402.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Annotation to expose this controller
@Controller
public class HelloController {
	
	// Annotation to map this method to /hello !
	@GetMapping(path="/hello")
	public String sayHello(
			Model model,
			@RequestParam
			String name
	) {
		String message = "Hello " + name + " !";
		// pass message to view ...
		model.addAttribute("message", message);
		return "sample";
	}

}
