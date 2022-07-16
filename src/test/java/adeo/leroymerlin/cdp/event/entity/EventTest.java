package adeo.leroymerlin.cdp.event.entity;

import adeo.leroymerlin.cdp.utils.GenerateEntityForTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void getId() {
        Event woodstockEvent = GenerateEntityForTest.generateWoodstockEvent();

        Long idWoodstockEnvent =  woodstockEvent.getId();

        assertEquals(1l, idWoodstockEnvent);

    }

    @Test
    void setId() {
        Event event = new Event();

        event.setId(14l);

        assertEquals(14l, event.getId());
    }

    @Test
    void getTitle() {
        Event woodstockEvent = GenerateEntityForTest.generateWoodstockEvent();

        String titleWoodStockEvent = woodstockEvent.getTitle();

        assertEquals("Woodstock", titleWoodStockEvent);
    }

    @Test
    void setTitle() {
        Event event = new Event();

        event.setTitle("MainSquare");

        assertEquals("MainSquare", event.getTitle());

    }

    @Test
    void getImgUrl() {
        Event woodstockEvent = GenerateEntityForTest.generateWoodstockEvent();

        String woodstockImgUrl = woodstockEvent.getImgUrl();

        assertEquals("1000.png", woodstockImgUrl);
    }

    @Test
    void setImgUrl() {
        Event event = new Event();

        event.setImgUrl("508.png");

        assertEquals("508.png", event.getImgUrl());
    }

    @Test
    void getBands() {
        Event woodstockEvent = GenerateEntityForTest.generateWoodstockEvent();

        Set<Band> listBandWoodStock = woodstockEvent.getBands();

        assertEquals(1, listBandWoodStock.size());
        assertEquals("Green Day", listBandWoodStock.iterator().next().getName());
    }

    @Test
    void setBands() {
        Band band = GenerateEntityForTest.generateGreenDayBand();
        Set<Band> listBand = new HashSet<>();
        listBand.add(band);
        Event event = new Event();

        event.setBands(listBand);

        assertEquals(1, event.getBands().size());
        assertEquals("Green Day", event.getBands().iterator().next().getName());
    }

    @Test
    void getNbStars() {
        Event woodstockEvent = GenerateEntityForTest.generateWoodstockEvent();

        int numberStars = woodstockEvent.getNbStars();

        assertEquals(5, numberStars);
    }

    @Test
    void setNbStars() {
        Event event = new Event();

        event.setNbStars(3);

        assertEquals(3, event.getNbStars());
    }

    @Test
    void getComment() {
        Event woodstockEvent = GenerateEntityForTest.generateWoodstockEvent();

        String comment = woodstockEvent.getComment();

        assertEquals("Concert mythique", comment);


    }

    @Test
    void setComment() {
        Event event = new Event();

        event.setComment("La musique était trop forte");

        assertEquals("La musique était trop forte", event.getComment());
    }
}