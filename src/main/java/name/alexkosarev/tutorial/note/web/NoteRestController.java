/*
 * MIT License
 *
 * Copyright (c) 2019 Alexander Kosarev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package name.alexkosarev.tutorial.note.web;

import lombok.RequiredArgsConstructor;
import name.alexkosarev.tutorial.note.Note;
import name.alexkosarev.tutorial.note.NoteService;
import name.alexkosarev.tutorial.note.web.request.CreateNoteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("notes")
@RequiredArgsConstructor
public class NoteRestController {

    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> findAll() {
        return ResponseEntity.ok(this.noteService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Note> findOneById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(this.noteService.findOneById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Note> create(@RequestBody CreateNoteRequest request) {
        return ResponseEntity.ok(this.noteService.create(request.getTitle(), request.getContent()));
    }
}
