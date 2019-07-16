package start.filters;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prova")
public class prova {
	
	@RequestMapping("/bo")
	public String printella(@RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit) {
		
		return("get users was called with page = " + page + "and limit " + limit);


	}
}
