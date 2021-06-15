package rw.ac.rca.classcSoap.soap.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import rw.ac.rca.classc.courses.CourseDetails;
import rw.ac.rca.classc.courses.GetAllCourseDetailsRequest;
import rw.ac.rca.classc.courses.GetAllCourseDetailsResponse;
import rw.ac.rca.classc.courses.GetCourseDetailsRequest;
import rw.ac.rca.classc.courses.GetCourseDetailsResponse;
import rw.ac.rca.classcSoap.soap.bean.Course;
import rw.ac.rca.classcSoap.soap.repository.ICourseRepository;

@Endpoint
public class CourseDetailsEndPoint {

	@Autowired
	private ICourseRepository courseRepository;

	// method
	// request ---- GetCourseDetailsRequest
	// response --- GetCourseDetailsResponse
	@PayloadRoot(namespace = "http://rca.ac.rw/classc/courses", localPart = "GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse findById(@RequestPayload GetCourseDetailsRequest request) {

		Course course = courseRepository.findById(request.getId()).get();

		GetCourseDetailsResponse courseDetailsResponse = mapCourseDetails(course);
		return courseDetailsResponse;
	}

	@PayloadRoot(namespace = "http://rca.ac.rw/classc/courses", localPart = "GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse findAll(@RequestPayload GetAllCourseDetailsRequest request) {

		GetAllCourseDetailsResponse allCourseDetailsResponse = new GetAllCourseDetailsResponse();
		List<Course> courses = courseRepository.findAll();
		for (Course course : courses) {
			GetCourseDetailsResponse courseDetailsResponse = mapCourseDetails(course);
			allCourseDetailsResponse.getCourseDetails().add(courseDetailsResponse.getCourseDetails());
		}

		return allCourseDetailsResponse;
	}

	private GetCourseDetailsResponse mapCourseDetails(Course course) {
		CourseDetails courseDetails = mapCourse(course);

		GetCourseDetailsResponse courseDetailsResponse = new GetCourseDetailsResponse();

		courseDetailsResponse.setCourseDetails(courseDetails);
		return courseDetailsResponse;
	}

	private CourseDetails mapCourse(Course course) {
		CourseDetails courseDetails = new CourseDetails();
		courseDetails.setDescription(course.getDescription());
		courseDetails.setId(course.getId());
		courseDetails.setName(course.getName());
		return courseDetails;
	}

}
