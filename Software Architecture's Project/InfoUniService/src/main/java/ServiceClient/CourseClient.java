package ServiceClient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("course")
public interface CourseClient {
	
	@RequestMapping("course/{university}/{faculty}")
	public String getFacultyInfo(@PathVariable String faculty);

	@RequestMapping("course/{university}/{faculty}/{course}")
	public String getCourseInfo(@PathVariable String faculty,@PathVariable String course);
}
