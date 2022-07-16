package adeo.leroymerlin.cdp.event.service;

import adeo.leroymerlin.cdp.event.boundary.repository.EventRepository;
import adeo.leroymerlin.cdp.event.entity.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    EventRepository eventRepositoryMock;



    @Test
    void getEvents() {
        EventService eventService = new EventService(this.eventRepositoryMock);
        when(eventRepositoryMock.findAllBy()).thenReturn(Arrays.asList(new Event()));

        eventService.getEvents();

        verify(eventRepositoryMock).findAllBy();


    }

    @Test
    void delete() {
        EventService eventService = new EventService(this.eventRepositoryMock);
        eventService.delete(1l);

        verify(eventRepositoryMock).deleteById(1l);
    }

    @Test
    void getFilteredEvents() {

    }
}