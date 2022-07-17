package adeo.leroymerlin.cdp.event.boundary.controller;

import adeo.leroymerlin.cdp.event.entity.Event;
import adeo.leroymerlin.cdp.event.service.EventService;
import adeo.leroymerlin.cdp.utils.GenerateEntityForTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void whenUpdateEventNullEventThrowError() {
        this.eventController = new EventController(eventServiceMock);
        assertThrows(RuntimeException.class, () -> eventController.updateEvent(1l, null));
    }

    @Test
    void whenUpdateIdNullEventThrowError() {
        this.eventController = new EventController(eventServiceMock);
        assertThrows(RuntimeException.class, () -> eventController.updateEvent(null, new Event()));
    }

    @Test
    void whenUpdateIdAndEventNullEventThrowError() {
        this.eventController = new EventController(eventServiceMock);
        assertThrows(RuntimeException.class, () -> eventController.updateEvent(null, null));
    }

    @Test
    void updateCommentWithoutStarEvent() {
        this.eventController = new EventController(eventServiceMock);
        Event woodstockEvent  = GenerateEntityForTest.generateWoodstockEvent();
        woodstockEvent.setNbStars(null);
        eventController.updateEvent(1l, woodstockEvent);

        verify(eventServiceMock).addReview(1l, "Concert mythique", null);
    }

    @Test
    void updateStarWithoutCommentEvent() {
        this.eventController = new EventController(eventServiceMock);
        Event woodstockEvent  = GenerateEntityForTest.generateWoodstockEvent();
        woodstockEvent.setComment(null);
        eventController.updateEvent(1l, woodstockEvent);

        verify(eventServiceMock).addReview(1l, null, 5);
    }

    @Test
    void updateStarAndCommentEvent() {
        this.eventController = new EventController(eventServiceMock);
        Event woodstockEvent  = GenerateEntityForTest.generateWoodstockEvent();
        eventController.updateEvent(1l, woodstockEvent);

        verify(eventServiceMock).addReview(1l, "Concert mythique", 5);
    }

    @Test
    void updateEventWithoutStarAndComment() {
        this.eventController = new EventController(eventServiceMock);
        Event event = new Event();
        eventController.updateEvent(1l, event);

        verify(eventServiceMock).addReview(1l, null, null);
    }
}