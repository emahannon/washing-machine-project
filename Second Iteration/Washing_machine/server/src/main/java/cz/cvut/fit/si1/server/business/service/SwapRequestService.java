package cz.cvut.fit.si1.server.business.service;

import cz.cvut.fit.si1.server.business.dto.SwapRequestCreateDto;
import cz.cvut.fit.si1.server.business.dto.SwapRequestDto;
import cz.cvut.fit.si1.server.data.entity.Student;
import cz.cvut.fit.si1.server.data.entity.SwapRequest;
import cz.cvut.fit.si1.server.data.repository.entityrepostory.SwapRequestRepository;
import cz.cvut.fit.si1.server.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SwapRequestService {
    private final SwapRequestRepository swapRequestRepository;
    private final StudentService studentService;

    @Autowired
    public SwapRequestService(SwapRequestRepository swapRequestRepository, StudentService studentService) {
        this.swapRequestRepository = swapRequestRepository;
        this.studentService = studentService;
    }

    public SwapRequestDto create(SwapRequestCreateDto swapRequestCreateDto) throws Exception {
        Optional<Student> optionalStudent = studentService.findByID(swapRequestCreateDto.getStudent_id());

        if (optionalStudent.isEmpty())
            throw new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage());

        SwapRequest swapRequest = new SwapRequest(swapRequestCreateDto.getTimeReq(),
                swapRequestCreateDto.getReason(),
                false,
                optionalStudent.get());

        return toDTO(swapRequestRepository.save(swapRequest));

    }

    @Transactional
    public SwapRequestDto update(Integer id, SwapRequestCreateDto swapRequestCreateDto) throws Exception {
        Optional<SwapRequest> optionalSwapRequest = swapRequestRepository.findById(id);
        if (optionalSwapRequest.isEmpty()) {
            throw new Exception(ErrorMessage.SR_NOT_FOUND.getMessage());
        }

        SwapRequest swapRequest = optionalSwapRequest.get();
        Optional<Student> optionalStudent = studentService.findByID(swapRequestCreateDto.getStudent_id());
        if (optionalStudent.isEmpty()) {
            throw new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage());
        }

        swapRequest.setReason(swapRequestCreateDto.getReason());
        swapRequest.setStudent(optionalStudent.get());
        return toDTO(swapRequest);
    }

    @Transactional
    public void approveSwapRequest(SwapRequestDto swapRequestDto) throws Exception {

        Optional<SwapRequest> optionalSwapRequest = swapRequestRepository.findById(swapRequestDto.getId());
        if (optionalSwapRequest.isEmpty()) {
            throw new Exception(ErrorMessage.SR_NOT_FOUND.getMessage());
        }

        SwapRequest swapRequest = optionalSwapRequest.get();

        swapRequest.setStatus(true);
    }

    public List<SwapRequestDto> findAll() {
        List<SwapRequest> swapRequestList = swapRequestRepository.findAll();
        List<SwapRequestDto> list = new ArrayList<SwapRequestDto>();
        for (SwapRequest swapRequest : swapRequestList) {
            list.add(toDTO(swapRequest));
        }
        return list;
    }

    public List<SwapRequestDto> findByStudent(int student_id) throws Exception {
        Optional<Student> optionalStudent = studentService.findByID(student_id);
        if (optionalStudent.isEmpty()) {
            throw new Exception(ErrorMessage.STUDENT_NOT_FOUND.getMessage());
        }
        List<SwapRequest> swapRequestList = swapRequestRepository.findByStudent(optionalStudent.get());
        return swapRequestList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<SwapRequest> findByID(int id) throws Exception {
        return Optional.ofNullable(swapRequestRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorMessage.SR_NOT_FOUND.getMessage())));
    }

    public Optional<SwapRequestDto> findByIDAsDTO(int id) throws Exception {
        return toDTO(Optional.ofNullable(swapRequestRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorMessage.SR_NOT_FOUND.getMessage()))));
    }

    public void deleteById(int id) throws Exception {
        Optional<SwapRequest> optionalSwapRequest = swapRequestRepository.findById(id);
        if (optionalSwapRequest.isEmpty()) {
            throw new Exception(ErrorMessage.SR_NOT_FOUND.getMessage());
        }

        swapRequestRepository.deleteById(id);
    }

    private SwapRequestDto toDTO(SwapRequest swapRequest) {
        return new SwapRequestDto(swapRequest.getId(),
                swapRequest.getReason(),
                swapRequest.getStudent().getId(),
                swapRequest.getTimeReq(),
                swapRequest.getStatus());
    }

    private Optional<SwapRequestDto> toDTO(Optional<SwapRequest> optionalSwapRequest) {
        if (optionalSwapRequest.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(toDTO(optionalSwapRequest.get()));
    }
}
