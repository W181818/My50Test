package org.MyNote;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryNoteRepository implements NoteRepository {
    private final List<Note> notes = new ArrayList<>();

    @Override
    public void addNote(Note note) {
        notes.add(note);
    }

    @Override
    public void updateNote(Note note) {
        int index = notes.indexOf(getNoteById(note.getId()));
        if (index >= 0) {
            notes.set(index, note);
        }
    }

    @Override
    public void deleteNote(int id) {
        notes.removeIf(note -> note.getId() == id);
    }

    @Override
    public Note getNoteById(int id) {
        return notes.stream().filter(note -> note.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Note> getAllNotes() {
        return notes.stream().collect(Collectors.toList());
    }
}
