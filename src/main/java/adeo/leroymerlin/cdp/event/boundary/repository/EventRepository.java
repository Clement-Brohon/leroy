package adeo.leroymerlin.cdp.event.boundary.repository;

import adeo.leroymerlin.cdp.event.entity.Event;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EventRepository extends Repository<Event, Long> {

    void deleteById(Long eventId);

    List<Event> findAllBy();
}
