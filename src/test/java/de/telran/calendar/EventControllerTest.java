package de.telran.calendar;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.calendar.controller.EventController;
import de.telran.calendar.entity.Event;
import de.telran.calendar.service.EventService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = EventController.class)
@AutoConfigureMockMvc
@DisplayName("Tests for EventController API")
class EventControllerTest {
    static final String API_PATH = "/event/";
    static final MediaType APP_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    @Autowired
    ObjectMapper mapper;
    @Autowired
    MockMvc mockMvc;

    @MockBean
    EventService service;

    @Nested
    @DisplayName("Get events tests")
    class GetEventsTests {
        @Test
        @DisplayName("Event found and returned")
        void Should_ReturnEventAndStatus200() throws Exception {
            LocalDate currentDate = LocalDate.of(2023, 02, 19);
            Mockito.when(service.get(any(Long.class)))
                    .thenAnswer(i -> new Event(i.getArgument(0, Long.class), currentDate, "Test event"));

            mockMvc.perform(get(API_PATH + "1"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$").isNotEmpty())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.date").value("2023-02-19"))
                    .andExpect(jsonPath("$.name").value("Test event"))
                    .andDo(print());

            Mockito.verify(service, times(1)).get(any(Long.class));
        }

        @Test
        @DisplayName("Event not found and 404 returned")
        void Should_ReturnStatus404() throws Exception {
            Mockito.when(service.get(any(Long.class)))
                    .thenAnswer(i -> {
                        throw new IllegalArgumentException();
                    });

            mockMvc.perform(get(API_PATH + "1"))
                    .andExpect(status().isNotFound())
                    .andDo(print());
        }

        @Nested
        @DisplayName("POST events tests")
        class EventsCreationTest {

            @Test
            @DisplayName("Event successfully posted")
            void Should_ReturnIdAndStatus200() throws Exception {
                Event testEvent = new Event();
                testEvent.setId(1L);
                Mockito.when(service.create(any(Event.class)))
                        .thenAnswer(i -> 1L);

                mockMvc.perform(post("/event")
                                .content(mapper.writeValueAsString(testEvent))
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$").isNotEmpty())
                        .andExpect(jsonPath("$").value(1L))
                        .andDo(print());


            }

            @Test
            @DisplayName("Invalid event data returns bad request status")
            void Should_ReturnBadRequestStatus() throws Exception {
                Event invalidEvent = new Event(0, null, "Test event");

                Mockito.when(service.create(any(Event.class))).thenThrow(IllegalStateException.class);

                mockMvc.perform(post("/event")
                                .content(mapper.writeValueAsString(invalidEvent))
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                        .andDo(print());
            }

        }


        @Nested
        @DisplayName("Delete events tests")
        class EventsDeleteTest {

            @Test
            @DisplayName("Event successfully deleted")
            void Should_ReturnNoContentStatus() throws Exception {
                long eventId = 1;

                Mockito.doNothing().when(service).delete(eventId);

                mockMvc.perform(delete("/event/{id}", eventId))
                        .andExpect(status().isNoContent())
                        .andExpect(content().string(""))
                        .andDo(print());
            }

            @Test
            @DisplayName("Event not found")
            void Should_ReturnNotFoundStatus() throws Exception {
                long eventId = 1;

                Mockito.doThrow(new IllegalArgumentException()).when(service).delete(eventId);

                mockMvc.perform(delete("/event/{id}", eventId))
                        .andExpect(status().isNotFound())
                        .andExpect(content().string(""))
                        .andDo(print());
            }

        }


        @Nested
        @DisplayName("PATCH events tests")
        class EventsModificationTest {

            @Test
            @DisplayName("Event successfully modified")
            void Should_ReturnOkStatus() throws Exception {
                long eventId = 1;
                String newEventName = "New event name";

                Mockito.doNothing().when(service).update(eventId, newEventName);

                mockMvc.perform(patch(API_PATH + eventId)
                                .content(newEventName)
                                .contentType(MediaType.TEXT_PLAIN))
                        .andExpect(status().isOk())
                        .andDo(print());
            }

            @Test
            @DisplayName("Event not found")
            void Should_ReturnNotFoundStatus() throws Exception {
                long eventId = 1000;
                String newEventName = "New event name";

                Mockito.doThrow(new IllegalArgumentException()).when(service).update(eventId, newEventName);

                mockMvc.perform(patch(API_PATH + eventId)
                                .content(newEventName)
                                .contentType(MediaType.TEXT_PLAIN))
                        .andExpect(status().isNotFound())
                        .andDo(print());
            }

        }
    }


}
