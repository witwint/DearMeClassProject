package classproject.dearme.controller.timecapsule;

import classproject.dearme.dto.timecapsule.TimeCapsuleInfoDto;
import classproject.dearme.response.Response;
import classproject.dearme.service.timecapsule.TimeCapsuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timecapsule")
public class TimeCapsuleController {


	private final TimeCapsuleService timeCapsuleService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Response saveTimeCapsule(@RequestBody TimeCapsuleInfoDto timeCapsuleInfoDto) {
		return Response.success(timeCapsuleService.save(timeCapsuleInfoDto));
	}

	@GetMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Response getTimeCapsuleInfo(@PathVariable String username) {
		return Response.success(timeCapsuleService.findAll(username));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Response deleteTimeCapsule(@PathVariable Long id) {
		return Response.success(timeCapsuleService.delete(id));
	}

}
