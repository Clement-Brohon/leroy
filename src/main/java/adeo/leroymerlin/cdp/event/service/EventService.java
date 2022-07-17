package adeo.leroymerlin.cdp.event.service;

import adeo.leroymerlin.cdp.event.boundary.repository.EventRepository;
import adeo.leroymerlin.cdp.event.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAllBy();
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

    public List<Event> getFilteredEvents(String query) {
        List<Event> events = eventRepository.findAllBy();
        // Filter the events list in pure JAVA here

        return events;
    }

    public void addReview(Long idEvent, String comment, Integer nbStars){
        Optional<Event> eventFromBdd = eventRepository.findById(idEvent);

        eventFromBdd.ifPresentOrElse(
            (event) -> {
                event.setComment(comment);
                event.setNbStars(nbStars);
                eventRepository.save(event);
            },
            () -> { throw new RuntimeException("Event non trouvé"); }
        );
    }
}
