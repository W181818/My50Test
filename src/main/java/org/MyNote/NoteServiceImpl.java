package org.MyNote;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Класс NoteServiceImpl реализует логику работы с заметками, используя хранилище заметок,
 * предоставляемое через интерфейс NoteRepository
 */
public class NoteServiceImpl {
    private  NoteRepository noteRepository;
    private  List<String> restrictedWords;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteServiceImpl( List<String> restrictedWords) {
        this.restrictedWords = restrictedWords;
    }




    /**
     * addNote: делегирует добавление новой замнтки в хранилище,
     * используя noteRepository.addNote.
     */
    public void addNote(Note note) {
        if (note.getTitle() == null || note.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null, or empty, or whitespace");
        }
        if (note.getContent() == null || note.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty or whitespace");
        }
        if (note.getId() <= 0) {
            throw new IllegalArgumentException("ID cannot be zero or negative");
        }
        if (note.getTitle().length() > 255) {
            throw new IllegalArgumentException("Title is too long");
        }
        if (note.getContent().length() > 2048) {
            throw new IllegalArgumentException("Content is too long");
        }

//        if (note.getId() < 0) {
//            throw new IllegalArgumentException("ID cannot be negative");
//        }
//        noteRepository.addNote(note.withTimestamp(LocalDateTime.now()));
//        noteRepository.addNote(note.withTimestamp(LocalDateTime.now()));

//        noteRepository.addNote(note.withTimestamp(LocalDateTime.now()));
        note.setTimestamp(LocalDateTime.now());
        noteRepository.addNote(note);
    }

    /**
     * updateNote: проверяет наличие заметки с указанным идентификатором в хранилище,
     * и если она существует, обновляет ее, используя noteRepository.updateNote.
     */

    public void updateNote(Note note) {
//        Note existingNote = noteRepository.getNoteById(note.getId());
//        if (existingNote != null) {
//            if (note.getContent() != null && !note.getContent().isEmpty()) {
//                noteRepository.updateNote(note);
//            } else {
//                throw new IllegalArgumentException("Content cannot be empty");
//            }
//        } else {
//            throw new IllegalArgumentException("Note with the given ID does not exist");
//        }
//todo ver2
//        if (note.getId() < 0) {
//            throw new IllegalArgumentException("ID cannot be negative");
//        }
//        if (note.getTitle().length() > 255) {
//            throw new IllegalArgumentException("Title is too long");
//        }
//        if (note.getContent().length() > 2048) {
//            throw new IllegalArgumentException("Content is too long");
//        }
//        Note existingNote = noteRepository.getNoteById(note.getId());
//        if (existingNote != null) {
//            noteRepository.updateNote(note);
//        } else {
//            throw new IllegalArgumentException("Note with the given ID does not exist");
//        }
        //todo ver 3.0

//todo VEr 5.0
        if (note == null) {
            throw new IllegalArgumentException("Note cannot be null");
        }
        if (note.getId() < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
        if (note.getId() == 0) {
            throw new IllegalArgumentException("ID cannot be zero");
        }
        if (note.getTitle() == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }

        if (note.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be  empty or whitespace");
        }
//
        if (note.getContent() == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        if (note.getContent().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
        if (note.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or whitespace");
        }
        if (note.getTitle().length() > 255) {
            throw new IllegalArgumentException("Title is too long");
        }
        if (note.getContent().length() > 2048) {
            throw new IllegalArgumentException("Content is too long");
        }


        Note existingNote = noteRepository.getNoteById(note.getId());
        if (existingNote != null) {

            noteRepository.updateNote(note);
        } else {
            throw new IllegalArgumentException("Note with the given ID does not exist");
        }

        note.setTimestamp(LocalDateTime.now());
    }
//todo ver 6.0
//            if (note == null) {
//                throw new IllegalArgumentException("Note cannot be null");
//            }
//            if (note.getId() < 0) {
//                throw new IllegalArgumentException("ID cannot be negative");
//            }
//            if (note.getId() == 0) {
//                throw new IllegalArgumentException("ID cannot be zero");
//            }
//            if (note.getTitle().length() > 255) {
//                throw new IllegalArgumentException("Title is too long");
//            }
//            if (note.getTitle().trim().isEmpty()) {
//                throw new IllegalArgumentException("Title cannot be empty");
//            }
//            if (note.getTitle().trim().equals("")) {
//                throw new IllegalArgumentException("Title cannot be whitespace");
//            }
//            if (note.getContent() == null) {
//                throw new IllegalArgumentException("Content cannot be null");
//            }
//            if (note.getContent().trim().isEmpty()) {
//                throw new IllegalArgumentException("Content cannot be null or whitespace");
//            }
//            if (note.getContent().length() > 2048) {
//                throw new IllegalArgumentException("Content is too long");
//            }
//
//            Note existingNote = noteRepository.getNoteById(note.getId());
//            if (existingNote != null) {
//                existingNote.setTitle(note.getTitle().trim());
//                existingNote.setContent(note.getContent().trim());
//                existingNote.setTimestamp(LocalDateTime.now());
//                noteRepository.updateNote(existingNote);
//            } else {
//                throw new IllegalArgumentException("Note with the given ID does not exist");
//            }
//        }

//       //todo ver4.0
//        if (note.getId() < 0) {
//            throw new IllegalArgumentException("ID cannot be negative");
//        }
//        if (note.getTitle().length() > 255) {
//            throw new IllegalArgumentException("Title is too long");
//        }
//        if (note.getContent().trim().isEmpty()) {
//            throw new IllegalArgumentException("Content cannot be whitespace");
//        }
//        if (note.getContent().length() > 2048) {
//            throw new IllegalArgumentException("Content is too long");
//        }
//        Note existingNote = noteRepository.getNoteById(note.getId());
//        if (existingNote != null) {
//            noteRepository.updateNote(note);
//        } else {
//            throw new IllegalArgumentException("Note with the given ID does not exist");
//        }


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

    /**
     * getNoteById: делегирует получение заметки по идентификатору, используя
     * noteRepository.getNoteById.
     */
    public Note getNoteById(int id) {
        if (id == 0) {
            throw new IllegalArgumentException("ID cannot be zero");
        }
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

//    /**
//     * addNoteWithRestrictedWords принимает объект Note и проверяет его на наличие запрещенных слов.
//     */
//    public void addNoteWithRestrictedWords(Note note, List<String> restrictedWords) {
//        if (note == null) {
//            throw new IllegalArgumentException("Note cannot be null");
//        }
//        if (note.getId() <= 0) {
//            throw new IllegalArgumentException("ID must be positive");
//        }
//        if (note.getTitle() == null || note.getTitle().trim().isEmpty()) {
//            throw new IllegalArgumentException("Title cannot be null or empty");
//        }
//        if (note.getContent() == null || note.getContent().trim().isEmpty()) {
//            throw new IllegalArgumentException("Content cannot be null or empty");
//        }
//        if (note.getTitle().length() > 255) {
//            throw new IllegalArgumentException("Title is too long");
//        }
//        if (note.getContent().length() > 2048) {
//            throw new IllegalArgumentException("Content is too long");
//        }
//
//        restrictedWords = Arrays.asList("бля","пиздец","нахуй");
//
//        for (String word : restrictedWords) {
//            if (note.getTitle().contains(word) || note.getContent().contains(word)) {
//                throw new IllegalArgumentException("Note contains a restricted word");
//            }
//        }
//        noteRepository.addNote(note);
//    }
}
