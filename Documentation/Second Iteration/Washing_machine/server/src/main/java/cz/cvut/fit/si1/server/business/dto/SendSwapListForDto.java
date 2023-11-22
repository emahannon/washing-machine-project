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

    /**
     * Constructor for the SendSwapListForDto class.
     *
     * @param swap_id           Integer that represents the id of the SwapRequest
     * @param res_id            Integer that represents the id of the Reservation
     * @param swap_student      Integer that represents the id of the Student that submitted the SwapRequest
     * @param res_student       Integer that represents the id of the Student that made the Reservation
     * @param washingMachine_id Integer that represents the id of the WashingMachine
     * @param startTime         Instant that represents the time the reservation starts
     * @param endTime           Instant that represents the time the reservation ends
     */
    public SendSwapListForDto(Integer swap_id, Integer res_id, Integer swap_student, Integer res_student, Integer washingMachine_id, Instant startTime, Instant endTime) {
        this.swap_id = swap_id;
        this.res_id = res_id;
        this.swap_student = swap_student;
        this.res_student = res_student;
        this.washingMachine_id = washingMachine_id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Getter for the swap ID.
     *
     * @return swap_id - Integer that represents the id of the SwapRequest
     */
    public Integer getSwap_id() {
        return swap_id;
    }

    /**
     * Getter for the reservation ID.
     *
     * @return res_id - Integer that represents the id of the Reservation
     */

    public Integer getRes_id() {
        return res_id;
    }

    /**
     * Getter for the student seeking to swap.
     *
     * @return swap_student - Integer that represents the id of the Student that submitted the SwapRequest
     */
    public Integer getSwap_student() {
        return swap_student;
    }

    /**
     * Getter for the student currently holding the reservation.
     *
     * @return res_student - Integer that represents the id of the Student that made the Reservation
     */
    public Integer getRes_student() {
        return res_student;
    }

    /**
     * Getter for the washing machine ID.
     *
     * @return washingMachine_id - Integer that represents the id of the WashingMachine
     */
    public Integer getWashingMachine_id() {
        return washingMachine_id;
    }

    /**
     * Getter for the start time of the reservation.
     *
     * @return startTime - Instant that represents the time the reservation starts
     */
    public Instant getStartTime() {
        return startTime;
    }

    /**
     * Getter for the end time of the reservation
     *
     * @return endTime - Instant that represents the time the reservation ends
     */
    public Instant getEndTime() {
        return endTime;
    }
}
