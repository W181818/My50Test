package org.MyNote;

import java.util.List;
/**Интерфейс NoteRepository определяет контракт для работы с хранилищем заметок.  */
public interface NoteRepository {
    /**
     addNote: добавление новой заметки в хранилище.
     updateNote: обновление существующей заметки в хранилище.
     deleteNote: удаление заметки из хранилища по идентификатору.
     getNoteById: получение заметки из хранилища по идентификатору.
     getAllNotes: получение списка всех заметок из хранилища.*/

    void addNote(Note note);
    void updateNote(Note note);
    void deleteNote(int id);
    Note getNoteById(int id);
    List<Note> getAllNotes();
}
