package classproject.dearme.service.diary;

import classproject.dearme.domain.diary.Diary;
import classproject.dearme.domain.user.User;
import classproject.dearme.dto.diary.DiaryRequest;
import classproject.dearme.dto.diary.DiaryResponse;
import classproject.dearme.repository.diary.DiaryRepository;
import classproject.dearme.repository.user.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiaryService {

	private final DiaryRepository diaryRepository;
	private final UserRepository userRepository;

	@Transactional
	public DiaryResponse save(DiaryRequest diaryRequest) {
		User user = userRepository.findByUsername(diaryRequest.getUsername());
		Diary diary = new Diary(diaryRequest.getCoordinateX(), diaryRequest.getCoordinateY(),
			diaryRequest.getImageType(), diaryRequest.getTitle(), diaryRequest.getColor(), user);
		diaryRepository.save(diary);
		return DiaryResponse.toDto(diary);
	}

	@Transactional
	public DiaryResponse update(DiaryRequest diaryRequest, Long diaryId) {
		Diary findDiary = diaryRepository.getReferenceById(diaryId);
		findDiary.setCoordinateX(diaryRequest.getCoordinateX());
		findDiary.setCoordinateY(diaryRequest.getCoordinateY());
		findDiary.setImageType(diaryRequest.getImageType());
		findDiary.setTitle(diaryRequest.getTitle());
		findDiary.setColor(diaryRequest.getColor());
		return DiaryResponse.toDto(findDiary);
	}

	@Transactional
	public List<DiaryResponse> findAll(String username) {
		User user = userRepository.findByUsername(username);
		List<Diary> resultDomain = diaryRepository.findByUser(user);
		List<DiaryResponse> result = new ArrayList<>();
		for (Diary diary : resultDomain) {
			result.add(DiaryResponse.toDto(diary));
		}
		return result;

	}

	@Transactional
	public void deleteAll() {
		diaryRepository.deleteAll();
	}

}
