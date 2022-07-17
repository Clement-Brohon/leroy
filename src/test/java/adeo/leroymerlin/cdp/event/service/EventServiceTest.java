package adeo.leroymerlin.cdp.event.service;

import adeo.leroymerlin.cdp.event.boundary.repository.EventRepository;
import adeo.leroymerlin.cdp.event.entity.Band;
import adeo.leroymerlin.cdp.event.entity.Event;
import adeo.leroymerlin.cdp.utils.GenerateEntityForTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @Test
    void addReviewWithoutIdThrowError() {
        EventService eventService = new EventService(this.eventRepositoryMock);
        when(eventRepositoryMock.findById(null)).thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> eventService.addReview(null, "Commentaire", 5));
    }

    @Test
    void addReviewWitBadIdThrowError() {
        EventService eventService = new EventService(this.eventRepositoryMock);
        when(eventRepositoryMock.findById(45l)).thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> eventService.addReview(45l, "Commentaire", 5));
    }

    @Test
    void addReviewWitBadId() {
        EventService eventService = new EventService(this.eventRepositoryMock);
        when(eventRepositoryMock.findById(45l)).thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> eventService.addReview(45l, "Commentaire", 5));
    }

    @Test
    void addReview() {
        EventService eventService = new EventService(this.eventRepositoryMock);

        Event woodstockEventBdd =  GenerateEntityForTest.generateWoodstockEvent();
        when(eventRepositoryMock.findById(45l)).thenReturn(Optional.of(woodstockEventBdd));
        ArgumentCaptor<Event> eventModifie = ArgumentCaptor.forClass(Event.class);

        eventService.addReview(45l, "Commentaire modifié", 3);

        verify(eventRepositoryMock).save(eventModifie.capture());
        //modification ok
        assertEquals(3, eventModifie.getValue().getNbStars());
        assertEquals("Commentaire modifié", eventModifie.getValue().getComment());
        //le reste n'a pas changé
        assertEquals("Woodstock", eventModifie.getValue().getTitle());
        assertEquals("1000.png", eventModifie.getValue().getImgUrl());
    }

    @Test
    void getFilteredEventsReturnNoValueWhenDataHasNoMemberQuery() {
        EventService eventService = new EventService(this.eventRepositoryMock);

        Event woodstockEventBdd =  GenerateEntityForTest.generateWoodstockEvent();
        when(eventRepositoryMock.findAllBy()).thenReturn(Arrays.asList(woodstockEventBdd));

        List<Event> listEventsFiltreTeste = eventService.getFilteredEvents("Wa");

        assertEquals(0, listEventsFiltreTeste.size());

    }

    @Test
    void getFilteredEventsReturnOneValueWhenGoodQuery() {
        EventService eventService = new EventService(this.eventRepositoryMock);

        Event woodstockEventBdd =  GenerateEntityForTest.generateWoodstockEvent();
        when(eventRepositoryMock.findAllBy()).thenReturn(Arrays.asList(woodstockEventBdd));

        List<Event> listEventsFiltreTeste = eventService.getFilteredEvents("oe");

        assertEquals(1, listEventsFiltreTeste.size());
    }

    @Test
    void getFilteredEventsReturnNoValueIfNoBand() {
        EventService eventService = new EventService(this.eventRepositoryMock);

        when(eventRepositoryMock.findAllBy()).thenReturn(Arrays.asList(new Event()));

        List<Event> listEventsFiltreTeste = eventService.getFilteredEvents("oe");

        assertEquals(0, listEventsFiltreTeste.size());
    }

    @Test
    void getFilteredEventsReturnNoValueIfNoMemberInBand() {
        EventService eventService = new EventService(this.eventRepositoryMock);

        //Generation d'un evènement avec une bande sans aucun membre
        Event eventTest = new Event();
        Set<Band> listBandWithoutMember = new HashSet<>();
        listBandWithoutMember.add(new Band());
        eventTest.setBands(listBandWithoutMember);

        //mock
        when(eventRepositoryMock.findAllBy()).thenReturn(Arrays.asList(new Event()));

        //test
        List<Event> listEventsFiltreTeste = eventService.getFilteredEvents("oe");

        //verification
        assertEquals(0, listEventsFiltreTeste.size());
    }


}