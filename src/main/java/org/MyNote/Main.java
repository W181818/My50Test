package org.MyNote;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        // Создаем реализацию NoteRepository и сервис NoteServiceImpl
        NoteRepository noteRepository = new InMemoryNoteRepository();
        NoteServiceImpl noteService = new NoteServiceImpl(noteRepository);

        // Добавляем заметки
        Note note1 = new Note(1, "Заметка 1", "Dei voluntas");
        Note note2 = new Note(2, "Заметка 2", "Honor et virtus");
        Note note3 = new Note(3, "Заметка 3", "Patientia et perseverantia");
        Note note4 = new Note(4, "25", "44 ");


        noteService.addNote(note1);
        noteService.addNote(note2);
        noteService.addNote(note3);
        noteService.addNote(note4);


        // Выводим список заметок
        System.out.println("Список заметок:");
        for (Note note : noteService.getAllNotes()) {
            System.out.println(note.getId() + ": " + note.getTitle());
        }

        System.out.println("\nСодержание заметок : " );
        for (Note note : noteService.getAllNotes()) {
            System.out.println(note.getTitle() + ": " + note.getContent());
        }

        // Обновляем заметку
        Note updatedNote2 = new Note(2, "Обновленная заметка 2", "Терпение и устремленность ");
        noteService.updateNote(updatedNote2);
        Note updatedNote4 = new Note(50, "Обновленная заметка 2", "Терпение и устремленность ");
        noteService.updateNote(updatedNote4);


        // Выводим обновленную заметку
        System.out.println("\nОбновленная заметка 2:");
        Note updatedNote = noteService.getNoteById(2);
        System.out.println(updatedNote.getId() + ": " + updatedNote.getTitle() + " - " + updatedNote.getContent());

        // Удаляем заметку
        noteService.deleteNote(1);

        // Выводим список заметок после удаления
        System.out.println("\nСписок заметок после удаления:");
        for (Note note : noteService.getAllNotes()) {
            System.out.println(note.getId() + ": " + note.getTitle());
        }

        System.out.println("\nСодержание заметок : " );
        for (Note note : noteService.getAllNotes()) {
            System.out.println(note.getTitle() + ": " + note.getContent());
        }

    }
}

