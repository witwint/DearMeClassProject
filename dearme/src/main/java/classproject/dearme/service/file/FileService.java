package classproject.dearme.service.file;

import classproject.dearme.domain.uploadfile.UploadFile;
import classproject.dearme.dto.file.UploadFileDto;
import classproject.dearme.repository.file.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

	private final FileRepository fileRepository;

	@Transactional
	public UploadFileDto save(UploadFileDto uploadFileDto) {
		UploadFile uploadFile = UploadFile.getUploadFile(uploadFileDto);
		fileRepository.save(uploadFile);
		return UploadFileDto.toDto(uploadFile);
	}

	@Transactional
	public UploadFileDto findUploadFile(Long id) {
		UploadFile findUploadFile = fileRepository.getReferenceById(id);
		return UploadFileDto.toDto(findUploadFile);
	}


}
