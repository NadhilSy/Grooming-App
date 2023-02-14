package com.enigma.grooming.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class Summary {
    @Getter
    @Setter
    @AllArgsConstructor
    private class CurrentMonth {
        private Long saleCount;
        private Long saleTotal;
    }
    private CurrentMonth currentMonth;
    private Long omzet;
    List<TopSpender> topSpenders;
    public Summary(Long thisMonthCount,Long thisMonthSum,Long omzet,List<TopSpender> topSpenders) {
        this.currentMonth = new CurrentMonth(thisMonthCount,thisMonthSum);
        this.omzet = omzet;
        this.topSpenders = topSpenders;
    }
}
