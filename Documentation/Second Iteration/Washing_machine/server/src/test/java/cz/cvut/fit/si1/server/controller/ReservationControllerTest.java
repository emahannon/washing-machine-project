package cz.cvut.fit.si1.server.controller;

import cz.cvut.fit.si1.server.business.service.serviceimpl.ReservationService;
import cz.cvut.fit.si1.server.presentation.controller.ReservationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = ReservationController.class)
@AutoConfigureMockMvc
class ReservationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ReservationService reservationService;

    /**
     * Method to test create method.
     *
     * @throws Exception Throws exception if create unsuccessful.
     */
    @Test
    void create() throws Exception {


    }

    /**
     * Method to test the getReservationByStudent method.
     */
    @Test
    void getReservationsByStudent() {
    }

    /**
     * Method to test the getReservationByWm method.
     */
    @Test
    void getReservationsByWm() {
    }
}