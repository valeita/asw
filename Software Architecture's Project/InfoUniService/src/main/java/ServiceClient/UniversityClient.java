package ServiceClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("university")
public interface UniversityClient {

	@RequestMapping("university/{university}")
	public String getUniversityInfo(@PathVariable String university);
}
