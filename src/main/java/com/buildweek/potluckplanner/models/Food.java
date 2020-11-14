package com.buildweek.potluckplanner.models;

import javax.persistence.*;

@Entity
@Table(name = "food")
public class Food
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long foodid;

    @Column(nullable = false)
    private String foodname;

    // user

    public Food()
    {
    }

    public Food(String foodname)
    {
        this.foodname = foodname;
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
}
