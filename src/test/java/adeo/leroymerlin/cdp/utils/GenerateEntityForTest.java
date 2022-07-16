package adeo.leroymerlin.cdp.utils;

import adeo.leroymerlin.cdp.event.entity.Band;
import adeo.leroymerlin.cdp.event.entity.Event;
import adeo.leroymerlin.cdp.event.entity.Member;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GenerateEntityForTest {

    public static Band generateGreenDayBand(){
        Member member = generateGreenDayMember();
        Set<Member> listMembers = new HashSet<>();
        listMembers.add(member);

        Band greenDayBand = new Band();
        greenDayBand.setMembers(listMembers);
        greenDayBand.setName("Green Day");

        return greenDayBand;

    }

    public static Member generateGreenDayMember() {
        Member member = new Member();
        member.setName("Billie Joe Armstrong");
        return member;
    }


    public static Event generateWoodstockEvent(){
        Event woodstockEvent = new Event();

        Set<Band> listBand = new HashSet<>();
        listBand.add(generateGreenDayBand());

        woodstockEvent.setBands(listBand);
        woodstockEvent.setComment("Concert mythique");
        woodstockEvent.setId(1l);
        woodstockEvent.setImgUrl("1000.png");
        woodstockEvent.setNbStars(5);
        woodstockEvent.setTitle("Woodstock");

        return woodstockEvent;
    }

}
