package ru.ilya.NauJava.model;

public class Note {
    private Long id;
    private String header;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "Note [id=" + id + ", header=" + header + ", content=" + content + "]";
    }
}
