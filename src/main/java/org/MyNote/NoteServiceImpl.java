package org.MyNote;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Класс NoteServiceImpl реализует логику работы с заметками, используя хранилище заметок,
 * предоставляемое через интерфейс NoteRepository
 */
public class NoteServiceImpl {
    private NoteRepository noteRepository;
    private List<String> restrictedWords;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public NoteServiceImpl(List<String> restrictedWords) {
        this.restrictedWords = restrictedWords;
    }


    /**
     * Добавляет новую заметку в хранилище.
     *
     * @param note новая заметка, которую нужно добавить
     * @throws IllegalArgumentException если заметка не удовлетворяет какому-либо из требований
     */
    public void addNote(Note note) {
        // Проверяем, что заголовок заметки не является пустым или состоит только из пробелов
        if (note.getTitle() == null || note.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null, or empty, or whitespace");
        }
        // Проверяем, что содержимое заметки не является пустым или состоит только из пробелов
        if (note.getContent() == null || note.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty or whitespace");
        }
        // Проверяем, что идентификатор заметки больше 0
        if (note.getId() <= 0) {
            throw new IllegalArgumentException("ID cannot be zero or negative");
        }
        // Проверяем, что заголовок заметки не слишком длинный
        if (note.getTitle().length() > 255) {
            throw new IllegalArgumentException("Title is too long");
        }
        // Проверяем, что содержимое заметки не слишком длинное
        if (note.getContent().length() > 2048) {
            throw new IllegalArgumentException("Content is too long");
        }

        // Устанавливаем текущую дату и время как временную метку заметки
        note.setTimestamp(LocalDateTime.now());

        // Добавляем заметку в хранилище
        noteRepository.addNote(note);
    }




    /**
     * updateNote: проверяет наличие заметки с указанным идентификатором в хранилище,
     * и если она существует, обновляет ее, используя noteRepository.updateNote.
     *
     * @param note Объект заметки, которую нужно обновить.
     * @throws IllegalArgumentException если заметка не удовлетворяет какому-либо из требований
     */

    public void updateNote(Note note) {
//        Проверяем, что переданная заметка null, ID заметки меньше или равен нулю
        if (note == null) {
            throw new IllegalArgumentException("Note cannot be null");
        }

        //    Проверяем, что идентификатор заметки больше 0
        if (note.getId() < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
        if (note.getId() == 0) {
            throw new IllegalArgumentException("ID cannot be zero");
        }
//       Проверяем, что заголовок заметки null, или пустой, или состоит только из пробельных символов
        if (note.getTitle() == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }

        if (note.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be  empty or whitespace");
        }
//        Проверяем, что контент заметки null, или пустой, или состоит только из пробельных символов
        if (note.getContent() == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        if (note.getContent().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
        if (note.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or whitespace");
        }
//      Проверяем, что длина заголовка превышает 255 символов, длина контента превышает 2048 символов,
        if (note.getTitle().length() > 255) {
            throw new IllegalArgumentException("Title is too long");
        }
        if (note.getContent().length() > 2048) {
            throw new IllegalArgumentException("Content is too long");
        }

        //      Проверяем, что заметка с указанным ID не существует в хранилище.
        Note existingNote = noteRepository.getNoteById(note.getId());
        if (existingNote != null) {

            noteRepository.updateNote(note);
        } else {
            throw new IllegalArgumentException("Note with the given ID does not exist");
        }

        note.setTimestamp(LocalDateTime.now());
    }


    /**
     * deleteNote: проверяет наличие заметки с указанным идентификатором в хранилище и
     * корректность идентификатора, и если она существует, удаляет ее, используя
     * noteRepository.deleteNote.
     */
    public void deleteNote(int id) {
        // Проверяем, что идентификатор заметки больше 0
        if (id == 0) {
            throw new IllegalArgumentException("ID cannot be zero");
        }

        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
        //      Проверяем, что заметка с указанным ID не существует в хранилище.
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
        // Проверяем, что идентификатор заметки больше 0
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
