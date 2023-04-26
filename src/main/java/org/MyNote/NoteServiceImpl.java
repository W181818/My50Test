package org.MyNote;

import java.util.List;

/**
 * Класс NoteServiceImpl реализует логику работы с заметками, используя хранилище заметок,
 * предоставляемое через интерфейс NoteRepository
 */
public class NoteServiceImpl {
    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    /**
     * addNote: делегирует добавление новой замнтки в хранилище,
     * используя noteRepository.addNote.
     */
    public void addNote(Note note) {
        if (note.getTitle() == null || note.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (note.getContent() == null || note.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or whitespace");
        }
        noteRepository.addNote(note);
    }

    /**
     * updateNote: проверяет наличие заметки с указанным идентификатором в хранилище,
     * и если она существует, обновляет ее, используя noteRepository.updateNote.
     */
    public void updateNote(Note note) {
        if (note.getTitle().length() > 255) {
            throw new IllegalArgumentException("Title is too long");
        }
        Note existingNote = noteRepository.getNoteById(note.getId());
        if (existingNote != null) {
            noteRepository.updateNote(note);
        } else {
            throw new IllegalArgumentException("Note with the given ID does not exist");
        }
    }

    /**
     * deleteNote: проверяет наличие заметки с указанным идентификатором в хранилище и
     * корректность идентификатора, и если она существует, удаляет ее, используя
     * noteRepository.deleteNote.
     */
    public void deleteNote(int id) {
            if (id == 0) {
                throw new IllegalArgumentException("ID cannot be zero");
            }

            if (id < 0) {
                throw new IllegalArgumentException("ID cannot be negative");
            }

            Note existingNote = noteRepository.getNoteById(id);
            if (existingNote != null) {
                noteRepository.deleteNote(id);
            } else {
                throw new IllegalArgumentException("Note with the given ID does not exist");
            }
        }

//        Note existingNote = noteRepository.getNoteById(id);
//        if (id < 0) {
//            throw new IllegalArgumentException("ID cannot be negative");
//        }
//        if (existingNote != null) {
//            noteRepository.deleteNote(id);
//        } else {
//            throw new IllegalArgumentException("Note with the given ID does not exist");
//        }
//    }

    /**
     * getNoteById: делегирует получение заметки по идентификатору, используя
     * noteRepository.getNoteById.
     */
    public Note getNoteById(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
        return noteRepository.getNoteById(id);
    }

    /**
     * getAllNotes: делегирует получение списка всех заметок из хранилища,
     * используя noteRepository.getAllNotes.
     */
    public List<Note> getAllNotes() {
        return noteRepository.getAllNotes();
    }


}
