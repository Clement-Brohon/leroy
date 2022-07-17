package adeo.leroymerlin.cdp.event.boundary.repository;

import adeo.leroymerlin.cdp.event.entity.Event;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface EventRepository extends Repository<Event, Long> {

    void deleteById(Long eventId);

    List<Event> findAllBy();

    Optional<Event> findById(Long id);

    Event save(Event event);

}
