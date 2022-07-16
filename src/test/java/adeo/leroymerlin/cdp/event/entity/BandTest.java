package adeo.leroymerlin.cdp.event.entity;

import adeo.leroymerlin.cdp.utils.GenerateEntityForTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BandTest {


    @Test
    void getMembers() {
        Band band = GenerateEntityForTest.generateGreenDayBand();

        Set<Member> listMemberTest = band.getMembers();

        assertEquals(1, listMemberTest.size());
        assertEquals("Billie Joe Armstrong", listMemberTest.iterator().next().getName());
    }

    @Test
    void setMembers() {

        Set<Member> listMember = new HashSet<>();
        Member member = new Member();
        member.setName("Chris Martin");
        listMember.add(member);

        Band band = new Band();
        band.setMembers(listMember);

        assertEquals(1, band.getMembers().size());
        assertEquals("Chris Martin", band.getMembers().iterator().next().getName());
    }

    @Test
    void getName() {
        Band greenDayBand = GenerateEntityForTest.generateGreenDayBand();

        String nameBandJdd = greenDayBand.getName();

        assertEquals("Green Day", nameBandJdd);

    }

    @Test
    void setName() {
        Band band = new Band();

        band.setName("Sum 41");

        assertEquals("Sum 41", band.getName());
    }
}