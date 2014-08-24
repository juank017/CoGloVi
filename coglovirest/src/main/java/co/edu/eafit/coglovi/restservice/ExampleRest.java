package co.edu.eafit.coglovi.restservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class ExampleRest {


	

	@RequestMapping("/person")
	@ResponseBody
	public void randomPerson(@RequestParam(value="name", required=false, defaultValue="World") String name) {
		
		return;
	}

	@RequestMapping("person/{id}")
	@ResponseBody
	public String getById(@PathVariable String id) {
		return id;
	}

	/* same as above method, but is mapped to
	 * /api/person?id= rather than /api/person/{id}
	 */
	@RequestMapping(value="person", params="id")
	@ResponseBody
	public String getByIdFromParam(@RequestParam Long id) {
		return "fdsfsd"+id;
	}

	/**
	 * Saves new person. Spring automatically binds the name
	 * and age parameters in the request to the person argument
	 * @param person
	 * @return String indicating success or failure of save
	 */
	@RequestMapping(value="person", method=RequestMethod.POST)
	@ResponseBody
	public String savePerson(String person) {
		return "Saved person: " + person.toString();
	}
	
	
}