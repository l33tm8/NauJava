package ru.ilya.NauJava.DTO;

import ru.ilya.NauJava.model.ReportStatus;

public class ReportDTO {
    private Long id;
    private ReportStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
