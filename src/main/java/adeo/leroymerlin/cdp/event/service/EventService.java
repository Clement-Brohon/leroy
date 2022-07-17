package adeo.leroymerlin.cdp.event.service;

import adeo.leroymerlin.cdp.event.boundary.repository.EventRepository;
import adeo.leroymerlin.cdp.event.entity.Band;
import adeo.leroymerlin.cdp.event.entity.Event;
import adeo.leroymerlin.cdp.event.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        return events.stream()
                .filter(isMemberOfBandOfThisEventContain(query))
                .collect(Collectors.toList());
    }

    private Predicate<Event> isMemberOfBandOfThisEventContain(String query) {
        return (event) -> {
            if (event.getBands() == null) {
                return false;
            }
            else {
                return event.getBands().stream().anyMatch(isMemberOfThisBandContain(query));
            }
        };
    }

    private Predicate<Band> isMemberOfThisBandContain(String query) {
        return (band) -> {
            if (band.getMembers() == null) {
                return false;
            } else {
               return band.getMembers().stream().anyMatch(memberNameContains(query));
            }
        };
    }

    private Predicate<Member> memberNameContains(String query) {
        return member -> {
            if (member.getName() == null){
                return false;
            }
            else {
                return member.getName().contains(query);
            }
        };
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
