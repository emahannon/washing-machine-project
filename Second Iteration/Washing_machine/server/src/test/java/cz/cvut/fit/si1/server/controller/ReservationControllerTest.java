package cz.cvut.fit.si1.server.controller;

import cz.cvut.fit.si1.server.presentation.controller.ReservationController;
import cz.cvut.fit.si1.server.business.service.ReservationService;
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

    @Test
    void create() throws Exception {


    }

    @Test
    void getReservationsByStudent() {
    }

    @Test
    void getReservationsByWm() {
    }
}