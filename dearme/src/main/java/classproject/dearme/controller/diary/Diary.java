package classproject.dearme.controller.diary;

import classproject.dearme.dto.diary.DiaryInfoDto;
import classproject.dearme.response.Response;
import classproject.dearme.service.diary.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class Diary {

	private final DiaryService diaryService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Response save(@RequestBody DiaryInfoDto diaryInfoDto) {
		return Response.success(diaryService.save(diaryInfoDto));
	}

	@GetMapping("/{username}")
	@ResponseStatus(HttpStatus.OK)
	public Response getDiaryInfo(@PathVariable String username) {
		return Response.success(diaryService.findAll(username));
	}

	@PatchMapping
	@ResponseStatus(HttpStatus.OK)
	public Response updateDiaryInfo(@RequestBody DiaryInfoDto diaryInfoDto) {
		return Response.success(diaryService.update(diaryInfoDto));
	}
}
