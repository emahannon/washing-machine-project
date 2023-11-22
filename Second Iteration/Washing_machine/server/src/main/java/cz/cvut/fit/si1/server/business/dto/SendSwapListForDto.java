package cz.cvut.fit.si1.server.business.dto;

import java.time.Instant;

public class SendSwapListForDto {
    private final Integer swap_id;
    private final Integer res_id;
    private final Integer swap_student;
    private final Integer res_student;
    private final Integer washingMachine_id;
    private final Instant startTime;
    private final Instant endTime;

    public SendSwapListForDto(Integer swap_id, Integer res_id, Integer swap_student, Integer res_student, Integer washingMachine_id, Instant startTime, Instant endTime) {
        this.swap_id = swap_id;
        this.res_id = res_id;
        this.swap_student = swap_student;
        this.res_student = res_student;
        this.washingMachine_id = washingMachine_id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getSwap_id() {
        return swap_id;
    }

    public Integer getRes_id() {
        return res_id;
    }

    public Integer getSwap_student() {
        return swap_student;
    }

    public Integer getRes_student() {
        return res_student;
    }

    public Integer getWashingMachine_id() {
        return washingMachine_id;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }
}
