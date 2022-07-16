package adeo.leroymerlin.cdp.event.boundary.controller;

import adeo.leroymerlin.cdp.event.entity.Event;
import adeo.leroymerlin.cdp.event.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {
    @Mock
    EventService eventServiceMock;

    EventController eventController;

    @Test
    void findEvents() {
        this.eventController = new EventController(eventServiceMock);
        when(eventController.findEvents()).thenReturn(null);
        eventController.findEvents();
        verify(eventServiceMock).getEvents();
    }

    @Test
    void findEventsWithQuerry() {
        this.eventController = new EventController(eventServiceMock);
        eventController.findEvents("Groupe");
        verify(eventServiceMock).getFilteredEvents("Groupe");
    }

    @Test
    void deleteEvent() {
        this.eventController = new EventController(eventServiceMock);
        eventController.deleteEvent(32l);
        verify(eventServiceMock).delete(32l);
    }

    @Test
    void updateEvent() {
        this.eventController = new EventController(eventServiceMock);
        eventController.updateEvent(1l, new Event());
    }
}