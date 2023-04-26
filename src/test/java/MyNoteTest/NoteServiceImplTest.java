package MyNoteTest;

import org.MyNote.Note;
import org.MyNote.NoteRepository;
import org.MyNote.NoteServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class NoteServiceImplTest {
    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteServiceImpl noteService;

    @Captor
    private ArgumentCaptor<Note> noteCaptor;

    @Captor
    private ArgumentCaptor<Integer> idCaptor;

    @BeforeAll
    static void init() {
        System.out.println("BeforeAll : ");

    }

    @BeforeEach
    public void setUp() {
        noteRepository = mock(NoteRepository.class);
        noteService = new NoteServiceImpl(noteRepository);
        noteCaptor = ArgumentCaptor.forClass(Note.class);
        idCaptor = ArgumentCaptor.forClass(Integer.class);
    }

    @AfterEach
    public void tearDown() {
        reset(noteRepository);
    }

    /**
     * 1. Тест на успешное добавление заметки
     */
    @Test
    public void addNoteTest() {
        Note note = new Note(1, "Title", "Content");
        noteService.addNote(note);

        verify(noteRepository).addNote(noteCaptor.capture());
        Note capturedNote = noteCaptor.getValue();

        assertThat(capturedNote).isEqualToComparingFieldByField(note);
    }

    /**
     * 2. Тест на добавление заметки с пустым заголовком
     */
    @Test
    public void addNoteWithEmptyTitleTest() {
        Note note = new Note(1, "", "Content");

        assertThatThrownBy(() -> noteService.addNote(note))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title cannot be empty");
    }

    /**
     * 3. Тест на добавление заметки с пустым содержанием
     */
    @Test
    public void addNoteWithEmptyContentTest() {
        Note note = new Note(1, "Title", "");

        assertThatThrownBy(() -> noteService.addNote(note))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Content cannot be empty");
    }

    /**
     * 4. Тест на успешное обновление заметки
     */
    @Test
    public void updateNoteTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);

        noteService.updateNote(note);

        verify(noteRepository).updateNote(noteCaptor.capture());
        Note capturedNote = noteCaptor.getValue();

        assertThat(capturedNote).isEqualToComparingFieldByField(note);
    }

    /**
     * 5. Тест на обновление несуществующей заметки
     */
    @Test
    public void updateNonExistingNoteTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(null);

        assertThatThrownBy(() -> noteService.updateNote(note))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Note with the given ID does not exist");
    }

    /**
     * 6. Тест на удаление заметки
     */
    @Test
    public void deleteNoteTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);

        noteService.deleteNote(1);

        verify(noteRepository).deleteNote(idCaptor.capture());
        int capturedId = idCaptor.getValue();

        assertThat(capturedId).isEqualTo(1);
    }

    /**
     * 7. Тест на удаление несуществующей заметки
     */
    @Test
    public void deleteNonExistingNoteTest() {
        when(noteRepository.getNoteById(1)).thenReturn(null);

        assertThatThrownBy(() -> noteService.deleteNote(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Note with the given ID does not exist");
    }

    /**
     * 8. Тест на получение заметки по ID
     */
    @Test
    public void getNoteByIdTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);

        Note result = noteService.getNoteById(1);

        assertThat(result).isEqualToComparingFieldByField(note);
    }

    /**
     * 9. Тест на получение несуществующей заметки по ID
     */
    @Test
    public void getNonExistingNoteByIdTest() {
        when(noteRepository.getNoteById(1)).thenReturn(null);

        Note result = noteService.getNoteById(1);

        assertThat(result).isNull();
    }

    /**
     * 10. Тест на получение всех заметок
     */
    @Test
    public void getAllNotesTest() {
        Note note1 = new Note(1, "Title1", "Content1");
        Note note2 = new Note(2, "Title2", "Content2");
        List<Note> notes = Arrays.asList(note1, note2);
        when(noteRepository.getAllNotes()).thenReturn(notes);

        List<Note> result = noteService.getAllNotes();

        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isEqualToComparingFieldByField(note1);
        assertThat(result.get(1)).isEqualToComparingFieldByField(note2);
    }

    /**
     * 11. Тест на добавление заметки с отрицательным ID
     */
    @Test
    public void addNoteWithNegativeIdTest() {
        Note note = new Note(-1, "Title", "Content");

        assertThatThrownBy(() -> noteService.addNote(note))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ID cannot be negative");
    }

    /**
     * 12. Тест на добавление заметки с нулевым ID
     */
    @Test
    public void addNoteWithZeroIdTest() {
        Note note = new Note(0, "Title", "Content");

        assertThatThrownBy(() -> noteService.addNote(note))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ID cannot be zero");
    }

    /**
     * 13. Тест на обновление заметки с отрицательным ID
     */
    @Test
    public void updateNoteWithNegativeIdTest() {
        Note note = new Note(-1, "Title", "Content");

        assertThatThrownBy(() -> noteService.updateNote(note))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ID cannot be negative");
    }

    /**
     * 14. Тест на обновление заметки с нулевым ID
     */
    @Test
    public void updateNoteWithZeroIdTest() {
        Note note = new Note(0, "Title", "Content");

        assertThatThrownBy(() -> noteService.updateNote(note))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ID cannot be zero");
    }

    /**
     * 15. Тест на удаление заметки с отрицательным ID
     */
    @Test
    public void deleteNoteWithNegativeIdTest() {
        assertThatThrownBy(() -> noteService.deleteNote(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ID cannot be negative");
    }

    /**
     * 16. Тест на удаление заметки с нулевым ID
     */
    @Test
    public void deleteNoteWithZeroIdTest() {
        assertThatThrownBy(() -> noteService.deleteNote(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ID cannot be zero");
    }


    /**
     * 17. Тест на получение заметки с отрицательным ID
     */
    @Test
    public void getNoteWithNegativeIdTest() {
        assertThatThrownBy(() -> noteService.getNoteById(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ID cannot be negative");
    }

    /**
     * 18. Тест на получение заметки с нулевым ID
     */
    @Test
    public void getNoteWithZeroIdTest() {
        assertThatThrownBy(() -> noteService.getNoteById(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ID cannot be zero");
    }

    /**
     * 19. Тест на добавление заметки с длинным заголовком
     */
    @Test
    public void addNoteWithLongTitleTest() {
        String longTitle = "A".repeat(256);
        Note note = new Note(1, longTitle, "Content");

        assertThatThrownBy(() -> noteService.addNote(note))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title is too long");
    }

    /**
     * 20. Тест на добавление заметки с длинным содержанием
     */
    @Test
    public void addNoteWithLongContentTest() {
        String longContent = "A".repeat(2049);
        Note note = new Note(1, "Title", longContent);

        assertThatThrownBy(() -> noteService.addNote(note))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Content is too long");
    }

    /**
     * 21. Тест на обновление заметки с длинным заголовком
     */
    @Test
    public void updateNoteWithLongTitleTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);

        String longTitle = "A".repeat(256);
        Note updatedNote = new Note(1, longTitle, "Content");

        assertThatThrownBy(() -> noteService.updateNote(updatedNote))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title is too long");
    }

    /**
     * 22. Тест на обновление заметки с длинным содержанием
     */
    @Test
    public void updateNoteWithLongContentTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);

        String longContent = "A".repeat(2049);
        Note updatedNote = new Note(1, "Title", longContent);

        assertThatThrownBy(() -> noteService.updateNote(updatedNote))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Content is too long");
    }

    /**
     * 23. Тест на обновление заметки с изменением времени
     */
    @Test
    public void updateNoteWithDifferentTimeTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);

        Note updatedNote = new Note(1, "Updated Title", "Updated Content");
        noteService.updateNote(updatedNote);

        verify(noteRepository).updateNote(noteCaptor.capture());
        Note capturedNote = noteCaptor.getValue();

        assertThat(capturedNote.getTimestamp()).isAfter(note.getTimestamp());
    }

    /**
     * 24. Тест на добавление заметки с вызовом метода addNote только один раз
     */
    @Test
    public void addNoteCalledOnceTest() {
        Note note = new Note(1, "Title", "Content");
        noteService.addNote(note);

        verify(noteRepository, times(1)).addNote(noteCaptor.capture());
    }

    /**
     * 25. Тест на обновление заметки с вызовом метода updateNote только один раз
     */
    @Test
    public void updateNoteCalledOnceTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);

        noteService.updateNote(note);

        verify(noteRepository, times(1)).updateNote(noteCaptor.capture());
    }

    /**
     * 26. Тест на удаление заметки с вызовом метода deleteNote только один раз
     */
    @Test
    public void deleteNoteCalledOnceTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);

        noteService.deleteNote(1);

        verify(noteRepository, times(1)).deleteNote(idCaptor.capture());
    }

    /**
     * 27. Тест на вызов метода getNoteById только один раз
     */
    @Test
    public void getNoteByIdCalledOnceTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);

        noteService.getNoteById(1);

        verify(noteRepository, times(1)).getNoteById(idCaptor.capture());
    }

    /**
     * 28. Тест на вызов метода getAllNotes только один раз
     */
    @Test
    public void getAllNotesCalledOnceTest() {
        Note note1 = new Note(1, "Title1", "Content1");
        Note note2 = new Note(2, "Title2", "Content2");
        List<Note> notes = Arrays.asList(note1, note2);
        when(noteRepository.getAllNotes()).thenReturn(notes);

        noteService.getAllNotes();

        verify(noteRepository, times(1)).getAllNotes();
    }

    /**
     * 29. Тест на добавление двух заметок с разными timestamp
     */
    @Test
    public void addTwoNotesWithDifferentTimestampsTest() {
        Note note1 = new Note(1, "Title1", "Content1");
        Note note2 = new Note(2, "Title2", "Content2");

        noteService.addNote(note1);
        noteService.addNote(note2);

        verify(noteRepository, times(2)).addNote(noteCaptor.capture());
        List<Note> capturedNotes = noteCaptor.getAllValues();

        assertThat(capturedNotes.get(0).getTimestamp()).isBefore(capturedNotes.get(1).getTimestamp());
    }

    /**
     * 30. Тест на получение заметки по ID с вызовом метода getNoteById в определенном порядке
     */
    @Test
    public void getNoteByIdCalledInOrderTest() {
        Note note1 = new Note(1, "Title1", "Content1");
        Note note2 = new Note(2, "Title2", "Content2");
        when(noteRepository.getNoteById(1)).thenReturn(note1);
        when(noteRepository.getNoteById(2)).thenReturn(note2);

        noteService.getNoteById(1);
        noteService.getNoteById(2);

        InOrder inOrder = inOrder(noteRepository);
        inOrder.verify(noteRepository).getNoteById(1);
        inOrder.verify(noteRepository).getNoteById(2);
    }

    /**
     * 31. Тест на обновление заметки с вызовом updateNote в определенном порядке
     */
    @Test
    public void updateNoteCalledInOrderTest() {
        Note note1 = new Note(1, "Title1", "Content1");
        Note note2 = new Note(2, "Title2", "Content2");
        when(noteRepository.getNoteById(1)).thenReturn(note1);
        when(noteRepository.getNoteById(2)).thenReturn(note2);

        Note updatedNote1 = new Note(1, "Updated Title1", "Updated Content1");
        Note updatedNote2 = new Note(2, "Updated Title2", "Updated Content2");

        noteService.updateNote(updatedNote1);
        noteService.updateNote(updatedNote2);

        InOrder inOrder = inOrder(noteRepository);
        inOrder.verify(noteRepository).updateNote(updatedNote1);
        inOrder.verify(noteRepository).updateNote(updatedNote2);
    }

    /**
     * 32. Тест на удаление заметки с вызовом deleteNote в определенном порядке
     */
    @Test
    public void deleteNoteCalledInOrderTest() {
        Note note1 = new Note(1, "Title1", "Content1");
        Note note2 = new Note(2, "Title2", "Content2");
        when(noteRepository.getNoteById(1)).thenReturn(note1);
        when(noteRepository.getNoteById(2)).thenReturn(note2);

        noteService.deleteNote(1);
        noteService.deleteNote(2);

        InOrder inOrder = inOrder(noteRepository);
        inOrder.verify(noteRepository).deleteNote(1);
        inOrder.verify(noteRepository).deleteNote(2);
    }

    /**
     * 33. Тест на обновление заметки с пустым заголовком
     */
    @Test
    public void updateNoteWithEmptyTitleTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);

        Note updatedNote = new Note(1, "", "Content");

        assertThatThrownBy(() -> noteService.updateNote(updatedNote))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title cannot be empty");
    }

    /**
     * 34. Тест на обновление заметки с пустым содержанием
     */
    @Test
    public void updateNoteWithEmptyContentTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);

        Note updatedNote = new Note(1, "Title", "");

        assertThatThrownBy(() -> noteService.updateNote(updatedNote))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Content cannot be empty");
    }

    /**
     * 35. Тест на добавление заметки с пробелами в заголовке
     */
    @Test
    public void addNoteWithWhitespaceTitleTest() {
        Note note = new Note(1, " ", "Content");

        assertThatThrownBy(() -> noteService.addNote(note))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title cannot be whitespace");
    }

    /**
     * 36. Тест на добавление заметки с пробелами в содержании
     */
    @Test
    public void addNoteWithWhitespaceContentTest() {
        Note note = new Note(1, "Title", " ");

        assertThatThrownBy(() -> noteService.addNote(note))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Content cannot be null or whitespace");
    }

    /**
     * 37. Тест на обновление заметки с пробелами в заголовке
     */
    @Test
    public void updateNoteWithWhitespaceTitleTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);

        Note updatedNote = new Note(1, " ", "Content");

        assertThatThrownBy(() -> noteService.updateNote(updatedNote))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title cannot be whitespace");
    }

    /**
     * 38. Тест на обновление заметки с пробелами в содержании
     */
    
    @Test
    public void updateNoteWithWhitespaceContentTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);

        Note updatedNote = new Note(1, "Title", " ");

        assertThatThrownBy(() -> noteService.updateNote(updatedNote))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Content cannot be whitespace");
    }

    /**
     * 39. Тест на добавление заметки с null в заголовке
     */
    @Test
    public void addNoteWithNullTitleTest() {
        Note note = new Note(1, null, "Content");
        assertThatThrownBy(() -> noteService.addNote(note))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title cannot be null");
    }

    /**
     * 41. Тест на обновление заметки с null в заголовке
     */
    @Test
    public void updateNoteWithNullTitleTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);
        Note updatedNote = new Note(1, null, "Content");
        assertThatThrownBy(() -> noteService.updateNote(updatedNote))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Title cannot be null");
    }

    /**
     * 42. Тест на обновление заметки с null в содержании
     */
    @Test
    public void updateNoteWithNullContentTest() {
        Note note = new Note(1, "Title", "Content");
        when(noteRepository.getNoteById(1)).thenReturn(note);
        Note updatedNote = new Note(1, "Title", null);

        assertThatThrownBy(() -> noteService.updateNote(updatedNote))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Content cannot be null");
    }

    /**
     * 43. Тест на добавление заметки без вызова addNote
     */
    @Test
    public void addNoteNotCalledTest() {
        verify(noteRepository, never()).addNote(any(Note.class));
    }

    /**
     * 44. Тест на обновление заметки без вызова updateNote
     */
    @Test
    public void updateNoteNotCalledTest() {
        verify(noteRepository, never()).updateNote(any(Note.class));
    }

    /**
     * 45. Тест на удаление заметки без вызова deleteNote
     */
    @Test
    public void deleteNoteNotCalledTest() {
        verify(noteRepository, never()).deleteNote(anyInt());
    }

    /**
     * 46. Тест на получение заметки по ID без вызова getNoteById
     */
    @Test
    public void getNoteByIdNotCalledTest() {
        verify(noteRepository, never()).getNoteById(anyInt());
    }

    /**
     * 47. Тест на получение всех заметок без вызова getAllNotes
     */
    @Test
    public void getAllNotesNotCalledTest() {
        verify(noteRepository, never()).getAllNotes();
    }

    /**
     * 48. Тест на добавление заметки с вызовом addNote в определенном порядке
     */
    @Test
    public void addNoteCalledInOrderTest() {
        Note note1 = new Note(1, "Title1", "Content1");
        Note note2 = new Note(2, "Title2", "Content2");
        noteService.addNote(note1);
        noteService.addNote(note2);

        InOrder inOrder = inOrder(noteRepository);
        inOrder.verify(noteRepository).addNote(note1);
        inOrder.verify(noteRepository).addNote(note2);
    }


    @AfterAll
    static void closeConnectPool() {
        System.out.println("AfterAll: ");

    }
}
