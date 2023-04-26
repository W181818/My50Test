package org.MyNote;

import java.time.LocalDateTime;

public class Note {
/**Класс Note является представлением заметки. Он содержит следующие поля:

 id: уникальный идентификатор заметки.
 title: заголовок заметки.
 content: текст заметки.
 timestamp: временная метка создания заметки.*/

    private int id;
    private String title;
    private String content;
    private LocalDateTime timestamp;




    public void setTitle(String title) {
        this.title = title;
    }

    /**конструктор, который принимает id, title, и content и создает объект Note
 *  с указанными параметрами. Временная метка timestamp автоматически устанавливается
 *  на текущее время при создании заметки*/
    public Note(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timestamp = LocalDateTime.now();

    }
//    public Note(int id, String title, String content, LocalDateTime timestamp) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.timestamp = LocalDateTime.now();
//    }
//    public Note withTimestamp(LocalDateTime timestamp) {
//        return new Note(this.id, this.title, this.content, timestamp);
//    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    public void setContent(String content) {
        this.content = content;
    }
}
