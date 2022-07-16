package adeo.leroymerlin.cdp.event.entity;

import adeo.leroymerlin.cdp.utils.GenerateEntityForTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void getName() {
        Member singerGreenDay = GenerateEntityForTest.generateGreenDayMember();

        String nameSinger = singerGreenDay.getName();

        assertEquals("Billie Joe Armstrong", nameSinger);

    }

    @Test
    void setName() {

        Member member = new Member();

        member.setName("Edith Piaf");

        assertEquals("Edith Piaf", member.getName());
    }
}