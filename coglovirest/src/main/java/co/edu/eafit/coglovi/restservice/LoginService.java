package co.edu.eafit.coglovi.restservice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners/{ownerId}/**")
public class LoginService {
	
//	@Autowired
//	private ExampleCommand exampleCommand;

	@RequestMapping("/pets/{petId}")
	public void findPet(@PathVariable String ownerId,@PathVariable String petId, Model model) {
		System.out.println("entro");
	}

}