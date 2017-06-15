package ServiceClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("faculty")
public interface FacultyClient {

	@RequestMapping("faculty/{university}")
	public String getUniversityInfo(@PathVariable String university);
	
	@RequestMapping("faculty/{university}/{faculty}")
	public String getFacultyInfo(@PathVariable String university,@PathVariable String faculty);
		
}
