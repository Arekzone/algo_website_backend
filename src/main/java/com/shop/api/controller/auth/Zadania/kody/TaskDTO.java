package com.shop.api.controller.auth.Zadania.kody;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
    private Long zadanie_id;
    private Long wynik_count;

    public TaskDTO(Long zadanie_id, Long wynik_count) {
        this.zadanie_id = zadanie_id;
        this.wynik_count = wynik_count;
    }
}
