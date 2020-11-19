package com.buildweek.potluckplanner.models;

import javax.persistence.*;

@Entity
@Table(name = "food")
public class Food extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long foodid;

    @Column(nullable = false)
    private String foodname;

//    @ManyToOne
//    @JoinColumn(name = "attendeeid",
//        nullable = false)
//    private Attendee attendee;

    public Food()
    {
    }

    public Food(
        String foodname,
        Attendee attendee)
    {
        this.foodname = foodname;
//        this.attendee = attendee;
    }

    public long getFoodid()
    {
        return foodid;
    }

    public void setFoodid(long foodid)
    {
        this.foodid = foodid;
    }

    public String getFoodname()
    {
        return foodname;
    }

    public void setFoodname(String foodname)
    {
        this.foodname = foodname;
    }

//    public Attendee getAttendee()
//    {
//        return attendee;
//    }
//
//    public void setAttendee(Attendee attendee)
//    {
//        this.attendee = attendee;
//    }
}
